package me.salimm.sfb;

public class DataUtils {

	
	public static  double[][] generateNumericData(int width, int length){
		double[][] data = new double[width][length];
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < length; j++) {
				data[i][j] = Math.random();
			}
		}
		return data;
	}
}
