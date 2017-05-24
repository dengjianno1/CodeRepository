package djsoft;

import java.util.ArrayList;
import java.util.List;

public class MergeSort {

	public static void main(String[] args) {
		int[] left={1,3,4,5,9,11,24,25,27};
		int[] right={1,2,7,10,11,26,27,28,29};
		List<Integer> list=mergeSort(left, right);
		for (Integer integer : list) {
			System.out.print(integer+" ");
		}
	}
	public static List<Integer> mergeSort(int[] left,int[] right){
		ArrayList<Integer> list=new ArrayList<>();
		int l=0,r=0;
		while (l<left.length&&r<right.length) {
			if (left[l]<=right[r]) {
				list.add(left[l++]);
			}else {
				list.add(right[r++]);
			}
		}
		if (l!=left.length) {
			while (l<left.length) {
				list.add(left[l++]);
			}
		}
		if (r!=right.length) {
			while (r<right.length) {
				list.add(right[r++]);
			}
		}
		return list;
	}
}
