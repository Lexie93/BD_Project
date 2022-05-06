package javaFxController;

import control.ControlloreUtente;
import exception.ErroreFxController;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;

public class ModificaPasswordController {

	@FXML
	PasswordField password;
	@FXML
	PasswordField password2;
	
	private SceneUpdater sceneUpdater;
	private ControlloreUtente cUtente;
	
	public ModificaPasswordController(SceneUpdater sceneUpdater,ControlloreUtente cUtente)
	{
		this.sceneUpdater = sceneUpdater;
		this.cUtente = cUtente;
	}
	
	public void conferma()
	{
		if(password.getText().equals(password2.getText()))
		{
			try {
				cUtente.modificaPassword(password.getText());
				sceneUpdater.updateScene("index");
			} catch (Exception e) {
				AlertMessageGenerator.createAlertForError("modifica Password", e);
			}
		}
		else
			AlertMessageGenerator.createAlert("errore modifica Password", "le due password devono coincidere");
			
	}
	
	public void annulla()
	{
		try {
			sceneUpdater.updateScene("index");
		} catch (ErroreFxController e) 
		{
			AlertMessageGenerator.createAlertForError("modifica Password", e);
		}
	}
	
}
