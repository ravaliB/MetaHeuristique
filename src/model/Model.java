package model;

import java.awt.Point;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

public class Model extends AbstractModel {
	public void run()
	{
		this.isRunning = true;
		Set<Integer> keys = sites.keySet();
		Iterator<Integer> it = keys.iterator();
		int dest;
		Random rand = new Random();
		
		while (it.hasNext())
		{
			dest = rand.nextInt((24 - 0) + 1);
			links.put(sites.get(it.next()), sites.get(dest));
		}
		
		notifyObserver(sites, links, temperature);
		recuit();
	}
	
	public void stop()
	{
		this.isRunning = false;
	}

	
	public void setHashMap(HashMap<Integer, Point> sites) {
		this.sites = sites;
	}
	
	public void setTemperature(double temperature)
	{
		this.temperature = temperature;
	}
}
