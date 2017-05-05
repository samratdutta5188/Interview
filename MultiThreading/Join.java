package Examples;

import Examples.RunnableThread.MyThread;

public class Join {
	
	public void joinExample(){
		RunnableThread rt = new RunnableThread();
		MyThread t1 = rt.getMyThread("t1");
		MyThread t2 = rt.getMyThread("t2");
		MyThread t3 = rt.getMyThread("t3");
		t1.start();
		try {
			t1.join(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		t2.start();
		try {
			t1.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		t3.start();
		try {
			t1.join();
			t2.join();
			t3.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Exiting Main Thread");
	}

}
