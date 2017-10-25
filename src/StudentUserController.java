import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class StudentUserController {

	@FXML
	Rectangle redBox , greenBox, yellowBox;
	
	@FXML
	Text name_card,uid_card,roll_card,class_card,hrscomp,hrsleft;
	
	@FXML
	ImageView img_card1;
	
	@FXML
	private void initialize() throws SQLException   //intializes the view
	{
		try{
		System.out.println("yo");
		Connection con1=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","KATkane039");  
		PreparedStatement ps1=con1.prepareStatement("select * from student where uid_no=?");  
		ps1.setString(1,UserController.uname);
	
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
            	hrsleft.setText("0");
		      }
		    
		    
		    Blob imageBlob =rs1.getBlob(6);
			    
			byte[] image= imageBlob.getBytes(1,(int)imageBlob.length());
	        BufferedImage image_format = ImageIO.read(new ByteArrayInputStream(image));
			   
			img_card1.setImage(SwingFXUtils.toFXImage(image_format, null));
		    
			
		  
		   }
		   catch(Exception e)
		   {
			e.printStackTrace();
		   }
			
	}
	
	public void changeColor(){
		redBox.setVisible(false);
		greenBox.setVisible(false);
	}
	
	public void changeColorBack(){
		redBox.setVisible(true);
		greenBox.setVisible(true);
	}
	
	public void showFests()
	{
		String ecc="Activity"+"\t"+"Hours"+"\n";
		try{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","KATkane039");  
		PreparedStatement ps=con.prepareStatement("select * from ecchrs where uid_no=?");  
		ps.setString(1,UserController.uname);
	
		ResultSet rs=ps.executeQuery();  
		
		while(rs.next())
		   {
			ecc=ecc+rs.getString(2)+"\t"+rs.getString(3)+"\n";
			//System.out.println(rs.getString(2)+" "+rs.getString(3));
		    }
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Fests");
		alert.setHeaderText("ECC hours ");
		alert.setContentText(ecc);
		alert.showAndWait();
	}//end of method showFests
	
	
	
}//end of class
