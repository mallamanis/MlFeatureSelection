/**
 * 
 */
package mlfeatsel;

import java.util.Arrays;

import mulan.data.InvalidDataFormatException;
import mulan.data.MultiLabelInstances;
import weka.core.Instances;

/**
 * Abstract Class Transform multilabel instances
 * 
 * @author Miltiadis Allamanis
 * 
 */
public class FeatureSelector {

	final MultiLabelInstances dataset;
	final IDatasetFeatureEvaluator evaluator;
	final double[] evals;

	/**
	 * Constructor
	 * 
	 * @param initialDataset
	 *            the initial dataset
	 * @param featureEval
	 *            an evaluator for all features
	 * @throws Exception
	 */
	public FeatureSelector(MultiLabelInstances initialDataset,
			IDatasetFeatureEvaluator featureEval) throws Exception {
		this.dataset = initialDataset;
		this.evaluator = featureEval;

		evals = evaluator.evaluateAttributes();
	}

	/**
	 * 
	 * @return the total number of attributes in the initial dataset
	 */
	public final int getNumberOfAttributes() {
		return evals.length;
	}

	/**
	 * Select features from the inital dataset
	 * 
	 * @param numOfFeaturesToRemain
	 *            the number of features to keep
	 * @return a MultiLabelInstances with the selected features
	 * @throws Exception
	 */
	public MultiLabelInstances selectFeatures(int numOfFeaturesToRemain)
			throws Exception {
		return this.selectFeatures(numOfFeaturesToRemain, this.dataset);
	}

	/**
	 * Select features given a policy from a dataset
	 * 
	 * @param numOfFeaturesToRemain
	 *            the number of features to keep
	 * @param initialDataset
	 *            the dataset to select the features from
	 * @return a MultiLabelInstances with the selected features
	 * @throws Exception
	 */
	public MultiLabelInstances selectFeatures(int numOfFeaturesToRemain,
			MultiLabelInstances initialDataset) throws Exception {

		// Get evaluation threshold
		final double[] sorted = Arrays.copyOf(evals, evals.length);
		Arrays.sort(sorted);
		double threshold = sorted[sorted.length - numOfFeaturesToRemain];

		// Get Indices of attributes to remove
		int[] attrsToRemove = new int[evals.length - numOfFeaturesToRemain];
		int currentIndex = 0;
		for (int i = 0; i < evals.length; i++) {
			if (evals[i] < threshold) {
				attrsToRemove[currentIndex] = i;
				currentIndex++;
			}
		}

		return this.removeFeatures(attrsToRemove, initialDataset);

	}

	/**
	 * Remove the features from the given attribute indices
	 * 
	 * @param indicesToRemove
	 *            the list of features indices to remove
	 * @param initialDataset
	 *            the dataset to remove the features from
	 * @return the MultiLabelInstances after feature removal
	 * @throws InvalidDataFormatException
	 */
	protected MultiLabelInstances removeFeatures(int[] indicesToRemove,
			MultiLabelInstances initialDataset)
			throws InvalidDataFormatException {
		MultiLabelInstances resultingDset = initialDataset.clone();
		final Instances dset = resultingDset.getDataSet();
		Arrays.sort(indicesToRemove);
		for (int i = indicesToRemove.length - 1; i >= 0; i--)
			dset.deleteAttributeAt(indicesToRemove[i]);

		resultingDset.reintegrateModifiedDataSet(dset);
		return resultingDset;
	}

}
