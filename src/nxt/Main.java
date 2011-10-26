import lejos.nxt.*;
import lejos.nxt.addon.ColorSensor;

/*
 * TODO: check values after calibration
 * black = 0
 * brown = 0
 * blue = 2
 * dark green = 4
 * light green = 6 (u)
 * yellow = 7 (u)
 * red = 9 (u)
 * orange = 9 (u)
 * grey = 14 (u)
 * white = 14 (u)
 */

public class Main {
   public static void main(String[] args){
	   final int PUSH_SPEED = 150;
	   final int LINE_SPEED = 300;
	   int counter = 0;
	   boolean cs1Active = false;
	   boolean cs2Active = false;
	   int oldColor1 = -1;
	   int oldColor2 = -1;
       String sensor1, sensor2, sensor3, counterString;
       ColorSensor cs1 = new ColorSensor(SensorPort.S1);
       ColorSensor cs2 = new ColorSensor(SensorPort.S2);
       ColorSensor counterSensor = new ColorSensor(SensorPort.S3);
       TouchSensor ts1 = new TouchSensor(SensorPort.S4);
       int pushAngle = -155;
	   Motor.A.setSpeed(LINE_SPEED);
	   Motor.B.setSpeed(PUSH_SPEED);
	   
	   while(!Button.ESCAPE.isPressed()) {
		   if(ts1.isPressed()) {
			   cs1.initWhiteBalance();
			   cs2.initWhiteBalance();
			   counterSensor.initWhiteBalance();
		   }
	   }
	   
       while(!Button.ENTER.isPressed()){            
           LCD.clearDisplay();
           sensor1 = "ColorSensor1:" + cs1.getColorNumber();            
           sensor2 = "ColorSensor2:" + cs2.getColorNumber();            
           sensor3 = "ColorSensor3:" + counterSensor.getColorNumber();
           counterString = "Counter:" + counter;
           LCD.drawString(sensor1,0,0);
           LCD.drawString(sensor2,0,1);
           LCD.drawString(sensor3,0,2);
           LCD.drawString(counterString,0,3);
           try {
               Thread.sleep(250);
           }
           catch (InterruptedException e) {
           }
           if(Button.LEFT.isPressed()){
               while(Button.LEFT.isPressed()){
                   Motor.A.backward();
                   /*if (cs1.getColorNumber()>= 2 && cs1.getColorNumber()<=14) {
                	   if (cs1.getColorNumber() > oldColor1) {
                		   	oldColor1 = cs1.getColorNumber();
                		   	cs1Active = true;
                	   }
                   }
                   if (cs2.getColorNumber()>= 2 && cs2.getColorNumber()<=14){
                	   if (cs2.getColorNumber() > oldColor2) {
               		   		oldColor2 = cs2.getColorNumber();
               		   		cs2Active = true;
                	   }
                   }
            	   if (cs1Active || cs2Active) {
            		   cs1Active = false;
            		   cs2Active = false;
            		   counter--;
            		   break;
            	   }*/
               }
               Motor.A.stop();
           }
           if(Button.RIGHT.isPressed()){
               while(Button.RIGHT.isPressed()){
                   Motor.A.forward();
                   /*if (cs1.getColorNumber()>= 2 && cs1.getColorNumber()<=14) {
                	   if (cs1.getColorNumber() > oldColor1) {
                		   	oldColor1 = cs1.getColorNumber();
                		   	cs1Active = true;
                	   }
                   }
                   if (cs2.getColorNumber()>= 2 && cs2.getColorNumber()<=14){
                	   if (cs2.getColorNumber() > oldColor2) {
               		   		oldColor2 = cs2.getColorNumber();
               		   		cs2Active = true;
                	   }
                   }
            	   if (cs1Active || cs2Active) {
            		   cs1Active = false;
            		   cs2Active = false;
            		   counter++;
            		   break;
            	   }*/
                   if (counterSensor.getColorNumber() >= 8 && counterSensor.getColorNumber() <= 10) {
            		   counter++;
                   }
               }
               Motor.A.stop();
           }
           /*if (cs1.getColorNumber()>= 2 && cs1.getColorNumber()<=14){
               Motor.B.rotate(pushAngle);
               Motor.B.rotate(pushAngle*(-1)+1);
           }*/
       }
   }
}
