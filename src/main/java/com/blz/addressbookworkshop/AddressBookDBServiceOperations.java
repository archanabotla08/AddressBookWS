package com.blz.addressbookworkshop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class AddressBookDBServiceOperations {
	
	private static AddressBookDBServiceOperations addressBookDBService;
	private PreparedStatement addressBookPreparedStatement;
	private List<AddressBookData> addressBookData;
	private AddressBookDBServiceOperations() {
	}

	public static AddressBookDBServiceOperations getInstance() {
		if (addressBookDBService == null)
			addressBookDBService = new AddressBookDBServiceOperations();
		return addressBookDBService;
	}
	private Connection getConnection() throws SQLException {
		String jdbcURL = "jdbc:mysql://localhost:3306/ADDRESSBOOK?useSSL=false";
		String username = "root";
		String password = "root";
		Connection con;
		System.out.println("Connecting to database:" + jdbcURL);
		con = DriverManager.getConnection(jdbcURL, username, password);
		System.out.println("Connection is successful:" + con);
		return con;
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
	public int updateAddressBookData(String firstname, String address) throws AddressBookException {
		try (Connection connection = this.getConnection()) {
			String sql = String.format("update ADDRESSBOOKWS set ADDRESS = '%s' where FIRSTNAME = '%s';", address,
					firstname);
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			return preparedStatement.executeUpdate(sql);
		} catch (SQLException sqlException) {
			throw new AddressBookException(sqlException.getMessage(),
					AddressBookException.ExceptionType.DATABASE_EXCEPTION);
		}
	}

	public List<AddressBookData> getAddressBookData(String firstname) throws AddressBookException {
		if (this.addressBookPreparedStatement == null)
			this.prepareAddressBookStatement();
		try {
			addressBookPreparedStatement.setString(1, firstname);
			ResultSet resultSet = addressBookPreparedStatement.executeQuery();
			addressBookData = this.getAddressBookData(resultSet);
		} catch (SQLException sqlException) {
			throw new AddressBookException(sqlException.getMessage(),
					AddressBookException.ExceptionType.DATABASE_EXCEPTION);
		}
		return addressBookData;
	}

	private void prepareAddressBookStatement() throws AddressBookException {
		try {
			Connection connection = this.getConnection();
			String sql = "select * from ADDRESSBOOKWS where FIRSTNAME = ?";
			addressBookPreparedStatement = connection.prepareStatement(sql);
		} catch (SQLException sqlException) {
			throw new AddressBookException(sqlException.getMessage(),
					AddressBookException.ExceptionType.DATABASE_EXCEPTION);
		}
	}
	public List<AddressBookData> readData(LocalDate start, LocalDate end) throws AddressBookException {
		String query = null;
		if (start != null)
			query = String.format("select * from ADDRESSBOOKWS where startDate between '%s' and '%s';", start, end);
		if (start == null)
			query = "select * from ADDRESSBOOKWS";
		List<AddressBookData> addressBookList = new ArrayList<>();
		try (Connection con = this.getConnection();) {
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(query);
			addressBookList = this.getAddressBookData(rs);
		} catch (SQLException e) {
			System.out.println(e);
			throw new AddressBookException(e.getMessage(), AddressBookException.ExceptionType.DATABASE_EXCEPTION);
		}
		return addressBookList;
	}
	public int readDataPayroll(String total, String city) throws AddressBookException {
		int count = 0;
		String query = String.format("select %s(state) from ADDRESSBOOKWS where city = '%s' group by city;", total, city);
		try (Connection connection = this.getConnection()) {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			resultSet.next();
			count = resultSet.getInt(1);
		} catch (SQLException e) {
			throw new AddressBookException(e.getMessage(), AddressBookException.ExceptionType.DATABASE_EXCEPTION);
		}
		return count;
	}
	public AddressBookData addNewContact(String firstName, String lastName, String address, String city, String state,
			int zip, int phoneNumber, String email, String startDate,String endDate) throws AddressBookException, SQLException {
		int personID = -1;
		Connection connection=null;
		AddressBookData personDetails;
		String query = String.format(
				"insert into ADDRESSBOOKWS(FirstName, LastName, Address, City, State, Zip, PhoneNumber, Email, startDate,endDate) values ('%s', '%s', '%s', '%s', '%s', '%d', '%d', '%s')",
				firstName, lastName, address, city, state, zip, phoneNumber, email,startDate,endDate);
		try {
			connection = this.getConnection();
			Statement statement = connection.createStatement();
			int rowChanged = statement.executeUpdate(query, statement.RETURN_GENERATED_KEYS);
			if (rowChanged == 1) {
				ResultSet resultSet = statement.getGeneratedKeys();
				if (resultSet.next())
					personID = resultSet.getInt(1);
			}
			personDetails = new AddressBookData(personID,firstName, lastName, address, city, state, zip, phoneNumber, email,startDate,endDate);
		} catch (SQLException e) {
			throw new AddressBookException(e.getMessage(), AddressBookException.ExceptionType.DATABASE_EXCEPTION);
		}
		return personDetails;
	}
	public AddressBookData addNewContact(String firstName, String lastName, String address, String city, String state,
			long zip, long phoneNo, String email) throws AddressBookException, SQLException {
		int personID = -1;
		Connection connection = null;
		AddressBookData personDetails;
		String query = String.format(
				"insert into addressbook(FirstName, LastName, Address, City, State, Zip, PhoneNumber, Email) values ('%s', '%s', '%s', '%s', '%s', '%d', '%d', '%s')",
				firstName, lastName, address, city, state, zip, phoneNo, email);
		try {
			connection = this.getConnection();
			Statement statement = connection.createStatement();
			int rowChanged = statement.executeUpdate(query, statement.RETURN_GENERATED_KEYS);
			if (rowChanged == 1) {
				ResultSet resultSet = statement.getGeneratedKeys();
				if (resultSet.next())
					personID = resultSet.getInt(1);
			}
			personDetails = new AddressBookData(personID, firstName, lastName, address, city, state, zip, phoneNo, email);
		} catch (SQLException e) {
			throw new AddressBookException(e.getMessage(), AddressBookException.ExceptionType.DATABASE_EXCEPTION);
		} 
		return personDetails;
	}
}

