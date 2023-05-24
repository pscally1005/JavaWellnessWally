import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Objects;

// TimePace is an immutable ADT
public class TimePace {
	
	@Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface ExcludeFromJacocoGeneratedReport {}
	
	// private variables
	private boolean isTime;
	private int hours;
	private int minutes;
	private int seconds;
	
	/**
	 * ABSTRACTION FUNCTION
	 * The object TimePace represents either a time or a pace that the user inputs
	 * It is used in the classes PaceCalculator and SplitCalculator
	 * ints hours, minutes, and seconds represent their respective parts of the inputted time/pace
	 * If the user inputs a pace, then hours == 0
	 * boolean isTime == true if the object represents a time [HH:MM:SS]
	 * isTime == false if the object represents a pace [MM:SS]
	 */
	
	/**
	 * REPRESENTATION INVARIANT
	 * If representing a pace (isTime == false), then hours == 0
	 * 0 <= hours < 100
	 * 0 <= minutes < 60
	 * 0 <= seconds < 60
	 * hours, minutes, and seconds all can't be inputted as zero
	 * But by default they will all be zero
	 * This isn't checked in checkRep, but it won't be allowed by the oaram constructors (only in the paramless constructor)
	 */
	
	/**
	 * @requries none
	 * @modifies this.isTime, this.hours, this.minutes, this.seconds, this.input
	 * @effects sets private variables to defaults
	 * @throws none
	 * @returns none
	 */
	public TimePace() {
		this.isTime = true;
		this.hours = 0;
		this.minutes = 0;
		this.seconds = 0;
		checkRep();
	}
	
	/**
	 * @requries isTime
	 * @modifies this.isTime, this.hours, this.minutes, this.seconds, this.input
	 * @effects sets private variables equal to the inputs
	 * @throws IllegalArgumentException if hours, minutes, or seconds are too low or high
	 * @returns none
	 */
	public TimePace(int hours, int minutes, int seconds) throws IllegalArgumentException {
		if(hours < 0 || minutes < 0 || seconds < 0) throw new IllegalArgumentException();
		if(hours > 99 || minutes > 59 || seconds > 59) throw new IllegalArgumentException();
		
		this.isTime = true;
		this.hours = hours;
		this.minutes = minutes;
		this.seconds = seconds;
		checkRep();
	}
	
	/**
	 * @requries isTime
	 * @modifies this.isTime, this.minutes, this.seconds, this.input
	 * @effects sets private variables equal to the inputs
	 * @throws IllegalArgumentException if hours, minutes, or seconds are too low or high
	 * @returns none
	 */
	public TimePace(int minutes, int seconds) throws IllegalArgumentException {
		if(minutes < 0 || seconds < 0) throw new IllegalArgumentException();
		if(minutes > 59 || seconds > 59) throw new IllegalArgumentException();
		
		this.isTime = false;
		this.hours = 0;
		this.minutes = minutes;
		this.seconds = seconds;
		checkRep();
	}
	
	/**
	 * @requries isTime
	 * @modifies this.isTime, this.hours, this.minutes, this.seconds
	 * @effects sets private variables equal to the inputs
	 * @throws IllegalArgumentException if input is null
	 * @throws IllegalArgumentException if input is not proper length or doesn't have correct colons
	 * @throws IllegalArgumentException if hours, minutes, or seconds are too low or high
	 * @returns none
	 */
	public TimePace(boolean isTime, String input) throws IllegalArgumentException {
		this.isTime = isTime;
		
		if(input == null) throw new IllegalArgumentException(); 
		if(!this.isTime) input = "00:" + input;
		
		if(input.length() != 8) throw new IllegalArgumentException();
		if(input.charAt(2) != ':' 
				|| input.charAt(5) != ':') throw new IllegalArgumentException();
		
		int hours, minutes, seconds;
		hours = minutes = seconds = -1;
		try {
			hours = Integer.valueOf(input.substring(0, 2));
			minutes = Integer.valueOf(input.substring(3, 5));
			seconds = Integer.valueOf(input.substring(6, 8));
			if(hours < 0 
					|| minutes < 0 
					|| seconds < 0) throw new IllegalArgumentException();
			if(/*hours > 99 
					|| */minutes > 59 
					|| seconds > 59) throw new IllegalArgumentException();
			if(hours == 0 
					&& minutes == 0 
					&& seconds == 0) throw new IllegalArgumentException();
		} catch(NumberFormatException e) {
			throw new IllegalArgumentException();
		}
		
		this.hours = hours;
		this.minutes = minutes;
		this.seconds = seconds;		
		
		checkRep();
	}
	
	/**
	 * @requries none
	 * @modifies none
	 * @effects none
	 * @throws RuntimeException if representation invariant is violated
	 * @returns none
	 */
	@ExcludeFromJacocoGeneratedReport
	private void checkRep() throws RuntimeException {
		if(hours < 0 || minutes < 0 || seconds < 0) throw new RuntimeException();
		if(hours > 99 || minutes > 59 || seconds > 59) throw new RuntimeException();
//		if(hours == 0 && minutes == 0 && seconds == 0) throw new RuntimeException();
	}
	
	/**
	 * @requires none
	 * @modifies none
	 * @effects none
	 * @throws none
	 * @returns boolean isTime
	 */
	public boolean isTime() {
		checkRep();
		return isTime;
	}
	
	/**
	 * @requires none
	 * @modifies none
	 * @effects none
	 * @throws none
	 * @returns int hours
	 */
	public int getHours() {
		checkRep();
		return hours;
	}
	
	/**
	 * @requires none
	 * @modifies none
	 * @effects none
	 * @throws none
	 * @returns int minutes
	 */
	public int getMinutes() {
		checkRep();
		return minutes;
	}
	
	/**
	 * @requires none
	 * @modifies none
	 * @effects none
	 * @throws none
	 * @returns int hours
	 */
	public int getSeconds() {
		checkRep();
		return seconds;
	}
	
	/**
	 * @requires none
	 * @modifies none
	 * @effects none
	 * @throws none
	 * @returns String of formatted time
	 */
	public String toString() {
		checkRep();
		
		String str = "";
		if(isTime) {
			if(hours < 10) str += "0";
			str += hours + ":";
		}
		
		if(minutes < 10) str += "0";
		str += minutes + ":";
		
		if(seconds < 10) str += "0";
		str += seconds;
		
		return str;
	}
	
	/**
	 * 
	 * @requires TimePace tp
	 * @modifies none
	 * @effects none
	 * @throws none
	 * @return double number of seconds in the given time/pace
	 */
	public static double toSeconds(TimePace tp) {
		double num = (double) (tp.getHours()*3600 + tp.getMinutes()*60 + tp.getSeconds());
		return num;
	}
	
	/**
	 * @requries boolean isMetric representing if units are metric (true) or imperial (false)
	 * @requries TimePace objects time and pace
	 * @modifies none
	 * @effects none
	 * @throws none
	 * @returns double representing the distance with given time and pace
	 */
	public static double calcDist(boolean isMetric, TimePace time, TimePace pace) {
		double t = toSeconds(time);
		double p = toSeconds(pace);
		double d = t / p;
		return d;
	}
	
	/**
	 * @requries boolean isMetric representing if units are metric (true) or imperial (false)
	 * @requries double dist and TimePace pace
	 * @modifies none
	 * @effects none
	 * @throws none
	 * @returns TimePace representing the time with given dist and pace
	 */
	public static TimePace calcTime(boolean isMetric, double dist, TimePace pace) {
		double d = dist;
		double p = toSeconds(pace);
		int t = (int) (d * p);
		
		int hours = t / 3600;
		int minutes = (t - hours * 3600) / 60;
		int seconds = (t - hours * 3600) - minutes * 60;
		return new TimePace(hours,minutes,seconds);
	}

	/**
	 * @requries boolean isMetric representing if units are metric (true) or imperial (false)
	 * @requries double dist and TimePace time
	 * @modifies none
	 * @effects none
	 * @throws none
	 * @returns TimePace representing the pace with given dist and time
	 */
	public static TimePace calcPace(boolean isMetric, double dist, TimePace time) {
		double d = dist;
		double t = toSeconds(time);
		int p = (int) (t / d);
		
		int minutes = p  / 60;
		int seconds = p - minutes * 60;
		return new TimePace(minutes,seconds);
	}

	/**
	 * @requries Object obj to compare this to
	 * @modifies none
	 * @effects none
	 * @throws none
	 * @returns true if this and obj are equal, false otherwise
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass())
			return false;
		TimePace other = (TimePace) obj;
		return hours == other.hours 
				&& isTime == other.isTime 
				&& minutes == other.minutes 
				&& seconds == other.seconds;
	}
	
	/**
	 * @requries none
	 * @modifies none
	 * @effects none
	 * @throws none
	 * @returns int hash code of this
	 */
	@Override
	public int hashCode() {
		return Objects.hash(hours, isTime, minutes, seconds);
	}

}
