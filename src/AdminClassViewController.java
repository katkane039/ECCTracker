import java.io.IOException;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import application.Login;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class AdminClassViewController {
	
	
	@FXML
    TableView<Student> adminTableView;
	
	@FXML
	TableColumn<Student, String> col_srno,col_name,col_uid,col_roll,col_hrscomp;
	
	ObservableList<Student> studentData = FXCollections.observableArrayList();
	
	
	public void goBack(){
		System.out.println("holla");
		 
		  try {
			  Parent window;
			  window = FXMLLoader.load(getClass().getClassLoader().getResource("Admin1.fxml"));
			  Stage mainStage;
		      mainStage = Login.parentWindow;
		      mainStage.getScene().setRoot(window); 
		    }
		catch (Exception e) {

			   e.printStackTrace();
		}
	}	     
	
	public void showClassDefaulters()
	{
		String def="";
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","KATkane039");
			PreparedStatement ps=con.prepareStatement("select * from student where class=? and ecchours<?");  
			ps.setString(1, AdminUserController.cselect);
			ps.setInt(2, 60);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
			def=def+rs.getString(1)+"\t"+rs.getString(2)+"\n";	
			System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4));
			}
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Class Defaulters");
			alert.setHeaderText("Defaulters of class "+AdminUserController.cselect);
			alert.setContentText(def);
			alert.showAndWait();
		 }
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	

	@FXML
	private void initialize()
	{
		int i=0;
		try{	
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","KATkane039");  
			PreparedStatement ps=con.prepareStatement("select * from student where class=?");  
			ps.setString(1, AdminUserController.cselect);
			ResultSet rs=ps.executeQuery();  
			while(rs.next())
			{
			 i++;	
		     System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4));
	             
		     studentData.add(new Student(i,rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)));
			 
			}
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		
		 col_name.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
		 col_uid.setCellValueFactory(cellData -> cellData.getValue().uidProperty());
		 col_roll.setCellValueFactory(cellData -> cellData.getValue().rollProperty());
		 col_hrscomp.setCellValueFactory(cellData -> cellData.getValue().hoursProperty());
		 col_srno.setCellValueFactory(cellData -> cellData.getValue().srnoProperty());
		 
		 
		 adminTableView.setItems(studentData);
	     
		
		
	}
}
	



	
	
	
	

