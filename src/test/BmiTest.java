import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;

import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;

public class BmiTest {

	private static Bmi bmi;
	private static Testing testing;
	private final static String directory = "Bmi";
	private static String expectedFilename;
	private static String outFilename;
	public static double EPSILON = 0.000000000001d;
	
	@BeforeClass
	public static void setUp() {
		bmi = new Bmi();
		assertNotNull(bmi);
		testing = new Testing(directory);
	}
	
	@Test
	public void testPrints() throws IOException {
		String expected = ""
				+ "BMI CALCULATOR\n\n"
				+ "This will allow you to enter your height and weight to calculate BMI\n";
		assertEquals(expected, Bmi.prints());
	}
	
	@Test
	public void testValidUnits() throws IOException {
		testing.runTest(() -> Bmi.isMetric(), "unitsMetric");
		testing.runTest(() -> Bmi.isMetric(), "unitsImperial");
	}
	
	@Test
	public void testInvalidUnits() throws IOException {
		MainTest.invalid(() -> Bmi.isMetric(), "unitsInvalidPositive", testing, directory, expectedFilename, outFilename);
		MainTest.invalid(() -> Bmi.isMetric(), "unitsInvalidNegative", testing, directory, expectedFilename, outFilename);
		MainTest.invalid(() -> Bmi.isMetric(), "unitsInvalidZero", testing, directory, expectedFilename, outFilename);
		MainTest.invalid(() -> Bmi.isMetric(), "unitsInvalidEmpty", testing, directory, expectedFilename, outFilename);
		MainTest.invalid(() -> Bmi.isMetric(), "unitsInvalidNotInt", testing, directory, expectedFilename, outFilename);
	}
	
	@Test
	public void testValidHeight() throws IOException {
		testing.runTest(() -> Bmi.enterHeight(true), "metricHeightSmall");
		testing.runTest(() -> Bmi.enterHeight(true), "metricHeightMed");
		testing.runTest(() -> Bmi.enterHeight(true), "metricHeightLarge");
		
		testing.runTest(() -> Bmi.enterHeight(false), "imperialHeightSmall");
		testing.runTest(() -> Bmi.enterHeight(false), "imperialHeightMed");
		testing.runTest(() -> Bmi.enterHeight(false), "imperialHeightLarge");
	}
	
	@Test
	public void testInvalidHeight() throws IOException {
		MainTest.invalid(() -> Bmi.enterHeight(false), "heightEmpty1", testing, directory, expectedFilename, outFilename);
		MainTest.invalid(() -> Bmi.enterHeight(true), "heightEmpty2", testing, directory, expectedFilename, outFilename);
		
		MainTest.invalid(() -> Bmi.enterHeight(false), "heightNegative1", testing, directory, expectedFilename, outFilename);
		MainTest.invalid(() -> Bmi.enterHeight(true), "heightNegative2", testing, directory, expectedFilename, outFilename);
		
		MainTest.invalid(() -> Bmi.enterHeight(false), "heightTooLarge1", testing, directory, expectedFilename, outFilename);
		MainTest.invalid(() -> Bmi.enterHeight(true), "heightTooLarge2", testing, directory, expectedFilename, outFilename);
		
		MainTest.invalid(() -> Bmi.enterHeight(false), "heightNotNum1", testing, directory, expectedFilename, outFilename);
		MainTest.invalid(() -> Bmi.enterHeight(true), "heightNotNum2", testing, directory, expectedFilename, outFilename);
	}
	
	@Test
	public void testValidWeight() throws IOException {
		testing.runTest(() -> Bmi.enterWeight(true), "metricWeightSmall");
		testing.runTest(() -> Bmi.enterWeight(true), "metricWeightMed");
		testing.runTest(() -> Bmi.enterWeight(true), "metricWeightLarge");
		
		testing.runTest(() -> Bmi.enterWeight(false), "imperialWeightSmall");
		testing.runTest(() -> Bmi.enterWeight(false), "imperialWeightMed");
		testing.runTest(() -> Bmi.enterWeight(false), "imperialWeightLarge");
	}
	
	@Test
	public void testInvalidWeight() throws IOException {
		MainTest.invalid(() -> Bmi.enterWeight(false), "weightEmpty1", testing, directory, expectedFilename, outFilename);
		MainTest.invalid(() -> Bmi.enterWeight(true), "weightEmpty2", testing, directory, expectedFilename, outFilename);
		
		MainTest.invalid(() -> Bmi.enterWeight(false), "weightNegative1", testing, directory, expectedFilename, outFilename);
		MainTest.invalid(() -> Bmi.enterWeight(true), "weightNegative2", testing, directory, expectedFilename, outFilename);
		
		MainTest.invalid(() -> Bmi.enterWeight(false), "weightTooLarge1", testing, directory, expectedFilename, outFilename);
		MainTest.invalid(() -> Bmi.enterWeight(true), "weightTooLarge2", testing, directory, expectedFilename, outFilename);
		
		MainTest.invalid(() -> Bmi.enterWeight(false), "weightNotNum1", testing, directory, expectedFilename, outFilename);
		MainTest.invalid(() -> Bmi.enterWeight(true), "weightNotNum2", testing, directory, expectedFilename, outFilename);
	}
	
	@Test
	public void testBmiCalc() {
		double h = 68.75 * Bmi.HEIGHT_TO_METRIC;
		double w = 120.4 * Bmi.WEIGHT_TO_METRIC;
		assertEquals(17.91, Bmi.calcBmi(h, w), EPSILON);
		
		h = 1 * Bmi.HEIGHT_TO_METRIC;
		w = 1 * Bmi.WEIGHT_TO_METRIC;
		assertEquals(702.95, Bmi.calcBmi(h, w), EPSILON);
		
		h = 63 * Bmi.HEIGHT_TO_METRIC;
		w = 105 * Bmi.WEIGHT_TO_METRIC;
		assertEquals(18.6, Bmi.calcBmi(h, w), EPSILON);
		
		h = 69 * Bmi.HEIGHT_TO_METRIC;
		w = 175 * Bmi.WEIGHT_TO_METRIC;
		assertEquals(25.84, Bmi.calcBmi(h, w), EPSILON);
	}
	
	@Test
	public void testInvalidBmi() {
		assertThrows(IllegalArgumentException.class, () -> Bmi.calcBmi(0,120.4));
		assertThrows(IllegalArgumentException.class, () -> Bmi.calcBmi(69,0));
		assertThrows(IllegalArgumentException.class, () -> Bmi.calcBmi(0,0));
	}
	
	@Test
	public void testValidWeightClass() {
		assertEquals("UNDERWEIGHT", Bmi.weightClass(17.91));
		assertEquals("NORMAL", Bmi.weightClass(23.57));
		assertEquals("OVERWEIGHT", Bmi.weightClass(29.999));
		assertEquals("OBESE", Bmi.weightClass(36));
	}
	
	@Test
	public void testInvalidWeightClass() {
		assertThrows(IllegalArgumentException.class, () -> Bmi.weightClass(-19));
	}
	
	@Test
	public void testRound() {
		assertEquals(1.6, Bmi.round(1.567, 1), EPSILON);
		assertEquals(2, Bmi.round(1.567, 0), EPSILON);
		assertEquals(1.57, Bmi.round(1.567, 2), EPSILON);
		assertEquals(1.567, Bmi.round(1.567, 3), EPSILON);
		assertEquals(1.567, Bmi.round(1.567, 4), EPSILON);
		assertEquals(1.567, Bmi.round(1.567, 10), EPSILON);
		assertThrows(IllegalArgumentException.class, () -> Bmi.round(1.2345, -6));
	}
}
