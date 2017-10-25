import application.Login;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AdminUserController{
	
	ObservableList<String> cb_value= FXCollections.observableArrayList("fyit", "syit", "tyit", "fybmm", "sybmm", "tybmm", "fyba", "fybms", "sybms", "tybms" );

   static String auid="";
   static String cselect="";

	@FXML
	Text name_card,uid_card,roll_card,class_card,hrscomp,hrsleft;
	
	@FXML
	ImageView img_card1;
	
	@FXML
	private ComboBox<String> comboBox;
	
	@FXML
	private TextField admin_uid;
	
	@FXML

	private void initialize() {
	 try
		{
		comboBox.setItems(cb_value);
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	}
	
	
	public void classSelect() throws Exception{
		cselect= (String) comboBox.getValue();
		
		if(!cselect.isEmpty())
		{
		try{
			
			  Parent window2 ;
			  window2 = FXMLLoader.load(getClass().getClassLoader().getResource("Admin3.fxml"));
		      Stage mainStage;
		      mainStage = Login.parentWindow;
		      mainStage.getScene().setRoot(window2); 
		      	      
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		
		
		}
	}
	
	
	public void uidSelect()
	{
		auid=admin_uid.getText();
		if(!auid.isEmpty())
		{
		try{
		  Parent window2;
	      window2 = FXMLLoader.load(getClass().getClassLoader().getResource("Admin2.fxml"));
	      Stage mainStage;
	      mainStage = Login.parentWindow;
	      mainStage.getScene().setRoot(window2); 
	      	      
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	  }
	}
	
	
	
	public void viewClassStatistics()
	{
		System.out.println("yo");
		try{
			  Parent window2;
		      window2 = FXMLLoader.load(getClass().getClassLoader().getResource("Graph.fxml"));
		      Stage mainStage;
		      mainStage = Login.parentWindow;
		      mainStage.getScene().setRoot(window2); 
		      	      
			}catch(Exception e)
			{
				e.printStackTrace();
			}
			
	}

}
