package entity;

import java.util.Vector;

public class SorgenteConRiferimentoCompleta 
{
	private SorgenteConRiferimento sorgenteConRiferimento;
	private Vector <FlussoPerBanda> flussoPerBanda;
	
	public SorgenteConRiferimentoCompleta()
	{
		flussoPerBanda=new Vector <>();
	}

	public SorgenteConRiferimento getSorgenteConRiferimento() {
		return sorgenteConRiferimento;
	}

	public void setSorgenteConRiferimento(SorgenteConRiferimento sorgenteConRiferimento) {
		this.sorgenteConRiferimento = sorgenteConRiferimento;
	}

	public Vector<FlussoPerBanda> getFlussoPerBanda() {
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
