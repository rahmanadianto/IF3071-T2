package classifier;

import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
/**
 * *
 * @author Muhtar Hartopo
 * NIM : 13513068
 *
 */
public class DataReader {
	private Instances data;
	public DataReader(String fileName) throws Exception {
		DataSource source = new DataSource(fileName);
		data = source.getDataSet();
		data.setClassIndex(data.numAttributes() - 1);
	}
	public Instances getData() {
		return data;
	}
	public void setData(Instances data) {
		this.data = data;
	}
}
