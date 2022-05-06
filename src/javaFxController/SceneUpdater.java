package javaFxController;

import java.io.IOException;
import java.util.HashMap;

import exception.ErroreFxController;
import javafx.scene.Scene;
import javafx.stage.Stage;

public  class SceneUpdater
{
	Stage primaryStage;
	private HashMap<String,Scene> scenes;
	//private HashMap<String,Object> controllers;
	SceneFactory sceneFactory;
	
	public SceneUpdater(Stage primaryStage) throws IOException
	{
		this.primaryStage = primaryStage;
		scenes= new HashMap<String, Scene>();
		//controllers = new HashMap<String, Object>();
	}
	public void addSceneFactory(SceneFactory sceneFactory)
	{
		this.sceneFactory = sceneFactory;
	}
	
	
	private void updateScene(Scene scene)
	{
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	private void createScene(String s) throws ErroreFxController
	{
		try 
		{
			//String sUp = s.substring(0,1).toUpperCase()+s.substring(1);
			
			//controllers.put(s,Class.forName("javaFxController."+sUp+"Controller").getConstructors()[0].newInstance(this));
			//System.out.println(s);
			//controllers.put(s,controller);
			//Scene scene = sceneFactory.createScene("/fxml/"+sUp+".fxml", controllers.get(s));
			Scene scene= sceneFactory.createScene(s);
			scenes.put(s,scene);
			updateScene(scene);
		} catch (Exception  e) {
			e.printStackTrace();
			throw new ErroreFxController();
		}
	}
	
	public void updateScene(String s) throws ErroreFxController//funzione chiamata esternamente
	{
		if(!scenes.containsKey(s))
		{
			createScene(s);
		}
		updateScene(scenes.get(s));
	}
	
	
	/*public void updateSceneAndController(String s) throws ErroreFxController //funzione chiamata esternamente
	{
		try
		{
		if(!scenes.containsKey(s))	
		createScene(s);
		((UpdatableController)controllers.get(s)).update();
		updateScene(scenes.get(s));
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new ErroreFxController();
		}
	}*/
	
	/*public void updateSceneAndController(String s, int ID) throws ErroreFxController    //per il passaggio dell'ID
	{
		try
		{
		if(!scenes.containsKey(s))	
		createScene(s);
		((UpdatableController)controllers.get(s)).update(ID);
		updateScene(scenes.get(s));
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new ErroreFxController();
		}
	}*/

	
}
