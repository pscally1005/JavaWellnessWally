import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

public class NutritionFactsTest {
	
	private static NutritionFacts nutritionFacts;
	private static Testing testing;
	private final static String directory = "NutritionFacts";

	@BeforeClass
	public static void setUp() {
		nutritionFacts = new NutritionFacts();
		assertNotNull(nutritionFacts);
		testing = new Testing(directory);
	}
}
