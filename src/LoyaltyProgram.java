import java.util.Scanner;

public class LoyaltyProgram {

	Brand bd = new Brand();
	
	public void tiersSetupLanding() throws Exception {
		Scanner op = new Scanner(System.in);
		System.out.println("1. Setup");
		System.out.println("2. Go Back");
		System.out.print("Your option: ");
    	
    	int userop = op.nextInt();
		
    	switch (userop) {
    	case 1:
            tiersSetup();
            break;
        case 2:
           // bd.tiered();
            break;
		default:
			System.out.println("You have entered an invlaid option");
            tiersSetupLanding();
    	}
    	
    	op.close();
	}

	public void tiersSetup() {
		
		
	}

	public void validateLP() {
		// Validates loyalty program
		
	}
}
