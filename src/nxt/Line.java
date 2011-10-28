import lejos.nxt.*;

public class Line {

	public enum Direction {
		LEFT, RIGHT
	}

	public void driveLine(Direction d) {
		if(d == LEFT) {
			Motor.A.backward();
		}
		else if (d == RIGHT) {
			Motor.A.forward();
		}
	}

}