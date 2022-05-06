package filesystem;

import java.io.BufferedReader;

//import entity.Clump;

public class CatalogoClumpsReader {

	//private String path;
	public CatalogoClumpsReader(String path)
	{
		//this.path="/configurationFiled/"+path;
	}
	
	/*public Vector<Clump> getClumps()
	{
		try{
		BufferedReader in = new BufferedReader(new FileReader(path));
		String s=new String();
		
		moveReaderOnData(in);
		
		//String typeOfData= readTypeOfData(in);
		while((s=in.readLine())!=null)
		{
			
		}
		
		
		
		in.close();
		}
		catch(Exception e)
		{
			return null;
		}
	}*/
	
	public void moveReaderOnData(BufferedReader in) throws Exception
	{
		String s = new String();
		int i=0;
		while((s=in.readLine())!=null && i<6)
		{
			if(s.startsWith("-----------"))
				i++;
		}
	}
	
	public String[] readTypeOfData(BufferedReader in,int numberOfTypes) throws Exception
	{
		String[] types = new String[numberOfTypes];
		String s=new String();
		boolean trunchedType=false;
		int offset=0;
		while((s=in.readLine())!=null)
		{
			int i=0;
			String typeInRow[]=s.split(",");
			if(trunchedType)
			{
				types[offset-1]=types[offset-1]+typeInRow[0];
				offset++;
				i++;
				trunchedType=false;
			}
			if(!s.endsWith(","))
				trunchedType=true;
		
			for(;i<typeInRow.length;i++)
			{
				types[i+offset]=typeInRow[i];
			}
			offset+=typeInRow.length;
			if(offset==numberOfTypes)
				break;
		}
		return types;
	}
}
