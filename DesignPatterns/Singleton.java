package com.designpatterns;

import java.io.Serializable;

public class Singleton {
	/**
	 * THUMB RULE
	 * ----------
	 * 1. Private constructor to restrict instantiation of the class from other classes.
	 * 2. Private static variable of the same class that is the only instance of the class.
	 * 3. Public static method that returns the instance of the class, this is the global 
	 *    access point for outer world to get the instance of the singleton class.
	 */
}

/**
 * Eager initialization
 */
class EagerInitializationSingleton {
	private static final EagerInitializationSingleton instance = new EagerInitializationSingleton();
	private EagerInitializationSingleton(){}
	public static EagerInitializationSingleton getInstance() {
		return instance;
	}
}

/**
 * Static block initialization
 */
class StaticBlockInitializationSingleton {
	private static StaticBlockInitializationSingleton instance;
	private StaticBlockInitializationSingleton(){}
	static {
		try {
			instance = new StaticBlockInitializationSingleton();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static StaticBlockInitializationSingleton getInstance() {
		return instance;
	}
}

/**
 * Lazy initialization
 */
class LazyInitializationSingleton {
	private static LazyInitializationSingleton instance;
	private LazyInitializationSingleton(){}
	public static LazyInitializationSingleton getInstance() {
		if(instance == null)
			instance = new LazyInitializationSingleton();
		return instance;
	}
}

/**
 * Thread safe
 */
class ThreadSafeSingleton {
	private static ThreadSafeSingleton instance;
	private ThreadSafeSingleton(){}
	public static synchronized ThreadSafeSingleton getInstance() {
		if(instance == null)
			instance = new ThreadSafeSingleton();
		return instance;
	}
}

/**
 * Double checking Thread safe
 */
class DoubleCheckingThreadSafeSingleton {
	private static DoubleCheckingThreadSafeSingleton instance;
	private DoubleCheckingThreadSafeSingleton(){}
	public static DoubleCheckingThreadSafeSingleton getInstance() {
		if(instance == null){
			synchronized (DoubleCheckingThreadSafeSingleton.class) {
				if(instance == null)
					instance = new DoubleCheckingThreadSafeSingleton();
			}
		}
		return instance;
	}
}

/**
 * Serialized Singleton
 */
class SerializedSingleton implements Serializable {
	private static final long serialVersionUID = 5457251671384857756L;
	private SerializedSingleton(){}
	private static class SerializedSingletonHelper{
		private static final SerializedSingleton instance = new SerializedSingleton();
	}
	public static SerializedSingleton getInstance() {
		return SerializedSingletonHelper.instance;
	}
	protected Object readResolve(){
		return getInstance();
	}
}