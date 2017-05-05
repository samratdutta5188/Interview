package com.designpatterns;

public class Builder {

	public static void main(String[] args) throws Exception {
		User.UserBuilder builder = new User.UserBuilder();
		User user = builder.withName("sam").
				withPhone("7303454344").withEmail("sdfa@sdf.com").
				withAddress("Mantri Elite").withGender("Male").
				withCountry("Australia").build();
		System.out.println(user.toString());
	}

}

class User{

	public static class UserBuilder{
		private String name;
		private String email;
		private String phone;
		private String address;
		private String gender = "Male"; // optional: setting default to MALE
		private String country = "India"; // optional: setting default to INDIA
		
		public UserBuilder withName(String name) {
			this.name = name;
			return this;
		}
		public UserBuilder withEmail(String email) {
			this.email = email;
			return this;
		}
		public UserBuilder withPhone(String phone) {
			this.phone = phone;
			return this;
		}
		public UserBuilder withAddress(String address) {
			this.address = address;
			return this;
		}
		public UserBuilder withGender(String gender) {
			this.gender = gender;
			return this;
		}
		public UserBuilder withCountry(String country) {
			this.country = country;
			return this;
		}
		
		// checking mandatory fields
		public User build() throws Exception{
			if(this.name == null || this.phone == null || this.email == null || this.address == null)
				throw new IllegalStateException("Mandatory fields missing");
			return new User(this.name, this.phone, this.email, this.address, this.gender, this.country);
		}
		
	}
	
	private String name;
	private String email;
	private String phone;
	private String address;
	private String gender;
	private String country;
	
	public User(String name, String phone, String email, String address,
			String gender, String country) {
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.address = address;
		this.gender = gender;
		this.country = country;
	}
	
	@Override
	public String toString(){
		return this.name+", "+this.email+", "+this.phone+", "+this.address+", "+this.gender+", "+this.country;
	}
}
