package com.blz.addressbookworkshop;

import java.sql.SQLException;
import java.time.LocalDate;
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

	public void updateContactAddress(String firstname, String address) throws AddressBookException {
		int result = addressBookDBService.updateAddressBookData(firstname, address);
		if (result == 0)
			throw new AddressBookException("Address book update failed",
					AddressBookException.ExceptionType.DATABASE_EXCEPTION);
		AddressBookData addressBookData = this.getAddressBookData(firstname);
		if (addressBookData != null)
			addressBookData.address = address;
	}

	private AddressBookData getAddressBookData(String firstname) {
		return this.addressBookList.stream().filter(addressBookItem -> addressBookItem.firstName.equals(firstname))
				.findFirst().orElse(null);
	}

	public boolean checkAddressBookInSyncWithDatabase(String firstname) throws AddressBookException {
		try {
			List<AddressBookData> addressBookData = addressBookDBService.getAddressBookData(firstname);
			return addressBookData.get(0).equals(getAddressBookData(firstname));
		} catch (AddressBookException addressBookException) {
			throw new AddressBookException("Cannot execute query",
					AddressBookException.ExceptionType.DATABASE_EXCEPTION);
		}
	}
	public List<AddressBookData> readEmployeePayrollData(IOService ioService, String start, String end)
			throws AddressBookException {
		try {
			LocalDate startLocalDate = LocalDate.parse(start);
			LocalDate endLocalDate = LocalDate.parse(end);
			if (ioService.equals(IOService.DB_IO))
				return addressBookDBService.readData(startLocalDate, endLocalDate);
			return this.addressBookList;
		} catch (AddressBookException e) {
			throw new AddressBookException(e.getMessage(), AddressBookException.ExceptionType.DATABASE_EXCEPTION);
		}
	}

	
}
