package djsoft;

import java.util.concurrent.atomic.AtomicBoolean;

public class StopThread {

	public static void main(String[] args) throws InterruptedException {
		new StopThread().doStopThread();
	}

	public void doStopThread() {
		MyRunnable myRunnable = new MyRunnable();
		new Thread(myRunnable).start();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		myRunnable.runFlag.set(false);
		System.out.println("stoped");
	}

	class MyRunnable implements Runnable {
		AtomicBoolean runFlag = new AtomicBoolean(true);

		@Override
		public void run() {
			while (runFlag.get()) {
				System.out.println("runing...");
			}
		}
	}
}
