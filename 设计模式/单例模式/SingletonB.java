package 饿汉式;

public class SingletonB {
	private static SingletonB singletonB=new SingletonB();
	private SingletonB() {
		System.out.println("构造函数");
	}
	public static SingletonB getInstance(){
		return singletonB;
	}
}
