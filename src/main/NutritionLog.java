import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;

public class NutritionLog {
	
	/**
	 * @requires none
	 * @modifies none
	 * @effects none
	 * @throws none
	 * @returns String representing the prints for top of file
	 */
	public static String prints() {
		String p = "NUTRITION LOG"
				+ "\n\nThis will allow you to rate your nutrition for today and why"
				+ "\nThis data will be saved to a log file\n";
		return p;
	}
	
	/**
	 * User enters the number, representing their rating for that day
	 * @requires none
	 * @modifies none
	 * @effects none
	 * @throws NoSuchElementException in Main.intInput();
	 * @returns int rating that user inputted
	 */
	public static int enterNum() throws NoSuchElementException {
		String prompt = "Please rate your nutrition for today from 1 to 5: ";
		System.out.print(prompt);
		
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		int input = (int) Main.intInput(scan, 1, 5, prompt);

		System.out.println();
		return input;
	}
	
	/**
	 * @requires int rating
	 * @modifies none
	 * @effects none
	 * @throws none
	 * @return String representing description of their daily nutrition
	 */
	public static String enterDesc(int rating) {
		System.out.println("Please describe why you rated your day as a \"" + rating + "\":");
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		String input = scan.nextLine();
		return input;
		
	}
	
	/**
	 * Writes user description to a file
	 * @requries String desc
	 * @modifies none
	 * @effects none
	 * @throws IOException
	 * @returns none
	 */
	public static void writeToFile(int rating, String desc) throws IOException {
			String filename = "data/log.txt";
			FileWriter fileWriter = new FileWriter(filename, true);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			
			// https://www.javatpoint.com/java-simpledateformat
			Date date = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("MMMM-dd-yyyy");
			String strDate = formatter.format(date);
			
			String str = "[" + strDate + "]: (" + rating + ") " + desc;
			bufferedWriter.append(str + "\n");
			bufferedWriter.close();
			System.out.println();
	
	}
	
	/**
	 * Allows user to stay on the current screen or go back to the main menu
	 * @requires none
	 * @modifies none
	 * @effects none
	 * @throws none
	 * @returns true if user wishes to exit, false if they want to stay
	 */
	public static boolean exit() {
		System.out.print("Enter \"Y\" to stay on this screen, or anything else to return: ");
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		String input = scan.nextLine();
		if(input.equalsIgnoreCase("y")) return false;
		else {
			System.out.println(Main.EXIT);
			return true;
		}
	}

	/**
	 * Overall main method for NutritionLog
	 * @requires args
     * @modifies none
     * @effects none
     * @throws none
     * @returns none
	 */
	@ExcludeFromJacocoGeneratedReport
	public static void main(String[] args) throws IOException {
		System.out.println(prints());
		int num = enterNum();
		String desc = enterDesc(num);
		writeToFile(num,desc);
		if(!exit()) NutritionLog.main(null);
		else Main.main(null);
	}
	
	@Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface ExcludeFromJacocoGeneratedReport {}

}
