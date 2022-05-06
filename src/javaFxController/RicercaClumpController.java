package javaFxController;

import java.util.Vector;

import database.RicercaDao;
import entity.FlussoPerBanda;
import entity.OggettoConFlusso;
import entity.OggettoFlussoFX;
import entity.OggettoGenerico;
import exception.ErroreFxController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class RicercaClumpController implements UpdatableController
{
	private SceneUpdater sceneUpdater;
	private RicercaDao rDao;
	
	@FXML
	TableView <OggettoFlussoFX> dati;
	@FXML
	TableColumn <OggettoFlussoFX,String> banda,flusso;
	@FXML
	Label id,longitudine,latitudine;
	@FXML
	TextField ricerca;
	
	public RicercaClumpController(SceneUpdater sceneUpdater, RicercaDao rDao)
	{
		this.sceneUpdater = sceneUpdater;
		this.rDao = rDao;
	}
	
	public void update()
	{
		banda.setCellValueFactory(cellData -> cellData.getValue().bandaProperty());
		flusso.setCellValueFactory(cellData -> cellData.getValue().flussoProperty());
	}
	
	public void conferma()
	{
		try 
		{
			String id1 = ricerca.getText();
			id.setText(id1);
			longitudine.setText(String.valueOf(rDao.getLongitude(id1)));
			latitudine.setText(String.valueOf(rDao.getLatitude(id1)));
			Vector <FlussoPerBanda> flussi =rDao.getFlussi(id1);
			ObservableList<OggettoFlussoFX>  oggetto = FXCollections.observableArrayList();
			if(flussi!=null)
			for(FlussoPerBanda flusso:flussi)
			oggetto.add(new OggettoFlussoFX( new OggettoConFlusso(new OggettoGenerico( id1,rDao.getLatitude (id1) ,rDao.getLongitude(id1) ),flusso)));
			
			dati.setItems(oggetto);
		} catch (Exception e) {
			AlertMessageGenerator.createAlertForError("Ricerca clump", e);
			e.printStackTrace();
		}
		
	}
	
	public void annulla() throws ErroreFxController
	{
		sceneUpdater.updateScene("index");
	}
}
