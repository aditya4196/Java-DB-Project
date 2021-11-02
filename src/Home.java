import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Home {

    public static void login() throws Exception {

        DBConnector db = new DBConnector();

        Scanner op = new Scanner(System.in);

        System.out.println("Welcome to Login Screen !!!");
        System.out.print("Please enter your username : ");
        String usr = op.nextLine();
        System.out.println();
        System.out.print("Please enter your password");
        String pass = op.nextLine();

        Connection conn = db.getConnection();
        PreparedStatement statement;
        try {
            statement = conn.prepareStatement("SELECT usercode from U_Admin where username = ? and userpwd = ?");
            statement.setString(1, usr);
            statement.setString(2, pass);
            ResultSet rs = statement.executeQuery();

            switch (rs.getString("usercode")) {
            case "C":
                System.out.println("Go to Customer Landing page");
                break;
            case "B":
                System.out.println("Go to Brand Landing page");
                break;
            }
        } catch (NullPointerException ne) {
            System.out.println("User is not Registered");
        }

    }

    public static void main(String[] args) throws Exception {
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
        }

    }

    private static void showQueries() {
        // logic to see all queries
    }

    private static void signup() {

        DBConnector db = new DBConnector();
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
            customerEntry();
            break;
        case 2:
            brandEntry();
            break;
        case 3:
            // goto statement
            break;
        default:
            System.out.println("You chose an invalid option");
        }

    }

    private static void customerEntry() {
        System.out.println("Welcome to Customer Signup");
        // Ask for customer details and insert
    }

    private static void brandEntry() {
        System.out.println("Welcome to Brand Signup");
        // Ask for brand details and insert
    }
}
