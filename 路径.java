package newpath;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class 路径 {

	public static void main(String[] args) throws Exception {
		路径 路径=new 路径();
		路径.getPath();
	}
	public void getPath() throws Exception{
		//在jar包中寻找路径(只能在src源文件夹下)
		//String path=this.getClass().getClassLoader().getResource("./source/result.txt").getFile();
		//InputStream is=this.getClass().getClassLoader().getResourceAsStream("./source/result.txt");
		//打成jar包后与*.jar同级的source文件夹下的result.txt
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
