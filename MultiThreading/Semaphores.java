package Examples;

public class Semaphores {

	private boolean signal = false;
	
	public synchronized void take(){
		this.signal = true;
		this.notify();
	}
	
	public synchronized void release() throws InterruptedException {
		while(!this.signal)
			wait();
		this.signal = false;
	}
}

class CountingSemaphores {

	private int signal = 0;
	
	public synchronized void take(){
		this.signal++;
		this.notify();
	}
	
	public synchronized void release() throws InterruptedException {
		while(this.signal == 0)
			wait();
		this.signal--;
	}
}

class BoundedSemaphores {

	private int signal = 0;
	private int bound;
	
	public BoundedSemaphores(int bound) {
		this.bound = bound;
	}
	
	public synchronized void take() throws InterruptedException {
		while(this.signal == bound)
			wait();
		this.signal++;
		this.notify();
	}
	
	public synchronized void release() throws InterruptedException {
		while(this.signal == 0)
			wait();
		this.signal--;
		this.notify();
	}
}
