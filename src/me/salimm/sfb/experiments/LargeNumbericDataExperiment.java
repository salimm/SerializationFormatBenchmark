package me.salimm.sfb.experiments;

import java.util.List;

import me.salimm.sfb.formats.Serializer;

public class LargeNumbericDataExperiment implements Experiment {

	private static final String NAME = "Large numeric data";
	private static final int widths = 100;

	private static final int[] lengths = new int[] { 100, 1000, 10000, 100000, 1000000, 10000000 };

	@Override
	public List<ExperimentResult> run(List<Serializer> serializer) {

		for (int length : lengths) {
			
		}
		return null;
	}
	
	

}
