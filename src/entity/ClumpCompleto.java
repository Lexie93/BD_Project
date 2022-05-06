package entity;

import java.util.Vector;

public class ClumpCompleto
{
	private Clump clump;
	private Vector <FlussoPerBanda> flussoPerBanda;
	private Vector <ClumpPerBanda> clumpPerBanda;
	
	public ClumpCompleto()
	{
		flussoPerBanda=new Vector <>();
		clumpPerBanda=new Vector<>();
	}
	
	public Clump getClump() {
		return clump;
	}

	public void setClump(Clump clump) {
		this.clump = clump;
	}
	
	public ClumpPerBanda getClumpPerBanda(double banda) {
		for(ClumpPerBanda clump:clumpPerBanda)
			if(clump.getBanda()==banda)
				return clump;
			return null;
	}
	
	public Vector<ClumpPerBanda> getClumpPerBanda()
	{
		return clumpPerBanda;
	}

	public void addClumpPerBanda(ClumpPerBanda clumpPerBanda) {
		this.clumpPerBanda.add(clumpPerBanda);
	}
	
	public FlussoPerBanda getFlussoPerBanda(double banda) {
		for(FlussoPerBanda flusso:flussoPerBanda)
		if(flusso.getBanda()==banda)
			return flusso;
		return null;
	}
	
	public Vector<FlussoPerBanda> getFlussoPerBanda() {
		return flussoPerBanda;
	}

	public void addFlussoPerBanda(FlussoPerBanda flussoPerBanda) {
		this.flussoPerBanda.add(flussoPerBanda);
	}
	
}
