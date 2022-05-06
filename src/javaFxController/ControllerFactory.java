package javaFxController;

import control.ControlloreSalvataggioDati;
import control.ControlloreSessione;
import control.ControlloreUtente;
import database.RicercaDao;

public class ControllerFactory {

	private SceneUpdater sceneUpdater;
	private ControlloreUtente cUtente;
	private ControlloreSessione cSessione;
	private ControlloreSalvataggioDati cDati;
	private RicercaDao rDao;
	
public ControllerFactory(SceneUpdater sceneUpdater,ControlloreUtente cUtente,ControlloreSessione cSessione,ControlloreSalvataggioDati cDati,
		RicercaDao rDao)
{
	this.sceneUpdater = sceneUpdater;
	this.cSessione = cSessione;
	this.cUtente = cUtente;
	this.cDati= cDati;
	this.rDao = rDao;
}

public IndexController createIndexController()
{
	return new IndexController(sceneUpdater, cSessione,rDao);
}

public LoginController createLoginController()
{
	return new LoginController(sceneUpdater,cUtente);
}

public ModificaPasswordController createModificaPasswordController()
{
	return new ModificaPasswordController(sceneUpdater, cUtente);
}

public ModificaProfiloController createModificaProfiloController()
{
	return new ModificaProfiloController(sceneUpdater, cUtente,cSessione);
}

public GestioneProfiloController createGestioneProfiloController()
{
	return new GestioneProfiloController(sceneUpdater,cUtente,cSessione);
}

public RegistrazioneController createRegistrazioneController()
{
	return new RegistrazioneController(sceneUpdater, cUtente);
}

public EliminaProfiloController createEliminaProfiloController()
{
	return new EliminaProfiloController(sceneUpdater,cUtente);
}

public InserisciSatelliteController createInserisciSatelliteController()
{
	return new InserisciSatelliteController(sceneUpdater,cDati);
}

public ImportaDatiController createImportaDatiController()
{
	return new ImportaDatiController(sceneUpdater,cDati);
}

public InserisciStrumentoController createInserisciStrumentoController()
{
	return new InserisciStrumentoController(sceneUpdater,cDati);
}

public VisualizzaOggettiMappaController createVisualizzaOggettiMappaController()
{
	return new VisualizzaOggettiMappaController(sceneUpdater,rDao);
}

public RicercaClumpController createRicercaClumpController()
{
	return new RicercaClumpController(sceneUpdater,rDao);
}

public RicercaOggettoRegioneController createRicercaOggettoRegioneController()
{
	return new RicercaOggettoRegioneController(sceneUpdater,rDao);
}

public RicercaOggettiInClumpController createRicercaOggettiInClumpController()
{
	return new RicercaOggettiInClumpController(sceneUpdater,rDao);
}

public VisualizzaMassaClumpController createVisualizzaMassaClumpController()
{
	return new VisualizzaMassaClumpController(sceneUpdater,rDao);
}
public VisualizzaStellaGiovaneController createVisualizzaStellaGiovaneController()
{
	return new VisualizzaStellaGiovaneController(sceneUpdater,rDao);
}

}
