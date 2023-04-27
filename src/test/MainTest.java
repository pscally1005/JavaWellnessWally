import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.Test;

public class MainTest {
	
	@Test
	public void testMainConstructor() {
		Main main = new Main();
		assertNotNull(main);
	}

	@Test
	public void testQuit() throws IOException {
		Testing.runTest("main_quit");
	}
	
	@Test
	public void testValidUserInput() throws IOException {
		Testing.runTest("main_launchNutritionLog");
	}
	
}
