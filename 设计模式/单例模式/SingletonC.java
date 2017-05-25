package ����ʽ;

public class SingletonC {
	private static SingletonC singletonc;
	private SingletonC() {
	}
	public static SingletonC getInstance(){
		if (singletonc==null) {
			synchronized (SingletonC.class) {
				if (singletonc==null) {
					System.out.println("�����ж�");
					singletonc = new SingletonC();
				}
			}
		}
		return singletonc;
	}
}
