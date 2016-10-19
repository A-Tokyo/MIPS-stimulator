import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	static PC pc;
	static InstructionMemory im;
	static ControlUnit cu;
	static RegisterFile rf;
	static SignExtender se;
	static ALU alu;
	static DataMemory dm;
	static Shifter shift1, shift2;
	static Multiplixer bim, balu, amem, up1, up2, bf, exreg;
	static Scanner sc;
	static IFIDRegister ifid, ifid2;
	static IDEXRegister idex, idex2;
	static EXMEMRegister exmem, exmem2;
	static MEMWBRegister memwb, memwb2;
	static int clock, block, ipos, val;

	public static void init(){
		pc = new PC();
		im = new InstructionMemory();
		cu = new ControlUnit();
		rf = new RegisterFile();
		se = new SignExtender();
		alu = new ALU();
		dm = new DataMemory();
		shift1 = new Shifter();
		shift2 = new Shifter();
		bim = new Multiplixer(1, 5);
		balu = new Multiplixer(1, 32);
		amem = new Multiplixer(1, 32);
		up1 = new Multiplixer(1, 32);
		up2 = new Multiplixer(1, 32);
		//new ones
		bf = new Multiplixer(1, 32);
		exreg = new Multiplixer(1, 32);
		sc = new Scanner(System.in);
		ifid = new IFIDRegister();
		idex = new IDEXRegister();
		exmem = new EXMEMRegister();
		memwb = new MEMWBRegister();
		ifid2 = new IFIDRegister();
		idex2 = new IDEXRegister();
		exmem2 = new EXMEMRegister();
		memwb2 = new MEMWBRegister();
		clock = 1;
		block = 0;
		ipos = 0;
		val = 0;
	}

	public static void printReg(int[] reg) {
		for (int i = 0; i < reg.length; i++) {
			System.out.print("[" + reg[i] + "] ");
		}
		System.out.print(", " + reg.length + "bit register." + "\n");
	}

	public static void simulate() throws Exception{
		init();
		System.out.println("To run a mips program please type your program in program.mips file then type \"done\" press enter in the console");
		System.out.println("please add \"END\" in a last separate line to end the program");
		sc.next();
		System.out.println("---------------------------------------------------------");
		BufferedReader buf=new BufferedReader(new FileReader(new File("program.mips")));
		ArrayList<String> pro = new ArrayList<String>();
		while(true){
			String x = buf.readLine();
			x = x.toLowerCase();
			if(x.equals("end"))
				break;
			pro.add(x);
		}
		buf.close();
		System.out.println("Please enter in integers where you want your program to be stored in the instruction memory");
		int pos = sc.nextInt();
		System.out.println("---------------------------------------------------------");
		ipos = pos;
		int size = pro.size();
		for(int i=0; i<size; i++){
			int [] [] inst = im.getInstructions();
			int [] machineCode = Convertor.convert(pro.get(i));
			inst[pos] = machineCode;
			im.setInstructions(inst);
			pos += 4;
		}
		pc.setAddress(alu.registify(Integer.toBinaryString(ipos)));
		bf.insertData(pc.getAddress(),0);
		System.out.println("please enter the data and its address in memory.txt in order to initialize the data memory ex. 8901 0000000000000000000000001001001 will add the value 73 to the memory address 8901");
		System.out.println("Please separate each entry in a new line and add \"END\" at the end of the file then type done and press enter to proceed");
		sc.next();
		System.out.println("---------------------------------------------------------");
		BufferedReader buf2=new BufferedReader(new FileReader(new File("memory.txt")));
		ArrayList<String> pro2 = new ArrayList<String>();
		while(true){
			String x = buf2.readLine();
			x = x.toLowerCase();
			if(x.equals("end"))
				break;
			pro2.add(x);
		}
		buf2.close();
		int size2 = pro2.size();
		for(int i=0; i<size2; i++){
			int [] [] mem = dm.getData();
			String entry [] = pro2.get(i).split(" ");
			int [] d = alu.registify(entry[1]);
			mem[Integer.parseInt(entry[0])] = d;
			dm.setData(mem);
		}
		run();
	}

	public static void fetch(){
		bf.insertData(exmem.getPc(),1);
		AndGate pcSrcAnd = new AndGate();
		int pcSrc = pcSrcAnd.and(exmem.getBranch(), exmem.getZero());
		if(pcSrc == 1 || exmem.getJump() == 1)
			block = 3;
		int [] array = new int [1];
		array[0] = pcSrc;
		up1.insertData(bf.getData(array),0);
		up1.insertData(exmem.getJumpAd(),1);
		array[0] = exmem.getJump();
		pc.setAddress(up1.getData(array));
		int[] tmp = pc.getAddress();
		val=0;
		for (int i=0; i<tmp.length; i++)
			val += ((int) Math.pow(2,tmp.length - 1 - i)) * tmp[i];
		System.out.println("Fetching the instruction number: "+ ((val - ipos)/4 + 1));
		int[] ins = im.getInstruction(tmp);
		pc.increment();
		bf.insertData(pc.getAddress(),0);
		ifid2.setPc(pc.getAddress());
		ifid2.setIns(ins);
		ifid2.setVal(val);
		System.out.println("---------------------------------------------------------");
	}

	public static void decode(){
		if(clock>1){
			System.out.println("Decoding the instructon number: "+((ifid.getVal() - ipos)/4 + 1));	
			int [] ins = ifid.getIns();
			boolean flag = false;
			for(int i =0; i<ins.length; i++)
				if(ins[i] == 1){
					flag = true;
					break;
				}
			if(!flag)
				System.out.println("nop");
			int[] opcode = new int[6];
			for (int i = 0; i < 6; i++)
				opcode[i] = ins[i];
			cu.Control(opcode);
			idex2.setRegWrite(cu.getRegWrite());
			idex2.setMemtoReg(cu.getMemToReg());
			idex2.setBranch(cu.getBranch());
			idex2.setMemRead(cu.getMemRead());
			idex2.setMemWrite(cu.getMemWrite());
			idex2.setRegDst(cu.getRegDst());
			idex2.setALUOp(cu.getALUOp());
			idex2.setALUSrc(cu.getALUSrc());
			idex2.setJump(cu.getJump());
			int rr1[] = new int[5];
			for (int i = 6; i < 11; i++)
				rr1[i - 6] = ins[i];
			int rr2[] = new int[5];
			for (int i = 11; i < 16; i++)
				rr2[i - 11] = ins[i];
			rf.setReadReg1(rr1);
			rf.setReadReg2(rr2);
			int [] inst16 = new int[16];
			for(int i=16; i<32; i++)
				inst16[i-16] = ins[i];
			idex2.setSignExtend(se.extendData(inst16));
			int wb[] = new int[5];
			for (int i = 11; i < 16; i++)
				wb[i - 11] = ins[i];
			idex2.setIns20to16(wb);
			int wb2[] = new int[5]; 
			for (int i = 16; i < 21; i++)
				wb2[i - 16] = ins[i];
			idex2.setIns15to11(wb2);
			idex2.setReadData1(rf.getReadData1());
			idex2.setReadData2(rf.getReadData2());
			idex2.setPc(ifid.getPc());
			int[] oldData = new int[26];
			for (int i = 6; i < 32; i++)
				oldData[i - 6] = ins[i];
			int[] shiftedData = shift1.shiftEllybtzawed(oldData);
			int[] shiftShiftedData = new int[32];
			for (int i = 0; i < 4; ++i)
				shiftShiftedData[i] = 0;
			for (int i = 4; i < 32; i++)
				shiftShiftedData[i] = shiftedData[i - 4];
			idex2.setJumpAd(shiftShiftedData);
			idex2.setVal(ifid.getVal());
			System.out.println("---------------------------------------------------------");
		}
	}

	public static void excute(){
		if(clock>2){
			System.out.println("Excuting the instructon number: "+((idex.getVal() - ipos)/4 + 1));
			exmem2.setRegWrite(idex.getRegWrite());
			exmem2.setMemtoReg(idex.getMemtoReg());
			exmem2.setBranch(idex.getBranch());
			exmem2.setMemRead(idex.getMemRead());
			exmem2.setMemWrite(idex.getMemWrite());
			exmem2.setJump(idex.getJump());
			exmem2.setJumpAd(idex.getJumpAd());
			exmem2.setVal(idex.getVal());
			exreg.insertData(idex.getIns20to16(),0);
			exreg.insertData(idex.getIns15to11(),1);
			int [] array = new int [1];
			array[0] = idex.getRegDst();
			exmem2.setRegDst(exreg.getData(array));
			array[0] = idex.getALUSrc();
			balu.insertData(idex.getReadData2(), 0);
			balu.insertData(idex.getSignExtend(), 1);
			int Alu2[] = balu.getData(array);
			int[] outputALU = alu.toggle(idex.getALUOp(), idex.getSignExtend(), idex.getReadData1(), Alu2); //needs adjustment in ALU Class
			exmem2.setALUResult(outputALU);
			exmem2.setZero(alu.getZero());
			int[] shiftBranch = Shifter.shiftby2(idex.getSignExtend());
			ALU pcALU = new ALU();
			pcALU.add(shiftBranch, idex.getPc());
			exmem2.setPc(pcALU.getRegZ());
			exmem2.setReadData2(idex.getReadData2());
			System.out.println("---------------------------------------------------------");
		}
	}

	public static void mem(){
		if(block<1){
			if(clock>3){
				System.out.println("currently in memory phase of the instructon number: "+((exmem.getVal() - ipos)/4 + 1));
				memwb2.setRegWrite(exmem.getRegWrite());
				memwb2.setMemtoReg(exmem.getMemtoReg());
				memwb2.setVal(exmem.getVal());
				dm.setMemRead(exmem.getMemRead());
				dm.setMemWrite(exmem.getMemWrite());
				dm.writeData(exmem.getALUResult(), exmem.getReadData2());
				memwb2.setReadData(dm.readData(exmem.getALUResult()));
				memwb2.setALUResult(exmem.getALUResult());
				memwb2.setRegDst(exmem.getRegDst());
				System.out.println("---------------------------------------------------------");
			}
		}
	}

	public static void wb(){
		if(block<1){
			if(clock>4){
				System.out.println("Writting back the instructon number: "+((memwb.getVal() - ipos)/4 + 1));
				amem.insertData(memwb.getReadData(),1);
				amem.insertData(memwb.getALUResult(),0);
				int [] array = new int [1];
				array[0] = memwb.getMemtoReg();
				rf.setWriteData(amem.getData(array));
				rf.setWriteReg(memwb.getRegDst());
				rf.setRegWrite(memwb.getRegWrite());
				rf.writeToReg();
				System.out.println("---------------------------------------------------------");
			}
		}
		else
			block--;
	}

	public static void run() {
		while (true) {
			System.out.println("clock cycle number: "+clock);
			wb();
			fetch();
			decode();
			excute();
			mem();
			ifid.copy(ifid2);
			idex.copy(idex2);
			exmem.copy(exmem2);
			memwb.copy(memwb2);
			ifid.print();
			idex.print();
			exmem.print();
			memwb.print();
			System.out.println("****************************************************************************************************************************");
			System.out.println("type next then press enter for the next clock cycle");
			sc.next();
			System.out.println("****************************************************************************************************************************");
			System.out.println("****************************************************************************************************************************");
			clock++;
		}
	}
	public static void main(String[] args) throws Exception {
		simulate();
	}
}
