package control;

import exception.DatoNonValidoException;

public class ControlloreDatiImmessi {

	public static void controlloStringaNonVuota(String s) throws DatoNonValidoException
	{
		if((s==null)||s.equals(""))
			throw new DatoNonValidoException();
	}
	
	public static void controlloStringaNonVuota(String s,String nomeDato) throws DatoNonValidoException
	{
		if((s==null)||s.equals(""))
			throw new DatoNonValidoException(nomeDato);
	}
	
	public static void controlloLunghezzaMassima(String s,int maxLenght, String nomeDato) throws DatoNonValidoException
	{
		if((s==null)||s.length()>maxLenght)
			throw new DatoNonValidoException(nomeDato);	
	}
	
	public static void controlloLunghezzaMinima(String s, int minLenght, String nomeDato) throws DatoNonValidoException
	{
		if((s==null)||s.length()<minLenght)
			throw new DatoNonValidoException(nomeDato);
	}
	
	public static void controlloEMail(String s,String nomeDato) throws DatoNonValidoException
	{
		if((s==null)||!s.contains("@"))
			throw new DatoNonValidoException(nomeDato);
	}
	
	public static void controlloNumero(String s,String nomeDato) throws DatoNonValidoException
	{
		if((s==null)||!isNumeric(s))
			throw new DatoNonValidoException(nomeDato);
	}
	
 	
	private static boolean isNumeric(String s)
	{
		try
		{
			Integer.parseUnsignedInt(s);
		}
		catch(NumberFormatException e)
		{
			return false;
		}
		return true;
	}
	
	public static void controlloDouble(String s) throws Exception{
		if(!isDouble(s))
			throw new Exception();
	}
	
	private static boolean isDouble(String s){
		try
		{
			Double.parseDouble(s);
		}
		catch(NumberFormatException e)
		{
			return false;
		}
		return true;
	}

}
