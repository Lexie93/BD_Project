package entity;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ClumpMassaFX 
{
	private final StringProperty id;
	private final StringProperty longitudine;
	private final StringProperty latitudine;
	private final StringProperty temperatura;
	private final StringProperty tempSuMassa;
	private final StringProperty densitaSuperficie;
	private final StringProperty tipo;
	private final StringProperty massa;
	
	public ClumpMassaFX(ClumpConMassa cMassa)
	{
		this.id =new SimpleStringProperty(cMassa.getClump().id);
		this.longitudine = new SimpleStringProperty(String.valueOf(cMassa.getClump().longitudine));
		this.latitudine = new SimpleStringProperty(String.valueOf(cMassa.getClump().latitudine));
		this.temperatura = new SimpleStringProperty(String.valueOf(cMassa.getClump().temperatura));
		this.tempSuMassa = new SimpleStringProperty(String.valueOf(cMassa.getClump().tempSuMassa));
		this.densitaSuperficie = new SimpleStringProperty(String.valueOf(cMassa.getClump().densitaSuperficie));
		this.tipo = new SimpleStringProperty(String.valueOf(cMassa.getClump().tipo));
		this.massa = new SimpleStringProperty(String.valueOf(cMassa.getMassa()));
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

	
	public StringProperty temperaturaProperty()
	{
		return temperatura;
	}
	
	public StringProperty tempSuMassaProperty()
	{
		return tempSuMassa;
	}
	public StringProperty densitaSuperficieProperty()
	{
		return densitaSuperficie;
	}
	public StringProperty tipoProperty()
	{
		return tipo;
	}
	public StringProperty massaProperty()
	{
		return massa;
	}
}
