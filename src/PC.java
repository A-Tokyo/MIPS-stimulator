
public class PC {

	private int[] address;

	public PC(int[] address) {
		this.address = address;
	}

	public PC() {
		address = new int[32];
	}

	public int[] getAddress() {
		return address;
	}

	public void setAddress(int[] address) {
		this.address = address;
	}

	public void increment(){
		int decX = Integer.parseInt(stringify(address), 2);
		int decZ = decX + 4;
		address = registify(Integer.toBinaryString(decZ));
	}

	private String stringify(int[] reg) {
		String out="";
		for (int i = 0; i < reg.length; i++) {
			out += reg[i];
		}
		return out;
	}

	private int[] registify(String z) {
		int [] regOut = new int [32];
		int i = 31;
		while ( z.length()!=0 && i>-1) {
			regOut[i--] = Integer.parseInt(z.charAt(z.length()-1)+"");
			z = z.substring(0, z.length()-1);
		}
		return regOut;
	}
}
