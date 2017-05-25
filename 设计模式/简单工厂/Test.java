package ¼òµ¥¹¤³§;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Dog dog=AnimalFactory.createDog();
		Dog dog=(Dog) AnimalFactory.createAnimal("dog");
		dog.eat();
		System.out.println(dog.toString());
		
		Cat cat=(Cat) AnimalFactory.createAnimal("cat");
		cat.eat();
	}

}
