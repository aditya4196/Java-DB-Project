import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

public class RERules {
	
	DBConnector db = new DBConnector();
	Properties props;
	Connection con;
	PreparedStatement statement;
	public void addRERules(int bid) throws Exception {
	 try {
		 	ActivityType actType = new ActivityType();
			props = readPropertiesFile();
			con = db.getConnection();
			
		   	Scanner op = new Scanner(System.in);
			RRRules rr = new RRRules();
	    	System.out.println("1. Add RE Rules");
	    	System.out.println("2. Go Back");
	    	System.out.print("Your option: ");
	    	
	    	int userop = op.nextInt();
	    	
	    	if(userop == 1) {
			String addRERule = props.getProperty("addRERule");
			statement = con.prepareStatement(addRERule);
			
			statement.setInt(2, actType.getSingleActivityType());
			
			System.out.print("Enter the points to be added to the rule :");
			int repoints = op.nextInt();
			
			statement.setInt(1,repoints);
			statement.setInt(3, bid);
			
			statement.executeQuery();
			System.out.println("RE Points added successfully");
	    	}
	 }
	 
	 catch(Exception e) {
		System.out.println("RE Points not added successfully");
	 }
			
	}
	
	public void updateRERules(int bid) {
		 try {
			 	ActivityType actType = new ActivityType();
				props = readPropertiesFile();
				con = db.getConnection();
				
			   	Scanner op = new Scanner(System.in);
				RRRules rr = new RRRules();
		    	System.out.println("1. Update RE Rules");
		    	System.out.println("2. Go Back");
		    	System.out.print("Your option: ");
		    	
		    	int userop = op.nextInt();
		    	
		    	if(userop == 1) {
				String updateRERule = props.getProperty("updateRERule");
				statement = con.prepareStatement(updateRERule);
				
				statement.setInt(2, actType.getSingleActivityType());
				
				System.out.print("Enter the points to be updated to the rule :");
				int repoints = op.nextInt();
				
				statement.setInt(1,repoints);
				statement.setInt(3, bid);
				
				statement.executeQuery();
				System.out.println("RE Points updated successfully");
		    	}
			 }
			 catch(Exception e) {
				System.out.println("RE Points not updated successfully");
			 }
	}
	
	public static Properties readPropertiesFile() throws IOException {
		FileInputStream fis = null;
		Properties prop = null;
		try {
			fis = new FileInputStream("src/queries.properties");
			prop = new Properties();
			prop.load(fis);
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			fis.close();
		}
		return prop;
	}
}