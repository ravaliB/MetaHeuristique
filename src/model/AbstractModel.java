package model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import observer.Observable;
import observer.Observer;

public abstract class AbstractModel implements Observable {
	protected boolean isRunning = false;
	protected HashMap<Integer, Point> sites;
	protected HashMap<Point, Point> links = new HashMap<Point, Point>();
	protected double temperature;
	private ArrayList<Observer> listObserver = new ArrayList<Observer>();

	public abstract void run();

	public abstract void stop();

	public abstract void setHashMap(HashMap<Integer, Point> sites);

	public abstract void setTemperature(double temperature);

	public void addObserver(Observer obs) {
		listObserver.add(obs);
	}

	public void removeObserver() {
		listObserver = new ArrayList<Observer>();
	}

	public void notifyObserver(HashMap<Integer, Point> sites,
			HashMap<Point, Point> links, double temperature) {
		for (Observer obs : listObserver) {
			obs.update(sites, links, temperature);
		}
	}

	protected void recuit() {
		/*
		 * static double T = T_INIT = 66666666 T_STEP = 0.999 T_STOP 0.000001
		 * I_STEP 10
		 */
		TimerTask task = new TimerTask() {
			int t = 0, nbiter = 0;

			@Override
			public void run() {
				double cost_pt1, cost_pt2, best_cost;
				double delta;
				int pt1, pt2, k = 0;
				Random r = new Random();

				best_cost = cost();

				if (isRunning && (temperature > 0)) {
					t++;
					nbiter++;
					cost_pt1 = cost();
					pt1 = r.nextInt((24 - 0) + 1);

					do {
						pt2 = r.nextInt((24 - 0) + 1);
					} while (pt1 == pt2);

					swap(pt1, pt2);
					cost_pt2 = cost();

					if (cost_pt2 < best_cost) {
						best_cost = cost_pt2;
					}

					if (cost_pt2 == 200)
						System.out.println("solution trouvée, iteration :"
								+ nbiter);

					delta = cost_pt2 - cost_pt1;

					if (delta > 0) {
						if (Math.exp(-(delta / temperature)) <= r.nextInt(24) + 1)
							swap(pt2, pt1);
					}

					if ((t % 25) == 0) {
						temperature *= 0.9999;
						t = 0;
						k++;

						if (k == 10) {
							k = 0;

							notifyObserver(sites, links, temperature);
						}
					}
				}
				else
				{
					cancel();
				}
			}
		};

		Timer timer = new Timer();

		timer.schedule(task, 0, 500);
	}

	private double cost() {
		Set<Point> keys = links.keySet();
		Iterator<Point> it = keys.iterator();
		double dist, cost = 0;
		Point src, dest;

		while (it.hasNext()) {
			src = it.next();
			dest = links.get(src);
			dist = Math.sqrt(Math.pow(dest.x - src.x, 2)
					+ Math.pow(dest.y - src.y, 2));
			cost += dist;
		}

		return cost;
	}

	private void swap(Integer pt1, Integer pt2) {
		Point tmp = sites.get(pt1);
		sites.put(pt1, sites.get(pt2));
		sites.put(pt2, tmp);

		Set<Point> keys = links.keySet();
		Iterator<Point> it = keys.iterator();

		while (it.hasNext()) {
			Point ptsrc = it.next();

			if (ptsrc.equals(sites.get(pt1))) {
				Point dst = links.get(ptsrc);
				links.put(sites.get(pt2), dst);
			}

			if (ptsrc.equals(sites.get(pt2))) {
				Point dst = links.get(ptsrc);
				links.put(sites.get(pt1), dst);
			}
			
			if (links.get(ptsrc).equals(sites.get(pt1))) {
				links.put(ptsrc, sites.get(pt2));
			}

			if (links.get(ptsrc).equals(sites.get(pt2))) {
				links.put(ptsrc, sites.get(pt2));
			}
		}

		notifyObserver(sites, links, temperature);
	}
}
