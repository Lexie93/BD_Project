package database;


import entity.Utente;
import exception.ErroreDbException;
import exception.UtenteNonTrovatoException;

public interface UtenteDaoInterface
{
	Utente getUtente(String username) throws UtenteNonTrovatoException, ErroreDbException ;
	boolean validateUtente(String username,String password) throws Exception ;
    void createUtente(Utente utente) throws ErroreDbException ;
    void updateUtente(String username, Utente utente) throws ErroreDbException;
    void updatePassword(String username, String password) throws ErroreDbException;
    void deleteUtente(String username) throws ErroreDbException;
}
