package exception;

public class UtenteNonTrovatoException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */

	public UtenteNonTrovatoException()
	{
		super("L'username inserito non risulta associato a nessun utente");
	}
}
