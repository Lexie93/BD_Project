package javaFxController;

import control.ControlloreSessione;
import database.RicercaDao;
import exception.ErroreFxController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class IndexController implements UpdatableController
{
	private SceneUpdater sceneUpdater;
	private ControlloreSessione cSessione;
	private RicercaDao rDao;
	
	@FXML
	Button buttonRegistrazione, buttonLogin,buttonGestioneProfilo,buttonLogout,
	buttonInserisciSatellite,buttonImportaDati,buttonInserisciStrumento,buttonOperazioniUtente;
	@FXML
	Label benvenutoLbl;
	@FXML
	ComboBox<String> operazioni;
	
	public IndexController(SceneUpdater sceneUpdater,ControlloreSessione cSessione,RicercaDao rDao)
	{
		this.sceneUpdater = sceneUpdater;
		this.cSessione = cSessione;
		cSessione.attach(this);
		this.rDao = rDao;
	}
	
	public void goToLogin() throws Exception 
	{
		sceneUpdater.updateScene("login");
	}
	
	public void goToRegistrazione() throws ErroreFxController 
	{
		sceneUpdater.updateScene("registrazione");
	}
	
	public void goToGestioneProfilo() throws ErroreFxController
	{
		sceneUpdater.updateScene("gestioneProfilo");
	}
	public void logout()
	{
		cSessione.logout();
		try {
			sceneUpdater.updateScene("index");
		} catch (ErroreFxController e) {
			AlertMessageGenerator.createAlertForError("logout", e);
		}
	}
	
	public void goToImportaDati() throws ErroreFxController
	{
		sceneUpdater.updateScene("importaDati");
	}
	
	public void goToInserisciSatellite() throws ErroreFxController
	{
		sceneUpdater.updateScene("inserisciSatellite");
	}
	
	public void goToInserisciStrumento() throws ErroreFxController
	{
		sceneUpdater.updateScene("inserisciStrumento");
	}
	
	
	public void goToOperazioniUtente() throws ErroreFxController
	{
		if(operazioni.getValue().equals("VisualizzaStelleMassive"))
			stelleMassive();
		else
			if(operazioni.getValue().equals("VisualizzaStatisticheMassa"))
				statisticheMassa();
			else
		sceneUpdater.updateScene(operazioni.getValue());
		
	}
	
	public void stelleMassive() {
		  try{
		   Double fraction=rDao.cercaClumpPerDensita();
		   AlertMessageGenerator.createAlert("ricerca densità", "il rapporto 'clump candidati ad ospitare una stella massiva/clump totali' vale " + fraction);
		  }catch(Exception e){
		   e.printStackTrace();
		   AlertMessageGenerator.createAlert("ricerca clump per densita'", "errore durante l'operazione");
		  }
		 }
	
	public void statisticheMassa() {
		  try{
		   double[] stat=rDao.statistica();
		   AlertMessageGenerator.createAlert("statistiche massa", "Media: " + stat[0] + "\nDeviazione Standard: " + stat[1] + "\nMediana: " + stat[2] + "\nDeviazione Media Assoluta: " + stat[3]);
		  }catch(Exception e){
		   e.printStackTrace();
		   AlertMessageGenerator.createAlert("calcolo statistiche", "errore durante l'operazione");
		  }
		 }
	

	@Override
	public void update() 
	{
		if(cSessione.getUtente()!=null)
			setUser();
		else
			setAnonimo();
	}
	
	private void setUser()
	{
		boolean amministratore = cSessione.getUtente().isAmministratore();
		String username = cSessione.getUtente().getUsername();
		buttonRegistrazione.setVisible(amministratore);
		buttonImportaDati.setVisible(amministratore);
		buttonInserisciSatellite.setVisible(amministratore);
		buttonInserisciStrumento.setVisible(amministratore);
		
		buttonLogin.setVisible(false);
		buttonGestioneProfilo.setVisible(true);
		buttonLogout.setVisible(true);
		buttonOperazioniUtente.setVisible(true);
		operazioni.setVisible(true);
		benvenutoLbl.setText("Benvenuto "+username);
	}
	
	private void setAnonimo()
	{
		buttonRegistrazione.setVisible(false);
		buttonLogin.setVisible(true);
		buttonGestioneProfilo.setVisible(false);
		buttonLogout.setVisible(false);
		benvenutoLbl.setText("Benvenuto utente anonimo");
		buttonImportaDati.setVisible(false);
		buttonInserisciSatellite.setVisible(false);
		buttonInserisciStrumento.setVisible(false);
		buttonOperazioniUtente.setVisible(false);
		operazioni.setVisible(false);
	}
	
}
