package main;

import classifier.*;
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
		//buat kelas KNN CLassifier, gunakan K = 3
		KNNClassifier kn = new KNNClassifier(dr.getData(),3); 
		
		//cross validation
		CrossValidation cv = new CrossValidation(kn, 10);
		cv.test();
		System.out.println("10 fold cross validation");
		System.out.print("Jumlah benar = ");
		System.out.println(cv.getTrue());
		double percentT = (double) cv.getTrue() / cv.getnumData();
		System.out.print("Persentase benar = ");
		System.out.println(percentT);
		System.out.print("Jumlah salah = ");
		System.out.println(cv.getFalse());
		double percentF = (double) cv.getFalse() / cv.getnumData();
		System.out.print("Persentase salah = ");
		System.out.println(percentF);
		
		//Full training
		System.out.println("\nFull training");
		FullTrainingKNN ftk = new FullTrainingKNN(kn);
		ftk.test();
		System.out.print("Jumlah benar = ");
		System.out.println(ftk.getTrue());
		percentT = (double) ftk.getTrue() / ftk.getnumData();
		System.out.print("Persentase benar = ");
		System.out.println(percentT);
		System.out.print("Jumlah salah = ");
		System.out.println(ftk.getFalse());
		percentF = (double) ftk.getFalse() / ftk.getnumData();
		System.out.print("Persentase salah = ");
		System.out.println(percentF);
		ftk.writeModel();
	}

}
