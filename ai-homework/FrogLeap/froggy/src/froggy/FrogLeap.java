package froggy;

import java.util.Arrays;

public class FrogLeap {

	private final int leaves = 9;
	private int frogs[];
	private int zeroPos;
	private int finalFrogs[];

	public FrogLeap(int frogs[], int finalFrogs[]) {
		this.frogs = new int[leaves];
		this.finalFrogs = new int[leaves];
		for (int i = 0; i < this.leaves; i++) {
			this.frogs[i] = frogs[i];
			if (frogs[i] == 0)
				zeroPos = i;
			this.finalFrogs[i] = finalFrogs[i];
		}
	}

	private boolean checkFinal(int currentFrogs[]) {
		for (int i = 0; i < this.leaves; i++) {
			if (currentFrogs[i] != finalFrogs[i])
				return false;
		}
		return true;
	}

	private void swap(int x, int y) {
		int temp = frogs[x];
		frogs[x] = frogs[y];
		frogs[y] = temp;
	}

	public boolean doLeap() {
		if (checkFinal(frogs))
			return true;
		if (zeroPos >= 2 && frogs[zeroPos - 2] == 1) {
			swap(zeroPos, zeroPos - 2);
			zeroPos = zeroPos - 2;
				if(doLeap()) {
					System.out.println(Arrays.toString(frogs));
					zeroPos = zeroPos + 2;
					swap(zeroPos, zeroPos - 2);
					return true;
				} else {
					zeroPos = zeroPos + 2;
					swap(zeroPos, zeroPos - 2);
				}
		}
		if (zeroPos >= 1 && frogs[zeroPos - 1] == 1) {
			swap(zeroPos, zeroPos - 1);
			zeroPos = zeroPos - 1;
				if(doLeap()) {
					System.out.println(Arrays.toString(frogs));
					zeroPos = zeroPos + 1;
					swap(zeroPos, zeroPos - 1);	
					return true;
				} else {
					zeroPos = zeroPos + 1;
					swap(zeroPos, zeroPos - 1);				
				}
		}
		if (zeroPos <= (leaves - 2) && frogs[zeroPos + 1] == 2) {
			swap(zeroPos, zeroPos + 1);
			zeroPos = zeroPos + 1;
				if(doLeap()) {
					System.out.println(Arrays.toString(frogs));
					zeroPos = zeroPos - 1;
					swap(zeroPos, zeroPos + 1);	
					return true;
				} else {
					zeroPos = zeroPos - 1;
				swap(zeroPos, zeroPos + 1);		
				}
		}
		
		if (zeroPos <= (leaves - 3) && frogs[zeroPos + 2] == 2) {
			swap(zeroPos, zeroPos + 2);
			zeroPos = zeroPos + 2;
				if(doLeap()) {
					System.out.println(Arrays.toString(frogs));
					zeroPos = zeroPos - 2;
					swap(zeroPos, zeroPos + 2);
					return true;
				} else {
					zeroPos = zeroPos - 2;
				swap(zeroPos, zeroPos + 2);
			}
		}
		return false;
	}
	
	
	public static void main(String[] args) {
		int frogs[] = new int[]{1,1,1,1,0,2,2,2,2};
		int finalFrogs[] = new int[]{2,2,2,2,0,1,1,1,1};
		FrogLeap frogLeap = new FrogLeap(frogs, finalFrogs);
		frogLeap.doLeap();

	}
}
