package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class returnBook {
	@FXML 
	private TextField ItemId, StudentId;
	
	public void ReturnBook() throws SQLException {
		
		

		DatabaseConnection connectNow = new DatabaseConnection();
		Connection connectDB = connectNow.getConnection();
		Statement statement = connectDB.createStatement();

		
		try {
			
			String item_id = ("select Copy_id, Item_id, LentStudent from Copy where Item_Id = " + ItemId.getText() + " and LentStudent = " + StudentId.getText());
			ResultSet resultId = statement.executeQuery(item_id);
			
			if (resultId.next() == false) {
				LibDashBoard dashboard = new LibDashBoard(); 
				Alert a1 = new Alert(Alert.AlertType.INFORMATION);
				a1.setHeaderText("Student " + StudentId.getText() + " does not have book " + ItemId.getText()) ;
				a1.showAndWait();
			} else {
				connectDB.close();
				run();
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
					
					String DeleteCopy = ("DELETE FROM Copy WHERE Item_Id = " + ItemId.getText() + " and LentStudent = " + StudentId.getText());
					
					System.out.println(DeleteCopy);
					statement.execute(DeleteCopy);
					
					
					String Lendedoutamount = ("select LendedOutQuantity from book where Item_Id = " + ItemId.getText());
					
					System.out.println(Lendedoutamount);
					ResultSet resultLib = statement.executeQuery(Lendedoutamount);
					
					
					String remove1 = ("update book set LendedOutQuantity = "+ (Integer.parseInt(resultLib.getString("LendedOutQuantity")) - 1)+  " where Item_Id = " + ItemId.getText());
					
					System.out.println(remove1);
					statement.execute(remove1);
					
					LibDashBoard dashboard = new LibDashBoard(); 
					Alert a1 = new Alert(Alert.AlertType.INFORMATION);
					a1.setHeaderText("Item: " + ItemId.getText() + " taken back from student: " + StudentId.getText());
					a1.showAndWait();
					
					
				} catch(Exception e) {
					e.printStackTrace();
					e.getCause();
				}
	}
}
