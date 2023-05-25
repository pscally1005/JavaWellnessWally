import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;

public class SplitCalculatorTest {

	private static SplitCalculator splitCalculator;

	@BeforeClass
	public static void setUp() {
		splitCalculator = new SplitCalculator();
		assertNotNull(splitCalculator);
	}
	
	@Test
	public void testPrints() {
		String expected = ""
				+ "SPLIT CALCULATOR\n\n"
				+ "This will allow you to enter a pace, and will give you times for certain distances\n";
		assertEquals(expected, SplitCalculator.prints());
	}
	
	@Test
	public void testTimes() {
		String expected = ""
				+ "100 m:   00:00:29\n"
				+ "200 m:   00:00:59\n"
				+ "400 m:   00:01:59\n"
				+ "800 m:   00:03:58\n"
				+ "1 km:    00:04:58\n"
				+ "1 mi:    00:08:00\n"
				+ "5 km:    00:24:50\n"
				+ "10 km:   00:49:40\n"
				+ "13.1 mi: 01:44:48\n"
				+ "26.2 mi: 03:29:36\n";
		assertEquals(expected, SplitCalculator.calcTimes(false, new TimePace(8,0)));
		
		expected = ""
				+ "100 m:   00:00:24\n"
				+ "200 m:   00:00:48\n"
				+ "400 m:   00:01:36\n"
				+ "800 m:   00:03:12\n"
				+ "1 km:    00:04:00\n"
				+ "1 mi:    00:06:26\n"
				+ "5 km:    00:20:00\n"
				+ "10 km:   00:40:00\n"
				+ "13.1 mi: 01:24:16\n"
				+ "26.2 mi: 02:48:33\n";
		assertEquals(expected, SplitCalculator.calcTimes(true, new TimePace(4,0)));
		
		expected = ""
				+ "100 m:   00:00:23\n"
				+ "200 m:   00:00:47\n"
				+ "400 m:   00:01:35\n"
				+ "800 m:   00:03:11\n"
				+ "1 km:    00:03:59\n"
				+ "1 mi:    00:06:26\n"
				+ "5 km:    00:19:55\n"
				+ "10 km:   00:39:50\n"
				+ "13.1 mi: 01:24:16\n"
				+ "26.2 mi: 02:48:33\n";
		assertEquals(expected, SplitCalculator.calcTimes(false, new TimePace(6,26)));
	}
	
}
