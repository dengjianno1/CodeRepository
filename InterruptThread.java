package djsoft;
/**
 * interrupt方法用于中断线程。调用该方法的线程的状态为将被置为"中断"状态。<br>
 * interrupted();测试当前线程是否已经中断。线程的中断状态由该方法清除。<br>
 * 抛出InterruptedException异常后，中断标示位会自动清除<br>
 * 产生了死锁则不可能被中断。因为锁定的位置根本无法抛出异常。reentrantLock.tryLock除外。
 * @author dengjian
 * @since 2017-6-8
 */
public class InterruptThread {

	public static void main(String[] args) {
		new InterruptThread().doInterruptThread();
	}

	public void doInterruptThread() {
		Thread myThread = new Thread(new MyRunnable());
		myThread.start();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		myThread.interrupt();
		System.out.println("stoped");
	}

	class MyRunnable implements Runnable {
		@Override
		public void run() {
			while (!Thread.currentThread().isInterrupted()) {
				System.out.println("running..." + Thread.currentThread().isInterrupted());
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					System.err.println("中断被阻塞,请重新打中断标志位");
					Thread.currentThread().interrupt();
				}
			}
		}
	}
}
