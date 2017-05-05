package com.designpatterns;

public class Strategy {

	public static void main(String[] args) {
		ShoppingCart cart = new ShoppingCart();
		cart.pay(new PaypalStrategy("myemail@example.com", "mypwd"), 100);
		cart.pay(new CreditCardStrategy("Pankaj Kumar", "1234567890123456", "786", "12/15"), 100);
	}

}

interface PaymentStrategy {
	public void pay(int amount);
}

class CreditCardStrategy implements PaymentStrategy {

	private String name;
	private String cardNumber;
	private String cvv;
	private String dateOfExpiry;
	
	public CreditCardStrategy(String name, String cardNumber, String cvv, String dateOfExpiry) {
		this.cardNumber = cardNumber;
		this.cvv = cvv;
		this.dateOfExpiry = dateOfExpiry;
		this.name = name;
	}
	
	@Override
	public void pay(int amount) {
		System.out.println(amount +" paid with credit/debit card");
	}
	
}

class PaypalStrategy implements PaymentStrategy {
	
	private String emailId;
	private String password;
	
	public PaypalStrategy(String email, String pwd){
		this.emailId=email;
		this.password=pwd;
	}
	
	@Override
	public void pay(int amount) {
		System.out.println(amount +" paid with paypal");
	}
}

class ShoppingCart {
	void pay(PaymentStrategy paymentStrategy, int amount){
		paymentStrategy.pay(amount);
	}
}