package newpath;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class ·�� {

	public static void main(String[] args) throws Exception {
		·�� ·��=new ·��();
		·��.getPath();
	}
	public void getPath() throws Exception{
		//��jar����Ѱ��·��(ֻ����srcԴ�ļ�����)
		//String path=this.getClass().getClassLoader().getResource("./source/result.txt").getFile();
		//InputStream is=this.getClass().getClassLoader().getResourceAsStream("./source/result.txt");
		//���jar������*.jarͬ����source�ļ����µ�result.txt
		File file=new File("./source/result.txt");
		FileInputStream fis=new FileInputStream(file);
		InputStreamReader ipsr=new InputStreamReader(fis);
		BufferedReader bfr=new BufferedReader(ipsr);
		String string=null;
		while((string=bfr.readLine())!=null){
			System.out.println(string);
		}
		bfr.close();
	}
}
