package application;
	
import control.ControlloreSalvataggioDati;
import control.ControlloreSessione;
import control.ControlloreUtente;
import database.MyDataSource;
import database.RicercaDao;
import database.SalvaDatiDao;
import database.UtenteDAO;
import javaFxController.ControllerFactory;
import javaFxController.SceneFactory;
import javaFxController.SceneUpdater;
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			MyDataSource datasource = new MyDataSource();
			
			UtenteDAO uDao= new UtenteDAO(datasource);
			SalvaDatiDao sDao = new SalvaDatiDao(datasource);
			RicercaDao rDao=new RicercaDao(datasource);
			ControlloreSessione cSessione= new ControlloreSessione();
			ControlloreUtente cUtente= new ControlloreUtente(uDao,cSessione);
			ControlloreSalvataggioDati cDati= new ControlloreSalvataggioDati(sDao);
			
			SceneUpdater sceneUpdater = new SceneUpdater(primaryStage);	
			SceneFactory sceneFactory = new SceneFactory(new ControllerFactory(sceneUpdater, cUtente, cSessione,cDati,rDao));	
			
			sceneUpdater.addSceneFactory(sceneFactory);
			sceneUpdater.updateScene("index");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
