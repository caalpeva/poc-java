package team.boolbee.poc.concurrency.basic.blocking;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousCloseException;
import java.nio.channels.ClosedByInterruptException;
import java.nio.channels.SocketChannel;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class NIOBlocked implements Runnable {

	private static Log logger = LogFactory.getLog(NIOBlocked.class);
	
	private SocketChannel sc;
	
	public NIOBlocked(SocketChannel sc) {
		this.sc = sc;
	}
	
	public void run() {
		try {
			logger.info("Waiting for read() in " + this);
			sc.read(ByteBuffer.allocate(1));
		} catch (ClosedByInterruptException e) {
			logger.info("ClosedByInterruptException");
		} catch (AsynchronousCloseException e) {
			logger.info("AsynchronousCloseException");
		} catch (IOException e) {
			throw new RuntimeException();
		}
		
		logger.info("Exiting NIOBlocked.run() " + this);
	}
}