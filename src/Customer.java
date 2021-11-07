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
				//getrewardActivitiesScreen(custid);
				break;
			case 2:
				//home.customerLanding(custid);
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

	public void enrollInLPProcess(int custid) throws Exception {
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
//			for (int i = 1; i <= columnsNumber; i++) {
//				if (i > 1) System.out.print(",  ");
				int columnValue = rs.getInt("lpid");
				String columnName = rs.getString("lpname");
				System.out.print( columnValue  + "--" + columnName );
//			}
			System.out.println(" ");
		}

		System.out.println("Choose your Loyalty Program : ");
		
		return(op1.nextInt());

	}

	public void getrewardActivitiesScreen(int custid) throws Exception {
		
		int lpid = getlpid(custid);

		ActivityType act = new ActivityType();
		//Map<Integer, String> actkeys = new HashMap();
		
    	props = readPropertiesFile();
		home = new Home();
		Connection con = db.getConnection();
		String selectActTypes =  props.getProperty("selectActivityIDs");	
		
		PreparedStatement statement = con.prepareStatement("Select t.actid, at.actName from rerules t, activitytype at where t.lpid = ? and t.actid = at.actid");
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
		
		earnPoints(custid, lpid, userip);
		
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
				//home.purchase(custid);
				break;
			case 2:
				rewardName = "Leave a review";
				//home.leaveReview();
				break;
			case 3:
				rewardName = "Refer a friend";
				//home.referFriend();
				break;
			case 4:
				System.out.println("Go back");
				//home.customerLanding(custid);
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

	public void earnPoints(int custid,int lpid, String acttype) throws Exception {

		DBConnector db = new DBConnector();
        Connection con = db.getConnection();
        Scanner op1 = new Scanner(System.in);
        
        Connection conn = db.getConnection();
        PreparedStatement statement = con.prepareStatement("Select t.repoints from rerules t, activitytype at where t.lpid = ? and at.actname = ?");
		statement.setInt(1, lpid);
		statement.setString(2, acttype);
		
		ResultSet rs = statement.executeQuery();
		rs.next();
		int points = rs.getInt("repoints");
		
		PreparedStatement statement1;
		statement1 = conn.prepareStatement("SELECT points, tier from WALLET where cid = ? and lpid = ?");
		statement1.setInt(1, custid);
		statement1.setInt(2, lpid);
		
		ResultSet rs2 = statement1.executeQuery();
		rs2.next();

		int custpoints = rs2.getInt("points");
		int cust_tier = rs2.getInt("tier");
		
		statement1 = conn.prepareStatement("SELECT lp_type from LOYALTYPROGRAM where lpid = ?");
		statement1.setInt(1, lpid);
		ResultSet rs3 = statement1.executeQuery();
		rs3.next();
		
		int multiplier = 1;
		int totalpts = 0;
		int new_cust_tier = cust_tier;
		String lp_type = rs3.getString("lp_type");
		
		if (lp_type == "tiered") {
			statement1 = conn.prepareStatement("SELECT multiplier from BRAND_TIER where lpid = ? and tier_num = ?");
			statement1.setInt(1, lpid);
			statement1.setInt(2, cust_tier);
			ResultSet rs4 = statement1.executeQuery();
			rs4.next();
			
			multiplier = rs4.getInt("multiplier");
			totalpts = custpoints + (points * multiplier);
			
			statement1 = conn.prepareStatement("SELECT tier,points from BRAND_TIER where lpid = ? and tier_num >= ?");
			statement1.setInt(1, lpid);
			statement1.setInt(2, cust_tier);
			ResultSet rs5 = statement1.executeQuery();
			
			while(rs5.next()) {
				
				int tier = rs5.getInt("tier");
				int rew_pts = rs5.getInt("points");
				
				if (totalpts >= rew_pts)
					new_cust_tier = tier;
			}
			
		}

		totalpts = custpoints + (points * multiplier);

		statement1 = conn.prepareStatement("UPDATE WALLET set points = ?, cust_tier = ? where cid = ? and lpid = ?");
		statement1.setInt(1, totalpts);
		statement1.setInt(2, new_cust_tier);
		statement1.setInt(3, custid);
		statement1.setInt(4, lpid);

		statement1.executeUpdate();
		
		

		System.out.println("You have earned " + points + " points");
		

	}
	
	public void redeemPoints(int custid, int lpid, String rewtype) throws Exception {

		DBConnector db = new DBConnector();
        Connection con = db.getConnection();
        Scanner op1 = new Scanner(System.in);
        
        PreparedStatement statement = con.prepareStatement("Select t.rrpoints, t.rew_count from rrrules t, rewardtype rt where t.lpid = ? and rt.rewname = ?");
		statement.setInt(1, lpid);
		statement.setString(2, rewtype);
		
		ResultSet rs = statement.executeQuery();
		rs.next();
		int rewardPoints = rs.getInt("rrpoints");
		int brandRewCount = rs.getInt("rew_count");
		
		PreparedStatement statement1;
		statement1 = con.prepareStatement("SELECT points, num_cust_gc, num_cust_fp from WALLET where cid = ? and lpid = ?");
		statement1.setInt(1, custid);
		statement1.setInt(2, lpid);
		
		ResultSet rs2 = statement1.executeQuery();
		rs2.next();

		int custpoints = rs2.getInt("points");
		
		int customerGCCount = rs2.getInt("num_cust_gc");
		int customerFPCount = rs2.getInt("num_cust_fp");
		int totalpoints = 0;
		
		if(rewtype.equalsIgnoreCase("GiftCard")) {
			int giftCardCount = 0;
			Scanner op = new Scanner(System.in);
			System.out.println("Enter the number of gift cards you want");
			System.out.println("This Loyalty Program has " + brandRewCount + " gift cards left at " + rewardPoints + " each");
			while(true) {
				System.out.print("How many do you want: ");
				giftCardCount = op.nextInt();
				if (giftCardCount <= brandRewCount && custpoints >= rewardPoints * giftCardCount) {
					totalpoints = custpoints - (rewardPoints * giftCardCount);
					customerGCCount += giftCardCount;
					brandRewCount -= giftCardCount;
					break;
				}
				else {
					System.out.println("Enetered amount is greater than number available or you do not have enough points");
				}
			}
		}
		else {
			int freeProductCount = 0;
			Scanner op = new Scanner(System.in);
			System.out.println("Enter the number of free products you want");
			System.out.println("This Loyalty Program has " + brandRewCount + " free products left at " + rewardPoints + " each");
			while(true) {
				System.out.print("How many do you want: ");
				freeProductCount = op.nextInt();
				if (freeProductCount <= brandRewCount && custpoints >= rewardPoints * freeProductCount) {
					totalpoints = custpoints - (rewardPoints * freeProductCount);
					customerFPCount += freeProductCount;
					brandRewCount -= freeProductCount;
					break;
				}
				else {
					System.out.println("Enetered amount is greater than number available or you do not have enough points");
				}
			}
		}

		

		String queryStatement = "UPDATE WALLET set points = ?, num_cust_gc = ?, num_cust_fp = ? where cid = ? and lpid = ?";
		statement1 = con.prepareStatement(queryStatement);
		statement1.setInt(1, totalpoints);
		statement1.setInt(2, customerGCCount);
		statement1.setInt(3, customerFPCount);
		statement1.setInt(4, custid);
		statement1.setInt(5, lpid);

		statement1.executeUpdate();
		
		queryStatement = "UPDATE RRRules set rew_count = ? where lpid = ? and rewname = ?";
		statement1 = con.prepareStatement(queryStatement);
		statement1.setInt(1, brandRewCount);
		statement1.setInt(2, lpid);
		statement1.setString(3, rewtype);

		statement1.executeUpdate();

		System.out.println("You have spent " + totalpoints + " points");
		

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
				//home.customerLanding(custid);
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
	
	public void redeemPointsUI(int custid) throws Exception {
		int lpid = getlpid(custid);

		RewardType rew = new RewardType();
		//Map<Integer, String> actkeys = new HashMap();
		
    	props = readPropertiesFile();
		home = new Home();
		Connection con = db.getConnection();
		String selectRewTypes =  props.getProperty("selectRewardIDs");	
		
		PreparedStatement statement = con.prepareStatement("Select t.rewid, rt.rewName from rrrules t, rewardtype rt where t.lpid = ? and t.rewid = rt.rewid");
		statement.setInt(1, lpid);
		ResultSet rs = statement.executeQuery();
		
		
		int ind = 1;
		while(rs.next()) {
			//actkeys.put(ind, rs.getString("actName"));
    		System.out.println(rs.getInt("rewid") + "--" +rs.getString("rewname"));
			
		}
		
		System.out.print("Choose the name from the above Rewards: ");
		Scanner op = new Scanner(System.in);
		String userip = op.next();
		
		redeemPoints(custid, lpid, userip);
	}
	
	public void viewWallet(int custid) {
		try {
			props = readPropertiesFile();
			home = new Home();
			con = db.getConnection();
			String viewWallet = props.getProperty("viewWallet");
			PreparedStatement statement = con.prepareStatement(viewWallet);
			statement.setInt(1, custid);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				
				System.out.println("Points you have in your wallet for Loyalty Program " +rs.getString("lpname")+" are "
						+ rs.getString("points"));
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

}
