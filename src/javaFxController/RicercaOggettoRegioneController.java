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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class RicercaOggettoRegioneController implements UpdatableController
{
	private SceneUpdater sceneUpdater;
	private RicercaDao rDao;
	@FXML
	TableView<OggettoFlussoFX> dati;
	@FXML
     TableColumn<OggettoFlussoFX, String> id,longitudineC,latitudineC;
    @FXML
	TextField longitudine,latitudine,distanza;
    @FXML
	RadioButton quadrato,cerchio,sorgente,clump;
	
	public RicercaOggettoRegioneController(SceneUpdater sceneUpdater,RicercaDao rDao)
	{
		this.sceneUpdater=sceneUpdater;
		this.rDao = rDao;
	}
	
	public void update()
	{
		id.setCellValueFactory(cellData -> cellData.getValue().idProperty());
	    longitudineC.setCellValueFactory(cellData -> cellData.getValue().longitudineProperty());
	    latitudineC.setCellValueFactory(cellData -> cellData.getValue().latitudineProperty());
	}
	
	public void conferma()
	{
		String forma=null,tipo=null;
		if(quadrato.isSelected())
			forma="quadrato";
		if(cerchio.isSelected())
			forma="cerchio";
		if(quadrato.isSelected())
			tipo="sorgente";
		if(cerchio.isSelected())
			tipo="clump";
		if(forma==null||tipo==null)
			return;
		try {
			Vector <OggettoGenerico> oggetti =rDao.oggettiInRegione(Double.parseDouble(latitudine.getText()), Double.parseDouble(longitudine.getText())
					, Double.parseDouble(distanza.getText()),forma , tipo);
			ObservableList<OggettoFlussoFX>  oggettiOs = FXCollections.observableArrayList();
			
			if(oggetti!=null)
			for(OggettoGenerico oggetto:oggetti)
			{
				oggettiOs.add(new OggettoFlussoFX(new OggettoConFlusso( oggetto,new FlussoPerBanda("",0.0,0.0,0.0)) ) );
			}
			
			dati.setItems(oggettiOs);
			
		} catch (Exception e) {
			AlertMessageGenerator.createAlertForError("Ricerca oggetto per regione", e);
			e.printStackTrace();
		}
	}
	
	public void annulla() throws ErroreFxController
	{
		sceneUpdater.updateScene("index");
	}
}
