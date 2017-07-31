package me.salimm.sfb.experiments;

import java.util.ArrayList;
import java.util.List;

import me.salimm.sfb.formats.Serializer;

public class ExperimentExecutor {

	private List<Experiment> experiments;

	private List<Serializer> serializers;

	private int attempts;

	public ExperimentExecutor(int attempts) {
		this.attempts = attempts;
		setExperiments(new ArrayList<Experiment>());
		this.serializers = new ArrayList<Serializer>();
	}


	public void init(){
		for (Serializer serializer : getSerializers()) {
			try {
				serializer.deserialize(serializer.serialize(new int[]{1,2,3,4,5,6,7,8,9,10}, int[].class), int[].class);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public List<ExperimentResult> run() {
		List<ExperimentResult> results = new ArrayList<ExperimentResult>();

		for (Experiment exp : getExperiments()) {
			results.addAll(exp.run(serializers,attempts));
		}

		return results;
	}

	public List<Experiment> getExperiments() {
		return experiments;
	}

	public void setExperiments(List<Experiment> experiments) {
		this.experiments = experiments;
	}

	public List<Serializer> getSerializers() {
		return serializers;
	}

	public void setSerializers(List<Serializer> serializers) {
		this.serializers = serializers;
	}

}
