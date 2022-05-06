package entity;

public class Clump extends OggettoGenerico 
{
	protected double temperatura;
	protected double tempSuMassa;
	protected double densitaSuperficie;
	protected int tipo;
	
	public Clump(String id,double longitudine,double latitudine,double temperatura, double tempSuMassa, double densitaSuperficie
			,int tipo) {
		super(id,longitudine,latitudine);
		this.temperatura = temperatura;
		this.tempSuMassa = tempSuMassa;
		this.densitaSuperficie = densitaSuperficie;
		this.id = id;
		this.tipo = tipo;
		this.longitudine = longitudine;
		this.latitudine = latitudine;
	}
	
	public Clump(String id, String lon,String lat,String temp,String tempSuM,String dens,String tipo)
	{

		super(id,Double.parseDouble(lon),Double.parseDouble(lat));
		this.temperatura = Double.parseDouble(temp);
		this.tempSuMassa = Double.parseDouble(tempSuM);
		this.densitaSuperficie = Double.parseDouble(dens);
		this.tipo = Integer.parseInt(tipo);
	}
	
	
	@Override
	public String toString() {
		return "Clump [id=" + id + ", longitudine=" + longitudine + ", latitudine=" + latitudine + ", temperatura="
				+ temperatura + ", tempSuMassa=" + tempSuMassa + ", densitaSuperficie=" + densitaSuperficie + ", tipo="
				+ tipo + "]";
	}

	public double getTemperatura() {
		return temperatura;
	}
	public void setTemperatura(double temperatura) {
		this.temperatura = temperatura;
	}
	public double getTempSuMassa() {
		return tempSuMassa;
	}
	public void setTempSuMassa(double tempSuMassa) {
		this.tempSuMassa = tempSuMassa;
	}
	public double getDensitaSuperficie() {
		return densitaSuperficie;
	}
	public void setDensitaSuperficie(double densitaSuperficie) {
		this.densitaSuperficie = densitaSuperficie;
	}
	public int getTipo() {
		return tipo;
	}
	public void setTipo(int tipo) {
		this.tipo = tipo;
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
