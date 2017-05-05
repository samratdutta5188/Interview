package Examples;

import java.util.ArrayList;
import java.util.List;

public class ThreadPools {

	private BlockingQueues taskQueue = null;
    private List<PoolThread> threads = new ArrayList<PoolThread>();
    private boolean isStopped = false;

    public ThreadPools(int noOfThreads, int maxNoOfTasks){
        taskQueue = new BlockingQueues(maxNoOfTasks);

        for(int i=0; i<noOfThreads; i++){
            threads.add(new PoolThread(taskQueue));
        }
        for(PoolThread thread : threads){
            thread.start();
        }
    }

    public synchronized void  execute(Runnable task) throws Exception{
        if(this.isStopped) throw
            new IllegalStateException("ThreadPool is stopped");

        this.taskQueue.enqueue(task);
    }

    public synchronized void stop(){
        this.isStopped = true;
        for(PoolThread thread : threads){
           thread.doStop();
        }
    }
}

class PoolThread extends Thread {
	private BlockingQueues taskQueue = null;
	private boolean isStopped = false;
	
	public PoolThread(BlockingQueues blockingQueues) {
		this.taskQueue = blockingQueues;
	}
	
	public void run() {
		while (!isStopped()) {
			try {
				Runnable runnable = (Runnable) taskQueue.dequeue();
				runnable.run();
			} catch(Exception e){
				e.printStackTrace();
			}
		}
	}

	public synchronized void doStop(){
		this.isStopped = true;
		//this.interrupt(); //break pool thread out of dequeue() call.
	}
	
	private synchronized boolean isStopped() {
		return isStopped;
	}
}