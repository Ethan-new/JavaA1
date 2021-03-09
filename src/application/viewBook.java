package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class viewBook  implements Initializable {

	
	
	@FXML 
	private TableView<Book> table;
	 
	@FXML 
	private TableColumn<Book, Integer> idCol;
	
	@FXML 
	private TableColumn<Book, String> titleCol;
	
	@FXML 
	private TableColumn<Book, String> authorCol;	
	
	@FXML 
	private TableColumn<Book, String> dateCol;	
	
	@FXML 
	private TableColumn<Book, String> quantityCol; 
	
	@FXML
	private TableColumn<Book, String> lendedCol;
	
	@FXML
	private TableColumn<Book, String> Type;

	@Override
	public void initialize(URL url, ResourceBundle rsb) {
		// TODO Auto-generated method stub
		idCol.setCellValueFactory(new PropertyValueFactory<Book, Integer>("Item_id"));
		titleCol.setCellValueFactory(new PropertyValueFactory<Book, String>("Title"));
		authorCol.setCellValueFactory(new PropertyValueFactory<Book, String>("Author"));
		dateCol.setCellValueFactory(new PropertyValueFactory<Book, String>("Year"));
		quantityCol.setCellValueFactory(new PropertyValueFactory<Book, String>("CopyQuantity"));
		lendedCol.setCellValueFactory(new PropertyValueFactory<Book, String>("LendedOutQuantity"));
		Type.setCellValueFactory(new PropertyValueFactory<Book, String>("Type"));
		
		DatabaseConnection connectNow = new DatabaseConnection();
		Connection connectDB = connectNow.getConnection();
		final ObservableList<Book> data = FXCollections.observableArrayList();
		
		try {
			Statement statement = connectDB.createStatement();
			ResultSet resultLib = statement.executeQuery("SELECT * FROM Book");
			while(resultLib.next()) {
				data.add( new Book( resultLib.getInt("Item_id"), resultLib.getString("Title"), resultLib.getString("Author"), resultLib.getString("Year"), resultLib.getString("CopyQuantity"), resultLib.getString("LendedOutQuantity"), resultLib.getString("Type")));
				System.out.print(data.size());
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
