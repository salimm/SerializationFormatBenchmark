package me.salimm.sfb.experiments;

import java.util.List;

import me.salimm.sfb.formats.Serializer;

public interface Experiment {
	
	
	public List<ExperimentResult> run(List<Serializer> serializers);
	

	
}
