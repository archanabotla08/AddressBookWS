package com.blz.addressbookworkshop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class AddressBookOperations {

	public static Scanner sc = new Scanner(System.in);
	public static List<AddressBookData> addressBookList = new ArrayList<>();
	public static List<AddressBookRecord> bookNameList = new ArrayList<>();
	public static Map<Integer, String> booksList = new HashMap<>();
  public boolean  result;

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
	
	public static void deletePerson() {
		if (addressBookList.isEmpty()) {
			System.out.println("No Enteries in Address Book : Delete Invalid");
			addressBookCRUDOperationChoice();
		} else {
			for (AddressBookData i : addressBookList) {
				System.out.println(addressBookList.indexOf(i) + " : " + i.getFirstName());
			}
			System.out.println("Enter the firstName to delete that person details : ");
			String deletePerson = sc.next();
			for(int i=0;i< addressBookList.size(); i++){
				if(addressBookList.get(i).getFirstName().equals(deletePerson)){
					addressBookList.remove(addressBookList.get(i));
				}
			}
			System.out.println("Deleted Successfully: " + " : " + deletePerson);
			addressBookCRUDOperationChoice();
		}

	}

	public static void addMultiplePersons(){
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the Number of Persons to add :");
		int numofContacts = sc.nextInt();
		int createdContacts = 1;
		while (createdContacts <= numofContacts) {
			if ((addressBookWithUniqueName() == true) && (noDuplicateEntry() == true)) {
				addPerson();
			}else {
				addressBookCRUDOperationChoice();
			}
			createdContacts++;
		}
		System.out.println("Number of Persons " + numofContacts +" Add Successfully");
		addressBookCRUDOperationChoice();	
	}
	public static boolean addressBookWithUniqueName() {
		System.out.println("FirstName of a person is referred to as AddressBookName");
		System.out.println("Enter First Name");
		String firstName = sc.next();
		for (int count = 0; count < addressBookList.size(); count++) {
			if (addressBookList.get(count).getFirstName().equals(firstName)) {
				System.out.println("Already an AddressBook exist with this name");
				return false;
			} else {
				return true;
			}
		}
		return true;
	}

	public static boolean noDuplicateEntry() {
		System.out.println("Enter your First Name");
		String name = sc.next();
		for (int count = 0; count < addressBookList.size(); count++) {
			if (addressBookList.get(count).getFirstName().equals(name)) {
				System.out.println("Already an AddressBook exist with this name");
				return false;
			} else {
				return true;
			}
		}
		return true;
	}

	
	public static void addAddressBook() {
		boolean  result = false;
		System.out.println("Enter the Address Book Id and Name : ");
		int id = sc.nextInt();
		String name = sc.next();
		AddressBookRecord books = new AddressBookRecord(id, name);

		if (bookNameList.isEmpty()) {
			System.out.println("check");
			bookNameList.add(books);
			booksList.put(id, name);
			System.out.println("Address Book Added Successfully: ");
			for (AddressBookRecord i : bookNameList) {
				System.out.println(i.bookName);
			}
		} else {
			for (int i = 0; i < bookNameList.size(); i++) {
				if (bookNameList.get(i).getBookName().equals(name)) {
					result = bookNameList.get(i).getBookName().equals(name);
					System.out.println("Address Book Already Exists");
					break;
				}
			}
			if (!result) {
				bookNameList.add(books);
				booksList.put(id, name);
				System.out.println("Address Book Added Successfully: ");
			}

		}

		addressBookCRUDOperationChoice();

	}
	
	public static void displayAddressBookRecord() {
		for (Map.Entry m : booksList.entrySet()) {
			System.out.println(m.getKey() + " " + m.getValue());
		}
	}

	
	public static void addressBookCRUDOperationChoice() {
		int choice;
		System.out.println("Menu Item: " + "\n" + "1: Add Person" + "\n" + "2: Display " + "\n" + "3: Edit person"
				+ "\n" + "4: Delete Person" + "\n" + "5: Add Multiple Persons " + "\n" + "6: Add Address Book " + "\n"
				+ "7: Display Address Book Record " + "\n"  + "8: Exit");
		while (true) {
			System.out.println("Enter the choice");
			choice = sc.nextInt();
			switch (choice) {
			case 1:
				if ((addressBookWithUniqueName() && noDuplicateEntry()) == true) {
					addPerson();
				}else {
					addressBookCRUDOperationChoice();
				}
				
				break;
			case 2:
				display();
				break;
			case 3:
				editPerson();
			case 4:
				deletePerson();
			case 5:
					addMultiplePersons();
			case 6:
				addAddressBook();
			case 7:
				displayAddressBookRecord();
			case 8:
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
