package me.salimm.sfb.experiments;

import java.util.ArrayList;
import java.util.List;

import me.salimm.sfb.formats.Serializer;

public class ExperimentExecutor {

	private List<Experiment> experiments;

	private List<Serializer> serializers;

	public ExperimentExecutor() {
		setExperiments(new ArrayList<Experiment>());
	}

	public ExperimentExecutor(List<Experiment> experimnets) {
		setExperiments(experimnets);

	}

	public List<ExperimentResult> run() {
		List<ExperimentResult> results = new ArrayList<ExperimentResult>();

		for (Experiment exp : getExperiments()) {
			results.addAll(exp.run(serializers));
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
