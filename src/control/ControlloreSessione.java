package control;

import java.util.Vector;

import entity.Utente;
import javaFxController.UpdatableController;

public class ControlloreSessione 
{
	private Utente utente;
	Vector <UpdatableController> observers;
	
	public ControlloreSessione()
	{
		utente=null;
		this.observers = new Vector<>();
	}

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) 
	{
		this.utente = utente;
		update();
	}
	
	public void logout()
	{
		utente=null;
		update();
	}
	
	public void attach(UpdatableController uController)
	{
		observers.add(uController);
	}
	
	public void detach(UpdatableController uController)
	{
		observers.remove(uController);
	}
	
	private void update()
	{
		for(UpdatableController uC:observers)
		{
			uC.update();
		}
	}
	
}
