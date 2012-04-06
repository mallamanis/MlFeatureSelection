/**
 * 
 */
package mlfeatsel.featureevaluators;

import mlfeatsel.IFeatureEvaluator;
import weka.core.Instance;
import weka.core.Instances;

/**
 * A chi^2 feature evaluator.
 * 
 * @author Miltiadis Allamanis
 * 
 */
public class ChiSqFeatureEvaluator implements IFeatureEvaluator {

	/**
	 * Cut terms with freq. less than this number.
	 */
	private final int cutoffThreshold;

	/**
	 * Default constructor.
	 */
	public ChiSqFeatureEvaluator() {
		cutoffThreshold = 0;
	}

	/**
	 * Constructor
	 * 
	 * @param cutoff
	 *            the least possible feature frequency, before cutoff
	 */
	public ChiSqFeatureEvaluator(int cutoff) {
		cutoffThreshold = cutoff;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see mlfeatsel.IFeatureEvaluator#evaluateFeature(weka.core.Instances,
	 * int)
	 */
	@Override
	public double evaluateFeature(Instances dataset, int attributeIndex)
			throws Exception {
		if (!dataset.attribute(attributeIndex).isNominal())
			throw new Exception("Attributes not binary");

		final double N = dataset.numInstances();
		double A = 0; // f=1, l=1
		double B = 0; // f=1, l=0
		double C = 0; // f=0, l=1
		double D = 0; // f=0, l=0

		for (int instance = 0; instance < N; instance++) {
			Instance currentInst = dataset.get(instance);
			double attributeValue = currentInst.value(attributeIndex);
			if ((Double.compare(attributeValue, 0) != 0)
					&& (Double.compare(attributeValue, 1) != 0))
				throw new Exception("Attributes not binary");
			double labelValue = currentInst.classValue();
			if ((Double.compare(labelValue, 0) != 0)
					&& (Double.compare(labelValue, 1) != 0))
				throw new Exception("Label not binary");

			if (Double.compare(attributeValue, 1.) == 0) {
				if (Double.compare(labelValue, 1.) == 0)
					A++;
				else
					B++;
			} else {
				if (Double.compare(labelValue, 0.) == 0)
					D++;
				else
					C++;
			}

		}

		if (A < this.cutoffThreshold)
			return 0;

		double nominator = Math.pow((A * D) - (C * B), 2);
		double denominator = (A + C) * (B + D) * (A + B) * (C + D);

		return nominator / denominator;
	}
}
