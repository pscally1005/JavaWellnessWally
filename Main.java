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
        Scanner scan = new Scanner(System.in);
        int input = scan.nextInt();     // User int input
        scan.nextLine();                // serves at getch
        return input;
    }

    public static void main(String[] args) {
        printTitle();
        printOptions();
//        int input = userInput();
//        System.out.println("You entered: " + input);
    }
}