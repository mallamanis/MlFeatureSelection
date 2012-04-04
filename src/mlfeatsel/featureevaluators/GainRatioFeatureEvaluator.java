/**
 * 
 */
package mlfeatsel.featureevaluators;

import mlfeatsel.IFeatureEvaluator;
import weka.attributeSelection.GainRatioAttributeEval;
import weka.core.Instances;

/**
 * Wrapper around Weka's Feature Evaluator
 * 
 * @author Miltiadis Allamanis
 * 
 */
public class GainRatioFeatureEvaluator implements IFeatureEvaluator {

	private GainRatioAttributeEval eval;

	@Override
	public double evaluateFeature(Instances dataset, int attributeIndex)
			throws Exception {
		eval = new GainRatioAttributeEval();
		eval.buildEvaluator(dataset);
		return eval.evaluateAttribute(attributeIndex);
	}
}
