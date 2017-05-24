package package_a;

import java.util.ArrayList;
import java.util.List;

public class Leap {
	//能被400整除或非整百年能被4整除
	public boolean isLeapYear(int n){
		if (n%400==0||(n%100!=0&&n%4==0)) {
			return true;
		}
		return false;
	}
}
