package javaFxController;

import java.io.IOException;
import java.lang.reflect.Method;

import application.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class SceneFactory 
{
	ControllerFactory cFactory;
	public SceneFactory(ControllerFactory cFactory)
	{
		this.cFactory = cFactory;
	}
	
	public Scene createScene(String resource) throws Exception
	{
		String sUp = resource.substring(0,1).toUpperCase()+resource.substring(1);
		Method method = cFactory.getClass().getMethod("create"+sUp+"Controller",(Class<?>[])null );
		Object controller = method.invoke(cFactory,(Object[]) null);
		Scene scene = SceneFactory.createScene("/fxml/"+sUp+".fxml",controller);
		initUpdatableController(controller);
		return scene;
	}
	static public Scene createScene(String resource, Object controller) throws IOException
	{
		FXMLLoader fxmlLoader =  new FXMLLoader(Main.class.getResource(resource));
		fxmlLoader.setController(controller);
		Parent root = fxmlLoader.load();
		Scene scene = new Scene(root);
		scene.getStylesheets().add(Main.class.getResource("application.css").toExternalForm());
		return scene;
	}
	public void initUpdatableController(Object controller)
	{
		if(UpdatableController.class.isInstance(controller))
			((UpdatableController)controller).update();
	}
}
