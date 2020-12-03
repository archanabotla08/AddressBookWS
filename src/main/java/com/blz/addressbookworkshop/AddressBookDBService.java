package com.blz.addressbookworkshop;

import java.sql.SQLException;
import java.util.List;

public class AddressBookDBService {
	public enum IOService {
		CONSOLE_IO, FILE_IO, DB_IO, REST_IO
	}

	private List<AddressBookData> addressBookList;
	private static AddressBookDBServiceOperations addressBookDBService;

	public AddressBookDBService() {
		addressBookDBService = AddressBookDBServiceOperations.getInstance();
	}

	public AddressBookDBService(List<AddressBookData> addresBookList) {
		this();
		this.addressBookList = addressBookList;
	}

	public List<AddressBookData> readAddressBookData(IOService ioservice) throws AddressBookException, SQLException {
		if (ioservice.equals(IOService.DB_IO))
			this.addressBookList = addressBookDBService.readData();
		return this.addressBookList;
	}

	
}
