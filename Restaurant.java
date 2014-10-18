import java.util.concurrent.*;

//Meal class
class Meal{
	private final int orderNum;
	public Meal(int orderNum){
		this.orderNum = orderNum;
	}
	public String toString(){
		return "Meal" + orderNum;
	}
}

//wait person class
class WaitPerson implements Runnable{
	private Restaurant restaurant;
	public WaitPerson(Restaurant r){
		restaurant = r;
	}
	public void run(){
		try{
			while(!Thread.interrupted()){
				synchronized(this){
					while(restaurant.meal == null)
						wait();	//wait for chef cooking
				}

				System.out.println("WaitPerson got " + restaurant.meal);

				synchronized(restaurant.chef){
					restaurant.meal = null;
					restaurant.chef.notifyAll();	//notify chef to cook
				}
			}
		}catch(InterruptedException e){
			System.out.println("WaitPerson interrupted.");
		}
	}
}

//chef class
class Chef implements Runnable{
	private Restaurant restaurant;
	private int count = 0;
	public Chef(Restaurant r){
		this.restaurant = r;
	}
	public void run(){
		try{
			while(!Thread.interrupted()){
				synchronized(this){
					while(restaurant.meal != null)
						wait(); 	//wait for consuming meal
				}

				if(++count == 10){
					System.out.println("Out of food, closing.");
					restaurant.exec.shutdownNow();
				}

				System.out.print("Order up! ");

				synchronized(restaurant.waitPerson){
					restaurant.meal = new Meal(count);
					restaurant.waitPerson.notifyAll();
				}
				TimeUnit.MILLISECONDS.sleep(100);
			}
		}catch(InterruptedException e){
			System.out.println("Chef interrupted.");
		}
	}
}

//restaurant class, main class
public class Restaurant{
	Meal meal;
	ExecutorService exec = Executors.newCachedThreadPool();
	WaitPerson waitPerson = new WaitPerson(this);
	Chef chef = new Chef(this);

	public Restaurant(){
		exec.execute(chef);
		exec.execute(waitPerson);
	}
	public static void main(String[] args){
		new Restaurant();
	}
}