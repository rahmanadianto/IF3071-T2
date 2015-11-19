package classifier;

import java.util.ArrayList;

import weka.core.Instance;
import weka.core.Instances;
/**
 * *
 * @author Muhtar Hartopo
 * NIM : 13513068
 *
 */
public class KNNClassifier {
	private ArrayList<Instance> ListInstance;
	
	private int k = 5;
	public KNNClassifier() {
		ListInstance = new ArrayList<Instance>();
	}
	public KNNClassifier(Instances ins, int k) {
		ListInstance = getListInstance(ins);
		this.k = k;
	}
	public KNNClassifier(ArrayList<Instance> ins, int k) {
		ListInstance = ins;
		this.k = k;
	}
	//get class of instance ins
	public String getClass(Instance ins) {
		int size = ListInstance.size();
		DistanceTable DT = new DistanceTable(k);
		for(int i = 0; i < size; i++) {
			String className = ListInstance.get(i).toString(ListInstance.get(i).classIndex());
			int distance = getDistance(ListInstance.get(i),ins);
		
			DT.add(className, distance);
		}
		return DT.getMostClassFreq();
	}
	
	public int getDistance(Instance ins1, Instance ins2) {
		int classidx = ins1.classIndex();
		int dis = 0;
		for(int i = 0; i < classidx; i++) {
			String s1 = ins1.stringValue(i);
			String s2 = ins2.stringValue(i);
			if(s1.compareTo(s2) != 0) {
				dis++;
			}
		}
		return dis;
	}
	
	public ArrayList<Instance> getListInstance(Instances instances) {
		ArrayList<Instance> ListIns = new ArrayList<Instance>();
		for(int i = 0; i < instances.numInstances(); i++) {
			ListIns.add(instances.instance(i));
		}
		return ListIns;
	}
	
	public ArrayList<Instance> getData() {
		return ListInstance;
	}
	public void setListInstance(ArrayList<Instance> LI) {
		ListInstance = LI;
	}
	public void SetK(int _K) {
		this.k = _K;
	}
	


}
