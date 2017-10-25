package team.boolbee.commons;

import java.util.Vector;

public class MemoryEater {
	
	private static final int MB = 1048576;
	
	public static void main(String[] args) throws InterruptedException{
		while (true) {
			long freeMemory = getFreeMemory();
			Vector<byte[]> v = new Vector<byte[]>();
			do{
		    	v.add(new byte[MB]);
		    	freeMemory = getFreeMemory();
			} while (freeMemory > 512 * MB);
			System.out.println("Max memory consumed. Sleep for 1 seconds and continue");
			Thread.sleep(1000);
		}
	}
	
	private static long getFreeMemory(){
		long freeMemory = Runtime.getRuntime().freeMemory();
    	System.out.println("Free memory: " + getMb(freeMemory));
    	return freeMemory;
	}
	
	private static double getMb(long bytes){
		return (double) bytes / MB;
	}
}