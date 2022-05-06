package javaFxController;

import control.ControlloreSessione;
import control.ControlloreUtente;
import entity.Utente;
import exception.ErroreFxController;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class ModificaProfiloController implements UpdatableController
{	
	private SceneUpdater sceneUpdater;
	private ControlloreUtente cUtente;
	@FXML
	private TextField username;
	@FXML
    private PasswordField password;
	@FXML
    private TextField nome;
	@FXML
    private TextField cognome;
	@FXML
    private TextField email;
	
	
public ModificaProfiloController(SceneUpdater sceneUpdater,ControlloreUtente cUtente,ControlloreSessione cSessione)
{
	this.sceneUpdater = sceneUpdater;
	this.cUtente = cUtente;
	cSessione.attach(this);
}

public void conferma()
{
	try {
		cUtente.modificaProfilo(getUtente());
		sceneUpdater.updateScene("index");
	} catch (Exception e) {
		AlertMessageGenerator.createAlertForError("modifica profilo", e);
	}
}

public void annulla()
{
	try {
		sceneUpdater.updateScene("index");
	} catch (ErroreFxController e) 
	{
		AlertMessageGenerator.createAlertForError("passaggio a index", e);
	}
}

@Override
public void update() 
{
	try {
		if(cUtente.getProfilo()!=null)
		setUtente(cUtente.getProfilo());
	} catch (Exception e) {
		AlertMessageGenerator.createAlertForError("visualizzazione profilo", e);
	}
	
}
public  void setUtente(Utente u)
{
	username.setText(u.getUsername());
	nome.setText(u.getNome());
	cognome.setText(u.getCognome());
	email.setText(u.getEmail());
}

public Utente getUtente()
{
	return new Utente(
	username.getText(),
	"",
	nome.getText(),
	cognome.getText(),
	email.getText(),false);
}

}
