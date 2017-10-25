import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import javax.imageio.ImageIO;

import application.Login;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Pair;

public class AdminUidViewController {
	
	@FXML
	Text name_card,uid_card,roll_card,class_card,hrscomp,hrsleft;
	
	@FXML
	ImageView img_card1;
	
	static String festenter;
	
	static String hourenter;
	
	@FXML
	private void initialize() throws SQLException   //intializes the view
	{
		try{
		Connection con1=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","KATkane039");  
		PreparedStatement ps1=con1.prepareStatement("select * from student where uid_no=?");  
		ps1.setString(1,AdminUserController.auid);
		ResultSet rs1=ps1.executeQuery();  
		
		while(rs1.next()){
			
			name_card.setText(rs1.getString(2));
			class_card.setText(rs1.getString(4));
			uid_card.setText(rs1.getString(1));
			roll_card.setText(rs1.getString(3));
		    hrscomp.setText(rs1.getString(5));
		    int hrs=Integer.parseInt(rs1.getString(5));
            if(hrs <= 60)
            {
            	int l=60-hrs;
            	hrsleft.setText(""+l);
            }
            else
               {
            	hrsleft.setText(""+0);
		      }
		    
		    Blob imageBlob =rs1.getBlob(6);
		    byte[] image= imageBlob.getBytes(1,(int)imageBlob.length());
		    BufferedImage image_format = ImageIO.read(new ByteArrayInputStream(image));
		   
		    img_card1.setImage(SwingFXUtils.toFXImage(image_format, null));
		   
			
		   
		   }
		}
		   catch(Exception e)
		   {
			e.printStackTrace();
		   }
			
	}
	
	public void goBack() throws IOException
	{
		  Parent window;
		  window = FXMLLoader.load(getClass().getClassLoader().getResource("Admin1.fxml"));
	      Stage mainStage;
	      mainStage = Login.parentWindow;
	      mainStage.getScene().setRoot(window); 
		
	}

	
	public void updateHours(){
		Dialog<Pair<String, String>> dialog = new Dialog<>();
		dialog.setTitle("Login Dialog");
		dialog.setHeaderText("Look, a Custom Login Dialog");
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 150, 10, 10));

		ButtonType loginButtonType = new ButtonType("Submit", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(loginButtonType);
		TextField activity = new TextField();
		activity.setPromptText("Eg.Xenith");
		TextField hours = new TextField();
		hours.setPromptText("Eg. 10");

		grid.add(new Label("Activity:"), 0, 0);
		grid.add(activity, 1, 0);
		grid.add(new Label("Hours:"), 0, 1);
		grid.add(hours, 1, 1);
		dialog.getDialogPane().setContent(grid);
		
		dialog.setResultConverter(dialogButton -> {
		    if (dialogButton == loginButtonType) {
		        return new Pair<>(activity.getText(), hours.getText());
		    }
		    return null;
		});

		Optional<Pair<String, String>> result = dialog.showAndWait();
		
		result.ifPresent((activityevent) -> {
		    festenter= activityevent.getKey();
		    hourenter =activityevent.getValue();
		});
		int hr=Integer.parseInt(hourenter);
		
		try{	
			Class.forName("oracle.jdbc.driver.OracleDriver");  
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","KATkane039");  
			PreparedStatement ps=con.prepareStatement("update student set ecchours=ecchours+? where uid_no=?");
			ps.setInt(1, hr);
			ps.setString(2, AdminUserController.auid);
			ps.executeQuery();  //to update hours completed in student table
			
			PreparedStatement ps2=con.prepareStatement("insert into ecchrs values(?,?,?)");
			ps2.setString(1, AdminUserController.auid);
			ps2.setString(2, festenter);
			ps2.setInt(3, hr);
			ps2.executeQuery(); //to insert a new row into ecchours table
			
			
			PreparedStatement ps1=con.prepareStatement("Select * from student where uid_no=?");  
			//to update student data that the user sees after entering hours
			ps1.setString(1, AdminUserController.auid);
            ResultSet rs1=ps1.executeQuery();  
            int h;
			while(rs1.next()){
			h=rs1.getInt(5);	
			
			hrscomp.setText(""+h);
			
			if(h<60)
			 hrsleft.setText(""+(60-h));
			
			else
		    hrsleft.setText(""+0);
			 
			   }
			
			
		   }
			catch(Exception e)
			{
				e.printStackTrace();
			}
	}//rnd of method updateHours
}//end of class



