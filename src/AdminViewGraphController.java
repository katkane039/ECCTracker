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
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class AdminViewGraphController {
	 
	    @FXML
	    private BarChart<String, Integer> bar;
	    
	    @FXML
	    private CategoryAxis xaxis;
	    
	    ObservableList<String> classNames = FXCollections.observableArrayList("fyit", "syit", "tyit", "fybmm", "sybmm", "tybmm", "fyba", "fybms", "sybms", "tybms" );

        @FXML
        public void initialize()
        {
        	// xaxis.setCategories(classNames);
        	int[] studentsdone=new int[3];
        	String[] classes=new String[3];
        	int i=0;
        	 try{
         		Connection con1=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","KATkane039");  
         		PreparedStatement ps1=con1.prepareStatement("select class,count(*) from student where ecchours>? group by class");  
         		ps1.setInt(1,60);
         		ResultSet rs1=ps1.executeQuery();  
         		
         		while(rs1.next()){
         			classes[i]=rs1.getString(1);
         			System.out.println(classes[i]);
         			studentsdone[i]=rs1.getInt(2);
         			System.out.println(studentsdone[i]);
         			i++;
         		   }
                }
         	 catch(Exception e)
  		     {
  			  e.printStackTrace();
  		     }
  			
  
        	 
        	/* XYChart.Series<String, Integer> series1 = new XYChart.Series<>(); 
        	 series1.setName("<60"); 
        	 series1.getData().add(new XYChart.Data<>("fyit", 1)); 
        	 series1.getData().add(new XYChart.Data<>("syit", 3));
        	 series1.getData().add(new XYChart.Data<>("tyit", 4));  
     

        	 XYChart.Series<String, Integer> series2 = new XYChart.Series<>(); 
        	 series2.setName(">=60"); 
        	 series2.getData().add(new XYChart.Data<>("fyit", 5)); 
        	 series2.getData().add(new XYChart.Data<>("syit", 6));  
        	 series2.getData().add(new XYChart.Data<>("tyit", 3));  */
             
        	 int j=0;
        	 XYChart.Series<String, Integer> series1 = new XYChart.Series<>(); 
        	 series1.setName(">=60"); 
        	 XYChart.Series<String, Integer> series2 = new XYChart.Series<>(); 
        	 series2.setName("<60"); 
        	while(j<3)
        	{
        	 series1.getData().add(new XYChart.Data<>(classes[j], studentsdone[j])); 
        	 
        	 series2.getData().add(new XYChart.Data<>(classes[j],(5-studentsdone[j]) )); 
             j++;
        	}    

        	 bar.setTitle("Class comparison");
        	 bar.getData().addAll(series1, series2);
        }
        
   
        public void goBack() throws IOException
    	{
    		  Parent window;
    		  window = FXMLLoader.load(getClass().getClassLoader().getResource("Admin1.fxml"));
    	      Stage mainStage;
    	      mainStage = Login.parentWindow;
    	      mainStage.getScene().setRoot(window); 
    		
    	}

}
