package djsoft;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Persion persion=new Persion("shit",18);
		Observer ageObserver=new AgeObserver();
		Observer nameObserver=new NameOberver();
		persion.addObserver(ageObserver);
		persion.addObserver(nameObserver);
		persion.setAge(20);
		persion.setName("fuck");
	}

}
