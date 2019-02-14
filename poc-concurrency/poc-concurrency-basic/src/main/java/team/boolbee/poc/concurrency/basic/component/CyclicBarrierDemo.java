package team.boolbee.poc.concurrency.basic.component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class CyclicBarrierDemo {

	static Log logger = LogFactory.getLog(CyclicBarrierDemo.class);
	
	public static void main(String[] args) {
		int nHorses = 7;
		int pause = 250;
		
		if (args.length > 0) {
			int n = new Integer(args[0]);
			nHorses = n > 0 ? n: nHorses;
		}
		
		if (args.length > 1) {
			int p = new Integer(args[1]);
			pause = p > 0 ? p: pause;
		}
		
		new HorseRace(nHorses, pause);
	}
}

class Horse implements Runnable {
	private static int counter = 1;
	private final int id = counter++;
	private Random random = new Random();
	private int strides = 0;
	private CyclicBarrier barrier;
	
	public Horse(CyclicBarrier barrier) {
		this.barrier = barrier;
	}
	
	public int getId() {
		return id;
	}
	
	public synchronized int getStrides() { return strides; }
	
	public void run() {
		try {
			while(!Thread.interrupted()) {
				synchronized (this) {
					strides += random.nextInt(3);
				}
				barrier.await();
			} // while
		} catch (InterruptedException e) {
			CyclicBarrierDemo.logger.info("Exiting via InterruptedException");
		} catch (BrokenBarrierException e) {
			throw new RuntimeException(e);
		}
		
		CyclicBarrierDemo.logger.info("Exiting Horse.run()");
	}

	public String toString() {
		return "Horse " + id + " ";
	}
}

class HorseRace {
	private static final int FINISH_LINE = 75;
	private List<Horse> horses = new ArrayList<Horse>();
	private ExecutorService exec = Executors.newCachedThreadPool();
	private CyclicBarrier barrier;
	
	public HorseRace(int nHorses, final int pause) {
		barrier = new CyclicBarrier(nHorses, new Runnable() {
			public void run() {
				StringBuilder s = new StringBuilder();
				for(int i = 0; i < FINISH_LINE; i++) {
					s.append("=");
				} // for
				CyclicBarrierDemo.logger.info(s);
				
				for(Horse horse: horses) {
					CyclicBarrierDemo.logger.info(tracks(horse));
				} // for
				
				for(Horse horse: horses) {
					if (horse.getStrides() >= FINISH_LINE) {
						CyclicBarrierDemo.logger.info(horse.getId() + " won!");
						exec.shutdownNow();
						return;
					}
				} // for
				
				try {
					TimeUnit.MILLISECONDS.sleep(pause);
				} catch (InterruptedException e) {
					CyclicBarrierDemo.logger.info("Barrier-action sleep interrupted");
				}
			}
		});
		
		for(int i = 0; i < nHorses; i++) {
			Horse horse = new Horse(barrier);
			horses.add(horse);
			exec.execute(horse);
		} // for
	}
	
	private String tracks(Horse horse) {
		StringBuilder s = new StringBuilder();
		for(int i = 0; i < horse.getStrides(); i++) {
			s.append("*");
		} // for
		s.append(" " + horse.getId());
		
		return s.toString();
	}
}