package Examples;

import java.util.LinkedList;
import java.util.List;

public class BlockingQueues {

	private List queue = new LinkedList();
	private int limit;
	
	public BlockingQueues(int limit) {
		this.limit = limit;
	}
	
	public synchronized void enqueue(Object o) throws InterruptedException {
		while(this.queue.size() == this.limit)
			this.wait();
		if(this.queue.size() == 0)
			notifyAll();
		this.queue.add(o);
	}
	
	public synchronized Object dequeue() throws InterruptedException {
		while(this.queue.size() == 0)
			this.wait();
		if(this.queue.size() == this.limit)
			notifyAll();
		return this.queue.remove(0);
	}
}
