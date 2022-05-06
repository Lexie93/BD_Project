package filesystem;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.Vector;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import entity.Clump;
import entity.ClumpCompleto;
import entity.ClumpPerBanda;
import entity.FlussoPerBanda;
import entity.OggettoCompleto;
import entity.OggettoGenerico;
import entity.SorgenteConRiferimento;
import entity.SorgenteConRiferimentoCompleta;

public class DataReader
{
	private static final String path="src/configurationFiles/";
	public DataReader()
	{
	}
	
	public Vector<Clump>  readClumps(String filename) throws IOException
	{
		Reader in = new FileReader(path+filename);
	    List<CSVRecord> records = CSVFormat.EXCEL.parse(in).getRecords();
	    
	    int i=0;
	    while(records.get(i).size()==1)
	    {
	    	i++;
	    }
	    i++;
	    Vector<Clump> clumps=new Vector<>();
	    while(i<records.size())
	    {
	    	//System.out.println(records.get(i).get(0));
	    	CSVRecord record =records.get(i);
	    	clumps.add(new Clump(record.get(0),record.get(1),record.get(2),record.get(3)
	    			,record.get(4),record.get(5),record.get(6)));
	    	i++;
	    }
	    in.close();
	    return clumps;
	}
	
	public Vector<ClumpCompleto>  readClumpInformations(String filename) throws IOException
	{
		Reader in = new FileReader(path+filename);
	    List<CSVRecord> records = CSVFormat.EXCEL.parse(in).getRecords();
	    
	    int i=0;
	    while(records.get(i).size()<20)
	    {
	    	i++;
	    }
	    i++;
	    Vector<ClumpCompleto> informazioniClump=new Vector<>();
	    while(i<records.size())
	    {
	    	//System.out.println(records.get(i).get(0));
	    	CSVRecord record =records.get(i);
	    	ClumpCompleto informazioneClump =new ClumpCompleto();
	    	
	    	//Leggo il flusso
	    	if(!record.get(1).trim().equals("0.0"))
	    	informazioneClump.addFlussoPerBanda(new FlussoPerBanda(record.get(0),70.0,Double.parseDouble(record.get(1))));
	    	if(!record.get(2).trim().equals("0.0"))
	    	informazioneClump.addFlussoPerBanda(new FlussoPerBanda(record.get(0),160.0,Double.parseDouble(record.get(2))));
	    	if(!record.get(3).trim().equals("0.0"))
	    	informazioneClump.addFlussoPerBanda(new FlussoPerBanda(record.get(0),250.0,Double.parseDouble(record.get(3))));
	    	if(!record.get(4).trim().equals("0.0"))
	    	informazioneClump.addFlussoPerBanda(new FlussoPerBanda(record.get(0),350.0,Double.parseDouble(record.get(4))));
	    	if(!record.get(5).trim().equals("0.0"))
	    	informazioneClump.addFlussoPerBanda(new FlussoPerBanda(record.get(0),500.0,Double.parseDouble(record.get(5))));
	    	
	    	//Leggo i dati del clump rispetto la banda
	    	
	    	if(!record.get(6).trim().equals("0.0")&&!record.get(7).trim().equals("0.0")&&!record.get(16).trim().equals("0.0"))
	    	informazioneClump.addClumpPerBanda(new ClumpPerBanda(record.get(0),70.0,Double.parseDouble(record.get(6))
	    			,Double.parseDouble(record.get(7)),Double.parseDouble(record.get(16)) ));
	    	
	    	if(!record.get(8).trim().equals("0.0")&&!record.get(9).trim().equals("0.0")&&!record.get(17).trim().equals("0.0"))
	    	informazioneClump.addClumpPerBanda(new ClumpPerBanda(record.get(0),160.0,Double.parseDouble(record.get(8))
	    			,Double.parseDouble(record.get(9)),Double.parseDouble(record.get(17)) ));
	    	
	    	if(!record.get(10).trim().equals("0.0")&&!record.get(11).trim().equals("0.0")&&!record.get(18).trim().equals("0.0"))
	    	informazioneClump.addClumpPerBanda(new ClumpPerBanda(record.get(0),250.0,Double.parseDouble(record.get(10))
	    			,Double.parseDouble(record.get(11)),Double.parseDouble(record.get(18)) ));
	    	
	    	if(!record.get(12).trim().equals("0.0")&&!record.get(13).trim().equals("0.0")&&!record.get(19).trim().equals("0.0"))
	    	informazioneClump.addClumpPerBanda(new ClumpPerBanda(record.get(0),350.0,Double.parseDouble(record.get(12))
	    			,Double.parseDouble(record.get(13)),Double.parseDouble(record.get(19)) ));
	    	
	    	if(!record.get(14).trim().equals("0.0")&&!record.get(15).trim().equals("0.0")&&!record.get(20).trim().equals("0.0"))
	    	informazioneClump.addClumpPerBanda(new ClumpPerBanda(record.get(0),500.0,Double.parseDouble(record.get(14))
	    			,Double.parseDouble(record.get(15)),Double.parseDouble(record.get(20)) ));
	    	
	    	informazioniClump.add(informazioneClump);
	    	i++;
	    }
	    in.close();
	    return informazioniClump;
	}
	
	public Vector <OggettoCompleto> readSorgenteSenzaRiferimentoInformation(String fileName) throws IOException
	{
		Reader in = new FileReader(path+fileName);
	    List<CSVRecord> records = CSVFormat.EXCEL.parse(in).getRecords();
	    
	    int i=0;
	    while(records.get(i).size()==1)
	    {
	    	i++;
	    }
	    i++;
	    Vector<OggettoCompleto> oggettiCompleti=new Vector<>();
	    while(i<records.size())
	    {
	    	CSVRecord record =records.get(i);
	    	
	    	OggettoCompleto oggettoCompleto = new OggettoCompleto();
	    	
	    	//leggo i dati generici della sorgente
	    	oggettoCompleto.setOggetto(new OggettoGenerico(record.get(0),Double.parseDouble(record.get(1)),
	    			Double.parseDouble(record.get(2) )));
	    	
	    	//leggo il flusso
	    	
	    	if(!record.get(3).trim().isEmpty())
	    	oggettoCompleto.addFlussoPerBanda(new FlussoPerBanda(record.get(0),3.6,Double.parseDouble(record.get(3))));
	    	if(!record.get(4).trim().isEmpty())
	    	oggettoCompleto.addFlussoPerBanda(new FlussoPerBanda(record.get(0),4.5,Double.parseDouble(record.get(4))));
	    	if(!record.get(5).trim().isEmpty())
	    	oggettoCompleto.addFlussoPerBanda(new FlussoPerBanda(record.get(0),5.8,Double.parseDouble(record.get(5))));
	    	if(!record.get(6).trim().isEmpty())
	    	oggettoCompleto.addFlussoPerBanda(new FlussoPerBanda(record.get(0),8.0,Double.parseDouble(record.get(6))));
	    	
	    	oggettiCompleti.add(oggettoCompleto);
	    	i++;
	    }
	    in.close();
	    return oggettiCompleti;
	}
	
	public Vector <SorgenteConRiferimentoCompleta> readSorgenteConRiferimentoInformation(String fileName) throws IOException
	{
		Reader in = new FileReader(path+fileName);
	    List<CSVRecord> records = CSVFormat.EXCEL.parse(in).getRecords();
	    
	    int i=0;
	    while(records.get(i).size()==1)
	    {
	    	i++;
	    }
	    i++;
	    Vector<SorgenteConRiferimentoCompleta> sorgentiComplete=new Vector<>();
	    while(i<records.size())
	    {
	    	CSVRecord record =records.get(i);
	    	
	    	SorgenteConRiferimentoCompleta sorgenteCompleta = new SorgenteConRiferimentoCompleta();
	    	
	    	//leggo i dati generici della sorgente
	    	sorgenteCompleta.setSorgenteConRiferimento(new SorgenteConRiferimento(record.get(0),Double.parseDouble(record.get(1)),
	    			Double.parseDouble(record.get(2)) ,record.get(5)));
	    	
	    	//leggo il flusso
	    	
	    	if(!record.get(3).trim().isEmpty()&& !record.get(4).trim().isEmpty())
	    	sorgenteCompleta.addFlussoPerBanda(new FlussoPerBanda(record.get(0),24.0,Double.parseDouble(record.get(3))
	    			,Double.parseDouble(record.get(4))));
	    	
	    	
	    	sorgentiComplete.add(sorgenteCompleta);
	    	i++;
	    }
	    in.close();
	    return sorgentiComplete;
	}
	
	public Vector<String> getFilenames()
	{
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();
		Vector <String> fileNames= new Vector<String>();
		for(int i=0;i<listOfFiles.length;i++)
			if(listOfFiles[i].getName().endsWith(".csv"))
			fileNames.add(listOfFiles[i].getName());
		return fileNames;
	}
}
