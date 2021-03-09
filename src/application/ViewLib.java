package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ViewLib implements Initializable{
	@FXML 
	private TableView<Librarian> table;
	 
	@FXML 
	private TableColumn<Librarian, Integer> idCol;
	
	@FXML 
	private TableColumn<Librarian, String> fnameCol;
	
	@FXML 
	private TableColumn<Librarian, String> lnameCol;	
	
	@FXML 
	private TableColumn<Librarian, String> usernameCol;	
	
	@FXML 
	private TableColumn<Librarian, String> phoneCol; 
	
	@FXML
	private TableColumn<Librarian, String> emailCol;

	@Override
	public void initialize(URL url, ResourceBundle rsb) {
		// TODO Auto-generated method stub
		idCol.setCellValueFactory(new PropertyValueFactory<Librarian, Integer>("Lib_id"));
		fnameCol.setCellValueFactory(new PropertyValueFactory<Librarian, String>("FName"));
		lnameCol.setCellValueFactory(new PropertyValueFactory<Librarian, String>("LName"));
		usernameCol.setCellValueFactory(new PropertyValueFactory<Librarian, String>("Username"));
		phoneCol.setCellValueFactory(new PropertyValueFactory<Librarian, String>("Phone"));
		emailCol.setCellValueFactory(new PropertyValueFactory<Librarian, String>("Email"));
		
		DatabaseConnection connectNow = new DatabaseConnection();
		Connection connectDB = connectNow.getConnection();
		final ObservableList<Librarian> data = FXCollections.observableArrayList();
		
		try {
			Statement statement = connectDB.createStatement();
			ResultSet resultLib = statement.executeQuery("SELECT * FROM Lib");
			while(resultLib.next()) {
				data.add( new Librarian( resultLib.getInt("Lib_id"), resultLib.getString("FName"), resultLib.getString("LName")
						, resultLib.getString("Username"), resultLib.getString("PhoneNumber"), resultLib.getString("Email")));
				
			}
			table.setItems(data);
			//table.getColumns().addAll(idCol);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			e.getCause();
		}
	}
}
