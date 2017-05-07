import java.awt.Rectangle;

import lejos.nxt.SensorPort;
import lejos.nxt.addon.NXTCam;

public class NXTCamera {
	
	NXTCam cam;
	int[] width, height, posX, posY;

	NXTCamera( SensorPort port ) throws InterruptedException {
		cam = new NXTCam( port );
		cam.sendCommand( 'A' );
		Thread.sleep( 200 );
		cam.sendCommand( 'B' );
		Thread.sleep( 200 );
		cam.sendCommand( 'E' );
		Thread.sleep( 200 );
		cam.enableTracking( true );
		Thread.sleep( 200 );
	}
	
	void setParameters( int[] width, int[] height, int[] posX, int[] posY ) {
		this.width = width;
		this.height = height;
		this.posX = posX;
		this.posY = posY;
	}
	
	boolean objectDetected() {
		return cam.getNumberOfObjects() > 0;
	}
	
	int[] existsTarget() {
		int target = searchTarget();
		if ( target < 0 )
			return null;
		int[] t = { positionOn( target ), cam.getObjectColor( target ) };
		return t;
	}
	
	int searchTarget() {
		Rectangle rectangle;
		int y, w, h;
		for ( int i = 0; i < cam.getNumberOfObjects(); i++ ) {
			rectangle = cam.getRectangle( i );
			y = rectangle.y;
			w = rectangle.width;
			h = rectangle.height;
			if ( verifyWidth( w ) && verifyHeight( h ) && verifyY( y ) )
				return i;				
		}
		return -1;
	}
	
	private int positionOn( int target ) {
		Rectangle rectangle = cam.getRectangle( target );
		int x = ( posX[0] + posX[1] ) / 2;
		if ( x - rectangle.x > 0 )
			return -1;
		else if ( x - rectangle.x < 0 )
			return 1;
		else return 0;
	}
	
	private boolean verifyY( int y ) {
		return y > posY[0] && y < posY[1];
	}
	
	private boolean verifyWidth( int w ) {
		return w > width[0] && w < width[1];
	}
	
	private boolean verifyHeight( int h ) {
		return h > height[0] && h < height[1];
	}
}