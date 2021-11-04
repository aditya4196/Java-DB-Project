import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

        Scanner op = new Scanner(System.in);
        System.out.println("Welcome to Add Customer Screen !!!");

        System.out.println("Please choose from below, to which category you belong");
        System.out.println("1.Add Reward Type");
        System.out.println("2.Go Back");
        System.out.print("Your Option : ");

        int userop = op.nextInt();

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

        op.close();

    }

    public static void addRewardType() throws Exception {

        DBConnector db = new DBConnector();
        Connection con = db.getConnection();
        db.close(con);
        Scanner op = new Scanner(System.in);
        
        System.out.println("Please enter the Reward Type Name : ");
        String rewardTypeName = op.nextLine();
        System.out.println("Please enter the Reward Type Description : ");
        String rewardTypeDescription = op.nextLine();

        String sql = "INSERT INTO reward_type (reward_type_name, reward_type_description) VALUES (?,?)";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, rewardTypeName);
        pstmt.setString(2, rewardTypeDescription);
        pstmt.executeUpdate();

        System.out.println("Reward Type Added Successfully");
        Admin.adminLanding();
        op.close();

    }

    public static void addActivityTypePage() throws Exception {
        
        Scanner op = new Scanner(System.in);
        System.out.println("Welcome to Add Customer Screen !!!");

        System.out.println("Please choose from below, to which category you belong");
        System.out.println("1.Add Activity Type");
        System.out.println("2.Go Back");
        System.out.print("Your Option : ");

        int userop = op.nextInt();

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

        op.close();

    }

    public static void addActivityType() throws Exception {

        DBConnector db = new DBConnector();
        Connection con = db.getConnection();
        db.close(con);
        Scanner op = new Scanner(System.in);
        
        System.out.println("Please enter the Activity Type Name : ");
        String activityTypeName = op.nextLine();
        System.out.println("Please enter the Activity Type Description : ");
        String activityTypeDescription = op.nextLine();

        String sql = "INSERT INTO activity_type (activity_type_name, activity_type_description) VALUES (?,?)";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, activityTypeName);
        pstmt.setString(2, activityTypeDescription);
        pstmt.executeUpdate();

        System.out.println("Activity Type Added Successfully");
        Admin.adminLanding();
        op.close();

    }

    public static void showCustomerInfoPage() throws Exception {

        Scanner op = new Scanner(System.in);
        System.out.println("Welcome to Add Customer Screen !!!");

        System.out.println("Please choose from below, to which category you belong");
        System.out.println("1.Show Customer Info");
        System.out.println("2.Go Back");
        System.out.print("Your Option : ");

        int userop = op.nextInt();

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

        op.close();
    }

    public  static  void showCustomerInfo() throws Exception {

        DBConnector db = new DBConnector();
        Connection con = db.getConnection();
        db.close(con);
        Scanner op = new Scanner(System.in);
        
        System.out.println("Please enter the Customer ID : ");
        String customerID = op.nextLine();
        Connection conn = db.getConnection();
        PreparedStatement statement;
        
        try 
        {
            statement = conn.prepareStatement("SELECT CustomerID from Customer where CustomerID = ?");
            statement.setString(1, customerID);
            
            ResultSet rs = statement.executeQuery();
            rs.next();
            String usercode = rs.getString("usercode");
            
            op.close();
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
        Scanner op = new Scanner(System.in);
        System.out.println("Welcome to Add Customer Screen !!!");

        System.out.println("Please choose from below, to which category you belong");
        System.out.println("1.Show Brand Info");
        System.out.println("2.Go Back");
        System.out.print("Your Option : ");

        int userop = op.nextInt();

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

        op.close();

    }

    public static void showBrandInfo() throws Exception {
        
        DBConnector db = new DBConnector();
        Connection con = db.getConnection();
        db.close(con);
        Scanner op = new Scanner(System.in);
        
        System.out.println("Please enter the Brand ID : ");
        String brandID = op.nextLine();
        Connection conn = db.getConnection();
        PreparedStatement statement;
        
        try 
        {
            statement = conn.prepareStatement("SELECT brandID from Brand where brandID = ?");
            statement.setString(1, brandID);
            
            ResultSet rs = statement.executeQuery();
            rs.next();
            String usercode = rs.getString("usercode");
            
            op.close();
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
        Scanner op = new Scanner(System.in);
        System.out.println("Welcome to Add Customer Screen !!!");

        System.out.println("Please choose from below, to which category you belong");
        System.out.println("1.Add Customer");
        System.out.println("2.Go Back");
        System.out.print("Your Option : ");

        int userop = op.nextInt();

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

        op.close();

    }

    public static void addCustomer() throws Exception {
            
        DBConnector db = new DBConnector();
        Connection con = db.getConnection();
        db.close(con);
        Scanner op = new Scanner(System.in);

        System.out.println("Please enter the Customer Name : ");
        String customerName = op.nextLine();
        System.out.println("Please enter the Customer Address : ");
        String customerAddress = op.nextLine();
        System.out.println("Please enter the Customer Phone Number : ");
        String customerPhoneNumber = op.nextLine();
        System.out.println("Please enter the Customer Email : ");
        String customerEmail = op.nextLine();
        System.out.println("Please enter the Customer Password : ");
        String customerPassword = op.nextLine();

        String sql = "INSERT INTO customer (customer_name, customer_address, customer_phone_number, customer_email, customer_password) VALUES (?,?,?,?,?)";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, customerName);
        pstmt.setString(2, customerAddress);
        pstmt.setString(3, customerPhoneNumber);
        pstmt.setString(4, customerEmail);
        pstmt.setString(5, customerPassword);
        pstmt.executeUpdate();

        System.out.println("Customer Added Successfully");
        Admin.adminLanding();
        op.close();

    }

    public static void addBrandPage() throws Exception {

        DBConnector db = new DBConnector();
        Connection con = db.getConnection();
        db.close(con);
        Scanner op = new Scanner(System.in);
        System.out.println("Welcome to Add Brand Screen !!!");

        System.out.println("Please choose from below, to which category you belong");
        System.out.println("1.Add Brand");
        System.out.println("2.Go Back");
        System.out.print("Your Option : ");

        int userop = op.nextInt();

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

        op.close();

    }

    public static void addBrand() throws Exception { 
                   
        DBConnector db = new DBConnector();
        Connection con = db.getConnection();
        db.close(con);
        Scanner op = new Scanner(System.in);

        System.out.println("Please enter the Brand Name : ");
        String brandName = op.nextLine();
        System.out.println("Please enter the Brand Address : ");
        String brandAddress = op.nextLine();
        System.out.println("Please enter the Brand Phone Number : ");
        String brandPhoneNumber = op.nextLine();
        System.out.println("Please enter the Brand Email : ");
        String brandEmail = op.nextLine();
        System.out.println("Please enter the Brand Password : ");
        String brandPassword = op.nextLine();

        String sql = "INSERT INTO brand (brand_name, brand_address, brand_phone_number, brand_email, brand_password) VALUES (?,?,?,?,?)";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, brandName);
        pstmt.setString(2, brandAddress);
        pstmt.setString(3, brandPhoneNumber);
        pstmt.setString(4, brandEmail);
        pstmt.setString(5, brandPassword);
        pstmt.executeUpdate();

        System.out.println("Brand Added Successfully");
        Admin.adminLanding();
        op.close();

    }

}
