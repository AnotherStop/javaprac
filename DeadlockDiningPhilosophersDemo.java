import java.util.*;
import java.util.concurrent.*;

class Chopstick{
	private boolean taken = false;
	public synchronized void take() throws InterruptedException{
		while(taken)
			wait();
		taken = true;
	}

	public synchronized void drop(){
		taken = false;
		notifyAll();
	}
}

class Philosopher implements Runnable{
	private Chopstick left;
	private Chopstick right;
	private final int id;
	private final int ponderFactor;
	private Random rand = new Random(47);
	private void pause() throws InterruptedException{
		if(ponderFactor == 0)
			return;
		TimeUnit.MILLISECONDS.sleep(rand.nextInt(ponderFactor*250));
	}

	public Philosopher(Chopstick left, Chopstick right, int ident, int ponder){
		this.left = left;
		this.right = right;
		id = ident;
		ponderFactor = ponder;
	}

	public void run(){
		try{
			while(!Thread.interrupted()){
				System.out.println(this + " thinking...");
				pause();
				System.out.println(this + " grabbing right");
				right.take();
				System.out.println(this + " grabbing left");
				left.take();
				System.out.println(this + " eating");
				pause();
				right.drop();
				left.drop();
			}
		}catch(InterruptedException e){
			System.out.println(this + " " + "exiting via interrupt.");
		}
	}
	public String toString(){
		return "Philosopher " + id;
	}
}

public class DeaklockDiningPhilosophersDemo{
	public static void main(String[] args) throws Exception{
		int ponder = 5;
		if(args.length > 0)
			ponder = Integer.parseInt(args[0]);
		int size = 5;
		if(args.length > 1)
			size = Integer.parseInt(args[1]);
		ExecutorService exec = Executors.newCachedThreadPool();
		Chopstick[] sticks = new Chopstick[size];
		for(int i = 0;i < size;i++)
			sticks[i] = new Chopstick();

		/*
			to prevent deadlock, see following comment
		*/
		for(int i = 0;i < size;i++)
			exec.execute(new Philosopher(sticks[i], sticks[(i+1)%size],i,ponder));
		/*
		
		for(int i = 0; i < size; i++)
      		if(i < (size-1))
        		exec.execute(new Philosopher(sticks[i], sticks[i+1], i, ponder));
      		else
      			exec.execute(new Philosopher(sticks[0], sticks[i], i, ponder));
		*/

		if(args.length == 3 && args[2].equals("timeout"))
			TimeUnit.SECONDS.sleep(5);
		else{
			System.out.println("Press 'Enter' to quit.");
			System.in.read();
		}
		exec.shutdownNow();
	}
}