package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

import entity.Clump;
import entity.ClumpConMassa;
import entity.FlussoPerBanda;
import entity.OggettoConFlusso;
import entity.OggettoGenerico;
import entity.SorgenteConRiferimento;


public class RicercaDao implements RicercaDaoInterface {
	
	MyDataSource datasource;
	
	public RicercaDao(MyDataSource datasource)
	{
		this.datasource = datasource;
	}

	public Double cercaClumpPerDensita() throws Exception{    //REQ. 7
		  Connection con=datasource.getConnection();
		  double adatti;
		  String command="select count(*) from clump where densitasuperficie>0.1 and densitaSuperficie<1.0";
		  PreparedStatement statement = con.prepareStatement(command);
		  ResultSet result=statement.executeQuery();
		  if (result.next())
		   adatti=result.getInt(1);
		  else
		   adatti=0.0;
		  
		  command="select count(*) from clump";
		  statement = con.prepareStatement(command);
		  result=statement.executeQuery();
		  if (!result.next()) return 0.0;
		  double totali=result.getInt(1);
		  
		  statement.close();
		  con.close();
		  
		  if(totali>0)
		   return adatti/totali;
		  return 0.0;
		 }
	
	public Double getLatitude(String id) throws Exception{				//REQ. 6
		Connection con=datasource.getConnection();
		Double lat=null;
		String command="select latitudine from clump where id=?";
		PreparedStatement statement = con.prepareStatement(command);
		statement.setString(1, id);
		ResultSet result=statement.executeQuery();
		if(result.next())
			lat=result.getDouble("latitudine");
		
		statement.close();
		con.close();
		
		return lat;
	}
	
	public Double getLongitude(String id) throws Exception{				//REQ. 6
		Connection con=datasource.getConnection();
		Double lon=null;
		String command="select longitudine from clump where id=?";
		PreparedStatement statement = con.prepareStatement(command);
		statement.setString(1, id);
		ResultSet result=statement.executeQuery();
		if(result.next())
			lon=result.getDouble("longitudine");
		
		statement.close();
		con.close();
		
		return lon;
	}
	
	public Vector<FlussoPerBanda> getFlussi(String id) throws Exception{				//REQ. 6
		Vector<FlussoPerBanda> ris=new Vector<FlussoPerBanda>();
		Connection con=datasource.getConnection();
		String command="select valore,errore,banda from flusso where riferimentoid=?";
		PreparedStatement statement = con.prepareStatement(command);
		statement.setString(1, id);
		ResultSet result=statement.executeQuery();
		while(result.next()){
			FlussoPerBanda riga= new FlussoPerBanda(id,result.getDouble("banda"),result.getDouble("valore"),result.getDouble("errore"));
			ris.add(riga);
		}
		
		statement.close();
		con.close();
		
		if (!ris.isEmpty())
			return ris;
		return null;
	}
	
	public Vector<OggettoConFlusso> getOggettiMappa(String map) throws Exception{				//REQ. 5
		return getOggettiMappa(map, 0.0);
	}
	
	public Vector<OggettoConFlusso> getOggettiMappa(String map, double banda) throws Exception{				//REQ. 5
		
		Connection con=datasource.getConnection();
		String command;
		String s;
		PreparedStatement statement;
		
		Vector<OggettoConFlusso> oggetti=new Vector<OggettoConFlusso>();
		
		switch(map){
			case "HIGAL":
				s="clump";
				break;
			case "MIPSGAL":
				s="sorgentemipsgal";
				break;
			case "GLIMPSE":
				s="sorgenteglimpse";
				break;
			default :
				con.close();
				throw new Exception("Mappa non valida");
			
		}
		
		if(banda==0.0){
			command="select id,latitudine,longitudine,valore,errore,banda from " + s + " join flusso on id=riferimentoid order by id";
			statement = con.prepareStatement(command);
		}
		else {
			command="select id,latitudine,longitudine,valore,errore,banda from " + s + " join flusso on id=riferimentoid where banda=? order by id";
			statement = con.prepareStatement(command);
			statement.setDouble(1, banda);
		}
		
		//System.out.println(statement);
		ResultSet result=statement.executeQuery();
		
		while(result.next()){
			OggettoGenerico obj=new OggettoGenerico(result.getString("id"),result.getDouble("longitudine"),result.getDouble("latitudine"));
			FlussoPerBanda flusso=new FlussoPerBanda(result.getString("id"),result.getDouble("banda"),result.getDouble("valore"),result.getDouble("errore"));
			
			oggetti.add(new OggettoConFlusso(obj,flusso));
		}
		
		statement.close();
		con.close();
		
		if(!oggetti.isEmpty())
			return oggetti;
		return null;
	}

	
	public Vector<OggettoGenerico> oggettiInRegione(double lat, double lon, double dim, String forma, String tipoOggetto) throws Exception{				//REQ. 8
		Vector<OggettoGenerico> oggetti=new Vector<OggettoGenerico>();
		Connection con=datasource.getConnection();
		String command;
		PreparedStatement statement;
		
		switch (forma){
		case "quadrato":
			dim=dim/2;
			switch(tipoOggetto){
			case "clump":
				command="select id,longitudine,latitudine from clump where latitudine-? < ? and longitudine-? < ?"
						+ " order by sqrt(power(latitudine - ?,2)+power(longitudine - ?,2))";
				statement=con.prepareStatement(command);
				statement.setDouble(1, lat);
				statement.setDouble(2, dim);
				statement.setDouble(3, lon);
				statement.setDouble(4, dim);
				statement.setDouble(5, lat);
				statement.setDouble(6, lon);
				break;
			case "sorgente":
				command="select * from(select id,longitudine,latitudine from sorgentemipsgal where latitudine-? < ? and longitudine-? < ? "
						+ " union select id,longitudine,latitudine from sorgenteglimpse where latitudine-? < ? and longitudine-? < ?) as foo"
						+ " order by sqrt(power(latitudine - ?,2)+power(longitudine - ?,2))";
				statement=con.prepareStatement(command);
				statement.setDouble(1, lat);
				statement.setDouble(2, dim);
				statement.setDouble(3, lon);
				statement.setDouble(4, dim);
				statement.setDouble(5, lat);
				statement.setDouble(6, dim);
				statement.setDouble(7, lon);
				statement.setDouble(8, dim);
				statement.setDouble(9, lat);
				statement.setDouble(10, lon);
				break;
			default :
				con.close();
				throw new Exception("Tipo non valido");
			}
			
			break;
		case "cerchio":
			
			switch(tipoOggetto){
			case "clump":
				command="select id,longitudine,latitudine from clump where sqrt(power(latitudine - ?,2)+power(longitudine - ?,2))< ?"
						+ " order by sqrt(power(latitudine - ?,2)+power(longitudine - ?,2))";
				statement=con.prepareStatement(command);
				statement.setDouble(4, lat);
				statement.setDouble(5, lon);
				break;
			case "sorgente":
				command="select * from(select id,longitudine,latitudine from sorgentemipsgal where sqrt(power(latitudine - ?,2)+power(longitudine - ?,2))< ? "
						+ " union select id,longitudine,latitudine from sorgenteglimpse where sqrt(power(latitudine - ?,2)+power(longitudine - ?,2))< ?) as foo"
						+ " order by sqrt(power(latitudine - ?,2)+power(longitudine - ?,2))";
				statement=con.prepareStatement(command);
				statement.setDouble(4, lat);
				statement.setDouble(5, lon);
				statement.setDouble(6, dim);
				statement.setDouble(7, lat);
				statement.setDouble(8, lon);
				break;
			default :
				con.close();
				throw new Exception("Tipo non valido");
			}
			
			statement.setDouble(1, lat);
			statement.setDouble(2, lon);
			statement.setDouble(3, dim);
			//System.out.println(statement);
			break;
		default:
			throw new Exception("Forma non valida");
		}
		
		//System.out.println(statement);
		
		ResultSet result=statement.executeQuery();
		while(result.next()){
			OggettoGenerico obj=new OggettoGenerico(result.getString("id"), result.getDouble("longitudine"), result.getDouble("latitudine"));
			oggetti.add(obj);
		}
		
		statement.close();
		con.close();
		
		if (!oggetti.isEmpty())
			return oggetti;
		return null;
	}
	
	public Vector<SorgenteConRiferimento> oggettiDentroClump(String id, double banda) throws Exception{				//REQ. 9
		Vector<SorgenteConRiferimento> oggetti=new Vector<SorgenteConRiferimento>();
		Connection con=datasource.getConnection();
		String command="select semiassemaggioreellisse/1800 as semiassegradi from clumpperbanda where clump=? and banda=?";
		PreparedStatement statement=con.prepareStatement(command);
		statement.setString(1, id);
		statement.setDouble(2, banda);
		ResultSet result=statement.executeQuery();
		if(!result.next()) return null;
		double semiasseGradi=result.getDouble("semiassegradi");
		
		command="select * from clump join (select * from clumpperbanda where clump=? and banda=?) as clumpperbanda2 on clump.id=clumpperbanda2.clump";
		statement=con.prepareStatement(command);
		statement.setString(1, id);
		statement.setDouble(2, banda);
		result=statement.executeQuery();
		result.next();
		double lon=result.getDouble("longitudine");
		double lat=result.getDouble("latitudine");
		
		command="select * from sorgentemipsgal where sqrt(power(latitudine-?,2) + power(longitudine-?,2))<?";
		statement=con.prepareStatement(command);
		statement.setDouble(1, lat);
		statement.setDouble(2, lon);
		statement.setDouble(3, semiasseGradi);
		//System.out.println(statement);
		result=statement.executeQuery();
		while(result.next()){
			SorgenteConRiferimento sorg=new SorgenteConRiferimento(result.getString("id"),result.getDouble("longitudine"),result.getDouble("latitudine"),result.getString("riferimentoglimpse"));
			oggetti.add(sorg);
		}
		
		statement.close();
		con.close();
		
		//if (!oggetti.isEmpty())
			return oggetti;
		//return null;
	}
	
	public Vector<ClumpConMassa> getMasse() throws Exception{				//REQ. 10
		Vector<ClumpConMassa> clumps=new Vector<ClumpConMassa>();
		Clump c;
		Connection con=datasource.getConnection();
		String command;
		PreparedStatement statement;
		ResultSet result,result2;
		Double massaClump;
		int distance=10000;
		double S350;
		
		command="select * from clump";
		statement=con.prepareStatement(command);
		result=statement.executeQuery();
		
		while (result.next()){
			
			command="select valore from flusso where banda=350 and riferimentoid=?";
			statement=con.prepareStatement(command);
			statement.setString(1, result.getString("id").trim());
			result2=statement.executeQuery();
			result2.next();
			S350=result2.getDouble("valore");
			massaClump=0.0;
			Vector<SorgenteConRiferimento> oggetti=oggettiDentroClump(result.getString("id"),350);
			for(@SuppressWarnings("unused") SorgenteConRiferimento obj : oggetti){
				massaClump+=0.053 * S350 * Math.pow(distance, 2) * (Math.exp(41.14/result.getDouble("temperatura")-1));
			}
			c=new Clump(result.getString("id"),result.getDouble("longitudine"),result.getDouble("latitudine"),result.getDouble("temperatura"),result.getDouble("temperaturabolometrica_massa"),result.getDouble("densitasuperficie"),result.getInt("tipo"));
			clumps.add(new ClumpConMassa(c,massaClump));
		}
		
		statement.close();
		con.close();
		
		if (!clumps.isEmpty())
			return clumps;
		return null;
	}
	
	public double getMedia(List<Double> v){				//REQ. 10.1
		double media=0.0;
		for(Double m: v){
			media+=m;
		}
		return media/v.size();
	}
	
	public double getDeviazioneStandard(List<Double> v){				//REQ. 10.1
		double deviazioneStandard=0.0;
		double media=getMedia(v);
		for(Double m: v){
			deviazioneStandard+=Math.pow(m-media,2);
		}
		return Math.sqrt(deviazioneStandard/v.size());
	}
	
	public double getMediana(List<Double> v){				//REQ. 10.1
		Collections.sort(v);
		if (v.size()%2!=0)
			return v.get((v.size()+1)/2-1);
		return (v.get(v.size()/2-1) + v.get(v.size()/2)) / 2;
	}
	
	public double getDeviazioneMediaAssoluta(List<Double> v){    //REQ. 10.1
		  double mediana=getMediana(v);
		  List<Double> v1=new Vector<Double>();
		  for (Double d:v){
		   v1.add(Math.abs(d-mediana));
		  }
		  return getMediana(v1);
		 }
	
	public double[] statistica() throws Exception{				//REQ. 10.1
		double[] valori=new double[4];
		double media;
		double deviazioneStandard;
		double mediana;
		double deviazioneMediaAssoluta;
		Vector<ClumpConMassa> clumps=getMasse();
		List<Double> masse=new Vector<Double>();
		
		for(ClumpConMassa m :clumps){
			masse.add(m.getMassa());
		}
		
		media=getMedia(masse);
		deviazioneStandard=getDeviazioneStandard(masse);
		mediana=getMediana(masse);
		deviazioneMediaAssoluta=getDeviazioneMediaAssoluta(masse);
		
		valori[0]=media;
		valori[1]=deviazioneStandard;
		valori[2]=mediana;
		valori[3]=deviazioneMediaAssoluta;
		
		return valori;
	}
	
	public Vector<SorgenteConRiferimento> getStelleGiovani() throws Exception{    //REQ. 11
		  Vector<SorgenteConRiferimento> stelleGiovani=new Vector<SorgenteConRiferimento>();
		  Connection con=datasource.getConnection();
		  String command="select * from sorgentemipsgal as sm join sorgenteglimpse as sg on sm.riferimentoglimpse=sg.id join (select valore as valore1"
		  		+ ",riferimentoid from flusso where banda=3.6) as flusso1 on sg.id=flusso1.riferimentoid join (select valore as valore2,riferimentoid "
		  		+ "from flusso where banda=4.5) as flusso2 on sg.id=flusso2.riferimentoid"
		    + " join (select valore as valore3,riferimentoid from flusso where banda=5.8) as flusso3 on sg.id=flusso3.riferimentoid"
		    + " where valore2-valore3>0.7 and valore1-valore2>0.7 and valore1-valore2>(1.4*(valore2-valore3-0.7)+0.15)";
		  PreparedStatement statement=con.prepareStatement(command);
		  ResultSet result=statement.executeQuery();
		  while (result.next()){
		   SorgenteConRiferimento sorg=new SorgenteConRiferimento(result.getString("id").trim(),result.getDouble("longitudine"),result.getDouble("latitudine"),result.getString("riferimentoglimpse").trim());
		   stelleGiovani.add(sorg);
		  }
		  
		  statement.close();
		  con.close();
		  
		  if (!stelleGiovani.isEmpty())
		   return stelleGiovani;
		  return null;
		 }
	
	
	public static void main(String[] args){
		  try {
		   RicercaDao rdao= new RicercaDao(new MyDataSource());
		   System.out.println(rdao.getStelleGiovani());
		   
		  } catch (Exception e) {
		   System.out.println(e.getMessage());
		  }
		 }
}