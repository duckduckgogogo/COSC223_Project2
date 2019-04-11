import java.util.LinkedList; 
import java.util.HashMap;
import java.util.ArrayList;

public class Generators {
	
	double lambda;
	double mu1;
	double mu2;
	double p;
	
	Generators(double lambda, double mu1, double mu2, double p)
	{
		this.lambda = lambda;
		this.mu1 = mu1;
		this.mu2 = mu2;
		this.p = p;
	}
	
	public HashMap<Integer, ArrayList<Double>> getJobs(int numJobs){
		HashMap<Integer, ArrayList<Double>> jobs = new HashMap<Integer, ArrayList<Double>>(); 
		for (int i = 0; i<numJobs; i++) {
			jobs.put(i, new ArrayList<Double>());
			double u = Math.random();
			double coin = Math.random();
			if (coin < p) {
				Double size = (-1/this.mu1)*Math.log(1-u); 
				jobs.get(i).add(0, size);
			}
			else {
				Double size = (-1/this.mu2)*Math.log(1-u);
				jobs.get(i).add(0, size);
			}
		}
		return jobs;
	}
	
	private LinkedList<Double> getInterarrivalIntervals(int numArrivals){
		LinkedList<Double> arrIntervals = new LinkedList<>();
		for (int i = 0; i<numArrivals; i++) {
			double u = Math.random();
			double intervalLength = (-1/this.lambda)*Math.log(1-u);
			arrIntervals.add(intervalLength);
		}
		
		return arrIntervals;
	}

	public LinkedList<Double> getArrivalTimes(int numJobs){
		LinkedList<Double> arrTimes = new LinkedList<>();
		//arrTimes.add(0.0);
		LinkedList<Double> arrIntervals = getInterarrivalIntervals(numJobs-1);
		double arrTime = 0.0;
		Double next = arrIntervals.poll();
		while(next != null) {
			arrTime += next;
			arrTimes.add(arrTime);
			next = arrIntervals.poll();
		}
		
		return arrTimes;
	}
	
	//This method returns a HashMap that maps jobs to servers based on a coin toss result
	//We need another method in this class for a policy that assigns jobs to servers based on size that also returns a mapping of jobs to servers  
	public HashMap<Integer, Integer> mapJobsToServers(int numJobs){
		HashMap<Integer, Integer> jobsToServers = new HashMap<Integer, Integer>(); 
		for (int i = 0; i<numJobs; i++) {
			double coin = Math.random();
			if (coin < 0.5) {
				
				jobsToServers.put(i, 1);
			}
			else {
				
				jobsToServers.put(i, 2);
			}
		}
		return jobsToServers;
	}
	
	
}
