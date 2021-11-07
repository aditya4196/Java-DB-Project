import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;

public class Brand{
	
	DBConnector db = new DBConnector();
	Properties props;
	Connection con;
	Home home = new Home();
	
    public void addLoyaltyProgram(int bid) throws Exception {
    	Scanner op = new Scanner(System.in);
    	System.out.println("1. Regular");
    	System.out.println("2. Tiered");
    	System.out.println("3. Go Back");
    	System.out.print("Your option: ");
    	
    	int userop = op.nextInt();
    	
    	switch (userop) {
        case 1:
            regular(bid);
            break;
        case 2:
            tiered(bid);
            break;
        case 3:
            home.brandLanding(bid);
            break;
        default:
            System.out.println("You have entered an invlaid option");
            addLoyaltyProgram(bid);
    	}
    	
    	op.close();
    }
    
    public int insertLoyaltyProgram(int bid, String lpname, int lptype) {
    	
    try {	
    	props = readPropertiesFile();
		home = new Home();
		Connection con = db.getConnection();
		String insertLoyalty =  props.getProperty("insertLoyalty");
		String getLoyaltyProgram = props.getProperty("getLoyaltyProgram");
		
		
		PreparedStatement statement1 = con.prepareStatement(insertLoyalty);
		PreparedStatement statement2 = con.prepareStatement(getLoyaltyProgram);
		
		statement1.setString(1,lpname);
		statement1.setInt(2, bid);
		statement1.setInt(3, lptype);
		
		ResultSet rs = statement1.executeQuery();
		if(rs!=null) {
			System.out.println("Added Loyalty Program successfully");
		}

		
		statement2.setInt(1, bid);
		rs = statement2.executeQuery();
		
		rs.next();
		return rs.getInt("lpid");
    }
    catch(Exception e) {
    	System.out.println("You can add only one Loyalty Program");
    	e.printStackTrace();
    	
    }
    
    return -1;

    }
    
    public void regular(int bid) throws Exception {
    	Scanner op = new Scanner(System.in);
    	System.out.print("Please enter the Loyalty Program Name : ");
		String lpname = op.nextLine();
		int lpid = insertLoyaltyProgram(bid, lpname, 0);
    	
    	
    	ActivityType at = new ActivityType();
    	RewardType rt = new RewardType();
    	System.out.println("1. Activity Types");
    	System.out.println("2. Reward Types");
    	System.out.println("3. Go Back");
    	System.out.print("Your option: ");
    	
    	int userop = op.nextInt();
		
    	switch (userop) {
        case 1:
            at.activityTypes(0, lpid);
            break;
        case 2:
            rt.rewardTypes(0, lpid);
            break;
        case 3:
            //addLoyaltyProgram();
            break;
        default:
            System.out.println("You have entered an invalid option");
            regular(bid);
    	}
    	
    	op.close();
	}

	public void tiered(int bid) throws Exception {
    	Scanner op = new Scanner(System.in);
    	System.out.print("Please enter the Loyalty Program Name : ");
		String lpname = op.nextLine();
		int lpid = insertLoyaltyProgram(bid, lpname, 1);
		
    	ActivityType at = new ActivityType();
    	RewardType rt = new RewardType();
    	LoyaltyProgram lp = new LoyaltyProgram();
    	System.out.println("1. Tiers Setup");
    	System.out.println("2. Activity Types");
    	System.out.println("3. Reward Types");
    	System.out.println("4. Go Back");
    	System.out.print("Your option: ");
    	
    	int userop = op.nextInt();
		
    	switch (userop) {
    	case 1:
            lp.tiersSetupLanding();
            break;
        case 2:
            at.activityTypes(1,lpid);
            break;
        case 3:
            rt.rewardTypes(1,lpid);
            break;
        case 4:
           // addLoyaltyProgram();
            break;
        default:
            System.out.println("You have entered an invlaid option");
            tiered(bid);
    	}
    	
    	op.close();
		
	}

	public void addRERules(int bid) throws Exception {
		Scanner op = new Scanner(System.in);
		RERules re = new RERules();
    	System.out.println("1. Add RE Rules");
    	System.out.println("2. Go Back");
    	System.out.print("Your option: ");
    	
    	int userop = op.nextInt();
    	
    	switch (userop) {
        case 1:
           // re.addRERules(bid);
            break;
        case 2:
          //  home.brandLanding();
            break;
        default:
            System.out.println("You have entered an invlaid option");
            addRERules(bid);
    	}
    	
    	op.close();
    }

    public void updateRERules(int bid) throws Exception {
    	Scanner op = new Scanner(System.in);
		RERules re = new RERules();
    	System.out.println("1. Update RE Rules");
    	System.out.println("2. Go Back");
    	System.out.print("Your option: ");
    	
    	int userop = op.nextInt();
    	
    	switch (userop) {
        case 1:
           // re.updateRERules(bid);
            break;
        case 2:
           // home.brandLanding(bid);
            break;
        default:
            System.out.println("You have entered an invlaid option");
           // updateRERules();
    	}
    	
    	op.close();
    }

    public void addRRRules(int bid) throws Exception {
    	Scanner op = new Scanner(System.in);
		RRRules rr = new RRRules();
    	System.out.println("1. Add RR Rules");
    	System.out.println("2. Go Back");
    	System.out.print("Your option: ");
    	
    	int userop = op.nextInt();
    	
    	switch (userop) {
        case 1:
            rr.addRRRules(bid);
            break;
        case 2:
            home.brandLanding(bid);
            break;
        default:
            System.out.println("You have entered an invalid option");
            addRRRules(bid);
    	}
    	
    	op.close();
    }

    public void updateRRRules(int bid) throws Exception {
    	Scanner op = new Scanner(System.in);
		RRRules rr = new RRRules();
    	System.out.println("1. Update RR Rules");
    	System.out.println("2. Go Back");
    	System.out.print("Your option: ");
    	
    	int userop = op.nextInt();
    	
    	switch (userop) {
        case 1:
         //   rr.updateRRRules();
            break;
        case 2:
           // home.brandLanding();
            break;
        default:
            System.out.println("You have entered an invlaid option");
            updateRRRules(bid);
    	}
    	
    	op.close();
    }

    public void validateLoyaltyProgram() throws Exception {
    	Scanner op = new Scanner(System.in);
		LoyaltyProgram lp = new LoyaltyProgram();
    	System.out.println("1. Validate Loyalty Program");
    	System.out.println("2. Go Back");
    	System.out.print("Your option: ");
    	
    	int userop = op.nextInt();
    	
    	switch (userop) {
        case 1:
            lp.validateLP();
            break;
        case 2:
           // home.brandLanding();
            break;
        default:
            System.out.println("You have entered an invlaid option");
            validateLoyaltyProgram();
    	}
    	
    	op.close();
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
