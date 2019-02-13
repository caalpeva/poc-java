package team.boolbee.poc.concurrency.basic.blocking;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class NIOInterrupting {

	private static Log logger = LogFactory.getLog(NIOInterrupting.class);
	
	public static void main(String[] args) throws Exception {
		ExecutorService exec = Executors.newCachedThreadPool();
		ServerSocket server = new ServerSocket(8080);
		try {
			InetSocketAddress isa = new InetSocketAddress("localhost", 8080);
			SocketChannel socketChannel1 = SocketChannel.open(isa);
			SocketChannel socketChannel2 = SocketChannel.open(isa);
			
			NIOBlocked r = new NIOBlocked(socketChannel1);
			Future<?> f = exec.submit(r);
			exec.execute(new NIOBlocked(socketChannel2));
			TimeUnit.MILLISECONDS.sleep(1000);
			exec.shutdown();
	
			TimeUnit.SECONDS.sleep(1);
			logger.info("Interrupting " + r.getClass().getSimpleName());
			f.cancel(true);
			logger.info("Interrupt sent to " + r.getClass().getSimpleName());
	
			TimeUnit.SECONDS.sleep(1);
			logger.info("Closing " + socketChannel2.getClass().getSimpleName());
			socketChannel2.close();
		} finally {
			server.close();
		}
	}
}