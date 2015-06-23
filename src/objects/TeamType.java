package objects;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * users are ranked based on their betting performance with either a specific team or all teams in general. <br>
 * 
 */
public class TeamType {

	/**"allAround" or "specific"*/
	public String type;

	/**group column query -- team abbrev or "%" for the 'allAround' type */
	public String teamGeneralColQry;

	/**group column query -- team abbrev or "%" for the 'allAround' type */
	public String team1ColQry;

	/**group column query -- team abbrev or "%" for the 'allAround' type */
	public String team2ColQry;


	/**a 'group' option */
	public static String allAround = "allAround";
	public static String specific = "specific";

	private TeamType(String t, String t1, String t2) {
		type = t;
		teamGeneralColQry = "%";
		team1ColQry = t1;
		team2ColQry = t2;
	}

	/**
	 * should either be "by specific team" or "by all teams, in general"
	 * @param gameID
	 * @return
	 */
	public static List<TeamType> listTeamTypes(String gameID) {

		Pattern pattern = Pattern.compile("(\\w*)\\d*(\\w*)");
		Matcher matcher = pattern.matcher(gameID);

		String team1 = matcher.group(1);
		String team2 = matcher.group(2);

		List<TeamType> types = new ArrayList<TeamType>();

		types.add(new TeamType( TeamType.allAround, null, null ));
		types.add(new TeamType( TeamType.specific, team1, team2 ));

		return types;
	}
	
	/**for listing the consensusBetIDs, only <br><br>
	 * should either be "by specific team" or "by all teams, in general"
	 * @param gameID
	 * @return
	 */
	public static List<TeamType> listTeamTypes() {

		List<TeamType> types = new ArrayList<TeamType>();

		types.add(new TeamType( TeamType.allAround, null, null ));
		types.add(new TeamType( TeamType.specific, null, null ));

		return types;
	}

}
