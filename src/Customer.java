import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSetMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Formattable;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;

public class Customer extends Throwable {

	DBConnector db = new DBConnector();
	Properties props;
	Connection con;
	Home home = new Home();


	public void enrollInLP(int custid) {

		Scanner op = new Scanner(System.in);
		System.out.println("1. Enroll in Loyalty Program");
		System.out.println("2. Go Back");
		System.out.print("Your Option : ");

		Home home = new Home();
		int userop = op.nextInt();

		try {
			switch (userop) {
			case 1:
				enrollInLPProcess(custid);
				break;
			case 2:
				home.customerLanding(custid);
				break;
			default:
				System.out.println("You have entered an invalid option");
				enrollInLP(custid);
			}

			op.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void enrollInLPProcess(int custid) {
		System.out.println("Customer enrolled in Loyalty program");
		int x = getlpid(custid);
		
	}

	/*public void rewardActivites(int custid, int lpid) throws Exception {
		Scanner op = new Scanner(System.in);
		ActivityType act = new ActivityType();
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
		System.out.print("Choose one of the above Reward Activities: ");
		int userop = op.nextInt();
		
		if(userop == 1) {
			act.purchase();	
		}
		else if(userop == 2) {
			act.leaveAReview();
		}
		else if(userop == 3) {
			act.referAFriend();
		}
		
  
    	op.close();
		
	}*/


	public int getlpid(int custid) throws Exception {

		DBConnector db = new DBConnector();
        Connection con = db.getConnection();
        Scanner op1 = new Scanner(System.in);
        
        Connection conn = db.getConnection();
        PreparedStatement statement;
		statement = conn.prepareStatement("SELECT lpid, lpname from LoyaltyProgram where lpid IN (select lpid from WALLET where cid = ? )");
		statement.setInt(1, custid);
		
		ResultSet rs = statement.executeQuery();
		
		ResultSetMetaData rsmd = rs.getMetaData();
		
		int columnsNumber = rsmd.getColumnCount();
		
		while (rs.next()) {
			for (int i = 1; i <= columnsNumber; i++) {
				if (i > 1) System.out.print(",  ");
				int columnValue = rs.getInt(i);
				System.out.print( columnValue  + "--" + rsmd.getColumnName(i) );
			}
			System.out.println(" ");
		}

		System.out.println("Choose your Loyalty Program : ");
		
		return(op1.nextInt());

	}

	public void getrewardActivitiesScreen(Integer custid) throws Exception {
		
		int lpid = getlpid(custid);

		ActivityType act = new ActivityType();
		//Map<Integer, String> actkeys = new HashMap();
		
    	props = readPropertiesFile();
		home = new Home();
		Connection con = db.getConnection();
		String selectActTypes =  props.getProperty("selectActivityIDs");	
		
		PreparedStatement statement = con.prepareStatement("Select t.actid, at.actName from activityinstance t, activitytype at where t.lpid = ? and t.actid = at.actid");
		statement.setInt(1, lpid);
		ResultSet rs = statement.executeQuery();
		
		
		int ind = 1;
		while(rs.next()) {
			//actkeys.put(ind, rs.getString("actName"));
    		System.out.println(rs.getInt("actid") + "--" +rs.getString("actname"));
			
		}
		
		System.out.print("Choose the name from the above Reward Activities: ");
		Scanner op = new Scanner(System.in);
		String userip = op.next();
		
		func(custid, lpid, userip);
		
	}
	
	public int getRewardType(String custid) throws Exception {

		Scanner op = new Scanner(System.in);
		props = readPropertiesFile();
		home = new Home();
		con = db.getConnection();
		String rewardName = null;

		System.out.println("1. Purchase");
		System.out.println("2. Leave a review");
		System.out.println("3. Refer a friend");
		System.out.println("4. Go back");
		System.out.print("Your Option : ");

		Home home = new Home();
		Customer cust = new Customer();
		int userop = op.nextInt();

		try {
			switch (userop) {
			case 1:
				rewardName = "Purchase";
				home.purchase(custid);
				break;
			case 2:
				rewardName = "Leave a review";
				home.leaveReview();
				break;
			case 3:
				rewardName = "Refer a friend";
				home.referFriend();
				break;
			case 4:
				System.out.println("Go back");
				home.customerLanding(custid);
				break;
			default:
				System.out.println("You have entered an invalid option");
				getLPjoinedToRewardActivities(custid);
			}

			op.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String getRewardTypeIdByName = props.getProperty("getRewardTypeIdByName").toString();
		PreparedStatement statement = con.prepareStatement(getRewardTypeIdByName);
		statement.setString(1, rewardName);
		ResultSet rs = statement.executeQuery();
		rs.next();
		return rs.getInt("rewid");

	}

	public void func(int custid,int lpid, String acttype) throws Exception {

		DBConnector db = new DBConnector();
        Connection con = db.getConnection();
        Scanner op1 = new Scanner(System.in);
        
        Connection conn = db.getConnection();
        PreparedStatement statement;
		statement = conn.prepareStatement("SELECT repoints from RERULES where lpid = ? and actname == ?");
		statement.setInt(1, lpid);
		statement.setString(2, acttype);
		
		ResultSet rs = statement.executeQuery();
		rs.next();
		int points = rs.getInt("repoints");
		
		PreparedStatement statement1;
		statement1 = conn.prepareStatement("SELECT points from WALLET where cid = ? and lpid = ?");
		statement1.setInt(1, custid);
		statement1.setInt(2, lpid);
		
		ResultSet rs2 = statement1.executeQuery();
		rs2.next();

		int custpoints = rs2.getInt("repoints");

		int totalpts = custpoints + points;

		statement1 = conn.prepareStatement("UPDATE WALLET set points = ? where cid = ? and lpid = ?");
		statement1.setInt(1, totalpts);
		statement1.setInt(2, custid);
		statement1.setInt(3, lpid);

		statement1.executeUpdate();

		System.out.println("You have earned " + points + " points");
		

	}
	public void getLPjoinedToRewardActivities(String custid) {

	}

	public void viewWallet(String custid) {
		try {
			props = readPropertiesFile();
			home = new Home();
			con = db.getConnection();
			String viewWalletQuery = props.getProperty("viewWalletQuery").toString();
			PreparedStatement statement = con.prepareStatement(viewWalletQuery);
			statement.setString(1, custid);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				System.out.println(rs.getString("username"));
			}

			Scanner op = new Scanner(System.in);
			System.out.println("1. Go Back");
			System.out.println("2. Log Out");
			System.out.print("Your Option : ");

			int userop = op.nextInt();
			switch (userop) {
			case 1:
				home.customerLanding(custid);
				break;
			case 2:
				home.login();
				break;
			default:
				System.out.println("You have entered an invalid option");
				viewWallet(custid);
			}

			op.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void redeemPoints(String custid) {

		try {
			Scanner op = new Scanner(System.in);
			System.out.println("1. Reward Selection");
			System.out.println("2. Go Back");
			System.out.print("Your Option : ");

			int userop = op.nextInt();
			switch (userop) {
			case 1:
				performRedeemOperation(getRewardType(custid), custid);
				break;
			case 2:
				home.customerLanding(custid);
				break;
			default:
				System.out.println("You have entered an invalid option");
				viewWallet(custid);
			}

			op.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void performRedeemOperation(int rewid, String custid) {
		try {

			props = readPropertiesFile();
			home = new Home();
			Connection con = db.getConnection();
			String performRedeemUpdate = props.getProperty("performRedeem").toString();
			PreparedStatement statement = con.prepareStatement(performRedeemUpdate);
			statement.setString(1, custid);
			statement.setInt(2, rewid);
			ResultSet rs = statement.executeQuery();
			System.out.println("Points Redeemed for your selected Reward Type");
		} catch (Exception e) {
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

	public void performRewardOperation(String custid, int rewid) {
	
		
	}

}
