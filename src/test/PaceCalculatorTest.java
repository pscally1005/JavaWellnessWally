import static org.junit.Assert.assertNotNull;

import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;

public class PaceCalculatorTest {

	private static PaceCalculator paceCalculator;
	private static Testing testing;
	private final static String directory = "Pace";
	private static String expectedFilename;
	private static String outFilename;

	@BeforeClass
	public static void setUp() {
		paceCalculator = new PaceCalculator();
		assertNotNull(paceCalculator);
		testing = new Testing(directory);
	}
	
	@Test
	public void testPrints() throws IOException {
		testing.runTest(() -> System.out.println(PaceCalculator.prints()), "prints");
	}
	
	@Test
	public void testValidSelect() throws IOException {
		testing.runTest(() -> PaceCalculator.select(), "selectDist1");
		testing.runTest(() -> PaceCalculator.select(), "selectDist2");
		
		testing.runTest(() -> PaceCalculator.select(), "selectTime1");
		testing.runTest(() -> PaceCalculator.select(), "selectTime2");
		
		testing.runTest(() -> PaceCalculator.select(), "selectPace1");
		testing.runTest(() -> PaceCalculator.select(), "selectPace2");
		
		testing.runTest(() -> PaceCalculator.select(), "selectMultipleTries");
	}
	
	@Test
	public void testInvalidSelect() throws IOException {
		MainTest.invalid(() -> PaceCalculator.select(), "invalidSelectEmpty", testing, directory, expectedFilename, outFilename);
		MainTest.invalid(() -> PaceCalculator.select(), "invalidSelectSpace", testing, directory, expectedFilename, outFilename);
		MainTest.invalid(() -> PaceCalculator.select(), "invalidSelectLetter1", testing, directory, expectedFilename, outFilename);
		MainTest.invalid(() -> PaceCalculator.select(), "invalidSelectLetter2", testing, directory, expectedFilename, outFilename);
		MainTest.invalid(() -> PaceCalculator.select(), "invalidSelectRandomString", testing, directory, expectedFilename, outFilename);
	}
	
	@Test
	public void testValidDistance() throws IOException {
		testing.runTest(() -> PaceCalculator.enterDist(true), "distanceSmallMetric");
		testing.runTest(() -> PaceCalculator.enterDist(false), "distanceSmallImperial");
		
		testing.runTest(() -> PaceCalculator.enterDist(true), "distanceMedMetric");
		testing.runTest(() -> PaceCalculator.enterDist(false), "distanceMedImperial");
		
		testing.runTest(() -> PaceCalculator.enterDist(true), "distanceLargeMetric");
		testing.runTest(() -> PaceCalculator.enterDist(false), "distanceLargeImperial");
	}
	
	@Test
	public void testInvalidDistance() throws IOException {
		MainTest.invalid(() -> PaceCalculator.enterDist(true), "distanceZeroMetric", testing, directory, expectedFilename, outFilename);
		MainTest.invalid(() -> PaceCalculator.enterDist(false), "distanceZeroImperial", testing, directory, expectedFilename, outFilename);
		
		MainTest.invalid(() -> PaceCalculator.enterDist(true), "distanceNegativeMetric", testing, directory, expectedFilename, outFilename);
		MainTest.invalid(() -> PaceCalculator.enterDist(false), "distanceNegativeImperial", testing, directory, expectedFilename, outFilename);
		
		MainTest.invalid(() -> PaceCalculator.enterDist(true), "distanceTooLargeMetric", testing, directory, expectedFilename, outFilename);
		MainTest.invalid(() -> PaceCalculator.enterDist(false), "distanceTooLargeImperial", testing, directory, expectedFilename, outFilename);
		
		MainTest.invalid(() -> PaceCalculator.enterDist(true), "distanceNotNumMetric", testing, directory, expectedFilename, outFilename);
		MainTest.invalid(() -> PaceCalculator.enterDist(false), "distanceNotNumImperial", testing, directory, expectedFilename, outFilename);
	}
	
	@Test
	public void testValidTime() throws IOException {
		testing.runTest(() -> PaceCalculator.enterTimePace(true,true), "timeValid1");
	}
	
	@Test
	public void testInvalidTime() throws IOException {
		MainTest.invalid(() -> PaceCalculator.enterTimePace(true,true), "timeInvalid1", testing, directory, expectedFilename, outFilename);
	}
	
	@Test
	public void testValidPace() throws IOException {
		testing.runTest(() -> PaceCalculator.enterTimePace(true,false), "paceValid1");
	}
	
	@Test
	public void testInvalidPace() throws IOException {
		MainTest.invalid(() -> PaceCalculator.enterTimePace(true,false), "paceInvalid1", testing, directory, expectedFilename, outFilename);
	}
	
}
