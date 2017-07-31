package me.salimm.sfb;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
import me.salimm.sfb.experiments.BigDataNormalizedDoubleRoundedExperiment;
import me.salimm.sfb.experiments.BigDataSmallIntegerExperiment;
import me.salimm.sfb.experiments.BigDataStringExperiment;

public class Runner {

	public static final String RESULTS_DIR = "results/";

	public static void main(String[] args) throws IOException {

		boolean testMode = true;
		int attempts = 5;

		DataUtils.generateNumericData(100, 2000000, 0, 1, false);
		// creating executor
		ExperimentExecutor exec = new ExperimentExecutor(attempts);

		// adding experiments
		exec.getExperiments().add(new BigDataNormalizedDoubleExperiment(testMode));
		exec.getExperiments().add(new BigDataDoubleExperiment(testMode));
		exec.getExperiments().add(new BigDataNormalizedDoubleRoundedExperiment(testMode));
		exec.getExperiments().add(new BigDataIntegerExperiment(testMode));
		exec.getExperiments().add(new BigDataSmallIntegerExperiment(testMode));
		exec.getExperiments().add(new BigDataStringExperiment(testMode));

		// adding serializes
		exec.getSerializers().add(new JSONSerializer());
		exec.getSerializers().add(new SmileSerializer());
		exec.getSerializers().add(new BSONSerializer());
		exec.getSerializers().add(new CBORSerializer());
		exec.getSerializers().add(new AVROSerializer());
		exec.getSerializers().add(new IonSerializer());
		exec.getSerializers().add(new MsgPackSerializer());
		System.out.println("************************************");
		System.out.println("****     created experiment[isTest:" + testMode + "]...");
		System.out.println("************************************");

		// initializing
		exec.init();
		System.out.println("************************************");
		System.out.println("****     Intialized experiment...");
		System.out.println("************************************");

		// executing
		List<ExperimentResult> results = exec.run();
		System.out.println("************************************");
		System.out.println("****     Experiment executed...");
		System.out.println("************************************");

		// pritning results sets
		List<List<ExperimentResult>> grouped = groupResults(results);
		for (List<ExperimentResult> list : grouped) {
			System.out.println("------------------------------");
			for (ExperimentResult experimentResult : list) {
				System.out.println(experimentResult);
			}
		}
		System.out.println("************************************");
		System.out.println("****     saving files...");
		System.out.println("************************************");

		// generating outputs
		for (List<ExperimentResult> list : grouped) {
			double[][] mat = reprotMatrix(list);

			String filename = RESULTS_DIR + list.get(0).getName() + ".csv";
			FileWriter fw = new FileWriter(new File(filename));

			// writing header
			print(fw, "\"" + list.get(0).getName() + "\", " + "\"Range/Method\"");
			for (ExperimentResult result : list) {
				print(fw, ", \"" + result.getFormatType().name() + "\"");
			}
			print(fw, "\n");

			// writing rows
			for (int i = 0; i < mat.length; i++) {
				print(fw, "\"\"");
				for (int j = 0; j < mat[i].length; j++) {
					print(fw, ", " + mat[i][j]);
				}
				print(fw, "\n");
			}
			fw.flush();
			fw.close();
		}

	}

	private static void print(FileWriter fw, String str) throws IOException {
		fw.write(str);
		// System.out.print(str);
	}

	private static double[][] reprotMatrix(List<ExperimentResult> list) {
		double[][] mat = new double[list.get(0).getRanges().length][list.size() + 1];

		for (int i = 0; i < mat.length; i++) {
			mat[i][0] = list.get(0).getRanges()[i];
			for (int j = 0; j < list.size(); j++) {
				mat[i][j + 1] = list.get(j).getValues()[i];
			}
		}
		return mat;
	}

	public static List<List<ExperimentResult>> groupResults(List<ExperimentResult> results) {
		results.sort(new Comparator<ExperimentResult>() {

			@Override
			public int compare(ExperimentResult o1, ExperimentResult o2) {
				int val = o1.getName().compareTo(o2.getName());
				if (val == 0) {
					return o1.getFormatType().name().compareTo(o2.getFormatType().name());
				}
				return val;
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
