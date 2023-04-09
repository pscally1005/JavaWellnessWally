import java.util.Scanner;

// Main.java is the main file to run all the different screens of Wellness Wally
public class Main {

    /**
     * Prints the title at the top of Main
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
     */
    public static void printOptions() {
        System.out.println();
        System.out.println("1: Nutrition Log");
        System.out.println("2: BMI Calculator");
        System.out.println("3: Pace Calculator");
        System.out.println("4: Split Calculator");
        System.out.println("5: Nutrition Facts Calculator");
        System.out.println("X: Exit");
        System.out.println("\nEnter a number to select");
    }

    /**
     * User inputs an integer to choose a screen
     * @return int input
     */
    public static int userInput() {
        // https://stackoverflow.com/questions/39316625/make-user-only-input-integer-in-scanner-java
        Scanner sc = new Scanner(System.in);
        int intInputValue = -1;
        while (true) {
            String input = sc.next();
            try {
                intInputValue = Integer.parseInt(input);
                // TODO: check for whitespace?
                if(intInputValue <= 0) Integer.parseInt("a");
                break;
            } catch (NumberFormatException ne) {
                System.out.println("This is not a number");
            }
        }
        return intInputValue;
    }

    public static void main(String[] args) {
        printTitle();
        printOptions();
        int input = userInput();
        System.out.println("You entered: " + input);
    }
}