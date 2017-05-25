package 工厂方法;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DogFactory df=new DogFactory();
		Dog dog=(Dog) df.createAnimal();
		dog.eat();
	}

}
