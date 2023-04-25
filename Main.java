import java.util.InputMismatchException;
import java.util.Scanner;

// Main.java is the main file to run all the different screens of Wellness Wally
public class Main {
	
	private final static String LINE = "\n-------------------";

    /**
     * Prints the title at the top of Main
     * @requires none
     * @modifies none
     * @effects none
     * @throws none
     * @returns none
     */
    public static void printTitle() {
        System.out.println("##################");
        System.out.println("#                #");
        System.out.println("# WELLNESS WALLY #");
        System.out.println("#                #");
        System.out.println("##################");
    }

    /**
     * Prints the screen options
     * @requires none
     * @modifies none
     * @effects none
     * @throws none
     * @returns none
     */
    public static void printOptions() {
        System.out.println();
        System.out.println("1: Nutrition Log");
        System.out.println("2: BMI Calculator");
        System.out.println("3: Pace Calculator");
        System.out.println("4: Split Calculator");
        System.out.println("5: Nutrition Facts Calculator");
        System.out.println("0: Exit\n");
    }

    /**
     * User inputs an integer to choose a screen
     * @requires none
     * @modifies none
     * @effects none
     * @throws none
     * @returns int input of the user from command line
     */ 
    @SuppressWarnings("resource")
	public static int userInput() {  
    	System.out.print("Enter a number to select: ");
    	Scanner scan = new Scanner(System.in);
    	int input = -1;
    	try {
    		input = scan.nextInt();
    		if(input < 0) throw new InputMismatchException();
    		System.out.println();
    	}
    	catch(InputMismatchException e) {
    		System.out.println("\nERROR: Invalid.  Please try again");
    		input = userInput();
    	}
    	scan.close();
		return input;
    }
    
    /**
     * Launches the java class (screen) corresponding to the input
     * @requires int input
     * @modifies none
     * @effects none
     * @throws none
     * @returns none
     */
    public static void screenSelect(int input) {
    	if(input == 0) return;
    	
    	System.out.println(LINE);
    	if(input == 1) NutritionLog.main(null);
    	else if(input == 2) BMI.main(null);
    	else if(input == 3) PaceCalculator.main(null);
    	else if(input == 4) SplitCalculator.main(null);
    	else if(input == 5) NutritionFacts.main(null);
    }

    /**
     * Main method for the entire program
     * @requires args
     * @modifies none
     * @effects none
     * @throws none
     * @returns none
     */
    public static void main(String[] args) {
        printTitle();
        printOptions();
        int input = userInput();
        screenSelect(input);
    }
}