package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class rentBook {
	@FXML 
	private TextField ItemId, StudentId;
	
	public void IssueBook() throws SQLException {
		
		

		DatabaseConnection connectNow = new DatabaseConnection();
		Connection connectDB = connectNow.getConnection();
		Statement statement = connectDB.createStatement();

		
		try {
			
			String item_id = ("select Item_id, CopyQuantity, LendedOutQuantity from book where Item_Id = " + ItemId.getText());
			ResultSet resultId = statement.executeQuery(item_id);
			
			if (resultId.next() == false) {
				LibDashBoard dashboard = new LibDashBoard(); 
				Alert a1 = new Alert(Alert.AlertType.INFORMATION);
				a1.setHeaderText("Item " + ItemId.getText() + " does not exist");
				a1.showAndWait();
			} else if(Integer.parseInt(resultId.getString("LendedOutQuantity")) <= Integer.parseInt(resultId.getString("CopyQuantity"))) {
				connectDB.close();
				run();
			} else {
				LibDashBoard dashboard = new LibDashBoard(); 
				Alert a1 = new Alert(Alert.AlertType.INFORMATION);
				a1.setHeaderText("Item " + ItemId.getText() + " has no more copies left");
				a1.showAndWait();
			}
					
		} catch(Exception e) {
			e.printStackTrace();
			e.getCause();
		}
		

		
		
	}
	

	public void run() throws SQLException {
		
		DatabaseConnection connectNow = new DatabaseConnection();
		Connection connectDB = connectNow.getConnection();
		Statement statement = connectDB.createStatement();
		
		
		try {
					ResultSet resultDB = statement.executeQuery("Select MAX(Copy_id) FROM Copy");
					int max_id = resultDB.next() == true ? resultDB.getInt(1) + 1 : 0;
					
					String insertBook = ("insert into Copy (Copy_id, Item_id, LentStudent) "
							+ "values (" + max_id + ", '" + ItemId.getText() + "', '" + StudentId.getText()  + "')");
					
					System.out.println(insertBook);
					statement.execute(insertBook);
					
					
					String Lendedoutamount = ("select LendedOutQuantity from book where Item_Id = " + ItemId.getText());
					
					System.out.println(Lendedoutamount);
					ResultSet resultLib = statement.executeQuery(Lendedoutamount);
					
					
					String remove1 = ("update book set LendedOutQuantity = "+ (Integer.parseInt(resultLib.getString("LendedOutQuantity")) + 1)+  " where Item_Id = " + ItemId.getText());
					
					System.out.println(remove1);
					statement.execute(remove1);
					
					LibDashBoard dashboard = new LibDashBoard(); 
					Alert a1 = new Alert(Alert.AlertType.INFORMATION);
					a1.setHeaderText("Item " + ItemId.getText() + " rented to student " + StudentId.getText());
					a1.showAndWait();
					
					
				} catch(Exception e) {
					e.printStackTrace();
					e.getCause();
				}
	}
	
	
}
