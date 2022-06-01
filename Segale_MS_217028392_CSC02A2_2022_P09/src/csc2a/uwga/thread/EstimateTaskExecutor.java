package csc2a.uwga.thread;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Tlale
 *@version P09
 */
public class EstimateTaskExecutor implements Runnable {

	private ArrayList<Integer> planetScoreList;
	private double CostTotal = 0.0;

	/**
	 * @param planetScoreList from the planet
	 */
	public EstimateTaskExecutor(ArrayList<Integer> planetScoreList) {
		this.planetScoreList = planetScoreList;
	}

	@Override
	public void run() {
		ExecutorService threadPool = Executors.newCachedThreadPool();
		ArrayList<EstimateTask> tasks = new ArrayList<>();
		for(Integer i : planetScoreList) {
			Runnable task = new EstimateTask(i);
			tasks.add((EstimateTask) task);
			threadPool.execute(task);
		}
		threadPool.shutdown();
		while(!threadPool.isTerminated()) {
			//System.err.println("waiting. .");
		}
		//System.err.println("Threadpool Shuttingdown");
		for(EstimateTask t: tasks) {
			CostTotal += t.getCost();
		}System.out.printf("Total Cost is: \tU$%.2fM\n", CostTotal);
		
	}
	
	
}