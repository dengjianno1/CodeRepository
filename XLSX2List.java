package icm.index.ashare.tools.OperateExcel;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackageAccess;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.util.SAXHelper;
import org.apache.poi.xssf.eventusermodel.ReadOnlySharedStringsTable;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler;
import org.apache.poi.xssf.eventusermodel.XSSFSheetXMLHandler.SheetContentsHandler;
import org.apache.poi.xssf.model.StylesTable;
import org.apache.poi.xssf.usermodel.XSSFComment;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

/**
 * 大数据量多Sheet的xlsx文件导入ArrayList<br>
 * 参考POI官方实例XLSX2CSV.java编写
 * @author dengjian
 * @since 2017-1-18
 */
public class XLSX2List {
    /**
     * Uses the XSSF Event SAX helpers to do most of the work
     *  of parsing the Sheet XML, and outputs the contents
     *  as a Java ArrayList&ltString[]&gt.
     */
    private class SheetToList implements SheetContentsHandler {
        private int currentRow = -1;
        private int currentCol = -1;
        public ArrayList<Object[]> sheetList=new ArrayList<Object[]>();
        private ArrayList<Object> rowList=new ArrayList<Object>();
        /**
         * 输出空行行号
         */
        private void outputMissingRows(int number) {
            for (int i=0; i<number; i++) {
            	int missRowNumber=currentRow+number+1;
                System.out.println("第"+missRowNumber+"行为空!");
            }
        }
        /**
         * 保存一行单元格到ArrayList&ltObject&gt
         */
        public void rowtoStrings(int rowNum,ArrayList<Object> list){
        	if (rowNum!=0) {
            	Object[] strings=new Object[list.size()];
            	for (int i = 0; i < list.size(); i++) {
    				strings[i]=list.get(i);
    			}
            	sheetList.add(strings);
			}
        }

        @Override
        public void startRow(int rowNum) {
	        // If there were gaps, output the missing rows
	        outputMissingRows(rowNum-currentRow-1);
	        currentRow = rowNum;
	        currentCol = -1;
        }

        @Override
        public void endRow(int rowNum) {
        	//sheet中有多少有效列,有第一行(表头)决定
        	if (rowNum==0) {
				minColumns=currentCol;
			}
	        //若该列无有效单元格,跳过该列
        	if (currentCol!=-1) {
        		// Ensure the minimum number of columns
    	        for (int i=currentCol; i<minColumns; i++) {
    		           rowList.add("");
    		        }
    	        rowtoStrings(rowNum,rowList);
    	        rowList.clear();
			}
        }

        @Override
        public void cell(String cellReference, String formattedValue,
                XSSFComment comment) {

            // gracefully handle missing CellRef here in a similar way as XSSFCell does
            if(cellReference == null) {
                cellReference = new CellAddress(currentRow, currentCol).formatAsString();
            }
            // Did we miss any cells?
            int thisCol = (new CellReference(cellReference)).getCol();
            int missedCols = thisCol - currentCol - 1;
            for (int i=0; i<missedCols; i++) {
                rowList.add("");
            }
            currentCol = thisCol;
            
            // Number or string?
            try {
                Double.parseDouble(formattedValue);
				rowList.add(formattedValue);
            } catch (NumberFormatException e) {
                rowList.add(formattedValue);
            }
        }
        /**
         * 不会进入,未利用上
         */
        @Override
        public void headerFooter(String text, boolean isHeader, String tagName) {
        }
    }


    ///////////////////////////////////////

    private final OPCPackage xlsxPackage;

    /**
     * Number of columns to read starting with leftmost
     */
    private int minColumns;
    /**
     * 存放整个xlsx数据的List
     */
    public ArrayList<Object[]> xlsxList=new ArrayList<Object[]>();

    /**
     * Creates a new XLSX -> CSV converter
     *
     * @param pkg        The XLSX package to process
     * @param sheetList  存放整个sheet数据的List
     */
    public XLSX2List(OPCPackage pkg) {
        this.xlsxPackage = pkg;
    }
    /**
     * Parses and shows the content of one sheet
     * using the specified styles and shared-strings tables.
     *
     * @param styles
     * @param strings
     * @param sheetInputStream
     */
    public void processSheet(
            StylesTable styles,
            ReadOnlySharedStringsTable strings,
            SheetContentsHandler sheetHandler, 
            InputStream sheetInputStream)
            throws IOException, ParserConfigurationException, SAXException {
        DataFormatter formatter = new DataFormatter();
        InputSource sheetSource = new InputSource(sheetInputStream);
        try {
            XMLReader sheetParser = SAXHelper.newXMLReader();
            ContentHandler handler = new XSSFSheetXMLHandler(
                  styles, null, strings, sheetHandler, formatter, false);
            sheetParser.setContentHandler(handler);
            sheetParser.parse(sheetSource);
         } catch(ParserConfigurationException e) {
            throw new RuntimeException("SAX parser appears to be broken - " + e.getMessage());
         }
    }

    /**
     * Initiates the processing of the XLS workbook file to CSV.
     *
     * @throws IOException
     * @throws OpenXML4JException
     * @throws ParserConfigurationException
     * @throws SAXException
     */
    public void process()
            throws IOException, OpenXML4JException, ParserConfigurationException, SAXException {
        ReadOnlySharedStringsTable strings = new ReadOnlySharedStringsTable(this.xlsxPackage);
        XSSFReader xssfReader = new XSSFReader(this.xlsxPackage);
        StylesTable styles = xssfReader.getStylesTable();
        XSSFReader.SheetIterator iter = (XSSFReader.SheetIterator) xssfReader.getSheetsData();
        while (iter.hasNext()) {
            InputStream stream = iter.next();
            String sheetName = iter.getSheetName();
            SheetToList sheetToList=new SheetToList();
            System.out.println("正在解析"+sheetName+"...");
            processSheet(styles, strings, sheetToList, stream);
            stream.close();
            //最容易报内存溢出的地方
            xlsxList.addAll(sheetToList.sheetList);
        }
    }
    /**
     * xlsx转list
     */
    public static ArrayList<Object[]> xlsxToList(String xlsxPath) throws Exception {
    	File xlsxFile = new File(xlsxPath);
        if (!xlsxFile.exists()) {
            System.err.println("Not found or not a file: " + xlsxFile.getPath());
            return null;
        }
        OPCPackage p = OPCPackage.open(xlsxFile.getPath(), PackageAccess.READ);
		XLSX2List xlsx2List = new XLSX2List(p);
		xlsx2List.process();
		p.close();
		return xlsx2List.xlsxList;
	}
    public static void main(String[] args) {
		System.out.println(Runtime.getRuntime().totalMemory());
		System.out.println(Runtime.getRuntime().maxMemory());
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	ArrayList<Object[]> list = null;
    	try {
			list=xlsxToList("./sources/导入数据测试_小.xlsx");
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (Object[] strings : list) {
			for (Object string : strings) {
				System.out.print(string+"  ");
			}
		System.out.println();
		}
	}
}
