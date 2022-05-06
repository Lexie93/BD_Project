package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import entity.Clump;
import entity.ClumpCompleto;
import entity.ClumpPerBanda;
import entity.FlussoPerBanda;
import entity.OggettoCompleto;
import entity.Satellite;
import entity.SorgenteConRiferimentoCompleta;
import entity.Strumento;
import exception.ErroreDbException;

public class SalvaDatiDao implements SalvaDatiDaoInterface {

	private MyDataSource datasource;
	
	public SalvaDatiDao(MyDataSource dataSource)
	{
		this.datasource = dataSource;
	}
	
	@Override
	public void inserisciClump(Vector<Clump> clumps) throws ErroreDbException {
		// TODO Auto-generated method stub  TRUNCATE TABLE  table_name;
		String command="insert into clump (id,longitudine,latitudine,temperatura,temperaturabolometrica_massa,densitasuperficie"
				+ ",tipo) values (?,?,?,?,?,?,?) on conflict(id) do update "
				+ "set longitudine=?,latitudine=?,temperatura=?,temperaturabolometrica_massa=?,densitasuperficie=?,tipo=?";
		PreparedStatement statement;
		
		try {
				Connection connection=datasource.getConnection();
				for(Clump clump:clumps)	
				{	
					statement = connection.prepareStatement(command);	
					statement.setString(1, clump.getId());
					statement.setDouble(2, clump.getLongitudine());
					statement.setDouble(3, clump.getLatitudine());
					statement.setDouble(4, clump.getTemperatura());
					statement.setDouble(5, clump.getTempSuMassa());
					statement.setDouble(6, clump.getDensitaSuperficie());
					statement.setInt(7, clump.getTipo());
					
					statement.setDouble(8, clump.getLongitudine());
					statement.setDouble(9, clump.getLatitudine());
					statement.setDouble(10, clump.getTemperatura());
					statement.setDouble(11, clump.getTempSuMassa());
					statement.setDouble(12, clump.getDensitaSuperficie());
					statement.setInt(13, clump.getTipo());
				
					statement.executeUpdate();
					statement.close();
				}
				connection.close();
				
			} catch (SQLException e) 
		{
			e.printStackTrace();
			throw new ErroreDbException();
		}
		
		
	}
	
	
	/*public Vector<String> getClumpId() throws ErroreDbException
	{
		String command ="select id from clump order by id";
		PreparedStatement statement;
		Vector <String> riferimenti = new Vector<>(); 
		try
		{
			Connection connection=datasource.getConnection();
			statement = connection.prepareStatement(command);
			ResultSet result = statement.executeQuery();
			while(result.next())
			{
				String id=result.getString("id");
				riferimenti.add(id.trim());
			}
			return riferimenti;
		}
		catch(Exception e)
		{
			throw new ErroreDbException();
		}
	}*/
	/*public boolean clumpEsiste(Vector<String> idEsistenti,String id)
	{
		for(int i=0;i<idEsistenti.size();i++)
		{
			int comp=idEsistenti.get(i).compareToIgnoreCase(id);
			System.out.println("comp= "+comp+"idEs= "+idEsistenti.get(i));
			if(comp==1)
				return false;
			if(comp==0)
				return true;
		}
		return false;
	}*/
	@Override
	public void inserisciDatiClump(Vector<ClumpCompleto> clumpCompleti) throws ErroreDbException {
		String commandPerBanda="insert into clumpperbanda (clump,banda,semiassemaggioreellisse,semiasseminoreellisse,"
				+ "angolorotazione) values (?,?,?,?,?) on conflict(clump,banda) do update "
		+ "set semiassemaggioreellisse=?,semiasseminoreellisse=?,angolorotazione=?";
		
		String commandFlusso="insert into flusso (riferimentoid,banda,valore) values (?,?,?)"
				+ "on conflict(riferimentoid,banda) do update set valore=?";
		PreparedStatement statementCPB,statementF;
		
		try {
				Connection connection=datasource.getConnection();
				statementCPB = connection.prepareStatement(commandPerBanda);
				statementF = connection.prepareStatement(commandFlusso);
				for(ClumpCompleto clump:clumpCompleti)	
				{		
					Vector <ClumpPerBanda> clumpBande = clump.getClumpPerBanda();
					for(ClumpPerBanda clumpBanda: clumpBande)
					{
						statementCPB.setString(1, clumpBanda.getId());
						statementCPB.setDouble(2, clumpBanda.getBanda());
						statementCPB.setDouble(3, clumpBanda.getSemiasseMaggiore());
						statementCPB.setDouble(4, clumpBanda.getSemiasseMinore());
						statementCPB.setDouble(5, clumpBanda.getAngoloRotazione());
						
						statementCPB.setDouble(6, clumpBanda.getSemiasseMaggiore());
						statementCPB.setDouble(7, clumpBanda.getSemiasseMinore());
						statementCPB.setDouble(8, clumpBanda.getAngoloRotazione());
						
						statementCPB.executeUpdate();
					}
						
					Vector <FlussoPerBanda> flussoBande = clump.getFlussoPerBanda();
					
					for(FlussoPerBanda flussoBanda:flussoBande)
					{
						
						statementF.setString(1, flussoBanda.getId());
						statementF.setDouble(2, flussoBanda.getBanda());
						statementF.setDouble(3, flussoBanda.getValore());
						
						statementF.setDouble(4, flussoBanda.getValore());
						
						statementF.executeUpdate();
					}
				}
				statementCPB.close();
				statementF.close();
				connection.close();		
		} catch (SQLException e) 
		{
			e.printStackTrace();
			throw new ErroreDbException();
		}
		
	}

	@Override
	public void inserisciSorgentiGlimpseEDati(Vector<OggettoCompleto> oggettiCompleti) throws ErroreDbException {
		String commandGlimpse="insert into sorgenteglimpse (id,longitudine,latitudine) values (?,?,?)"
				+ " on conflict(id) do update set longitudine=?,latitudine=?";
		
		String commandFlusso="insert into flusso (riferimentoid,banda,valore) values (?,?,?)"
				+ "on conflict(riferimentoid,banda) do update set valore=?";
		PreparedStatement statementG,statementF;
		
		try {
				Connection connection=datasource.getConnection();
				statementG = connection.prepareStatement(commandGlimpse);
				statementF = connection.prepareStatement(commandFlusso);
				for(OggettoCompleto oggetto:oggettiCompleti)	
				{		
				
						statementG.setString(1, oggetto.getOggetto().getId());
						statementG.setDouble(2, oggetto.getOggetto().getLongitudine());
						statementG.setDouble(3, oggetto.getOggetto().getLatitudine());
						
						statementG.setDouble(4, oggetto.getOggetto().getLongitudine());
						statementG.setDouble(5, oggetto.getOggetto().getLatitudine());
						
						statementG.executeUpdate();
						
					Vector <FlussoPerBanda> flussoBande = oggetto.getFlussoPerBanda();
					
					for(FlussoPerBanda flussoBanda:flussoBande)
					{
						
						statementF.setString(1, flussoBanda.getId());
						statementF.setDouble(2, flussoBanda.getBanda());
						statementF.setDouble(3, flussoBanda.getValore());
						
						statementF.setDouble(4, flussoBanda.getValore());
						
						statementF.executeUpdate();
					}
				}
				statementG.close();
				statementF.close();
				connection.close();		
		} catch (SQLException e) 
		{
			e.printStackTrace();
			throw new ErroreDbException();
		}
		
	}

	@Override
	public void inserisciSorgentiMipsgalEDati(Vector <SorgenteConRiferimentoCompleta> sorgentiComplete) throws ErroreDbException {
		String commandMipsgal="insert into sorgentemipsgal (id,longitudine,latitudine,riferimentoglimpse) values (?,?,?,?)"
				+ " on conflict(id) do update set longitudine=?,latitudine=?,riferimentoglimpse=?";
		
		String commandFlusso="insert into flusso (riferimentoid,banda,valore) values (?,?,?)"
				+ "on conflict(riferimentoid,banda) do update set valore=?";
		PreparedStatement statementM,statementF;
		
		try {
				Connection connection=datasource.getConnection();
				statementM = connection.prepareStatement(commandMipsgal);
				statementF = connection.prepareStatement(commandFlusso);
				for(SorgenteConRiferimentoCompleta sCompleta:sorgentiComplete)	
				{		
				
						statementM.setString(1, sCompleta.getSorgenteConRiferimento().getId());
						statementM.setDouble(2, sCompleta.getSorgenteConRiferimento().getLongitudine());
						statementM.setDouble(3, sCompleta.getSorgenteConRiferimento().getLatitudine());
						statementM.setString(4, sCompleta.getSorgenteConRiferimento().getIdSorgenteRiferita());
						

						statementM.setDouble(5, sCompleta.getSorgenteConRiferimento().getLongitudine());
						statementM.setDouble(6, sCompleta.getSorgenteConRiferimento().getLatitudine());
						statementM.setString(7, sCompleta.getSorgenteConRiferimento().getIdSorgenteRiferita());
						
						statementM.executeUpdate();
						
					Vector <FlussoPerBanda> flussoBande = sCompleta.getFlussoPerBanda();
					
					for(FlussoPerBanda flussoBanda:flussoBande)
					{
						
						statementF.setString(1, flussoBanda.getId());
						statementF.setDouble(2, flussoBanda.getBanda());
						statementF.setDouble(3, flussoBanda.getValore());
						
						statementF.setDouble(4, flussoBanda.getValore());
						
						statementF.executeUpdate();
					}
				}
				statementM.close();
				statementF.close();
				connection.close();		
		} catch (SQLException e) 
		{
			e.printStackTrace();
			throw new ErroreDbException();
		}
		
	}

	@Override
	public void reset(String tabella) throws ErroreDbException {
		String command="TRUNCATE TABLE  "+tabella+" CASCADE";
		PreparedStatement statement;
		try {
				Connection connection=datasource.getConnection();
				
				statement = connection.prepareStatement(command);	
				
				statement.executeUpdate();
				statement.close();
				connection.close();
				
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ErroreDbException();	
		}
	}

	@Override
	public void inserisciSatellite(Satellite satellite) throws ErroreDbException 
	{
		String commandS="insert into satellite (nome,inizio,fine) values (?,?,?)";
		String commandA="insert into partecipazioneagenzia (agenzia,satellite) values (?,?)";
		PreparedStatement statement;
		try {
				Connection connection=datasource.getConnection();
				
				statement = connection.prepareStatement(commandS);	
				
				statement.setString(1, satellite.getNome());
				
				String dataI[]=satellite.getInizio().split("-");
				statement.setDate(2,Date.valueOf(dataI[2]+"-"+dataI[1]+"-"+dataI[0]));
				
				String dataF[]=satellite.getInizio().split("-");
				statement.setDate(3,Date.valueOf(dataF[2]+"-"+dataF[1]+"-"+dataF[0]));
				
				statement.executeUpdate();
				statement.close();
				
				Vector <String> agenzie = satellite.getAgenzie();
				
				for(String agenzia:agenzie)
				{
					statement = connection.prepareStatement(commandA);	
					
					statement.setString(1, agenzia);
					statement.setString(2, satellite.getNome());
					statement.executeUpdate();
				}
				
				connection.close();	
			}
		catch (SQLException e) 
		{
			e.printStackTrace();
			throw new ErroreDbException();	
		}	
	}

	@Override
	public void inserisciStrumento(Strumento strumento) throws ErroreDbException 
	{
		String commandS="insert into strumento (nome,satellite,mappa) values (?,?,?)";
		String commandB="insert into strumenti_banda (strumento,banda) values (?,?)";
		PreparedStatement statement;
		try {
				Connection connection=datasource.getConnection();
				
				statement = connection.prepareStatement(commandS);	
				
				statement.setString(1, strumento.getNome());
				statement.setString(2, strumento.getSatellite());
				statement.setString(3, strumento.getMappa());
			
				statement.executeUpdate();
				statement.close();
				
				Vector <Double> bande = strumento.getBanda();
				
				for(Double banda:bande)
				{
					statement = connection.prepareStatement(commandB);	
					
					statement.setString(1, strumento.getNome());
					statement.setDouble(2, banda);
					statement.executeUpdate();
				}
				connection.close();	
			}
		catch (SQLException e) 
		{
			e.printStackTrace();
			throw new ErroreDbException();	
		}
	}

	@Override
	public Vector<String> getNomiSatellite() throws ErroreDbException {
		String command="select nome from satellite";
		PreparedStatement statement;
		try {
				Connection connection=datasource.getConnection();
				
				statement = connection.prepareStatement(command);	
				
				ResultSet result=statement.executeQuery();
				
				Vector <String> nomi= new Vector<>();
				
				while(result.next())
				{
					nomi.add(result.getString("nome"));
				}
				statement.close();
				connection.close();	
				return nomi;
			}
		catch (SQLException e) 
		{
			e.printStackTrace();
			throw new ErroreDbException();	
		}
	}
}
