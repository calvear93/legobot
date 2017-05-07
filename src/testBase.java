import java.io.IOException;


public class testBase {

	static NXTCaterpillar c = new NXTCaterpillar( "FireFly-045C" );
	public static void main(String[] args) throws IOException, InterruptedException {
		c.tryConnection();

		c.left();
		Thread.sleep( 3000 );
		c.right();
		Thread.sleep( 3000 );
		c.advance();
		Thread.sleep( 3000 );
		c.stop();
		c.closeConnection();
	}
}
