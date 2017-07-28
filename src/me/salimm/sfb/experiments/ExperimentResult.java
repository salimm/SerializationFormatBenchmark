package me.salimm.sfb.experiments;

import me.salimm.sfb.formats.FormatType;

public class ExperimentResult {
	
	private String name;
	
	private double[] values;
	
	private int [] ranges;
	
	private FormatType formatType;
	
	
	public ExperimentResult(FormatType formatType){
		this.formatType = formatType;
		
	}
	

	public int [] getRanges() {
		return ranges;
	}

	public void setRanges(int [] ranges) {
		this.ranges = ranges;
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
	
	
	
	

}
