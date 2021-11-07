import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Admin {

    
    public static void adminLanding() throws Exception {

        Scanner op = new Scanner(System.in);
        System.out.println("Welcome to Admin Screen !!!");

        System.out.println("Please select which action you want to perform :-");
        System.out.println("1. Add Brand");
        System.out.println("2. Add Customer");
        System.out.println("3. Show Brand's Info");
        System.out.println("4. Show Customer's Info");
        System.out.println("5. Add Activity Type");
        System.out.println("6. Add Reward Type");
        System.out.println("7. Logout");

        System.out.print("Your Option : ");

        int userop = op.nextInt();

        switch (userop) {
        case 1:
            addBrandPage();
            break;
        case 2:
            addCustomerPage();
            break;
        case 3:
            showBrandInfoPage();
            break;
        case 4:
            showCustomerInfoPage();
            break;
        case 5:
            addActivityTypePage();
            break;
        case 6:
            addRewardTypePage();
            break;
        case 7:
            System.out.println("Logging out...");
            Home.main(null);
            break; 
        default:
            System.out.println("You chose an invalid option");
        }

        op.close();

    }

    public static void addRewardTypePage() throws Exception {

        Scanner op1 = new Scanner(System.in);
        System.out.println("Welcome to Add Customer Screen !!!");

        System.out.println("Please choose from below, to which category you belong");
        System.out.println("1.Add Reward Type");
        System.out.println("2.Go Back");
        System.out.print("Your Option : ");

        int userop = op1.nextInt();

        switch (userop) {
        case 1:
            addRewardType();
            break;
        case 2:
            Admin.adminLanding();
            break;
        default:
            System.out.println("You chose an invalid option");
        }

        
    }

    public static void addRewardType() throws Exception {

        DBConnector db = new DBConnector();
        Connection con = db.getConnection();
        Scanner op1 = new Scanner(System.in);
        
        System.out.println("Please enter the Reward Type ID : ");
        String rewId = op1.nextLine();
        
        System.out.println("Please enter the Reward Type Name : ");
        String rewardTypeName = op1.nextLine();
        
        System.out.println("Please enter the Reward Type Expiry Date : ");
        
        SimpleDateFormat dateInput = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = op1.nextLine();

        Date rewExpdate = dateInput.parse(strDate);
        System.out.println(rewExpdate);
        System.out.println(rewExpdate.getTime());
        System.out.println(new java.sql.Date(rewExpdate.getTime()));
        
        try {
        String sql = "INSERT INTO REWARDTYPE (rewId, rewname, expirydate) VALUES (?,?,?)";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setInt(1, Integer.valueOf(rewId));
        pstmt.setString(2, rewardTypeName);
        pstmt.setDate(3, new java.sql.Date(rewExpdate.getTime()));
        pstmt.executeUpdate();

        System.out.println("Reward Type Added Successfully");
        System.out.println(" ");
        Admin.adminLanding();
        db.close(con);
        
        }
        
        catch (Exception e) {
            System.out.println("Customer ID not found");
            Admin.adminLanding();
        }
        
    }

    public static void addActivityTypePage() throws Exception {
        
        Scanner op1 = new Scanner(System.in);
        System.out.println("Welcome to Add Customer Screen !!!");

        System.out.println("Please choose from below, to which category you belong");
        System.out.println("1.Add Activity Type");
        System.out.println("2.Go Back");
        System.out.print("Your Option : ");

        int userop = op1.nextInt();

        switch (userop) {
        case 1:
            addActivityType();
            break;
        case 2:
            Admin.adminLanding();
            break;
        default:
            System.out.println("You chose an invalid option");
        }


    }

    public static void addActivityType() throws Exception {

        DBConnector db = new DBConnector();
        Connection con = db.getConnection();
        
        Scanner op1 = new Scanner(System.in);
        
        System.out.println("Please enter the Activity Type ID : ");
        String actId = op1.nextLine();
        
        System.out.println("Please enter the Activity Type Code : ");
        String actCode = op1.nextLine();
        
        System.out.println("Please enter the Activity Type Name : ");
        String activityTypeName = op1.nextLine();
        
        try {

        String sql = "INSERT INTO ACTIVITYTYPE (actid, actcode, actname, actdate) VALUES (?,?,?,(SELECT SYSDATE FROM DUAL))";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, actId);
        pstmt.setString(2, actCode);
        pstmt.setString(3, activityTypeName);
        pstmt.executeUpdate();

        System.out.println("Activity Type Added Successfully");
        System.out.println(" ");
        Admin.adminLanding();
        //op1.close();
        db.close(con);
        
        }
        catch (Exception e) {
            System.out.println("Customer ID not found");
            Admin.adminLanding();
        }
        

    }

    public static void showCustomerInfoPage() throws Exception {

        Scanner op1 = new Scanner(System.in);
        System.out.println("Welcome to Add Customer Screen !!!");

        System.out.println("Please choose from below, to which category you belong");
        System.out.println("1.Show Customer Info");
        System.out.println("2.Go Back");
        System.out.print("Your Option : ");

        int userop = op1.nextInt();

        switch (userop) {
        case 1:
            showCustomerInfo();
            break;
        case 2:
            Admin.adminLanding();
            break;
        default:
            System.out.println("You chose an invalid option");
        }

    }

    public  static  void showCustomerInfo() throws Exception {

        DBConnector db = new DBConnector();
        Connection con = db.getConnection();
        Scanner op1 = new Scanner(System.in);
        
        System.out.println("Please enter the Customer Name : ");
        String customerName = op1.nextLine();
        Connection conn = db.getConnection();
        PreparedStatement statement;
        
        try 
        {
            statement = conn.prepareStatement("SELECT cname, phoneno, caddr from Customer where cname = ?");
            statement.setString(1, customerName);
            
            ResultSet rs = statement.executeQuery();
            
            ResultSetMetaData rsmd = rs.getMetaData();
            
            int columnsNumber = rsmd.getColumnCount();
            
            while (rs.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    if (i > 1) System.out.print(",  ");
                    String columnValue = rs.getString(i);
                    System.out.print( rsmd.getColumnName(i)  + "--" + columnValue);
                }
                System.out.println("");
            }
            
            System.out.println(" ");
            db.close(con);
            Admin.adminLanding();
        } 
        
        catch (Exception e) {
            System.out.println("Customer ID not found");
            Admin.adminLanding();
        }

    }

    public static void showBrandInfoPage() throws Exception {

        DBConnector db = new DBConnector();
        Connection con = db.getConnection();
        db.close(con);
        Scanner op1 = new Scanner(System.in);
        System.out.println("Welcome to Add Customer Screen !!!");

        System.out.println("Please choose from below, to which category you belong");
        System.out.println("1.Show Brand Info");
        System.out.println("2.Go Back");
        System.out.print("Your Option : ");

        int userop = op1.nextInt();
        
        switch (userop) {
        case 1:
            showBrandInfo();
            break;
        case 2:
            Admin.adminLanding();
            break;
        default:
            System.out.println("You chose an invalid option");
        }

        System.out.println(" ");

    }

    public static void showBrandInfo() throws Exception {
        
        DBConnector db = new DBConnector();
        Connection con = db.getConnection();
        Scanner op1 = new Scanner(System.in);
        
        System.out.println("Please enter the Brand Name : ");
        String brandName = op1.nextLine();
        Connection conn = db.getConnection();
        PreparedStatement statement;
        
        try 
        {
            statement = conn.prepareStatement("SELECT bname, baddr, bjoindate from brand where bname = ?");
            statement.setString(1, brandName);
            
            ResultSet rs = statement.executeQuery();
            
            ResultSetMetaData rsmd = rs.getMetaData();
            
            int columnsNumber = rsmd.getColumnCount();
            while (rs.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    if (i > 1) System.out.print(",  ");
                    String columnValue = rs.getString(i);
                    System.out.print(rsmd.getColumnName(i)  + "--" +  columnValue);
                }
                System.out.println("");
            }
            
            db.close(con);
            System.out.println(" ");
            Admin.adminLanding();         
            
        } 
        
        catch (Exception e) {
            System.out.println("Brand ID not found");
            Admin.adminLanding();
        }

    }
    public static void addCustomerPage() throws Exception {

        DBConnector db = new DBConnector();
        Connection con = db.getConnection();
        db.close(con);
        Scanner op1 = new Scanner(System.in);
        System.out.println("Welcome to Add Customer Screen !!!");

        System.out.println("Please choose from below, to which category you belong");
        System.out.println("1.Add Customer");
        System.out.println("2.Go Back");
        System.out.print("Your Option : ");

        int userop = op1.nextInt();

        switch (userop) {
        case 1:
            addCustomer();
            break;
        case 2:
            Admin.adminLanding();
            break;
        default:
            System.out.println("You chose an invalid option");
        }


    }

    public static void addCustomer() throws Exception {
            
        DBConnector db = new DBConnector();
        Connection con = db.getConnection();
        //db.close(con);
        Scanner op1 = new Scanner(System.in);

        System.out.println("Please enter the Customer ID : ");
        String custId = op1.nextLine();
        System.out.println("Please enter the Cutomer Name : ");
        String custName = op1.nextLine();
        System.out.println("Please enter the Cutomer Password : ");
        String custPassword = op1.nextLine();
        System.out.println("Please enter the Cutomer Address : ");
        String customerAddress = op1.nextLine();
        System.out.println("Please enter the Cutomer Phone Number : ");
        String customerPhone = op1.nextLine();
        
        String sql = "INSERT INTO U_ADMIN (userid, username, usercode, userpwd) VALUES (?,?,?,?)";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setInt(1, Integer.valueOf(custId));
        pstmt.setString(2, custName);
        pstmt.setString(3, "C");
        pstmt.setString(4, custPassword);
        pstmt.executeUpdate();
        
        PreparedStatement statement;
        try {
	        statement = con.prepareStatement("SELECT userid from U_ADMIN where username = ?");
	        statement.setString(1, custName);
	        
	        ResultSet rs = statement.executeQuery();
	        rs.next();
	        Integer userid = rs.getInt("userid");
	        
	        //Admin.adminLanding();
	        System.out.println(userid);
	      
	        Integer id = 4000;
	        String sql1 = "INSERT INTO CUSTOMER (cid, cname, phoneno, caddr, userid) VALUES (?,?,?,?,?)";
	        PreparedStatement pstmt1 = con.prepareStatement(sql1);
	        pstmt1.setInt(1, id++);
	        pstmt1.setString(2, custName);
	        pstmt1.setString(3, customerPhone);
	        pstmt1.setString(4, customerAddress);
	        pstmt1.setInt(5, userid);
	        
	        pstmt1.executeUpdate();
	
	        System.out.println("Customer Added Successfully");
	        Admin.adminLanding();
	        System.out.println(" ");
	        db.close(con);
        
        } 
        catch (Exception e) {
            System.out.println("Customer ID not found");
            Admin.adminLanding();
        }


    }

    public static void addBrandPage() throws Exception {

        DBConnector db = new DBConnector();
        Connection con = db.getConnection();
        db.close(con);
        Scanner op1 = new Scanner(System.in);
        System.out.println("Welcome to Add Brand Screen !!!");

        System.out.println("Please choose from below, to which category you belong");
        System.out.println("1.Add Brand");
        System.out.println("2.Go Back");
        System.out.print("Your Option : ");

        int userop = op1.nextInt();

        switch (userop) {
        case 1:
            addBrand();
            break;
        case 2:
            Admin.adminLanding();
            break;
        default:
            System.out.println("You chose an invalid option");
        }

    }

    public static void addBrand() throws Exception { 
    	
                   
        DBConnector db = new DBConnector();
        Connection con = db.getConnection();
        System.out.println(con.prepareStatement("SELECT SYSDATE FROM DUAL").executeQuery());
        
        
        Scanner op1 = new Scanner(System.in);
        
        System.out.println("Please enter the Brand ID : ");
        String brandId = op1.nextLine();
        System.out.println("Please enter the Brand Name : ");
        String brandName = op1.nextLine();
        System.out.println("Please enter the Brand Password : ");
        String brandPassword = op1.nextLine();
        System.out.println("Please enter the Brand Address : ");
        String brandAddress = op1.nextLine();
        
        String sql = "INSERT INTO U_ADMIN (userid, username, usercode, userpwd) VALUES (?,?,?,?)";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setInt(1, Integer.valueOf(brandId));
        pstmt.setString(2, brandName);
        pstmt.setString(3, "B");
        pstmt.setString(4, brandPassword);
        pstmt.executeUpdate();
        
        PreparedStatement statement;
        try {
            statement = con.prepareStatement("SELECT userid from U_ADMIN where username = ?");
            statement.setString(1, brandName);
            
            ResultSet rs = statement.executeQuery();
            rs.next();
            Integer userid = rs.getInt("userid");
            
            //Admin.adminLanding();
            System.out.println(userid);
      
        Integer id = 2000;
        String sql1 = "INSERT INTO BRAND (bid, bname, baddr, bjoindate, userid) VALUES (?,?,?,(SELECT SYSDATE FROM DUAL),?)";
        PreparedStatement pstmt1 = con.prepareStatement(sql1);
        pstmt1.setInt(1, id++);
        pstmt1.setString(2, brandName);
        pstmt1.setString(3, brandAddress);
        //pstmt1.setDate(4, con.prepareStatement("SELECT SYSDATE FROM DUAL").executeQuery().getDate(0));
        pstmt1.setInt(4, userid);
        
        pstmt1.executeUpdate();

        System.out.println("Brand Added Successfully");
        System.out.println(" ");
        Admin.adminLanding();
        db.close(con);
        
        } 
        catch (Exception e) {
           System.out.println("Brand ID not found");
           Admin.adminLanding();
       }
    }

}