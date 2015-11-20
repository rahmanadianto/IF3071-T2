package classifier;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import weka.core.Instance;
/**
 * *
 * @author Muhtar Hartopo
 * NIM : 13513068
 *
 */

public class FullTrainingKNN {
	private KNNClassifier kc;
	private boolean result[];
	private int numData;
	private int nTrue;
	private int nFalse;
	public FullTrainingKNN(KNNClassifier kc) {
		this.kc = kc;
		numData = kc.getData().size();
		result = new boolean[numData];
	}
	public void test() {
		
		int classidx = kc.getData().get(0).classIndex();
		for(int i = 0 ; i < numData; i++) {
			Instance ins =  kc.getData().get(i);
			String classRes = kc.getClass(ins);
			String className = ins.toString(classidx);
			result[i]= className.compareTo(classRes) == 0;
		}
		
		int t =0, f=0;
		for(int i = 0; i < numData; i++) {
			if(result[i]) {
				t++;
			} else {
				f++;
			}
		}
		nTrue = t;
		nFalse = f;
	}
	public int getTrue() {
		return nTrue;
	}
	public int getFalse() {
		return nFalse;
	}
		
	public int getnumData() {
		return numData;
	}
	public void writeModel() {
		double percT = (double) nTrue*100/numData;
		double percF = (double) nFalse*100/numData;
		String S ="K-NN Classification Full Training\n";
		S += "K = " + Integer.toString(kc.getK()) + "\n"
				+ "Jumlah instans = " + Integer.toString(numData) + "\n"
				+ "Jumlah benar = " + Integer.toString(nTrue)+"\n"
				+ "Jumlah salah = " + Integer.toString(nFalse)+ "\n"
				+ "Persentase kebenaran = " + Double.toString(percT) + "\n"
				+ "Persentase kesalahan = " + Double.toString(percF) + "\n";
		ArrayList<Instance> instances = kc.getData();
		S += "\nData\n";
		for(int i = 0; i < numData; i++) {
			S+= instances.get(i).toString() + "\n";
		}
		writeFile(S,"K-NN model.txt");
	}
	
	private void writeFile(String content, String fileName) {
		try {

			File file = new File(fileName);
			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(content);
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	
}
