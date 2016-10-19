public class ControlUnit {
	private int regDst;
	private int branch;
	private int memRead;
	private int memToReg;
	private int [] ALUOp;
	private int memWrite;
	private int ALUSrc;
	private int RegWrite;
	private int jump;

	public void Control(int [] input){
		String opCode ="";
		for(int i=0; i<input.length; i++)
			opCode += input[i];
		switch(opCode){ 
		case "000000":regDst=1;branch=0;memRead=0;memToReg=0;memWrite=0;ALUSrc=0;RegWrite=1;jump=0;break;
		case "100011":regDst=0;branch=0;memRead=1;memToReg=1;memWrite=0;ALUSrc=1;RegWrite=1;jump=0;break;
		case "101011":regDst=0;branch=0;memRead=0;memToReg=0;memWrite=1;ALUSrc=1;RegWrite=0;jump=0;break;
		case "000100":regDst=0;branch=1;memRead=0;memToReg=0;memWrite=0;ALUSrc=0;RegWrite=0;jump=0;break;
		case "000010":regDst=0;branch=0;memRead=0;memToReg=0;memWrite=0;ALUSrc=0;RegWrite=0;jump=1;break;
		case "000101":regDst=0;branch=1;memRead=0;memToReg=0;memWrite=0;ALUSrc=0;RegWrite=0;jump=0;break;
		case "001000":regDst=0;branch=0;memRead=0;memToReg=0;memWrite=0;ALUSrc=1;RegWrite=1;jump=0;break;
		case "001111":regDst=0;branch=0;memRead=0;memToReg=0;memWrite=0;ALUSrc=1;RegWrite=1;jump=0;break;
		default: regDst=0;branch=0;memRead=0;memToReg=0;memWrite=0;ALUSrc=0;RegWrite=0;jump=0;
		}
		ALUOp = input;
	}

	public int getRegDst() {
		return regDst;
	}

	public int getBranch() {
		return branch;
	}

	public int getMemRead() {
		return memRead;
	}

	public int getMemToReg() {
		return memToReg;
	}

	public int[] getALUOp() {
		return ALUOp;
	}

	public int getMemWrite() {
		return memWrite;
	}

	public int getALUSrc() {
		return ALUSrc;
	}

	public int getRegWrite() {
		return RegWrite;
	}

	public int getJump() {
		return jump;
	}
}
