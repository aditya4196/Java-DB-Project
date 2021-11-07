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
        System.out.println("1. List all customers that are not part of Brand02’s program.");
        System.out.println("2. List customers that have joined a loyalty program but have not participated in any activity in that program (list the customerid and the loyalty program id)");
        System.out.println("3. List the rewards that are part of Brand01 loyalty program");
        System.out.println("4. List all the loyalty programs that include “refer a friend” as an activity in at least one of their reward rules");
        System.out.println("5. For Brand01, list for each activity type in their loyalty program, the number instances that have occurred");
        System.out.println("6. List customers of Brand01 that have redeemed at least twice");
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
            statement = conn.prepareStatement("SELECT CID, CNAME, PHONENO, CADDR, USERID from Customer where CID NOT IN "
            		+ "(Select CID from WALLET where LPID = (Select LPID from LOYALTYPROGRAM where BID = '3'))");
            
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
            Admin.adminLanding();
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
            statement = conn.prepareStatement("SELECT CID, CNAME, PHONENO, CADDR, USERID from Customer where CID NOT IN "
            		+ "(Select CID from WALLET where LPID = (Select LPID from LOYALTYPROGRAM where BID = '3'))");
            
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
            Admin.adminLanding();
        } 
        
        catch (Exception e) {
            System.out.println("Customer ID not found");
            Admin.adminLanding();
        }

    }
    public  static  void query3() throws Exception {

        DBConnector db = new DBConnector();
        Connection conn = db.getConnection();
        PreparedStatement statement;
        
        try 
        {
            statement = conn.prepareStatement("SELECT CID, CNAME, PHONENO, CADDR, USERID from Customer where CID NOT IN "
            		+ "(Select CID from WALLET where LPID = (Select LPID from LOYALTYPROGRAM where BID = '3'))");
            
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
            Admin.adminLanding();
        } 
        
        catch (Exception e) {
            System.out.println("Customer ID not found");
            Admin.adminLanding();
        }

    }
    public  static  void query4() throws Exception {

        DBConnector db = new DBConnector();
        Connection conn = db.getConnection();
        PreparedStatement statement;
        
        try 
        {
            statement = conn.prepareStatement("SELECT CID, CNAME, PHONENO, CADDR, USERID from Customer where CID NOT IN "
            		+ "(Select CID from WALLET where LPID = (Select LPID from LOYALTYPROGRAM where BID = '3'))");
            
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
            Admin.adminLanding();
        } 
        
        catch (Exception e) {
            System.out.println("Customer ID not found");
            Admin.adminLanding();
        }

    }
    public  static  void query5() throws Exception {

        DBConnector db = new DBConnector();
        Connection conn = db.getConnection();
        PreparedStatement statement;
        
        try 
        {
            statement = conn.prepareStatement("SELECT CID, CNAME, PHONENO, CADDR, USERID from Customer where CID NOT IN "
            		+ "(Select CID from WALLET where LPID = (Select LPID from LOYALTYPROGRAM where BID = '3'))");
            
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
            Admin.adminLanding();
        } 
        
        catch (Exception e) {
            System.out.println("Customer ID not found");
            Admin.adminLanding();
        }

    }
    public  static  void query6() throws Exception {

        DBConnector db = new DBConnector();
        Connection conn = db.getConnection();
        PreparedStatement statement;
        
        try 
        {
            statement = conn.prepareStatement("SELECT CID, CNAME, PHONENO, CADDR, USERID from Customer where CID NOT IN "
            		+ "(Select CID from WALLET where LPID = (Select LPID from LOYALTYPROGRAM where BID = '3'))");
            
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
            Admin.adminLanding();
        } 
        
        catch (Exception e) {
            System.out.println("Customer ID not found");
            Admin.adminLanding();
        }

    }
    public  static  void query7() throws Exception {

        DBConnector db = new DBConnector();
        Connection conn = db.getConnection();
        PreparedStatement statement;
        
        try 
        {
            statement = conn.prepareStatement("SELECT CID, CNAME, PHONENO, CADDR, USERID from Customer where CID NOT IN "
            		+ "(Select CID from WALLET where LPID = (Select LPID from LOYALTYPROGRAM where BID = '3'))");
            
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
            Admin.adminLanding();
        } 
        
        catch (Exception e) {
            System.out.println("Customer ID not found");
            Admin.adminLanding();
        }

    }
    public  static  void query8() throws Exception {

        DBConnector db = new DBConnector();
        Connection conn = db.getConnection();
        PreparedStatement statement;
        
        try 
        {
            statement = conn.prepareStatement("SELECT CID, CNAME, PHONENO, CADDR, USERID from Customer where CID NOT IN "
            		+ "(Select CID from WALLET where LPID = (Select LPID from LOYALTYPROGRAM where BID = '3'))");
            
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
            Admin.adminLanding();
        } 
        
        catch (Exception e) {
            System.out.println("Customer ID not found");
            Admin.adminLanding();
        }

    }
}
