package package_a;

import java.util.Arrays;

public class Sort {

	public static void main(String[] args) {
		int[] a={42, 99, 5, 63, 95, 36, 2, 69, 200, 96};
			//Ñ¡Ôñ
		for (int i = 0; i < a.length; i++) {
			for (int j = i+1; j < a.length; j++) {
				if (a[i]>a[j]) {
					int temp=a[i];
					a[i]=a[j];
					a[j]=temp;
				}
				System.out.println(Arrays.toString(a));
			}
		}
/*			//Ã°ÅÝ
		for (int i = 1; i < a.length; i++) {
			for(int j=0;j<a.length-i;j++){
				if (a[j]>a[j+1]) {
					int temp=a[j];
					a[j]=a[j+1];
					a[j+1]=temp;
				}
				System.out.println(Arrays.toString(a));
			}
		}*/
		
	}

}
