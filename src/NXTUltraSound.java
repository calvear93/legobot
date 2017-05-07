import lejos.nxt.LCD;
import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;

class NXTUltraSound {
	
	private UltrasonicSensor sonic;
	private int from, to;
	
	NXTUltraSound( SensorPort port, int from, int to ) {
		sonic = new UltrasonicSensor( port );
		this.from = from;
		this.to = to;
	}
	
	boolean targetDetected() {
		return sonic.getDistance() > from && sonic.getDistance() < to;
	}
	
	boolean obstacleInFront() {
		return sonic.getDistance() != 255;
	}
	
	int getDistance() {
		return sonic.getDistance();
	}
	void a() {
		LCD.drawString( ""+sonic.getDistance(), 1, 1);
	}
}