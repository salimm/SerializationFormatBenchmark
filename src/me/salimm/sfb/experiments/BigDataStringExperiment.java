package me.salimm.sfb.experiments;

import java.util.ArrayList;
import java.util.List;

import me.salimm.sfb.DataUtils;
import me.salimm.sfb.formats.Serializer;

public class BigDataStringExperiment extends Experiment {

	public BigDataStringExperiment(boolean isTest) {
		super(isTest);
	}

	private static final String NAME = "Big Data Range String (5-15 length) ";
	private static final String SERIALIZATION_TIME = " Serialization Time";
	private static final String DESERIALIZATION_TIME = " Deserialization Time";
	private static final String SERIALIZED_SIZE = " Serialized Size";

	private static final int width = 10;

	private final int[] lengthsTest = new int[] { 100, 1000 };
	private final int[] lengths = new int[] { 10, 100, 1000, 10000, 100000 };

	@Override
	public List<ExperimentResult> run(List<Serializer> serializers) {
		int[] lengths = this.lengths;
		if (isTest()) {
			lengths = lengthsTest;
		}

		List<ExperimentResult> results = new ArrayList<ExperimentResult>();

		double[][] sTimes = new double[serializers.size()][lengths.length];
		double[][] dsTimes = new double[serializers.size()][lengths.length];

		double[][] sSizes = new double[serializers.size()][lengths.length];

		for (int valIdx = 0; valIdx < lengths.length; valIdx++) {
			int length = lengths[valIdx];

			String[][] data = DataUtils.generateStringData(width, length, 3, 15);

			System.gc();
			// try {
			// Thread.sleep(1000);
			// } catch (InterruptedException e1) {
			// e1.printStackTrace();
			// }

			for (int i = 0; i < serializers.size(); i++) {
				Serializer serializer = serializers.get(i);

				try {
					long t1 = System.currentTimeMillis();
					byte[] deData = serializer.serialize(data, String[][].class);
					long t2 = System.currentTimeMillis();
					serializer.deserialize(deData, String[][].class);
					long t3 = System.currentTimeMillis();

					System.out.println("i: " + i + " valIdx: " + valIdx + " lenght: " + length);
					// running time
					sTimes[i][valIdx] = t2 - t1;
					dsTimes[i][valIdx] = t3 - t2;
					sSizes[i][valIdx] = deData.length;

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		for (int i = 0; i < serializers.size(); i++) {
			Serializer serializer = serializers.get(i);
			// create serialization experiment result
			ExperimentResult result = new ExperimentResult(serializer.getFormatType());
			result.setName(NAME + SERIALIZATION_TIME);
			result.setRanges(lengths);
			result.setValues(sTimes[i]);
			results.add(result);

			// create deserialization experiment result
			result = new ExperimentResult(serializer.getFormatType());
			result.setName(NAME + DESERIALIZATION_TIME);
			result.setRanges(lengths);
			result.setValues(dsTimes[i]);
			results.add(result);

			// making Experiment Result for size of serialization
			result = new ExperimentResult(serializer.getFormatType());
			result.setName(NAME + SERIALIZED_SIZE);
			result.setRanges(lengths);
			result.setValues(sSizes[i]);
			results.add(result);
		}

		return results;
	}

}
