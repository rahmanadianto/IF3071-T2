package classifier;

import java.util.ArrayList;

import weka.core.Instance;

public class CrossValidation {
	private KNNClassifier kc;
	private int fold;
	private boolean result[];
	private int foldTable[];
	private int numData;
	private int nTrue;
	private int nFalse;
	public CrossValidation(KNNClassifier kc, int fold) {
		this.kc = kc;
		this.fold = fold;
		createFoldTable();
		result = new boolean[numData];
	}
	public void test() {
		
		int residx = 0;
		ArrayList<Instance> temp = kc.getData();
		int classidx = temp.get(0).classIndex();
		for(int i = 1; i <= fold; i++) {
			ArrayList<Instance> test = new ArrayList<Instance>();
			ArrayList<Instance> training  = new ArrayList<Instance>();
			//add to test, remove from training
			for(int j = 0; j < numData; j++) {
				if(foldTable[j] == i) {
					test.add(temp.get(j));
				}else {
					training.add(temp.get(j));
				}
			}
			kc.setListInstance(training);
			for(int j = 0; j < test.size(); j++) {
				String className = test.get(j).toString(classidx);
				String classRes = kc.getClass(test.get(j));

				result[residx] = (className.compareTo(classRes) == 0);
				residx++;
			}
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
		kc.setListInstance(temp);
	}
	public int getTrue() {
		return nTrue;
	}
	public int getFalse() {
		return nFalse;
	}
	
	public void createFoldTable() {
		numData = kc.getData().size();
		int dataPerFold = numData/fold;
		int remain = numData % fold;
		foldTable = new int[numData];
		//inisialisasi fold table
		int k = remain;
		int sred = (fold - remain)/2;
		int numFold = 1;
		for(int i = 0; i < numData; i++) {
			for(int j = 0; j < dataPerFold; j++) {
				foldTable[i] = numFold;
				if(k>0) i++;
			}
			if(k > 0 && i > sred) {
				foldTable[i] = numFold;
				k--;
			}
			numFold++;
		}
	}	
		
	public int getnumData() {
		return numData;
	}	
	
}
