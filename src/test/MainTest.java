import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.concurrent.Callable;
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
	
	private void files(String filename) {
		expectedFilename = "data/"+"/"+directory+"/"+filename+".expected"; // Expected result filename: [filename].expected
		outFilename = "data/"+"/"+directory+"/"+filename+".out"; // Output filename: [filename].out
	}
	
	@BeforeClass
	public static void setUp() {
		main = new Main();
		assertNotNull(main);
		testing = new Testing(directory);
	}
	
	@Test
	public void testTitleAndOptions() throws Exception {
		ExecutorService executor = Executors.newCachedThreadPool();
	    Future<String> task1 = executor.submit(Main::title);
	    String result = task1.get();
	    testing.write("title", result);
	    assertTrue(Testing.compare("data/Main/title.expected", "data/Main/title.out"));
	    
//	    testing.runTest(() -> task1, "title");
	    
		
		
//		testing.runTest(() -> System.out.println(Main.title()), "title");
//		testing.runTest(() -> System.out.println(Main.options()), "options");
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
	public void testInvalidNegative() throws IOException {
		assertThrows(NoSuchElementException.class, () -> testing.runTest(() -> Main.userInput(), "invalidNegative"));
		files("invalidNegative");
		assertTrue(Testing.compare(expectedFilename, outFilename));
	}
	
	@Test
	public void testInvalidPositive() throws IOException {
		assertThrows(NoSuchElementException.class, () -> testing.runTest(() -> Main.userInput(), "invalidPositive"));
		files("invalidPositive");
		assertTrue(Testing.compare(expectedFilename, outFilename));
	}
	
	@Test
	public void testInvalidEmpty() throws IOException {
		assertThrows(NoSuchElementException.class, () -> testing.runTest(() -> Main.userInput(), "invalidEmpty"));
		files("invalidEmpty");
		assertTrue(Testing.compare(expectedFilename, outFilename));
	}
	
	@Test
	public void testInvalidNotInt() throws IOException {
		assertThrows(NoSuchElementException.class, () -> testing.runTest(() -> Main.userInput(), "invalidNotAnInt"));
		files("invalidNotAnInt");
		assertTrue(Testing.compare(expectedFilename, outFilename));
	}
	
	@Test
	public void testInvalidLaunch() {
		assertThrows(IllegalArgumentException.class, () -> Main.launch(-1));
		assertThrows(IllegalArgumentException.class, () -> Main.launch(6));
	}
	
	@Test
	public void testValidLaunch() throws IOException {
		Main.launch(1);
		Main.launch(2);
		Main.launch(3);
		Main.launch(4);
		Main.launch(5);
		Main.launch(0);
	}
	
}
