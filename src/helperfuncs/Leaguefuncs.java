package helperfuncs;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import main.Master.DateSpan;
import objects.Game;

/**
 * !!!!!this should be split up into Sportsfuncs (with all static methods)<br>
 * and Leaguefuncs, which will be the abstract class for NBAfuncs, etc...
 * @author Admin
 *
 */
public abstract class Leaguefuncs {

	public class InvalidTeamException extends Exception {
		private static final long serialVersionUID = -1758578547141078338L;
		public InvalidTeamException() {}
		public InvalidTeamException(String arg0) {
			super(arg0);
		}
	}

	public String databaseName = null;

	public String wlSportID;	//9 for nba

	public Calendar dateJustAfterSsn;
	public Calendar dateJustBeforeSsn;

	public int startYear;
	public int endYear;


	public String sSQLDateJustBeforeSsn(Integer ssn) {
		setSeasonBoundaryDates(ssn);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		return formatter.format(dateJustBeforeSsn.getTime());
	}

	public String sSQLDateJustAfterSsn(Integer ssn) {
		setSeasonBoundaryDates(ssn);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		return formatter.format(dateJustAfterSsn.getTime());
	}

	abstract public void setSeasonBoundaryDates(Integer ssn);

	//
	//    Pattern pattern = Pattern.compile("user=([-\\d]*)&sportID");	//.*user=-(\\d)&sportID.*");		//console.readLine("%nEnter your regex: "));
	//
	//    Matcher matcher = pattern.matcher("profile.aspx?user=-63417&sportID=9&type=4");	//console.readLine("Enter input string to search: "));
	//
	//    boolean found = false;
	//    while (matcher.find()) {
	//        System.out.println("I found the text "+matcher.group(1)+" starting at " +
	//           "index "+matcher.start()+" and ending at index "+ matcher.end()+".");
	//        found = true;
	//    }
	//    if(!found){
	//        System.out.println("No match found.");
	//    }


	public Integer getSsnFromGameID(String gID){
		String format = "yyyyMMdd";					//this should probably be an attribute of Game
		String sDate = null;

		Pattern pattern = Pattern.compile(".*(\\d*).*");
		Matcher matcher = pattern.matcher(gID);

		sDate = matcher.group(1);
		return getSsnFromDate(sDate, format);
	}

	/**
	 * determines season of the given string date in the given format
	 * @param sDate
	 * @param format
	 * @return
	 */
	abstract public Integer getSsnFromDate(String sDate, String format);

	/**
	 * determines wagerline season dropdown option for given season
	 * @param ssn
	 * @return
	 */
	abstract public String ssnDropdownText(Integer ssn);

	abstract public String teamAcronym(String teamName) throws InvalidTeamException;

	/**
	 * this is because the URL that lists a user's monthly bets contains the date of the first of that month.
	 * @param ssn
	 * @return
	 */
	public List<String> listWLFirstsOfTheMonth(Integer ssn){
		List<String> dates = new ArrayList<String>();

		SimpleDateFormat wagerlineDateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Calendar c = Calendar.getInstance();

		setSeasonBoundaryDates(ssn);

		for (c = dateJustBeforeSsn; c.compareTo(dateJustAfterSsn) < 0; c.add(Calendar.MONTH, 1) ) 
			dates.add(wagerlineDateFormat.format(c.getTime()));       

		return dates;		
	}

	public String url_leaderboard(){
		return "http://contests.covers.com/sportscontests/leaders.aspx?sportID="+ wlSportID +"&t=0";
	}

	public String url_record(String sUserID, String sDate) {
		return "http://contests.covers.com/sportscontests/recordsByDate.aspx?interval=overall&sportID="+ wlSportID
		+"&date=" +	sDate + "&ur=" + sUserID + "&t=0";		
	}

	public String url_history(String sUserID) {
		//http://contests.covers.com/sportscontests/profile.aspx?user=53243&sportID=9&type=5
		return "http://contests.covers.com/sportscontests/profile.aspx?interval=overall&sportID="+
		wlSportID +"&user=" + sUserID;		
	}



	public List<Integer> listSeasons(Integer firstSsn, Integer lastSsn) {
		List<Integer> ssns = new ArrayList<Integer>();
		for (Integer ssn = firstSsn; ssn <= lastSsn; ssn++){
			ssns.add(ssn);
		}		
		return ssns;		
	}


	/**a lot of waste here ... doing stuff that should probably be done elsewhere like getting Date for game */
	public boolean gameLiesWithinDateSpan(Game game, DateSpan days) throws Exception {

		Calendar firstDate = Calendar.getInstance();
		Calendar lastDate = Calendar.getInstance();

		//determine year of datespan first date

		//build Calendar objects for datespan days
		firstDate.set(startYear, days.firstMonth, days.firstDay);
		if (!(firstDate.after(dateJustBeforeSsn) && firstDate.before(dateJustAfterSsn))){

			firstDate.set(endYear, days.firstMonth, days.firstDay);
			if (!(firstDate.after(dateJustBeforeSsn) && firstDate.before(dateJustAfterSsn))){
				throw new Exception("invalid beginning of date span " + days.toString());
			}
		}


		lastDate.set(startYear, days.lastMonth, days.lastDay);
		if (!(lastDate.after(dateJustBeforeSsn) && lastDate.before(dateJustAfterSsn))){

			lastDate.set(endYear, days.lastMonth, days.lastDay);
			if (!(lastDate.after(dateJustBeforeSsn) && lastDate.before(dateJustAfterSsn))){
				throw new Exception("invalid beginning of date span " + days.toString());
			}
		}

		//now, calendar objects for firstDate and lastDate are set
		//now carry out this method's actual task

		DateFormat df = new SimpleDateFormat ("yyyy-MM-dd");
		Date gameDate;

		gameDate = df.parse(game.d);

		if (gameDate.after(firstDate.getTime()) && gameDate.before(lastDate.getTime()))
			return true;
		else
			return false;
	}

	protected void setSeasonBoundayDates() {
		startYear = dateJustBeforeSsn.get(Calendar.YEAR);
		endYear = dateJustAfterSsn.get(Calendar.YEAR);
	}

}
