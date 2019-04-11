import java.util.LinkedList;
import java.util.HashMap;
import java.util.ArrayList;
public class Simulator {
	
	double lambda;
	double mu1;
	double mu2;
	double p;
	int num_jobs = 1000000;
	Generators gen;
	HashMap<Integer, ArrayList<Double>> jobs;
	LinkedList<Double> arrTimes;
	
	
	
	public Simulator(double lambda, double mu1, double mu2, double p) {
		
		this.lambda = lambda;
		this.mu1 = mu1;
		this.mu2 = mu2;
		this.p = p;
		this.gen = new Generators(lambda, mu1, mu2, p);
		this.jobs = gen.getJobs(num_jobs);
		this.arrTimes = gen.getArrivalTimes(num_jobs);
	}
	
	public double runSimulation() {
		Double curr_time = 0.0;
		Integer job_in_service = 0; //Server currently working on job 0
		Double next_arr_time = arrTimes.poll(); //When job 1 will arrive
		jobs.get(job_in_service).add(1, curr_time);// Set arrival time of job 0
		Double next_dep_time = curr_time + jobs.get(job_in_service).get(0);//When job 0 will be completed
		LinkedList<Integer> jobQ = new LinkedList<>();
		Integer next_job_ind = 1; // The next job to add to queue/to server if empty
		Integer num_completed = 0;// Number of jobs completed
	
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
	public double runSimulation2(int policy) { //Policy 1: assign jobs to servers based on probability
		HashMap<Integer, Integer> jobsToServers = null;
		if(policy == 1) {//set jobsToServers based on which policy we're simulating so we'll have more cases for different policies 
			jobsToServers = this.gen.mapJobsToServers(num_jobs);
		}
		Double curr_time = 0.0;
		Integer job_in_server1 = -1; 
		Integer job_in_server2 = -1;
		Double next_dep_time1 = Double.MAX_VALUE; //The next time a job will depart server 1
		Double next_dep_time2 = Double.MAX_VALUE; //The next time a job will depart server 2
		if(jobsToServers.get(0) == 1){
			job_in_server1 = 0; //send job 0 to server 1
			jobs.get(job_in_server1).add(1, curr_time); //set arrival time of job 0
			next_dep_time1 = curr_time + jobs.get(job_in_server1).get(0);
		}
		else {
			job_in_server2 = 0; //send job 0 to server 2
			jobs.get(job_in_server1).add(1, curr_time); //set arrival time of job 0
			next_dep_time2 = curr_time + jobs.get(job_in_server1).get(0);
		}
		Double next_arr_time = arrTimes.poll(); //When job 1 will arrive
		Double next_dep_time = Math.min(next_dep_time1, next_dep_time2);
		
		LinkedList<Integer> jobQ1 = new LinkedList<>();
		LinkedList<Integer> jobQ2 = new LinkedList<>();
		Integer next_job_ind = 1; // The next job to add to queue/to server if empty
		Integer num_completed = 0;// Number of jobs completed
	
		while(num_completed < num_jobs) {
			//If next event is an arrival
			if(next_arr_time!=null && next_arr_time < next_dep_time) {
				curr_time = next_arr_time;
				//If the next job that arrives goes to server1
				if (jobsToServers.get(next_job_ind) == 1) { 
					if(job_in_server1 == -1) {
						job_in_server1 = next_job_ind;
						jobs.get(job_in_server1).add(1, curr_time);
						next_dep_time1 = curr_time + jobs.get(job_in_server1).get(0);
						next_dep_time = Math.min(next_dep_time1, next_dep_time2);
						next_job_ind ++;
					}
					else {
						jobQ1.add(next_job_ind);
						jobs.get(next_job_ind).add(1, curr_time);
						next_job_ind ++;
					}			
				}
				//If the next job that arrives goes to server2
				else{ 
					if(job_in_server2 == -1) {
						job_in_server2 = next_job_ind;
						jobs.get(job_in_server2).add(1, curr_time);
						next_dep_time2 = curr_time + jobs.get(job_in_server2).get(0);
						next_dep_time = Math.min(next_dep_time1, next_dep_time2);
						next_job_ind ++;
					}
					else {
						jobQ2.add(next_job_ind);
						jobs.get(next_job_ind).add(1, curr_time);
						next_job_ind ++;
					}
				}
				
				next_arr_time = arrTimes.poll();
				
			}
			
			//If next event is a departure
			else {
				curr_time = next_dep_time;
				//If departure is happening from server 1
				if(next_dep_time1 < next_dep_time2) {
					jobs.get(job_in_server1).add(2, curr_time);
					num_completed ++;
					Integer next_to_serve = jobQ1.poll();
					if(next_to_serve != null) {
						job_in_server1 = next_to_serve;
						next_dep_time1 = curr_time + jobs.get(job_in_server1).get(0);
						next_dep_time = Math.min(next_dep_time1, next_dep_time2);
					}
					else {
						job_in_server1 = -1;
						if(next_arr_time!=null) { //If jobQ1 is not empty
							Double next_arr_time1 = arrTimes.get(0);//Arrival time of the next job that will be sent to server 1
							Integer next_job1 = next_job_ind; //Index of the next job that will be sent to server 1
							int i = 1;
							while(jobsToServers.get(next_job1) != 1) {
								next_arr_time1 = arrTimes.get(i);//Arrival time of the next job that will be sent to server 1
								next_job1++; //Index of the next job that will be sent to server 1
								i++;
								
							}
							next_dep_time1 = next_arr_time1 + jobs.get(next_job1).get(0);
							next_dep_time = Math.min(next_dep_time1, next_dep_time2);
						}
					}
				}
				//If departure is happening from server 2
				else {
					jobs.get(job_in_server2).add(2, curr_time);
					num_completed ++;
					Integer next_to_serve = jobQ2.poll();
					if(next_to_serve != null) { //If jobQ2 is not empty
						job_in_server2 = next_to_serve;
						next_dep_time2 = curr_time + jobs.get(job_in_server2).get(0);
						next_dep_time = Math.min(next_dep_time1, next_dep_time2);
					}
					else {
						job_in_server2 = -1;
						if(next_arr_time!=null) {
							Double next_arr_time2 = arrTimes.get(0);//Arrival time of the next job that will be sent to server 2
							Integer next_job2 = next_job_ind; //Index of the next job that will be sent to server 2
							int i = 1;
							while(jobsToServers.get(next_job2) != 2) {
								next_arr_time2 = arrTimes.get(i);//Arrival time of the next job that will be sent to server 2
								next_job2++; //Index of the next job that will be sent to server 2
								i++;
								
							}
							next_dep_time2 = next_arr_time2 + jobs.get(next_job2).get(0);
							next_dep_time = Math.min(next_dep_time1, next_dep_time2);
						}
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
