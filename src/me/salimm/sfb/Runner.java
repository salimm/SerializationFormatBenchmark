package me.salimm.sfb;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import me.salimm.sfb.experiments.ExperimentExecutor;
import me.salimm.sfb.experiments.ExperimentResult;
import me.salimm.sfb.formats.AVROSerializer;
import me.salimm.sfb.formats.BSONSerializer;
import me.salimm.sfb.formats.CBORSerializer;
import me.salimm.sfb.formats.IonSerializer;
import me.salimm.sfb.formats.JSONSerializer;
import me.salimm.sfb.formats.MsgPackSerializer;
import me.salimm.sfb.formats.SmileSerializer;
import me.salimm.sfb.experiments.BigDataDoubleExperiment;
import me.salimm.sfb.experiments.BigDataIntegerExperiment;
import me.salimm.sfb.experiments.BigDataNormalizedDoubleExperiment;
import me.salimm.sfb.experiments.BigDataSmallIntegerExperiment;
import me.salimm.sfb.experiments.BigDataStringExperiment;

public class Runner {

	public static final String RESULTS_DIR = "results/";

	public static void main(String[] args) {

		boolean testMode = true;

		DataUtils.generateNumericData(100, 2000000, 0, 1);
		// creating executor
		ExperimentExecutor exec = new ExperimentExecutor();

		// adding experiments
		exec.getExperiments().add(new BigDataNormalizedDoubleExperiment(testMode));
		exec.getExperiments().add(new BigDataDoubleExperiment(testMode));
		exec.getExperiments().add(new BigDataIntegerExperiment(testMode));
		exec.getExperiments().add(new BigDataSmallIntegerExperiment(testMode));
		exec.getExperiments().add(new BigDataStringExperiment(testMode));

		// adding serializers
		exec.getSerializers().add(new JSONSerializer());
		exec.getSerializers().add(new SmileSerializer());
		exec.getSerializers().add(new BSONSerializer());
		exec.getSerializers().add(new CBORSerializer());
		exec.getSerializers().add(new AVROSerializer());
		exec.getSerializers().add(new IonSerializer());
		exec.getSerializers().add(new MsgPackSerializer());

		exec.init();
		// executing
		List<ExperimentResult> results = exec.run();

		// generating graphs and outputs
		List<List<ExperimentResult>> grouped = groupResults(results);
		for (List<ExperimentResult> list : grouped) {
			System.out.println("------------------------------");
			for (ExperimentResult result : list) {
				System.out.println(result);
			}
		}

	}

	public static List<List<ExperimentResult>> groupResults(List<ExperimentResult> results) {
		results.sort(new Comparator<ExperimentResult>() {

			@Override
			public int compare(ExperimentResult o1, ExperimentResult o2) {
				int val = o1.getName().compareTo(o2.getName());
				if (val == 0) {
					return -o1.getFormatType().compareTo(o2.getFormatType());
				}
				return -val;
			}
		});

		String currentName = "";
		List<List<ExperimentResult>> grouped = new ArrayList<List<ExperimentResult>>();
		for (ExperimentResult result : results) {
			if (!result.getName().equals(currentName)) {
				currentName = result.getName();
				grouped.add(new ArrayList<ExperimentResult>());
			}
			grouped.get(grouped.size() - 1).add(result);
		}

		return grouped;
	}

}
