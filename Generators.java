import java.util.LinkedList; 
import java.util.Queue; 
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
	
	private Queue<Double> getInterarrivalIntervals(int numArrivals){
		Queue<Double> arrIntervals = new LinkedList<>();
		for (int i = 0; i<numArrivals; i++) {
			double u = Math.random();
			double intervalLength = (-1/this.lambda)*Math.log(1-u);
			arrIntervals.add(intervalLength);
		}
		
		return arrIntervals;
	}

	public Queue<Double> getArrivalTimes(int numJobs){
		Queue<Double> arrTimes = new LinkedList<>();
		//arrTimes.add(0.0);
		Queue<Double> arrIntervals = getInterarrivalIntervals(numJobs-1);
		double arrTime = 0.0;
		Double next = arrIntervals.poll();
		while(next != null) {
			arrTime += next;
			arrTimes.add(arrTime);
			next = arrIntervals.poll();
		}
		
		return arrTimes;
	}
}
