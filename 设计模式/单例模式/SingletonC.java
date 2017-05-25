package ¶öººÊ½;

public class SingletonC {
	private static SingletonC singletonc;
	private SingletonC() {
	}
	public static SingletonC getInstance(){
		if (singletonc==null) {
			synchronized (SingletonC.class) {
				if (singletonc==null) {
					System.out.println("½øÈëÅÐ¶Ï");
					singletonc = new SingletonC();
				}
			}
		}
		return singletonc;
	}
}
