import java.util.Scanner;

// Main.java is the main file to run all the different screens of Wellness Wally
public class Main {

	final static String LINE = "-------------------";

    /**
     * @requires none
     * @modifies none
     * @effects none
     * @throws none
     * @returns String title at the top of main
     */
    public static String title() {
        String title = "##################\n"
        			 + "#                #\n"
        			 + "# WELLNESS WALLY #\n"
        			 + "#                #\n"
        			 + "##################\n";
        return title;
    }

    /**
     * @requires none
     * @modifies none
     * @effects none
     * @throws none
     * @returns String options of Main screen
     */
    public static String options() {
    	String options = "1: Nutrition Log\n"
    				   + "2: BMI Calculator\n"
        			   + "3: Pace Calculator\n"
        			   + "4: Split Calculator\n"
        			   + "5: Nutrition Facts Calculator\n"
        			   + "0: Exit\n";
    	return options;
    }

    /**
     * User inputs an integer to choose a screen
     * @requires none
     * @modifies none
     * @effects none
     * @throws none
     * @returns int input of the user from command line
     * https://stackoverflow.com/questions/18804872/only-let-users-input-positive-integers-no-decimals-or-strings
     */ 
	public static int userInput() {     
		String prompt = "Enter a number to select: ";
		String error = "\nERROR: Invalid input.  Please try again";
		System.out.print(prompt);
		
		int input = -1;
		Scanner scan = new Scanner(System.in);
		
		while(true) {
		  try {
		    input = Integer.valueOf(scan.nextLine());
		    if (input < 0 || input > 5) {
		      System.out.println(error);
		      System.out.print(prompt);
		    } else break;
		  } catch(NumberFormatException e) {
		      System.out.println(error);
		      System.out.print(prompt);
		  }

		}
		
		System.out.println();
		scan.close();
		return input;
    }
    
    /**
     * Launches the java class (screen) corresponding to the input
     * @requires int input
     * @modifies none
     * @effects none
     * @throws IllegalArgumentException if input is invalid
     * @returns none
     */
    public static void screenSelect(int input) throws IllegalArgumentException {
    	if(input == 0) {
    		System.out.println("Quitting now...");
    		return;
    	}
    	
    	System.out.println(LINE);
    	if(input == 1) NutritionLog.main(null);
    	else if(input == 2) Bmi.main(null);
    	else if(input == 3) PaceCalculator.main(null);
    	else if(input == 4) SplitCalculator.main(null);
    	else if(input == 5) NutritionFacts.main(null);
    	else throw new IllegalArgumentException();
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
        System.out.println(title());
        System.out.println(options());
        int input = userInput();
        screenSelect(input);
    }
}