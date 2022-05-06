package control;

import java.io.IOException;
import java.util.Vector;

import database.SalvaDatiDao;
import entity.Satellite;
import entity.Strumento;
import exception.DatoNonValidoException;
import exception.ErroreDbException;
import filesystem.DataReader;

public class ControlloreSalvataggioDati {

	private SalvaDatiDao sDao;
	DataReader dataReader;
	
	public ControlloreSalvataggioDati(SalvaDatiDao sDao)
	{
		this.sDao= sDao;
		this.dataReader = new DataReader();
	}
	
	public void inserisciSatellite(String nome,String dataInizio,String dataFine,Vector <String> agenzie) throws DatoNonValidoException, ErroreDbException
	{
		ControlloreDatiImmessi.controlloStringaNonVuota(nome);
		ControlloreDatiImmessi.controlloStringaNonVuota(agenzie.get(0));
		//ControlloreDatiImmessi.controlloStringaNonVuota(agenzie.get(1));
		String dataInizioC[]=dataInizio.split("-");
		dataInizio=dataInizioC[2]+"-"+dataInizioC[1]+"-"+dataInizioC[0];
		
		String dataFineC[]=dataFine.split("-");
		dataFine=dataFineC[2]+"-"+dataFineC[1]+"-"+dataFineC[0];
		
		System.out.println(dataInizio);
		Satellite satellite = new Satellite(nome,dataInizio,dataFine);
		satellite.addAgenzia(agenzie.get(0));
		if(!agenzie.get(1).trim().isEmpty())
			satellite.addAgenzia(agenzie.get(1));
		sDao.inserisciSatellite(satellite);	
	}
	
	public void InserisciStrumento(String nome,String nomeSatellite,String nomeMappa,Vector<Double> bande) throws ErroreDbException
	{
		Strumento strumento = new Strumento(nome,nomeSatellite,nomeMappa);
		for(Double banda:bande)
		{
			strumento.addBanda(banda);
		}
		
		sDao.inserisciStrumento(strumento);
	}
	
	public void importaDati(String filename,String tipoFile) throws IOException, ErroreDbException
	{
		switch(tipoFile)
		{
		case "Informazioni base Clump": sDao.inserisciClump(dataReader.readClumps(filename));break;
		case "Informazioni aggiuntive sul clump": sDao.inserisciDatiClump(dataReader.readClumpInformations(filename));break;
		case "sorgenti nella mappa Mipsgal": sDao.inserisciSorgentiMipsgalEDati(dataReader.readSorgenteConRiferimentoInformation(filename));break;
		case "sorgenti nella mappa Glimpse": sDao.inserisciSorgentiGlimpseEDati(dataReader.readSorgenteSenzaRiferimentoInformation(filename));break;
		default:System.out.println("ddd"); ;break;
		}
	}
	
	public Vector<String> getNomiSatelliti() throws ErroreDbException
	{
		return sDao.getNomiSatellite();
	}
	
	public Vector<String> getFileNames()
	{
		return dataReader.getFilenames();
	}
}
