package com.blz.addressbookworkshop;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class AddressBookOperations {
	
	public static List<AddressBookData> addressBookList = new ArrayList<>();
	
	public static void addPerson() {
		System.out.println("Enter person details : " + "\n");
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter first Name : ");
		String firstName = sc.next();
		System.out.print("Enter last Name : ");
		String lastName = sc.next();
		System.out.print("Enter Address : ");
		String address = sc.next();
		System.out.print("Enter City : ");
		String city = sc.next();
		System.out.print("Enter City : ");
		String state = sc.next();
		System.out.print("Enter Zip : ");
		long zip = sc.nextLong();
		System.out.print("Enter PhoneNumber : ");
		long phoneNumber = sc.nextLong();
		System.out.print("Enter Email Id : ");
		String email = sc.next();
		
		AddressBookData addPerson = new AddressBookData(firstName, lastName, address, city, state,zip, phoneNumber, email);
		addressBookList.add(addPerson);
		System.out.print("Person is Added Successfully");
		display();
	}
	public static void display() {
		System.out.println(addressBookList);
	}
	public static void main(String[] args) {
		//UC2
		addPerson();
	}

}
