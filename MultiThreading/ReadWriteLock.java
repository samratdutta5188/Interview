package Examples;

import java.util.HashMap;
import java.util.Map;

public class ReadWriteLock {

	private Map<Thread, Integer> readingThreads = new HashMap<Thread, Integer>();
	private int writeRequests = 0;
	private int writeAccesses = 0;
	private Thread writingThread = null;
	
	public synchronized void lockRead() throws InterruptedException {
		Thread callingThread = Thread.currentThread();
		while(!canGrantReadAccess(callingThread))
			wait();
		readingThreads.put(callingThread, getReadAccessCount(callingThread) + 1);
	}
	
	public synchronized void unlockRead() throws InterruptedException {
		Thread callingThread = Thread.currentThread();
		if(!isReader(callingThread))
			throw new IllegalMonitorStateException("Calling thread does not have Read access");
		readingThreads.put(callingThread, readingThreads.get(callingThread) - 1);
		if(readingThreads.get(callingThread) == 0)
			readingThreads.remove(callingThread);
		notifyAll();
	}
	
	public synchronized void lockWrite() throws InterruptedException {
		writeRequests++;
		Thread callingThread = Thread.currentThread();
		while(!canGrantWriteAccess(callingThread))
			wait();
		writeRequests--;
		writeAccesses++;
		writingThread = callingThread;
	}
	
	public synchronized void unlockWrite() throws InterruptedException {
		if(!isWriter(Thread.currentThread()))
			throw new IllegalMonitorStateException("Calling thread does not have Write access");
		writeAccesses--;
		if(writeAccesses == 0)
			writingThread = null;
		notifyAll();
	}
	
	private boolean canGrantReadAccess(Thread callingThread) {
		if(isWriter(callingThread)) return true;
		if(hasWriter()) return false;
		if(isReader(callingThread)) return true;
		if(hasWriteRequests()) return false;
		return true;
	}

	private boolean canGrantWriteAccess(Thread callingThread) {
		if(isOnlyReader(callingThread)) return true;
		if(hasReaders()) return false;
		if(writingThread == null) return true;
		if(!isWriter(callingThread)) return false;
		return true;
	}
	
	private int getReadAccessCount(Thread callingThread) {
		return readingThreads.containsKey(callingThread) ? readingThreads.get(callingThread) : 0;
	}
	
	private boolean hasReaders() {
		return readingThreads.size() > 0;
	}
	
	private boolean isReader(Thread callingThread) {
		return readingThreads.get(callingThread) != null;
	}
	
	private boolean isOnlyReader(Thread callingThread) {
		return readingThreads.size() == 1 & readingThreads.get(callingThread) != null;
	}
	
	private boolean hasWriter() {
		return this.writingThread != null;
	}
	
	private boolean isWriter(Thread callingThread) { 
		return this.writingThread == callingThread;
	}
	
	private boolean hasWriteRequests(){
		return this.writeRequests > 0;
	}
}
