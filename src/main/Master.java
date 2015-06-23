package main;


import helperfuncs.Leaguefuncs;
import helperfuncs.NBAfuncs;
import helperfuncs.NHLfuncs;
import helperfuncs.WNBAfuncs;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import main.Farmer.AtLastLeaderboardPageException;
import objects.BetType;
import objects.ConsensusBet;
import objects.ConsensusBetID;
import objects.ConsensusBetResult;
import objects.Game;
import objects.TeamType;
import objects.User;
import objects.Value;

import org.openqa.selenium.By;

import schemas.BetsC;
import schemas.Table;


public class Master {

	public Research research;
	public Leaguefuncs lf;
	public Farmer f;

	public static final int NBA 	= 1;
	public static final int WNBA  	= 2;
	public static final int NHL 	= 3;
	public static final int MLB 	= 4;
	public static final int NFL 	= 5;

	public Master(int whichSport){
		switch (whichSport){
		case NBA:	lf = new NBAfuncs();	break;
		case WNBA:	lf = new WNBAfuncs();	break;
		case NHL:	lf = new NHLfuncs();	break;
		default:	lf = null;				break;
		// ...  expand for more sports
		}

		System.out.println("lf is a "+ lf.getClass().toString());
		f = new Farmer(this);
	}

	public Master() {}

	public void setResearchParameters(Research params) {
		this.research = params;
	}
	public void startWebBrowser() {
		f.startWebBrowser();
	}

	public void quitWebBrowser(){
		f.quitWebBrowser();
	}

	public void connectDatabase() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		f.connectDatabase();
	}
	public void closeDatabase(){
		f.closeDatabase();
	}

	public void getUsers(Integer firstSsn, Integer lastSsn)  throws Exception {

		System.out.println("");
		System.out.println("-------------------------------------------------");
		System.out.println("-------------------  Get Users ------------------");
		System.out.println("-------------------------------------------------");
		
		for (Integer ssn : lf.listSeasons(firstSsn, lastSsn)) {			
			System.out.println("ssn: " + ssn);

			f.db.tm.makeUsersTable(ssn);

			f.w.t.selectSsnFromDropdown(ssn);			


//			System.out.println("okay now automatically move to near end of users");
//			f.w.d.findElement(By.cssSelector("#ctrl_leaderboard_last_button")).click();
//			//wait for next page to load
//			f.w.wait.until(f.w.cssExists("#ctrl_leaderboard_next_button"));
			
			int i;
			for (i=0; i< 20000; i++) { 

				try { f.farmUsers(ssn); }
				catch (AtLastLeaderboardPageException e) {break;}

				f.w.d.findElement(By.cssSelector("#ctrl_leaderboard_next_button")).click();

				//wait for next page to load
				f.w.wait.until(f.w.cssExists("#ctrl_leaderboard_next_button"));
			}
			if (i > 19000)
				throw new Exception("tried to page through leaderboard infinite times in getUsers");
		}		
	}

	public void getBets(Integer firstSsn, Integer lastSsn) throws Exception {

		System.out.println("");
		System.out.println("-------------------------------------------------");
		System.out.println("-------------------  Get Bets -------------------");
		System.out.println("-------------------------------------------------");
		

		f.db.tm.makeMetaBetsTable();
		
		for (Integer ssn : lf.listSeasons(firstSsn, lastSsn)) {
			System.out.println("ssn: " + ssn);

			for (String userID :  f.db.r.listUserIDs(ssn) ) { 
				System.out.println("    user: " + userID);
				f.db.tm.makeBetsTable(userID, ssn);
				
				f.db.l.loadBetsCreationMetaData(userID, ssn);

				for (String day : lf.listWLFirstsOfTheMonth(ssn)){		

					f.w.d.get(lf.url_record(userID, day));

					if (f.w.t.pageContainsBets()) {

						List<String> detailLinkURLs = f.w.s.listDetailLinks();
						Collections.reverse(detailLinkURLs);
						for (String url : detailLinkURLs )							
							f.farmBets(userID, ssn, url);
					}
				}

				f.db.l.loadBetsCompletionMetaData(userID, ssn);
			}
		}
	}

	/**
	 * DNE //TODO <br>
	 * using sbrforum open and closing lines which uses 5Dimes.com sportsbook <br>
	 * will be straight-forward.  sbrforum.com has good layout.
	 * @param firstSsn
	 * @param lastSsn
	 */
	public void getLines(Integer firstSsn, Integer lastSsn) {

	}

	/** 
	 * uses the user bets tables.  no internet.
	 * this function does NOT get lines.  will have to do that later, after the games are all in place.
	 * @param firstSsn
	 * @param lastSsn
	 * @throws Exception
	 */
	public void fillGames(Integer firstSsn, Integer lastSsn) throws Exception {

		for (Integer ssn : lf.listSeasons(firstSsn, lastSsn)) {
			System.out.println("ssn: " + ssn);
			f.db.tm.makeGamesTable(ssn);

			for (String userID :  f.db.r.listUserIDs(ssn) ) { 
				System.out.println("    user: " + userID);

				f.farmGames(userID, ssn);				
			}
		}
	}

	/**
	 * uses the user bets tables.  no internet.
	 * @param firstSsn
	 * @param lastSsn
	 * @throws Exception
	 */
	public void fillLines_reconstructed(Integer firstSsn, Integer lastSsn) throws Exception {

		for (Integer ssn : lf.listSeasons(firstSsn, lastSsn)) {
			System.out.println("ssn: " + ssn);

			for (String gameID : f.db.r.listGameIDs(ssn)) { 

				f.fillLines_reconstructed(gameID);  
			}			
		}
	}

	/**for analyzing consensus bet */
	public class UserAndUnits {
		public User user;
		public Double cumUnits;
		public UserAndUnits(User user, Double cumUnits){
			this.user = user;
			this.cumUnits 	= cumUnits;
		}
	}
	
	public class DateSpan{
		public int firstDay;
		public int firstMonth;
		public int lastDay;
		public int lastMonth;
		public DateSpan(int m0, int d0, int m1, int dl) {
			firstMonth = m0;
			firstDay = d0;
			lastMonth = m1;
			lastDay = dl;
		}
		public String toString(){
			return firstMonth +"/"+ firstDay +" - " + lastMonth +"/"+ lastDay;
		}
	}
	
	public void analyze(Integer firstSsn, Integer lastSsn) throws Exception {

		
		/** need to record parameters per consensusID somewhere */
		//		String		consensusID;
		double 		cumUnits;
		List<Game>	games = new ArrayList<Game>();
		int totNumGames = 0;

		
		List<UserAndUnits> usersRanking;	
		List<User> users;
		
		
		for (Integer ssn : lf.listSeasons(firstSsn, lastSsn)) {
			System.out.println("ssn: " + ssn);

			Game game;													//to force variable within block
			ConsensusBet consBet;
			
			for (String gameID : f.db.r.listGameIDs(ssn)) { 
				System.out.println("gameID: " + gameID);
				totNumGames++;

				game = f.db.r.getGame(gameID);

				for (BetType betType : BetType.listBetTypes()) {
					for (TeamType teamType : TeamType.listTeamTypes(gameID)){
						for (int depth : research.depths){

							users = f.db.r.listUsers(ssn);
							usersRanking = new ArrayList<UserAndUnits>(users.size());

							for (User user :  users ) {
								if (f.db.sql.contains(Table.bets(user), BetsC.gID, gameID)){

									user.bet = f.db.sql.selectString(Table.bets(user), betType.betCol, BetsC.gID, "=", gameID);
									
									if (teamType.type == TeamType.allAround){
										cumUnits = f.calcCumUnits(user, betType, teamType.teamGeneralColQry, depth, gameID);
										usersRanking.add(new UserAndUnits(user, cumUnits));
									}
									if (teamType.type == TeamType.specific){

										cumUnits = f.calcCumUnits(user, betType, teamType.team1ColQry, depth, gameID);
										usersRanking.add(new UserAndUnits(user, cumUnits));

										cumUnits= f.calcCumUnits(user, betType, teamType.team2ColQry, depth, gameID);
										usersRanking.add(new UserAndUnits(user, cumUnits));
									}
								}
							}

							//sort in decreasing order
							Collections.sort(usersRanking,  new Comparator<UserAndUnits>(){
				                     public int compare(UserAndUnits a, UserAndUnits b) {
				                         return b.cumUnits.compareTo(a.cumUnits);
				                     }});


							for (int base : research.bases){
								for (int n : research.numUsersToInclude){
									consBet = ConsensusBet.calculateConsensusBet(betType, usersRanking, base, n);
									consBet.processResult(game);
									for (int pctCutoff : research.pctCutoffs){

										if (consBet.percent >= pctCutoff){
											for (DateSpan days : research.dateSpans){

												if (lf.gameLiesWithinDateSpan(game, days)){
													consBet.processID(days, betType, teamType, depth, base, n, pctCutoff);
													game.ConsensusBets.put(consBet.id.sID, consBet);
												}
											}
										}
									}
								}
							}
						}
					}
				}	
				
				if (!game.ConsensusBets.isEmpty())
					games.add(game);

			}
		}
		
		
//		game.
		
		//now, all the games consensus bets have been processed. 
		//		use the game objects' ConsensusBets treemaps to iterate through each consensusBetID (need a List of these)


		List<ConsensusBetResult> consResults = new ArrayList<ConsensusBetResult>();
		for (BetType betType : BetType.listBetTypes()) 
			for (TeamType teamType : TeamType.listTeamTypes())
				for (int depth : research.depths)
					for (int base : research.bases)
						for (int n : research.numUsersToInclude)
							for (int pctCutoff : research.pctCutoffs)
								for (DateSpan days : research.dateSpans)
									consResults.add(
											new ConsensusBetResult(
													new ConsensusBetID(
															days, betType, teamType, depth, base, n, pctCutoff)));

//		List<ConsensusBetID> consBetIDs = new ArrayList<ConsensusBetID>();


		ConsensusBet consBet;
		for (ConsensusBetResult consResult : consResults){
			consResult.totNumGames = totNumGames;
			
			for (Game game : games){
				if (game.ConsensusBets.containsKey(consResult.consBetID.sID)){
					consResult.numGamesBetUpon++;

					consBet = game.ConsensusBets.get(consResult.consBetID.sID);
					
					if (consBet.result.equals(Value.w))
						consResult.wins++;
					else if (consBet.result.equals(Value.l))
						consResult.losses++;
					else if (consBet.result.equals(Value.p))
						consResult.ties++;
					else
						throw new Exception("consensus bet result is not valid: "+ consBet.result);
				}
				
				consResult.finishProcessing();
				
			}
		}
	}

}


//for each game (by gameID)
//determine bet categories by game teams
//for each bet category (genOU, atlOU, noOU, atlATS, noATS)

//for each bet depth (cumulative depth class)
//make new empty Tree Map
//for each user betting on day of game
//place user into tree map with key: cumulative units. --- for this, make a new userAndBet object. with just userID and bet
//dump tree map into a list, so it sorts (automatically right?)

//initialize Game object with game data from db
//for each parameter consensus formula (CF)
//calculate ConsensusBetDetail, append to list of these in the Game object
//calculate results for each consensus bet. (winrate, units, num bets, other?)

