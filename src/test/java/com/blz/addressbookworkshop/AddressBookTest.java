package com.blz.addressbookworkshop;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

import org.junit.BeforeClass;
import org.junit.Test;

public class AddressBookTest {

	static AddressBookIOServiceOperations addressBook;

	@BeforeClass
	public static void createAddressBookObj() {
		addressBook = new AddressBookIOServiceOperations();
	}
	
	@Test
	public void given3PersonDetailsWriteToFileJSONShouldMatchWithEntries() throws IOException {
		AddressBookData[] personDetailsAdd = {
				new AddressBookData("Archana", "Botla", "Somesh colony ", "Nanded","Maharashtra" ,431601, 98913526,
						"arcbot@gmail.com"),
				new AddressBookData("sweety", "shide", "Somesh colony ", "Nanded","Maharashtra" , 431601, 989143526,
						"arcbot@gmail.com"),
				new AddressBookData("sridhar", "Botla", "Somesh colony ", "Nanded","Maharashtra" , 431601, 989143526,
						"arcbot@gmail.com"),
				 };
		addressBook = new AddressBookIOServiceOperations(Arrays.asList(personDetailsAdd));
		AddressBookIOServiceOperations.writeAddressBookDataToJSON(com.blz.addressbookworkshop.AddressBookIOServiceOperations.IOService.FILE_IO);
		long entries = AddressBookIOService.countPersonInFile(com.blz.addressbookworkshop.AddressBookIOServiceOperations.IOService.FILE_IO);
		assertEquals(3, entries);
	}
	@Test
	public void readAddressBookFile() throws FileNotFoundException {
		addressBook.readAddressBookDataFromJSON();
		long entries = AddressBookIOService.countPersonInFile(com.blz.addressbookworkshop.AddressBookIOServiceOperations.IOService.FILE_IO);
		assertEquals(3, entries);
	}

}