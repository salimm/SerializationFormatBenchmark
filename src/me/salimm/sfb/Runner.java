package me.salimm.sfb;

import java.util.List;

import me.salimm.sfb.experiments.ExperimentExecutor;
import me.salimm.sfb.experiments.ExperimentResult;

public class Runner {
	
	public static final String RESULTS_DIR = "results.";
	
	public static void main(String[] args) {
		// creating executor
		ExperimentExecutor exec = new ExperimentExecutor();
		
		// adding experiments
		
		
		// adding serializers
		
		
		//executing
		List<ExperimentResult> results = exec.run();
		
		// generating graphs and outputs
		for (ExperimentResult result : results) {
			
		}
		
	}

}
