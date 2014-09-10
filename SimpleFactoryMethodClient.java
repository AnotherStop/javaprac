/*
*Design pattern: Simply factory method
* encapsulate the process of creating concrete instance into a factory
*
* drawback: when concrete instances have 
* complicated hierarch, factory method will be messed up
*/

public class SimpleFactoryMethodClient{
	public static void main(String[] args){
		SimpleFactory.factory("Apple");
		SimpleFactory.factory("Grape");
		SimpleFactory.factory("Strawberry");
	}
}

/*Simple factory to create Fruit instance*/
class SimpleFactory{

	//must be a static method to avoid creating factory instance
	public static Fruit factory(String name){
		if(name.equalsIgnoreCase("Grape"))
			return new Grape();
		else if(name.equalsIgnoreCase("Apple"))
			return new Apple();
		else if(name.equalsIgnoreCase("Strawberry"))
			return new Strawberry();
		else
			return null;
	}
}

//interface for Fruit
interface Fruit{
	void grow();
	void harvest();
}

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
}

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
}

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
}