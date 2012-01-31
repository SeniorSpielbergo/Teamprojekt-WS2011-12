import lejos.nxt.*;
import lejos.nxt.addon.ColorSensor;
import lejos.nxt.remote.*;
import lejos.nxt.comm.*;
import javax.bluetooth.*;;
import java.io.*;

/*
 * black = 0
 * brown = 0
 * blue = 2
 * dark green = 4
 * light green = 6
 * yellow = 7
 * red = 9
 * orange = 9
 * grey = 14
 * white = 14
 */

/**
 * Application running on the Master-NXT.
 * @author David Wille
 * @author Sven Schuster
 */
public class MainMaster {
	static int counter = 0;
	static DataInputStream in;
	static DataOutputStream out;


	ColorSensor cs1 = null;
	ColorSensor cs2 = null;
	TouchSensor ts1 = null;

	Tape tape = null;

	public static void printRightArrow(){
		int x =0, y = 29;
		for(int i = 1; i <= 20 ; i++){
			LCD.setPixel(1, x+i, y+5);
			LCD.setPixel(1, x+i, y+30);
		}
		for(int i = 1; i <=4 ; i++){
			LCD.setPixel(1, x+20 , y+i);
			LCD.setPixel(1, x+20 , y+i+30);
		}
		for (int i = 5; i <= 30; i++) {
			LCD.setPixel(1, x+1 , y+i);
		}
		for(int i = 1; i <=17 ; i++){
			LCD.setPixel(1, x+20+i , y+i);
			LCD.setPixel(1, x+20+i , y+35-i);
		}
	}
	
	public static void clearRightArrow(){
		int x =0, y = 29;
		for(int i = 1; i <= 20 ; i++){
			LCD.setPixel(0, x+i, y+5);
			LCD.setPixel(0, x+i, y+30);
		}
		for(int i = 1; i <=4 ; i++){
			LCD.setPixel(0, x+20 , y+i);
			LCD.setPixel(0, x+20 , y+i+30);
		}
		for (int i = 5; i <= 30; i++) {
			LCD.setPixel(0, x+1 , y+i);
		}
		for(int i = 1; i <=17 ; i++){
			LCD.setPixel(0, x+20+i , y+i);
			LCD.setPixel(0, x+20+i , y+35-i);
		}
	}
	
	public static void printLeftArrow(){
		int x = 62, y = 29;
		for(int i = 1; i <= 20 ; i++){
			LCD.setPixel(1, x-i+37, y+5);
			LCD.setPixel(1, x-i+37, y+30);
		}
		for(int i = 1; i <=4 ; i++){
			LCD.setPixel(1, x+17 , y+i);
			LCD.setPixel(1, x+17 , y+i+30);
		}
		for (int i = 5; i <= 30; i++) {
			LCD.setPixel(1, x+37 , y+i);
		}
		for(int i = 1; i <=17 ; i++){
			LCD.setPixel(1, x+17-i , y+i);
			LCD.setPixel(1, x+17-i , y+35-i);
		}
	}
	
	public static void clearLeftArrow(){
		int x = 62, y = 29;
		for(int i = 1; i <= 20 ; i++){
			LCD.setPixel(0, x-i+37, y+5);
			LCD.setPixel(0, x-i+37, y+30);
		}
		for(int i = 1; i <=4 ; i++){
			LCD.setPixel(0, x+17 , y+i);
			LCD.setPixel(0, x+17 , y+i+30);
		}
		for (int i = 5; i <= 30; i++) {
			LCD.setPixel(0, x+37 , y+i);
		}
		for(int i = 1; i <=17 ; i++){
			LCD.setPixel(0, x+17-i , y+i);
			LCD.setPixel(0, x+17-i , y+35-i);
		}
	}
	
	public void run() {
		Common.playTune("HAHA",200);
		String sensor1, sensor2, sensor3, counterString;
		this.cs1 = new ColorSensor(SensorPort.S1);
		this.cs2 = new ColorSensor(SensorPort.S2);
		this.ts1 = new TouchSensor(SensorPort.S4);
		// initialize speeds
		Motor.A.setSpeed(Common.LINE_SPEED);
		Motor.B.setSpeed(Common.PUSH_SPEED);
		Motor.C.setSpeed(Common.PUSH_SPEED);

		// sensor listener for emergency stop
		SensorPort.S4.addSensorPortListener(new SensorPortListener() {
			public void stateChanged(SensorPort port, int oldValue, int newValue) {
				if (oldValue > 500 && newValue < 500)
					System.exit(0);
			}
		});

		while (true) {
			LCD.drawString("Moving to begin...", 0, 0);
			tape = new Tape(Common.TAPE_SIZE);
			tape.start();
			LCD.clearDisplay();
			LCD.drawString("Clearing tape...", 0, 0);
			if (tape.clearTape()) {
				break;
			}
			else {
				LCD.clearDisplay();
				LCD.drawString("ATTENTION", 0, 0);
				LCD.drawString("Clearing tape failed.", 0, 1);
				LCD.drawString("After the sound,", 0, 2);
				LCD.drawString("clearing starts again!", 0, 3);

				tape.stop();
				
				Common.playTune("CXCXCXCXCXCXCXCX", 400);
				for (int i = 0; i < 3; i++) {
					try {
						Thread.sleep(1000);
					}
					catch (InterruptedException e) {}
					Common.playTune("E", 600);
				}
				LCD.clearDisplay();
			}
		}


		while (true) {
			// setup connection
			Common.playTune("CEG", 400);
			LCD.clearDisplay();
			LCD.drawString("Waiting...", 0, 0);
			NXTConnection connection = Bluetooth.waitForConnection();           
			LCD.clearDisplay();
			LCD.drawString("Connecting...", 0, 0);
			in = connection.openDataInputStream();
			out = connection.openDataOutputStream();          
			LCD.clearDisplay();
			LCD.drawString("Connected", 0, 0);

			//listen to commands
			this.serve();

			//close connection
			LCD.clearDisplay();
			LCD.drawString("Disconnecting...", 0, 0);
			tape.stop();
			connection.close();

			LCD.drawString("Moving to begin...", 0, 0);
			tape = new Tape(Common.TAPE_SIZE); // laenge uebergeben
			tape.start();
		}
	}

	private void serve() {
		char ch = ' ';

		while (true) {
			try {
				ch = in.readChar();
			}
			catch (IOException e) {
				break;
			}

			LCD.clearDisplay();
			switch (ch) {
			case 'q':
				return; //end serving
			case 't':
				LCD.drawString("Pushing...", 0, 0);
				Motor.B.rotate(Common.PUSH_ANGLE_MASTER);
				Motor.B.rotate(Common.PUSH_ANGLE_MASTER*(-1)+1);
				Motor.C.rotate(Common.PUSH_ANGLE_MASTER);
				Motor.C.rotate(Common.PUSH_ANGLE_MASTER*(-1)+1);
				LCD.clearDisplay();
				break;
			case 'r':
				boolean cs1Active, cs2Active;
				if (cs1.getColorNumber() >= 5 && cs1.getColorNumber() <= 10) {
					cs1Active = true;
				}
				else {
					cs1Active = false;

				}
				if (cs2.getColorNumber() >= 5 && cs2.getColorNumber() <= 10) {
					cs2Active = true;
				}
				else {
					cs2Active = false;
				}
				try {
					if (!cs1Active && !cs2Active) {
						out.writeChar('#');
					}
					else if (!cs1Active && cs2Active) {
						out.writeChar('0');
					}
					else if (cs1Active && !cs2Active) {
						out.writeChar('1');
					}
					else if (cs1Active && cs2Active) {
						out.writeChar('2');
					}
					out.flush();
				}
				catch (IOException e) {
				}
				break;
			case 'w':
				int bit1, bit0, nbit1, nbit0;
				char newSymbol = 'n', currentSymbol = 'n';
				try{
					currentSymbol = in.readChar();
					newSymbol = in.readChar();
				}
				catch(IOException e){}
				switch(currentSymbol){
				case '#': bit1 = 0; bit0 = 0; break;
				case '0': bit1 = 0; bit0 = 1; break;
				case '1': bit1 = 1; bit0 = 0; break;
				case '2': bit1 = 1; bit0 = 1; break;
				default: bit1 = -1; bit0 = -1; break;
				}
				switch(newSymbol){
				case '#': nbit1 = 0; nbit0 = 0; break;
				case '0': nbit1 = 0; nbit0 = 1; break;
				case '1': nbit1 = 1; nbit0 = 0; break;
				case '2': nbit1 = 1; nbit0 = 1; break;
				default: nbit1 = -1; nbit0 = -1; break;
				}				
				Common.pushBits(bit1>nbit1,bit0>nbit0);
					
				try {
					out.writeChar('.');
					out.flush();
				}
				catch (IOException e) {
				}
				break;
			case 'L':
				printLeftArrow();
				if(tape.moveLeft()) {
					try {
						out.writeChar('.');
						out.flush();
					}
					catch (IOException e) {
					}
				}
				else {
					try {
						out.writeChar('!');
						out.flush();
					}
					catch (IOException e) {
					}						
				}
				clearLeftArrow();
				break;
			case 'R': 
				printRightArrow();
				if(tape.moveRight()) {
					try {
						out.writeChar('.');
						out.flush();
					}
					catch (IOException e) {
					}
				}
				else {
					try {
						out.writeChar('!');
						out.flush();
					}
					catch (IOException e) {
					}
				}
				clearRightArrow();
				break;
			}
		}
	}

	/**
	 * Establishes connection to the controlling PC and waits for commands.
	 * @param args
	 */
	public static void main(String[] args) {
		MainMaster master = new MainMaster();
		master.run();
	}
}
