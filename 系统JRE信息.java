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
		System.out.println("操作系统名称"+osName);
		System.out.println("操作系统架构"+osArch);
		System.out.println("操作系统版本"+osVersion);
		System.out.println("Java安装路径"+javaHome);
		System.out.println("JRE信息"+jreVersionB+"  "+jreVersionA);
	}

}
