package 工厂方法;

public class DogFactory implements Factory {

	@Override
	public Animal createAnimal() {
		// TODO Auto-generated method stub
		return new Dog();
	}

}
