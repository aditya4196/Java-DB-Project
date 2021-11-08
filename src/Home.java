import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Home {
    public static void main(String[] args) throws Exception {

        Home home = new Home();
        DBConnector db = new DBConnector();
        Connection con = db.getConnection();
        db.close(con);
        Scanner op = new Scanner(System.in);
        
        System.out.println("Welcome to the MarketPlace !!!");

        System.out.println("Please choose from the below options");
        System.out.println("1. Sign up");
        System.out.println("2. Login");
        System.out.println("3. Show Queries");
        System.out.print("Your Option : ");

        int option = op.nextInt();
        
        switch (option) {
        case 1:
            home.signup();
            break;
        case 2:
            home.login();
            break;
            
        case 3:
            Queries.showQueries();
            break;
        default:
            System.out.println("Chose an invalid option");
            main(args);
        }

        op.close();
    }

    public void signup() throws Exception {
        Scanner op = new Scanner(System.in);
        System.out.println("Welcome to SignUp Screen !!!");
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

    public void customerSignup() throws Exception {
        System.out.println("Welcome to Customer Signup");
        Admin.addCustomer();
        main(null);
    }

    public void brandSignup() throws Exception {
        System.out.println("Welcome to Brand Signup");
        Admin.addBrand();
        main(null);
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
                Admin.adminLanding();
                break;
            case "B":
            	statement = conn.prepareStatement("SELECT BID from BRAND where userid = (Select userid from U_ADMIN where USERNAME = ?)");
                statement.setString(1, usr);
                ResultSet rs1 = statement.executeQuery();
                rs1.next();
                int bid = rs1.getInt("bid");
                brandLanding(bid);
                break;
            case "C":
            	
                statement = conn.prepareStatement("SELECT c.cid from Customer c, U_Admin u where u.userid = c.userid and u.username = ? and u.userpwd = ?");
                statement.setString(1, usr);
                statement.setString(2, pass);
                ResultSet rs2 = statement.executeQuery();
                rs2.next();
                System.out.println(rs2);
                int custid = rs2.getInt("cid");
                
                customerLanding(custid);
                break;
            }
        } catch (NullPointerException ne) {
            System.out.println("User is not Registered");
        }

        op.close();
    }

    public void logout() throws Exception {
        main(null);
    }

    public void customerLanding(int custid) throws Exception {
        Scanner op = new Scanner(System.in);
        Customer cstmr = new Customer();
        System.out.println("1. Enroll in Loyalty Program");
        System.out.println("2. Reward Activities");
        System.out.println("3. View Wallet");
        System.out.println("4. Redeem Points");
        System.out.println("5. Logout");
        System.out.print("Your Option : ");

        int userop = op.nextInt();

        switch (userop) {
        case 1:
            cstmr.enrollInLP(custid);
            break;
        case 2:
        	cstmr.getrewardActivitiesScreen(custid);
        	break;
        case 3:
        	cstmr.viewWallet(custid);
        	break;
        case 4:
        	cstmr.redeemPointsUI(custid);
        	break;
        case 5:
        	logout();
        	break;
        default:
            System.out.println("You have entered an invlaid option");
            customerLanding(custid);
        }

        op.close();
    }

    public void brandLanding(int bid) throws Exception {
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
            bd.addLoyaltyProgram(bid);
            break;
        case 2:
            bd.addRERules(bid);
            break;
        case 3:
            bd.updateRERules(bid);
            break;
        case 4:
            bd.addRRRules(bid);
            break;
        case 5:
            bd.updateRRRules(bid);
            break;
        case 6:
            bd.validateLoyaltyProgram(bid);
            break;
        case 7:
            logout();
            break;
        default:
            System.out.println("You have entered an invlaid option");
            brandLanding(bid);
        }

        op.close();
    }

}
