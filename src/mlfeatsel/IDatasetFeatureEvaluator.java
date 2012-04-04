package mlfeatsel;

/**
 * An interface for all dataset feature evaluators
 * 
 * @author Miltiadis Allamanis
 * 
 */
public interface IDatasetFeatureEvaluator {

	/**
	 * Evaluate all attributes
	 * 
	 * @return a double[] containing the final attribute evaluations
	 * @throws Exception
	 */
	public abstract double[] evaluateAttributes() throws Exception;

}