package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class addBook {
	
	@FXML 
	private TextField bookName, bookAuthor, bookYear, bookQuantity, bookType;
	@FXML 
	private Label addItemError;


	
	public void checkBook() throws Exception {
		if(bookName.getText().length() == 0 || bookAuthor.getText().length() == 0 || bookYear.getText().length() == 0  || bookQuantity.getText().length() == 0  || bookType.getText().length() == 0)
			addItemError.setText("Information can't be empty!");
		else 
			registerBook();
	}
	
	public void registerBook() throws SQLException {
		
		DatabaseConnection connectNow = new DatabaseConnection();
		Connection connectDB = connectNow.getConnection();
		Statement statement = connectDB.createStatement();
		
		try {
			ResultSet resultDB = statement.executeQuery("Select MAX(Item_id) FROM Book");
			int max_id = resultDB.next() == true ? resultDB.getInt(1) + 1 : 0;
			
			String insertBook = ("insert into Book (Item_id, Title, Author, Year, Type, CopyQuantity, LendedOutQuantity) "
					+ "values (" + max_id + ", '" + bookName.getText() + "', '" + bookAuthor.getText() + "', '" + bookYear.getText() + "', '" + bookType.getText()  +  "', '"  + bookQuantity.getText()  
					+ "', '" + "0"  + "')");
			
			System.out.println(insertBook);
			statement.execute(insertBook);
			
			
			LibDashBoard dashboard = new LibDashBoard(); 
			Alert a1 = new Alert(Alert.AlertType.INFORMATION);
			a1.setHeaderText("Book " + bookName.getText() + " added!");
			a1.showAndWait();
			
			//dashboard.addLib();
		} catch(Exception e) {
			e.printStackTrace();
			e.getCause();
		}
	}
}
