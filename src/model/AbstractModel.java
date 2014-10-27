package model;

import java.util.ArrayList;

import observer.Observable;
import observer.Observer;

public abstract class AbstractModel implements Observable{
	protected boolean isRunning = false;
	private ArrayList<Observer> listObserver = new ArrayList<Observer>();
	
	public abstract void run();
	public abstract void stop();
	
	public void addObserver(Observer obs)
	{
		listObserver.add(obs);
	}
	
	public void removeObserver()
	{
		listObserver = new ArrayList<Observer>();
	}
	
	public void notifyObserver(String str)
	{
		for (Observer obs : listObserver)
		{
			obs.update(str);
		}
	}
	
	protected void recuit()
	{
		while (isRunning)
		{
			
		}
	}
}
