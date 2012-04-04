/**
 * 
 */
package mlfeatsel;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import mulan.data.MultiLabelInstances;
import weka.core.Instances;

/**
 * @author Miltiadis Allamanis
 * 
 */
public final class DatasetWritter {

	/**
	 * Write a dataset to a .arff file
	 * 
	 * @param dataset
	 *            the dataset instance
	 * @param filename
	 *            the path to save the new dataset
	 */
	public static void writeDataset(MultiLabelInstances dataset, String filename) {
		Instances dset = dataset.getDataSet();
		FileWriter fstream;
		try {
			fstream = new FileWriter(filename);
			BufferedWriter out = new BufferedWriter(fstream);
			out.write(dset.toString());
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
