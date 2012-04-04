/**
 * 
 */
package mlfeatsel;

import weka.core.Instances;

/**
 * Interface for evaluating a feature of a multi-class Instances
 * 
 * @author Miltiadis Allamanis
 * 
 */
public interface IFeatureEvaluator {
	/**
	 * Take a multi-class dataset and evaluate the given attribute
	 * 
	 * @param dataset
	 * @param attributeIndex
	 * @return
	 * @throws Exception
	 */
	public abstract double evaluateFeature(final Instances dataset,
			final int attributeIndex) throws Exception;

}
