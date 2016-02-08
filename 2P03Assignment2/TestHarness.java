/*
 *Tyler Martin
 *
 *4836318
 *
 *Friday October 19,2012
 *
 *This is my main class, it is in charge of loading the file. That was an impossible task I spent more time trying to do that then
 *anything else.
 *
 */

import java.io.*;
import java.util.Scanner;
import java.util.*;
import java.io.FileNotFoundException;

class TestHarness {

	Bst beast;  // My binary search tree class does the majority of the work

	public TestHarness() {

		Scanner scanner;

		beast = new Bst();

		try{
			scanner = new Scanner(new File("a2_data.txt"));

			while (scanner.hasNextLine()){
				String name = scanner.nextLine();
				beast.insertB(name);

			}
		}
			catch (FileNotFoundException e) { // no idea what a printstacktrace is but it would not work without this.
				e.printStackTrace();
			}
			beast.printPreT ();

	}
	public static void main ( String args[] ) { new TestHarness(); };
}
