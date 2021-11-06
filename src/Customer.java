import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Formattable;
import java.util.Properties;
import java.util.Scanner;

public class Customer extends Throwable {

	DBConnector db = new DBConnector();
	Properties props;
	Connection con;
	Home home = new Home();

	public void enrollInLP(String custid) {

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

	private void enrollInLPProcess(String custid) {
		// Query for enrolling
		System.out.println("Customer enrolled in Loyalty program");

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
				break;
			case 2:
				rewardName = "Leave a review";
				break;
			case 3:
				rewardName = "Refer a friend";
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

}
