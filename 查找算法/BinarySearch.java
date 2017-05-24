package djsoft;

public class BinarySearch {

	public static void main(String[] args) {
		int[] arr={0,1,2,3,4,5,6,7,8,9,10};
		int target=1;
		int start=0;
		int end=arr.length-1;
		System.out.println(binarySearch(arr, target, start, end));
	}

	private static int binarySearch(int[] arr, int target, int start, int end) {
		int middel=0;
		while (end>=start) {
			middel=(start+end)/2;
			if (target>arr[middel]) {
				start=middel+1;
				continue;
			}else if (target<arr[middel]) {
				end=middel-1;
				continue;
			}else {
				return middel;
			}
		}
		return -1;
	}

}
