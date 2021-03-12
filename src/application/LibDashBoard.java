package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class LibDashBoard implements Initializable{

	@FXML 
	private Text welcomeAdmin;
	
	public void addBook(ActionEvent event) {
		Main main = new Main();
		main.addScene("addBook.fxml");
	}
	public void viewBook() {
		Main main = new Main();
		main.addScene("viewBooks.fxml");
	}
	public void adminHome(ActionEvent event) {
		Main main = new Main();
		welcomeAdmin.setText("Welcome!");
		welcomeAdmin.setFont(Font.font(40));
		main.addScene("none");
	}
	public void rentBook() {
		
		Main main = new Main();
		main.addScene("RentBook.fxml");
	}
	public void returnBook() {
		
		Main main = new Main();
		main.addScene("ReturnBook.fxml");
	}

	@Override
	public void initialize(URL url, ResourceBundle rsb) {
		// TODO Auto-generated method stub
		welcomeAdmin.setText("Welcome!");
		welcomeAdmin.setFont(Font.font(40));
	}
}
