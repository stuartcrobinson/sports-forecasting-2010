package main;

import helperfuncs.Helper;

import java.sql.SQLException;
import java.util.List;

import objects.Bet;
import objects.BetType;
import objects.Game;
import objects.Line;
import objects.User;

import org.apache.commons.math.stat.StatUtils;

import schemas.BetsC;
import web.WebHandler;
import database.DatabaseHandler;

/**
 * owns the WebHandler (w) and DatabaseHandler (db) <br>
 * <dt>handles data:</dt> 
 * <ul>
 * <li>extraction - from database (use db.r) or website (use w.s) </li>
 * <li>packaging - e.g. into lists or objects (use db.r, w.s) </li>
 * <li>loading - into database (use db.l) </li></ul>
 */
public class Farmer {

	public class AtLastLeaderboardPageException extends Exception {
		private static final long serialVersionUID = -1059335782774468888L;
		public AtLastLeaderboardPageException(String string) {
			// TODO Auto-generated constructor stub
		}

	}


	public Master m;	
	
	public WebHandler w;	
	public DatabaseHandler db;
	
	public String leaderboardPageTopUser_previous = "prev";
	public String leaderboardPageTopUser_current = "curr";

	public Farmer(Master master) {		
		m = master;
	}

	public void startWebBrowser() {
		w = new WebHandler(this);
	}
	
	public void quitWebBrowser(){
		w.quit();
	}
	
	public void connectDatabase() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		db = new DatabaseHandler(this);
	}
	public void closeDatabase(){
		db.close();
	}

	public int farmUsers(Integer ssn) throws Exception {
		int numLoaded = 0;
		
		leaderboardPageTopUser_previous = leaderboardPageTopUser_current;
		leaderboardPageTopUser_current = w.s.getTopUserName();
		System.out.println("top name: "+ this.leaderboardPageTopUser_current);
		
		if (this.leaderboardPageTopUser_current.equals(this.leaderboardPageTopUser_previous))
				throw new AtLastLeaderboardPageException("top member: "+ this.leaderboardPageTopUser_current);
		
		List<User> users = w.s.listUsers();
		
		for (User user : users){
			db.l.loadUser(ssn, user);
			System.out.println(user.toString());
			numLoaded++;
		}

		return numLoaded;
	}
	/** 
	 *  uses ... to scrape the data and store it in database, respectively
	 * @param ssn TODO
	 * @param url 
	 * @return number of bets loaded to database
	 * @throws SQLException 
	 */
	public int farmBets(String userID, Integer ssn, String url) throws SQLException {
		int numLoaded = 0;
		
		w.d.get(url);
		
		List<Bet> bets = w.s.listBets();
		
		for (Bet bet : bets){
			db.l.loadBet(userID, ssn, bet);
			System.out.println(bet.toString());
			numLoaded++;
		}
		
		return numLoaded;
	}


	/**
	 * farms from database, not internet
	 * @param userID
	 * @param ssn
	 * @return number of games loaded to database
	 * @throws Exception 
	 */
	public int farmGames(String userID, Integer ssn) throws Exception {
		Game game;

		for (Bet bet : db.r.listBets(userID, ssn)){		
			game = new Game(bet);
			try {
				db.l.loadGame(game);
			}
			catch (Exception e) {
				throw new Exception("mismatch with game table data: (user, gameID): ("
										+ userID + ", " + game.gID + ").");
			}
		}
		
		
		return 0;
	}

	
	/**  fill an existing row of games<ssn> with game lines (use Lines object). <br><br>
	 * db.r.newMethod - return list&lt;numeric&gt; of all users' OU or ATS lines for a given game(call once per each)<br>
	 * find functions- get ave, min, max, sd <br>
	 * db.l.fillGameLines - load Lines object ((conditionally) open, close, ave, min, max, sd for OU and ATS) into games&lt;ssn&gt;
	 * @param gameID
	 * @throws Exception 
	 */
	public void fillLines_reconstructed(String gameID) throws Exception {
		
		double [] linesOU  = db.r.compileDataAcrossAllUsersForGame(BetsC.lOU, gameID); 
		double [] linesATS = db.r.compileDataAcrossAllUsersForGame(BetsC.lATS, gameID);
		
		Line line = new Line();
		
		line.lATSmin = StatUtils.min(linesATS);
		line.lATSmax = StatUtils.max(linesATS);
		line.lATSave = StatUtils.mean(linesATS);
		line.lATSsd  = Math.sqrt(StatUtils.variance(linesATS));

		line.lOUmin = StatUtils.min(linesOU);
		line.lOUmax = StatUtils.max(linesOU);
		line.lOUave = StatUtils.mean(linesOU);
		line.lOUsd  = Math.sqrt(StatUtils.variance(linesOU));
		
		db.l.fillGameLines(gameID, line);
		
	}
	

	/**
	 * @param userID
	 * @param betType
	 * @param depth 
	 * @param teamType 
	 * @param gameID
	 * @return
	 * @throws SQLException 
	 */
	public double calcCumUnits(User user, BetType betType, String teamColQry, int depth, String gameID) throws SQLException {
		
		int numWins 	= db.r.getNumWins(user, betType, teamColQry, depth, gameID);
		int numLosses 	= db.r.getNumLosses(user, betType, teamColQry, depth, gameID);
		
		double v = 0.13;
		int p = 500;
		
		return Helper.calculateProfit(v, p, numWins, numLosses);
	}


	
}
