package team.boolbee.poc.logging.jul;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Downloader {
	private static Logger logger = Logger.getLogger("");
	
	public static boolean downloadFromURL(URL url) {
		logger.info(String.format("Downloading file from %s", url));
		File targetFile = new File(System.getProperty("java.io.tmpdir") + "downloaded.html");
		try {
			FileUtils.copyURLToFile(url, targetFile, 2000, 2000);
			logger.info(String.format("File %s downloaded", targetFile));
		} catch (IOException e) {
			//e.printStackTrace();
			logger.log(Level.SEVERE, "Copying Failed", e);
			return false;
		}
		
		return true;
	}
	
	public static boolean downloadFromURL(String spec) {
		URL url;
		try {
			url = new URL(spec);
		} catch (MalformedURLException e) {
			//e.printStackTrace();
			logger.log(Level.SEVERE, "Malformed URL", e);
			return false;
		}

		return Downloader.downloadFromURL(url);
	}
}