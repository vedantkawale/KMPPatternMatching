package project1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class testcases {
	/**
	 * First five letter of the line keep as the format in the text file // with
	 * ending double quotes as per below example for string // S = "Data....." L =
	 * {{},{}}.
	 */
	// if string is extra trim align the substring according to input. go to
	// variable name "result" in Line 74.
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new FileReader("test.txt")); // Input file
		String str;
		File myObj = new File("Output.txt");
		myObj.createNewFile();
		FileOutputStream outputStream = new FileOutputStream("Output.txt");
		OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-16");
		BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
		kmpArray km = new kmpArray();
		List<String> al = new ArrayList<>();
		// S in input file for string L is for list of words
		// Put test cases in array list separated by each line
		while ((str = in.readLine()) != null) {
			al.add(str.replaceAll("“", "\"").replaceAll("”", "\""));
		}
		// System.out.println(al);
		for (int i = 1; i < al.size(); i += 2) {
			// increment by 2 to process get the word in arraylist and skip message string.
			List<List<String>> ls = new ArrayList<>();
			String sr = al.get(i);

			if (sr.charAt(0) == 'L') // taking list of variables
			{

				String[] srArr = sr.replaceAll("\\}", "").replaceAll("\\,", "").split("\\{");

				for (int j = 1; j < srArr.length; j++) {
					String[] srArr1 = srArr[j].split("\"");
					List<String> al1 = new ArrayList<>();
					for (int k = 0; k < srArr1.length; k++) {

						if (srArr1[k].replaceAll(" ", "").length() > 0) {
							// remove array with spaces

							al1.add(srArr1[k].trim());
						}

					}

					if (al1.size() >= 2)
						ls.add(al1);
				}

			} else {
				System.out.println("Input is not in correct format");
			}
			System.out.println(al.get(i - 1));
			// if string is extra trim align the substring according to input al.get(i -
			// 1).substring(val, al.get(i - 1).length() - val)
			String result = km.system(al.get(i - 1).substring(7, al.get(i - 1).length() - 1), ls);

			bufferedWriter.write(result);
			bufferedWriter.newLine();
			// myWriter.write("result");
		}
		bufferedWriter.close();
	}

}
