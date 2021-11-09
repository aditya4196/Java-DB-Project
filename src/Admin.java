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
            adminLanding();
            break;
        case 2:
            addCustomerPage();
            adminLanding();
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
        
        System.out.println("Please enter the Reward Type Name : ");
        String rewardTypeName = op1.nextLine();
        
        try {
        String sql = "INSERT INTO REWARDTYPE (rewname) VALUES (?)";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, rewardTypeName);
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
        
        System.out.println("Please enter the Activity Type Name : ");
        String activityTypeName = op1.nextLine();
        
        try {

        String sql = "INSERT INTO ACTIVITYTYPE (actname, actdate) VALUES (?,(SELECT SYSDATE FROM DUAL))";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, activityTypeName);
        pstmt.executeUpdate();

        System.out.println("Activity Type Added Successfully");
        System.out.println(" ");
        Admin.adminLanding();
        //op1.close();
        db.close(con);
        
        }
        catch (Exception e) {
        	e.printStackTrace();
            //System.out.println("Customer ID not found");
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
        
        System.out.println("Please enter the Customer Username : ");
        String customerUserName = op1.nextLine();
        Connection conn = db.getConnection();
        PreparedStatement statement;
        
        try 
        {
            statement = conn.prepareStatement("SELECT c.cname, c.phoneno, c.caddr from Customer c, U_Admin u where cname = ? and c.userid = u.userid");
            statement.setString(1, customerUserName);
            
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
        
        System.out.println("Please enter the Brand Username : ");
        String brandUserName = op1.nextLine();
        Connection conn = db.getConnection();
        PreparedStatement statement;
        
        try 
        {
            statement = conn.prepareStatement("SELECT b.bname, b.baddr, b.bjoindate from brand b, U_Admin u where b.userid = u.userid and b.bname = ?");
            statement.setString(1, brandUserName);
            
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
        	e.printStackTrace();
            //System.out.println("Brand ID not found");
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

        System.out.println("Please enter the Customer username : ");
        String custUserName = op1.nextLine();
        System.out.println("Please enter the Customer Password : ");
        String custPassword = op1.nextLine();
        System.out.println("Please enter the Customer Address : ");
        String customerAddress = op1.nextLine();
        System.out.println("Please enter the Customer Phone Number : ");
        String customerPhone = op1.nextLine();
        
        String sql = "INSERT INTO U_ADMIN (username, usercode, userpwd) VALUES (?,?,?)";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, custUserName);
        pstmt.setString(2, "C");
        pstmt.setString(3, custPassword);
        pstmt.executeUpdate();
        
        PreparedStatement statement;
        try {
	        statement = con.prepareStatement("SELECT userid from U_ADMIN where username = ?");
	        statement.setString(1, custUserName);
	        
	        ResultSet rs = statement.executeQuery();
	        rs.next();
	        Integer userid = rs.getInt("userid");
	        
	        //Admin.adminLanding();
	        System.out.println(userid);
	      
	        //Integer id = 4000;
	        String sql1 = "INSERT INTO CUSTOMER (cname, phoneno, caddr, userid) VALUES (?,?,?,?)";
	        PreparedStatement pstmt1 = con.prepareStatement(sql1);
	        pstmt1.setString(1, custUserName);
	        pstmt1.setString(2, customerPhone);
	        pstmt1.setString(3, customerAddress);
	        pstmt1.setInt(4, userid);
	        
	        pstmt1.executeUpdate();
	
	        System.out.println("Customer Added Successfully");
	        System.out.println(" ");
	        db.close(con);
        
        } 
        catch (Exception e) {
            System.out.println("Customer ID not found");
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
        
        System.out.println("Please enter the Brand username : ");
        String brandUserName = op1.nextLine();
        System.out.println("Please enter the Brand Password : ");
        String brandPassword = op1.nextLine();
        System.out.println("Please enter the Brand Address : ");
        String brandAddress = op1.nextLine();
        
        String sql = "INSERT INTO U_ADMIN (username, usercode, userpwd) VALUES (?,?,?)";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, brandUserName);
        pstmt.setString(2, "B");
        pstmt.setString(3, brandPassword);
        pstmt.executeUpdate();
        
        PreparedStatement statement;
        try {
            statement = con.prepareStatement("SELECT userid from U_ADMIN where username = ?");
            statement.setString(1, brandUserName);
            
            ResultSet rs = statement.executeQuery();
            rs.next();
            Integer userid = rs.getInt("userid");
            
            //Admin.adminLanding();
            System.out.println(userid);
      
        Integer id = 2000;
        String sql1 = "INSERT INTO BRAND (bname, baddr, bjoindate, userid) VALUES (?,?,(SELECT SYSDATE FROM DUAL),?)";
        PreparedStatement pstmt1 = con.prepareStatement(sql1);
        pstmt1.setString(1, brandUserName);
        pstmt1.setString(2, brandAddress);
        //pstmt1.setDate(4, con.prepareStatement("SELECT SYSDATE FROM DUAL").executeQuery().getDate(0));
        pstmt1.setInt(3, userid);
        
        pstmt1.executeUpdate();

        System.out.println("Brand Added Successfully");
        System.out.println(" ");
        db.close(con);
        
        } 
        catch (Exception e) {
           System.out.println("Brand ID not found");
       }
    }

}