package database;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import objects.Bet;
import objects.Game;
import objects.Line;
import objects.User;
import schemas.BetsC;
import schemas.GamesC;
import schemas.MetaBetsC;
import schemas.Table;
import schemas.UsersC;

public class Loader {

	DatabaseHandler db;
	
	public Loader(DatabaseHandler databasehandler){
		db = databasehandler;
	}

	public int loadUser(Integer ssn, User user) throws SQLException {
		String tableName = Table.users(ssn);

		String [] columns = {	
				UsersC.userName, 
				UsersC.userID, 
				UsersC.initRank, 
				UsersC.wins, 
				UsersC.losses, 
				UsersC.ties
		};		
		String [] values = {	
				user.userName, 
				user.userID, 
				user.initialRank, 
				user.wins, 
				user.losses, 
				user.ties 
		};
		return db.sql.insert(tableName,columns, values);		
	}
	
	/**
	 * 
	 * @param userID
	 * @param bet
	 * @return the number of rows loaded into the database
	 * @throws SQLException 
	 */
	public int loadBet(String userID, Integer ssn, Bet bet) throws SQLException {		
		String tableName = Table.bets(userID, ssn);//userID.replace('-', '_') + "bets";

		String [] columns = {	BetsC.gID,
								BetsC.ssn,
								BetsC.d,	
								BetsC.a,	
								BetsC.h,	
								BetsC.aS,
								BetsC.hS,
								BetsC.lOU,
								BetsC.lATS,
								BetsC.bOU,
								BetsC.bATS,
								BetsC.rOU,
								BetsC.rATS   };
		
		String [] values = {	bet.gID,
								bet.ssn,
								bet.d,	
								bet.a,	
								bet.h,	
								bet.aS,
								bet.hS,
								bet.lOU,
								bet.lATS,
								bet.bOU,
								bet.bATS,
								bet.rOU,
								bet.rATS	};
		
		ArrayList<String> colList = new ArrayList<String>(Arrays.asList(columns));
		ArrayList<String> valList = new ArrayList<String>(Arrays.asList(values));
		
		//why am i doing this crap.  why not just leave them null? (instead of "_")
		for (int i=columns.length-1; i >= 0; i--){
			if (valList.get(i) == null || valList.get(i) == "_") {
				colList.remove(i);
				valList.remove(i);
			}
		}
		
		String [] strArray = {""};
		return db.sql.insert(tableName, colList.toArray(strArray), valList.toArray(strArray));		
	}


	/**
	 * 
	 * @param game
	 * @return number of rows loaded to db
	 * @throws Exception if new game data disagrees with current database contents
	 */
	public int loadGame(Game game) throws Exception {
		String tableName = Table.games(db.f.m.lf.getSsnFromGameID(game.gID));	//"games" +  db.f.m.lf.getSsnFromGameID(game.gID);
		
		String [] columns = {	GamesC.gID,
								GamesC.d,	
								GamesC.a,	
								GamesC.h,	
								GamesC.aS,
								GamesC.hS,
								GamesC.tot,
								GamesC.spread };
		
		String [] values = {	game.gID,
								game.d,	
								game.a,	
								game.h,	
								game.aS,
								game.hS,
								game.tot,
								game.spread };
		
		//now... extra step ... check if game row already exists.  if it does, ensure that these values all match.
		if (!db.sql.contains(tableName, GamesC.gID, game.gID))
			return db.sql.insert(tableName, columns, values);
		else {
			Game dbGame = db.r.getGame(game.gID);
			dbGame.isTheSameAs(game);		
		}
		
		return -229;
	}

	public void fillGameLines(String gameID, Line lines) throws SQLException {
		String tableName = Table.games(db.f.m.lf.getSsnFromGameID(gameID));	//"games" +  db.f.m.lf.getSsnFromGameID(game.gID);
		
		db.sql.executeUpdate("update "+tableName+" set "+GamesC.lATSave  +"="+lines.lATSave  +" where "+GamesC.gID+"='"+gameID+"'", false);
		db.sql.executeUpdate("update "+tableName+" set "+GamesC.lATSmin  +"="+lines.lATSmin  +" where "+GamesC.gID+"='"+gameID+"'", false);
		db.sql.executeUpdate("update "+tableName+" set "+GamesC.lATSmax  +"="+lines.lATSmax  +" where "+GamesC.gID+"='"+gameID+"'", false);
		db.sql.executeUpdate("update "+tableName+" set "+GamesC.lATSsd   +"="+lines.lATSsd   +" where "+GamesC.gID+"='"+gameID+"'", false);
		db.sql.executeUpdate("update "+tableName+" set "+GamesC.lATSopen +"="+lines.lATSopen +" where "+GamesC.gID+"='"+gameID+"'", false);
		db.sql.executeUpdate("update "+tableName+" set "+GamesC.lATSclose+"="+lines.lATSclose+" where "+GamesC.gID+"='"+gameID+"'", false);
		
	}

	public int loadBetsCreationMetaData(String userID, Integer ssn) throws SQLException {
		String tableName = Table.metaBets();
		String betsTableName = Table.bets(userID, ssn);

		String [] columns = {	
				MetaBetsC.betsTableName, 
				MetaBetsC.isFinished 
		};		
		String [] values = {	
				betsTableName, 
				"0" 
		};
		return db.sql.insert(tableName,columns, values);		
	}

	public void loadBetsCompletionMetaData(String userID, Integer ssn) throws SQLException {
		String tableName = Table.metaBets();
		String betsTableName = Table.bets(userID, ssn);
		
		db.sql.executeUpdate("update "+tableName+
				" set "+
				MetaBetsC.isFinished  +"="+ "1"+
				" where "+
				MetaBetsC.betsTableName+"='"+betsTableName+"'", true);
	}
	
}
