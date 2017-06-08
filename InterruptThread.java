package djsoft;
/**
 * interrupt���������ж��̡߳����ø÷������̵߳�״̬Ϊ������Ϊ"�ж�"״̬��<br>
 * interrupted();���Ե�ǰ�߳��Ƿ��Ѿ��жϡ��̵߳��ж�״̬�ɸ÷��������<br>
 * �׳�InterruptedException�쳣���жϱ�ʾλ���Զ����<br>
 * �����������򲻿��ܱ��жϡ���Ϊ������λ�ø����޷��׳��쳣��reentrantLock.tryLock���⡣
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
					System.err.println("�жϱ�����,�����´��жϱ�־λ");
					Thread.currentThread().interrupt();
				}
			}
		}
	}
}
