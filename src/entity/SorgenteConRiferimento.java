package entity;

public class SorgenteConRiferimento extends OggettoGenerico
{
	private String idSorgenteRiferita;
	public SorgenteConRiferimento(String id, double longitudine, double latitudine,String idSorgenteRiferita) {
		super(id, longitudine, latitudine);
		this.setIdSorgenteRiferita(idSorgenteRiferita);
	}
	public String getIdSorgenteRiferita() {
		return idSorgenteRiferita;
	}
	public void setIdSorgenteRiferita(String idSorgenteRiferita) {
		this.idSorgenteRiferita = idSorgenteRiferita;
	}
	@Override
	public String toString() {
		return "SorgenteConRiferimento [idSorgenteRiferita=" + idSorgenteRiferita + ", id=" + id + ", longitudine="
				+ longitudine + ", latitudine=" + latitudine + "]";
	}
	
	
}
