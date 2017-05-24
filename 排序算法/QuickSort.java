package djsoft;

import java.util.Arrays;

public class QuickSort {

	public static void main(String[] args) {
		int[] arr = new int[100000];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) (Math.random() * 1000);
		}
		//int[] arr={8,9,4,1,3,10,2,7,5,6};
		long startTime=System.currentTimeMillis();
		QuickSort(arr, 0, arr.length-1);
		long endTime=System.currentTimeMillis();
		System.out.println(Arrays.toString(arr));
		System.out.println((endTime-startTime)/1000.0);
	}
	public static void QuickSort(int[] arr,int start,int end){
		int low=start;
		int high=end;
		if (low>=high) {
			return;
		}
		int key=arr[low];
		while (low<high) {
			while (high>low) {
				if (arr[high]<key) {
					arr[low]=arr[high];
					break;
				}
				high--;
			}
			while (low<high) {
				if (arr[low]>key) {
					arr[high]=arr[low];
					arr[low]=key;
					break;
				}
				low++;
			}
			if (low==high) {
				arr[low]=key;
			}
		}
		QuickSort(arr, start, low-1);
		QuickSort(arr, high+1, end);
	}
}
