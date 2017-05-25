package djsoft;

public class AgeObserver implements Observer {

	@Override
	public void update(Object object) {
		if (object instanceof Integer) {
			System.out.println("年龄更新成"+object);
		}
	}
}
