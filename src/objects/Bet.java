package objects;

import helperfuncs.Helper;

public class Bet {
	
	public String userID;
	public String userName;
	public String ssn;
	public String gID;	
	/**date*/
	public String d;	
	/**abrv name*/
	public String a;	
	/**abrv name*/	
	public String h;	
	/**score*/	
	public String aS;	
	/**score*/	
	public String hS;	
	
	public String lOU;
	public String bOU;
	public String rOU;
	
	public String lATS;
	public String bATS;
	public String rATS;

	public String wlBetText1, result1;	//these are to help parse the wagerline page
	public String wlBetText2, result2;	
	
	public String awayTeamFullName;
	public String homeTeamFullName;
	
	public Bet(){}
	
	public String toString(){
		return "(userStrID, userIntID, ssn, d, a, h, aS, hS, lOU, lATS, bOU, bATS): " +
				userID +", "+ userName +", "+ ssn +", "+d +", "+ a +", "+ h +", "+ aS +", "+ hS +", "+ 
									lOU +", "+ lATS +", "+ bOU +", "+ bATS; 
	}

	
	public void processBetStrings() {
		String bOU_text = null, bATS_text = null;
		if (wlBetText1.indexOf("Over ") != -1 || wlBetText1.indexOf("Under ") != -1) {
			bOU_text = wlBetText1;
			rOU = result1;
		}
		else if (wlBetText1.length() > 3){
			bATS_text = wlBetText1;
			rATS = result1;
		}
		if (wlBetText2.indexOf("Over ") != -1 || wlBetText2.indexOf("Under ") != -1){
			bOU_text = wlBetText2;
			rOU = result2;
		}
		else if (wlBetText2.length() > 3){
			bATS_text = wlBetText2;
			rATS = result2;
		}
		
		if (bOU_text != null) {
			if (bOU_text.indexOf("Over") != -1)		bOU = Value.o;
			if (bOU_text.indexOf("Under") != -1)	bOU = Value.u;
		}
		else 										bOU = "_";		//why? i think this is stupid.
		
		
		if (bATS_text != null){
			if (bATS_text.indexOf(awayTeamFullName) != -1)	bATS = Value.a;
			if (bATS_text.indexOf(homeTeamFullName) != -1)	bATS = Value.h;
		}
		else												bATS = "_";
	
		lOU = Helper.extractLine(bOU_text);
		lATS = Helper.extractLine(bATS_text);
		
	}

	
	public static String translateResult(String word) {
		if (word.indexOf("w") != -1 || word.indexOf("W") != -1) return Value.w;
		if (word.indexOf("l") != -1 || word.indexOf("L") != -1) return Value.l;
		if (word.indexOf("p") != -1 || word.indexOf("P") != -1) return Value.p;
		else													return null;
	}

}
