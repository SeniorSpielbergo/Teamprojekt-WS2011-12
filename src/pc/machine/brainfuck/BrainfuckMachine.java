package machine.brainfuck;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import machine.Machine;

public class BrainfuckMachine extends Machine {
	
	private String code;

	public void save(String filename) throws IOException {
		FileWriter fstream = new FileWriter(filename);
		BufferedWriter out = new BufferedWriter(fstream);
		out.write(code);
		out.close();
	}
	
	public void openFile(File fileName) throws FileNotFoundException, IOException {
		byte[] buffer = new byte[(int) fileName.length()];
		BufferedInputStream f = new BufferedInputStream(new FileInputStream(fileName.getPath()));
		f.read(buffer);
		String code = new String(buffer);
		this.code = code;
	}
}