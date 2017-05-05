package Examples;

import Examples.RunnableThread.MyThread;
import Examples.ThreadLocalExample.MyRunnable;

public class Test {

	public static void main(String[] args) throws InterruptedException {
		System.out.println("-----Daemon Threads-----");
		Thread dt = new Thread(new DeamonThread(), "dt");
		dt.setDaemon(true);
		dt.start();
		Thread.sleep(5000);
		System.out.println("Main Ends");
		Thread.sleep(10000);
		System.out.println();
		System.out.println("-----Join-----");
		Join join = new Join();
		join.joinExample();
		Thread.sleep(10000);
		System.out.println();
		System.out.println("-----Runnable Thread-----");
		RunnableThread rt = new RunnableThread();
		Thread t1 = new Thread(rt.getMyRunnable(), "t1");
		Thread t2 = new Thread(rt.getMyRunnable(), "t2");
		t1.start();
		t2.start();
		MyThread t3 = rt.getMyThread("t3");
		MyThread t4 = rt.getMyThread("t4");
		t3.start();
		t4.start();
		Thread.sleep(10000);
		System.out.println();
		System.out.println("-----ThreadLocal-----");
		MyRunnable sharedRunnableInstance = new MyRunnable();
		Thread t5 = new Thread(sharedRunnableInstance);
		Thread t6 = new Thread(sharedRunnableInstance);
		t5.start();
		t6.start();
		t5.join();
		t6.join();
	}

}
