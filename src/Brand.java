import java.util.Scanner;

public class Brand {
	
	Home home = new Home();
	
    public void addLoyaltyProgram() throws Exception {
    	Scanner op = new Scanner(System.in);
    	System.out.println("1. Regular");
    	System.out.println("2. Tiered");
    	System.out.println("3. Go Back");
    	System.out.print("Your option: ");
    	
    	int userop = op.nextInt();
    	
    	switch (userop) {
        case 1:
            regular();
            break;
        case 2:
            tiered();
            break;
        case 3:
            home.brandLanding();
            break;
        default:
            System.out.println("You have entered an invlaid option");
            addLoyaltyProgram();
    	}
    	
    	op.close();
    }
    
    public void regular() throws Exception {
    	Scanner op = new Scanner(System.in);
    	ActivityType at = new ActivityType();
    	RewardType rt = new RewardType();
    	System.out.println("1. Activity Types");
    	System.out.println("2. Reward Types");
    	System.out.println("3. Go Back");
    	System.out.print("Your option: ");
    	
    	int userop = op.nextInt();
		
    	switch (userop) {
        case 1:
            at.activityTypes(0);
            break;
        case 2:
            rt.rewardTypes(0);
            break;
        case 3:
            addLoyaltyProgram();
            break;
        default:
            System.out.println("You have entered an invlaid option");
            regular();
    	}
    	
    	op.close();
	}

	public void tiered() throws Exception {
		Scanner op = new Scanner(System.in);
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
            at.activityTypes(1);
            break;
        case 3:
            rt.rewardTypes(1);
            break;
        case 4:
            addLoyaltyProgram();
            break;
        default:
            System.out.println("You have entered an invlaid option");
            tiered();
    	}
    	
    	op.close();
		
	}

	public void addRERules() throws Exception {
		Scanner op = new Scanner(System.in);
		RERules re = new RERules();
    	System.out.println("1. Add RE Rules");
    	System.out.println("2. Go Back");
    	System.out.print("Your option: ");
    	
    	int userop = op.nextInt();
    	
    	switch (userop) {
        case 1:
            re.addRERules();
            break;
        case 2:
            home.brandLanding();
            break;
        default:
            System.out.println("You have entered an invlaid option");
            addRERules();
    	}
    	
    	op.close();
    }

    public void updateRERules() throws Exception {
    	Scanner op = new Scanner(System.in);
		RERules re = new RERules();
    	System.out.println("1. Update RE Rules");
    	System.out.println("2. Go Back");
    	System.out.print("Your option: ");
    	
    	int userop = op.nextInt();
    	
    	switch (userop) {
        case 1:
            re.updateRERules();
            break;
        case 2:
            home.brandLanding();
            break;
        default:
            System.out.println("You have entered an invlaid option");
            updateRERules();
    	}
    	
    	op.close();
    }

    public void addRRRules() throws Exception {
    	Scanner op = new Scanner(System.in);
		RRRules rr = new RRRules();
    	System.out.println("1. Add RR Rules");
    	System.out.println("2. Go Back");
    	System.out.print("Your option: ");
    	
    	int userop = op.nextInt();
    	
    	switch (userop) {
        case 1:
            rr.addRRRules();
            break;
        case 2:
            home.brandLanding();
            break;
        default:
            System.out.println("You have entered an invlaid option");
            addRRRules();
    	}
    	
    	op.close();
    }

    public void updateRRRules() throws Exception {
    	Scanner op = new Scanner(System.in);
		RRRules rr = new RRRules();
    	System.out.println("1. Update RR Rules");
    	System.out.println("2. Go Back");
    	System.out.print("Your option: ");
    	
    	int userop = op.nextInt();
    	
    	switch (userop) {
        case 1:
            rr.updateRRRules();
            break;
        case 2:
            home.brandLanding();
            break;
        default:
            System.out.println("You have entered an invlaid option");
            updateRRRules();
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
            home.brandLanding();
            break;
        default:
            System.out.println("You have entered an invlaid option");
            validateLoyaltyProgram();
    	}
    	
    	op.close();
    }
}
