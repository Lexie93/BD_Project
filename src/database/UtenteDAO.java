package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//import javax.naming.InitialContext;



//import org.apache.tomcat.dbcp.dbcp2.BasicDataSource ;

import entity.Utente;
import exception.ErroreDbException;
import exception.PasswordException;
import exception.UtenteNonTrovatoException;

public class UtenteDAO implements UtenteDaoInterface{

	private MyDataSource datasource;
	
	public UtenteDAO(MyDataSource datasource)
	{
		this.datasource = datasource;
	}
	
	@Override
	public Utente getUtente(String username) throws UtenteNonTrovatoException, ErroreDbException 
	{
		String command="select * from utente where \"user-id\" = ?";
		PreparedStatement statement;
		try {
				Connection connection=datasource.getConnection();
				
				
				statement = connection.prepareStatement(command);	
				statement.setString(1, username);
		
				ResultSet rs=statement.executeQuery();
				
				if(!rs.next())
				{
					System.out.println("user non trovato");
					throw new UtenteNonTrovatoException();
				}
					
				String password = rs.getString("password");
				String nome = rs.getString("nome");
				String cognome = rs.getString("cognome");
				String email = rs.getString("email");
				boolean amministratore= rs.getBoolean("amministratore");
				Utente u= new Utente(username.trim(),password.trim(),nome.trim(),cognome.trim(),email.trim()
						,amministratore);
				
				statement.close();
				connection.close();
				return u;
		} catch (SQLException e) {
			System.out.println("errore in query");
			e.printStackTrace();
			throw new ErroreDbException();
		}
	}
	
	@Override
	public boolean validateUtente(String username, String password) throws UtenteNonTrovatoException, ErroreDbException, PasswordException 
	{
		String command="select password from utente where \"user-id\" = ?";
		PreparedStatement statement;
		String passwordFromDb;
				Connection connection;
				try {
					connection = datasource.getConnection();
				
				statement = connection.prepareStatement(command);	
				statement.setString(1, username);
		
				ResultSet rs=statement.executeQuery();
				
				if(!rs.next())
				{
					System.out.println("user non trovato");
					throw new UtenteNonTrovatoException();
				}	
				
				passwordFromDb= rs.getString("password");
								
				statement.close();
				connection.close();
				
				} catch (SQLException e) {
					throw new ErroreDbException();
				}
				if(password.equals(passwordFromDb.trim()))
					return true;
				else
				throw new PasswordException();	
	}

	@Override
	public void createUtente(Utente utente) throws ErroreDbException {
		String command="insert into utente (\"user-id\",password,nome,cognome,email,amministratore) values (?,?,?,?,?,?)";
		PreparedStatement statement;
		try {
				Connection connection=datasource.getConnection();
			
				statement = connection.prepareStatement(command);	
				statement.setString(1, utente.getUsername());
				statement.setString(2, utente.getPassword());
				statement.setString(3, utente.getNome());
				statement.setString(4, utente.getCognome());
				statement.setString(5, utente.getEmail());
				statement.setBoolean(6, utente.isAmministratore());
				
				statement.executeUpdate();
				
				statement.close();
				connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ErroreDbException();
			
		}
	}

	@Override
	public void updateUtente(String username, Utente utente) throws ErroreDbException {
		
		String command="update utente set \"user-id\" = ?, password = ?,nome = ?,cognome = ?,email = ?, amministratore=? where \"user-id\" = ?";
		PreparedStatement statement;
		try {
			
				Connection connection=datasource.getConnection();
				statement = connection.prepareStatement(command);	
				statement.setString(1, utente.getUsername());
				statement.setString(2, utente.getPassword());
				statement.setString(3, utente.getNome());
				statement.setString(4, utente.getCognome());
				statement.setString(5, utente.getEmail());
				statement.setBoolean(6, utente.isAmministratore());
				
				statement.setString(7, username);
				
				statement.executeUpdate();
				
				statement.close();
				connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ErroreDbException();
		}
	}

	@Override
	public void deleteUtente(String username) throws ErroreDbException 
	{
		String command="delete from utente where \"user-id\" = ?";
		PreparedStatement statement;
		try {
				Connection connection=datasource.getConnection();
				
				statement = connection.prepareStatement(command);	
				statement.setString(1, username);
		
				statement.executeUpdate();
				
				statement.close();
				connection.close();

		} catch (SQLException e) 
		{
			System.out.println("errore in query");
			e.printStackTrace();
			throw new ErroreDbException();
		}
		
	}
	@Override
	public void updatePassword(String username, String password) throws ErroreDbException {
		String command="update utente set password = ? where \"user-id\" = ?";
		PreparedStatement statement;
		try {
			
				Connection connection=datasource.getConnection();
				statement = connection.prepareStatement(command);	
				statement.setString(1, password);
				statement.setString(2, username);
				
				statement.executeUpdate();
				
				statement.close();
				connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ErroreDbException();
		}
	}
		
}
