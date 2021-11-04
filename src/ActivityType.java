import java.util.Scanner;

public class ActivityType {

	Brand bd = new Brand();
	
	public void activityTypes(int lpType) throws Exception {
		Scanner op = new Scanner(System.in);
    	System.out.println("1. Purchase");
    	System.out.println("2. Leave a review");
    	System.out.println("3. Refer a friend");
    	System.out.println("4. Go Back");
    	System.out.print("Your option: ");
    	
    	int userop = op.nextInt();
		
    	switch (userop) {
        case 1:
            purchase();
            break;
        case 2:
            leaveAReview();
            break;
        case 3:
            referAFriend();
            break;
        case 4:
        	if (lpType == 0) {
        		bd.regular();
        	}
        	else {
        		bd.tiered();
        	}
        	break;
        default:
            System.out.println("You have entered an invlaid option");
            activityTypes(lpType);
    	}
    	
    	op.close();
		
	}
	
	public void purchase() {
		// Create/update purchase
		
	}
	
	public void leaveAReview() {
		// Create/update leave a review
		
	}

	public void referAFriend() {
		// Create/update refer a friend
		
	}
}
