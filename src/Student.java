import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Student {
	private SimpleStringProperty uidno;
	private SimpleStringProperty sname;
	private SimpleStringProperty rollno;
	private SimpleStringProperty classname;
	private SimpleStringProperty hours;
	private SimpleStringProperty srno;


	 public Student() {
		 
		     }
		 
		  
		 
	public Student(int i1,String s1, String s2,String s3,String s4,String s5) 
	     {
		    srno = new SimpleStringProperty(""+i1);

		    uidno = new SimpleStringProperty(s1);
		 
		    sname = new SimpleStringProperty(s2);
		    
		    rollno = new SimpleStringProperty(s3);
		    
		    classname = new SimpleStringProperty(s4);
		    
		    hours = new SimpleStringProperty(s5);
		 
		     }

	  public StringProperty nameProperty() {
	        return sname;
	    }
	  
	  public StringProperty uidProperty() {
	        return uidno;
	    }
	
	  public StringProperty rollProperty() {
	        return rollno;
	    }
	  
	  public StringProperty classProperty() {
	        return classname;
	    }
	
	  public StringProperty hoursProperty() {
	        return hours;
	    }
	  
	  public StringProperty srnoProperty() {
	        return srno;
	    }
	  
}
