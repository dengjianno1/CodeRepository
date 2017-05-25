package djsoft;

public class NameOberver implements Observer {

	@Override
	public void update(Object object) {
		if (object instanceof String) {
			System.out.println("名字更新成"+object);
		}
	}

}
