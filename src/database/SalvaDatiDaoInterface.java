package database;

import java.util.Vector;

import entity.Clump;
import entity.ClumpCompleto;
import entity.OggettoCompleto;
import entity.Satellite;
import entity.SorgenteConRiferimentoCompleta;
import entity.Strumento;
import exception.ErroreDbException;

public interface SalvaDatiDaoInterface {

	public void inserisciClump(Vector <Clump> clumps) throws ErroreDbException;
	public void inserisciDatiClump(Vector <ClumpCompleto> clumpCompleti) throws ErroreDbException;
	public void inserisciSorgentiGlimpseEDati(Vector <OggettoCompleto> oggettiCompleti) throws ErroreDbException;
	public void inserisciSorgentiMipsgalEDati(Vector <SorgenteConRiferimentoCompleta> sorgentiComplete) throws ErroreDbException;
	
	public void inserisciSatellite(Satellite satellite) throws ErroreDbException;
	public void inserisciStrumento(Strumento strumento) throws ErroreDbException;
	
	public Vector<String> getNomiSatellite() throws ErroreDbException;
	
	public void reset(String tabella) throws ErroreDbException;
}
