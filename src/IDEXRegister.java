
public class IDEXRegister {
	private int regWrite=0;
	private int memtoReg=0;
	private int branch=0;
	private int memRead=0;
	private int memWrite=0;
	private int regDst=0;
	private int jump=0;
	private int [] ALUOp= new int [6];
	private int ALUSrc=0; 
	private int [] pc = new int[32];
	private int [] readData1 = new int [32];
	private int [] readData2= new int [32];
	private int [] signExtend= new int [32];
	private int [] ins20to16=new int [5];
	private int [] ins15to11= new int [5];
	private int [] jumpAd= new int [32];
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
	public int getRegDst() {
		return regDst;
	}
	public void setRegDst(int regDst) {
		this.regDst = regDst;
	}
	public int[] getALUOp() {
		return ALUOp;
	}
	public void setALUOp(int[] aLUOp) {
		ALUOp = aLUOp;
	}
	public int getALUSrc() {
		return ALUSrc;
	}
	public void setALUSrc(int aLUSrc) {
		ALUSrc = aLUSrc;
	}
	public int[] getPc() {
		return pc;
	}
	public void setPc(int[] pc) {
		this.pc = pc;
	}
	public int[] getReadData1() {
		return readData1;
	}
	public void setReadData1(int[] readData1) {
		this.readData1 = readData1;
	}
	public int[] getReadData2() {
		return readData2;
	}
	public void setReadData2(int[] readData2) {
		this.readData2 = readData2;
	}
	public int[] getSignExtend() {
		return signExtend;
	}
	public void setSignExtend(int[] signExtend) {
		this.signExtend = signExtend;
	}
	public int[] getIns20to16() {
		return ins20to16;
	}
	public void setIns20to16(int[] ins20to16) {
		this.ins20to16 = ins20to16;
	}
	public int[] getIns15to11() {
		return ins15to11;
	}
	public void setIns15to11(int[] ins15to11) {
		this.ins15to11 = ins15to11;
	}

	public void copy(IDEXRegister r){
		this.regWrite = r.regWrite;
		this.memtoReg = r.memtoReg;
		this.branch = r.branch;
		this.memRead = r.memRead;
		this.memWrite = r.memWrite;
		this.regDst = r.regDst;
		this.ALUOp = r.ALUOp;
		this.ALUSrc = r.ALUSrc; 
		this.pc = r.pc;
		this.readData1 = r.readData1;
		this.readData2 = r.readData2;
		this.signExtend = r.signExtend;
		this.ins20to16 = r.ins20to16;
		this.ins15to11 = r.ins15to11;
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
		System.out.println("The Values in the Pipline Register between instruction Decode and Excute are");
		System.out.print("regWrite: "+regWrite+" ");
		System.out.print("memtoReg: "+memtoReg+" ");
		System.out.print("branch: "+branch+" ");
		System.out.print("memRead: "+memRead+" ");
		System.out.println("memWrite: "+memWrite);
		System.out.print("regDst: "+regDst+" ");
		System.out.print("jump: "+jump+" ");
		System.out.print("ALUSrc: "+ALUSrc+" ");
		System.out.print("ALUOp: "+getRegVal(ALUOp)+" ");
		System.out.println("pc: "+getRegVal(pc));
		System.out.print("readData1: "+getRegVal(readData1)+" ");
		System.out.print("readData2: "+getRegVal(readData2)+" ");
		System.out.print("signExtend: "+getRegVal(signExtend)+" ");
		System.out.print("ins20to16: "+getRegVal(ins20to16)+" ");
		System.out.println("ins15to11: "+getRegVal(ins15to11));
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
