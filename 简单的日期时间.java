package learn.时间;

import java.time.LocalDate;
import java.time.LocalTime;

public class 日期 {

	public static void main(String[] args) {
		LocalDate today=LocalDate.now();
		System.out.println(today);
		LocalTime now=LocalTime.now();
		System.out.println(now);
	}

}
