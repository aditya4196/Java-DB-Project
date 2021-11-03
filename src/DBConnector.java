import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {

    public Connection getConnection() throws Exception {

        Connection conn = null;
        try {

            Class.forName("oracle.jdbc.OracleDriver");
            String jdbcUrl = "jdbc:oracle:thin:@ora.csc.ncsu.edu:1521:orcl01";
            String user = "cpsmith6";
            String passwd = "abcd1234";

            conn = DriverManager.getConnection(jdbcUrl, user, passwd);
            System.out.println("Connected to Database");

        } catch (SQLException se) {
            se.printStackTrace();
        }

        return conn;
    }

    public void close(Connection conn) throws SQLException {
        conn.close();
    }
}
