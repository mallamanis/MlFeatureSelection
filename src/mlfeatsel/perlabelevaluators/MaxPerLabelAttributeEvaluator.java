/**
 * 
 */
package mlfeatsel.perlabelevaluators;

import mlfeatsel.AbstractPerLabelAttributeEvaluator;
import mlfeatsel.IFeatureEvaluator;
import mlfeatsel.PerLabelInstancePresenter;

/**
 * Evaluate all attributes by getting the max value of all labels
 * 
 * @author Miltiadis Allamanis
 * 
 */
public class MaxPerLabelAttributeEvaluator extends
		AbstractPerLabelAttributeEvaluator {

	/**
	 * @param dataset
	 * @param baseEvaluator
	 */
	public MaxPerLabelAttributeEvaluator(PerLabelInstancePresenter dataset,
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
			double max = 0;
			for (int lbl = 0; lbl < evals[attr].length; lbl++) {
				if (evals[attr][lbl] > max)
					max = evals[attr][lbl];
			}
			finalEvals[attr] = max;
		}
		return finalEvals;
	}

}
