package team.boolbee.poc.logging.jcl;

import java.util.Map;

public interface ILoggingHelper {
	
	public void dumpChartSet();
	public void dumpCurrentDate();
	public void dumpDefaultLocale();
	public void dumpException(Throwable throwable);
	public void dumpVars(Map<String, ?> map);

}