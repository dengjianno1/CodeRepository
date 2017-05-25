package 饿汉式;

public class Singleton {
	private static Singleton singleton;
	private Singleton() {
		System.out.println("构造函数");
	}
	public static synchronized Singleton getInstance(){
		if (singleton==null) {
			System.out.println("进入判断");
			singleton = new Singleton();
		}
		return singleton;
	}
}
