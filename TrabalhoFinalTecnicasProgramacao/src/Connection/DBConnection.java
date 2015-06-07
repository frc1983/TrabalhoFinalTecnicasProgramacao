package Connection;

import Exception.ConnectionException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static Connection connection = null;
    private static final String USER = "usuario";
    private static final String PASS = "senha";

    private DBConnection() {
    }

    public static synchronized Connection getConnection() throws ConnectionException {
        try {
            if (connection == null) {
                Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
                connection = DriverManager.getConnection("jdbc:derby://localhost:1527/TrabalhoFinal", USER, PASS);
            }
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            throw new ConnectionException(ex);
        }

        return connection;
    }
}
