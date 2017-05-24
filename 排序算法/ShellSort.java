package djsoft;

import java.util.Arrays;

public class ShellSort {

	public static void main(String[] args) {
		int[] arr = new int[100000];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) (Math.random() * 1000);
		}
		//int[] arr = { 49, 38, 65, 97, 76, 13, 27, 49, 55, 04 };
		long startTime=System.currentTimeMillis();
		int d = arr.length >> 1;
		while (d >= 1) {
			for (int i = 0; i + d < arr.length; i++) {
				for (int j = i + d;j < arr.length;j+=d) {
					int temp = arr[j];
					int k=0;
					for (k = j-d; k >=0&&arr[k]>temp; k-=d) {
						arr[k+d]=arr[k];
					}
					arr[k+d]=temp;
				}
			}
			d = d >> 1;
		}
		long endTime=System.currentTimeMillis();
		System.out.println(Arrays.toString(arr));
		System.out.println((endTime-startTime)/1000.0);
	}
}
