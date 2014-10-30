package controller;

import java.awt.Point;
import java.util.HashMap;

import model.AbstractModel;

public abstract class AbstractController {
	protected AbstractModel model;
	protected String action;
	protected double temperature;
	
	public AbstractController(AbstractModel model)
	{
		this.model = model;
	}
	
	public void setAction(String action)
	{
		this.action = action;
		control();
	}
	
	public void setTemperature(double temperature)
	{
		model.setTemperature(temperature);
	}
	
	public void setHashBlock(HashMap<Integer, Point> hashBlocks)
	{
		model.setHashMap(hashBlocks);
	}
	
	abstract void control();
}
