package ����ʽ;

public class SingletonB {
	private static SingletonB singletonB=new SingletonB();
	private SingletonB() {
		System.out.println("���캯��");
	}
	public static SingletonB getInstance(){
		return singletonB;
	}
}
