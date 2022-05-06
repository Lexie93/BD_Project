package entity;

import java.util.Vector;

public class Strumento {

	private String nome;
	private String satellite;
	private String mappa;
	private Vector <Double> banda;
	
	public Strumento(String nome,String satellite,String mappa)
	{
		this.nome = nome;
		this.satellite = satellite;
		this.mappa = mappa;
		this.banda = new Vector<>();
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getMappa() {
		return mappa;
	}
	public void setMappa(String mappa) {
		this.mappa = mappa;
	}
	public String getSatellite() {
		return satellite;
	}
	public void setSatellite(String satellite) {
		this.satellite = satellite;
	}
	public Vector <Double> getBanda() {
		return banda;
	}
	public void addBanda(double banda) {
		this.banda.add(banda);
	}
}
