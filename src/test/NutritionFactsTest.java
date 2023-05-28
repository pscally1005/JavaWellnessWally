import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;

public class NutritionFactsTest {
	
	private static NutritionFacts nutritionFacts;
	private static Testing testing;
	private final static String directory = "NutritionFacts";
	private static String expectedFilename;
	private static String outFilename;

	@BeforeClass
	public static void setUp() {
		nutritionFacts = new NutritionFacts();
		assertNotNull(nutritionFacts);
		testing = new Testing(directory);
	}
	
	@Test
	public void testPrint() {
		String expected = ""
				+ "NUTRITION FACTS GENERATOR\n\n"
				+ "This will let you calculate the nutrition facts for a custom recipe\n";
		assertEquals(expected, NutritionFacts.prints());
	}
	
	@Test
	public void testName() throws IOException {
		testing.runTest(() -> NutritionFacts.enterName(), "name1");
		testing.runTest(() -> NutritionFacts.enterName(), "name2");
		testing.runTest(() -> NutritionFacts.enterName(), "name3");
	}
	
	@Test
	public void testServings() throws IOException {
		testing.runTest(() -> NutritionFacts.enterServings(), "servings1");
		testing.runTest(() -> NutritionFacts.enterServings(), "servings2");
		testing.runTest(() -> NutritionFacts.enterServings(), "servings3");
	}
	
	@Test
	public void testInvalidServings() throws IOException {
		MainTest.invalid(() -> NutritionFacts.enterServings(), "servingEmpty", testing, directory, expectedFilename, outFilename);
		MainTest.invalid(() -> NutritionFacts.enterServings(), "servingNegative", testing, directory, expectedFilename, outFilename);
		MainTest.invalid(() -> NutritionFacts.enterServings(), "servingZero", testing, directory, expectedFilename, outFilename);
		MainTest.invalid(() -> NutritionFacts.enterServings(), "servingTooLarge", testing, directory, expectedFilename, outFilename);
	}
}
