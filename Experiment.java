
public class Experiment {

	public static void main(String[] args) {
		
		
		double [] lambda = new double[] {0.1,0.2,0.3,0.4,0.5,0.6,0.7,0.8,0.9};
		double [] mu1s = new double[] {1.0,0.095466,0.0488103,0.0198039};
		double [] mu2s = new double[] {1.0,1.90453,1.95119,1.9802};
		double [] ps = new double[] {0.5,0.047733,0.0244051,0.00990197};
		
		//Var(S) = 1; lambda = 0.1,0.2,0.3,0.4,0.5,0.6,0.7,0.8,0.9
		double [] mrts_1 = new double[lambda.length];
		double [] mrts1 = new double[lambda.length];
		
		for (int i = 0; i < lambda.length; i++) {
			Simulator s = new Simulator(lambda[i],mu1s[0], mu2s[0], ps[0]);
			mrts_1[i] = s.runSimulation();
			mrts1[i] = s.runSimulation2(1);
			
		}
		//Var(S) = 10; lambda = 0.1,0.2,0.3,0.4,0.5,0.6,0.7,0.8,0.9
		double [] mrts_10 = new double[lambda.length];
		double [] mrts2 = new double[lambda.length];
		for (int i = 0; i < lambda.length; i++) {
			Simulator s = new Simulator(lambda[i],mu1s[1], mu2s[1], ps[1]);
			mrts_10[i] = s.runSimulation();
			mrts2[i] = s.runSimulation2(1);
		}
		
		//Var(S) = 20; lambda = 0.1,0.2,0.3,0.4,0.5,0.6,0.7,0.8,0.9
		double [] mrts_20 = new double[lambda.length];
		double [] mrts3 = new double[lambda.length];
		for (int i = 0; i < lambda.length; i++) {
			Simulator s = new Simulator(lambda[i],mu1s[2], mu2s[2], ps[2]);
			mrts_20[i] = s.runSimulation();
			mrts3[i] = s.runSimulation2(1);
		}
		
		//Var(S) = 50; lambda = 0.1,0.2,0.3,0.4,0.5,0.6,0.7,0.8,0.9
		double [] mrts_50 = new double[lambda.length];
		double [] mrts4 = new double[lambda.length];
		for (int i = 0; i < lambda.length; i++) {
			Simulator s = new Simulator(lambda[i],mu1s[3], mu2s[3], ps[3]);
			mrts_50[i] = s.runSimulation();
			mrts4[i] = s.runSimulation2(1);
		}
		
		
		for(int i = 0; i < mrts_1.length; i++) {
			System.out.println(mrts_1[i]);
		
		}
		System.out.println();
		
		for(int i = 0; i < mrts_10.length; i++) {
			System.out.println(mrts_10[i]);
		}
		
		System.out.println();
		
		for(int i = 0; i < mrts_20.length; i++) {
			System.out.println(mrts_20[i]);
		}
		
		System.out.println();
		
		for(int i = 0; i < mrts_50.length; i++) {
			System.out.println(mrts_50[i]);          
		}	
		
		System.out.println();
		System.out.println();
		
		for(int i = 0; i < mrts_1.length; i++) {
			System.out.println(mrts1[i]);
		
		}
		System.out.println();
		
		for(int i = 0; i < mrts_10.length; i++) {
			System.out.println(mrts2[i]);
		}
		
		System.out.println();
		
		for(int i = 0; i < mrts_20.length; i++) {
			System.out.println(mrts3[i]);
		}
		
		System.out.println();
		
		for(int i = 0; i < mrts_50.length; i++) {
			System.out.println(mrts4[i]);
		}                                         
		
		
	}

}
