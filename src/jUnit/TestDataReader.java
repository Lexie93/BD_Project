package jUnit;

import static org.junit.Assert.assertEquals;

import java.util.Vector;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import entity.Clump;
import entity.ClumpCompleto;
import entity.OggettoCompleto;
import entity.SorgenteConRiferimentoCompleta;
import filesystem.DataReader;

public class TestDataReader 
{
	
	@Before
	public void start()
	{
		System.out.println("inizio test");
	}
	
	@After
	public void end()
	{
		System.out.println("fine test\n");
	}
	
	@Test
	public void testReadClumps() throws Exception
	{
		 Clump[] clumpEs={
				new Clump("179761",42.602398,0.017481,12.682,0.400791,0.0588188,1),
				new Clump("179768",42.603713,-0.190704,18.202,3.50297,0.0305294,0),
				new Clump("179779",42.606778,0.184258,16.834,2.19243,0.0139096,0),
				new Clump("179783",42.607509,-0.368501,8.469,0.0344902,0.475286,1),
				new Clump("179784",42.607560,-0.142551,12.983,3.26508,0.315412,2 )  };	
	
		DataReader d= new DataReader();
		Vector <Clump> clumps = d.readClumps("higal.csv");
		
		for(int i=0;i<clumpEs.length;i++)
			{
			System.out.println(clumps.get(i));

			assertEquals(clumpEs[i].getId(),clumps.get(i).getId());
			assertEquals(clumpEs[i].getLatitudine(),clumps.get(i).getLatitudine(),0.0);
			assertEquals(clumpEs[i].getLongitudine(),clumps.get(i).getLongitudine(),0.0);
			assertEquals(clumpEs[i].getTemperatura(),clumps.get(i).getTemperatura(),0.0);
			assertEquals(clumpEs[i].getTempSuMassa(),clumps.get(i).getTempSuMassa(),0.0);
			assertEquals(clumpEs[i].getDensitaSuperficie(),clumps.get(i).getDensitaSuperficie(),0.0);
			assertEquals(clumpEs[i].getTipo(),clumps.get(i).getTipo(),0);
			}
	}
	
	@Test
	public void testReadClumpInformation() throws Exception
	{
		final double banda1=70.0, banda2=160.0, banda3=250.0, banda4=350.0,banda5=500.0;
		DataReader d= new DataReader();
		Vector <ClumpCompleto> clumpInformations = d.readClumpInformations("higal_additionalinfo.csv");
		
		for(int i=0;i<5;i++)
		{
			System.out.println(clumpInformations.get(i).getClumpPerBanda(banda1));
			System.out.println(clumpInformations.get(i).getFlussoPerBanda(banda1));
			
			System.out.println(clumpInformations.get(i).getClumpPerBanda(banda2));
			System.out.println(clumpInformations.get(i).getFlussoPerBanda(banda2));
			
			System.out.println(clumpInformations.get(i).getClumpPerBanda(banda3));
			System.out.println(clumpInformations.get(i).getFlussoPerBanda(banda3));
			
			System.out.println(clumpInformations.get(i).getClumpPerBanda(banda4));
			System.out.println(clumpInformations.get(i).getFlussoPerBanda(banda4));
			
			System.out.println(clumpInformations.get(i).getClumpPerBanda(banda5));
			System.out.println(clumpInformations.get(i).getFlussoPerBanda(banda5));
			
		}
	}
	
	@Test
	public void testReadSorgenteSenzaRiferimentoInformation() throws Exception
	{
		final double banda1=3.6, banda2=4.5, banda3=5.8, banda4=8.0;
		DataReader d= new DataReader();
		Vector <OggettoCompleto> sorgenteInformations = d.readSorgenteSenzaRiferimentoInformation("r08.csv");
		
		for(int i=0;i<5;i++)
		{
			System.out.println(sorgenteInformations.get(i).getOggetto());
			
			System.out.println(sorgenteInformations.get(i).getFlussoPerBanda(banda1));
			
			System.out.println(sorgenteInformations.get(i).getFlussoPerBanda(banda2));
			
			System.out.println(sorgenteInformations.get(i).getFlussoPerBanda(banda3));
			
			System.out.println(sorgenteInformations.get(i).getFlussoPerBanda(banda4));
			
			
		}
	}
	
	@Test
	public void testReadSorgenteConRiferimentoInformation() throws Exception
	{
		final double banda1=24.0;
		DataReader d= new DataReader();
		Vector <SorgenteConRiferimentoCompleta> sorgenteInformations = d.readSorgenteConRiferimentoInformation("mips.csv");
		
		for(int i=0;i<5;i++)
		{
			System.out.println((sorgenteInformations.get(i).getSorgenteConRiferimento()));
			
			System.out.println(sorgenteInformations.get(i).getFlussoPerBanda(banda1));			
		}
	}
}
