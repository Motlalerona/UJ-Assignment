package csc2a.uwga.thread;

//import java.util.Random;

import acsse.csc2a.estimation.CostEstimator;

/**
 * @author Tlale
 *@version P09
 */
public class EstimateTask implements Runnable{
	private int planetScore;
	private double cost;

	/**
	 * @param planetScore from CostEstimator
	 */
	public EstimateTask(int planetScore) {
		
		this.planetScore = planetScore;
	}

	/*public double getCost(double cost ) {
		return cost;*/
	
	
	@Override
	public void run() {
		cost = CostEstimator.estimateMigrationCost(planetScore);
		System.out.printf("%d:\tU$%.2fM\n", planetScore, cost);
	}

	/**
	 * @return Cost
	 */
	public double getCost() {
		// TODO Auto-generated method stub
		return cost;
	}
		
}
