import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.List;

public class SplitCalculator {
	
	/**
	 * @requires none
	 * @modifies none
	 * @effects none
	 * @throws none
	 * @returns String of prints at top of SplitCalculator
	 */
	public static String prints() {
		String p = "SPLIT CALCULATOR\n\n"
				+ "This will allow you to enter a pace, and will give you times for certain distances\n";
		return p;
	}
	
	/**
	 * @requires boolean isMetric, TimePace pace to calculate times of
	 * @modifies none
	 * @effects none
	 * @throws none
	 * @returns String representing prints of different times for given pace and select distances
	 */
	public static String calcTimes(boolean isMetric, TimePace pace) {
		TimePace paceMetric, paceImperial;
		paceMetric = paceImperial = new TimePace();
		if(isMetric) {
			paceMetric = pace;
			paceImperial = PaceCalculator.paceMetricToImperial(paceMetric);
		} else {
			paceImperial = pace;
			paceMetric = PaceCalculator.paceImperialToMetric(paceImperial);
		}
		
		TimePace split100m = TimePace.calcTime(true, 0.1, paceMetric);
		TimePace split200m = TimePace.calcTime(true, 0.2, paceMetric);
		TimePace split400m = TimePace.calcTime(true, 0.4, paceMetric);
		TimePace split800m = TimePace.calcTime(true, 0.8, paceMetric);
		TimePace split1k = TimePace.calcTime(true, 1, paceMetric);
		TimePace split1mi = TimePace.calcTime(false, 1, paceImperial);
		TimePace split5k = TimePace.calcTime(true, 5, paceMetric);
		TimePace split10k = TimePace.calcTime(true, 10, paceMetric);
		TimePace splitHalf = TimePace.calcTime(false, 13.1, paceImperial);
		TimePace splitFull = TimePace.calcTime(false, 26.2, paceImperial);
		
		String str = "";
		str += "100 m:   " + split100m + "\n";
		str += "200 m:   " + split200m + "\n";
		str += "400 m:   " + split400m + "\n";
		str += "800 m:   " + split800m + "\n";
		str += "1 km:    " + split1k + "\n";
		str += "1 mi:    " + split1mi + "\n";
		str += "5 km:    " + split5k + "\n";
		str += "10 km:   " + split10k + "\n";
		str += "13.1 mi: " + splitHalf + "\n";
		str +="26.2 mi: " + splitFull + "\n";
		
		return str;
	}

	/**
	 * Overall main method for SplitCalculator
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
		TimePace pace = PaceCalculator.enterTimePace(isMetric, false);
		System.out.println(calcTimes(isMetric, pace));
		
		// all screens have identical exit methods, so I only wrote it in NutritionLog
		if(!NutritionLog.exit()) {
			System.out.println();
			SplitCalculator.main(null);
		}
	}
	
	@Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface ExcludeFromJacocoGeneratedReport {}

}
