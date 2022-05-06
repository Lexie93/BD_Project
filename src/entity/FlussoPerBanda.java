package entity;

public class FlussoPerBanda {
private String id;
private double banda;
private double valore;
private Double errore;

public FlussoPerBanda(String id,double banda,double valore,double errore)
{
	this.id = id;
	this.banda = banda;
	this.valore = valore;
	this.errore = errore;
}

public FlussoPerBanda(String id,double banda,double valore)
{
	this.id = id;
	this.banda = banda;
	this.valore = valore;
	this.errore = null;
}
@Override
public String toString() {
	return "FlussoPerBanda [id=" + id + ", banda=" + banda + ", valore=" + valore + ", errore=" + errore + "]";
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
public double getErrore() {
	return errore;
}
public void setErrore(double errore) {
	this.errore = errore;
}
public double getValore() {
	return valore;
}
public void setValore(double valore) {
	this.valore = valore;
}
}
