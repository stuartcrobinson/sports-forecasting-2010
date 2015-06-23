package helperfuncs;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Helper {

	/** extract the line (the projected game total or spread) 
	 * from strings like these: <li> Boston -1.5 <li> Under 208 <li> Golden State +1.5  <br> etc
	 * @param str
	 * @return
	 */
	public static String extractLine(String str) {
		if (str == null) return "_";
		
        Pattern pattern = Pattern.compile(".* ([\\d.\\+-]*).*");
        Matcher matcher = pattern.matcher(str);
		if (matcher.find()) {
			System.out.println("extracted "+ matcher.group(1) + " from '" + str + "'");
			return matcher.group(1);
		}
		else
			return null;
	}
	
	/**
	 * extract date from this string: "Daily Pick record for 02/28/2010"
	 * @param sText
	 * @return
	 */
	public static String extractDate(String sText) {
        Pattern pattern = Pattern.compile("Pick record for ([\\d/]*).*");
        Matcher matcher = pattern.matcher(sText);
		if (matcher.find()) {
			System.out.println("extracted "+ matcher.group(1) + " from '" + sText + "'");
			return matcher.group(1);
		}
		else
			return null;
	}

	/**
	 * from the 'history' link on wagerline leaderboard
	 * @param url
	 * @return
	 */
	public static String extractUserID(String url) {
		
        Pattern pattern = Pattern.compile("us*e*r=([-\\d]*)&");		//console.readLine("%nEnter your regex: "));
        Matcher matcher = pattern.matcher(url);								//console.readLine("Enter input string to search: "));
		if (matcher.find())
			return matcher.group(1);
		else
			return "no match!!";
	}
	
	public static String reformatDate(String sDate, String oldFormat, String newFormat) {
		System.out.print("attempting to reformat: " + sDate + " from " + oldFormat + " to " + newFormat + ".  ");
        DateFormat df = new SimpleDateFormat (oldFormat);

		Date myDate;
		try {
			myDate = df.parse(sDate);
			SimpleDateFormat formatter = new SimpleDateFormat(newFormat);
			System.out.println("result: " + formatter.format(myDate));
			return formatter.format(myDate);

		} catch (ParseException e) {System.out.println("date exception in formatDateFormysql in sportsfuncs: " + e);}	//supposed to use DateFormat.parse(String date) -- but this gives weird errors for me
		return null;
		
	}

	/**vig as a percent.  decimal less than 1.   v = 0.13 = a -113 juice */
	public static double calculateProfit(double vig, int principal, int numWins, int numLosses) {
	
		//use a -113 vig/juice (v = vig, as a percent.  v = 0.13 = a -113 juice). and $500 bets (p = principle)
	
		double winnings = numWins*principal*(1-vig);
		double losses = numLosses*principal;
		
		double cumUnits = winnings - losses;
		
		return cumUnits;
	}


}
