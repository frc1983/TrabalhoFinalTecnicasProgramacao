package Connection;

import Exception.ConnectionException;
import java.sql.Connection;

public interface IConnection {
	Connection getConnection() throws ConnectionException;
	void open() throws ConnectionException;
	void close() throws ConnectionException;
	void openTransaction() throws ConnectionException;
	void closeTransaction() throws ConnectionException;
	void commit() throws ConnectionException;
	void rollback() throws ConnectionException;
}