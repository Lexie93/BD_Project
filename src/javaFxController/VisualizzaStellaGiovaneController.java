package javaFxController;

import java.util.Vector;

import database.RicercaDao;
import entity.FlussoPerBanda;
import entity.OggettoConFlusso;
import entity.OggettoFlussoFX;
import entity.SorgenteConRiferimento;
import exception.ErroreFxController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class VisualizzaStellaGiovaneController implements UpdatableController
{
	private SceneUpdater sceneUpdater;
	private RicercaDao rDao;
	@FXML
	TableView<OggettoFlussoFX> dati;
	@FXML
     TableColumn<OggettoFlussoFX, String> id,longitudine,latitudine;
	
	public VisualizzaStellaGiovaneController(SceneUpdater sceneUpdater,RicercaDao rDao)
	{
		this.sceneUpdater=sceneUpdater;
		this.rDao = rDao;
	}
	
	public void update()
	{	
	    id.setCellValueFactory(cellData -> cellData.getValue().idProperty());
	    longitudine.setCellValueFactory(cellData -> cellData.getValue().longitudineProperty());
	    latitudine.setCellValueFactory(cellData -> cellData.getValue().latitudineProperty());
	}
	
	public void conferma()
	{
		try {
			Vector<SorgenteConRiferimento> sorgenti = rDao.getStelleGiovani();
			
		
		ObservableList<OggettoFlussoFX>  oggetto = FXCollections.observableArrayList();
		if(sorgenti!=null)
		for(SorgenteConRiferimento ogg:sorgenti)
		{
			oggetto.add(new OggettoFlussoFX(new OggettoConFlusso(ogg,new FlussoPerBanda("",0.0,0.0,0.0))));
		}
		dati.setItems(oggetto);
		} catch (Exception e) {
			AlertMessageGenerator.createAlertForError("Visualizza massa clump", e);
			e.printStackTrace();
		}
	}
	
	public void annulla() throws ErroreFxController
	{
		sceneUpdater.updateScene("index");
	}
}
