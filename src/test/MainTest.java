import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.NoSuchElementException;

import org.junit.BeforeClass;
import org.junit.Test;

public class MainTest {
	
	private static Main main;
	private static Testing testing;
	private final static String directory = "Main";
	
	@BeforeClass
	public static void setUp() {
		main = new Main();
		assertNotNull(main);
		testing = new Testing(directory);
	}
	
	@Test
	public void testValidUserInput() throws IOException {
		testing.runTest(() -> Main.userInput(), "launchNutritionLog");
		testing.runTest(() -> Main.userInput(), "launchBmi");
		testing.runTest(() -> Main.userInput(), "launchPace");
		testing.runTest(() -> Main.userInput(), "launchSplit");
		testing.runTest(() -> Main.userInput(), "launchFacts");
		testing.runTest(() -> Main.userInput(), "quit");
	}
	
	@Test(expected = NoSuchElementException.class)
	public void testInvalidUserInput() throws IOException {
		testing.runTest(() -> Main.userInput(), "invalid1");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testInvalidLaunch() {
		Main.launch(-1);
		Main.launch(6);
	}
	
}
