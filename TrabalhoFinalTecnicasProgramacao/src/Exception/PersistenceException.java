package Exception;

public class PersistenceException extends Exception {
	
	public PersistenceException(Throwable cause) {
		this("Erro ao abrir conexão com a base de dados.", cause);
	}
	
	public PersistenceException(String message, Throwable cause) {
		super(message, cause);
	}
}