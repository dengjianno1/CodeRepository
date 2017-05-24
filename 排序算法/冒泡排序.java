package package_a;

import java.util.Arrays;

public class Sort {

	public static void main(String[] args) {
		int[] a={42, 99, 5, 63, 95, 36, 2, 69, 200, 96};
		//ц╟ещ
		for (int i = 1; i < a.length; i++) {
			for(int j=0;j<a.length-i;j++){
				if (a[j]>a[j+1]) {
					int temp=a[j];
					a[j]=a[j+1];
					a[j+1]=temp;
				}
				System.out.println(Arrays.toString(a));
			}
		}
		
	}
}
