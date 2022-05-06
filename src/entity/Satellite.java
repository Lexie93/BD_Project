package entity;

import java.util.Vector;

public class Satellite 
{
	private String nome;
	private String inizio;
	private String fine;
	private Vector <String> agenzie;
	
	public Satellite(String nome, String inizio,String fine)
	{
		this.nome = nome;
		this.inizio = inizio;
		this.fine = fine;
		agenzie = new Vector<>();
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getInizio() {
		return inizio;
	}
	public void setInizio(String inizio) {
		this.inizio = inizio;
	}
	public String getFine() {
		return fine;
	}
	public void setFine(String fine) {
		this.fine = fine;
	}
	public Vector <String> getAgenzie() {
		return agenzie;
	}
	public void addAgenzia(String agenzia) {
		this.agenzie.add(agenzia);
	}
	
}
