package exception;

public class PasswordException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public PasswordException()
	{
		super("La password inserita non è valida");
	}

}
