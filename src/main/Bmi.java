import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class Bmi {
	
	/**
	 * @requires none
	 * @modifies none
	 * @effects none
	 * @throws none
	 * @return a String representing the print info at top of Bmi screen
	 */
	public static String prints() {
		String str = "BMI CALCULATOR"
				+ "\n\nThis will allow you to enter your height and weight to calculate BMI\n";
		return str;
	}
	
	/**
	 * @requires none
	 * @modifies none
	 * @effects none
	 * @throws none
	 * @returns true if user selects metric units, false otherwise
	 */
	public static boolean isMetric() {
		System.out.println("Enter 1 to use Imperial units (in,lbs)");
		System.out.println("Enter 2 to use Metric units (m,g)");
		System.out.print("\nPlease enter a selection: ");
		
		boolean metric = true;
		
		return metric;
	}
	
	/**
	 * @requires boolean metric representing units
	 * @modifies none
	 * @effects none
	 * @throws none
	 * @returns double height that user enters
	 */
	public static double enterHeight(boolean metric) {
		return 1.0;
	}
	
	/**
	 * @requires boolean metric representing units
	 * @modifies none
	 * @effects none
	 * @throws none
	 * @returns double weight that user enters
	 */
	public static double enterWeight(boolean metric) {
		return 1.0;
	}
	
	/**
	 * @requires boolean metric representing units, double height and weight
	 * @modifies none
	 * @effects none
	 * @throws NullPointerException if height or weight are null
	 * @throws IllegalArgumentException if height or weight are < 0
	 * @returns double representing the user's BMI
	 */
	public static double calcBmi(boolean metric, double height, double weight) throws NullPointerException, IllegalArgumentException {
		return 0.0;
	}
	
	/**
	 * @requires double bmi
	 * @modifies none
	 * @effects none
	 * @throws NullPointerException if bmi is null
	 * @throws IllegalArgumentException if bmi is < 0
	 * @returns String representing user's weight class
	 */
	public static String weightClass(double bmi) throws NullPointerException, IllegalArgumentException {
		return "";
	}

	/**
	 * Overall main method for BMI
	 * @requires args
     * @modifies none
     * @effects none
     * @throws none
     * @returns none
	 */
	@ExcludeFromJacocoGeneratedReport
	public static void main(String[] args) {
		System.out.println(prints());
		boolean metric = isMetric();
		double height = enterHeight(metric);
		double weight = enterWeight(metric);
		double bmi = calcBmi(metric,height,weight);
		String str = weightClass(bmi);
		
		// all screens have identical exit methods, so I only wrote it in NutritionLog
		if(!NutritionLog.exit()) Bmi.main(null);
	}
	
	@Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface ExcludeFromJacocoGeneratedReport {}

}
