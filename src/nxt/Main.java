import lejos.nxt.*;
import lejos.nxt.addon.ColorSensor;

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

public class Main {
   public static void main(String[] args){
	   final int PUSH_SPEED = 150;
	   final int LINE_SPEED = 360;
       String sensor1;
       ColorSensor cs1 = new ColorSensor(SensorPort.S1);
       int pushAngle = -155;
	   Motor.A.setSpeed(LINE_SPEED);
	   Motor.B.setSpeed(PUSH_SPEED);
       while(!Button.ENTER.isPressed()){            
           LCD.clearDisplay();            
           sensor1 = "ColorSensor1:" + cs1.getColorNumber();
           LCD.drawString(sensor1,0,0);
           try {
               Thread.sleep(250);
           } catch (InterruptedException e) {

           }
           if(Button.LEFT.isPressed()){
               while(Button.LEFT.isPressed()){
                   Motor.A.backward();
                   if (cs1.getColorNumber()>= 2 && cs1.getColorNumber()<=14){
                       break;
                   }
               }
               Motor.A.stop();
           }
           if(Button.RIGHT.isPressed()){
               while(Button.RIGHT.isPressed()){
                   Motor.A.forward();
                   if (cs1.getColorNumber()>= 2 && cs1.getColorNumber()<=14){
                       break;
                   }
               }
               Motor.A.stop();
           }
           if (cs1.getColorNumber()>= 2 && cs1.getColorNumber()<=14){
               Motor.B.rotate(pushAngle);
               Motor.B.rotate(pushAngle*(-1)+1);
           }
       }
   }
}
