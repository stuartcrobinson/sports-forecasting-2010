package database;

import java.sql.SQLException;

import schemas.BetsC;
import schemas.GamesC;
import schemas.MetaBetsC;
import schemas.Table;
import schemas.UsersC;
import mysql.MySQLHandler;

public class TableMaker {

	DatabaseHandler data;
	MySQLHandler db;


	public TableMaker(DatabaseHandler databaseHandler) {
		data = databaseHandler;
		db = data.sql;
	}

	/** TODO add a good index!!! ?<br>
	 * creates the table if it doesn't exist.  
	 * @param userID
	 * @return the number of rows in the table
	 * @throws SQLException 
	 */
	public int makeUsersTable(Integer ssn) throws SQLException {
		String tableName = Table.users(ssn);

		String columnDescriptions =
			UsersC.id 		+ " mediumint unsigned not null auto_increment primary key, " +
			UsersC.initRank	+ " mediumint unsigned not null, " +
			UsersC.userID	+ " int not null, index ( "+ UsersC.userID +" ),  " +
			UsersC.userName + " char(30) not null, " +
			UsersC.wins		+ " smallint not null, " +
			UsersC.losses	+ " smallint not null, " +
			UsersC.ties		+ " smallint not null";
		boolean doOverwrite = true;		

		db.createTable(tableName, columnDescriptions, doOverwrite);
		return -229;												//this means i haven't gotten this far; the function is sitll under construction
	}

	/** creates the table if it doesn't exist.  
	 * @param userID
	 * @return the number of rows in the table
	 * @throws SQLException 
	 */
	public int makeMetaBetsTable() throws SQLException {
		String tableName = Table.metaBets();

		String columnDescriptions =
			MetaBetsC.id 			+ " mediumint unsigned not null auto_increment primary key, " +
			MetaBetsC.betsTableName + " char(16) not null, index ( "+ MetaBetsC.betsTableName +" ), " +
			MetaBetsC.isFinished	+ " tinyint unsigned not null";
		boolean doOverwrite = true;		

		db.createTable(tableName, columnDescriptions, doOverwrite);
		return -229;												//this means i haven't gotten this far; the function is sitll under construction
	}

	/** TODO add a good index!!! ?<br>
	 * creates the table if it doesn't exist.  
	 * @param userID
	 * @return the number of rows in the table
	 * @throws SQLException 
	 */
	public int makeBetsTable(String userID, Integer ssn) throws SQLException{
		String tableName = Table.bets(userID, ssn); 

		String columnDescriptions =
			BetsC.id 	+ " mediumint unsigned not null auto_increment primary key, " +
			BetsC.ssn	+ " smallint unsigned not null, " +
			BetsC.gID 	+ " char(16) not null, index ( "+ BetsC.gID +" ), " +
			BetsC.d		+ " date not null, " +
			BetsC.a		+ " char(4) not null, " +
			BetsC.h		+ " char(4) not null, " +
			BetsC.aS	+ " tinyint unsigned not null, " +
			BetsC.hS	+ " tinyint unsigned not null, " +

			BetsC.lOU	+ " double(4,1), " +
			BetsC.bOU	+ " char(1), " +
			BetsC.rOU	+ " char(1), " +

			BetsC.lATS	+ " double(3,1), " +
			BetsC.bATS	+ " char(1), " +
			BetsC.rATS	+ " char(1)";
		boolean doOverwrite = true;		

		db.createTable(tableName, columnDescriptions, doOverwrite);
		return -229;												//this means i haven't gotten this far; the function is sitll under construction
	}


	/**
	 * add an index!
	 * @param ssn
	 * @return
	 * @throws SQLException 
	 */
	public int makeGamesTable(Integer ssn) throws SQLException {
		String tableName = Table.games(ssn);

		String columnDescriptions =
			GamesC.id 		+ " mediumint unsigned not null auto_increment, " +
			GamesC.gID	 	+ " char(16) not null  primary key, " +
			GamesC.d		+ " date not null, " +
			GamesC.a		+ " char(4) not null, " +
			GamesC.h		+ " char(4) not null, " +
			GamesC.aS		+ " tinyint unsigned not null, " +
			GamesC.hS		+ " tinyint unsigned not null, " +
			GamesC.tot		+ " tinyint unsigned, " +
			GamesC.spread	+ " tinyint, " +

			GamesC.lOUmin	+ " double(4,1), " +
			GamesC.lOUmax	+ " double(4,1), " +
			GamesC.lOUave	+ " double(4,1), " +
			GamesC.lOUsd	+ " double(3,2), " +
			GamesC.lOUopen	+ " double(4,1), " +
			GamesC.lOUclose	+ " double(4,1), " +

			GamesC.lATSmin	+ " double(3,1), " +
			GamesC.lATSmax	+ " double(3,1), " +
			GamesC.lATSave	+ " double(3,1), " +
			GamesC.lATSsd	+ " double(3,2), " +
			GamesC.lATSopen	+ " double(4,1), " +
			GamesC.lATSclose+ " double(4,1)";
		
		boolean doOverwrite = true;		

		db.createTable(tableName, columnDescriptions, doOverwrite);
		return -229;												//this means i haven't gotten this far; the function is sitll under construction
	}



}
