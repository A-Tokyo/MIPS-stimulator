public class DataMemory {
	private int[][] data = new int [1000000] [32];
	private int memWrite;
	private int memRead;

	public void writeData(int[] pc, int[] dataInput) {
		if(memWrite != 1) return;
		int x = 0;
		int val=0;
		for (int i = 0; i < pc.length; i++)
			x += ((int) Math.pow(2, pc.length - 1 - i)) * pc[i];
		for (int i=0; i<dataInput.length; i++)
			val += ((int) Math.pow(2,dataInput.length - 1 - i)) * dataInput[i];
		for (int i = 0; i < data[x].length; i++)
			data[x][i] = dataInput[i];
		System.out.println("The value in the memory address "+x+" is now: "+val);
	}

	public int[] readData(int[] pc) {
		if(memRead !=1)return null;
		int x = 0;
		for (int i = 0; i < pc.length; i++)
			x += ((int) Math.pow(2, pc.length - 1 - i)) * pc[i];
		int res [] = data[x];
		int val=0;
		for (int i=0; i<res.length; i++)
			val += ((int) Math.pow(2,res.length - 1 - i)) * res[i];
		System.out.println("The value in the memory address "+x+" is: "+val);
		return res;
	}

	public int[][] getData() {
		return data;
	}

	public void setData(int[][] data) {
		this.data = data;
	}

	public int getMemWrite() {
		return memWrite;
	}

	public void setMemWrite(int memWrite) {
		this.memWrite = memWrite;
	}

	public int getMemRead() {
		return memRead;
	}

	public void setMemRead(int memRead) {
		this.memRead = memRead;
	}
}
