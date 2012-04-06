/**
 * 
 */
package mlfeatsel.metricstatistics;

import java.util.Arrays;

import mlfeatsel.AbstractPerLabelAttributeEvaluator;
import mlfeatsel.PerLabelInstancePresenter;
import mlfeatsel.featureevaluators.InfoGainFeatureEvaluator;
import mlfeatsel.perlabelevaluators.MaxPerLabelAttributeEvaluator;

/**
 * Calculate the total per label (for all features)
 * 
 * @author Miltiadis Allamanis
 * 
 */
public class TotalMetricPerLabelStatistics {
	public static void main(String[] args) throws Exception {
		String arffFilename = "/home/miltiadis/Desktop/dataset/genbase.arff";
		String xmlFilename = "/home/miltiadis/Desktop/dataset/genbase.xml";

		PerLabelInstancePresenter a = new PerLabelInstancePresenter(
				arffFilename, xmlFilename);

		InfoGainFeatureEvaluator featEval = new InfoGainFeatureEvaluator();
		MaxPerLabelAttributeEvaluator b = new MaxPerLabelAttributeEvaluator(a,
				featEval); // use as dummy

		TotalMetricPerLabelStatistics stat = new TotalMetricPerLabelStatistics(
				b);

		double[] res = stat.evaluatePerLabel();
		System.out.println(Arrays.toString(res));
		double sum = 0;
		for (int i = 0; i < res.length; i++) {
			sum += res[i];
		}
		System.out.println("Sum" + sum);
		System.out.println("Avg" + (sum / res.length));
	}

	/**
	 * The feature evaluation method.
	 */
	private final AbstractPerLabelAttributeEvaluator featEval;

	/**
	 * Constructor
	 * 
	 * @param dataset
	 * @param featureEvaluator
	 */
	public TotalMetricPerLabelStatistics(
			AbstractPerLabelAttributeEvaluator featureEvaluator) {
		featEval = featureEvaluator;

	}

	/**
	 * 
	 * @return the total metric per label
	 * @throws Exception
	 */
	public double[] evaluatePerLabel() throws Exception {
		final int nLabels = featEval.getDataset().getNumLabels();
		double[] result = new double[nLabels];
		Arrays.fill(result, 0);
		double[][] all2all = featEval.evaluateAll2AllAttributes();
		for (int i = 0; i < all2all.length; i++) {
			for (int lbl = 0; lbl < nLabels; lbl++)
				if (!Double.isNaN(all2all[i][lbl]))
					result[lbl] += all2all[i][lbl];
		}
		return result;
	}
}
