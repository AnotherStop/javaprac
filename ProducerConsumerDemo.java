import java.util.concurrent.*;
public class ProducerConsumerDemo{
	public static void main(String[] args){
		BlockingQueue<Object> bq = new ArrayBlockingQueue<Object>(10);
		Producer p1 = new Producer(bq);
		Consumer c1 = new Consumer(bq);
		Consumer c2 = new Consumer(bq);

		new Thread(p1).start();
		new Thread(c1).start();
		new Thread(c2).start();
	}
}

class Producer implements Runnable{
	private final BlockingQueue<Object> bq;
	public Producer(BlockingQueue<Object> bq){
		this.bq = bq;
	}

	public void run(){
		try{
			while(true){
				bq.put(produce());
			}
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}

	Object produce(){
		System.out.println("Produce something...");
		return new Object();
	}
}

class Consumer implements Runnable{
	private final BlockingQueue<Object> bq;
	public Consumer(BlockingQueue<Object> bq){
		this.bq = bq;
	}

	public void run(){
		try{
			while(true){
				consume(bq.take());
			}
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}

	void consume(Object obj){
		System.out.println("Consume something...");
	}
}