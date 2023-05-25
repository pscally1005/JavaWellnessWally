import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.BeforeClass;
import org.junit.Test;

public class MainTest {
	
	private static Main main;
	private static Testing testing;
	private final static String directory = "Main";
	private static String expectedFilename;
	private static String outFilename;
	
	/**
	 * @requires String filename
	 * @modifies expectedFilename, outFilename
	 * @effects sets files to correct location for out and expected files
	 * @throws NullPointerException if filename is null
	 * @returns String expectedFilename
	 */
	public static String fileExp(String dir, String filename) throws NullPointerException {
		if(filename == null) throw new NullPointerException();
		return "data/"+dir+"/"+filename+".expected"; // Expected result filename: [filename].expected
	}
	
	/**
	 * @requires String filename
	 * @modifies expectedFilename, outFilename
	 * @effects sets files to correct location for out and expected files
	 * @throws NullPointerException if filename is null
	 * @returns String outFilename
	 */
	public static String fileOut(String dir, String filename) throws NullPointerException {
		if(filename == null) throw new NullPointerException();
		return "data/"+"/"+dir+"/"+filename+".out"; // Output filename: [filename].out
	}
	
	/**
	 * @requires String file to read from
	 * @modifies expectedFilename, outFilename
	 * @effects sets file to correct files to compare to for testing
	 * @throws NullPointerException if file is null
	 * @throws IOException in Testing.compare()
	 * @returns none
	 */
	public static void invalid(Runnable r, String file, Testing t, String dir, String expectedFilename, String outFilename) throws IOException, NullPointerException {
		if(file == null) throw new NullPointerException();
		assertThrows(NoSuchElementException.class, () -> t.runTest(() -> r.run(),file));
		expectedFilename = fileExp(dir,file);
		outFilename = fileOut(dir, file);
		assertTrue(Testing.compare(expectedFilename, outFilename));
	}
	
	@BeforeClass
	public static void setUp() {
		main = new Main();
		assertNotNull(main);
		testing = new Testing(directory);
	}
	
	@Test
	public void testTitle() {
		String expected = ""
				+ "##################\n"
				+ "#                #\n"
				+ "# WELLNESS WALLY #\n"
				+ "#                #\n"
				+ "##################\n";
		assertEquals(expected, Main.title());
	}
	
	@Test
	public void testOptions() {
		String expected = ""
				+ "1: Nutrition Log\n"
				+ "2: BMI Calculator\n"
				+ "3: Pace Calculator\n"
				+ "4: Split Calculator\n"
				+ "5: Nutrition Facts Generator\n"
				+ "0: Exit\n";
		assertEquals(expected, Main.options());
	}
	
	@Test
	public void testValidUserInput() throws IOException {
		testing.runTest(() -> Main.userInput(), "launchNutritionLog");
		testing.runTest(() -> Main.userInput(), "launchBmi");
		testing.runTest(() -> Main.userInput(), "launchPace");
		testing.runTest(() -> Main.userInput(), "launchSplit");
		testing.runTest(() -> Main.userInput(), "launchFacts");
		testing.runTest(() -> Main.userInput(), "quit");
		
		testing.runTest(() -> Main.userInput(), "firstFail");
		testing.runTest(() -> Main.userInput(), "manyFails");
	}
	
	@Test
	public void testInvalidInput() throws IOException {
		invalid(() -> Main.userInput(),"invalidNotAnInt",testing, directory, expectedFilename, outFilename);
		invalid(() -> Main.userInput(),"invalidPositive",testing, directory, expectedFilename, outFilename);
		invalid(() -> Main.userInput(),"invalidNegative",testing, directory, expectedFilename, outFilename);
		invalid(() -> Main.userInput(),"invalidEmpty",testing, directory, expectedFilename, outFilename);
	}
	
}
