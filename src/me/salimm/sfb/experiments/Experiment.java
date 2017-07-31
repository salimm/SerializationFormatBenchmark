package me.salimm.sfb.experiments;

import java.util.List;

import me.salimm.sfb.formats.Serializer;

public abstract class Experiment {

	private boolean isTest;

	public Experiment(boolean isTest) {
		this.setTest(isTest);
	}

	public abstract List<ExperimentResult> run(List<Serializer> serializers);

	public boolean isTest() {
		return isTest;
	}

	public void setTest(boolean isTest) {
		this.isTest = isTest;
	}

}
