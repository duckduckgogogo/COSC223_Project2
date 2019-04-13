import java.util.LinkedList;
import java.util.HashMap;
import java.util.ArrayList;
public class Simulator2 {
	
	double mu;
	double lambda1;
	double lambda2;
	double p;
	int num_jobs = 1000000;
	Generators gen;
	HashMap<Integer, ArrayList<Double>> jobs;
	LinkedList<Double> arrTimes;
	
	public Simulator2(double mu, double lambda1, double lambda2, double p) {
		
		this.mu = mu;
		this.lambda1 = lambda1;
		this.lambda2 = lambda2;
		this.p = p;
		this.gen = new Generators(mu, lambda1, lambda2, p);
		this.jobs = gen.getExpJobs(num_jobs);
		this.arrTimes = gen.getArrivalTimes(num_jobs, "Hyperxp");
	}
	
	// Runs event-driven simulation of a single-server FCFS system and returns mean response time
	// Jobs are exponentially distributed with rate mu
	// Inter-arrival times are hyperexponentailly distributed with lambda1, lambda2 and p
	public double runSimulation() {
		Double curr_time = 0.0;
		Integer job_in_service = 0; 
		Double next_arr_time = arrTimes.poll(); 
		jobs.get(job_in_service).add(1, curr_time);
		Double next_dep_time = curr_time + jobs.get(job_in_service).get(0);
		LinkedList<Integer> jobQ = new LinkedList<>();
		Integer next_job_ind = 1;
		Integer num_completed = 0;
	
		while(num_completed < num_jobs) {
			if(next_arr_time!=null && next_arr_time < next_dep_time) {
				curr_time = next_arr_time;
				if(job_in_service == -1) {
					job_in_service = next_job_ind;
					jobs.get(job_in_service).add(1, curr_time);
					next_dep_time = curr_time + jobs.get(job_in_service).get(0);
					next_job_ind ++;
				}
				else {
					jobQ.add(next_job_ind);
					jobs.get(next_job_ind).add(1, curr_time);
					next_job_ind ++;
				}
				next_arr_time = arrTimes.poll();
			}
			
			else {
				curr_time = next_dep_time;
				jobs.get(job_in_service).add(2, curr_time);
				num_completed ++;
				Integer next_to_serve = jobQ.poll();
				if(next_to_serve != null) {
					job_in_service = next_to_serve;
					next_dep_time = curr_time + jobs.get(job_in_service).get(0);
				}
				else {
					job_in_service = -1;
					if(next_arr_time!=null) {
						next_dep_time = next_arr_time + jobs.get(next_job_ind).get(0);
					}
				}
			}
		}
		
		Double total_rt = 0.0;
		
		for (int i = 10000; i<num_jobs; i++) {
			Double Tarrival = jobs.get(i).get(1);
			Double Tdeparture = jobs.get(i).get(2);
			total_rt += Tdeparture-Tarrival;
		}
		
		return total_rt/990000;
	}
	
}