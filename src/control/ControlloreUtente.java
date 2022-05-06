package control;


import database.UtenteDAO;
import entity.Utente;

public class ControlloreUtente
{
	private UtenteDAO utenteDao;
	private ControlloreSessione cSessione;
	public ControlloreUtente(UtenteDAO utenteDao,ControlloreSessione cSessione)
	{
		this.utenteDao= utenteDao;
		this.cSessione= cSessione;
	}
	
	public void validateLogin(String username, String password) throws Exception
	{
		//ControlloreDatiImmessi.controlloStringaNonVuota(username);
		//ControlloreDatiImmessi.controlloStringaNonVuota(password);
		 if(utenteDao.validateUtente(username, password))
		 {
			 cSessione.setUtente(utenteDao.getUtente(username));
		 }
		
	}
	
	public void registrazione(Utente utente) throws Exception
	{
		ControlloreDatiImmessiUtente.controlloCampiUtente(utente);
		utenteDao.createUtente(utente);
	}
	
	public void modificaProfilo(Utente utente) throws Exception
	{
		utente.setPassword(cSessione.getUtente().getPassword());
		utente.setAmministratore(cSessione.getUtente().isAmministratore());
		ControlloreDatiImmessiUtente.controlloCampiUtente(utente);
		utenteDao.updateUtente(cSessione.getUtente().getUsername(), utente);
		cSessione.setUtente(utente);
	}
	
	public void modificaPassword(String password) throws Exception
	{
		ControlloreDatiImmessiUtente.controlloPassword(password);
		password=password.trim();
		utenteDao.updatePassword(cSessione.getUtente().getUsername(), password);
		cSessione.getUtente().setPassword(password);
	}
	
	public Utente getProfilo() throws Exception
	{
		return cSessione.getUtente();
	}
	
	public void eliminaProfilo() throws Exception
	{
		utenteDao.deleteUtente(cSessione.getUtente().getUsername());
		cSessione.logout();
	}
}
