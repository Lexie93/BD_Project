package javaFxController;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class AlertMessageGenerator {

	public static void createAlertForError(String nameOfFailedOperation,Exception e)
	{
		createAlert(nameOfFailedOperation,e.getMessage());
	}
	
	public static void createAlert(String nameOfFailedOperation,String  msg)
	{
		Alert alert = new Alert(AlertType.INFORMATION,msg);
		alert.setTitle(nameOfFailedOperation);
		alert.setHeaderText(null);
		alert.showAndWait();
	}
	public static boolean askConfirmation(String title, String msg){
		Alert alert = new Alert(AlertType.CONFIRMATION, msg, ButtonType.YES, ButtonType.NO);
		alert.setHeaderText(null);
		alert.setTitle(title);
		alert.showAndWait();
		
		if (alert.getResult() == ButtonType.YES)
			return true;
		return false;
	}

}
