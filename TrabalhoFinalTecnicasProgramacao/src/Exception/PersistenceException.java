package Exception;

public class PersistenceException extends Exception {
	
	public PersistenceException(Throwable cause) {
		this("Erro ao salvar dados na base.", cause);
	}
	
	public PersistenceException(String message, Throwable cause) {
		super(message, cause);
	}
}