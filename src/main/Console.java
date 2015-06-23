package main;


public class Console {

	
	/**
	 * "season" refers to the year in which the given season ended.  so, it has 4 digits. <br>
	 * 'usersTable' is the table of users <br>
	 * 'userBetsTable' is the table of bets for one user.<br>
	 * @param args
	 * @throws InterruptedException 
	 * @throws Exception
	 */
	public static void main(String[] args) throws InterruptedException {
		
		Master m = new Master(Master.NBA);

		Research research = new Research();
		research.initialize();
		
		Integer firstSsn = new Integer(2004);
		Integer lastSsn = new Integer(2004);
		
		m.setResearchParameters(research);
		
		try { m.connectDatabase();				}catch (Exception e) {e.printStackTrace();}
		
		
		m.startWebBrowser();

		//what else should metabets have?  like, some brief stats on the table? num rows, OU/ATS w,l,t?
		//history page: http://contests.covers.com/sportscontests/profile.aspx?user=53243&sportID=9&type=5
		
//		try { m.getUsers(firstSsn, lastSsn);				}catch (Exception e) {e.printStackTrace();}
//		try { m.getBets(firstSsn, lastSsn); 				}catch (Exception e) {e.printStackTrace();}
//		try { m.getLines(firstSsn, lastSsn); 				}catch (Exception e) {e.printStackTrace();}
		
		m.quitWebBrowser();

		try { m.fillGames(firstSsn, lastSsn);				}catch (Exception e) {e.printStackTrace();}
		try { m.fillLines_reconstructed(firstSsn, lastSsn);	}catch (Exception e) {e.printStackTrace();}

		
		try { m.analyze(firstSsn, lastSsn);	}catch (Exception e) {e.printStackTrace();}

		
		m.closeDatabase();		
	}
}
