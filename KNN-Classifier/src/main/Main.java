package main;
import classifier.DataReader;
import classifier.KNNClassifier;
import weka.core.Instance;
/**
 * *
 * @author Muhtar Hartopo
 * NIM : 13513068
 *
 */

public class Main {
	public static void main (String[] args) throws Exception {
		//baca data
		DataReader dr = new DataReader("weather.nominal.arff");
		//buat kelas KNN CLassifier, gunakan K = 5
		KNNClassifier kn = new KNNClassifier(dr.getData(),5); 
		/*
		 * Coba melakukan klasifikasi
		 * Instans yang diambil adalah instans pertama dari data
		 * */
		Instance Ins = dr.getData().instance(0);
		//menentukan nama kelasnya
		String className = kn.getClass(Ins);
		System.out.println(className);
	}

}
