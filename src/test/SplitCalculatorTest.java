import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

public class SplitCalculatorTest {

	private static SplitCalculator splitCalculator;
	private static Testing testing;
	private final static String directory = "SplitCalculator";

	@BeforeClass
	public static void setUp() {
		splitCalculator = new SplitCalculator();
		assertNotNull(splitCalculator);
		testing = new Testing(directory);
	}
	
}
