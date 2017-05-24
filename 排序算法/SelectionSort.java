package djsoft;

import java.util.Arrays;

public class SelectionSort {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] ints=new int[100000];
		for (int i = 0; i < ints.length; i++) {
			ints[i]=(int)(Math.random()*1000);
		}
		System.out.println(Arrays.toString(ints));
		long startTime=System.currentTimeMillis();
		for (int i = 0; i < ints.length; i++) {
			for (int j = i+1; j < ints.length; j++) {
				if (ints[i]>=ints[j]) {
					int temp=ints[i];
					ints[i]=ints[j];
					ints[j]=temp;
				}
			}
		}
		long endTime=System.currentTimeMillis();
		System.out.println(Arrays.toString(ints));
		System.out.println((endTime-startTime)/1000.0);
	}

}
