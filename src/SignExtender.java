public class SignExtender {
	public int [] extendData(int [] oldData){
		int [] extendedData = new int [32];
		int sign = oldData[0];
		for(int i=0; i<16;i++)
			extendedData[i+16] = oldData[i];
		for(int j=0; j<16; j++)
			extendedData[j] = sign;
		return extendedData;
	}
}