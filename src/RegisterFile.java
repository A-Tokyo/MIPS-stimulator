
public class RegisterFile {
	int [] [] registers = new int [32] [32];

	private int regWrite;
	private int [] writeData = new int [32];
	private int [] readReg1 = new int [5];
	private int [] readReg2 = new int [5];
	private int [] writeReg = new int [5];
	private int [] readData1 = new int [32];
	private int [] readData2 = new int [32];

	public void setRegWrite(int regWrite) {
		this.regWrite = regWrite;
	}

	public void setWriteData(int [] writeData) {
		this.writeData = writeData;
	}

	public void setReadReg1(int [] readReg1) {
		this.readReg1 = readReg1;
	}

	public void setReadReg2(int [] readReg2) {
		this.readReg2 = readReg2;
	}

	public void setWriteReg(int [] writeReg) {
		this.writeReg = writeReg;
	}

	public int [] getReadData1() {
		int index=0;
		for(int i=0; i<readReg1.length; i++)
			index += ((int) Math.pow(2,readReg1.length-1-i))*readReg1[i];
		readData1 = registers[index];
		return readData1;
	}

	public int [] getReadData2() {
		int index=0;
		for(int i=0; i<readReg2.length; i++)
			index += ((int) Math.pow(2,readReg2.length-1-i))*readReg2[i];
		readData2 = registers[index];
		return readData2;
	}	

	public void writeToReg(){
		if(regWrite==1){
			int index=0;
			for(int i=0; i<writeReg.length; i++)
				index += ((int) Math.pow(2,writeReg.length-1-i))*writeReg[i];
			if(index != 0){
				registers[index] = writeData;
			int val =0;
			for(int i=0; i<writeData.length; i++)
				val += ((int) Math.pow(2,writeData.length-1-i))*writeData[i];
			System.out.println("The value in the register "+getReg(index)+" is now updated to: "+val);
			}
		}
	}
	
	public static String getReg(int index){
		switch(index){
		case 0: return "$0";
		case 1: return "$at";
		case 2: return "$v0";
		case 3: return "$v1";
		case 4: return "$a0";
		case 5: return "$a1";
		case 6: return "$a2";
		case 7: return "$a3";
		case 8: return "$t0";
		case 9: return "$t1";
		case 10: return "$t2";
		case 11: return "$t3";
		case 12: return "$t4";
		case 13: return "$t5";
		case 14: return "$t6";
		case 15: return "$t7";
		case 16: return "$s0";
		case 17: return "$s1";
		case 18: return "$s2";
		case 19: return "$s3";
		case 20: return "$s4";
		case 21: return "$s5";
		case 22: return "$s6";
		case 23: return "$s7";
		case 24: return "$t8";
		case 25: return "$t9";
		case 26: return "$k0";
		case 27: return "$k1";
		case 28: return "$gp";
		case 29: return "$sp";
		case 30: return "$fp";
		case 31: return "$ra";
		}
		return null;
	}
}
