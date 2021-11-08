import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;

public class ActivityType {

	Brand bd = new Brand();
	
	DBConnector db = new DBConnector();
	Properties props;
	Connection con;
	Home home = new Home();
	
	public void activityTypes(int lpType, int lpid) throws Exception {
		Scanner op = new Scanner(System.in);
		Map<Integer, Integer> actkeys = new HashMap();
		
    	props = readPropertiesFile();
		home = new Home();
		Connection con = db.getConnection();
		String selectActTypes =  props.getProperty("selectActivityIDs");	
		
		PreparedStatement statement = con.prepareStatement(selectActTypes);

		
		ResultSet rs = statement.executeQuery();
		
		int ind = 1;
		while(rs.next()) {
			actkeys.put(ind, rs.getInt("actid"));
    	System.out.println(ind + ". " +rs.getString("actname"));
		ind++;
		}
		System.out.print("Specify the options with space: ");
    	
		String userop = op.nextLine();
		
		int[] numbers = Arrays.stream(userop.split(" ")).mapToInt(Integer::parseInt).toArray(); 
		
		String insertActivityInstance =  props.getProperty("insertActivityInstance");
    	
    	PreparedStatement statement1;
    	statement1 = con.prepareStatement(insertActivityInstance);
    	statement1.setInt(1, lpid);
    	for(int num : numbers) {
    	
    	statement1.setInt(2,actkeys.get(num));
    	statement1.executeQuery();
    	}
    	
    	
    	op.close();
		
	}
	
	public int getSingleActivityType() throws Exception {
	Scanner op = new Scanner(System.in);
	Map<Integer, Integer> actkeys = new HashMap();
	
	props = readPropertiesFile();
	home = new Home();
	Connection con = db.getConnection();
	String selectActTypes =  props.getProperty("selectActivityIDs");	
	
	PreparedStatement statement = con.prepareStatement(selectActTypes);

	
	ResultSet rs = statement.executeQuery();
	
	int ind = 1;
	while(rs.next()) {
		actkeys.put(ind, rs.getInt("actid"));
	System.out.println(ind + ". " +rs.getString("actname"));
	ind++;
	}
	System.out.print("Choose the option: ");
	return(actkeys.get(op.nextInt()));
	
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
	
	public int showActivityTypesFromRules(int lpid) throws Exception {
		
		Scanner op = new Scanner(System.in);
		Map<Integer, Integer> rewkeys = new HashMap();
		
		props = readPropertiesFile();
		home = new Home();
		Connection con = db.getConnection();
		String selectActIDs =  props.getProperty("selectActIDsFromRules");	
		
		PreparedStatement statement = con.prepareStatement(selectActIDs);

		
		ResultSet rs = statement.executeQuery();
		
		int ind = 1;
		while(rs.next()) {
			rewkeys.put(ind, rs.getInt("actid"));
		System.out.println(ind + ". " +rs.getString("actname"));
		ind++;
		}
		
		System.out.print("Choose the option: ");
		
		
		return(rewkeys.get(op.nextInt()));		
	}
}
