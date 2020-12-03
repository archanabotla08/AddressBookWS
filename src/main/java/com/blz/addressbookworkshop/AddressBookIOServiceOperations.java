package com.blz.addressbookworkshop;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.opencsv.CSVReader;


public class AddressBookIOServiceOperations {

	public static  List<AddressBookData> addressBookList = new ArrayList<>();
	Scanner sc = new Scanner(System.in);

	public enum IOService {
		CONSOLE_IO, FILE_IO, DB_IO, REST_IO
	}
	public AddressBookIOServiceOperations() {
		
	}
	public AddressBookIOServiceOperations(List<AddressBookData> addressBookList) {
		super();
		this.addressBookList = addressBookList;
	}
	public static void writeAddressBookData(IOService ioService) {
		if (ioService.equals(com.blz.addressbookworkshop.AddressBookIOServiceOperations.IOService.CONSOLE_IO))
			System.out.println("Person Details : " + addressBookList);
		if (ioService.equals(com.blz.addressbookworkshop.AddressBookIOServiceOperations.IOService.FILE_IO))
			new AddressBookIOService().writeDataToFile(addressBookList);

	}
	
	public void readDataFromFile() {
		System.out.println("Enter address book name: ");
		String addressBookFile = sc.nextLine();
		Path filePath = Paths.get("C:\\Users\\AB\\eclipse-workspace\\AddressBookWorkshop\\static\\" + addressBookFile + ".txt");
		try {
			Files.lines(filePath).map(line -> line.trim()).forEach(line -> System.out.println(line));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public long countEntries(IOService ioService) {
		if (ioService.equals(IOService.FILE_IO))
			return new AddressBookIOService().countPersonInFile(ioService);
		return 0;
	}
	
	// UC14 - file operation to write into csv and read from csv

		public static void writeAddressBookDataToCSV(IOService ioService) {
			if (ioService.equals(com.blz.addressbookworkshop.AddressBookIOServiceOperations.IOService.CONSOLE_IO))
				System.out.println("Person Details : " + addressBookList);
			if (ioService.equals(com.blz.addressbookworkshop.AddressBookIOServiceOperations.IOService.FILE_IO))
				new AddressBookIOService().writeToCSV(addressBookList);
		}

		public void readDataFromCSV() {
			System.out.println("Enter address book name: ");
			String addressBookData = sc.next();
			CSVReader reader = null;
			try {
				reader = new CSVReader(new FileReader(
						"C:\\Users\\AB\\eclipse-workspace\\AddressBookWorkshop\\static\\" + addressBookData + ".csv"));
				String[] data;
				while ((data = reader.readNext()) != null) {
					for (String person : data) {
						System.out.println(person);
					}
					System.out.println("\n");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		public long countCSVEntries(IOService ioService) {
			if (ioService.equals(IOService.FILE_IO))
				return new AddressBookIOService().countPersonInCSV(ioService);
			return 0;
		}

}
