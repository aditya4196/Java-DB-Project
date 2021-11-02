import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DBConnector {

    public void Connection(String url, String user, String pass) {
        Connection con = null;

        // Try block to check for exceptions
        try {

            // Registering drivers
            DriverManager.registerDriver(new oracle.jdbc.OracleDriver());

            // Reference to connection interface
            con = DriverManager.getConnection(url, user, pass);

            // Creating a statement
            Statement st = con.createStatement();

            
            // Executing querry
            int m = st.executeUpdate(sql);
            if (m == 1)
                System.out.println("inserted successfully : " + sql);
            else
                System.out.println("insertion failed");

            // Closing the connections
            con.close();
        }

        // Catch block to handle exceptions
        catch (Exception ex) {
            // Display message when exceptions occurs
            System.err.println(ex);
        }
    }
}
