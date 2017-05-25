package djsoft;

import java.lang.reflect.Proxy;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GunPanther gunPanther=new GunPanther();
		MyInvocationHandler m=new MyInvocationHandler(gunPanther);
		Panther panther=(Panther) Proxy.newProxyInstance(GunPanther.class.getClassLoader(), GunPanther.class.getInterfaces(), m);
		panther.run();
	}

}
