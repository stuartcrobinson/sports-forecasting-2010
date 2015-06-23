package objects;

import helperfuncs.Helper;



/** what will this have? <br><br>
 * 
 * consensusBetID, numTotalGames, numGamesBetUpon, winrate, profit
 * 
 * 
 * 
 * 
 * 
 * 
 * <br>startDate, endDate -- no this stuff should bein consBetID
 * <br>
 * 
 * 
 * 
 * @author Stuart
 *
 */
public class ConsensusBetResult {

	ConsensusBetID consensusBetID;
	public int wins;
	public int losses;
	public int ties;

	public int totNumGames;
	public int numGamesBetUpon;
	public double winrate;
	public double profit;
	
	public ConsensusBetResult(ConsensusBetID consensusBetID) {
		this.consensusBetID	= consensusBetID;
		wins = 0;
		losses = 0;
		ties = 0;

		totNumGames = 0;
		numGamesBetUpon = 0;
	}

	public final ConsensusBetID consBetID = null;

	/** calcs winrate and profit using vig = 13% and principal = $500 */
	public void finishProcessing() {

		this.winrate = (double) this.wins / this.numGamesBetUpon;
		this.profit = Helper.calculateProfit(0.13, 500, this.wins, this.losses);		
	}

}
