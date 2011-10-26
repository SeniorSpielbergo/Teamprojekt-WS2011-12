import lejos.nxt.*;
import lejos.nxt.addon.ColorSensor;

public class Test {
   public static void main(String[] args){
	   final int PUSH_SPEED = 150;
	   final int LINE_SPEED = 300;
	   String sensor1, sensor2, sensor3;
       ColorSensor cs1 = new ColorSensor(SensorPort.S1);
       ColorSensor cs2 = new ColorSensor(SensorPort.S2);
       ColorSensor cs3 = new ColorSensor(SensorPort.S3);
       TouchSensor ts1 = new TouchSensor(SensorPort.S4);
       int pushAngle = -155;
	   Motor.A.setSpeed(LINE_SPEED);
	   Motor.B.setSpeed(PUSH_SPEED);
	   
	   while(!Button.ESCAPE.isPressed()) {
		   if(ts1.isPressed()) {
			   cs1.initWhiteBalance();
			   cs2.initWhiteBalance();
			   cs3.initWhiteBalance();
		   }
	   }
	   
       while(!Button.ENTER.isPressed()){
           LCD.clearDisplay();
           sensor1 = "ColorSensor1:" + cs1.getColorNumber();            
           sensor2 = "ColorSensor2:" + cs2.getColorNumber();
           sensor3 = "ColorSensor3:" + cs3.getColorNumber();   
           LCD.drawString(sensor1,0,0);
           LCD.drawString(sensor2,0,1);
           LCD.drawString(sensor3,0,2);
           try {
               Thread.sleep(250);
           }
           catch (InterruptedException e) {
           }
           if(Button.LEFT.isPressed()){
               while(Button.LEFT.isPressed()){
                   Motor.A.backward();
               }
               Motor.A.stop();
           }
           if(Button.RIGHT.isPressed()){
               while(Button.RIGHT.isPressed()){
                   Motor.A.forward();
               }
               Motor.A.stop();
           }
           if (Button.ESCAPE.isPressed()){
               Motor.B.rotate(pushAngle);
               Motor.B.rotate(pushAngle*(-1)+1);
           }
       }
   }
}
