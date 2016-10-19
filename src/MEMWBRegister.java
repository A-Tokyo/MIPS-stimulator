
public class MEMWBRegister {
	private int regWrite = 0;
	private int memtoReg = 0;
	private int [] readData= new int [32];
	private int [] ALUResult = new int [32];
	private int [] regDst = new int [5];
	private int val;

	public int getRegWrite() {
		return regWrite;
	}
	public void setRegWrite(int regWrite) {
		this.regWrite = regWrite;
	}
	public int getMemtoReg() {
		return memtoReg;
	}
	public void setMemtoReg(int memtoReg) {
		this.memtoReg = memtoReg;
	}
	public int[] getReadData() {
		return readData;
	}
	public void setReadData(int[] readData) {
		this.readData = readData;
	}
	public int[] getALUResult() {
		return ALUResult;
	}
	public void setALUResult(int[] aLUResult) {
		ALUResult = aLUResult;
	}
	public int[] getRegDst() {
		return regDst;
	}
	public void setRegDst(int[] regDst) {
		this.regDst = regDst;
	}

	public void copy(MEMWBRegister r){
		this.regWrite = r.regWrite;
		this.memtoReg = r.memtoReg;
		if(r.readData != null)
			this.readData = r.readData;
		if(r.ALUResult != null)
			this.ALUResult = r.ALUResult;
		if(r.regDst != null)
			this.regDst = r.regDst;
		this.val = r.val;
	}
	public int getVal() {
		return val;
	}
	public void setVal(int val) {
		this.val = val;
	}
	public void print(){
		System.out.println("The Values in the Pipline Register between memory and write back are");
		System.out.print("regWrite: "+regWrite+" ");
		System.out.print("memtoReg: "+memtoReg+" ");
		System.out.print("readData: "+getRegVal(readData)+" ");
		System.out.print("regDst: "+getRegVal(regDst)+" ");
		System.out.println("ALUResult: "+getRegVal(ALUResult));
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
	}
	public static int getRegVal(int [] tmp){
		int val=0;
		for (int i=0; i<tmp.length; i++)
			val += ((int) Math.pow(2,tmp.length - 1 - i)) * tmp[i];
		return val;
	}
}
