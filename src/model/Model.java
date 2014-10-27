package model;

public class Model extends AbstractModel {
	public void run()
	{
		this.isRunning = true;
		recuit();
	}
	
	public void stop()
	{
		this.isRunning = false;
	}
	
}
