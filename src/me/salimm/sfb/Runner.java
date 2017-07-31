package me.salimm.sfb;

import java.util.List;

import me.salimm.sfb.experiments.ExperimentExecutor;
import me.salimm.sfb.experiments.ExperimentResult;
import me.salimm.sfb.formats.AVROSerializer;
import me.salimm.sfb.formats.IonSerializer;
import me.salimm.sfb.formats.ProtocolBufferSerializer;
import me.salimm.sfb.experiments.BigDataNormalizedNumbericDataExperiment;

public class Runner {

	public static final String RESULTS_DIR = "results.";

	public static void main(String[] args) {

		DataUtils.generateNumericData(100, 2000000);
		// creating executor
		ExperimentExecutor exec = new ExperimentExecutor();

		// adding experiments
		exec.getExperiments().add(new BigDataNormalizedNumbericDataExperiment());

		// adding serializers
		// exec.getSerializers().add(new JSONSerializer());
		// exec.getSerializers().add(new SmileSerializer());
		// exec.getSerializers().add(new BSONSerializer());
		// exec.getSerializers().add(new CBORSerializer());
		exec.getSerializers().add(new ProtocolBufferSerializer());
		// exec.getSerializers().add(new AVROSerializer());
		// exec.getSerializers().add(new IonSerializer());
		// exec.getSerializers().add(new MsgPackSerializer());

		exec.init();
		// executing
		List<ExperimentResult> results = exec.run();

		// generating graphs and outputs
		for (ExperimentResult result : results) {
			System.out.println(result);
		}

	}

}
