package Brainfuck;
import java.io.*;
import Tape.*;

public class BFTest {
	public static void main(String[] args) throws IOException {
		final int TAPE_LENGTH = 4;
		//ConsoleTape ct = new ConsoleTape();
		MasterRobot ips_03 = new MasterRobot("IPS_03", "00:16:53:13:53:BB");
		SlaveRobot nxt_03 = new SlaveRobot("NXT_03", "00:16:53:0F:DB:8E");
		Tape lt = new LEGOTape(ips_03, nxt_03);
		lt.init();
		
		// count 255 symbols binary
		String inputString = "";
		for(int i = 0; i < Math.pow(2,TAPE_LENGTH-1)-1; i++)
			inputString += "0";
		File file = new File("binary_count.bf");
		
		// binary add
		/*String inputString = "010#11";
		File file = new File("binary_add.bf");*/
		
		// add 2+2 unary
		/*String inputString = "00#00";
		File file = new File("unary_add.bf");*/
		
		byte[] buffer = new byte[(int) file.length()];
		BufferedInputStream f = new BufferedInputStream(new FileInputStream(file.getPath()));
		f.read(buffer);
		String code = new String(buffer);

		BFSimulation sim = new BFSimulation(lt, inputString, null);
		try {
			sim.runSimulation(code);
		} 
		catch(TapeException te){
			System.out.println("something went wrong");
			te.printStackTrace();
		}
		catch(IllegalArgumentException iae) {
			System.out.println("syntaxcheck failed");
		}
	}
}