package me.salimm.sfb.experiments;



import me.salimm.sfb.DataUtils;

public class BigDataStringExperiment extends MatrixBasedExperiment<String[][]> {

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
	protected String[][] getData(int width, int length) {
		String[][] data = DataUtils.generateStringData(width, length, 3, 15);
		return data;
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

}
