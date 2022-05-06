package javaFxController;

import control.ControlloreUtente;
import exception.ErroreFxController;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class LoginController  implements UpdatableController
{
	private SceneUpdater sceneUpdater;
	private ControlloreUtente cUtente;
	
public LoginController(SceneUpdater sceneUpdater,ControlloreUtente cUtente) 
{
		this.sceneUpdater = sceneUpdater;
		this.cUtente = cUtente;
}

@FXML
private TextField username;

@FXML
private TextField password;

public void login()
{
	try 
	{
		cUtente.validateLogin(username.getText(),password.getText());
		reset();
		goToIndex();
	}
	catch (Exception e) 
	{
	AlertMessageGenerator.createAlertForError("login", e);
	}
	
}

public void goToIndex() throws ErroreFxController
{
	sceneUpdater.updateScene("index");;
}

private void reset()
{
	this.username.setText("");
	this.password.setText("");
}
@Override
public void update() 
{
	//this.username.setText("");
	//this.password.setText("");
	
}

 
}
