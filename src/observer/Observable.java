package observer;

import java.awt.Point;
import java.util.HashMap;

public interface Observable {
	public void addObserver(Observer obs);
	public void removeObserver();
	public void notifyObserver(HashMap<Integer, Point> sites, HashMap<Point, Point> links, double temperature);
}
