package entity;

public class OggettoConFlusso {
	
	private OggettoGenerico obj;
	private FlussoPerBanda flusso;
	
	public OggettoConFlusso(OggettoGenerico obj,FlussoPerBanda flusso){
		this.obj=obj;
		this.flusso=flusso;
	}
	
	public OggettoGenerico getObj() {
		return obj;
	}
	public void setObj(OggettoGenerico obj) {
		this.obj = obj;
	}
	public FlussoPerBanda getFlusso() {
		return flusso;
	}
	public void setFlusso(FlussoPerBanda flusso) {
		this.flusso = flusso;
	}
	
	
	
}
