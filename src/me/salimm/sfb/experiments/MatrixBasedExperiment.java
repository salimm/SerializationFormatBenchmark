package me.salimm.sfb.experiments;

import java.util.ArrayList;
import java.util.List;

import me.salimm.sfb.formats.Serializer;

public abstract class MatrixBasedExperiment<T> extends Experiment {

	public MatrixBasedExperiment(boolean isTest) {
		super(isTest);
	}

	@Override
	public List<ExperimentResult> run(List<Serializer> serializers, int attempts) {
		System.out.println("running: " + getName());
		int[] lengths = getList();
		if (isTest()) {
			lengths = getLengthsTest();
		}
		List<ExperimentResult> results = new ArrayList<ExperimentResult>();

		double[][] sTimes = new double[serializers.size()][lengths.length];
		double[][] dsTimes = new double[serializers.size()][lengths.length];

		double[][] sSizes = new double[serializers.size()][lengths.length];

		for (int valIdx = 0; valIdx < lengths.length; valIdx++) {
			int length = lengths[valIdx];

			T data = getData(getWidth(), length);

			System.gc();

			for (int i = 0; i < serializers.size(); i++) {
				Serializer serializer = serializers.get(i);

				try {
					for (int j = 0; j < attempts; j++) {
						long t1 = System.currentTimeMillis();
						byte[] deData = serializer.serialize(data, getSerializedClass());
						long t2 = System.currentTimeMillis();
						serializer.deserialize(deData, getSerializedClass());
						long t3 = System.currentTimeMillis();

						// running time
						sTimes[i][valIdx] += t2 - t1;
						dsTimes[i][valIdx] += t3 - t2;
						sSizes[i][valIdx] += deData.length;
					}

					sTimes[i][valIdx] /= attempts;
					dsTimes[i][valIdx] /= attempts;
					sSizes[i][valIdx] /= attempts;

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		for (int i = 0; i < serializers.size(); i++) {
			Serializer serializer = serializers.get(i);
			// create serialization experiment result
			ExperimentResult result = new ExperimentResult(serializer.getFormatType());
			result.setName(getName() + getSerializationTimeName());
			result.setRanges(lengths);
			result.setValues(sTimes[i]);
			results.add(result);

			// create deserialization experiment result
			result = new ExperimentResult(serializer.getFormatType());
			result.setName(getName() + getDeserializationTimeName());
			result.setRanges(lengths);
			result.setValues(dsTimes[i]);
			results.add(result);

			// making Experiment Result for size of serialization
			result = new ExperimentResult(serializer.getFormatType());
			result.setName(getName() + getSerializeSizeName());
			result.setRanges(lengths);
			result.setValues(sSizes[i]);
			results.add(result);
		}

		return results;
	}

	protected abstract Class<T> getSerializedClass();

	protected abstract String getSerializeSizeName();

	protected abstract String getDeserializationTimeName();

	protected abstract String getSerializationTimeName();

	protected abstract String getName();

	protected abstract T getData(int width, int length);

	protected abstract int getWidth();

	protected abstract int[] getLengthsTest();

	protected abstract int[] getList();

}
