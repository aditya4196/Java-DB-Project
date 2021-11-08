import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
			String addRERule = props.getProperty("addRERule");
			statement = con.prepareStatement(addRERule);
			int actid = actType.showActivityTypesFromRules(bid);
			
			System.out.print("Enter the points to be added to the rule :");
			int repoints = op.nextInt();
			statement.setInt(1,repoints);
			statement.setInt(4, bid);
			statement.setInt(5, actid);
			
			statement.executeQuery();
			System.out.println("RE Points added successfully");
	    	
	 }
	 
	 catch(Exception e) {
		System.out.println("RE Points not added successfully");
		e.printStackTrace();
	 }
	 
			
	}
	
	public void updateRERules(int bid) {
		 try {
			 	ActivityType actType = new ActivityType();
				props = readPropertiesFile();
				con = db.getConnection();
				
			   	Scanner op = new Scanner(System.in);
				RRRules rr = new RRRules();

				String updateRERule = props.getProperty("updateRERule");
				statement = con.prepareStatement(updateRERule);
				
				//System.out.print("Enter the Reward Rule code to be updated:");
				//int reid = op.nextInt();
				
				int actid = actType.showActivityTypesFromRules(bid);
				
				System.out.print("Enter the points to be updated to the rule :");
				int repoints = op.nextInt();
				
				statement.setInt(1,repoints);
				statement.setInt(3, actid);
				statement.setInt(2, bid);
				
				statement.executeQuery();
				System.out.println("RE Points updated successfully");
		    	
			 }
			 catch(Exception e) {
				System.out.println("RE Points not updated successfully");
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