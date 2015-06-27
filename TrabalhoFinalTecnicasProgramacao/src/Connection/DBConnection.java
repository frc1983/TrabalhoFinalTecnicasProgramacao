package Connection;

import Exception.ConnectionException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection implements IConnection {

    private Connection connection;
    private static final String USER = "usuario";
    private static final String PASS = "senha";
    
    public Connection getInstance() throws ConnectionException{
        return connection;
    }

    private Connection createConnection() throws ConnectionException {        
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
            connection = DriverManager.getConnection("jdbc:derby://localhost:1527/TrabalhoFinal", USER, PASS);

        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            throw new ConnectionException(ex);
        }

        return connection;
    }

    private boolean hasTransaction() throws ConnectionException {
        try {
            return !connection.getAutoCommit();
        } catch (SQLException e) {
            throw new ConnectionException(e);
        }
    }

    @Override
    public Connection getConnection() throws ConnectionException {
        this.open();
        return connection;
    }

    @Override
    public void open() throws ConnectionException {
        if (connection == null) {
            connection = createConnection();
        }
    }

    @Override
    public void close() throws ConnectionException {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
            } catch (SQLException e) {
                throw new ConnectionException(e);
            }
        }
    }

    @Override
    public void openTransaction() throws ConnectionException {
        try {
            if (!hasTransaction()) {
                connection.setAutoCommit(false);
            }
        } catch (SQLException e) {
            throw new ConnectionException(e);
        }
    }

    @Override
    public void closeTransaction() throws ConnectionException {
        try {
            if (!hasTransaction()) {
                connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            throw new ConnectionException(e);
        }
    }

    @Override
    public void commit() throws ConnectionException {
        try {
            if (hasTransaction()) {
                connection.commit();
            }
        } catch (SQLException e) {
            throw new ConnectionException(e);
        }
    }

    @Override
    public void rollback() throws ConnectionException {
        try {
            if (hasTransaction()) {
                connection.rollback();
            }
        } catch (SQLException e) {
            throw new ConnectionException(e);
        }
    }

    public boolean isOpen() throws ConnectionException {
        try {
            return !connection.isClosed();
        } catch (SQLException e) {
            throw new ConnectionException(e);
        }
    }
}
