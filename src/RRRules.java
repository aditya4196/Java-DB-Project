import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
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
				
				//statement.setInt(2, rewType.getSingleRewardType());
				int rewid = rewType.showRewardTypesFromRules(bid);
				
				System.out.println("Please enter the rule value");
				int rrvalue = op.nextInt();
				
				System.out.println("Please enter the no of rewards");
				int rewcount = op.nextInt();
				
		        System.out.println("Please enter the Expiry Date : ");
		        
		        SimpleDateFormat dateInput = new SimpleDateFormat("yyyy-MM-dd");
		        String strDate = op.nextLine();
		        
		        Date rewExpdate = dateInput.parse(strDate);
				
				System.out.print("Enter the points to be added to the rule :");
				int rrpoints = op.nextInt();
				
				statement.setInt(1,rrpoints);
				statement.setInt(2,rrvalue);
				statement.setInt(3,rewcount);
				statement.setDate(4, new java.sql.Date(rewExpdate.getTime()));
				statement.setInt(5, bid);
				statement.setInt(6, rewid);
				
				
				statement.executeQuery();
				System.out.println("RR Points added successfully");
		    	}
		    	
		    }
			 catch(Exception e) {
				System.out.println("RR Points not added successfully");
				e.printStackTrace();
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
				
				//System.out.print("Enter the Reward Rule code to be updated:");
				//int rrid = op.nextInt();
				
				int rewid = rewType.showRewardTypesFromRules(bid);
				statement.setInt(3, rewid);
				
				System.out.print("Enter the points to be updated to the rule :");
				int rrpoints = op.nextInt();
				
				System.out.print("Enter the no of new reward count :");
				int rewcount = op.nextInt();
				
				System.out.print("Enter the new Expiry date :");
				int expiryDate = op.nextInt();
				
				
				statement.setInt(1,rrpoints);
				statement.setInt(2, rewcount);
				statement.setInt(3, expiryDate);
				statement.setInt(4, bid);
				
				statement.executeQuery();
				System.out.println("RR Points updated successfully");
		    	}
			 }
			 catch(Exception e) {
				System.out.println("RR Points not updated successfully");
				e.printStackTrace();
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