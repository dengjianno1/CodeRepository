package learnpoi;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.openxml4j.opc.PackageAccess;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
/**
 * 事件模式下的xlsx读,Stream模式下的写,支持大数据量
 */
public class POIEventMode {

	public static void main(String[] args) {
		try {
			listToXLSX("E:\\test.xlsx");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			ArrayList<Object[]> list=xlsxToList("E:\\test.xlsx");
			for (Object[] objects : list) {
				System.out.println(objects[0]+"  "+objects[1]);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/**
	 * 写
	 */
	public static void listToXLSX(String path) throws Exception{
		@SuppressWarnings("resource")
		SXSSFWorkbook wb = new SXSSFWorkbook(1000); // keep 1000 rows in memory, exceeding rows will be flushed to disk
        Sheet sh = wb.createSheet("Sheet1");
		//输出内容
		for (int i = 0; i < 5; i++) {
			// Create a row and put some cells in it. Rows are 0 based.
			Row row = sh.createRow(i);
			// Create a cell and put a value in it.
			row.createCell(0).setCellValue("字符串");
			row.createCell(1).setCellValue(0.00000001);
		}
        FileOutputStream out = new FileOutputStream(path);
        wb.write(out);
        out.close();

        // dispose of temporary files backing this workbook on disk
        wb.dispose();
    }
	/**
	 * 读
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
}
