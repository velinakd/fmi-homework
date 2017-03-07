package froggy;

public class test {

	public static void main(String[] args) {
		int arr1[] = new int[]{1,2,3};
		int arr2[] = arr1.clone();
		arr2[0] = 4;
		arr2[0] = 1;
		System.out.println(arr2 == arr1);
	}

}
