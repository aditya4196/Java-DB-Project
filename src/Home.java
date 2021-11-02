import java.util.Scanner;

public class Home {

    public static void main(String[] args) throws Exception {
        Scanner op = new Scanner(System.in);
        System.out.println("Welcome to the MarketPlace !!!");

        System.out.println("Please choose from the below options");
        System.out.println("1. SignUp");
        System.out.println("2. Login");
        System.out.println("3. ShowQueries");
        System.out.print("Your Option : ");

        int option = op.nextInt();
        op.close();

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

    }

    private static void showQueries() {
        // logic to see all queries
    }

    private static void signup() throws Exception {
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
        op.close();

        switch (userop) {
        case 1:
            customerSignup();
            break;
        case 2:
            brandSignup();
            break;
        case 3:
            main(null);
            // goto statement
            break;
        default:
            System.out.println("You chose an invalid option");
        }
    }

    private static void customerSignup() {
        System.out.println("Welcome to Customer Signup");
        // Ask for customer details and insert
    }

    private static void brandSignup() {
        System.out.println("Welcome to Brand Signup");
        // Ask for brand details and insert
    }

    public static void login() throws Exception {
        Scanner op = new Scanner(System.in);
        System.out.println("Welcome to Login Screen !!!");
        System.out.print("Please enter your username : ");
        String usr = op.nextLine();
        System.out.println();
        System.out.print("Please enter your password");

        String pass = op.nextLine();
        op.close();

        // Query to validate, if true, fetch the upcode
        char upcode = 'C';
        boolean flag = true;
        // login query logic here

        if (!flag)
            System.out.println("User is not logged in");
        else {
            switch (upcode) {
            case 'C':
                customerLanding();
                break;
            case 'B':
                brandLanding();
                break;
            case 'A':
                adminLanding();
                break;
            }
        }
    }

    private static void logout() {
    }

    private static void customerLanding() throws Exception {
        Scanner op = new Scanner(System.in);
        System.out.println("1. Enroll in Loyalty Program");
        System.out.println("2. Go Back");
        System.out.print("Your Option : ");

        int userop = op.nextInt();
        op.close();

        switch (userop) {
        case 1:
            Customer.enrollInLP();
            break;
        case 2:
            main(null);
            break;
        default:
            System.out.println("You have entered an invlaid option");
            customerLanding();
        }
    }

    private static void brandLanding() throws Exception {
        Scanner op = new Scanner(System.in);
        System.out.println("1. Add Loyalty Program");
        System.out.println("2. Add RE Rules");
        System.out.println("3. Update RE Rules");
        System.out.println("4. Add RR Rules");
        System.out.println("5. Update RR Rules");
        System.out.println("6. Validate Loyalty Program");
        System.out.println("7. Logout");
        System.out.print("Your Option : ");

        int userop = op.nextInt();
        op.close();

        switch (userop) {
        case 1:
            Brand.addLoyaltyProgram();
            break;
        case 2:
            Brand.addRERules();
            break;
        case 3:
            Brand.updateRERules();
            break;
        case 4:
            Brand.addRRRules();
            break;
        case 5:
            Brand.updateRRRules();
            break;
        case 6:
            Brand.validateLoyaltyProgram();
            break;
        case 7:
            logout();
            break;
        default:
            System.out.println("You have entered an invlaid option");
            brandLanding();
        }
    }

    private static void adminLanding() {
        Scanner op = new Scanner(System.in);
        System.out.println("1. Add Brand");
        System.out.println("2. Add Customer");
        System.out.println("3. Show Brand's Info");
        System.out.println("4. Show Customer's Info");
        System.out.println("5. Add Activity Type");
        System.out.println("6. Add Reward Type");
        System.out.println("7. Logout");
        System.out.print("Your Option : ");

        int userop = op.nextInt();
        op.close();

        switch (userop) {
        case 1:
            Admin.addBrand();
            break;
        case 2:
            Admin.addCustomer();
            break;
        case 3:
            Admin.showBrandInfo();
            break;
        case 4:
            Admin.showCustomerInfo();
            break;
        case 5:
            Admin.addActivityType();
            break;
        case 6:
            Admin.addRewardType();
            break;
        case 7:
            logout();
            break;
        default:
            System.out.println("You have entered an invlaid option");
            adminLanding();
        }
    }
}
