package team.boolbee.poc.logging.jul;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class FileUtils {

	public static void copyURLToFile(final URL source, final File destination, final int connectionTimeout,
			final int readTimeout) throws IOException {
		final URLConnection connection = source.openConnection();
		connection.setConnectTimeout(connectionTimeout);
		connection.setReadTimeout(readTimeout);
		copyInputStreamToFile(connection.getInputStream(), destination);
	}

	public static void copyInputStreamToFile(InputStream source, File destination) throws IOException {
		FileOutputStream output = openOutputStream(destination);
		try {
			IOUtils.copy(source, output);
			// output.close();
		} finally {
			IOUtils.closeQuietly(output);
		}
	}

	public static FileOutputStream openOutputStream(File file) throws IOException {
		return openOutputStream(file, false);
	}

	public static FileOutputStream openOutputStream(File file, boolean append) throws IOException {
		if (file.exists()) {
			if (file.isDirectory()) {
				throw new IOException(String.format("File %s exists but is a directory.", file));
			}
			if (!file.canWrite()) {
				throw new IOException(String.format("File %s cannot be written.", file));
			}
		} else {
			File parent = file.getParentFile();
			if (parent != null) {
				if (!parent.mkdirs() && !parent.isDirectory()) {
					throw new IOException(String.format("Directory '%s' could not be created.", file));
				}
			}
		}

		return new FileOutputStream(file, append);
	}

	// https://docs.oracle.com/javase/7/docs/api/java/util/logging/FileHandler.html
	// Logger logger = Logger.getLogger("team.boolbee.poc.logging.jul");
	// FileHandler fh = new FileHandler("%t/mylog.txt");
	// logger.addHandler(fh);
}
