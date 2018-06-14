package team.boolbee.poc.logging.jul;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Logger;

public class IOUtils {

	private static Logger logger = Logger.getLogger("");
	
	private static final int DEFAULT_BUFFER_SIZE = 1024 * 4;
	public static final int EOF = -1;

	public static int copy(final InputStream input, final OutputStream output) throws IOException {
		logger.fine(String.format("copy(input, output)"));
		final long count = copyLarge(input, output);
		if (count > Integer.MAX_VALUE) {
			logger.warning(String.format("file too large!!"));
			return -1;
		}
		return (int) count;
	}

	public static long copy(final InputStream input, final OutputStream output, final int bufferSize)
			throws IOException {
		logger.finer(String.format("copy(input, output, %s)", bufferSize));
		return copyLarge(input, output, new byte[bufferSize]);
	}

	public static long copyLarge(final InputStream input, final OutputStream output) throws IOException {
		return copy(input, output, DEFAULT_BUFFER_SIZE);
	}

	public static long copyLarge(final InputStream input, final OutputStream output, final byte[] buffer)
			throws IOException {
		long count = 0;
		int n;
		while (EOF != (n = input.read(buffer))) {
			logger.finest(String.format("<< %s bytes read", n));
			output.write(buffer, 0, n);
			logger.finest(String.format(">> %s bytes written", n));
			count += n;
		}
		return count;
	}

//	public static void closeQuietly(final OutputStream output) {
//		closeQuietly((Closeable) output);
//	}

	public static void closeQuietly(final Closeable closeable) {
		logger.fine(String.format("closeQuietly(closable)"));
		try {
			if (closeable != null) {
				closeable.close();
			}
		} catch (final IOException ioe) {
			// ignore
		}
	}
}