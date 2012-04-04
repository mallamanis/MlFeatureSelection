/**
 * 
 */
package mlfeatsel;

import weka.core.Instances;

/**
 * Abstract class for selecting attributes by combining per label evaluations.
 * 
 * @author Miltiadis Allamanis
 * 
 */
public abstract class AbstractPerLabelAttributeEvaluator implements IDatasetFeatureEvaluator {

	private final IFeatureEvaluator baseAttributeEvaluator;

	private final PerLabelInstancePresenter dataset;

	/**
	 * Constructor
	 * 
	 * @param dataset
	 * @param baseEvaluator
	 */
	public AbstractPerLabelAttributeEvaluator(
			PerLabelInstancePresenter dataset, IFeatureEvaluator baseEvaluator) {
		this.dataset = dataset;
		this.baseAttributeEvaluator = baseEvaluator;
	}

	/* (non-Javadoc)
	 * @see mlfeatsel.IDatasetFeatureEvaluator#evaluateAttributes()
	 */
	@Override
	public abstract double[] evaluateAttributes() throws Exception;

	/**
	 * Evaluate all attributes to all labels.
	 * 
	 * @return a matrix containing attributes x labels evaluation
	 * @throws Exception
	 */
	protected double[][] evaluateAll2AllAttributes() throws Exception {
		final int numOfLabels = dataset.getNumberOfLabels();
		final int numOfAttributes = dataset.getNumberOfAttributes();
		double[][] evaluations = new double[numOfAttributes][numOfLabels];

		for (int lbl = 0; lbl < numOfLabels; lbl++) {
			final Instances currentDataset = dataset.getDatasetForLabel(lbl);
			for (int attr = 0; attr < numOfAttributes; attr++) {
				evaluations[attr][lbl] = baseAttributeEvaluator
						.evaluateFeature(currentDataset, attr);
			}
		}

		return evaluations;
	}
}
