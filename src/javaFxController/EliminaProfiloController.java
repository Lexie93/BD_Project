package javaFxController;

import control.ControlloreUtente;
import exception.ErroreFxController;

public class EliminaProfiloController 
{
	SceneUpdater sceneUpdater;
	ControlloreUtente cUtente;
	
	public EliminaProfiloController(SceneUpdater sceneUpdater,ControlloreUtente cUtente)
	{
		this.sceneUpdater = sceneUpdater;
		this.cUtente = cUtente;
	}
	public void conferma()
	{
		try 
		{
			cUtente.eliminaProfilo();
			sceneUpdater.updateScene("index");
		} catch (Exception e) 
		{
			AlertMessageGenerator.createAlertForError("eliminazione", e);
		}
	}
	
	public void annulla()
	{
		try {
			sceneUpdater.updateScene("gestioneProfilo");
		} catch (ErroreFxController e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			AlertMessageGenerator.createAlertForError("reindirizzo a gestione profilo", e);
		}
	}
}
