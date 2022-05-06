package database;

import java.util.Vector;

import entity.ClumpConMassa;
import entity.FlussoPerBanda;
import entity.OggettoConFlusso;
import entity.OggettoGenerico;
import entity.SorgenteConRiferimento;

public interface RicercaDaoInterface {
	public Double cercaClumpPerDensita() throws Exception;
	public Double getLatitude(String id) throws Exception;
	public Double getLongitude(String id) throws Exception;
	public Vector<FlussoPerBanda> getFlussi(String id) throws Exception;
	public Vector<OggettoConFlusso> getOggettiMappa(String map) throws Exception;
	public Vector<OggettoConFlusso> getOggettiMappa(String map, double banda) throws Exception;
	public Vector<OggettoGenerico> oggettiInRegione(double lat, double lon, double dim, String forma, String tipoOggetto) throws Exception;
	public Vector<SorgenteConRiferimento> oggettiDentroClump(String id, double banda) throws Exception;
	public Vector<ClumpConMassa> getMasse() throws Exception;
	public double[] statistica() throws Exception;
	public Vector<SorgenteConRiferimento> getStelleGiovani() throws Exception;
}
