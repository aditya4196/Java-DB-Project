import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Queries {
	public static void showQueries() throws Exception {
		Scanner op = new Scanner(System.in);
        System.out.println("Welcome to Queries Screen !!!");

        System.out.println("Please select which query you want to fire :-");
        System.out.println("1. List all customers that are not part of Brand 32 program.");
        System.out.println("2. List customers that have joined a loyalty program but have not participated in any activity in that program (list the customerid and the loyalty program id)");
        System.out.println("3. List the rewards that are part of Brand 31 loyalty program");
        System.out.println("4. List all the loyalty programs that include “refer a friend” as an activity in at least one of their reward rules");
        System.out.println("5. For Brand01, list for each activity type in their loyalty program, the number instances that have occurred");
        System.out.println("6. List customers of Brand 31 that have redeemed at least twice");
        System.out.println("7. All brands where total number of points redeemed overall is less than 500 points");
        System.out.println("8. For Customer C0003, and Brand02, number of activities they have done in the period of 08/1/2021 and 9/30/2021.");
        System.out.println("9. Logout");

        System.out.print("Your Option : ");

        int userop = op.nextInt();

        switch (userop) {
        case 1:
            query1();
            break;
        case 2:
        	query2();
            break;
        case 3:
        	query3();
            break;
        case 4:
        	query4();
            break;
        case 5:
        	query5();
            break;
        case 6:
        	query6();
            break;
        case 7:
        	query7();
            break;
        case 8:
        	query8();
            break;
        case 9:
            System.out.println("Logging out...");
            Home.main(null);
            break; 
        default:
            System.out.println("You chose an invalid option");
        }

        op.close();

	}
	
    public  static  void query1() throws Exception {

        DBConnector db = new DBConnector();
        Connection conn = db.getConnection();
        PreparedStatement statement;
        
        try 
        {
            statement = conn.prepareStatement("SELECT CID, CNAME, PHONENO, CADDR, USERID from Customer where CID NOT IN"
            		+ "(Select CID from WALLET where LPID = (Select LPID from LOYALTYPROGRAM where BID = '32'))");
            
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
            db.close(conn);
            Queries.showQueries();
        } 
        
        catch (Exception e) {
            System.out.println("Customer ID not found");
            Admin.adminLanding();
        }

    }
    
    public  static  void query2() throws Exception {

        DBConnector db = new DBConnector();
        Connection conn = db.getConnection();
        PreparedStatement statement;
        
        try 
        {
            statement = conn.prepareStatement("SELECT DISTINCT CID, LPID from Wallet W where NOT EXISTS "
            		+ "(Select CID, LPID from ACTIVITYINSTANCE AI where W.CID=AI.CID and W.LPID = AI.LPID)");
            
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
            db.close(conn);
            Queries.showQueries();
        } 
        
        catch (Exception e) {
            System.out.println("Customer ID not found");
            Queries.showQueries();
        }

    }
    public  static  void query3() throws Exception {

        DBConnector db = new DBConnector();
        Connection conn = db.getConnection();
        PreparedStatement statement;
        
        try 
        {
            statement = conn.prepareStatement("SELECT REWID, BID, LPID, REWNAME from RRRULES where BID = '31'");
            
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
            db.close(conn);
            Queries.showQueries();
        } 
        
        catch (Exception e) {
            System.out.println("Customer ID not found");
            Queries.showQueries();
        }

    }
    public  static  void query4() throws Exception {

        DBConnector db = new DBConnector();
        Connection conn = db.getConnection();
        PreparedStatement statement;
        
        try 
        {
            statement = conn.prepareStatement("SELECT LPID from RERULES where ACTID = "
            		+ "(Select ACTID from ACTIVITYTYPE where ACTNAME = 'Refer Friend')");
            
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
            db.close(conn);
            Queries.showQueries();
        } 
        
        catch (Exception e) {
            System.out.println("Customer ID not found");
            Queries.showQueries();
        }

    }
    public  static  void query5() throws Exception {

        DBConnector db = new DBConnector();
        Connection conn = db.getConnection();
        PreparedStatement statement;
        
        try 
        {
            statement = conn.prepareStatement("SELECT ACTID, COUNT(*) as count from ActivityInstance where"
            		+ "LPID in (Select distinct LPID from WALLET where BID = '31')"
            		+ "and ACTID in (Select ACTID from RERULES where BID = '31')n"
            		+ "group by ACTID");
            
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
            db.close(conn);
            Queries.showQueries();
        } 
        
        catch (Exception e) {
            System.out.println("Customer ID not found");
            Queries.showQueries();
        }

    }
    public  static  void query6() throws Exception {

        DBConnector db = new DBConnector();
        Connection conn = db.getConnection();
        PreparedStatement statement;
        
        try 
        {
            statement = conn.prepareStatement("SELECT CID from REWARDINSTANCE where BID = '31' "
            		+ "Group by CID having count(*)>1");
            
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
            db.close(conn);
            Queries.showQueries();
        } 
        
        catch (Exception e) {
            System.out.println("Customer ID not found");
            Queries.showQueries();
        }

    }
    public  static  void query7() throws Exception {

        DBConnector db = new DBConnector();
        Connection conn = db.getConnection();
        PreparedStatement statement;
        
        try 
        {
            statement = conn.prepareStatement("SELECT BID from BRAND where BID NOT IN"
            		+"(SELECT BID from REWARDINSTANCE "
            		+ "GROUP by BID having Sum(POINTS_REDEEM)>=500)");
            
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
            db.close(conn);
            Queries.showQueries();
        } 
        
        catch (Exception e) {
            System.out.println("Customer ID not found");
            Queries.showQueries();
        }

    }
    public  static  void query8() throws Exception {

        DBConnector db = new DBConnector();
        Connection conn = db.getConnection();
        PreparedStatement statement;
        
        try 
        {
            statement = conn.prepareStatement("SELECT count(*) as count from ACTIVITYINSTANCE where CID ='1' and BID = '32' and"
            		+ "EARN_DATE>'01-AUG-21' and EARN_DATE<'30-SEP-21'");
            
            
            System.out.println("Hello");
            ResultSet rs = statement.executeQuery();
            System.out.println("Hello");
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
            db.close(conn);
            Queries.showQueries();
        }
        
        catch (Exception e) {
            System.out.println("Customer ID not found");
            Queries.showQueries();
        }

    }
}
