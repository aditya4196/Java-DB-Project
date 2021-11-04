import java.util.Scanner;

public class RewardType {
	
	Brand bd = new Brand();

	public void rewardTypes(int lpType) throws Exception {
		Scanner op = new Scanner(System.in);
    	System.out.println("1. Gift Card");
    	System.out.println("2. Free Product");
    	System.out.println("3. Go Back");
    	System.out.print("Your option: ");
    	
    	int userop = op.nextInt();
		
    	switch (userop) {
        case 1:
            giftCard();
            break;
        case 2:
            freeProduct();
            break;
        case 3:
            if (lpType == 0) {
            	bd.regular();
            }
            else {
            	bd.tiered();
            }
            break;
        default:
            System.out.println("You have entered an invlaid option");
            rewardTypes(lpType);
    	}
    	
    	op.close();
		
	}

	public void giftCard() {
		// Create/update gift card
		
	}

	public void freeProduct() {
		// Create/update free product
		
	}

}
