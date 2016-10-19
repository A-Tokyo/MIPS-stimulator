public class Shifter {
	public static int[] shiftby2(int[] oldData) {
		int[] shiftedData = new int[32];
		for (int i = 2; i < 32; i++)
			shiftedData[i - 2] = oldData[i];
		shiftedData[30] = 0;
		shiftedData[31] = 0;
		return shiftedData;
	}

	public int[] shiftEllybtzawed(int[] oldData) {
		int[] ret = new int[28];
		ret[26] = 0;
		ret[27] = 0;
		for (int i = 0; i < 26; i++)
			ret[i] = oldData[i];

		return ret;
	}
}