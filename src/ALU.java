public class ALU {

	private final int size = 32;
	private int[] regZ = new int[size];
	private int zero;

	public int[] toggle(int[] opCode, int[] fn2, int[] regX, int[] regY) {
		int [] fn = new int [6];
		for(int i=26; i<32; i++)
			fn[i-26] = fn2[i];
		if (stringify(opCode).equals("000000")) {
			switch(stringify(fn)){
			case "100000": zero = 0; add(regX, regY); break;
			case "100010": zero = 0; sub(regX, regY); break;
			case "100100": zero = 0; and(regX, regY); break;
			case "100101": zero = 0; or(regX, regY); break;
			case "101010": sub(regX, regY);
			if(negative(regZ))
				writeOne();
			else
				writeZero();
			break;
			case "000000": zero = 0; sll(regX, fn2); break;
			case "000010": zero = 0; srl(regX, fn2); break;
			}
		} 
		else {
			switch (stringify(opCode)) {
			case "100011": 
			case "101011": 
			case "001000": zero = 0; add(regX, regY); break;
			case "000100": sub(regX, regY); zero = allZeros(getRegZ())?1:0; break;
			case "000010":break;
			case "000101": sub(regX, regY); zero = allZeros(getRegZ())?0:1; break;
			case "001111": zero = 0; lui(regX, regY); break;
			}
		}
		return regZ;
	}

	private boolean allZeros(int[] ar) {
		for (int i = 0; i < ar.length; i++)
			if (ar[i] != 0)
				return false;
		return true;
	}

	private void and(int[] regX, int[] regY) {
		writeZero();
		for (int i = 0; i < size; i++)
			if (regX[i] + regY[i] == 2)
				regZ[i] = 1;
			else
				regZ[i] = 0;
	}

	private void or(int[] regX, int[] regY) {
		writeZero();
		for (int i = 0; i < size; i++)
			if (regX[i] + regY[i] != 0)
				regZ[i] = 1;
			else
				regZ[i] = 0;
	}

	private void lui(int[] regX, int[] regY) {
		writeZero();
		for(int i=0; i<16; i++)
			regZ[i] = regY[i+16];
		for(int i=16; i<32; i++)
			regZ[i] = regX[i];
	}

	private void sll(int[] regX, int []regY) {
		int [] fn = new int[5];
		for(int i=21; i<26; i++)
			fn[i-21] = regY[i];
		int shamt = Integer.parseInt((stringify(fn)),2);
		writeZero();
		for (int i = 0; i < size - shamt; i++) {
			regZ[i] = regX[i + shamt];
		}
	}

	private void srl(int[] regX, int []regY) {
		int [] fn = new int[5];
		for(int i=21; i<26; i++)
			fn[i-21] = regY[i];
		int shamt = Integer.parseInt((stringify(fn)),2);
		writeZero();
		for (int i = size - 1; i > shamt; i--) {
			regZ[i] = regX[i - shamt];
		}
	}

	public void add(int[] regX, int[] regY) {
		int decX,decY;
		if(regX[0] == 1)
			decX = Short.parseShort(negatify(regX), 2);
		else
			decX = Integer.parseInt(stringify(regX), 2);
		if(regY[0] == 1)
			decY = Short.parseShort(negatify(regY), 2);
		else
			decY = Integer.parseInt(stringify(regY), 2);
		int decZ = decX + decY;
		regZ = registify(Integer.toBinaryString(decZ));
	}

	private void sub(int[] regX, int[] regY) {
		int decX,decY;
		if(regX[0] == 1)
			decX = Short.parseShort(negatify(regX), 2);
		else
			decX = Integer.parseInt(stringify(regX), 2);
		if(regY[0] == 1)
			decY = Short.parseShort(negatify(regY), 2);
		else
			decY = Integer.parseInt(stringify(regY), 2);
		int decZ = decX - decY;
		regZ = registify(Integer.toBinaryString(decZ));
	}

	private boolean negative(int [] x){
		if(x[0]==0)
			return false;
		return true;
	}

	public int getZero() {
		return zero;
	}

	private String stringify(int[] reg) {
		String out = "";
		for (int i = 0; i < reg.length; i++) {
			out += reg[i];
		}
		return out;
	}

	private String negatify(int [] reg){
		String out = "";
		for (int i = 16; i < 32; i++) {
			out += reg[i];
		}
		return out;
	}

	public int[] registify(String z) {
		int[] regOut = new int[32];
		int i = 31;
		while (z.length() != 0 && i > -1) {
			regOut[i--] = Integer.parseInt(z.charAt(z.length() - 1) + "");
			z = z.substring(0, z.length() - 1);
		}
		return regOut;
	}

	public int[] getRegZ() {
		return regZ;
	}

	private void writeZero(){
		for(int i=0; i<regZ.length; i++)
			regZ[i] = 0;
	}

	private void writeOne(){
		writeZero();
		regZ[regZ.length-1] = 1;
	}

	public static void printReg(int[] reg) {
		for (int i = 0; i < reg.length; i++) {
			System.out.print("[" + reg[i] + "] ");
		}
		System.out.print(", " + reg.length + "bit register." + "\n");
	}
}