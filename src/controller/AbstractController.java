package controller;

import java.awt.Point;
import java.util.HashMap;

import model.AbstractModel;

public abstract class AbstractController {
	protected AbstractModel model;
	protected String action;
	protected int temperature;
	protected HashMap<Integer, Point> blocks;
	
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
	
	public void setHashBlock(HashMap<Integer, Point> hashBlocks)
	{
		this.blocks = hashBlocks;
	}
	
	abstract void control();
}
