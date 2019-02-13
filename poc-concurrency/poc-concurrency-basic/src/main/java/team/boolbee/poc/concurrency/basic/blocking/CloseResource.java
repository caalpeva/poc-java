package team.boolbee.poc.concurrency.basic.blocking;

import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class CloseResource {

	private static Log logger = LogFactory.getLog(CloseResource.class);

	public static void main(String[] args) throws Exception {
		ExecutorService exec = Executors.newCachedThreadPool();
		ServerSocket server = new ServerSocket(8080);
		Socket socket = new Socket("localhost", 8080);
		try {
			InputStream socketInput = socket.getInputStream();
			exec.execute(new IOBlocked(socketInput));
			//exec.execute(new IOBlocked(System.in));
			TimeUnit.MILLISECONDS.sleep(100);
			logger.info("Shutting down all threads");
			exec.shutdownNow();
	
			TimeUnit.SECONDS.sleep(1);
			logger.info("Closing " + socketInput.getClass().getSimpleName());
			socketInput.close();
	
			//TimeUnit.SECONDS.sleep(1);
			//logger.info("Closing " + System.in.getClass().getSimpleName());
			//System.in.close();
		} finally {
			socket.close();
			server.close();
		}
	}

	// No se puede interrumpir una tarea que esté tratando de efectuar una operación de E/S.
	// Una solución un tanto drástica pero en ocasiones bastante efectiva para
	// este problema, consiste en cerrar el recurso subyacente que hace que la
	// tarea esté bloqueada.
	
	// Resulta interesante observar que la interrupción aparece
	// cuando se cierra un objeto Socket pero no al cerrar System.in

}