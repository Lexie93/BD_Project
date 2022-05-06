package entity;

import java.util.Vector;

public class OggettoCompleto 
{
	private OggettoGenerico oggetto;
	private Vector <FlussoPerBanda> flussoPerBanda;
	
	public OggettoCompleto()
	{
		flussoPerBanda=new Vector <>();
	}

	public OggettoGenerico getOggetto() {
		return oggetto;
	}

	public void setOggetto(OggettoGenerico oggetto) {
		this.oggetto = oggetto;
	}

	public Vector <FlussoPerBanda> getFlussoPerBanda() {
		return flussoPerBanda;
	}
	
	public FlussoPerBanda getFlussoPerBanda(double banda) {
		for(FlussoPerBanda flusso:flussoPerBanda)
		if(flusso.getBanda()==banda)
			return flusso;
		return null;
	}

	public void addFlussoPerBanda(FlussoPerBanda flussoPerBanda) {
		this.flussoPerBanda.add(flussoPerBanda);
	}
}
