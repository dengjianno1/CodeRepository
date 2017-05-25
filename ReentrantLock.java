package lthread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Demo_3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Printer3 p = new Printer3();
		new Thread() {
			@Override
			public void run() {
				while (true) {
					try {
						p.print1();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}.start();

		new Thread() {
			@Override
			public void run() {
				while (true) {
					try {
						p.print2();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}.start();

		new Thread() {
			@Override
			public void run() {
				while (true) {
					try {
						p.print3();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}.start();
	}
}

class Printer3 {
	private int flag = 1;
	private ReentrantLock r = new ReentrantLock();
	private Condition c1=r.newCondition();
	private Condition c2=r.newCondition();
	private Condition c3=r.newCondition();
	public void print1() throws InterruptedException {
		r.lock();
		while (flag != 1) {
			c1.await();
		}
		System.out.print("A");
		System.out.print("B");
		System.out.print("C");
		System.out.print("D");
		System.out.println();
		flag = 2;
		c2.signal();
		r.unlock();

	}

	public void print2() throws InterruptedException {
		r.lock();
		while (flag != 2) {
			c2.await();
		}
		System.out.print("¼×");
		System.out.print("ÒÒ");
		System.out.print("±û");
		System.out.print("¶¡");
		System.out.println();
		flag = 3;
		c3.signal();
		r.unlock();
	}

	public void print3() throws InterruptedException {
		r.lock();
		while (flag != 3) {
			c3.await();
		}
		System.out.print("1");
		System.out.print("2");
		System.out.print("3");
		System.out.print("4");
		System.out.print("\r\n");
		flag = 1;
		c1.signal();
		r.unlock();
	}
}
