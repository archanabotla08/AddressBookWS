package com.blz.addressbookworkshop;



public class AddressBookRecord {
	
	public int id;
	public String bookName;
	
	public AddressBookRecord() {
		
	}
	public AddressBookRecord(int id,String bookName) {
		this.id = id;
		this.bookName = bookName;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getBookName() {
		return bookName;
	}
}


