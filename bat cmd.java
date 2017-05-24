package test;

public class Bat {

	public static void main(String[] args) {
		Process process=null;
		String cmdStr="cmd /c start .\\source\\task.bat";//带批处理窗口
		//String cmdStr="cmd /c start /b D:/task.bat";//不带批处理窗口
		try {
			process=Runtime.getRuntime().exec(cmdStr);
			process.waitFor();
		} catch (Exception e) {
			e.printStackTrace();
		}
		int i=process.exitValue();
		if (i==0) {
			System.out.println("执行完成");
		}else {
			System.out.println("执行失败");
		}
		process.destroy();
	}

}
