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
	
	private final String directory = "Main";
	
	@Test
	public void testMainConstructor() {
		Main main = new Main();
		assertNotNull(main);
	}

	@Test
	public void testPrints() throws IOException {
		Testing.runTest(directory,"prints");
		// TODO: get a general Testing class working with compare() and runTest()
		// that way I dont have to copy those methods to every test file w/ slight changes
	}
	
	@Test
	public void testUserInput() throws IOException {
	}
	
	@Test
	public void testScreenSelect() {
		
	}
	
	@Test
	public void testMain() {
		
	}
	
}
