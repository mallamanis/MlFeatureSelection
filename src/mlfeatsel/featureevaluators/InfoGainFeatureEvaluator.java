/**
 * 
 */
package mlfeatsel.featureevaluators;

import mlfeatsel.IFeatureEvaluator;
import weka.attributeSelection.InfoGainAttributeEval;
import weka.core.Instances;

/**
 * Evaluate Attributes based on Information Gain
 * 
 * @author Miltiadis Allamanis
 * 
 */
public class InfoGainFeatureEvaluator implements IFeatureEvaluator {

	private InfoGainAttributeEval eval;

	/*
	 * (non-Javadoc)
	 * 
	 * @see mlfeatsel.IFeatureEvaluator#evaluateFeature(weka.core.Instances,
	 * int)
	 */
	@Override
	public double evaluateFeature(Instances dataset, int attributeIndex)
			throws Exception {
		eval = new InfoGainAttributeEval();
		eval.buildEvaluator(dataset);
		return eval.evaluateAttribute(attributeIndex);
	}

}
