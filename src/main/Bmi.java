import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class Bmi {
	
	public static double HEIGHT_TO_METRIC = 0.0254;
	public static double WEIGHT_TO_METRIC = 0.45351473922;
	
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
		String prompt = "Enter 1 to use Imperial units"
			+ "\nEnter 2 to use Metric units"
			+ "\n\nPlease enter a selection: ";
		System.out.print(prompt);
		
		boolean metric = true;
		int input = -1;
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		input = (int) Main.intInput(scan, 1, 2, prompt);
		
		if(input == 2) {
			metric = true;
			System.out.println("\nUsing Metric units...");
		} else {
			metric = false;
			System.out.println("\nUsing Imperial units...");
		}
		
		System.out.println();
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
		String prompt = "Please enter your height in ";		
		if(metric) prompt += "centimeters: ";
		else prompt += "inches: ";
		System.out.print(prompt);
		
		Scanner scan = new Scanner(System.in);
		double height = (double) Main.doubleInput(scan, 0.0, Double.MAX_VALUE, prompt);
		
		// convert height to meters
		if(metric) height /= 100;
		else height *= HEIGHT_TO_METRIC;
		
		System.out.println();
		return height;
	}
	
	/**
	 * @requires boolean metric representing units
	 * @modifies none
	 * @effects none
	 * @throws none
	 * @returns double weight that user enters
	 */
	public static double enterWeight(boolean metric) {	
		String prompt = "Please enter your weight in ";	
		if(metric == true) prompt += "kilograms: ";
		else prompt += "pounds: ";
		System.out.print(prompt);
		
		Scanner scan = new Scanner(System.in);
		double weight = (double) Main.doubleInput(scan, 0.0, Double.MAX_VALUE, prompt);
		
		// convert weight to kilograms
		if(!metric) weight *= WEIGHT_TO_METRIC;
		
		System.out.println();
		return weight;
	}
	
	/**
	 * @requires double value and int places to round to
	 * @modifies none
	 * @effects none
	 * @throws IllegalArgumentException if places < 0
	 * @return double rounded value
	 */
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    BigDecimal bd = BigDecimal.valueOf(value);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}
	
	/**
	 * @requires boolean metric representing units, double height and weight
	 * @modifies none
	 * @effects none
	 * @throws IllegalArgumentException if height or weight are <= 0
	 * @returns double representing the user's BMI
	 */
	public static double calcBmi(double height, double weight) throws IllegalArgumentException {
		if(height <= 0 || weight <= 0) throw new IllegalArgumentException();
		
		double bmi = weight / Math.pow(height, 2);
		bmi = round(bmi,2);
		return bmi;
	}
	
	/**
	 * @requires double bmi
	 * @modifies none
	 * @effects none
	 * @throws IllegalArgumentException if bmi is < 0
	 * @returns String representing user's weight class
	 */
	public static String weightClass(double bmi) throws IllegalArgumentException {
		if(bmi < 0) throw new IllegalArgumentException();
		
		String weightClass;
		if(bmi < 18.5) weightClass = "UNDERWEIGHT";
		else if(/*bmi >= 18.5 
				&&*/ bmi < 25) weightClass = "NORMAL";
		else if(/*bmi >= 25 
				&&*/ bmi < 30) weightClass = "OVERWEIGHT";
		else /*if(bmi >= 30)*/ weightClass = "OBESE";
		return weightClass;
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
		double bmi = calcBmi(height,weight);
		System.out.println("Your BMI is: " + bmi);
		String weightClass = weightClass(bmi);
		System.out.println("You are in the " + weightClass + " range\n");
		
		// all screens have identical exit methods, so I only wrote it in NutritionLog
		if(!NutritionLog.exit()) {
			System.out.println();
			Bmi.main(null);
		}
	}
	
	@Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface ExcludeFromJacocoGeneratedReport {}

}
