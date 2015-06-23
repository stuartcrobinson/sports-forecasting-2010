package schemas;

import objects.User;

/** methods to return database table names */
public class Table {

	/** return "users" + ssn; */
	public static String users(Integer ssn){
		return "users" + ssn;				
	}

	/** return "bets" + userID;	 */
	public static String bets(User user){
		return bets(user.userID, user.ssn);				
	}

	public static String bets(String userID, Integer ssn) {
		return "bets_" + userID.replace('-', 'n') + "_"+ ssn;	
	}

	/** return "games" + ssn;		 */
	public static String games(Integer ssn){
		return "games" + ssn;				
	}


	public static String metaBets() {
		return "bets_metadata";				
	}
}
