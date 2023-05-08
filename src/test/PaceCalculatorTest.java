import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

public class PaceCalculatorTest {

	private static PaceCalculator paceCalculator;
	private static Testing testing;
	private final static String directory = "PaceCalculator";

	@BeforeClass
	public static void setUp() {
		paceCalculator = new PaceCalculator();
		assertNotNull(paceCalculator);
		testing = new Testing(directory);
	}
	
}
