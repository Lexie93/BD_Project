package javaFxController;

import control.ControlloreUtente;
import entity.Utente;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegistrazioneController 
{
	private SceneUpdater sceneUpdater;
	private ControlloreUtente cUtente;
	
	@FXML
	private TextField username;
	@FXML
    private PasswordField password;
	@FXML
	PasswordField password2;
	@FXML
    private TextField nome;
	@FXML
    private TextField cognome;
	@FXML
    private TextField email;
	
	public RegistrazioneController(SceneUpdater sceneUpdater,ControlloreUtente cUtente)
	{
		this.sceneUpdater = sceneUpdater;
		this.cUtente = cUtente;
	}
	
	public void registrazione()
	{
		if(password.getText().equals(password2.getText()))
		{
		try {
			cUtente.registrazione(getUtente());
			goToIndex();
		} catch (Exception e) {
			AlertMessageGenerator.createAlertForError("registrazione", e);
		}
		}
		else
			AlertMessageGenerator.createAlert("errore registrazione", "le due password devono coincidere");
	}
	public void goToIndex() 
	{
		try
		{
		sceneUpdater.updateScene("index");
		}catch (Exception e) {
			AlertMessageGenerator.createAlertForError("registrazione", e);
		}
	}
	private Utente getUtente()
	{
		return new Utente(username.getText(), password.getText(),nome.getText(),cognome.getText(),email.getText(),false);
	}
}
