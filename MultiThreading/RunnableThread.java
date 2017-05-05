package Examples;

public class RunnableThread {

	class MyRunnable implements Runnable {

		@Override
		public void run() {
			System.out.println("Start MyRunnable - " + Thread.currentThread().getName());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("End MyRunnable - " + Thread.currentThread().getName());
		}

	}
	
	class MyThread extends Thread {

		public MyThread(String string) {
			super(string);
		}
		
		public void run(){
			System.out.println("Start MyThread - " + Thread.currentThread().getName());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("End MyThread - " + Thread.currentThread().getName());
		}

	}
	
	public MyRunnable getMyRunnable(){
		return new MyRunnable();
	}
	
	public MyThread getMyThread(String name){
		return new MyThread(name);
	}
}
