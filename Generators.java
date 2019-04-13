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
	
	// Returns a Hashmap mapping job indices to an ArrayList that contains the respective job size at index 0.
	// Tarrival and Tdeparture of jobs can be found in the ArrayLists at indices 1 and 2 respectively at the end of simulation
	// Job sizes are hyperexponentially distributed
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
	
	// Returns a Hashmap mapping job indices to an ArrayList that contains the respective job size at index 0.
	// Job sizes are exponentially distributed
	public HashMap<Integer, ArrayList<Double>> getExpJobs(int numJobs){
		HashMap<Integer, ArrayList<Double>> exp_jobs = new HashMap<Integer, ArrayList<Double>>(); 
		for (int i = 0; i<numJobs; i++) {
			exp_jobs.put(i, new ArrayList<Double>());
			double u = Math.random();
			double size = (-1/this.lambda)*Math.log(1-u);
			exp_jobs.get(i).add(0, size);
		}
		
		return exp_jobs;
	}
	
	// Returns a LinkedList of exponentially distributed interarrival times
	private LinkedList<Double> getExpInterarrivalIntervals(int numJobs){
		LinkedList<Double> arrIntervals = new LinkedList<>();
		for (int i = 0; i<numJobs-1; i++) {
			double u = Math.random();
			double intervalLength = (-1/this.lambda)*Math.log(1-u);
			arrIntervals.add(intervalLength);
		}
		
		return arrIntervals;
	}
	
	// Returns a LinkedList of hyperexponentially distributed interarrival times
	private LinkedList<Double> getHyperexpInterarrivalIntervals(int numJobs){
		LinkedList<Double> arrIntervals = new LinkedList<>();
		for (int i = 0; i<numJobs-1; i++) {
			double u = Math.random();
			double coin = Math.random();
			if (coin < p) {
				Double interT = (-1/this.mu1)*Math.log(1-u); 
				arrIntervals.add(interT);
			}
			else {
				Double interT = (-1/this.mu2)*Math.log(1-u);
				arrIntervals.add(interT);
			}
		}
		return arrIntervals;
	}

	// Computes and returns a LinkedList of arrival times
	public LinkedList<Double> getArrivalTimes(int numJobs, String distribution){
		LinkedList<Double> arrTimes = new LinkedList<>();
		LinkedList<Double> arrIntervals = new LinkedList<>();
		if(distribution.equals("Exp")) {
			arrIntervals = getExpInterarrivalIntervals(numJobs);
		}
		else {
			arrIntervals = getHyperexpInterarrivalIntervals(numJobs);
		}
		double arrTime = 0.0;
		while(arrIntervals.size() > 0) {
			arrTime += arrIntervals.poll();
			arrTimes.add(arrTime);
		}
		return arrTimes;
	}
		
}
