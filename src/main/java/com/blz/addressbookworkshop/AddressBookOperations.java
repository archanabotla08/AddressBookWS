package com.blz.addressbookworkshop;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class AddressBookOperations {

	public static Scanner sc = new Scanner(System.in);
	public static List<AddressBookData> addressBookList = new ArrayList<>();

	public static void addPerson() {
		System.out.println("Enter person details : " + "\n");

		System.out.print("Enter first Name : ");
		String firstName = sc.next();
		System.out.print("Enter last Name : ");
		String lastName = sc.next();
		System.out.print("Enter Address : ");
		String address = sc.next();
		System.out.print("Enter City : ");
		String city = sc.next();
		System.out.print("Enter State : ");
		String state = sc.next();
		System.out.print("Enter Zip : ");
		long zip = sc.nextLong();
		System.out.print("Enter PhoneNumber : ");
		long phoneNumber = sc.nextLong();
		System.out.print("Enter Email Id : ");
		String email = sc.next();

		AddressBookData addPerson = new AddressBookData(firstName, lastName, address, city, state, zip, phoneNumber,
				email);
		addressBookList.add(addPerson);
		System.out.print("Person is Added Successfully");
		display();
	}

	public static void editPerson() {
		if (addressBookList.isEmpty()) {
			System.out.println("No Enteries in Address Book");
		} else {

			for (AddressBookData i : addressBookList) {
				System.out.println(addressBookList.indexOf(i) + " : " + i.getFirstName());
			}
			System.out.println("Enter the firstName  and index to edit that person details : ");
			String editPerson = sc.next();
			int index = sc.nextInt();
			while (true) {
				System.out.println("Enter Choice to edit the details : " + "1: lastName " + "2: address " + "3: city "
						+ "4: state " + "5: zip " + "6: phoneNumber " + "7: email"  + "8: exit");
				int choice = sc.nextInt();
				switch (choice) {
				case 1:
					System.out.println("Enter LastName: ");
					String lastName = sc.next();
					addressBookList.get(index).setLastName(lastName);
					display();
					break;
				case 2:
					System.out.println("Enter Address: ");
					String address = sc.next();
					addressBookList.get(index).setAddress(address);
					display();
					break;
				case 3:
					System.out.println("Enter City: ");
					String city = sc.next();
					addressBookList.get(index).setCity(city);
					break;
				case 4:
					System.out.println("Enter State: ");
					String state = sc.next();
					addressBookList.get(index).setState(state);
					break;
				case 5:
					System.out.println("Enter Zip: ");
					long zip = sc.nextLong();
					addressBookList.get(index).setZip(zip);
					;
					break;
				case 6:
					System.out.println("Enter PhoneNumber: ");
					long phoneNumber = sc.nextLong();
					addressBookList.get(index).setPhoneNumber(phoneNumber);
					break;
				case 7:
					System.out.println("Enter Email: ");
					String email = sc.next();
					addressBookList.get(index).setEmail(email);
					break;
				case 8:
					addressBookCRUDOperationChoice();
				default:
					System.out.println("InValid Choice !!!!");
					editPerson();
				}

			}

		}
	}

	public static void addressBookCRUDOperationChoice() {
		int choice;
		System.out.println("Menu Item: " + "\n" + "1: Add Person" + "\n" + "2: Display " + "\n" + "3: Edit person"
				+ "\n" + "4: Exit");
		Label: while (true) {
			System.out.println("Enter the choice");
			choice = sc.nextInt();
			switch (choice) {
			case 1:
				addPerson();
				break;
			case 2:
				display();
				break;
			case 3:
				editPerson();
			case 4:
				System.exit(0);
				break;
			default:
				System.out.println("Invalid Choice");
				break;
			}

		}
	}

	public static void display() {
		System.out.println(addressBookList);
	}

	public static void main(String[] args) {
		AddressBookOperations addressBookObject = new AddressBookOperations();
		addressBookObject.addressBookCRUDOperationChoice();
	}

}
