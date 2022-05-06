package javaFxController;

import java.util.Vector;

import control.ControlloreSalvataggioDati;
import exception.ErroreFxController;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class InserisciSatelliteController 
{
	private SceneUpdater sceneUpdater;
	private ControlloreSalvataggioDati cDati;
	@FXML
	TextField nomeSatellite;
	@FXML
	DatePicker dataInizio;
	@FXML
	DatePicker dataFine;
	@FXML
	TextField nomeAgenzia1;
	@FXML
	TextField nomeAgenzia2;
	
	public InserisciSatelliteController(SceneUpdater sceneUpdater,ControlloreSalvataggioDati cDati)
	{
		this.sceneUpdater=sceneUpdater;
		this.cDati = cDati;
	}
	
	public void conferma()
	{
		Vector <String> agenzie=new Vector<>();
		agenzie.add(nomeAgenzia1.getText());
		agenzie.add(nomeAgenzia2.getText());
		try {
			cDati.inserisciSatellite(nomeSatellite.getText(), dataInizio.getValue().toString(), dataFine.getValue().toString(),agenzie);
		} catch (Exception e) {
			AlertMessageGenerator.createAlertForError("immissione satellite", e);
			e.printStackTrace();
		}
	}
	public void annulla() throws ErroreFxController
	{
		sceneUpdater.updateScene("index");
	}
}
