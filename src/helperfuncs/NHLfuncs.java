package helperfuncs;

import java.util.Calendar;

public class NHLfuncs extends Leaguefuncs {

	

	public NHLfuncs() {
		databaseName = "nhl";
		wlSportID = "6";
		
        dateJustAfterSsn = Calendar.getInstance();
        dateJustBeforeSsn = Calendar.getInstance();        
	}
	
	
	@Override
	public Integer getSsnFromDate(String sDate, String format) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setSeasonBoundaryDates(Integer ssn) {
		// TODO Auto-generated method stub

	}

	@Override
	public String ssnDropdownText(Integer ssn) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String teamAcronym(String teamName) {
		// TODO Auto-generated method stub
		return null;
	}

}
