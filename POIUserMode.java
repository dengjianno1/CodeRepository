package learnpoi;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
/**
 * �û�ģʽ�µ�xlsx��д,��֧��С������(һ��10������¼����)
 *
 */
public class POIUserMode {

	public static void main(String[] args) {
		try {
			listToXLSX("E:\\test2.xlsx");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			xlsxToList("E:\\test2.xlsx");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * д
	 */
	public static void listToXLSX(String path) throws Exception{
		@SuppressWarnings("resource")
		Workbook wb = new XSSFWorkbook(); 
		Sheet sheet = wb.createSheet("Sheet1");
		//�������
		for (int i = 0; i < 5; i++) {
			// Create a row and put some cells in it. Rows are 0 based.
			Row row = sheet.createRow(i+1);
			// Create a cell and put a value in it.
			row.createCell(0).setCellValue("�ַ���");
			row.createCell(1).setCellValue(1.999999999);
		}
		// Write the output to a file
		FileOutputStream fileOut = new FileOutputStream(path);
		wb.write(fileOut);
		fileOut.close();
	}
	/**
	 * ��
	 */
    public static void xlsxToList(String path) throws Exception {
        Workbook wb = new XSSFWorkbook(new FileInputStream(path));
        for (int i = 0; i < wb.getNumberOfSheets(); i++) {
            Sheet sheet = wb.getSheetAt(i);
            for (Row row : sheet) {
                for (Cell cell : row) {
                    System.out.print(cell.toString()+"  ");
                }
                System.out.println();
            }
        }
        wb.close();
    }
}
