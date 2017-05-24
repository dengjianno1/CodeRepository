package djsoft;

import java.util.LinkedList;

public class BinaryInsertSort {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LinkedList<Integer> arrList=new LinkedList<>();
		for (int i = 0; i < 10; i++) {
			arrList.add(i);
		}
		for (Integer integer : arrList) {
			System.out.print(integer+"  ");
		}
		System.out.println();
		
		binaryInsertSort(arrList, 0, arrList.size(), 9);
		
		for (Integer integer : arrList) {
			System.out.print(integer+"  ");
		}
		System.out.println();
	}
	public static void binaryInsertSort(LinkedList<Integer> list,int start,int end,int target) {
		if (end-start<=1) {
			list.add(start+1, target);
			return;
		}
		int middle=(start+end)/2;
		if (target>list.get(middle)) {
			binaryInsertSort(list, middle+1, end, target);
		}else if (target<list.get(middle)) {
			binaryInsertSort(list, start, middle-1, target);
		}else {
			list.add(middle+1, target);
			return;
		}
	}

}
