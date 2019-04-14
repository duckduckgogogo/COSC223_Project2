We have submitted three different java classes: Generators.java, Simulator.java, and Experiment.java. 

Generators.java contains the following methods:

1) getHyperexpJobs: generates hyperexponentially distributed jobs  
	input- number of jobs
	output- Hashmap that maps each job index to an ArrayList. Each ArrayList contains the respective job size at index 0.
		
2) getExpJobs: generates exponentially distributed jobs  
	input- number of jobs 
	output- Hashmap that maps each job index to an ArrayList. Each ArrayList contains the respective job size at index 0.
		
3) getJobs: returns a Hashmap of jobs based on a specified distribution
	input- number of jobs, String specifying type of job distribution
	output - Hashmap that maps each job index to an ArrayList. Each ArrayList contains the respective job size at index 0. 
	         Tarrival and Tdeparture can be found at index 1 and 2, respectively, at the end of simulation

4) getExpInterarrivalTimes: generates exponentially distributed interarrival times
	input- number of jobs
	output- a LinkedList of interarrival times 

5) getHyperxpInterarrivalTimes: generates hyperexponentially distributed interarrival times
	input- number of jobs
	output- a LinkedList of interarrival times 

6) getArrivalTimes: computes actual arrival times
	input- number of jobs, String specifying type of interarrival distribution
	output- a LinkedList of arrival times 

Simulator.java contains the method runSimulation which simulates an event driven single-server FCFS system with specified parameters,
and returns the mean response time.
	input- exponential distribution rate, hyperexponential distribution parameters (mu1, mu2 and p), 
	       interarrival distribution type (String), and job distribution type(String) 
	output- mean response time

Experiment.java contains the main method. Running this file performs the simulations using several different parameters,
and prints out respective mean response times. 
