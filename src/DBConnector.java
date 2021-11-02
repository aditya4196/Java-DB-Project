import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {

    public Connection createConnection() {

        Connection con = null;

        // Try block to check for exceptions
        try {

            Class.forName("oracle.jdbc.OracleDriver");

            String jdbcURL = "jdbc:oracle:thin:@ora.csc.ncsu.edu:1521:orcl01";
            String user = "cpsmith6";
            String pass = "abcd1234";

            // Reference to connection interface
            con = DriverManager.getConnection(jdbcURL, user, pass);
        }

        // Catch block to handle exceptions
        catch (Exception ex) {
            // Display message when exceptions occurs
            System.err.println(ex);
        }
        return con;
    }

    public void close(Connection con) throws SQLException {
        con.close();
    }
}
