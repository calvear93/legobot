import lejos.nxt.SensorPort;
import lejos.nxt.comm.*;
import java.io.*;

import javax.bluetooth.*;

class NXTCaterpillar {
	
	private RemoteDevice id;
	private BTConnection btc;
	private DataOutputStream out;
	private boolean isAdvance;
	//private NXTCamera cam;
	//private NXTClaw claw;
	
	NXTCaterpillar( String device ) {//throws InterruptedException {
		isAdvance = false;
		//cam = new NXTCamera( SensorPort.S4 );
		//claw = new NXTClaw();
		id = Bluetooth.getKnownDevice( device );
		if ( id == null )
			{ NXTScreen.printCenter( "[!]BT device dont found!", 10 ); System.exit(1); }
	}
	
	boolean isAdvance() {
		return isAdvance;
	}
	
	void advance() throws IOException, InterruptedException {
		out.writeByte( 'A' );
		out.writeByte( 20 );
		out.flush();
		isAdvance = true;
	}
	
	void back() throws IOException {
		out.writeByte( 'R' );
		out.writeByte( 30 );
		out.flush();
		isAdvance = false;
	}
	
	void stop() throws IOException {
		out.writeByte( 'P' );
		out.writeByte( 40 );
		out.flush();
		isAdvance = false;
	}
	
	void right() throws IOException {
		out.writeByte( 'I' );
		out.writeByte( 50 );
		out.flush();
		isAdvance = false;
	}
	
	void left() throws IOException {
		out.writeByte( 'D' );
		out.writeByte( 60 );
		out.flush();
		isAdvance = false;
	}
	
	void tryConnection() {
		btc = Bluetooth.connect( id );
		if ( id == null ) {
			NXTScreen.printCenter( "[!]BT failed!", 400 );
			NXTScreen.printCenter( "Retrying BT...", 300 );
			tryConnection();
		}
		NXTScreen.printCenter( "[i]BT enable!", 500 );
		enableOutputChannel();
	}
	
	void enableOutputChannel() {
		out = btc.openDataOutputStream();
	}
	
	void closeConnection() {
		btc.closeStream();
	}
}