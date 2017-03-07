package froggy;

import java.util.ArrayList;

public class FrogLeap {

	private int frogs[];
	private int zeroPos;
	private int count;
	private int finalFrogs[];
	private ArrayList<int[]> visited;

	public FrogLeap(int length, int frogs[], int finalFrogs[]) {
		this.count = length;
		this.visited = new ArrayList<int[]>();
		this.frogs = new int[length];
		this.finalFrogs = new int[length];
		for (int i = 0; i < this.count; i++) {
			this.frogs[i] = frogs[i];
			if (frogs[i] == 0)
				zeroPos = i;
			this.finalFrogs[i] = finalFrogs[i];
		}
	}

	private boolean checkFinal(int currentFrogs[]) {
		for (int i = 0; i < this.count; i++) {
			if (currentFrogs[i] != finalFrogs[i])
				return false;
		}
		return true;
	}

	private boolean compare(int arr1[], int arr2[]) {
		for (int i = 0; i < count; i++) {
			if (arr1[i] != arr2[i])
				return false;
		}
		return true;
	}
	
	private boolean isElement(ArrayList<int[]> list, int arr[]) {
		for (int i = 0; i < list.size(); i++) {
			if (compare(list.get(i), arr))
				return true;
		}
		return false;
	}

	private void swap(int x, int y) {
		int temp = frogs[x];
		frogs[x] = frogs[y];
		frogs[y] = temp;
	}

	public boolean doLeap() {
		if (checkFinal(frogs))
			return true;
		for (int i: new int[]{-2,-1,1,2}){
			System.out.println(i);
			if (zeroPos + i >= 0 && frogs[zeroPos + i] == 1) {
				swap(zeroPos, zeroPos + i);
				zeroPos = zeroPos + i;
				if (checkFinal(frogs)) {
					System.out.println(frogs.toString());
					return true;
				}
				if (isElement(visited, frogs)) {
					continue;
				} else {
					
					visited.add(frogs.clone());
				}
				if (doLeap()) {
					return true;
				} else {
					swap(zeroPos, zeroPos - i);
					zeroPos = zeroPos - i;
					doLeap();
				}
			}
		}
		return false;
	}

	public static void main(String[] args) {
		int frogs[] = new int[] { 1, 1, 1, 0, 2, 2, 2 };
		int finalFrogs[] = new int[] { 2, 2, 2, 0, 1, 1, 1 };
		FrogLeap frogLeap = new FrogLeap(7, frogs, finalFrogs);
		frogLeap.doLeap();

	}
}
