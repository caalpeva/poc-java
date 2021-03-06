package team.boolbee.poc.concurrency.semaphore.mutex;

public class GeneralizedMutalExclusionSolution {

	private static final int VISITORS = 120;
	
	public static void main(String[] args) throws Exception {
		Museum museum = new MuseumWithCapacity(80);
		for(int index = 1; index <= VISITORS; index++) {
			new Thread(new Visitor(museum), "Visitor-" + index).start();
		}
	}
}