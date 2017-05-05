package com.designpatterns;

/**
 * @author samratdutta
 * 
 * THUMB RULE
 * ----------
 * 1. We can keep Factory class Singleton or we can keep the method that returns the subclass as static.
 * 2. Notice that based on the input parameter, different subclass is created and returned. getComputer 
 *    is the factory method.
 * 3. Factory design pattern provides approach to code for interface rather than implementation.
 * 4. Factory pattern removes the instantiation of actual implementation classes from client code. 
 *    Factory pattern makes our code more robust, less coupled and easy to extend. For example, 
 *    we can easily change PC class implementation because client program is unaware of this.
 * 5. Factory pattern provides abstraction between implementation and client classes through inheritance.
 *
 */
public class Factory {

	public static void main(String[] args) {
		Computer pc = ComputerFactory.getComputer("PC", "500GB", "4-core", "8GB");
		Computer server = ComputerFactory.getComputer("Server", "10TB", "16-core", "72GB");
		System.out.println(pc);
		System.out.println(server);
	}
}

abstract class Computer {
	public abstract String getHDD();
	public abstract String getCPU();
	public abstract String getRAM();
	
	@Override
	public String toString(){
		return "HDD="+this.getHDD()+", RAM="+this.getRAM()+", CPU="+this.getCPU();
	}
}

class PC extends Computer {
	
	private String HDD;
	private String CPU;
	private String RAM;
	
	public PC(String HDD, String CPU, String RAM){
		this.HDD = HDD;
		this.CPU = CPU;
		this.RAM = RAM;
	}

	@Override
	public String getHDD() {
		return this.HDD;
	}

	@Override
	public String getCPU() {
		return this.CPU;
	}

	@Override
	public String getRAM() {
		return this.RAM;
	}
}

class Server extends Computer {
	
	private String HDD;
	private String CPU;
	private String RAM;
	
	public Server(String HDD, String CPU, String RAM){
		this.HDD = HDD;
		this.CPU = CPU;
		this.RAM = RAM;
	}

	@Override
	public String getHDD() {
		return this.HDD;
	}

	@Override
	public String getCPU() {
		return this.CPU;
	}

	@Override
	public String getRAM() {
		return this.RAM;
	}
}

class ComputerFactory {
	public static Computer getComputer(String type, String HDD, String CPU, String RAM) {
		if(type.equals("PC"))
			return new PC(HDD, CPU, RAM);
		else if(type.equals("Server"))
			return new Server(HDD, CPU, RAM);
		return null;
	}
}