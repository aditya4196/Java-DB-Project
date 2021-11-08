import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.Scanner;

public class LoyaltyProgram {

	Brand bd = new Brand();
	
	public void tiersSetupLanding(int lpid) throws Exception {
		Scanner op = new Scanner(System.in);
		System.out.println("1. Setup");
		System.out.println("2. Go Back");
		System.out.print("Your option: ");
    	
    	int userop = op.nextInt();
		
    	switch (userop) {
    	case 1:
            tiersSetup(lpid);
            break;
        case 2:
           // bd.tiered();
            break;
		default:
			System.out.println("You have entered an invlaid option");
            tiersSetupLanding(lpid);
    	}
    	
    	op.close();
	}

public void tiersSetup(int lpid) throws Exception {
		
		DBConnector db = new DBConnector();
        Connection con = db.getConnection();
		Scanner op = new Scanner(System.in);
		
		int tiers = 0;

		while (true){
			System.out.println("Add the number of Tiers for your program : ");
			tiers = op.nextInt();
			if(tiers > 0 && tiers<=3){
				break;
			}
			else{
				System.out.println("Tiers cannot be greater than 3. Please try again.");
			}
		}

		int i;

		System.out.println("Enter the tier data in the order from lowest to highest tier.");

		for(i=1;i<=tiers;i++)
		{
			
			System.out.println("Enter the Tier Name: ");
			String tierName = op.nextLine();
			
			System.out.println("Please enter the points required to reach this tier: ");
			String req_points = op.nextLine();
			
			System.out.println("Please enter the multiplier for this tier: ");
			String tier_multi = op.nextLine();

			try {

				String sql = "INSERT INTO BRAND_TIER VALUES (?,?,?,?,?)";
				PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, lpid);
				pstmt.setString(2, tierName);
				pstmt.setInt(3, i);
				pstmt.setString(4, req_points);
				pstmt.setString(5, tier_multi);

				pstmt.executeUpdate();

				System.out.println("Brand Tier Added Successfully");
				System.out.println(" ");
			
			}			
			catch (Exception e) {
				System.out.println("Error creating the brand tier.");
				//Admin.adminLanding();
			}
			
		}	
		
		db.close(con);
		
	}

	public void validateLP(int bid) {
		DBConnector db = new DBConnector();
		try {	
		Properties props = readPropertiesFile();
		String getLpidFromBrand = props.getProperty("getLpidFromBrand");
		Connection con = db.getConnection();	
		PreparedStatement pstmt = con.prepareStatement(getLpidFromBrand);
		pstmt.setInt(1, bid);
		ResultSet rs = pstmt.executeQuery();
	    rs.next();
		CallableStatement stmt=con.prepareCall("{call Validate_LP(?)}");  
		stmt.setInt(1,rs.getInt("lpid"));  
		stmt.execute();
		} catch (Exception e) {
			System.out.println("Loyalty Program validated successfully");
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
