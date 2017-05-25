package djsoft;

import java.util.ArrayList;
import java.util.List;

public abstract class Observerable {
	public List<Observer> observerList=new ArrayList<>();
	public void addObserver(Observer observer){
		observerList.add(observer);
	}
	public void removeObserver(Observer observer){
		observerList.remove(observer);
	}
	public void notifObserver(Object value){
		for (Observer observer : observerList) {
			observer.update(value);
		}
	}
}
