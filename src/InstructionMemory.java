
public class InstructionMemory {

	private int[][] instructions = new int [1000000] [32];

	public int[] getInstruction(int[] pc) {
		int x = 0;
		for (int i = 0; i < pc.length; i++)
			x += ((int) Math.pow(2, pc.length - 1 - i)) * pc[i];
		return instructions[x];
	}

	public int[][] getInstructions() {
		return instructions;
	}

	public void setInstructions(int[][] instructions) {
		this.instructions = instructions;
	}

}
