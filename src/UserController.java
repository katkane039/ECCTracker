
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.sql.*;
import application.Login;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class UserController implements Initializable {


	@FXML
	Button b1;
	
	@FXML
	TextField user;
	
	@FXML
	PasswordField pass;
	
	@FXML
	Text alt;

	static String uname;
	
	public UserController(){

	}

	
	public void handleLogin() throws IOException{
		   uname=user.getText();
		   String passwd=pass.getText();
		   
		if(!(user.getText().isEmpty()) && !(pass.getText().isEmpty())) //to check if both fields are filled
		{
			//if(user.getText().matches("")){  //to check if user entered numeric only
            try{
			Class.forName("oracle.jdbc.driver.OracleDriver");  
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","KATkane039");  
			PreparedStatement ps=con.prepareStatement("select * from login where login_id=?");  
			ps.setString(1, uname);
			ResultSet rs=ps.executeQuery();  
			while(rs.next())
			{
			  if(passwd.equalsIgnoreCase(rs.getString(2)) && uname.equalsIgnoreCase(rs.getString(1))) //to check if password entered by user is correct
			   {
				  if(uname.equalsIgnoreCase("123456")) //to check if the user is the admin
				  {
					  Parent window2;
				      window2 = FXMLLoader.load(getClass().getClassLoader().getResource("Admin1.fxml"));
				      Stage mainStage;
				      mainStage = Login.parentWindow;
				      mainStage.getScene().setRoot(window2);  
				  }
				  else{  //if it is not an admin
					  Parent window2;
				      window2 = FXMLLoader.load(getClass().getClassLoader().getResource("User1.fxml"));
				      Stage mainStage;
				      mainStage = Login.parentWindow;
				      mainStage.getScene().setRoot(window2); 
				      
			       }
			   }
			  else //if the user enters an incorrect password or username
			   {
			      alt.setText("*Incorrect username or password");
			   }
			
			   }
			  con.close();  
			}
            
			catch(Exception e)
                  {
				e.printStackTrace();
			       }
			//}

		}
		else 
		{
	      alt.setText("*Please enter username and password");
		}
		
		
	
	}//end of method handleLogin
	
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}

}


