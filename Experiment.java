
public class Experiment {

	public static void main(String[] args) {
		
		
		double [] lambda = new double[] {0.1,0.2,0.3,0.4,0.5,0.6,0.7,0.8,0.9}; //Exponential distribution rates for interarrival times
		double [] mu1s = new double[] {1.0,0.095466,0.0488103,0.0198039};
		double [] mu2s = new double[] {1.0,1.90453,1.95119,1.9802};
		double [] ps = new double[] {0.5,0.047733,0.0244051,0.00990197};
		Simulator s = new Simulator();
		
		System.out.println("Mean reponse times for hyperexponentially distributed jobs with Var(S) = 1,10,20,50");
		System.out.println("Interarrival times are exponentially distributed with rate (lambda) ranging from 0 to 1 ");
		
		System.out.println();
		
		System.out.println("Var(S) = 1");
		//Var(S) = 1; lambda = 0.1,0.2,0.3,0.4,0.5,0.6,0.7,0.8,0.9
		for (int i = 0; i < lambda.length; i++) {
			
			System.out.println(s.runSimulation(lambda[i],mu1s[0], mu2s[0], ps[0], "Exp", "Hyperexp"));
		}
		System.out.println();
		
		System.out.println("Var(S) = 10");
		//Var(S) = 10; lambda = 0.1,0.2,0.3,0.4,0.5,0.6,0.7,0.8,0.9
		for (int i = 0; i < lambda.length; i++) {
			System.out.println(s.runSimulation(lambda[i],mu1s[1], mu2s[1], ps[1], "Exp", "Hyperexp"));
		}
		System.out.println();
		
		System.out.println("Var(S) = 20");
		//Var(S) = 20; lambda = 0.1,0.2,0.3,0.4,0.5,0.6,0.7,0.8,0.9
		for (int i = 0; i < lambda.length; i++) {
			System.out.println(s.runSimulation(lambda[i],mu1s[2], mu2s[2], ps[2], "Exp", "Hyperexp"));
		}
		System.out.println();
		
		System.out.println("Var(S) = 50");
		//Var(S) = 10; lambda = 0.1,0.2,0.3,0.4,0.5,0.6,0.7,0.8,0.9
		for (int i = 0; i < lambda.length; i++) {
			System.out.println(s.runSimulation(lambda[i],mu1s[3], mu2s[3], ps[3], "Exp", "Hyperexp"));
		}
		System.out.println();
		
		double [] mu = new double[] {1.1,1.2,1.3,1.4,1.5,1.6,1.7,1.8,1.9}; //Exponential distribution rates for job size
		System.out.println("Mean reponse times for exponetially distributed jobs with rate (mu) ranging from 1 to 2");
		System.out.println("Interarrival times are hyperexponentially distributed with Var(interarrival times) = 1,10,20,50");
		
		System.out.println();
		
		System.out.println("Var(interarrival times) = 1");
		//Var(interarrival times) = 1; lambda = 1.1,1.2,1.3,1.4,1.5,1.6,1.7,1.8,1.9
		for (int i = 0; i < mu.length; i++) {
			System.out.println(s.runSimulation(mu[i],mu1s[0], mu2s[0], ps[0], "Hyperexp", "Exp"));
		}
		System.out.println();
		
		System.out.println("Var(interarrival times) = 10");
		//Var(interarrival times) = 10; lambda = 1.1,1.2,1.3,1.4,1.5,1.6,1.7,1.8,1.9
		for (int i = 0; i < mu.length; i++) {
			System.out.println(s.runSimulation(mu[i],mu1s[1], mu2s[1], ps[1], "Hyperexp", "Exp"));
		}
		System.out.println();
		
		System.out.println("Var(interarrival times) = 20");
		//Var(interarrival times) = 20; lambda = 1.1,1.2,1.3,1.4,1.5,1.6,1.7,1.8,1.9
		for (int i = 0; i < mu.length; i++) {
			System.out.println(s.runSimulation(mu[i],mu1s[2], mu2s[2], ps[2], "Hyperexp", "Exp"));
		}
		System.out.println();
		
		System.out.println("Var(interarrival times) = 50");
		//Var(interarrival times) = 50; lambda = 1.1,1.2,1.3,1.4,1.5,1.6,1.7,1.8,1.9
		for (int i = 0; i < mu.length; i++) {
			System.out.println(s.runSimulation(mu[i],mu1s[3], mu2s[3], ps[3], "Hyperexp", "Exp"));
		}
		
	}

}
