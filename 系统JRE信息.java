package test;

import java.util.Properties;

public class WindosInfo {

	public static void main(String[] args) {
		Properties properties=System.getProperties();
		String osName=properties.getProperty("os.name");
		String osArch=properties.getProperty("os.arch");
		String osVersion=properties.getProperty("os.version");
		String jreVersionA=properties.getProperty("java.vm.name");
		String jreVersionB=properties.getProperty("java.version");
		String javaHome=properties.getProperty("java.home");
		System.out.println("����ϵͳ����"+osName);
		System.out.println("����ϵͳ�ܹ�"+osArch);
		System.out.println("����ϵͳ�汾"+osVersion);
		System.out.println("Java��װ·��"+javaHome);
		System.out.println("JRE��Ϣ"+jreVersionB+"  "+jreVersionA);
	}

}
