import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;

public class RewardType {
	
	Brand bd = new Brand();
	DBConnector db = new DBConnector();
	Properties props;
	Connection con;
	Home home = new Home();

	public void rewardTypes(int lpType, int lpid) throws Exception {
		Scanner op = new Scanner(System.in);
		Map<Integer, Integer> rewkeys = new HashMap();
		
    	props = readPropertiesFile();
		home = new Home();
		Connection con = db.getConnection();
		String selectRewardIDs =  props.getProperty("selectRewardIDs");	
		
		PreparedStatement statement = con.prepareStatement(selectRewardIDs);

		
		ResultSet rs = statement.executeQuery();
		
		int ind = 1;
		while(rs.next()) {
			rewkeys.put(ind, rs.getInt("rewid"));
    	System.out.println(ind + ". " +rs.getString("rewname"));
		ind++;
		}
		System.out.print("Specify the options with space: ");
    	
		String userop = op.nextLine();
		
		int[] numbers = Arrays.stream(userop.split(" ")).mapToInt(Integer::parseInt).toArray(); 
		
		String insertRewardInstance =  props.getProperty("insertRewardInstance");
    	
    	PreparedStatement statement1;
    	statement1 = con.prepareStatement(insertRewardInstance);
    	statement1.setInt(1, lpid);
    	for(int num : numbers) {
    	
    	statement1.setInt(2,rewkeys.get(num));
    	statement1.executeQuery();
    	}
    	
    	op.close();
		
	}
	
	
	public int getSingleRewardType() throws Exception {
	Scanner op = new Scanner(System.in);
	Map<Integer, Integer> rewkeys = new HashMap();
	
	props = readPropertiesFile();
	home = new Home();
	Connection con = db.getConnection();
	String selectRewardIDs =  props.getProperty("selectRewardIDs");	
	
	PreparedStatement statement = con.prepareStatement(selectRewardIDs);

	
	ResultSet rs = statement.executeQuery();
	
	int ind = 1;
	while(rs.next()) {
		rewkeys.put(ind, rs.getInt("rewid"));
	System.out.println(ind + ". " +rs.getString("rewname"));
	ind++;
	}
	
	System.out.print("Choose the option: ");
	return(rewkeys.get(op.nextInt()));
	
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

	public int showRewardTypesFromRules(int bid) throws Exception {
		
		Scanner op = new Scanner(System.in);
		Map<Integer, Integer> rewkeys = new HashMap();
		
		props = readPropertiesFile();
		home = new Home();
		Connection con = db.getConnection();
		String selectRewardIDs =  props.getProperty("selectRewardIDsFromRules");	
		
		PreparedStatement statement = con.prepareStatement(selectRewardIDs);

		
		ResultSet rs = statement.executeQuery();
		
		int ind = 1;
		while(rs.next()) {
			rewkeys.put(ind, rs.getInt("rewid"));
		System.out.println(ind + ". " +rs.getString("rewname"));
		ind++;
		}
		
		System.out.print("Choose the option: ");
		return(rewkeys.get(op.nextInt()));		
	}

}
