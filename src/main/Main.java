import java.io.IOException;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.NoSuchElementException;
import java.util.Scanner;

// Main.java is the main file to run all the different screens of Wellness Wally
public class Main /*implements Callable<Object>*/ {

	public final static String LINE = "-------------------";
	public final static String ERROR = "\nERROR: Invalid input.  Please try again";
	public final static String EXIT = "Exiting to main menu...";

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
        			   + "5: Nutrition Facts Generator\n"
        			   + "0: Exit\n";
    	return options;
    }
    
    /**
     * @requires Scanner scan, low and high ints representing bounds to check
     * @requires String prompt telling user the instructions
     * @modifies none
     * @effects none
     * @throws NoSuchElementException if scanner has no next
     * @returns int valid user input
     */
    public static int intInput(Scanner scan, int low, int high, String prompt) throws NoSuchElementException {
    	int input = -1;
		while(true) {
		  try {
			  String next = scan.nextLine();
			  input = Integer.valueOf(next);
			  if (input < low
		    		|| input > high) {
			      System.out.println(Main.ERROR);
			      System.out.print(prompt);
			  } else break;
		  	} catch(NumberFormatException e) {
		  		System.out.println(Main.ERROR);
		  		System.out.print(prompt);
		  	}
		}
		return input;
    }
    
    /**
     * @param <T>
     * @requires Scanner scan, low and high doubles representing bounds to check
     * @requires String prompt telling user the instructions
     * @modifies none
     * @effects none
     * @throws NoSuchElementException if scanner has no next
     * @returns double valid user input
     */
    public static double doubleInput(Scanner scan, double low, double high, String prompt) throws NoSuchElementException {
    	double input = -1;
		while(true) {
		  try {
			  String next = scan.nextLine();
			  input = Double.valueOf(next);
			  if (input < low
		    		|| input > high) {
			      System.out.println(Main.ERROR);
			      System.out.print(prompt);
			  } else break;
		  	} catch(NumberFormatException e) {
		  		System.out.println(Main.ERROR);
		  		System.out.print(prompt);
		  	}
		}
		return input;
    }

    /**
     * User inputs an integer to choose a screen
     * @requires none
     * @modifies none
     * @effects none
     * @throws NoSuchElementException in intInput()
     * @returns int input of the user from command line
     * https://stackoverflow.com/questions/18804872/only-let-users-input-positive-integers-no-decimals-or-strings
     */ 
	public static int userInput() throws NoSuchElementException {     
		String prompt = "Enter a number to select: ";
		System.out.print(prompt);
		
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		int input = (int) intInput(scan,0,5,prompt);
		
		System.out.println();
		
    	if(input == 1) System.out.println("Launching Nutrition Log...");
    	else if(input == 2) System.out.println("Launching BMI Calculator...");
    	else if(input == 3) System.out.println("Launching Pace Calculator...");
    	else if(input == 4) System.out.println("Launching Split Calculator...");
    	else if(input == 5) System.out.println("Launching Nutrition Facts Generator...");
    	else /*if(input == 0)*/ System.out.println("Quitting now...");
		
		return input;
	}

    /**
     * Main method for the entire program
     * @requires args
     * @modifies none
     * @effects none
     * @throws IllegalArgumentException, IOException
     * @returns none
     */
    @ExcludeFromJacocoGeneratedReport
    public static void main(String[] args) throws IllegalArgumentException, IOException {
        System.out.println(title());
        System.out.println(options());
        int input = userInput();
        
        System.out.println(LINE);
    	if(input == 1) NutritionLog.main(null);
    	else if(input == 2) Bmi.main(null);
    	else if(input == 3) PaceCalculator.main(null);
    	else if(input == 4) SplitCalculator.main(null);
    	else if(input == 5) NutritionFacts.main(null);
    }
    
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface ExcludeFromJacocoGeneratedReport {}
    
}