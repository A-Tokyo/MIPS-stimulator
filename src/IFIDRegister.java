
public class IFIDRegister {
	private int [] ins = new int[32];
	private int [] pc = new int[32];
	private int val=0;
	public int [] getIns() {
		return ins;
	}
	public void setIns(int [] ins) {
		this.ins = ins;
	}
	public int [] getPc() {
		return pc;
	}
	public void setPc(int [] pc) {
		this.pc = pc;
	}

	public void copy(IFIDRegister r){
		if(r.ins != null)
			this.ins = new int [32];
		for(int i=0; i<r.ins.length; i++)
			this.ins[i] = r.ins[i];
		this.pc = r.pc;
		this.val = r.val;
	}
	public int getVal() {
		return val;
	}
	public void setVal(int val) {
		this.val = val;
	}
	public void print(){
		System.out.println("The Values in the Pipline Register between instruction fetch and instruction decode are");
		System.out.print("pc: "+getRegVal(pc)+" ");
		System.out.println("ins: "+getRegVal(ins));
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
	}
	public static int getRegVal(int [] tmp){
		int val=0;
		for (int i=0; i<tmp.length; i++)
			val += ((int) Math.pow(2,tmp.length - 1 - i)) * tmp[i];
		return val;
	}
}