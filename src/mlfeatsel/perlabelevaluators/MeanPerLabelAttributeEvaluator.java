/**
 * 
 */
package mlfeatsel.perlabelevaluators;

import mlfeatsel.AbstractPerLabelAttributeEvaluator;
import mlfeatsel.IFeatureEvaluator;
import mlfeatsel.PerLabelInstancePresenter;

/**
 * Evaluate each label to the mean of all labels.
 * 
 * @author Miltiadis Allamanis
 * 
 */
public class MeanPerLabelAttributeEvaluator extends
		AbstractPerLabelAttributeEvaluator {

	/**
	 * @param dataset
	 * @param baseEvaluator
	 */
	public MeanPerLabelAttributeEvaluator(PerLabelInstancePresenter dataset,
			IFeatureEvaluator baseEvaluator) {
		super(dataset, baseEvaluator);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mlfeatsel.AbstractPerLabelAttributeEvaluator#evaluateAttributes()
	 */
	@Override
	public double[] evaluateAttributes() throws Exception {
		double[][] evals = this.evaluateAll2AllAttributes();
		double[] finalEvals = new double[evals.length];

		for (int attr = 0; attr < evals.length; attr++) {
			double sum = 0;
			for (int lbl = 0; lbl < evals[attr].length; lbl++) {
				sum += evals[attr][lbl];
			}
			finalEvals[attr] = sum / evals[attr].length;
		}
		return finalEvals;
	}

}
