package classifier;
/**
 * *
 * @author Muhtar Hartopo
 * NIM : 13513068
 *
 */
public class DistanceTable {
	private DistElem dist[];
	private int size;
	private int idxAv;
	public DistanceTable(int size) {
		setDist(new DistElem[size+1]);
		idxAv = 0;
		this.setSize(size);
		for(int i = 0; i < size+1; i++) {
			dist[i] = new DistElem();
		}
	}
	public void add(String classN, int dis) {
		if (idxAv == 0) {
			dist[0].className = classN;
			dist[0].distance = dis;
			
		} else {
			int i = idxAv-1;
			boolean find = dis > dist[i].distance;
			boolean fl = !find;
			while (i >= 0 && !find) {
				dist[i+1].className = dist[i].className;
				dist[i+1].distance = dist[i].distance;
				i--;
				fl = true;
				if(i >= 0) {
					find = dis > dist[i].distance;	
				} else {
					find = true;
					
				}	
			}
			if (fl) {
				dist[i+1].className = classN;
				dist[i+1].distance = dis;
				
			}
			
			
		}
		if (idxAv < size) {
			idxAv++;
		}
		
		
		
	}
	
	public String getMostClassFreq() {
		classFreq cf[] = new classFreq[size];
		int last = 0;
		//cari apakah ada dalam kelas
		for(int a = 0; a < size; a++) {
			int i = 0;
			while(i < last && (dist[a].className).compareTo(cf[i].className) != 0) {
				i++;
			}
			if(i == last && dist[a].className.compareTo("None")!= 0) {
				cf[i] = new classFreq( dist[a].className, 1);
				last++;
			} else {
				cf[i].freq++;
			}
		}
		classFreq cfmax = new classFreq();
		cfmax.className = "";
		cfmax.freq = 0;
		for(int i = 0; i < last; i++) {	
			if(cf[i].freq > cfmax.freq) {
				cfmax.freq = cf[i].freq;
				cfmax.className = cf[i].className;
			}
		}
		
		return cfmax.className;
	}
	
	public DistElem[] getDist() {
		return dist;
	}

	public void setDist(DistElem dist[]) {
		this.dist = dist;
	}

	public int getSize() {
		return size;
	}


	public void setSize(int size) {
		this.size = size;
	}
	@Override
	public String toString() {
		String S = "";
		for(int i = 0 ; i < size; i++) {
			S += "Class name : " + dist[i].className + ", distance : " + Integer.toString(dist[i].distance) + "\n";
		}
		return S;
	}
	public class DistElem {
		public String className;
		public int distance;
		public DistElem() {
			className = "None";
			distance = 999;
		}
	}
	private class classFreq {
		public String className;
		public int freq;
		public classFreq() {
			className = "";
			freq = 0;
		}
		public classFreq(String cn, int f) {
			className = cn;
			freq = f;
		}
	}
	
}

