package test;

public class Bat {

	public static void main(String[] args) {
		Process process=null;
		String cmdStr="cmd /c start .\\source\\task.bat";//����������
		//String cmdStr="cmd /c start /b D:/task.bat";//������������
		try {
			process=Runtime.getRuntime().exec(cmdStr);
			process.waitFor();
		} catch (Exception e) {
			e.printStackTrace();
		}
		int i=process.exitValue();
		if (i==0) {
			System.out.println("ִ�����");
		}else {
			System.out.println("ִ��ʧ��");
		}
		process.destroy();
	}

}
