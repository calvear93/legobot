import lejos.nxt.SensorPort;


public class testCam {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		NXTCamera cam = new NXTCamera( SensorPort.S4 );
		while(true)
		NXTScreen.printCenter( "m " + cam.cam.getNumberOfObjects() + " c"+cam.cam.getObjectColor(0) , 100 );
	}

}
