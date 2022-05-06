package jUnit;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Vector;

import org.junit.Test;

import database.MyDataSource;
import database.RicercaDao;
import entity.FlussoPerBanda;
import entity.OggettoConFlusso;

public class TestRicercaDao {

	@Test
	public void testGetMedia() {
		List<Double> l=new Vector<Double>();
		l.add(12.0);l.add(55.0);l.add(74.0);l.add(79.0);l.add(90.0);
		RicercaDao rdao=new RicercaDao(new MyDataSource());
		assertEquals(62.0,rdao.getMedia(l),0);
	}
	
	@Test
	public void testGetDeviazioneStandard() {
		List<Double> l=new Vector<Double>();
		l.add(12.0);l.add(55.0);l.add(74.0);l.add(79.0);l.add(90.0);
		RicercaDao rdao=new RicercaDao(new MyDataSource());
		assertEquals(27.4,rdao.getDeviazioneStandard(l),0.1);
	}
	
	@Test
	public void testGetMediana() {
		List<Double> l=new Vector<Double>();
		l.add(12.0);l.add(90.0);l.add(74.0);l.add(79.0);l.add(55.0);
		RicercaDao rdao=new RicercaDao(new MyDataSource());
		assertEquals(74.0,rdao.getMediana(l),0);
	}
	
	@Test
	public void testGetDeviazioneMediaAssoluta() {
		List<Double> l=new Vector<Double>();
		l.add(12.0);l.add(90.0);l.add(74.0);l.add(79.0);l.add(55.0);
		RicercaDao rdao=new RicercaDao(new MyDataSource());
		assertEquals(16.0,rdao.getDeviazioneMediaAssoluta(l),0);
	}
	
	@Test
	public void testGetLatitudine() {
		String id="179761";
		RicercaDao rdao=new RicercaDao(new MyDataSource());
		try{
			assertEquals(0.017481,rdao.getLatitude(id),0);
		}catch(Exception e){
				fail("Lanciata eccezione non prevista");
			}
	}
	
	@Test
	public void testGetLongitudine() {
		String id="179761";
		RicercaDao rdao=new RicercaDao(new MyDataSource());
		try{
			assertEquals(42.602398,rdao.getLongitude(id),0);
		}catch(Exception e){
				fail("Lanciata eccezione non prevista");
			}
	}
	
	@Test
	public void testGetFlussi(){
		String id="179761";
		RicercaDao rdao=new RicercaDao(new MyDataSource());
		try{
			Vector<FlussoPerBanda> v=rdao.getFlussi(id);
			assertFalse(v.isEmpty());
		}catch(Exception e){
			fail("Lanciata eccezione non prevista");
		}
	}
	
	@Test
	public void testGetOggettiMappa(){
		String map="HIGAL";
		RicercaDao rdao=new RicercaDao(new MyDataSource());
		try{
			Vector<OggettoConFlusso> v=rdao.getOggettiMappa(map);
			assertEquals("179761",v.get(0).getObj().getId().trim());
		}catch(Exception e){
			fail("Lanciata eccezione non prevista");
		}
	}
	
	@Test
	public void testOggettiInRegione(){
		String forma="cerchio";
		String tipo="clump";
		double lat=0.017481;
		double lon=42.602398;
		double dim=0.1;
		RicercaDao rdao=new RicercaDao(new MyDataSource());
		try{
			assertEquals("179761",rdao.oggettiInRegione(lat, lon, dim, forma, tipo).get(0).getId().trim());
		}catch(Exception e){
			fail("Lanciata eccezione non prevista");
		}
	}
	
	@Test
	public void testOggettiDentroClump(){
		String fakeClump="abcdefg";
		RicercaDao rdao=new RicercaDao(new MyDataSource());
		try{
			assertNull(rdao.oggettiDentroClump(fakeClump, 350));
		}catch(Exception e){
			fail("Lanciata eccezione non prevista");
		}
	}

}
