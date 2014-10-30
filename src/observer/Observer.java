package observer;

import java.awt.Point;
import java.util.HashMap;

public interface Observer {
	public void update(HashMap<Integer, Point> map, HashMap<Point, Point> linksMap, double temperature);
}
