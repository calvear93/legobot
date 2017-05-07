import java.awt.Rectangle;
import java.io.IOException;

import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.SensorPort;


public class testLine {

	static NXTCaterpillar c = new NXTCaterpillar( "FireFly-045C" );
	static NXTLineSensor l = new NXTLineSensor( SensorPort.S2, SensorPort.S1, 36 ); //42
	static NXTCamera cam;
	static NXTClaw claw = new NXTClaw(c, l);
	static int f = 0;
	static NXTUltraSound sonic = new NXTUltraSound( SensorPort.S3, 16, 30 );
	
	public static void main(String[] args) throws IOException, InterruptedException {
		c.tryConnection();
		cam = new NXTCamera( SensorPort.S4 );
		setCameraLimits();
		Button.ENTER.waitForPressAndRelease();
		int al;
		while ( !Button.ESCAPE.isDown() ) {
			if ( !c.isAdvance() ) c.advance();
			//al = ( int ) ( Math.random() * ( 3 - 1 + 1 ) + 1 );
			if ( sonic.targetDetected() )
				{ c.stop(); 
				c.advance(); 
				Thread.sleep( 180 ); c.stop(); claw.capture(); Thread.sleep( 500 ); c.advance(); }
			
			if ( l.frontLine() ) 
				{ c.back(); Thread.sleep( 600 ); c.right(); }
			if ( !l.frontLine() && l.leftLine() )
				{ c.back(); Thread.sleep( 600 ); c.right(); }
			if ( !l.frontLine() && l.rightLine() )
				{ c.back(); Thread.sleep( 600 ); c.left(); }
			Thread.sleep( 100 );
		}
				
		c.closeConnection();
	}
	
	private static  void setCameraLimits() {
		int[] width = { 15, 36 };
		int[] height = { 15, 40 };
		int[] posX = { 100, 116};
		int[] posY = { 38, 50 };
		cam.setParameters( width, height, posX, posY );
	}

}