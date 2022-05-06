package javaFxController;

import java.util.Vector;

import database.RicercaDao;
import entity.OggettoConFlusso;
import entity.OggettoFlussoFX;
import exception.ErroreFxController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class VisualizzaOggettiMappaController implements UpdatableController
{
	private SceneUpdater sceneUpdater;
	private RicercaDao rDao;
	@FXML
	TableView<OggettoFlussoFX> dati;
	@FXML
     TableColumn<OggettoFlussoFX, String> colonna1,colonna2,colonna3,colonna4,colonna5,colonna6;
    @FXML
	ComboBox<String> banda;
    @FXML
	ComboBox<String>mappa;
	
	public VisualizzaOggettiMappaController(SceneUpdater sceneUpdater,RicercaDao rDao)
	{
		this.sceneUpdater=sceneUpdater;
		this.rDao = rDao;
	}
	
	public void update()
	{
		mappa.getItems().addAll("MIPSGAL");
		mappa.getItems().addAll("GLIMPSE");
		mappa.getItems().addAll("HIGAL");
		
		banda.getItems().addAll(String.valueOf(3.6));
		banda.getItems().addAll(String.valueOf(4.5));
		banda.getItems().addAll(String.valueOf(5.8));
		banda.getItems().addAll(String.valueOf(8.0));
		banda.getItems().addAll(String.valueOf(24.0));
		banda.getItems().addAll(String.valueOf(70.0));
		banda.getItems().addAll(String.valueOf(160.0));
		banda.getItems().addAll(String.valueOf(250.0));
		banda.getItems().addAll(String.valueOf(350.0));
		banda.getItems().addAll(String.valueOf(500.0));
		banda.getItems().add("Tutte le bande");
		
	    colonna1.setCellValueFactory(cellData -> cellData.getValue().idProperty());
	    colonna2.setCellValueFactory(cellData -> cellData.getValue().longitudineProperty());
	    colonna3.setCellValueFactory(cellData -> cellData.getValue().latitudineProperty());
	    colonna4.setCellValueFactory(cellData -> cellData.getValue().bandaProperty());
	    colonna5.setCellValueFactory(cellData -> cellData.getValue().flussoProperty());
	    colonna6.setCellValueFactory(cellData -> cellData.getValue().erroreProperty());
	}
	
	public void conferma() throws Exception
	{
		Vector<OggettoConFlusso> obj;
		if(banda.getValue().equals("Tutte le bande"))
		obj=rDao.getOggettiMappa((String)mappa.getValue());
		else
			obj=rDao.getOggettiMappa((String)mappa.getValue(),Double.parseDouble(banda.getValue().trim()));
		
		ObservableList<OggettoFlussoFX>  oggetto = FXCollections.observableArrayList();
		if(obj!=null)
		for(OggettoConFlusso ogg:obj)
		{
			oggetto.add(new OggettoFlussoFX(ogg));
			//System.out.println(ogg.getObj().getId());
		//dati.getItems().add(ogg.getObj().getId());
		}
		dati.setItems(oggetto);
	}
	
	public void annulla() throws ErroreFxController
	{
		sceneUpdater.updateScene("index");
	}
	
}
