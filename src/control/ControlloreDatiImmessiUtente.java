package control;

import entity.Utente;
import exception.DatoNonValidoException;

public class ControlloreDatiImmessiUtente 
{
	static final int lunghezzaMassimaUsername = 20;
	static final int lunghezzaMassimaPassword = 20;
	static final int lunghezzaMassimaNome = 20;
	static final int lunghezzaMassimaCognome = 20;
	static final int lunghezzaMassimaEmail = 35;
	static final int lunghezzaMassimaNumeroTelefono = 15;
	static final int lunghezzaMassimaIndirizzo = 50;
	
	
	public static void controlloCampiUtente(Utente u) throws DatoNonValidoException
	{
		ControlloreDatiImmessi.controlloLunghezzaMassima(u.getUsername(),lunghezzaMassimaUsername, "username");
		ControlloreDatiImmessi.controlloLunghezzaMinima(u.getUsername(),6,"username");
		
		ControlloreDatiImmessi.controlloLunghezzaMassima(u.getPassword(),lunghezzaMassimaPassword, "password");
		ControlloreDatiImmessi.controlloLunghezzaMinima(u.getPassword(),6,"password");
		
		ControlloreDatiImmessi.controlloLunghezzaMassima(u.getNome(),lunghezzaMassimaNome, "nome");
		ControlloreDatiImmessi.controlloStringaNonVuota(u.getNome(),"nome");
		
		ControlloreDatiImmessi.controlloLunghezzaMassima(u.getCognome(),lunghezzaMassimaCognome, "cognome");
		ControlloreDatiImmessi.controlloStringaNonVuota(u.getCognome(),"cognome");
		
		ControlloreDatiImmessi.controlloLunghezzaMassima(u.getEmail(),lunghezzaMassimaEmail, "e-mail");
		ControlloreDatiImmessi.controlloEMail(u.getEmail(),"e-mail");
	}
	
	public static void controlloPassword(String password) throws DatoNonValidoException
	{
		ControlloreDatiImmessi.controlloLunghezzaMassima(password,lunghezzaMassimaPassword, "password");
		ControlloreDatiImmessi.controlloLunghezzaMinima(password,8,"password");
	}
	
}
