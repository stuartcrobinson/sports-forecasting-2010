package objects;



/** a user is only defined for a specific season */
public class User {

	public String userID;
	public String userName;

	/** over an entire season? */
	public Integer ssn;
	public String initialRank;
	public String wins;
	public String losses;
	public String ties;
	
	/**used when calculating consensus bet*/
	public String bet;
//	public double cumAllUnits;
//	public double cumTm1Units;
//	public double cumTm2Units;
	
	public User(){}
	
	public User(String id, String name){
		userID = id;
		userName = name;
	}
	public User(String id, String name, String initRank, String w, String l, String t){
		userID = id;
		userName = name;
//		ssn = season;
		initialRank = initRank;
		wins = w;
		losses = l;
		ties = t;
	}
	
	public String toString(){
		return "user: (" + userName + ", " + userID + ", "+ ssn +")";
	}
	
	public String toString(boolean displayStats){
		if (displayStats)
			return toString() +", "+ wins +"-"+ losses +"-"+ ties;
		else
			return toString();
	}
	
}
