package com.blz.addressbookworkshop;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.blz.addressbookworkshop.AddressBookDBService.IOService;;

public class AddressBookTest {

	static AddressBookDBService addressBookDBService;
	
	@BeforeClass
	public static void AddressBookServiceObj() {
		addressBookDBService = new AddressBookDBService();
	}
	@Test
	public void givenAddressBookContactsInDB_WhenRetrieved_ShouldMatchContactsCount() throws AddressBookException, SQLException {
		List<AddressBookData> addressBookData = addressBookDBService.readAddressBookData(IOService.DB_IO);
		assertEquals(3, addressBookData.size());
	}

	@Test
	public void givenAddressBook_WhenUpdate_ShouldSyncWithDB() throws AddressBookException, SQLException {
		List<AddressBookData> personDetails = addressBookDBService.readAddressBookData(IOService.DB_IO);
		addressBookDBService.updateContactAddress("SWEETY", "PIMRINAGAR");
		boolean result = addressBookDBService.checkAddressBookInSyncWithDatabase("SWEETY");
		assertTrue(result);
	}

}
