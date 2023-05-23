import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class PaceCalculator {
	
	/**
	 * @requries none
	 * @modifies none
	 * @effects none
	 * @throws none
	 * @returns a String representing printing at top of Pace Calculator file
	 */
	public static String prints() {
		String p = "PACE CALCULATOR\n\n"
				+ "This will allow you to calculate pace, distance, or time\n";
		return p;
	}
	
	/*
	 * @requires none
	 * @modifies none
	 * @effects none
	 * @throws NoSuchElementException
	 * @returns String D,T, or P corresponding to what the user wants to calculate
	 */
	public static String select() throws NoSuchElementException {
		String prompt = "What would you like to calculate?\n"
				+ "D: Distance\n"
				+ "T: Time\n"
				+ "P: Pace\n"
				+ "\nPlease enter a letter: ";
		System.out.print(prompt);
		
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		String input = scan.nextLine();
		System.out.println();
		
		while(!input.equalsIgnoreCase("D") && !input.equalsIgnoreCase("T") && !input.equalsIgnoreCase("P")) {
			System.out.println(Main.ERROR);
			System.out.print(prompt);
			input = scan.nextLine();
			System.out.println();
		}
		
		input = input.toUpperCase();
		if(input.equals("D")) System.out.println("Calculating Distance...");
		else if(input.equals("T")) System.out.println("Calculating Time..."); 
		else System.out.println("Calculating Pace...");
		
		return input;
	}
	
	/**
	 * @requires boolean representing if units are metric are not
	 * @modifies none
	 * @effects none
	 * @throws NoSuchElementException in Main.doubleInput()
	 * @returns double representing the inputted distance
	 */
	public static double enterDist(boolean isMetric) {
		String unit = "";
		if(!isMetric) unit = "miles";
		else unit = "kilometers";
		String prompt = "Enter a distance in " + unit + ": ";
		
		System.out.print(prompt);
		Scanner scan = new Scanner(System.in);
		double dist = Main.doubleInput(scan, 0, Double.MAX_VALUE, prompt);
		System.out.println("You entered: " + dist + " " + unit);
		return dist;
	}
	
	/**
	 * @requires boolean representing if units are metric are not
	 * @modifies none
	 * @effects none
	 * @throws none
	 * @returns String representing the inputted time
	 */
	public static String enterTime(boolean isMetric) {
		return "";
	}
	
	/**
	 * @requires boolean representing if units are metric are not
	 * @modifies none
	 * @effects none
	 * @throws none
	 * @returns String representing the inputted pace
	 */
	public static String enterPace(boolean isMetric) {
		return "";
	}

	/**
	 * Overall main method for PaceCalculator
	 * @requires args
     * @modifies none
     * @effects none
     * @throws none
     * @returns none
	 */
	@ExcludeFromJacocoGeneratedReport
	public static void main(String[] args) {
		System.out.println(prints());
		boolean isMetric = Bmi.isMetric();
		String select = select();
//		
		double dist = 0.0;
		String time, pace;
		time = pace = "";
		
		if(!select.equals("D")) dist = enterDist(isMetric);
		if(!select.equals("T")) time = enterTime(isMetric);
		if(!select.equals("P")) pace = enterPace(isMetric);
	}
	
	@Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface ExcludeFromJacocoGeneratedReport {}

}
