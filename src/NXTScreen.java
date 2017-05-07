import lejos.nxt.LCD;

class NXTScreen {
	
	private NXTScreen() {}
	
	static void printUp( String msg, int duration ) {
		print( msg, duration, 2, 1 );
	}
	
	static void printDown( String msg, int duration ) {
		print( msg, duration, 2, 9 );
	}
	
	static void printCenter( String msg, int duration ) {
		print( msg, duration, 2, 4 );
	}

	private static void print( String msg, int duration, int x, int y ){
		LCD.clear();
	    LCD.drawString( msg, x, y );
	    LCD.refresh();
    }
}