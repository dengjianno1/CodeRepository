package package_a;

import java.util.ArrayList;
import java.util.List;

public class Leap {
	//�ܱ�400��������������ܱ�4����
	public boolean isLeapYear(int n){
		if (n%400==0||(n%100!=0&&n%4==0)) {
			return true;
		}
		return false;
	}
}
