package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import objects.Bet;
import objects.BetType;
import objects.Game;
import objects.User;
import objects.Value;
import schemas.BetsC;
import schemas.GamesC;
import schemas.Table;
import schemas.UsersC;

public class Retriever {

	DatabaseHandler db;
	
	public Retriever(DatabaseHandler databaseHandler) {
		db = databaseHandler;
	}

	public List<String> listUserIDs(Integer ssn) throws SQLException {
		return db.sql.selectStrings(Table.users(ssn), UsersC.userID);
	}
	
	

	public List<User> listUsers(Integer ssn) throws SQLException {
		String tableName = Table.users(ssn);

		User u;
		ArrayList<User> users = new ArrayList<User>();
		
		ResultSet rs = db.sql.executeQuery(
			"select * from "+ tableName, true);
		
		try {
			while (rs.next()){
				u = new User();

				u.userID = rs.getString(UsersC.userID);
				u.userName = rs.getString(UsersC.userName);
				u.ssn = ssn;
				u.wins = rs.getString(UsersC.wins);
				u.losses = rs.getString(UsersC.losses);
				u.ties = rs.getString(UsersC.ties);
				
				users.add(u);
			}
		} catch (SQLException e) {e.printStackTrace(); }
		
		return users;
	}
	
	
	
	/**
	 * get all the bets of a user in a given season.
	 * add a ssn column to user bets tables
	 * @param userID
	 * @param ssn
	 * @return
	 * @throws SQLException 
	 */
	public List<Bet> listBets(String userID, Integer ssn) throws SQLException {
		String tableName = Table.bets(userID, ssn);

		Bet b;
		ArrayList<Bet> bets = new ArrayList<Bet>();
		
		ResultSet rs = db.sql.executeQuery(
			"select * from "+ tableName +
			" where "+ BetsC.d +" > "+ db.f.m.lf.sSQLDateJustBeforeSsn(ssn) +
			" and   "+ BetsC.d +" < "+ db.f.m.lf.sSQLDateJustAfterSsn(ssn), true);
		
		try {
			while (rs.next()){
				b = new Bet();

				b.gID = rs.getString(BetsC.gID);
				b.d = rs.getString(BetsC.d);
				b.a = rs.getString(BetsC.a);
				b.h = rs.getString(BetsC.h);
				b.aS = rs.getString(BetsC.aS);
				b.hS = rs.getString(BetsC.hS);
				b.lOU = rs.getString(BetsC.lOU);
				b.lATS = rs.getString(BetsC.lATS);
				b.bOU = rs.getString(BetsC.bOU);
				b.bATS = rs.getString(BetsC.bATS);
				
				bets.add(b);
			}
		} catch (SQLException e) {e.printStackTrace(); }
		
		return bets;
	}

	/**
	 * return a list of all gameIDs in the season
	 * @param ssn
	 * @return
	 * @throws SQLException 
	 */
	public List<String> listGameIDs(Integer ssn) throws SQLException {
		String tableName = "games" + ssn;
		return db.sql.selectStrings(tableName, GamesC.gID);
	}

	/**
	 * this is called if the gameID exists in the games table
	 * @param gID
	 * @return new Game object
	 * @throws SQLException 
	 */
	public Game getGame(String gID) throws SQLException {
		String tableName = "games" + db.f.m.lf.getSsnFromGameID(gID);
		
		Game g = new Game();
		//select certain rows from games table where gID is matched
	
		String columnsQueryStr =
			GamesC.gID + ", " + 
			GamesC.d + ", " + 
			GamesC.a + ", " + 
			GamesC.h + ", " + 
			GamesC.aS + ", " + 
			GamesC.hS + ", " + 
			GamesC.tot + ", " + 
			GamesC.spread; 

		ResultSet rs = db.sql.selectRS(tableName, columnsQueryStr, GamesC.gID, "=", gID);
		
		
		try {
			if (rs.next()){
				g.gID		= rs.getString(GamesC.gID);
				g.d			= rs.getString(GamesC.d);
				g.a			= rs.getString(GamesC.a);
				g.h			= rs.getString(GamesC.h);
				g.aS		= rs.getString(GamesC.aS);
				g.hS		= rs.getString(GamesC.hS);
				g.tot		= rs.getString(GamesC.tot);
				g.spread	= rs.getString(GamesC.spread);
			}
			return g;
		} catch (SQLException e) {e.printStackTrace();}
		
		return null;
	}
	
	/**
	 * for the given gameID, for each user in the game's season, make an array of the users' values in the given column 
	 * @param sColName
	 * @param gameID
	 * @return
	 * @throws Exception 
	 */
	public double [] compileDataAcrossAllUsersForGame(String sColName, String gameID) throws Exception {
		String		 sTable;
		Integer		 ssn 		= db.f.m.lf.getSsnFromGameID(gameID);		
		List<String> userIDs	= listUserIDs(ssn);
		double []	 a			= new double [userIDs.size()];
		int			 i			= 0;

		for (String userID : userIDs) {
			sTable = "bets" + userID;
			a[i] = db.sql.selectdouble(sTable, sColName, BetsC.gID, "=", gameID);
			i++;
		}
		return a;
	}

	/** @throws SQLException */
	public int getNumWins(User user, BetType betType, String teamColQry, int depth, String gameID)
	throws SQLException {
		// this is how to do it:
		//		mysql> select count(*) from (select * from employee where email like "%ail%" and id <= 15 order by id desc limit 10) as bob where lastname like "%a%";

		String query =
			"select count(*) from ("+
			"	select * from "+ Table.bets(user) +" where "+ BetsC.ssn +" = "+ db.f.m.lf.getSsnFromGameID(gameID) +" and "+ 
			"		(where "+ BetsC.a +" like "+ teamColQry +" OR "+ BetsC.h +" like "+ teamColQry +") "+ 
			"		order by "+ BetsC.d +" desc limit "+ depth +
			") as inner where "+ betType.resultCol +" = "+ Value.w;
		
		ResultSet rs;
		rs = db.sql.executeQuery(query, true);
		try {
			return  rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}
	

	/** @throws SQLException */
	public int getNumLosses(User user, BetType betType, String teamColQry, int depth, String gameID) throws SQLException {
		String query =
			"select count(*) from ("+
			"	select * from "+ Table.bets(user) +" where "+ BetsC.ssn +" = "+ db.f.m.lf.getSsnFromGameID(gameID) +" and "+ 
			"		(where "+ BetsC.a +" like "+ teamColQry +" OR "+ BetsC.h +" like "+ teamColQry +") "+ 
			"		order by "+ BetsC.d +" desc limit "+ depth +
			") as inner where "+ betType.resultCol +" = "+ Value.l;
		
		ResultSet rs;
		rs = db.sql.executeQuery(query, true);
		try {
			return  rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}
//
//	public int getNumTies(String userID, BetType betType, String gameID) {
//		String query =
//			"select count(*) from ("+
//			"	select * from "+ Table.bets(userID) +" where "+ BetsC.ssn +" = "+ db.f.m.lf.getSsnFromGameID(gameID) +" and "+ 
//			"		(where "+ BetsC.a +" like "+ betType.teamColQry +" OR "+ BetsC.h +" like "+ betType.teamColQry +") "+ 
//			"		order by "+ BetsC.d +" desc limit "+ betType.depth +
//			") as inner where "+ betType.betColumn +" = "+ Value.p;
//		
//		ResultSet rs;
//		rs = db.sql.executeQuery(query, true);
//		try {
//			return  rs.getInt(1);
//		} catch (SQLException e) {
//			e.printStackTrace();
//			return -1;
//		}
//	}

}
