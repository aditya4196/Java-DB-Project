import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.Scanner;

public class RRRules {
	
	DBConnector db = new DBConnector();
	Properties props;
	Connection con;
	PreparedStatement statement;
	public void addRRRules(int bid) {
		 try {
			 	RewardType rewType = new RewardType();
				props = readPropertiesFile();
				con = db.getConnection();
				
			   	Scanner op = new Scanner(System.in);
				RRRules rr = new RRRules();
		    	System.out.println("1. Add RR Rules");
		    	System.out.println("2. Go Back");
		    	System.out.print("Your option: ");
		    	
		    	int userop = op.nextInt();
		    	
		    	if(userop == 1) {
				String addRRRule = props.getProperty("addRRRule");
				statement = con.prepareStatement(addRRRule);
				
				statement.setInt(2, rewType.getSingleRewardType());
				
				System.out.print("Enter the points to be added to the rule :");
				int rrpoints = op.nextInt();
				
				statement.setInt(1,rrpoints);
				statement.setInt(3, bid);
				
				statement.executeQuery();
				System.out.println("RR Points added successfully");
		    	}
		    	
		    }
			 catch(Exception e) {
				System.out.println("RR Points not added successfully");
			 }
	}
	
	public void updateRRRules(int bid) {
		
		 try {
			 	RewardType rewType = new RewardType();
				props = readPropertiesFile();
				con = db.getConnection();
				
			   	Scanner op = new Scanner(System.in);
				RRRules rr = new RRRules();
		    	System.out.println("1. Update RR Rules");
		    	System.out.println("2. Go Back");
		    	System.out.print("Your option: ");
		    	
		    	int userop = op.nextInt();
		    	
		    	if(userop == 1) {
				String updateRRRule = props.getProperty("updateRRRule");
				statement = con.prepareStatement(updateRRRule);
				
				statement.setInt(2, rewType.getSingleRewardType());
				
				System.out.print("Enter the points to be updated to the rule :");
				int rrpoints = op.nextInt();
				
				statement.setInt(1,rrpoints);
				statement.setInt(3, bid);
				
				statement.executeQuery();
				System.out.println("RR Points updated successfully");
		    	}
			 }
			 catch(Exception e) {
				System.out.println("RR Points not updated successfully");
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