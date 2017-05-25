package djsoft;

public class Persion extends Observerable {
	private String name;
	private Integer age;
	public Persion(String name,int age) {
		this.name=name;
		this.age=age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
		notifObserver(name);
	}
	public int getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
		notifObserver(age);
	}
	
}
