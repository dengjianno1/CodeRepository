package ����ʽ;

public class Singleton {
	private static Singleton singleton;
	private Singleton() {
		System.out.println("���캯��");
	}
	public static synchronized Singleton getInstance(){
		if (singleton==null) {
			System.out.println("�����ж�");
			singleton = new Singleton();
		}
		return singleton;
	}
}
