/**
 * 
 */
package mlfeatsel;

import mlfeatsel.featureevaluators.ChiSqFeatureEvaluator;
import mlfeatsel.perlabelevaluators.MeanPerLabelAttributeEvaluator;
import mulan.classifier.meta.RAkEL;
import mulan.classifier.transformation.LabelPowerset;
import mulan.data.MultiLabelInstances;
import mulan.evaluation.Evaluator;
import mulan.evaluation.MultipleEvaluation;
import weka.classifiers.trees.J48;

/**
 * @author miltiadis
 * 
 */
public class ExperimentRun {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {

		String arffFilename = "/home/miltiadis/Desktop/dataset/genbase.arff";
		String xmlFilename = "/home/miltiadis/Desktop/dataset/genbase.xml";

		PerLabelInstancePresenter a = new PerLabelInstancePresenter(
				arffFilename, xmlFilename);

		ChiSqFeatureEvaluator featEval = new ChiSqFeatureEvaluator();
		MeanPerLabelAttributeEvaluator b = new MeanPerLabelAttributeEvaluator(
				a, featEval);
		FeatureSelector c = new FeatureSelector(a.getDataset(), b);
		for (int feat = 1185; feat > 100; feat -= 60) {
			MultiLabelInstances d = c.selectFeatures(feat);
			RAkEL learner1 = new RAkEL(new LabelPowerset(new J48()));
			Evaluator eval = new Evaluator();
			MultipleEvaluation results = eval.crossValidate(learner1, d, 10);
			System.out.println("--------------------------" + feat
					+ "-----------------------");
			System.out.println(results);
		}
	}
}
