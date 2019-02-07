package team.boolbee.poc.concurrency.semaphore.mutex;

public class MutalExclusionSolution {

	private static final int VISITORS = 120;
	
	public static void main(String[] args) throws Exception {
		Museum museum = new MuseumWithoutCapacity();
		for(int index = 1; index <= VISITORS; index++) {
			new Thread(new Visitor(museum), "Visitor-" + index).start();
		}
	}
}