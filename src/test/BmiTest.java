import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;

public class BmiTest {

	private static Bmi bmi;
	private static Testing testing;
	private final static String directory = "Bmi";
	
	@BeforeClass
	public static void setUp() {
		bmi = new Bmi();
		assertNotNull(bmi);
		testing = new Testing(directory);
	}
	
	@Test
	public void testPrints() throws IOException {
		testing.runTest(() -> System.out.println(Bmi.prints()), "prints");
	}
	
	@Test
	public void testUnits() throws IOException {
		testing.runTest(() -> Bmi.isMetric(), "unitsMetric");
		testing.runTest(() -> Bmi.isMetric(), "unitsImperial");
	}
	
}
