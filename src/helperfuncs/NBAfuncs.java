package helperfuncs;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class NBAfuncs extends Leaguefuncs {

	public NBAfuncs() {
		databaseName = "nba";
		wlSportID = "9";
		
        dateJustAfterSsn = Calendar.getInstance();
        dateJustBeforeSsn = Calendar.getInstance();
        
        
	}


	
	@Override
	public void setSeasonBoundaryDates(Integer ssn){
        dateJustBeforeSsn.set(ssn - 1, 10, 1);
        dateJustAfterSsn.set(ssn, 7, 1);	
        
        super.setSeasonBoundayDates();
        
	}
	
	@Override
	public Integer getSsnFromDate(String sDate, String format) {
		try {
	        DateFormat df = new SimpleDateFormat (format);

			Calendar c = Calendar.getInstance();
			c.setTime(df.parse(sDate));
			
			if 		(c.get(Calendar.MONTH) <= Calendar.JUNE)	return c.get(Calendar.YEAR);
			else if (c.get(Calendar.MONTH) >= Calendar.OCTOBER)	return c.get(Calendar.YEAR) + 1;
			else												return null; 
			
		} catch (ParseException e) {e.printStackTrace();}
		
		return null;
	}

	@Override
	public String ssnDropdownText(Integer ssn){
//		System.out.println("returning: "+ Integer.toString(ssn - 1) + "-" + ssn);
		return Integer.toString(ssn - 1) + "-" + ssn;
	}


	@Override
	public String teamAcronym(String teamName) throws InvalidTeamException{
		String ac = "null";
		if (teamName.toLowerCase().indexOf("atlanta") != -1) 		ac = "atl";
		if (teamName.toLowerCase().indexOf("boston") != -1) 		ac = "bos";
		if (teamName.toLowerCase().indexOf("charlotte") != -1) 		ac = "char";
		if (teamName.toLowerCase().indexOf("chicago") != -1) 		ac = "chi";
		if (teamName.toLowerCase().indexOf("cleveland") != -1) 		ac = "cle";
		if (teamName.toLowerCase().indexOf("dallas") != -1) 		ac = "dal";
		if (teamName.toLowerCase().indexOf("denver") != -1) 		ac = "den";
		if (teamName.toLowerCase().indexOf("detroit") != -1) 		ac = "det";
		if (teamName.toLowerCase().indexOf("golden") != -1) 		ac = "gs";
		if (teamName.toLowerCase().indexOf("houston") != -1) 		ac = "hou";
		if (teamName.toLowerCase().indexOf("indiana") != -1) 		ac = "ind";
		if (teamName.toLowerCase().indexOf("clippers") != -1) 		ac = "lac";
		if (teamName.toLowerCase().indexOf("lakers") != -1) 		ac = "lal";
		if (teamName.toLowerCase().indexOf("memphis") != -1) 		ac = "mem";
		if (teamName.toLowerCase().indexOf("miami") != -1) 			ac = "mia";
		if (teamName.toLowerCase().indexOf("milwaukee") != -1) 		ac = "mil";
		if (teamName.toLowerCase().indexOf("minnesota") != -1) 		ac = "min";
		if (teamName.toLowerCase().indexOf("jersey") != -1) 		ac = "nj";
		if (teamName.toLowerCase().indexOf("orleans") != -1) 		ac = "no";
		if (teamName.toLowerCase().indexOf("york") != -1) 			ac = "ny";
		if (teamName.toLowerCase().indexOf("orlando") != -1) 		ac = "orl";
		if (teamName.toLowerCase().indexOf("philadelphia") != -1) 	ac = "phi";
		if (teamName.toLowerCase().indexOf("phoenix") != -1) 		ac = "pho";
		if (teamName.toLowerCase().indexOf("portland") != -1) 		ac = "por";
		if (teamName.toLowerCase().indexOf("sacramento") != -1) 	ac = "sac";
		if (teamName.toLowerCase().indexOf("antonio") != -1) 		ac = "sa";
		if (teamName.toLowerCase().indexOf("seattle") != -1) 		ac = "sea";
		if (teamName.toLowerCase().indexOf("toronto") != -1) 		ac = "tor";
		if (teamName.toLowerCase().indexOf("utah") != -1) 			ac = "uta";
		if (teamName.toLowerCase().indexOf("washington") != -1) 	ac = "was";
		if (teamName.toLowerCase().indexOf("oklahoma") != -1) 		ac = "okc";		
		if (teamName.toLowerCase().indexOf("east") != -1) 			ac = "east";		
		if (teamName.toLowerCase().indexOf("west") != -1) 			ac = "west";		
		
		if (ac == null)
			throw new InvalidTeamException("team name: " + teamName);
		return 	ac;
	}

}
