
public class Convertor {

	public static int[] convert(String s){
		String [] arr = s.split(" ");
		int [] res = new int [32];
		int [] op = getOp(arr[0]);
		for(int i=0; i<6; i++)
			res[i] = op[i];
		if(arr.length == 2){
			int num = Integer.parseInt(arr[1]);
			String str = Integer.toBinaryString(num);
			int []regShift = registify3(str);
			for(int i=6; i<32; i++)
				res[i] = regShift[i-6];
			return res;
		}
		arr[0] = arr[0].toLowerCase();
		if(arr[0].equals("and") || arr[0].equals("sub") || arr[0].equals("add") || arr[0].equals("or") || arr[0].equals("slt")){
			int[] reg = getReg(arr[2]);
			for(int i=6; i<11; i++)
				res[i] = reg[i-6];
			int[] reg2 = getReg(arr[3]);
			for(int i=11; i<16; i++)
				res[i] = reg2[i-11];
			int[] reg3 = getReg(arr[1]);
			for(int i=16; i<21; i++)
				res[i] = reg3[i-16];
			for(int i=21; i<26; i++)
				res[i] = 0;
			int[] fun = getFunc(arr[0]);
			for(int i=26; i<32; i++)
				res[i] = fun[i-26];
			return res;
		}
		if(arr[0].equals("sll") || arr[0].equals("srl")){
			int[] reg = getReg(arr[2]);
			for(int i=6; i<11; i++)
				res[i] = reg[i-6];
			for(int i=11; i<16; i++)
				res[i] = 0;
			int[] reg3 = getReg(arr[1]);
			for(int i=16; i<21; i++)
				res[i] = reg3[i-16];
			int num = Integer.parseInt(arr[3]);
			String str = Integer.toBinaryString(num);
			int []regShift = registify(str);
			for(int i=21; i<26; i++)
				res[i] = regShift[i-21];
			int[] fun = getFunc(arr[0]);
			for(int i=26; i<32; i++)
				res[i] = fun[i-26];
			return res;
		}
		if(arr[0].equals("bne") || arr[0].equals("beq") || arr[0].equals("addi")){
			int[] reg = getReg(arr[2]);
			for(int i=6; i<11; i++)
				res[i] = reg[i-6];
			int[] reg2 = getReg(arr[1]);
			for(int i=11; i<16; i++)
				res[i] = reg2[i-11];
			int num = Integer.parseInt(arr[3]);
			String str = Integer.toBinaryString(num);
			int []regShift = registify2(str);
			for(int i=16; i<32; i++)
				res[i] = regShift[i-16];
			return res;

		}
		if(arr[0].equals("lw") || arr[0].equals("sw")){
			int[] reg2 = getReg(arr[1]);
			for(int i=11; i<16; i++)
				res[i] = reg2[i-11];
			String reg = "";
			boolean found = false;
			int val = 0;
			for(int i=0; i<arr[2].length(); i++){
				if(arr[2].charAt(i) == ')')
					break;
				else
					if(arr[2].charAt(i) == '(')
						found = true;
					else
						if(found)
							reg += arr[2].charAt(i) + "";
						else
							val = val *10 + Integer.parseInt(arr[2].charAt(i) + "");
			}
			int[] reg3 = getReg(reg);
			for(int i=6; i<11; i++)
				res[i] = reg3[i-6];
			String str = Integer.toBinaryString(val);
			int []regShift = registify2(str);
			for(int i=16; i<32; i++)
				res[i] = regShift[i-16];
			return res;

		}
		if(arr[0].equals("lui")){
			int[] reg2 = getReg(arr[1]);
			for(int i=11; i<16; i++)
				res[i] = reg2[i-11];
			for(int i=6; i<11; i++)
				res[i] = reg2[i-6];
			int num = Integer.parseInt(arr[2]);
			String str = Integer.toBinaryString(num);
			int []regShift = registify2(str);
			for(int i=16; i<32; i++)
				res[i] = regShift[i-16];
			return res;
		}
		return null;
	}

	public static int[] getOp(String ins){
		switch(ins.toLowerCase()){
		case "add":
		case "sub":
		case "sll":
		case "srl":
		case "and":
		case "or":
		case "slt":return new int [] {0,0,0,0,0,0};
		case "addi":return new int [] {0,0,1,0,0,0};
		case "lw":return new int [] {1,0,0,0,1,1};
		case "sw":return new int [] {1,0,1,0,1,1};
		case "lui":return new int [] {0,0,1,1,1,1};
		case "beq":return new int [] {0,0,0,1,0,0};
		case "bne":return new int [] {0,0,0,1,0,1};
		case "j":return new int [] {0,0,0,0,1,0};
		default:return new int [] {0,0,0,0,0,0};
		}
	}

	public static int[] getFunc(String ins){
		switch(ins.toLowerCase()){
		case "add":return new int [] {1,0,0,0,0,0};
		case "sub":return new int [] {1,0,0,0,1,0};
		case "sll":return new int [] {0,0,0,0,0,0};
		case "srl":return new int [] {0,0,0,0,1,0};
		case "and":return new int [] {1,0,0,1,0,0};
		case "or":return new int [] {1,0,0,1,0,1};
		case "slt":return new int [] {1,0,1,0,1,0};
		default:return new int [] {0,0,0,0,0,0};
		}
	}

	public static int[] getReg(String ins){
		switch(ins.toLowerCase()){
		case "$0":
		case "$zero":return new int [] {0,0,0,0,0};
		case "$at":return new int [] {0,0,0,0,1};
		case "$v0":return new int [] {0,0,0,1,0};
		case "$v1":return new int [] {0,0,0,1,1};
		case "$a0":return new int [] {0,0,1,0,0};
		case "$a1":return new int [] {0,0,1,0,1};
		case "$a2":return new int [] {0,0,1,1,0};
		case "$a3":return new int [] {0,0,1,1,1};
		case "$t0":return new int [] {0,1,0,0,0};
		case "$t1":return new int [] {0,1,0,0,1};
		case "$t2":return new int [] {0,1,0,1,0};
		case "$t3":return new int [] {0,1,0,1,1};
		case "$t4":return new int [] {0,1,1,0,0};
		case "$t5":return new int [] {0,1,1,0,1};
		case "$t6":return new int [] {0,1,1,1,0};
		case "$t7":return new int [] {0,1,1,1,1};
		case "$s0":return new int [] {1,0,0,0,0};
		case "$s1":return new int [] {1,0,0,0,1};
		case "$s2":return new int [] {1,0,0,1,0};
		case "$s3":return new int [] {1,0,0,1,1};
		case "$s4":return new int [] {1,0,1,0,0};
		case "$s5":return new int [] {1,0,1,0,1};
		case "$s6":return new int [] {1,0,1,1,0};
		case "$s7":return new int [] {1,0,1,1,1};
		case "$t8":return new int [] {1,1,0,0,0};
		case "$t9":return new int [] {1,1,0,0,1};
		case "$k0":return new int [] {1,1,0,1,0};
		case "$k1":return new int [] {1,1,0,1,1};
		case "$gp":return new int [] {1,1,1,0,0};
		case "$sp":return new int [] {1,1,1,0,1};
		case "$fp":return new int [] {1,1,1,1,0};
		case "$ra":return new int [] {1,1,1,1,1};
		case "$0,":
		case "$zero,":return new int [] {0,0,0,0,0};
		case "$at,":return new int [] {0,0,0,0,1};
		case "$v0,":return new int [] {0,0,0,1,0};
		case "$v1,":return new int [] {0,0,0,1,1};
		case "$a0,":return new int [] {0,0,1,0,0};
		case "$a1,":return new int [] {0,0,1,0,1};
		case "$a2,":return new int [] {0,0,1,1,0};
		case "$a3,":return new int [] {0,0,1,1,1};
		case "$t0,":return new int [] {0,1,0,0,0};
		case "$t1,":return new int [] {0,1,0,0,1};
		case "$t2,":return new int [] {0,1,0,1,0};
		case "$t3,":return new int [] {0,1,0,1,1};
		case "$t4,":return new int [] {0,1,1,0,0};
		case "$t5,":return new int [] {0,1,1,0,1};
		case "$t6,":return new int [] {0,1,1,1,0};
		case "$t7,":return new int [] {0,1,1,1,1};
		case "$s0,":return new int [] {1,0,0,0,0};
		case "$s1,":return new int [] {1,0,0,0,1};
		case "$s2,":return new int [] {1,0,0,1,0};
		case "$s3,":return new int [] {1,0,0,1,1};
		case "$s4,":return new int [] {1,0,1,0,0};
		case "$s5,":return new int [] {1,0,1,0,1};
		case "$s6,":return new int [] {1,0,1,1,0};
		case "$s7,":return new int [] {1,0,1,1,1};
		case "$t8,":return new int [] {1,1,0,0,0};
		case "$t9,":return new int [] {1,1,0,0,1};
		case "$k0,":return new int [] {1,1,0,1,0};
		case "$k1,":return new int [] {1,1,0,1,1};
		case "$gp,":return new int [] {1,1,1,0,0};
		case "$sp,":return new int [] {1,1,1,0,1};
		case "$fp,":return new int [] {1,1,1,1,0};
		case "$ra,":return new int [] {1,1,1,1,1};
		default:return new int [] {0,0,0,0,0};
		}
	}

	public static int[] registify(String z) {
		int[] regOut = new int[5];
		int i = 4;
		while (z.length() != 0 && i > -1) {
			regOut[i--] = Integer.parseInt(z.charAt(z.length() - 1) + "");
			z = z.substring(0, z.length() - 1);
		}
		return regOut;
	}

	public static int[] registify2(String z) {
		int[] regOut = new int[16];
		int i = 15;
		while (z.length() != 0 && i > -1) {
			regOut[i--] = Integer.parseInt(z.charAt(z.length() - 1) + "");
			z = z.substring(0, z.length() - 1);
		}
		return regOut;
	}

	public static int[] registify3(String z) {
		int[] regOut = new int[26];
		int i = 25;
		while (z.length() != 0 && i > -1) {
			regOut[i--] = Integer.parseInt(z.charAt(z.length() - 1) + "");
			z = z.substring(0, z.length() - 1);
		}
		return regOut;
	}

	public static void printReg(int[] reg) {
		for (int i = 0; i < reg.length; i++) {
			System.out.print("[" + reg[i] + "] ");
		}
		System.out.print(", " + reg.length + "bit register." + "\n");
	}
}
