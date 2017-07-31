package me.salimm.sfb;

import java.util.Random;

public class DataUtils {

	public static double[][] generateNumericData(int width, int length, double minRange, double maxRange,
			boolean round) {
		double span = maxRange - minRange;
		double[][] data = new double[width][length];
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < length; j++) {
				data[i][j] = Math.random() * span + minRange;
				if (round) {
					data[i][j] = data[i][j] * 100 / 100;
				}
			}
		}
		return data;
	}

	public static int[][] generateIntegerData(int width, int length, int minRange, int maxRange) {
		double span = maxRange - minRange;
		int[][] data = new int[width][length];
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < length; j++) {
				data[i][j] = (int) (Math.random() * span + minRange);
			}
		}
		return data;
	}

	public static String[][] generateStringData(int width, int length, int minRange, int maxRange) {
		int span = maxRange - minRange;
		String[][] data = new String[width][length];
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < length; j++) {
				int len = (int) (Math.random() * span + minRange);
				data[i][j] = getRandomString(len);
			}
		}
		return data;
	}

	public static String getRandomString(int length) {
		String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890 - ";
		StringBuilder salt = new StringBuilder();
		Random rnd = new Random();
		while (salt.length() < length) { // length of the random string.
			int index = (int) (rnd.nextFloat() * SALTCHARS.length());
			salt.append(SALTCHARS.charAt(index));
		}
		String saltStr = salt.toString();
		return saltStr;

	}
}
