package com.blz.addressbookworkshop;

public class AddressBookData {

	public String firstName;
	public String lastName;
	public String address;
	public String city;
	public String state;
	public long zip;
	public long phoneNumber;
	public String email;
	
	public AddressBookData(String firstName,String lastName,String address,String city,String state,long zip,long phoneNumber,String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}

	@Override
	public String toString() {
		return "AddressBookData [firstName=" + firstName + ", lastName=" + lastName + ", address=" + address + ", city="
				+ city + ",state=" +state + "  , zip=" + zip + ", phoneNumber=" + phoneNumber + ", email=" + email + "]";
	}
	
	
}
