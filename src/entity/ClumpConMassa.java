package entity;

public class ClumpConMassa {
	private Clump clump;
	private double massa;
	
	public ClumpConMassa(Clump clump, double massa){
		this.clump=clump;
		this.massa=massa;
	}

	public Clump getClump() {
		return clump;
	}

	public void setClump(Clump clump) {
		this.clump = clump;
	}

	public double getMassa() {
		return massa;
	}

	public void setMassa(double massa) {
		this.massa = massa;
	}
}
