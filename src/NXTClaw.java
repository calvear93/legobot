import java.io.IOException;

import lejos.nxt.Motor;
import lejos.nxt.NXTRegulatedMotor;

class NXTClaw {
	
	private NXTRegulatedMotor arm, claw;
	private NXTCaterpillar c;
	private NXTLineSensor l;
	NXTClaw( NXTCaterpillar x, NXTLineSensor j ) { l = j; c = x; setServos( 120 ); }
	
	void capture( ) throws InterruptedException, IOException {
		down();
		if ( l.noLine() )
			c.advance();
		Thread.sleep( 600 );
		hold();
		up();
		release();
		Thread.sleep( 200 );
	}

	void drop( ) throws InterruptedException {
		down();
		release();
		up();
		Thread.sleep( 200 );
	}

	private void hold( ) { claw.rotateTo( -90 ); }

	private void release( ) { claw.rotateTo( 0 ); }

	private void up( ) { arm.rotateTo( 0 ); }

	private void down( ) { arm.rotateTo( 180 ); }

	private void setServos( int speed ) {
		arm = Motor.A;
		arm.setSpeed( speed );
		claw = Motor.B;
		claw.setSpeed( speed );
	}
}