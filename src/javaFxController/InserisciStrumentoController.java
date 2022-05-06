package javaFxController;

import java.util.Vector;

import control.ControlloreSalvataggioDati;
import exception.ErroreDbException;
import exception.ErroreFxController;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class InserisciStrumentoController implements UpdatableController
{
	private SceneUpdater sceneUpdater;
	private ControlloreSalvataggioDati cDati;
	@FXML
	TextField nomeStrumento;
	@FXML
	ComboBox<String> mappa;
	@FXML
	ComboBox<String> satellite;
	@FXML
	CheckBox banda1,banda2,banda3,banda4,banda5,banda6,banda7,banda8,banda9,banda10;
	
	public InserisciStrumentoController(SceneUpdater sceneUpdater,ControlloreSalvataggioDati cDati)
	{
		this.sceneUpdater= sceneUpdater;
		this.cDati = cDati;
	}
	
	public void update()
	{
		mappa.getItems().addAll("MIPS-GAL");
		mappa.getItems().addAll("GLIMPSE");
		mappa.getItems().addAll("HIGAL");
		
		try {
			satellite.getItems().addAll(cDati.getNomiSatelliti());
		} catch (ErroreDbException e) {
			AlertMessageGenerator.createAlertForError("Inserisci strumento", e);
			e.printStackTrace();
		}
	}
	
	public void conferma()
	{
		Vector <Double> bande= new Vector<>();
		if(banda1.isSelected())
			bande.add(Double.parseDouble(banda1.getText()));
		if(banda2.isSelected())
			bande.add(Double.parseDouble(banda2.getText()));
		if(banda2.isSelected())
			bande.add(Double.parseDouble(banda2.getText()));
		if(banda3.isSelected())
			bande.add(Double.parseDouble(banda3.getText()));
		if(banda4.isSelected())
			bande.add(Double.parseDouble(banda4.getText()));
		if(banda5.isSelected())
			bande.add(Double.parseDouble(banda5.getText()));
		if(banda6.isSelected())
			bande.add(Double.parseDouble(banda6.getText()));
		if(banda7.isSelected())
			bande.add(Double.parseDouble(banda7.getText()));
		if(banda8.isSelected())
			bande.add(Double.parseDouble(banda8.getText()));
		if(banda9.isSelected())
			bande.add(Double.parseDouble(banda9.getText()));
		if(banda10.isSelected())
			bande.add(Double.parseDouble(banda10.getText()));
		
			try {
				cDati.InserisciStrumento(nomeStrumento.getText()
						, satellite.getValue(), 
						mappa.getValue(),bande );
			} catch (ErroreDbException e) {
				AlertMessageGenerator.createAlertForError("Inserisci strumento", e);
				e.printStackTrace();
			}
	}
	
	public void annulla() throws ErroreFxController
	{
		sceneUpdater.updateScene("index");
	}
	
}
