import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class NutritionFacts {
	
	public static String API_KEY = "M0vNnETsfDcZHzDH1c4XrmNzmODhVFozxZVf0WX3";
	
	/**
	 * @requires none
	 * @modifies none
	 * @effects none
	 * @throws none
	 * @returns String to be printed at top of file
	 */
	public static String prints() {
		String str = ""
				+ "NUTRITION FACTS GENERATOR\n\n"
				+ "This will let you calculate the nutrition facts for a custom recipe\n";
		return str;
	}
	
	/**
	 * @requires none
	 * @modifies none
	 * @effects none
	 * @throws none
	 * @returns String name of recipe
	 */
	public static String enterName() {
		String prompt = "Enter a name for your recipe: ";
		System.out.print(prompt);
		
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		String input = scan.nextLine();
		
		System.out.println("Entered name: " + input + "\n");
		return input;
	}
	
	/**
	 * @requires none
	 * @modifies none
	 * @effects none
	 * @throws none
	 * @returns int number of servings
	 */
	public static int enterServings() {
		String prompt = "Enter the number of servings: ";
		System.out.print(prompt);
		
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		int input = Main.intInput(scan, 1, Integer.MAX_VALUE, prompt);
		
		System.out.println("Number of servings: " + input + "\n");
		
		return input;
	}
	
	/**
	 * @requries none
	 * @modifies none
	 * @effects none
	 * @throws none
	 * @returns Map representing food->gram amount
	 */
	public static Map<String,Integer> enterIngredients() {
		String prompt = ""
				+ "Enter the ingredients of your recipe\n"
				+ "Format: <NAME>,<GRAMS>,<OPTIONAL COMMENT>\n"
				+ "Press enter to finish\n";
		System.out.println(prompt);
		
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		Map<String,Integer> amounts = new HashMap<String,Integer>();
		Map<String,String> comments = new HashMap<String,String>();
		while(true) {
			try {
				String input = scan.nextLine();
				if(input.equals("")) break;
				
				int comma1 = input.indexOf(',');
				if(comma1 == -1) throw new IllegalArgumentException();
				String name = input.substring(0,comma1);
				
				input = input.substring(comma1+1);
				int comma2 = input.indexOf(',');
				String comment = "";
				int amount = -1;
				if(comma2 != -1) {
					comment = input.substring(comma2+1);
					amount = Integer.valueOf(input.substring(0,comma2));
				} else amount = Integer.valueOf(input);
				
				if(amount <= 0 || amount >= Integer.MAX_VALUE) throw new IllegalArgumentException();
				
				input = input.substring(comma2+1);
				int comma3 = input.indexOf(',');
				if(comma3 != -1) throw new IllegalArgumentException();
				
				// TODO: check if entered food is valid
				
				if(amounts.containsKey(name)) throw new IllegalArgumentException();
				amounts.put(name, amount);
				comments.put(name, comment);
				assert amounts.size() == comments.size();
				
			} catch(IllegalArgumentException e) {
				String[] strs = Main.ERROR.split("\n");
				System.out.println(strs[1]);
			}
		}
		
		System.out.println("Ingredients entered...");
		for(String s : amounts.keySet()) {
			System.out.print(s + ": " + amounts.get(s) + "g ");
			if(comments.get(s).equals("")) System.out.println();
			else System.out.print("(" + comments.get(s) + ")\n");
		}
		System.out.println("\nCalculating nutrition facts...");
		return amounts;
	}
	
	/**
	 * @requries String food to get ID of
	 * @modifies none
	 * @effects none
	 * @throws IllegalArgumentException if food does not exist
	 * @throws IOException 
	 * @returns int fdcID corresponding to given food
	 */
	public static int getFdcId(String food) throws IllegalArgumentException, IOException {
		int fdcID = -1;
		
		String requested_url = "https://api.nal.usda.gov/fdc/v1/search?api_key=" + API_KEY;		
		URL url = new URL(requested_url);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		
		// TODO: finish
		// baeldung.com/java-http-request
		// https://www.twilio.com/blog/5-ways-to-make-http-requests-in-java
		// https://stackoverflow.com/questions/1359689/how-to-send-http-request-in-java
		

		if(fdcID == -1) throw new IllegalArgumentException();
		return fdcID;
	}

	/**
	 * Overall main method for NutritionFacts
	 * @throws IOException 
	 * @requires args
     * @modifies none
     * @effects none
     * @throws none
     * @returns none
	 */
	@ExcludeFromJacocoGeneratedReport
	public static void main(String[] args) throws IOException {
//		System.out.println(prints());
//		String name = enterName();
//		int servings = enterServings();
		Map<String,Integer> ingredients = enterIngredients();
		
		
	}
	
	@Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface ExcludeFromJacocoGeneratedReport {}

}
