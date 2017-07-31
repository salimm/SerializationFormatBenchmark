package me.salimm.sfb.experiments;

import me.salimm.sfb.DataUtils;

public class BigDataDoubleExperiment extends MatrixBasedExperiment<double[][]> {

	public BigDataDoubleExperiment(boolean isTest) {
		super(isTest);
	}

	private static final String NAME = "Big Data Range(-MAX_DOUBLE, MAX_DOUBLE)";
	private static final String SERIALIZATION_TIME = " Serialization Time";
	private static final String DESERIALIZATION_TIME = " Deserialization Time";
	private static final String SERIALIZED_SIZE = " Serialized Size";

	private static final int width = 10;

	private final int[] lengthsTest = new int[] { 100, 1000, 10000 };
	private final int[] lengths = new int[] { 10, 100, 1000, 10000, 100000, 1000000, 10000000 };

	@Override
	protected String getSerializeSizeName() {
		return SERIALIZED_SIZE;
	}

	@Override
	protected String getDeserializationTimeName() {
		return DESERIALIZATION_TIME;
	}

	@Override
	protected String getSerializationTimeName() {
		return SERIALIZATION_TIME;
	}

	@Override
	protected String getName() {
		return NAME;
	}

	@Override
	protected double[][] getData(int width, int length) {
		return DataUtils.generateNumericData(width, length, Double.MIN_VALUE, Double.MAX_VALUE, false);
	}

	@Override
	protected int getWidth() {
		return width;
	}

	@Override
	protected int[] getLengthsTest() {
		return lengthsTest;
	}

	@Override
	protected int[] getList() {
		return lengths;
	}

	@Override
	protected Class<double[][]> getSerializedClass() {
		return double[][].class;
	}

}
