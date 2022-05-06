package entity;

public class ClumpPerBanda 
{
	private String id;
	private double banda;
	private double semiasseMaggiore;
	private double semiasseMinore;
	private double angoloRotazione;
	
	public ClumpPerBanda(String id, double banda,double semiasseMaggiore,double semiasseMinore,double angoloRotazione)
	{
		this.id=id;
		this.banda= banda;
		this.semiasseMaggiore= semiasseMaggiore;
		this.semiasseMinore = semiasseMinore;
		this.angoloRotazione = angoloRotazione;
	}
	@Override
	public String toString() {
		return "ClumpPerBanda [id=" + id + ", banda=" + banda + ", semiasseMaggiore=" + semiasseMaggiore
				+ ", semiasseMinore=" + semiasseMinore + ", angoloRotazione=" + angoloRotazione + "]";
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public double getBanda() {
		return banda;
	}
	public void setBanda(double banda) {
		this.banda = banda;
	}
	public double getSemiasseMaggiore() {
		return semiasseMaggiore;
	}
	public void setSemiasseMaggiore(double semiasseMaggiore) {
		this.semiasseMaggiore = semiasseMaggiore;
	}
	public double getSemiasseMinore() {
		return semiasseMinore;
	}
	public void setSemiasseMinore(double semiasseMinore) {
		this.semiasseMinore = semiasseMinore;
	}
	public double getAngoloRotazione() {
		return angoloRotazione;
	}
	public void setAngoloRotazione(double angoloRotazione) {
		this.angoloRotazione = angoloRotazione;
	}
	
}
