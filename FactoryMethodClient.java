/*
*Design pattern: Factory Method Pattern
* 
* This pattern correct the drawback of simple factory method pattern
*
* There is an abstract factory and an abstrate product
* Producing a concrete product is done by a concrete factory
* When we need to produce new kind of product, just adding a concrete
* factory. It follows open-close principle.  
*/

public class FactoryMethodClient{
	public static void main(String[] args){
		Factory f1 = new GrapeFactory();
		Factory f2 = new AppleFactory();
		Factory f3 = new StrawberryFactory();

		Fruit[] fruitBasket = new Fruit[3];
		fruitBasket[0] = f1.produce();
		fruitBasket[1] = f2.produce();
		fruitBasket[2] = f3.produce();

		System.out.println("What's in the fruit basket?");
		for(Fruit f : fruitBasket)
			System.out.println(f); 
	}
}

//interface for factory
interface Factory{
	public Fruit produce();
}

//concrete factory
class GrapeFactory implements Factory{
	public Fruit produce(){
		return new Grape();
	}
}

//concrete factory
class AppleFactory implements Factory{
	public Fruit produce(){
		return new Apple();
	}
}

//concrete factory
class StrawberryFactory implements Factory{
	public Fruit produce(){
		return new Strawberry();
	}
}

//interface for product
interface Fruit{
	void grow();
	void harvest();
}

//concrete product
class Grape implements Fruit{
	private boolean seedless;

	public Grape(){
		System.out.println("Grape instance is creating...");
	}

	public void setSeedless(boolean seedless){
		this.seedless = seedless;
	}

	public boolean getSeedless(){
		return this.seedless;
	}

	public void grow(){
		System.out.println("Grape is growing.");
	}

	public void harvest(){
		System.out.println("Grape is harvested.");
	}

	@Override
	public String toString(){
		return "This is a grape";
	}
}

//concrete product
class Apple implements Fruit{
	private int appleTreeAge;

	public Apple(){
		System.out.println("Apple instance is creating...");
	}

	public void setAppleTreeAge(int age){
		this.appleTreeAge = age;
	}

	public int getAppleTreeAge(){
		return appleTreeAge;
	}

	public void grow(){
		System.out.println("Apple is growing.");
	}

	public void harvest(){
		System.out.println("Apple is harvested.");
	}

	@Override
	public String toString(){
		return "This is a apple";
	}
}

//concrete product
class Strawberry implements Fruit{

	public Strawberry(){
		System.out.println("Strawberry instance is creating...");
	}

	public void grow(){
		System.out.println("Strawberry is growing.");
	}

	public void harvest(){
		System.out.println("Strawberry is harvested.");
	}

	@Override
	public String toString(){
		return "This is a strawberry";
	}
}