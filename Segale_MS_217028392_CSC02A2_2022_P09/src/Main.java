import java.util.ArrayList;
import csc2a.uwga.thread.EstimateTaskExecutor;

public class Main {
	
	public static void main(String[]args) {
		
		ArrayList<Integer> planetScoreList = new ArrayList<>();
		
		planetScoreList.add(7);
		planetScoreList.add(144);
		planetScoreList.add(77);
		planetScoreList.add(95);
		planetScoreList.add(136);
		planetScoreList.add(213);
		planetScoreList.add(93);
		planetScoreList.add(286);
		planetScoreList.add(256);
		planetScoreList.add(272);
		
		EstimateTaskExecutor threadPoolExecutor = new EstimateTaskExecutor(planetScoreList);
		Thread thread = new Thread(threadPoolExecutor);
		
		thread.start();
		try {
			thread.join();	
		}catch (InterruptedException I) {
			I.printStackTrace();
		} System.out.println("Processing done!");
	}
	}


