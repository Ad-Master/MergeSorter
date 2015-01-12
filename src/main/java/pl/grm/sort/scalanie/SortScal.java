package pl.grm.sort.scalanie;

import java.io.*;
import java.util.*;

public class SortScal {
	ArrayList<Integer>		ciag	= new ArrayList<Integer>();
	private int[]			ciagStart;
	private int[]			ciagSorted;
	private int[]			ciagTemp;
	private static SortScal	instance;
	
	public static void main(String[] args) throws Exception {
		instance = new SortScal();
		System.out.println("Podaj ciag oddzielajac elementy ciagu spacja: ");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean correct;
		String[] strings;
		do {
			correct = true;
			String inputString = br.readLine();
			strings = inputString.split(" ");
			
			if (strings.length <= 1) {
				correct = false;
				System.out.println("Ciag za krotki!\n Podaj ponownie");
			} else {
				for (String string : strings) {
					if (!string.matches("[0-9]+")) {
						System.out.println("Zly ciag! \n Podaj ponownie");
						correct = false;
					}
				}
			}
		}
		while (correct == false);
		for (int i = 0; i < strings.length; i++) {
			instance.ciag.add(Integer.parseInt(strings[i]));
		}
		instance.startMerge();
		for (Integer inter : instance.ciagSorted) {
			System.out.print(inter + " | ");
		}
	}
	
	public void startMerge() {
		ciagStart = new int[ciag.size()];
		for (int i = 0; i < ciagStart.length; i++) {
			ciagStart[i] = ciag.get(i).intValue();
		}
		ciagSorted = ciagStart.clone();
		ciagTemp = new int[ciagStart.length];
		MergeSort(0, ciagStart.length - 1);
	}
	
	public void MergeSort(int i_start, int i_end) {
		int i_podCiag, ind1, ind2, i;
		i_podCiag = (i_start + i_end + 1) / 2;
		if (i_podCiag - i_start > 1) {
			MergeSort(i_start, i_podCiag - 1);
		}
		if (i_end - i_podCiag > 0) {
			MergeSort(i_podCiag, i_end);
		}
		ind1 = i_start;
		ind2 = i_podCiag;
		for (i = i_start; i <= i_end; i++) {
			if ((ind1 == i_podCiag) || ((ind2 <= i_end) && (ciagSorted[ind1] > ciagSorted[ind2]))) {
				ciagTemp[i] = ciagSorted[ind2++];
			} else {
				ciagTemp[i] = ciagSorted[ind1++];
			}
		}
		for (i = i_start; i <= i_end; i++) {
			ciagSorted[i] = ciagTemp[i];
		}
	}
}