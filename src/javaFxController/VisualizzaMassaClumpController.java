package javaFxController;

import java.util.Vector;

import database.RicercaDao;
import entity.ClumpConMassa;
import entity.ClumpMassaFX;
import exception.ErroreFxController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class VisualizzaMassaClumpController implements UpdatableController
{
	private SceneUpdater sceneUpdater;
	private RicercaDao rDao;
	@FXML
	TableView<ClumpMassaFX> dati;
	@FXML
     TableColumn<ClumpMassaFX, String> id,longitudine,latitudine,temperatura,tempSM,densSup,tipo,massa;
	
	public VisualizzaMassaClumpController(SceneUpdater sceneUpdater,RicercaDao rDao)
	{
		this.sceneUpdater=sceneUpdater;
		this.rDao = rDao;
	}
	
	public void update()
	{	
	    id.setCellValueFactory(cellData -> cellData.getValue().idProperty());
	    longitudine.setCellValueFactory(cellData -> cellData.getValue().longitudineProperty());
	    latitudine.setCellValueFactory(cellData -> cellData.getValue().latitudineProperty());
	    temperatura.setCellValueFactory(cellData -> cellData.getValue().temperaturaProperty());
	    tempSM.setCellValueFactory(cellData -> cellData.getValue().tempSuMassaProperty());
	    densSup.setCellValueFactory(cellData -> cellData.getValue().densitaSuperficieProperty());
	    tipo.setCellValueFactory(cellData -> cellData.getValue().tipoProperty());
	    massa.setCellValueFactory(cellData -> cellData.getValue().massaProperty());
	}
	
	public void conferma()
	{
		Vector<ClumpConMassa> clump;
		try {
			clump = rDao.getMasse();
		
		ObservableList<ClumpMassaFX>  oggetto = FXCollections.observableArrayList();
		if(clump!=null)
		for(ClumpConMassa ogg:clump)
		{
			oggetto.add(new ClumpMassaFX(ogg));
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
