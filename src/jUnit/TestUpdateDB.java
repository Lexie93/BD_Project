package jUnit;

import org.junit.Test;

import database.MyDataSource;
import database.SalvaDatiDao;
import entity.Satellite;
import entity.Strumento;
import filesystem.DataReader;

public class TestUpdateDB 
{
	@Test
	public void testSalvaDatiClump() throws Exception
	{
		DataReader d = new DataReader();
		
		MyDataSource datasource = new MyDataSource();
		SalvaDatiDao sDao = new SalvaDatiDao(datasource);
		sDao.inserisciDatiClump(d.readClumpInformations("higal_additionalinfo.csv"));
	}
	
	@Test
	public void testSalvaClump() throws Exception
	{
		DataReader d = new DataReader();
		
		MyDataSource datasource = new MyDataSource();
		SalvaDatiDao sDao = new SalvaDatiDao(datasource);
		sDao.inserisciClump(d.readClumps("higal.csv"));
	}
	
	@Test
	public void testSalvaDatiClimpse() throws Exception
	{
		DataReader d = new DataReader();
		
		MyDataSource datasource = new MyDataSource();
		SalvaDatiDao sDao = new SalvaDatiDao(datasource);
		sDao.inserisciSorgentiGlimpseEDati((d.readSorgenteSenzaRiferimentoInformation("r08.csv")));
	}
	@Test
	public void testSalvaDatiMipsgal() throws Exception
	{
		DataReader d = new DataReader();
		
		MyDataSource datasource = new MyDataSource();
		SalvaDatiDao sDao = new SalvaDatiDao(datasource);
		sDao.inserisciSorgentiMipsgalEDati((d.readSorgenteConRiferimentoInformation("mips.csv")));
	}
	
	@Test
	public void testSalvaSatellite() throws Exception
	{
		MyDataSource datasource = new MyDataSource();
		SalvaDatiDao sDao = new SalvaDatiDao(datasource);
		
		Satellite herschel= new Satellite("Herschel","10-7-2009","17-6-2013");
		herschel.addAgenzia("ESA");
		Satellite spritzer= new Satellite("Spritzer","18-12-2003","15-5-2009");
		spritzer.addAgenzia("NASA");
		
		sDao.inserisciSatellite(herschel);
		sDao.inserisciSatellite(spritzer);
	}
	
	@Test
	public void testSalvaStrumento() throws Exception
	{
		MyDataSource datasource = new MyDataSource();
		SalvaDatiDao sDao = new SalvaDatiDao(datasource);
		
		Strumento pacs= new Strumento("PACS","Herschel","HIGAL");
		pacs.addBanda(70.0);
		pacs.addBanda(160.0);
		
		Strumento spire= new Strumento("SPIRE","Herschel","HIGAL");
		spire.addBanda(250.0);
		spire.addBanda(350.0);
		spire.addBanda(500.0);
		
		Strumento irac= new Strumento("IRAC","Spritzer","Glimpse");
		irac.addBanda(3.6);
		irac.addBanda(4.5);
		irac.addBanda(5.8);
		irac.addBanda(8.0);
		
		Strumento mips= new Strumento("MIPS","Spritzer","MIPS-GAL");
		mips.addBanda(24.0);
		
		sDao.inserisciStrumento(pacs);
		sDao.inserisciStrumento(spire);
		sDao.inserisciStrumento(irac);
		sDao.inserisciStrumento(mips);
	}
	
}
