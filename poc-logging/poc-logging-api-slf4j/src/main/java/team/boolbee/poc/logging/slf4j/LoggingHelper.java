package team.boolbee.poc.logging.slf4j;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import team.boolbee.poc.logging.jul.Downloader;

public class LoggingHelper extends team.boolbee.poc.logging.jcl.LoggingHelper {
	
	private static Logger sLogger = LoggerFactory.getLogger(LoggingHelper.class);

	@Override
	public void dumpCurrentDate() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
		String dateLocalizedFormatPattern = simpleDateFormat.toLocalizedPattern();
		sLogger.info(String.format("Date format: %s", dateLocalizedFormatPattern));
		sLogger.info(String.format("Date: %s", simpleDateFormat.format(Calendar.getInstance().getTime())));
	}
	
	@Override
	public void dumpDefaultLocale() {
		sLogger.info(String.format("Locale: %s", Locale.getDefault()));
		Downloader.downloadFromURL("http://www.google.es");
		Downloader.downloadFromURL("http://www.pageNotFound.es");
	}
}