import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
    static final String jdbcURL = "jdbc:oracle:thin:@ora.csc.ncsu.edu:1521:orcl01";

    public void Connection(String jdbcURL, String user, String pass) {

        // Try block to check for exceptions
        try {

            Class.forName("oracle.jdbc.OracleDriver");

            user = "cpsmith6";
            pass = "abcd1234";

            Connection con = null;

            // Reference to connection interface
            con = DriverManager.getConnection(jdbcURL, user, pass);
        }

        // Catch block to handle exceptions
        catch (Exception ex) {
            // Display message when exceptions occurs
            System.err.println(ex);
        }
    }

    public void close(Connection con) throws SQLException {
        con.close();
    }
}
