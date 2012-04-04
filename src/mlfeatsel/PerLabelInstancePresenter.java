/**
 * 
 */
package mlfeatsel;

import mulan.data.InvalidDataFormatException;
import mulan.data.MultiLabelInstances;
import mulan.transformations.BinaryRelevanceTransformation;
import weka.core.Instances;

/**
 * Load a multi-label dataset and retrieve each one per label.
 * 
 * @author Miltiadis Allamanis
 * 
 */
public class PerLabelInstancePresenter {

	/**
	 * The ml dataset.
	 */
	private MultiLabelInstances dataset;

	private BinaryRelevanceTransformation perLabelTransformation;

	/**
	 * Construct object.
	 * 
	 * @param arffFilename
	 * @param xmlFilename
	 * @throws InvalidDataFormatException
	 */
	public PerLabelInstancePresenter(final String arffFilename,
			final String xmlFilename) throws InvalidDataFormatException {
		this.loadDataset(arffFilename, xmlFilename);
	}

	public final MultiLabelInstances getDataset() {
		return dataset;
	}

	/**
	 * Return a Weka Instances containing only the given label.
	 * 
	 * @param labelToKeep
	 * @return a Weka Instances containing only the given label
	 * @throws Exception
	 */
	public final Instances getDatasetForLabel(int labelToKeep) throws Exception {
		return perLabelTransformation.transformInstances(dataset.getDataSet(),
				labelToKeep);
	}

	/**
	 * 
	 * @return the number of attributes
	 */
	public final int getNumberOfAttributes() {
		return dataset.getFeatureIndices().length;
	}

	/**
	 * 
	 * @return the number of labels in the dataset
	 */
	public final int getNumberOfLabels() {
		return dataset.getNumLabels();
	}

	/**
	 * Load a multilabel dataset.
	 * 
	 * @param arffFilename
	 * @param xmlFilename
	 * @throws InvalidDataFormatException
	 */
	protected final void loadDataset(final String arffFilename,
			final String xmlFilename) throws InvalidDataFormatException {
		dataset = new MultiLabelInstances(arffFilename, xmlFilename);
		perLabelTransformation = new BinaryRelevanceTransformation(
				dataset.getNumLabels());
	}
}
