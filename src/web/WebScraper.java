package web;




import helperfuncs.Helper;
import helperfuncs.Leaguefuncs.InvalidTeamException;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



import objects.Bet;
import objects.User;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;



/**
 * scrapes information from the current page.
 * @author Admin
 *
 */
public class WebScraper {

	WebHandler w;

	public String leaderboardPageTopNape_previous;
	public String leaderboardPageTopName_current;

	public WebScraper(WebHandler webhandler){
		w = webhandler;
	}

	/**
	 * @return list of Detail link url's from a user's Record page
	 */
	public List<String> listDetailLinks(){

		List<WebElement> detailLinks = w.d.findElements(By.cssSelector(".data a"));

		ArrayList<String> urls = new ArrayList<String>();

		for (WebElement e : detailLinks){
			urls.add("http://contests.covers.com" + e.getAttribute("href"));
		}
		return urls;
	}


	/**
	 * with the w.d pointing to a Detail page -- bets on a given date -- create a Bet 
	 * object for each listed bet, return these bets as a List<Bet>
	 * @return
	 */
	public List<Bet> listBets() {
		Bet bet;
		ArrayList<Bet> bets = new ArrayList<Bet>();

		w.wait.until(w.cssExists(".roundbox-profile a:nth-child(1)"));
		String userName = w.d.findElement(By.cssSelector(".roundbox-profile a:nth-child(1)")).getText();
		String userID = Helper.extractUserID(w.d.getCurrentUrl());

		String pageDate_raw = Helper.extractDate(
				w.d.findElement(By.cssSelector("#objRecordHeader_lblRecordDate span")).getText());

		String pageDate_mysql = Helper.reformatDate(pageDate_raw, "MM/dd/yyyy", "yyyy-MM-dd");
		String pageDate_gID = Helper.reformatDate(pageDate_raw, "MM/dd/yyyy", "yyyyMMdd");

		String sSsn = w.f.m.lf.getSsnFromDate(pageDate_mysql, "yyyy-MM-dd").toString();

		List<WebElement> weTeams = w.d.findElements(By.cssSelector("#innercontent td:nth-child(2)"));
		List<WebElement> weScores = w.d.findElements(By.cssSelector("td:nth-child(3)"));
		List<WebElement> weBets = w.d.findElements(By.cssSelector("td:nth-child(4)"));
		List<WebElement> weResults = w.d.findElements(By.cssSelector("td:nth-child(6)"));

		System.out.println(	"0-index of the web element lists: "+ 
				weTeams.get(0).getText() +", " + 
				weScores.get(0).getText() +", " + 
				weBets.get(0).getText() +", " + 
				weResults.get(0).getText());
		
		for (int i=1; i < weTeams.size(); i++){			//start at 1 instead of 0 cuz first element is column heading
			try {
				bet = new Bet();

				bet.awayTeamFullName = weTeams.get(i).getText();
				bet.a 				 = w.f.m.lf.teamAcronym(weTeams.get(i).getText());
				bet.aS 				 = weScores.get(i).getText();
				bet.wlBetText1 		 = weBets.get(i).getText();
				bet.result1 		 = Bet.translateResult(weResults.get(i).getText());

				i++;

				bet.homeTeamFullName = weTeams.get(i).getText();
				bet.h 				 = w.f.m.lf.teamAcronym(weTeams.get(i).getText());
				bet.hS 				 = weScores.get(i).getText();
				bet.wlBetText2 		 = weBets.get(i).getText();
				bet.result2 		 = Bet.translateResult(weResults.get(i).getText());


				bet.gID = bet.a + pageDate_gID + bet.h;
				bet.ssn = sSsn;
				bet.d = pageDate_mysql;		//formatted for mysql date
				bet.userID = userID;
				bet.userName = userName;

				bet.processBetStrings();

				bets.add(bet);
				
			} catch (InvalidTeamException e) {
				e.printStackTrace();
			}
		}

		return bets;
	}

	//get url_history values:
	// //*[contains(text(), 'foo')]
	// //table[@id="dtSeasonRecords"]/tbody/tr/td[. = '2009-2010 Season']/parent::*/following-sibling::tr[1][@class]/td[@align='center']/
	// //*[contains(text(), '2007-2008')]
	
	/** 
	 * use in a leaderboard page
	 * @return
	 */
	public String getTopUserName() {
		return w.d.findElement(By.cssSelector("tr:nth-child(2) .left a")).getText();
	}

	public List<User> listUsers() throws Exception {
		List<User> users = new ArrayList<User>();
		User user;

		w.wait.until(w.cssExists(".left a"));

		List<WebElement> userRanks = w.d.findElements(By.cssSelector(".left:nth-child(1)"));
		userRanks.remove(0);
		List<WebElement> userNames = w.d.findElements(By.cssSelector(".left a"));
		List<WebElement> userStats = w.d.findElements(By.cssSelector(".center"));	//WLT - remove the first element (column header)
		userStats.remove(0);
		List<WebElement> userUrls = w.d.findElements(By.cssSelector("td:nth-child(7) a"));  //history links


		Pattern pattern = Pattern.compile("([\\d]*)\\-([\\d]*)\\-([\\d]*)");
		Matcher matcher;
		String w, l, t;


		if (userNames.size() != userUrls.size()) 
			throw new Exception("unequal list lengths in listUsers()");

		for (int i=0; i < userNames.size() && i < 50; i++){

			matcher = pattern.matcher(userStats.get(i).getText());
			System.out.println("stats text: "+ userStats.get(i).getText());

			if (!matcher.find())
				throw new Exception("no WLT match in "+ userStats.get(i).getText());

			w = matcher.group(1);
			l = matcher.group(2);
			t = matcher.group(3);

			user = new User(
					Helper.extractUserID(userUrls.get(i).getAttribute("href")),
					userNames.get(i).getText(),
					userRanks.get(i).getText(),
					w, l, t);
			users.add(user);			
		}

		return users;
	}



}
