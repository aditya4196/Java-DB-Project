import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Home {
    public void main(String[] args) throws Exception {
        DBConnector db = new DBConnector();
        Connection con = db.getConnection();
        db.close(con);
        Scanner op = new Scanner(System.in);
        System.out.println("Welcome to the MarketPlace !!!");

        System.out.println("Please choose from the below options");
        System.out.println("1. SignUp");
        System.out.println("2. Login");
        System.out.println("3. ShowQueries");
        System.out.print("Your Option : ");

        int option = op.nextInt();

        switch (option) {
        case 1:
            signup();
            break;
        case 2:
            login();
            break;
        case 3:
            showQueries();
            break;
        default:
            System.out.println("Chose an invalid option");
            main(args);
        }

        op.close();
    }

    public void showQueries() {
        // logic to see all queries
    }

    public void signup() throws Exception {

        DBConnector db = new DBConnector();
        Connection con = db.getConnection();
        db.close(con);
        Scanner op = new Scanner(System.in);
        System.out.println("Welcome to SignUp Screen !!!");
        System.out.print("Define a username for yourself: ");
        String usr = op.nextLine();
        System.out.println();
        System.out.print("Enter your new password: ");
        String pass = op.nextLine();

        System.out.println("Please choose from below, to which category you belong");
        System.out.println("1. Customer");
        System.out.println("2. Brand");
        System.out.println("3. Go Back");
        System.out.print("Your Option : ");

        int userop = op.nextInt();

        switch (userop) {
        case 1:
            customerSignup();
            break;
        case 2:
            brandSignup();
            break;
        case 3:
            main(null);
            break;
        default:
            System.out.println("You chose an invalid option");
        }

        op.close();
    }

    public void customerSignup() {
        System.out.println("Welcome to Customer Signup");
        // Ask for customer details and insert
    }

    public void brandSignup() {
        System.out.println("Welcome to Brand Signup");
        // Ask for brand details and insert
    }

    public void login() throws Exception {

        DBConnector db = new DBConnector();

        Scanner op = new Scanner(System.in);

        System.out.println("Welcome to Login Screen !!!");
        System.out.print("Please enter your username : ");
        String usr = op.nextLine();
        System.out.println();
        System.out.print("Please enter your password : ");
        String pass = op.nextLine();

        Connection conn = db.getConnection();
        PreparedStatement statement;
        try {
            statement = conn.prepareStatement("SELECT usercode from U_Admin where username = ? and userpwd = ?");
            statement.setString(1, usr);
            statement.setString(2, pass);
            ResultSet rs = statement.executeQuery();
            rs.next();
            String usercode = rs.getString("usercode");

            switch (usercode) {
            case "A":
                adminLanding();
                break;
            case "B":
                brandLanding();
                break;
            case "C":
                customerLanding();
                break;
            }
        } catch (NullPointerException ne) {
            System.out.println("User is not Registered");
        }

        op.close();
    }

    public void logout() {
    }

    public void customerLanding() throws Exception {
        Scanner op = new Scanner(System.in);
        Customer cstmr = new Customer();
        System.out.println("1. Enroll in Loyalty Program");
        System.out.println("2. Go Back");
        System.out.print("Your Option : ");

        int userop = op.nextInt();

        switch (userop) {
        case 1:
            cstmr.enrollInLP();
            break;
        case 2:
            main(null);
            break;
        default:
            System.out.println("You have entered an invlaid option");
            customerLanding();
        }

        op.close();
    }

    public void brandLanding() throws Exception {
        Scanner op = new Scanner(System.in);
        Brand bd = new Brand();
        System.out.println("1. Add Loyalty Program");
        System.out.println("2. Add RE Rules");
        System.out.println("3. Update RE Rules");
        System.out.println("4. Add RR Rules");
        System.out.println("5. Update RR Rules");
        System.out.println("6. Validate Loyalty Program");
        System.out.println("7. Logout");
        System.out.print("Your Option : ");

        int userop = op.nextInt();

        switch (userop) {
        case 1:
            bd.addLoyaltyProgram();
            break;
        case 2:
            bd.addRERules();
            break;
        case 3:
            bd.updateRERules();
            break;
        case 4:
            bd.addRRRules();
            break;
        case 5:
            bd.updateRRRules();
            break;
        case 6:
            bd.validateLoyaltyProgram();
            break;
        case 7:
            logout();
            break;
        default:
            System.out.println("You have entered an invlaid option");
            brandLanding();
        }

        op.close();
    }

    public void adminLanding() {
        Scanner op = new Scanner(System.in);
        Admin ad = new Admin();
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
            ad.addBrand();
            break;
        case 2:
            ad.addCustomer();
            break;
        case 3:
            ad.showBrandInfo();
            break;
        case 4:
            ad.showCustomerInfo();
            break;
        case 5:
            ad.addActivityType();
            break;
        case 6:
            ad.addRewardType();
            break;
        case 7:
            logout();
            break;
        default:
            System.out.println("You have entered an invalid option");
            adminLanding();
        }

        op.close();
    }
}
