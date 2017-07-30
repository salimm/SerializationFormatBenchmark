package me.salimm.sfb.experiments;

import me.salimm.sfb.formats.FormatType;

public class ExperimentResult {

	private String name;

	private double[] values;

	private double[] ranges;

	private FormatType formatType;

	public ExperimentResult(FormatType formatType) {
		this.formatType = formatType;

	}

	public double[] getRanges() {
		return ranges;
	}

	public void setRanges(double[] ranges) {
		this.ranges = ranges;
	}

	public void setRanges(int[] ranges) {
		this.ranges = new double[ranges.length];
		for (int i = 0; i < ranges.length; i++) {
			this.ranges[i] = ranges[i];
		}
	}

	public double[] getValues() {
		return values;
	}

	public void setValues(double[] values) {
		this.values = values;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public FormatType getFormatType() {
		return formatType;
	}

	public void setFormatType(FormatType formatType) {
		this.formatType = formatType;
	}

	@Override
	public String toString() {
		String tmpVals = "[";
		for (double d : values) {
			String post= "";
			if(d>1024){
				d=d/1024;
				post="K";
			}
			tmpVals+= d+post+", ";
		}
		tmpVals += " ]";
		
		String tmpRanges = "[";
		for (double d : ranges) {
			String post= "";
			if(d>1024){
				d=d/1024;
				post="K";
			}
			tmpRanges+= d+post+", ";
		}
		tmpRanges += " ]";
		return "{ExperimentResult expName:'" + name + "',  formatType:'" + formatType + "', renges:"
				+ tmpRanges+ ",  values:" + tmpVals+ "}";
	}

}
