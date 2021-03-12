package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;




public class GenerateLibReport {

	
	
	public static void writeFile() throws FileNotFoundException {

		DatabaseConnection connectNow = new DatabaseConnection();
		Connection connectDB = connectNow.getConnection();
		
		


		
		//create the file
		File file = new File("LibReport.txt");
		//let us write to the file
		PrintWriter output = new PrintWriter(file);
		//print the first line of the file
		output.printf("%-8s%-25s%-10s%-10s%-10s%-10s%-10s\n", "ItemID","Title","Author","Year","Type","Quantity","LendOutQuantity","");
		try {
			Statement statement = connectDB.createStatement();
			ResultSet resultLib = statement.executeQuery("SELECT * FROM Book");
			while(resultLib.next()) {
				int libid = resultLib.getInt("Item_id");
				String Title = resultLib.getString("Title");
				String Author = resultLib.getString("Author");
				String Year = 	 resultLib.getString("Year");
				String Type = resultLib.getString("Type");
				String CopyQuantity = resultLib.getString("CopyQuantity");
				String Lent = resultLib.getString("LendedOutQuantity");
				output.printf("%-8s%-25s%-10s%-10s%-10s%-10s%-10s\n", libid,Title,Author,Year,Type,CopyQuantity,Lent );
			}
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			e.getCause();
		}
		//flush the output
		output.flush();
		//close the output
		output.close();
		
	}


		
	}
	
	

