package Examples;

public class DeamonThread implements Runnable {

	@Override
	public void run() {
		int i=0;
		while(i++<5){
			process();
		}
	}

	private void process() {
		try {
			System.out.println("Processing Daemon Thread");
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
