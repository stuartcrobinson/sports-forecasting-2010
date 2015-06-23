package database;

import java.sql.SQLException;

import main.Farmer;
import mysql.MySQLHandler;

public class DatabaseHandler {
	
	public Farmer f;
	
	public MySQLHandler sql;
	
	public Loader l;
	public Retriever r;
	public TableMaker tm;
	
	public DatabaseHandler(Farmer farmer) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		f = farmer;
		
		sql = new MySQLHandler(f.m.lf.databaseName);
		l = new Loader(this);
		r = new Retriever(this);
		tm = new TableMaker(this);
	}

	public void close(){
		sql.close();
	}
	
	
}
