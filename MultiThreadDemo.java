public class MultiThreadDemo{
	public static void main(String[] args){
		Thread t1 = new ThreadByExtend();
		t1.start();
		Thread t2 = new Thread(new ThreadByImplement());
		t2.start();

		Thread t3 = new ThreadByExtend();
		t3.start();
		Thread t4 = new Thread(new ThreadByImplement());
		t4.start();
	}
}

class ThreadByExtend extends Thread{
	public void run(){
		System.out.println("Run method in ThreadByExtend thread.");
		System.out.println(this.getClass());
	}
}

class ThreadByImplement implements Runnable{
	public void run(){
		System.out.println("Run method in ThreadByImplement.");
		System.out.println(this.getClass());
	}
}