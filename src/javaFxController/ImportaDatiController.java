package javaFxController;

import java.io.IOException;

import control.ControlloreSalvataggioDati;
import exception.ErroreDbException;
import exception.ErroreFxController;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

public class ImportaDatiController implements UpdatableController
{
	private SceneUpdater sceneUpdater;
	private ControlloreSalvataggioDati cDati;
	@FXML
	private ComboBox<String> nomeFile;
	@FXML
	private ComboBox<String> tipoFile;
	
	
	public ImportaDatiController(SceneUpdater sceneUpdater,ControlloreSalvataggioDati cDati)
	{
		this.sceneUpdater=sceneUpdater;
		this.cDati = cDati;
	}
	
	@Override
	public void update()
	{
		nomeFile.getItems().addAll(cDati.getFileNames());
		tipoFile.getItems().addAll("Informazioni base Clump");
		tipoFile.getItems().addAll("Informazioni aggiuntive sul clump");
		tipoFile.getItems().addAll("sorgenti nella mappa Glimpse");
		tipoFile.getItems().addAll("sorgenti nella mappa Mipsgal");
	}
	
	public void conferma() throws ErroreFxController
	{
		try {
			cDati.importaDati(nomeFile.getValue(), tipoFile.getValue());
		} catch (IOException | ErroreDbException e) {
			AlertMessageGenerator.createAlertForError("importazione dati da file", e);
			e.printStackTrace();
		}
		
		sceneUpdater.updateScene("index");
	}
	public void annulla() throws ErroreFxController
	{
		sceneUpdater.updateScene("index");
	}
}
