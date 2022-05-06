package entity;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class OggettoFlussoFX {

	private final StringProperty id;
	private final StringProperty longitudine;
	private final StringProperty latitudine;
	private final StringProperty banda;
	private final StringProperty flusso;
	private final StringProperty errore;
	
	public OggettoFlussoFX(OggettoConFlusso o)
	{
		this.id = new SimpleStringProperty(o.getObj().getId());
		this.longitudine = new SimpleStringProperty(String.valueOf(o.getObj().getLongitudine()));
		this.latitudine = new SimpleStringProperty(String.valueOf(o.getObj().getLatitudine()));
		this.banda = new SimpleStringProperty(String.valueOf(o.getFlusso().getBanda()));
		this.flusso = new SimpleStringProperty(String.valueOf(o.getFlusso().getValore()));
		this.errore = new SimpleStringProperty(String.valueOf(o.getFlusso().getValore()));
	}
	
	public StringProperty idProperty()
	{
		return id;
	}
	
	public StringProperty longitudineProperty()
	{
		return longitudine;
	}
	
	public StringProperty latitudineProperty()
	{
		return latitudine;
	}
	
	public StringProperty bandaProperty()
	{
		return banda;
	}
	
	public StringProperty flussoProperty()
	{
		return flusso;
	}
	
	public StringProperty erroreProperty()
	{
		return errore;
	}
}
