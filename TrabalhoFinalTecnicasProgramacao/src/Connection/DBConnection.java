package Connection;

import Exception.ConnectionException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {

    private static Connection connection = null;
    private static final String USER = "usuario";
    private static final String PASS = "senha";

    public static Connection getInstance() throws ConnectionException {
        if (connection == null) {
            connection = createConnection();
        }

        return connection;
    }

    private static Connection createConnection() throws ConnectionException {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
            connection = DriverManager.getConnection("jdbc:derby://localhost:1527/TrabalhoFinal", USER, PASS);

        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            throw new ConnectionException(ex);
        }

        return connection;
    }

    public static void close(Connection conn, Statement sta, ResultSet res) throws ConnectionException {
        try {
        if (res != null && !res.isClosed()) {
            res.close();
        }
        if (sta != null && !sta.isClosed()) {
            sta.close();
        }
        if (conn != null && conn.isClosed()) {
            conn.close();
        }
        } catch (SQLException e){
            throw new ConnectionException(e);
        }
    }

    private static boolean hasTransaction() throws ConnectionException {
        try {
            return !connection.getAutoCommit();
        } catch (SQLException e) {
            throw new ConnectionException(e);
        }
    }

    public static void openTransaction() throws ConnectionException {
        try {
            if (!hasTransaction()) {
                connection.setAutoCommit(false);
            }
        } catch (SQLException e) {
            throw new ConnectionException(e);
        }
    }

    public static void closeTransaction() throws ConnectionException {
        try {
            if (!hasTransaction()) {
                connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            throw new ConnectionException(e);
        }
    }
}
