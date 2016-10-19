public class Multiplixer {
	private int [] [] data;

	public Multiplixer(int selectionLinesNo, int multiplixerSize){
		data = new int[(int) Math.pow(2,selectionLinesNo)] [multiplixerSize];
	}

	public int[] getData(int[] selectionLines){
		int selection =0;
		for(int i=0;i<selectionLines.length;i++)
			selection += ((int) Math.pow(2,selectionLines.length-1-i))*selectionLines[i];
		return data[selection];
	}

	public void insertData(int [] data, int pos){
		this.data[pos] = data;
	}
}