package controller;

import model.AbstractModel;

public abstract class AbstractController {
	protected AbstractModel model;
	protected String action;
	protected int temperature;
	
	public AbstractController(AbstractModel model)
	{
		this.model = model;
	}
	
	public void setAction(String action)
	{
		this.action = action;
		control();
	}
	
	public void setTemperature(int temperature)
	{
		this.temperature = temperature;
	}
	
	abstract void control();
}
