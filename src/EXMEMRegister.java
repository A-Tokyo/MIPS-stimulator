
public class EXMEMRegister {
	private int regWrite =0;
	private int memtoReg =0;
	private int branch =0;
	private int memRead =0;
	private int memWrite =0;
	private int zero =0;
	private int jump =0;
	private int [] ALUResult = new int [32];
	private int [] readData2 = new int [32];
	private int [] regDst = new int [5];
	private int [] pc = new int [32];
	private int [] jumpAd = new int [32];
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
	public int getBranch() {
		return branch;
	}
	public void setBranch(int branch) {
		this.branch = branch;
	}
	public int getMemRead() {
		return memRead;
	}
	public void setMemRead(int memRead) {
		this.memRead = memRead;
	}
	public int getMemWrite() {
		return memWrite;
	}
	public void setMemWrite(int memWrite) {
		this.memWrite = memWrite;
	}
	public int getZero() {
		return zero;
	}
	public void setZero(int zero) {
		this.zero = zero;
	}
	public int[] getALUResult() {
		return ALUResult;
	}
	public void setALUResult(int[] aLUResult) {
		ALUResult = aLUResult;
	}
	public int[] getReadData2() {
		return readData2;
	}
	public void setReadData2(int[] readData2) {
		this.readData2 = readData2;
	}
	public int[] getRegDst() {
		return regDst;
	}
	public void setRegDst(int[] regDst) {
		this.regDst = regDst;
	}
	public int[] getPc() {
		return pc;
	}
	public void setPc(int[] pc) {
		this.pc = pc;
	}

	public void copy(EXMEMRegister r){
		this.regWrite = r.regWrite;
		this.memtoReg = r.memtoReg;
		this.branch = r.branch;
		this.memRead = r.memRead;
		this.memWrite = r.memWrite;
		this.zero = r.zero;
		this.ALUResult = new int [32];
		if(r.ALUResult != null)
			for(int i=0; i<r.ALUResult.length; i++)
				this.ALUResult[i] = r.ALUResult[i];
		this.readData2 = r.readData2;
		this.regDst = r.regDst;
		this.pc = r.pc;
		this.jump = r.jump;
		this.jumpAd = r.jumpAd;
		this.val = r.val;
	}
	public int getJump() {
		return jump;
	}
	public void setJump(int jump) {
		this.jump = jump;
	}
	public int [] getJumpAd() {
		return jumpAd;
	}
	public void setJumpAd(int [] jumpAd) {
		this.jumpAd = jumpAd;
	}
	public int getVal() {
		return val;
	}
	public void setVal(int val) {
		this.val = val;
	}
	public void print(){
		System.out.println("The Values in the Pipline Register between Excution and memory are now");
		System.out.print("regWrite: "+regWrite+" ");
		System.out.print("memtoReg: "+memtoReg+" ");
		System.out.print("regWrite: "+regWrite+" ");
		System.out.print("branch: "+branch+" ");
		System.out.print("memRead: "+memRead+" ");
		System.out.println("memWrite: "+memWrite);
		System.out.print("Zero: "+zero +" ");
		System.out.print("jump: "+jump+" ");
		System.out.print("ALUResult: "+getRegVal(ALUResult)+" ");
		System.out.print("readData2: "+getRegVal(readData2)+" ");
		System.out.println("regDst: "+getRegVal(regDst));
		System.out.print("pc: "+getRegVal(pc)+" ");
		System.out.println("jumpAddress: "+getRegVal(jumpAd));
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
	}
	
	public static int getRegVal(int [] tmp){
		int val=0;
		for (int i=0; i<tmp.length; i++)
			val += ((int) Math.pow(2,tmp.length - 1 - i)) * tmp[i];
		return val;
	}
}
