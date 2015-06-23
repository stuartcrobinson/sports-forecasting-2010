private package ztesting;


import helperfuncs.Leaguefuncs;
import helperfuncs.NBAfuncs;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import org.openqa.selenium.By;
import org.openqa.selenium.RenderedWebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;


public class Sportsfuncs2 {
	
	
	public String recordURL(String sUser, String sDate) {
		return "http://contests.covers.com/sportscontests/recordsByDate.aspx?interval=overall&sportID=9&date=" +
					sDate + "&ur=" + sUser + "&t=0";		
	}

	public static String determineSeason(String sDate, String format){
		try {
	        DateFormat df = new SimpleDateFormat (format);

			Calendar c = Calendar.getInstance();
			c.setTime(df.parse(sDate));
			
			if 		(c.get(Calendar.MONTH) <= Calendar.JUNE)	return Integer.toString(c.get(Calendar.YEAR));
			else if (c.get(Calendar.MONTH) >= Calendar.OCTOBER)	return Integer.toString(c.get(Calendar.YEAR) + 1);
			else												return "err"; 
			
		} catch (ParseException e) {e.printStackTrace();}
		
		return null;
	}
	
	public List<Integer> listSeasons(Integer firstSsn, Integer lastSsn) {
		List<Integer> ssns = new ArrayList<Integer>();
		for (Integer ssn = firstSsn; ssn <= lastSsn; ssn++){
			ssns.add(ssn);
		}		
		return ssns;		
	}
	
	public static String ssnDropdownText(Integer ssn){
		return Integer.toString(ssn - 1) + "-" + ssn;
	}
	
	public List<String> listWLFirstsOfTheMonth(Integer ssnInteger){
		int ssn = ssnInteger.intValue();
		List<String> dates = new ArrayList<String>();


		SimpleDateFormat wagerlineDateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Calendar c = Calendar.getInstance();
        Calendar lastDate = Calendar.getInstance();
        
        c.set(ssn - 1, 10, 1);
        lastDate.set(ssn, 7, 1);
        
        for (; c.compareTo(lastDate) < 0; c.add(Calendar.MONTH, 1) ) 
        	dates.add(wagerlineDateFormat.format(c.getTime()));       
        
		return dates;		
	}
	


	public static String extractLine(String str) {
		if (str == null) return "_";
		
        Pattern pattern = Pattern.compile(".* ([\\d.\\+-]*).*");		//console.readLine("%nEnter your regex: "));
        Matcher matcher = pattern.matcher(str);								//console.readLine("Enter input string to search: "));
		if (matcher.find()) {
			System.out.println("extracted "+ matcher.group(1) + " from '" + str + "'");
			return matcher.group(1);
		}
		else
			return null;
	}
	
	
	public static String extractDate(String sText) {
		//example: //Daily Pick record for 02/28/2010
		
        Pattern pattern = Pattern.compile("Pick record for ([\\d/]*).*");		//console.readLine("%nEnter your regex: "));
        Matcher matcher = pattern.matcher(sText);								//console.readLine("Enter input string to search: "));
		if (matcher.find()) {
			System.out.println("extracted "+ matcher.group(1) + " from '" + sText + "'");
			return matcher.group(1);
		}
		else
			return null;
	}

	/**
	 * for the 'history' link on nba leaderboard, wagerline
	 * @param url
	 * @return
	 */
	public static String extractIdInt(String url) {
		
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

	public static ExpectedCondition<Boolean> cssExists(final String myCssSelector) {		
		ExpectedCondition<Boolean> e = new ExpectedCondition<Boolean>() {
		public Boolean apply(WebDriver d) {
			return Boolean.valueOf(((RenderedWebElement) d.findElement(By.cssSelector(myCssSelector))).isDisplayed());
		}
		};
		return e;
	}
	
	public String teamAcronym(String teamName){
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
		return 	ac;
	}
	
	public static void main (String [] args) {
		
		Leaguefuncs mySF = new NBAfuncs();
		
		mySF.listSeasons(2007, 2009);
	}


}


/*

def TeamCoder (teamAbrv)

	if teamAbrv == "ATL"  then teamCode = 101   end 
	if teamAbrv == "BOS"  then teamCode = 102   end 
	if teamAbrv == "CHAR"  then teamCode = 103   end 
	if teamAbrv == "CHI"  then teamCode = 104   end 
	if teamAbrv == "CLE"  then teamCode = 105   end 
	if teamAbrv == "DAL"  then teamCode = 106   end 
	if teamAbrv == "DEN"  then teamCode = 107   end 
	if teamAbrv == "DET"  then teamCode = 108   end 
	if teamAbrv == "GS"  then teamCode = 109   end 
	if teamAbrv == "HOU"  then teamCode = 110   end 
	if teamAbrv == "IND"  then teamCode = 111   end 
	if teamAbrv == "LAC"  then teamCode = 112   end 
	if teamAbrv == "LAL"  then teamCode = 113   end 
	if teamAbrv == "MEM"  then teamCode = 114   end 
	if teamAbrv == "MIA"  then teamCode = 115   end 
	if teamAbrv == "MIL"  then teamCode = 116   end 
	if teamAbrv == "MIN"  then teamCode = 117   end 
	if teamAbrv == "NJ"  then teamCode = 118   end 
	if teamAbrv == "NO"  then teamCode = 119   end 
	if teamAbrv == "NY"  then teamCode = 120   end 
	if teamAbrv == "ORL"  then teamCode = 121   end 
	if teamAbrv == "PHI"  then teamCode = 122   end 
	if teamAbrv == "PHO"  then teamCode = 123   end 
	if teamAbrv == "POR"  then teamCode = 124   end 
	if teamAbrv == "SAC"  then teamCode = 125   end 
	if teamAbrv == "SA"  then teamCode = 126   end 
	if teamAbrv == "SEA"  then teamCode = 127   end 
	if teamAbrv == "TOR"  then teamCode = 128   end 
	if teamAbrv == "UTA"  then teamCode = 129   end 
	if teamAbrv == "WAS"  then teamCode = 130   end 
	if teamAbrv == "OKC"  then teamCode = 131   end 
	
	if teamAbrv =~ /Atlanta/ then teamCode = 101  end				
	if teamAbrv =~ /Boston/ then teamCode = 102  end				
	if teamAbrv =~ /Charlotte/ then teamCode = 103  end				
	if teamAbrv =~ /Chicago/ then teamCode = 104  end				
	if teamAbrv =~ /Cleveland/ then teamCode = 105  end				
	if teamAbrv =~ /Dallas/ then teamCode = 106  end				
	if teamAbrv =~ /Denver/ then teamCode = 107  end				
	if teamAbrv =~ /Detroit/ then teamCode = 108  end				
	if teamAbrv =~ /Golden/ then teamCode = 109  end				
	if teamAbrv =~ /Houston/ then teamCode = 110  end				
	if teamAbrv =~ /Indiana/ then teamCode = 111  end				
	if teamAbrv =~ /Clippers/ then teamCode = 112  end				
	if teamAbrv =~ /Lakers/ then teamCode = 113  end				
	if teamAbrv =~ /Memphis/ then teamCode = 114  end				
	if teamAbrv =~ /Miami/ then teamCode = 115  end				
	if teamAbrv =~ /Milwaukee/ then teamCode = 116  end				
	if teamAbrv =~ /Minnesota/ then teamCode = 117  end				
	if teamAbrv =~ /Jersey/ then teamCode = 118  end				
	if teamAbrv =~ /Orleans/ then teamCode = 119  end				
	if teamAbrv =~ /York/ then teamCode = 120  end				
	if teamAbrv =~ /Orlando/ then teamCode = 121  end				
	if teamAbrv =~ /Philadelphia/ then teamCode = 122  end				
	if teamAbrv =~ /Phoenix/ then teamCode = 123  end				
	if teamAbrv =~ /Portland/ then teamCode = 124  end				
	if teamAbrv =~ /S	acramento/ then teamCode = 125  end				
	if teamAbrv =~ /Antonio/ then teamCode = 126  end				
	if teamAbrv =~ /Seattle/ then teamCode = 127  end				
	if teamAbrv =~ /Toronto/ then teamCode = 128  end				
	if teamAbrv =~ /Utah/ then teamCode = 129  end				
	if teamAbrv =~ /Washington/ then teamCode = 130  end				
	if teamAbrv =~ /Oklahoma/ then teamCode = 131  end				
	
return teamCode
end
*/