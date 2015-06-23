package ztesting;


/**
 * this is just a text file to hold old dead script i don't want to delete yet
 * @author Admin
 *
 */
public class Scrap {

}

//
//public static String [] ssnDropdownText = {	"1999-2000",
//											"2000-2001",
//											"2001-2002",
//											"2002-2003",
//											"2003-2004",
//											"2004-2005",
//											"2005-2006",
//											"2006-2007",
//											"2007-2008",
//											"2008-2009",
//											"2009-2010",
//											"2010-2011" };
///**
// * needs a lot of work to be updated clean like getUsersBets(...) <br>
// * needs to deal with regular season vs. playoffs for more recent seasons
// * @throws Exception
// */
//public static void getUsers(Integer firstSsn, Integer lastSsn)  throws Exception {
//	initialize();
//	
//	String usersTable;
//
//	String previousTopName;
//	String currentTopName;
//
//	String name, url, id;
//	
//	List<WebElement> userNames;
//	List<WebElement> userUrls;
//	
//	Iterator<WebElement> userNamesI;
//	Iterator<WebElement> userUrlsI;
//	driver.get(leaderboardURL);
//
//	for (Integer ssn = firstSsn - 2000; ssn <= lastSsn - 2000; ssn++) {
//		previousTopName = "prevNull";
//		currentTopName = "currNull";
//		
//		usersTable = db.createUsersTableForSeason(ssn + 2000);
//		
//		wait.until(Sportsfuncs.cssExists(".left a"));
//		Select ssnDropdown = new Select(driver.findElement(By.id("ddlYear")));
//		ssnDropdown.selectByVisibleText(ssnDropdownText[ssn]);
//		driver.findElement(By.cssSelector("#dropDown .btn")).click();
//		Thread.sleep(20000);
//		
//		for (int i = 1; !currentTopName.equals(previousTopName) && i < 3; i++) { // &&  && i < 9 
//			System.out.println("page "+ i);
//			wait.until(Sportsfuncs.cssExists(".left a"));
//
//			userNames = driver.findElements(By.cssSelector(".left a"));
//			userUrls = driver.findElements(By.cssSelector("td:nth-child(7) a"));  //history links
//
//			previousTopName = currentTopName;
//			currentTopName = userNames.get(0).getText();
//			if (currentTopName.equals(previousTopName))
//				break;
//
//			userNamesI = userNames.iterator();
//			userUrlsI = userUrls.iterator();
//			for (int j = 1; userNamesI.hasNext() && userUrlsI.hasNext() && j <= 50; j++) { // restricting to 50 because some wagerline pages have error of listing a bogus empty name listing at bottom of page
//				name = userNamesI.next().getText();
//				url = userUrlsI.next().getAttribute("href");
//				id = Sportsfuncs.extractIdInt(url);
//
//				System.out.println(name + ",   " + id);
//				nr = stmt.executeUpdate("INSERT INTO " + usersTable	+ "(idStr, idInt) VALUES(\"" + name + "\", " + id	+ ")");
//			}
//			driver.findElement(By.cssSelector("#ctrl_leaderboard_next_button")).click();
//		}
//		rs = stmt.executeQuery("describe " + usersTable);
//		db.printRS(rs);
//	
//		rs = stmt.executeQuery("select * from " + usersTable);
//		db.printRS(rs);
//	}
//
//	rs = stmt.executeQuery("show tables");
//	db.printRS(rs);
//	
//}
//



//this stuff from DatabaseHandler
//
//
//public static void main(String [] args){
//	db = new DatabaseHandler("nba");		
//	
//	try {
//	    
//	    try{
//	        stmt.executeUpdate("DROP TABLE IF EXISTS myTable");
//	      }catch(Exception e){
//	        System.out.println(e);
//	        System.out.println(
//	                  "No existing table to delete");
//	      }//end catch
//	      
//	      stmt.executeUpdate("CREATE TABLE myTable(" +
//	      						"test_id int," +
//	      						"test_val char(15) not null" +
//	      					")");
//	      
//	      stmt.executeUpdate("INSERT INTO myTable(test_id, test_val) VALUES(1,'One')");
//	      stmt.executeUpdate("INSERT INTO myTable(test_id, test_val) VALUES(2,'Two')");
//	      stmt.executeUpdate("INSERT INTO myTable(test_id, test_val) VALUES(3,'Three')");
//	      stmt.executeUpdate("INSERT INTO myTable(test_id, test_val) VALUES(4,'Four')");
//	      stmt.executeUpdate("INSERT INTO myTable(test_id, test_val) VALUES(5,'Five')");
//	      
//	      //not sure about the point of this extra stuff in here:
////	      stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
//	      
//	      rs = stmt.executeQuery("SELECT * from myTable ORDER BY test_id");
//	      
//	      System.out.println("Display all results:");
//	      while(rs.next()){
//	        int theInt= rs.getInt("test_id");
//	        String str = rs.getString("test_val");
//	        System.out.println("\ttest_id= " + theInt + "\tstr = " + str);
//	      }//end while loop
//	      
//	      
//	      
//	      System.out.println(
//          "Display row number 2:");
//			if( rs.absolute(2) ){
//			int theInt= rs.getInt("test_id");
//			String str = rs.getString("test_val");
//			System.out.println("\ttest_id= " + theInt
//			                   + "\tstr = " + str);
//			}//end if
//
//			rs.beforeFirst();
//
//
//		      System.out.println("Display all results again:");
//		      while(rs.next()){
//		    	  System.out.println("");
//		    	  
//		    	  
//		    	  
//		      }//end while loop
//			
//			
//		      stmt.executeUpdate("DROP TABLE myTable");
//		   //   con.close();
//		  //  }catch( Exception e ) {
//		  //    e.printStackTrace();
//		  //  }//end catch
//
//	      
//
//	    
//	    stmt.execute("DROP TABLE IF EXISTS animal");
//	    System.out.println(stmt.getResultSet());
//	    stmt.execute("CREATE TABLE animal (" +
//	    		"name     CHAR(40)," +
//	    		"category CHAR(40)" +
//	    		" ) ");
//	    System.out.println(stmt.getResultSet());
//	    
//	    
//	    stmt.execute("INSERT INTO animal (name, category) VALUES" +
//	    		" ('snake', 'reptile')," +
//	    		" ('frog', 'amphibian')," +
//	    		" ('tuna', 'fish')," +
//	    		" ('racoon', 'mammal')");
//	    System.out.println(stmt.getResultSet());
//	    stmt.execute("SELECT name, category FROM animal");
//	    System.out.println(stmt.getResultSet());
//	    /*
//	    stmt.execute();
//	    System.out.println(stmt.getResultSet());
//	    stmt.execute();
//	    System.out.println(stmt.getResultSet());
//	    stmt.execute();
//	    System.out.println(stmt.getResultSet());
//	    stmt.execute();
//	    System.out.println(stmt.getResultSet());
//	    stmt.execute();
//	    System.out.println(stmt.getResultSet()); */
//	    //rs = stmt.executeQuery("");
//	    //rs = stmt.executeQuery("");
//	    //rs = stmt.executeQuery("");
//	    //rs = stmt.executeQuery("");
//	    
//	    
//	    
//	    // or alternatively, if you don't know ahead of time that
//	    // the query will be a SELECT...
//
//	    if (stmt.execute("SELECT foo FROM bar")) {
//	        rs = stmt.getResultSet();
//	    }
//
//	    // Now do something with the ResultSet ....
//	}
//	catch (SQLException ex){
//	    // handle any errors
//	    System.out.println("SQLException: " + ex.getMessage());
//	    System.out.println("SQLState: " + ex.getSQLState());
//	    System.out.println("VendorError: " + ex.getErrorCode());
//	}
//	finally {
//	    // it is a good idea to release
//	    // resources in a finally{} block
//	    // in reverse-order of their creation
//	    // if they are no-longer needed
//
//	    if (rs != null) {
//	        try {
//	            rs.close();
//	        } catch (SQLException sqlEx) { } // ignore
//
//	        rs = null;
//	    }
//
//	    if (stmt != null) {
//	        try {
//	            stmt.close();
//	        } catch (SQLException sqlEx) { } // ignore
//
//	        stmt = null;
//	    }
//	}
//	
//
//}



