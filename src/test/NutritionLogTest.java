import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.BufferedReader;
import java.io.FileReader;

import org.junit.BeforeClass;
import org.junit.Test;

public class NutritionLogTest {

	private static NutritionLog nutritionLog;
	private static Testing testing;
	private final static String directory = "NutritionLog";
	
	@BeforeClass
	public static void setUp() {
		nutritionLog = new NutritionLog();
		assertNotNull(nutritionLog);
		testing = new Testing(directory);
	}
	
	@Test
	public void testPrints() throws IOException {
		testing.runTest(() -> System.out.println(NutritionLog.prints()), "prints");
	}
	
	@Test
	public void testValidNum() throws IOException {
		testing.runTest(() -> NutritionLog.enterNum(), "enter1");
	}
	
	@Test
	public void testInvalidNum() throws IOException {
		testing.runTest(() -> NutritionLog.enterNum(), "invalidNotInt");
		testing.runTest(() -> NutritionLog.enterNum(), "invalidPositive");
		testing.runTest(() -> NutritionLog.enterNum(), "invalidNegative");	
		testing.runTest(() -> NutritionLog.enterNum(), "invalidEmpty");
		testing.runTest(() -> NutritionLog.enterNum(), "invalidZero");
	}
	
	@Test
	public void testEnterDesc() throws IOException {
		testing.runTest(() -> NutritionLog.enterDesc(1), "enterDesc1");
	}
	
	@Test
	public void testWriteToFile() throws IOException {
		int num = 1;
		String desc = "I ate the onion";
		NutritionLog.writeToFile(num,desc);
		
		String filename = "data/log.txt";
		FileReader fileReader = new FileReader(filename);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		
		String last = "";
		String line = "";
		while((line = bufferedReader.readLine()) != null) last = line;
		bufferedReader.close();
		
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("MMMM-dd-yyyy");
		String strDate = formatter.format(date);
		
		String expected = "[" + strDate + "]: (1) I ate the onion";
		assertEquals(expected,last);
	}
	
	@Test
	public void testExit() throws IOException {
		testing.runTest(() -> NutritionLog.exit(), "exitFalse1");
		testing.runTest(() -> NutritionLog.exit(), "exitFalse2");
		
		testing.runTest(() -> NutritionLog.exit(), "exitTrue1");
		testing.runTest(() -> NutritionLog.exit(), "exitTrue2");
		testing.runTest(() -> NutritionLog.exit(), "exitTrue3");
		testing.runTest(() -> NutritionLog.exit(), "exitTrue4");
	}
	
}
