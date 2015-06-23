
//classpath for this needs to be set to the connector j jar in Development, in the root directory 


package ztesting;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

	
public class mysqlTest {


	
	public static void main(String [] args){
		try {
			// The newInstance() call is a work around for some
			// broken Java implementations

			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (Exception ex) {
			System.out.println("error1 :( ");
			// handle the error
		}

		
		
		Connection conn = null;
		
		
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost/test?" +
										 "user=root"); //&password=tand0g
	
			// Do something with the Connection
	
		} catch (SQLException ex) {
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
		
		
		
		Statement stmt = null;
		ResultSet rs = null;

		
		
		try {
			stmt = conn.createStatement();
			
			int nr;


		//nr = stmt.executeUpdate("drop database if exists monkeys");
		 // nr = stmt.executeUpdate("drop database if exists nba");
			nr = stmt.executeUpdate("create database if not exists nba");
			System.out.println(nr + " rows involved creating database nba");	
			rs = stmt.executeQuery("show databases");

			while(rs.next()){
			 // int theInt= rs.getInt("test_id");
			 // String str = rs.getString("test_val");
				System.out.println("col 1: " + rs.getString(1));
			}//end while loop
			
			
			
			try{
				stmt.executeUpdate("DROP TABLE IF EXISTS myTable");
			}catch(Exception e){
				System.out.println(e);
				System.out.println(
						"No existing table to delete");
			}//end catch
			
			stmt.executeUpdate("CREATE TABLE myTable(" +
									"test_id int," +
									"test_val char(15) not null" +
								")");
			
			stmt.executeUpdate("INSERT INTO myTable(test_id, test_val) VALUES(1,'One')");
			stmt.executeUpdate("INSERT INTO myTable(test_id, test_val) VALUES(2,'Two')");
			stmt.executeUpdate("INSERT INTO myTable(test_id, test_val) VALUES(3,'Three')");
			stmt.executeUpdate("INSERT INTO myTable(test_id, test_val) VALUES(4,'Four')");
			stmt.executeUpdate("INSERT INTO myTable(test_id, test_val) VALUES(5,'Five')");
			
			//not sure about the point of this extra stuf in here:
	//		stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			rs = stmt.executeQuery("SELECT * from myTable ORDER BY test_id");
			
			System.out.println("Display all results:");
			while(rs.next()){
				int theInt= rs.getInt("test_id");
				String str = rs.getString("test_val");
				System.out.println("\ttest_id= " + theInt + "\tstr = " + str);
			}//end while loop
			
			
			
			System.out.println(
			"Display row number 2:");
				if( rs.absolute(2) ){
				int theInt= rs.getInt("test_id");
				String str = rs.getString("test_val");
				System.out.println("\ttest_id= " + theInt
								 + "\tstr = " + str);
				}//end if

				rs.beforeFirst();


				System.out.println("Display all results again:");
				while(rs.next()){
					System.out.println("");
					
					
					
				}//end while loop
				
				
				stmt.executeUpdate("DROP TABLE myTable");
			 // con.close();
			//}catch( Exception e ) {
			//	e.printStackTrace();
			//}//end catch

			

			
			stmt.execute("DROP TABLE IF EXISTS animal");
			System.out.println(stmt.getResultSet());
			stmt.execute("CREATE TABLE animal (" +
					"name	 CHAR(40)," +
					"category CHAR(40)" +
					" ) ");
			System.out.println(stmt.getResultSet());
			
			
			stmt.execute("INSERT INTO animal (name, category) VALUES" +
					" ('snake', 'reptile')," +
					" ('frog', 'amphibian')," +
					" ('tuna', 'fish')," +
					" ('racoon', 'mammal')");
			System.out.println(stmt.getResultSet());
			stmt.execute("SELECT name, category FROM animal");
			System.out.println(stmt.getResultSet());
			/*
			stmt.execute();
			System.out.println(stmt.getResultSet());
			stmt.execute();
			System.out.println(stmt.getResultSet());
			stmt.execute();
			System.out.println(stmt.getResultSet());
			stmt.execute();
			System.out.println(stmt.getResultSet());
			stmt.execute();
			System.out.println(stmt.getResultSet()); */
			//rs = stmt.executeQuery("");
			//rs = stmt.executeQuery("");
			//rs = stmt.executeQuery("");
			//rs = stmt.executeQuery("");
			
			
			
			// or alternatively, if you don't know ahead of time that
			// the query will be a SELECT...

			if (stmt.execute("SELECT foo FROM bar")) {
				rs = stmt.getResultSet();
			}

			// Now do something with the ResultSet ....
		}
		catch (SQLException ex){
			// handle any errors
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
		finally {
			// it is a good idea to release
			// resources in a finally{} block
			// in reverse-order of their creation
			// if they are no-longer needed

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
		}
		

	}
	
	
}
