import lejos.nxt.*;
import lejos.nxt.addon.ColorSensor;
public class Main {
   public static void main(String[] args){
       String sensor1;
       ColorSensor cs1 = new ColorSensor(SensorPort.S1);
       int pushAngle = -145;
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
                   if (cs1.getColorNumber()>= 6 &&
cs1.getColorNumber()<=9){
                       break;
                   }
               }
               Motor.A.stop();
           }
           if(Button.RIGHT.isPressed()){
               while(Button.RIGHT.isPressed()){
                   Motor.A.forward();
                   if (cs1.getColorNumber()>= 6 &&
cs1.getColorNumber()<=9){
                       break;
                   }
               }
               Motor.A.stop();
           }
           if (cs1.getColorNumber()>= 6 && cs1.getColorNumber()<=9){
               Motor.B.rotate(pushAngle);
               Motor.B.rotate(pushAngle*(-1)+1);
           }
       }
   }
}
