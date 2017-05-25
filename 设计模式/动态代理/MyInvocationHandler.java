package djsoft;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyInvocationHandler implements InvocationHandler {
	private Object target;
	public MyInvocationHandler(Object target) {
		this.target=target;
	}
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("方法开始");
		method.invoke(target, args);//执行被代理target对象的方法
		System.out.println("方法结束");
		return null;
	}

}
