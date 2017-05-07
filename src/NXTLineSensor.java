import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;


public class NXTLineSensor {
	
	private LightSensor left, right;
	private int limit;
	
	NXTLineSensor( SensorPort leftPort, SensorPort rightPort, int limit ) {
		left = new LightSensor( leftPort );
		right = new LightSensor( rightPort );
		this.limit = limit;		
	}
	
	boolean noLine() {
		return !frontLine();
	}
	
	boolean frontLine() {
		return isLine( left ) && isLine( right );
	}
	
	boolean leftLine() {
		return isLine( left );
	}
	
	boolean rightLine() {
		return isLine( right );
	}
	
	private boolean isLine( LightSensor sensor ) {
		return sensor.readValue() > limit;
	}
}