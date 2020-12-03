package com.blz.addressbookworkshop;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AddressBookDBServiceOperations {
	
	private static AddressBookDBServiceOperations addressBookDBService;

	private AddressBookDBServiceOperations() {
	}

	public static AddressBookDBServiceOperations getInstance() {
		if (addressBookDBService == null)
			addressBookDBService = new AddressBookDBServiceOperations();
		return addressBookDBService;
	}

	public List<AddressBookData> readData() throws AddressBookException, SQLException  {
		String sql = "SELECT * FROM ADDRESSBOOKWS; ";
		return this.getAddressBookDataUsingDB(sql);
	}

	private List<AddressBookData> getAddressBookDataUsingDB(String sql) throws SQLException, AddressBookException  {
		List<AddressBookData> employeePayrollList = new ArrayList<>();
		try (Connection connection = AddressBookConnection.getConnection();) {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			employeePayrollList = this.getAddressBookData(resultSet);
		} catch (SQLException e) {
			throw new AddressBookException(e.getMessage(), AddressBookException.ExceptionType.DATABASE_EXCEPTION);
		}
		return employeePayrollList;
	}

	private List<AddressBookData> getAddressBookData(ResultSet resultSet) throws AddressBookException {
		List<AddressBookData> addressBookList = new ArrayList<>();
		try {
			while (resultSet.next()) {
				int id = resultSet.getInt("Id");
				String firstName = resultSet.getString("FIRSTNAME");
				String lastName = resultSet.getString("LASTNAME");
				String address = resultSet.getString("ADDRESS");
				String city = resultSet.getString("CITY");
				String state = resultSet.getString("STATE");
				long zipCode = resultSet.getLong("ZIPCODE");
				long phoneNumber = resultSet.getLong("PHONENUMBER");
				String emailId = resultSet.getString("EMAIL");
			
				
				addressBookList.add(new AddressBookData(id, firstName, lastName, address, city,
						state ,	zipCode,phoneNumber, emailId));
			}
		} catch (SQLException e) {
			throw new AddressBookException(e.getMessage(), AddressBookException.ExceptionType.DATABASE_EXCEPTION);
		}
		return addressBookList;
	}

}
