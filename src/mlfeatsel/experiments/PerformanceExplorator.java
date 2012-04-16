/**
 * 
 */
package mlfeatsel.experiments;

import mlfeatsel.FeatureSelector;
import mulan.data.MultiLabelInstances;
import mulan.evaluation.MultipleEvaluation;

/**
 * Explore the performance of a classifier for different Feature Selections
 * 
 * @author Miltiadis Allamanis
 * 
 */
public class PerformanceExplorator {

	/**
	 * An abstract class to any classifier being explored
	 * 
	 * @author Miltiadis Allamanis
	 * 
	 */
	public abstract class ClassifierEvaluation {
		/**
		 * An interface for classifiers returning their performance
		 * 
		 * @param selector
		 *            a concrete feature selector, used to filter the train/test
		 *            set
		 * @return a Mulan Evaluation describing the results
		 */
		public abstract MultipleEvaluation getEvaluationFor(
				ConcreteFeatureSelector selector);

	}

	/**
	 * A Feature Selector for a specific selection
	 * 
	 * @author Miltiadis Allamanis
	 * 
	 */
	public class ConcreteFeatureSelector {
		/**
		 * The number of features that will be included in the final dataset.
		 */
		private final int nFeatures;

		/**
		 * Constructor. Can only be invoked in this class
		 * 
		 * @param numOfFeatures
		 *            the number of features to be included in the final dataset
		 */
		private ConcreteFeatureSelector(int numOfFeatures) {
			nFeatures = numOfFeatures;
		}

		/**
		 * Select features according to the parameters in this object.
		 * 
		 * @param dataset
		 *            the dataset to select features from
		 * @return a MultiLabelInstance containing the feature selected dataset
		 * @throws Exception
		 */
		public MultiLabelInstances featureSelect(MultiLabelInstances dataset)
				throws Exception {
			return featureSelector.selectFeatures(nFeatures, dataset);
		}
	}

	/**
	 * A percentages array for every 10% of the dataset size.
	 */
	public final static double[] EVERY_10 = { .1, .2, .3, .4, .5, .6, .7, .8,
			.9, 1 };

	/**
	 * A percentages array for every 20% of the dataset size.
	 */
	public final static double[] EVERY_20 = { .2, .4, .6, .8, 1 };

	/**
	 * A percentages array for every 10% of the dataset size but only the top
	 * 50%.
	 */
	public final static double[] EVERY_10_TOP50 = { .5, .6, .7, .8, .9, 1 };
	/**
	 * The feature selector being evaluated
	 */
	private final FeatureSelector featureSelector;

	/**
	 * The classifier to be used for exloration.
	 */
	private ClassifierEvaluation classifier;

	/**
	 * Constructor
	 * 
	 * @param selector
	 *            the feature selector to be used for exploration.
	 * @param evalClassifier
	 *            the classifier to be used for evaluation
	 */
	public PerformanceExplorator(FeatureSelector selector) {
		this.featureSelector = selector;
	}

	/**
	 * Perform an exploration for various percentages of feature points.
	 * 
	 * @param percentages
	 *            a double[] containing the percentage points of dataset size
	 * @return a Mulan Evaluation[] with the evaluation for the given
	 *         percentages
	 */
	public MultipleEvaluation[] explorePerformanceFor(double[] percentages) {
		MultipleEvaluation[] results = new MultipleEvaluation[percentages.length];
		for (int i = 0; i < percentages.length; i++) {
			final int featureSetSize = (int) (featureSelector
					.getNumberOfAttributes() * percentages[i]);
			results[i] = classifier
					.getEvaluationFor(new ConcreteFeatureSelector(
							featureSetSize));
		}

		return results;
	}

	/**
	 * Set the classifier evaluator.
	 * 
	 * @param classifierEvaluation
	 */
	public void setClassifierEvaluator(ClassifierEvaluation classifierEvaluation) {
		this.classifier = classifierEvaluation;
	}
}
