package entity;

public  class OggettoGenerico 
{
	protected String id;
	protected double longitudine;
	protected double latitudine;
	
	
	public OggettoGenerico(String id, double longitudine,double latitudine)
	{
		this.id = id;
		this.longitudine = longitudine;
		this.latitudine = latitudine;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public double getLongitudine() {
		return longitudine;
	}
	@Override
	public String toString() {
		return "OggettoGenerico [id=" + id + ", longitudine=" + longitudine + ", latitudine=" + latitudine + "]";
	}
	public void setLongitudine(double longitudine) {
		this.longitudine = longitudine;
	}
	public double getLatitudine() {
		return latitudine;
	}
	public void setLatitudine(double latitudine) {
		this.latitudine = latitudine;
	}
}
