
//classpath for this needs to be set to the connector j jar in Development, in the root directory 


package mysql;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

	
/**
 * this is a wrapper for mysql commands
 * @author Admin
 *
 */
public class MySQLHandler {	
	public static Connection conn = null;		
	public static Statement stmt = null;
	public static ResultSet rs = null;
	
	/** num rows returned by stmt.executeUpdate(...) command */
	public static int nr = -1;
	
	public static MySQLHandler db = null;

		
	public MySQLHandler(String database) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
//		try {
			conn = getConnection();
			stmt = conn.createStatement();
			stmt.executeUpdate("create database if not exists nba");
			stmt.executeUpdate("use nba");
//		} catch (SQLException e) {e.printStackTrace();}
	}

	public void close() {

		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException sqlEx) { } // ignore

			rs = null;
		}

		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException sqlEx) { } // ignore

			stmt = null;
		}


		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException sqlEx) { } // ignore

			conn = null;
		}
	}
	
	
	/**
	 * password is embedded.  returns a connector j Connection object.
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public Connection getConnection() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
//        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
//        } catch (Exception ex) {
//        	System.out.println("error1 :( ");
//        }

		Connection myConn = null;
		
		try {
		    myConn = DriverManager.getConnection("jdbc:mysql://localhost/test?" + "user=root");//&password=tand0g");
		    return myConn;
	
		} catch (SQLException ex) {
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		    return null;
		}
	}

	public void printRS(ResultSet rs) throws SQLException {

		//		try {
		for (int r = 1; rs.next(); r++) {
			for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
				//					try {
				System.out.println("row " + r + ", "
						+ rs.getMetaData().getColumnName(i) + ": "
						+ rs.getString(i));
				//					} catch (Exception e) {
				//						// TODO Auto-generated catch block
				//						e.printStackTrace();
				//					}
			}
		}
		//		} catch (Exception e) { System.out.println("error printing RS " + e);}
		//		try {
		rs.beforeFirst();
		//		} catch (SQLException e) {
		//			// TODO Auto-generated catch block
		//			e.printStackTrace();
		//		}
	}
	
	
	/**
	 * delete this and replace it with selectStringList to remove mysql queries from my scripts
	 * @param rs
	 * @param i
	 * @return
	 * @throws SQLException 
	 */
	public List<String> getColAsStringArrayList(ResultSet rs, int i) throws SQLException {
		List<String> myA = new ArrayList<String>();
//		try {
			for (int r = 1; rs.next(); r++) {
//				try {
					myA.add(rs.getString(i));
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
				
			}
//		} catch (Exception e) { System.out.println("error getting array from RS " + e);}
		return myA;
	}

	public ResultSet executeQuery(String query, boolean printTheQuery) throws SQLException {
//		try {
			if (printTheQuery)
				System.out.println(query);
			return stmt.executeQuery(query);
//		}
//		catch (SQLException e) {
//			e.printStackTrace();
//			return null;
//		}
	}
	public int executeUpdate(String query,  boolean printTheQuery) throws SQLException {
//		try {
			if (printTheQuery)
				System.out.println(query);
			return stmt.executeUpdate(query);
//		}
//		catch (SQLException e) {
//			e.printStackTrace();
//			return -1;
//		}
	}

	/**
	 * 
	 * @param targetColumn
	 * @param conditionColumn
	 * @param compare examples: =, >, <, like.  ie,  where f_name LIKE "J%";
	 * @param conditionValue
	 * @param table
	 * @return
	 * @throws SQLException 
	 */
	public List<String> selectStringList(String table, String targetColumn, String conditionColumn, String compare, Object conditionValue) throws SQLException {
		rs = executeQuery("SELECT "+targetColumn+" from "+table+" where "+conditionColumn+" "+
				compare+" \"" +conditionValue.toString()+ "\"", true);
		ArrayList<String> myA = new ArrayList<String>();
//		try {
			for (int r = 1; rs.next(); r++) {
//				try {
					myA.add(rs.getString(1));
//					}
//				catch (Exception e) {e.printStackTrace();}	
			}
//		} catch (Exception e) { System.out.println("error getting array from RS " + e);}
		return myA;
	}

	/**convenience function function for getting an entire column of a table with no conditions
	 * 
	 * @param targetColumn
	 * @param conditionColumn
	 * @param compare examples: =, >, <, like.  ie,  where f_name LIKE "J%";
	 * @param conditionValue
	 * @param table
	 * @return
	 * @throws SQLException 
	 */
	public List<String> selectStrings(String table, String targetColumn) throws SQLException {

		//		try {
		String query = "SELECT "+targetColumn+" from "+table;
		System.out.println("query: " + query);
		rs = stmt.executeQuery(query);
		//		} catch (SQLException e) {
		//			e.printStackTrace();
		//		}
		ArrayList<String> myA = new ArrayList<String>();
		//		try {
		for (int r = 1; rs.next(); r++) {
			//				try {
			myA.add(rs.getString(1));
			//					}
			//				catch (Exception e) {e.printStackTrace();}	
		}
		//		} catch (Exception e) { System.out.println("error getting array from RS " + e);}
		return myA;	
	}


	/**
	 * returns null if there are 0 or more than 1 results
	 * @param targetColumn
	 * @param conditionColumn
	 * @param compare
	 * @param conditionValue  should accept a string, int, or double
	 * @param table
	 * @return
	 * @throws SQLException 
	 */
	public String selectString(String table, String targetColumn, String conditionColumn, String compare, Object conditionValue) throws SQLException {
		String res = null;
//		try {
			rs = stmt.executeQuery("SELECT "+targetColumn+" from "+table+" where "+conditionColumn+" "+compare+" \"" +conditionValue.toString()+ "\"");
			if (rs.next())
				res = rs.getString(1);
			if (rs.next())					//there can only be one
				res = null;
//		} catch (SQLException e) {e.printStackTrace();}
		return res;
	}
	
		
	public double selectdouble(String table, String targetColumn,
			String conditionColumn, String compare, String conditionValue) throws Exception {
		rs = executeQuery("SELECT "+targetColumn+" from "+table+" where "+conditionColumn+" "+compare+" \"" +conditionValue.toString()+ "\"", true);
//		try{ 
			if (rs.next())
				return Double.parseDouble(rs.getString(1));
			if (rs.next())												//there can only be one
				throw new Exception("multiple values in result set");//}catch (Exception e){e.printStackTrace();}
			throw new Exception("empty result set");//}catch (Exception e){e.printStackTrace();}
//		}catch (SQLException e) {e.printStackTrace();}		
	}
	
	/**
	 * return stmt.executeQuery("SELECT "+targetColumn+" from "+table+" where "+conditionColumn+" "+compare+" \"" +conditionValue.toString()+ "\"");
	 * @param table
	 * @param targetColumn
	 * @param conditionColumn
	 * @param compare
	 * @param conditionValue
	 * @return
	 * @throws SQLException 
	 */
	public ResultSet selectRS(String table, String targetColumn, String conditionColumn, String compare, Object conditionValue) throws SQLException {
//		try {
			return stmt.executeQuery("SELECT "+targetColumn+" from "+table+" where "+conditionColumn+" "+compare+" \"" +conditionValue.toString()+ "\"");
//		} catch (SQLException e) {e.printStackTrace();}
//		return null;
	}
	
	
	/**
	 * 
	 * @param targetColumn
	 * @param targetValue
	 * @param conditionColumn
	 * @param compare
	 * @param conditionValue
	 * @param table
	 * @return the number of rows affected (the result of stmt.executeUpdate(...)
	 * @throws SQLException 
	 */
	public int update(String table, String targetColumn, Object targetValue, String conditionColumn, String compare, Object conditionValue) throws SQLException{
		//UPDATE table_name SET field1=new-value1, field2=new-value2 [WHERE Clause]
		int res = -1;
//		try {
			res = stmt.executeUpdate("update "+table+" set "+targetColumn+" = \"" +conditionValue.toString()+ "\" where "+
																conditionColumn+" "+compare+" \"" +conditionValue.toString()+ "\"");
//		} catch (SQLException e) {e.printStackTrace();}
		return res;
	}
	
	/**
	 * 
	 * @param table
	 * @param columns
	 * @param values
	 * @return the number of rows inserted
	 * @throws SQLException 
	 */
	public int insert(String table, String [] columns, String [] values) throws SQLException{
		System.out.println("cols: " + Arrays.asList(columns).toString());
		System.out.println("vals: " + Arrays.asList(values).toString());
		if (columns.length != values.length || columns.length == 0){
			System.out.println("in DatabaseHandler.insert, column length not equal to values length or is 0");
			return -1;
		}
			
		String sColumnsFormatted = columns[0];
		String sValuesFormatted = "\"" + values[0]+ "\"";		//to be formatted for the query, with commas
		
		
		
		for (int i=1; i< columns.length; i++){
			sColumnsFormatted += ", " + columns[i];
			sValuesFormatted += ", " + "\"" + values[i]+ "\"";
		}
		

		int res = -1;
//		try {
			String query = "insert into "+table+" ("+sColumnsFormatted+") values (" +sValuesFormatted+ ")";
			System.out.println(query);
			res = stmt.executeUpdate(query);
//		} catch (SQLException e) {e.printStackTrace();}
		return res;
	}
	
	/**
	 * 
	 * @param targetColumn
	 * @param targetValue
	 * @param conditionColumn
	 * @param compare
	 * @param conditionValue
	 * @param table
	 * @return true if selectString(...) call returns the targetValue in a given cell
	 * @throws SQLException 
	 */
	public boolean containsCell(String table, String targetColumn, String targetValue, String conditionColumn, String compare, String conditionValue) throws SQLException{
		if (selectString(table, targetColumn, conditionColumn, compare, conditionValue).equals(targetValue.toString()))
			return true;
		return false;
	}
	
	
	/**
	 * convenience call to containsCell with no conditions
	 * (will this work?  with the conditional column same as target column, condition: "like '%""
	 * @param table
	 * @param targetColumn
	 * @param targetValue
	 * @return
	 * @throws SQLException 
	 */
	public boolean contains(String table, String targetColumn, String targetValue) throws SQLException{
		if (containsCell(table, targetColumn, targetValue, targetColumn, "=", targetValue))
			return true;
		return false;
	}
	
	/**
	 * 
	 * @param tableName
	 * @param columnDescriptions - without the parentheses 
	 * @param doOverwrite - if true, the table will be dropped if already exists before attepting to create
	 * @return
	 * @throws SQLException 
	 */
	public String createTable(String tableName, String columnDescriptions, boolean doOverwrite) throws SQLException {
		String query;
//		try {
			if (doOverwrite){
				query = "DROP TABLE IF EXISTS " + tableName;
				System.out.println(query);
				stmt.executeUpdate(query);
			}
			query = "CREATE TABLE IF NOT EXISTS " + tableName + " (" + columnDescriptions + ")";
			System.out.println(query);
			stmt.executeUpdate(query); 

//			rs = stmt.executeQuery("describe " + tableName);
//			System.out.println("describe " + tableName);
//			db.printRS(rs);
		
//		} catch (Exception e) { System.out.println("error creating table " + tableName + ". " + e);}	
		return tableName;
	}

	public boolean tableContainsColumn(String sTable, String sCol) throws SQLException {
		ResultSet rs = executeQuery("describe "+ sTable, false);
		List<String> cols = getColAsStringArrayList(rs, 1);
		return cols.contains(sCol);
	}


	
	
	
}
