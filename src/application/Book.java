package application;

public class Book {

	Integer Item_id; 
	String Title, Author, Year , CopyQuantity, LendedOutQuantity, Type;
	
	public Book(Integer book_id1, String title1, String author1, String dateOfRelease1, String totalInStock1, String totalRentedOut1, String type1) {
		this.Item_id = book_id1;
		this.Title = title1;
		this.Author = author1;
		this.Year = dateOfRelease1;
		this.CopyQuantity = totalInStock1;
		this.LendedOutQuantity = totalRentedOut1;
		this.Type = type1;
		
	}
	public int getItem_id() {
		return Item_id;
	}
	
	public String getTitle() {
		return Title;
	}
	
	public String getAuthor() {
		return Author;
	}
	
	public String getYear() {
		return Year;
	}
	public String getCopyQuantity() {
		return CopyQuantity;
	}
	public String getLendedOutQuantity() {
		return LendedOutQuantity;
	}
	public String getType() {
		return Type;
	}
	
}
