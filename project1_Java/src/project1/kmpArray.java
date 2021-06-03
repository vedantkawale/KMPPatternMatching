package project1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class kmpArray {
	public kmpArray() {

	}

	public static String system(String S, List<List<String>> ls) {

		List<Integer> al = new ArrayList<>();
		List<String> retrivew = new ArrayList<>();
		StringBuilder sb = new StringBuilder();
		sb.append(S);
		System.out.println(ls);
		List<Boolean> Indexchanged = new ArrayList<>();
		for (int i = 0; i < S.length(); i++)
			Indexchanged.add(false);
		// al= Kmp(S,S1,S2);
		for (int i = 0; i < ls.size(); i++) {
			retrivew = ls.get(i);
			al = Kmp(sb.toString(), retrivew.get(0), retrivew.get(1));
			for (int j = 0; j < al.size(); j++) {
				int k = al.get(j);
				int k1 = k + retrivew.get(0).length();
				for (; k < k1; k++) {
					if (Indexchanged.get(k) == true) {
						al.remove(j);
						j--;
						break;
					}
				}

			}
			Indexchanged = AlterBoolean(Indexchanged, retrivew, al);
			sb = AlterString(sb, retrivew, al);

		}
		System.out.println(sb);
		return sb.toString();
	}

	public static StringBuilder AlterString(StringBuilder sb, List<String> retrivew, List<Integer> al) {
		String s1 = retrivew.get(0);
		String s2 = retrivew.get(1);
		int s1_len = s1.length(), s2_len = s2.length();
		int diff = 0, tmp = 0;

		if (s1_len >= s2_len) {
			diff = s1_len - s2_len;
			for (int i = 0; i < al.size(); i++) {
				int j, j1 = 0;
				tmp = al.get(i);
				for (j = tmp; j < tmp + s2_len; j++) {
					sb.setCharAt(j, s2.charAt(j1++));
				}
				sb.delete(j, j + diff);
			}

		} else {
			diff = s2_len - s1_len;

			for (int i = 0; i < al.size(); i++) {
				int j, j1 = 0;
				tmp = al.get(i);

				for (j = tmp; j < tmp + s1_len; j++) {
					sb.setCharAt(j, s2.charAt(j1++));
				}

				diff = s2_len - s1_len;
				while (diff > 0) {
					sb.insert(j, s2.charAt(j1++));
//	    				for(int i1=i+1;i1<al.size();i1++)
//	    				{
//	    					al.set(i1, al.get(i1)+1);
//	    				}
					// sb.append(j,s2.charAt(j1++));
					j++;
					diff--;
				}
			}
		}
		return sb;
	}

	public static List<Boolean> AlterBoolean(List<Boolean> Indexchanged, List<String> retrivew, List<Integer> al) {

		String s1 = retrivew.get(0);
		String s2 = retrivew.get(1);
		int s1_len = s1.length(), s2_len = s2.length();
		int diff = 0, tmp = 0;

		if (s1_len >= s2_len) {
			diff = s1_len - s2_len;
			for (int i = 0; i < al.size(); i++) {
				int j;
				tmp = al.get(i);
				for (j = tmp; j < tmp + s2_len; j++) {
					Indexchanged.set(j, true);
				}
				while (diff > 0) {
					Indexchanged.remove(j);
					diff--;
				}

			}

		} else {
			diff = s2_len - s1_len;
			for (int i = 0; i < al.size(); i++) {
				int j;
				tmp = al.get(i);
				diff = s2_len - s1_len;
				for (j = tmp; j < tmp + s1_len; j++) {
					Indexchanged.set(j, true);
				}
				while (diff > 0) {
					Indexchanged.add(j, true);
					for (int i1 = i + 1; i1 < al.size(); i1++) {
						al.set(i1, al.get(i1) + 1);
					}
					diff--;
				}
			}
		}
		return Indexchanged;
	}

	public static List<Integer> Kmp(String s, String s1, String s2) {
		int j = 0, k;
		int[] arr = kmpArrays(s1);
		List<Integer> al = new ArrayList<>();
		for (int i = 0; i <= s.length() - s1.length(); i++) {
			if (s.charAt(i) == s1.charAt(j)) {
				k = i;
				while (j < s1.length() && s.charAt(k) == s1.charAt(j)) {
					j++;
					k++;
				}
				if (j == s1.length()) {
					al.add(i);
					j = 0;
				} else {
					j = arr[j - 1];
				}
			}
		}
		return al;
	}

	public static int[] kmpArrays(String S) {
		int[] retval = new int[S.length()];
		int i = 0, j = 1;
		while (j < S.length()) {
			if (S.charAt(i) == S.charAt(j)) {
				retval[j] = i + 1;
				i++;
			} else {
				if (i != 0) {
					i--;
					j--;
				} else {
					retval[j] = 0;
				}
			}
			j++;
		}
		return retval;
	}
}
