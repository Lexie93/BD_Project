package javaFxController;

import java.util.Vector;

import database.RicercaDao;
import entity.FlussoPerBanda;
import entity.OggettoConFlusso;
import entity.OggettoFlussoFX;
import entity.OggettoGenerico;
import entity.SorgenteConRiferimento;
import exception.ErroreFxController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class RicercaOggettiInClumpController implements UpdatableController
{
	private SceneUpdater sceneUpdater;
	private RicercaDao rDao;
	@FXML
	TableView<OggettoFlussoFX> dati;
	@FXML
     TableColumn<OggettoFlussoFX, String> idC,longitudine,latitudine;
    @FXML
	TextField id;
    @FXML
	RadioButton b70,b160,b250,b350,b500;
	
	public RicercaOggettiInClumpController(SceneUpdater sceneUpdater,RicercaDao rDao)
	{
		this.sceneUpdater=sceneUpdater;
		this.rDao = rDao;
	}
	
	public void update()
	{
		idC.setCellValueFactory(cellData -> cellData.getValue().idProperty());
	    longitudine.setCellValueFactory(cellData -> cellData.getValue().longitudineProperty());
	    latitudine.setCellValueFactory(cellData -> cellData.getValue().latitudineProperty());
	}
	
	public void conferma()
	{
		double banda = 0;
		if(b70.isSelected())
			banda=70.0;
		if(b160.isSelected())
			banda=160.0;
		if(b250.isSelected())
			banda=250.0;
		if(b350.isSelected())
			banda=350.0;
		if(b500.isSelected())
			banda=500.0;
		if(banda==0.0|| id.getText().isEmpty())
			return;
		
		try {
			
			Vector <SorgenteConRiferimento> sorgenti=rDao.oggettiDentroClump(id.getText(), banda);
			
			ObservableList<OggettoFlussoFX>  oggettiOs = FXCollections.observableArrayList();
			
			if(sorgenti!=null)
			for(OggettoGenerico oggetto:sorgenti)
			{
				oggettiOs.add(new OggettoFlussoFX(new OggettoConFlusso( oggetto,new FlussoPerBanda("",0.0,0.0,0.0)) ) );
			}
			
			dati.setItems(oggettiOs);
		} catch (Exception e) {
			e.printStackTrace();
			AlertMessageGenerator.createAlertForError("Ricerca oggetti nel clump", e);;
		}
	}
	
	public void annulla() throws ErroreFxController
	{
		sceneUpdater.updateScene("index");
	}
}
