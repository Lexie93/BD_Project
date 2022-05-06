package javaFxController;

import control.ControlloreSessione;
import control.ControlloreUtente;
import entity.Utente;
import exception.ErroreFxController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class GestioneProfiloController implements UpdatableController
{
	private SceneUpdater sceneUpdater;
	private ControlloreUtente cUtente;
	@FXML
	private Label username;
	@FXML
    private Label nome;
	@FXML
    private Label cognome;
	@FXML
    private Label email;
	
	public GestioneProfiloController(SceneUpdater sceneUpdater,ControlloreUtente cUtente,ControlloreSessione cSessione)
	{
		this.sceneUpdater = sceneUpdater;
		this.cUtente = cUtente;
		
		cSessione.attach(this);
	}
	@Override
	public void update() 
	{
		try {
			if(cUtente.getProfilo()!=null)
			this.setUtente(cUtente.getProfilo());
		} catch (Exception e) 
		{
			e.printStackTrace();
			AlertMessageGenerator.createAlertForError("Visualizza profilo", e);
		}
		
	}
	public  void setUtente(Utente u)
	{
		username.setText(u.getUsername());
		nome.setText(u.getNome());
		cognome.setText(u.getCognome());
		email.setText(u.getEmail());
	}
	
	public void goToModificaProfilo()
	{
		try {
			sceneUpdater.updateScene("modificaProfilo");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			AlertMessageGenerator.createAlertForError("Passaggio a modifica profilo",e);
		}
	}
	
	public void goToModificaPassword()
	{
		try {
			sceneUpdater.updateScene("modificaPassword");
		} catch (ErroreFxController e) {
			AlertMessageGenerator.createAlertForError("Passaggio a modifica password",e);
		}
	}
	
	public void goToEliminaProfilo()
	{
		
		try {
			sceneUpdater.updateScene("eliminaProfilo");
		} catch (ErroreFxController e) {
			AlertMessageGenerator.createAlertForError("passaggio a eliminazione profilo", e);
			e.printStackTrace();
		}
	}
	
	public void goToIndex()
	{
		try {
			sceneUpdater.updateScene("index");
		} catch (ErroreFxController e) {
			AlertMessageGenerator.createAlertForError("passaggio a eliminazione profilo", e);
			e.printStackTrace();
		}
	}
	
}
