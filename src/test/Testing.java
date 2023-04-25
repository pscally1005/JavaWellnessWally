import static org.junit.Assert.assertTrue;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

public class Testing {

	// compare() and runTests() copied from CampusPathsTest (PSoft hw7)
	
		/**
		 * @requires Strings file1 and file2
		 * @modifies none
		 * @effects none
		 * @throws IOException if file is invalid
		 * @returns true if file1 = file2, false otherwise
		 */	
		/* compares two text files, line by line */
		static boolean compare(String file1, String file2) throws IOException {
			BufferedReader is1 = new BufferedReader(new FileReader(file1)); // Decorator design pattern!
			BufferedReader is2 = new BufferedReader(new FileReader(file2));
			String line1, line2;
			boolean result = true;
			while ((line1=is1.readLine()) != null) {
				line2 = is2.readLine();
				if (line2 == null) {
					System.out.println(file1+" longer than "+file2);
					result = false;
					break;
				}
				if (!line1.equals(line2)) {
					System.out.println("Lines: "+line1+" and "+line2+" differ.");
					result = false;
					break;
				}
			}
			if (result && is2.readLine() != null) {
				System.out.println(file1+" shorter than "+file2);
				result = false;
			}
			is1.close();
			is2.close();
			return result;
		}
		
		/**
		 * @requries String filename
		 * @modifies System.in, System.out
		 * @effects sets up input and output for testing
		 * @throws IOException if filename is invalid
		 * @returns none
		 */
		static void runTest(String directory, String filename) throws IOException {
			InputStream in = System.in;
			PrintStream out = System.out;
			String inFilename = "data/"+directory+"/"+filename+".test"; // Input filename: [filename].test here  
			String expectedFilename = "data/"+directory+"/"+filename+".expected"; // Expected result filename: [filename].expected
			String outFilename = "data/"+directory+"/"+filename+".out"; // Output filename: [filename].out
			BufferedInputStream is = new BufferedInputStream(new FileInputStream(inFilename));
			System.setIn(is); // redirects standard input to a file, [filename].test 
			PrintStream os = new PrintStream(new FileOutputStream(outFilename));
			System.setOut(os); // redirects standard output to a file, [filename].out 
			Main.main(null); // Call to YOUR main. May have to rename.
			System.setIn(in); // restores standard input
			System.setOut(out); // restores standard output
			assertTrue(compare(expectedFilename, outFilename)); 		
		}
	
}
