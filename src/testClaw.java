import lejos.nxt.Button;
import lejos.nxt.SensorPort;


public class testClaw {

	static NXTCamera cam;
	static NXTClaw claw;
	public static void main(String[] args) throws InterruptedException {
		cam = new NXTCamera( SensorPort.S4 );
		claw = new NXTClaw();
		int[] targ;
		
		while( !Button.ESCAPE.isDown() ) {
			NXTScreen.printCenter( "" + cam.cam.getNumberOfObjects() , 100 );
			
		}
	}

}
