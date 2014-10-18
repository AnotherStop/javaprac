public class MultiThreadDemo1{
	public static void main(String[] args){
		Thread[] threadSet = new Thread[500];
		for(int i = 0;i < threadSet.length;i++){
			threadSet[i] = new Thread(new Task());
			threadSet[i].start();
		}
	}
}

class Task implements Runnable{
	private static int totalThreads = 0;
	private final int id = (++totalThreads);

	public void run(){
		int i = 0;
		System.out.println("Thread: " + id + " Print message: " + (++i));
		Thread.yield(); //yield() suggests thread scheduler that it can switch at here
		System.out.println("Thread: " + id + " Print message: " + (++i));
		Thread.yield();
		System.out.println("Thread: " + id + " Print message: " + (++i));
		Thread.yield();
	}
}