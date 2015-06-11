package Connection;

import Exception.ConnectionException;

public class ConnectionFactory {

    public static IConnection getInstance() throws ConnectionException {
        IConnection conn = new DBConnection();
        
        return conn;
    }
}
