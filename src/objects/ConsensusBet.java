package objects;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import main.Master.DateSpan;
import main.Master.UserAndUnits;

import org.apache.commons.math.stat.StatUtils;


/**
 * contains bet, result, ConsensusBetID, and percent <br><br>
 * 
 * encapsulates consensus bet's id, bet ('a', 'o', 'h', 'u', 'p'...), pct (percent strength of the consensus bet), and result (whether the cons bet is a win, lose, or push)
 * <br>
 * should 'result' really be in here?<br>
 * staticfuncs.Analytics.calculateConsensusBet(SortedMap<Double, UserAndBet>, int) can't determine the result.  
 * something else will have to come along and fill that in later.
 * @author Admin
 *
 */
public class ConsensusBet {

	public BetType betType;
	public String bet, result;
	public ConsensusBetID id;
	public double percent;

	private ConsensusBet(){}

		/**
		 * i think this also needs some qualifier, like consensus bet ID, to map these results details with the 
		 * bet's filter and parameter constraints.<br>
		 * each consensusBetDetail ID will essentially be a column in the RAM table of Game objects -- each Game
		 * object contains a list (row) of consensusBetDetails.  so they need IDs to be able to tabulate results
		 * in final list of Game objects
		 * @param betType 
		 * @param b
		 * @param pct
		 * @param r
		 */
		private ConsensusBet(BetType betType, String bet, double pct){
			this.betType = betType;
			this.bet = bet;
			this.percent = pct;
		}

	/** TODO needs to know which line to use - ave, open, close, min, max fills result<br>
	 * sets this.result */
	public void processResult(Game game) {
		
		if (betType.type.equals(BetType.ATS)){
			if (this.bet.equals(Value.a)){ 
				if ( Double.parseDouble(game.spread) < Double.parseDouble(game.lATSave) )
					this.result = Value.w;
				if ( Double.parseDouble(game.spread) > Double.parseDouble(game.lATSave) )
					this.result = Value.l;
				if ( Double.parseDouble(game.spread) == Double.parseDouble(game.lATSave) )
					this.result = Value.p;				
			}
			if (this.bet.equals(Value.h)){
				if ( Double.parseDouble(game.spread) > Double.parseDouble(game.lATSave) )
					this.result = Value.w;
				if ( Double.parseDouble(game.spread) < Double.parseDouble(game.lATSave) )
					this.result = Value.l;
				if ( Double.parseDouble(game.spread) == Double.parseDouble(game.lATSave) )
					this.result = Value.p;				
			}
		}
		if (betType.type.equals(BetType.OU)){
			if (this.bet.equals(Value.o)){
				if ( Double.parseDouble(game.tot) > Double.parseDouble(game.lOUave) )
					this.result = Value.w;
				if ( Double.parseDouble(game.tot) < Double.parseDouble(game.lOUave) )
					this.result = Value.l;
				if ( Double.parseDouble(game.tot) == Double.parseDouble(game.lOUave) )
					this.result = Value.p;				
			}
			if (this.bet.equals(Value.u)){
				if ( Double.parseDouble(game.tot) < Double.parseDouble(game.lOUave) )
					this.result = Value.w;
				if ( Double.parseDouble(game.tot) > Double.parseDouble(game.lOUave) )
					this.result = Value.l;
				if ( Double.parseDouble(game.tot) == Double.parseDouble(game.lOUave) )
					this.result = Value.p;				
			}
		}		
	}


	/**fills String bet and double percent
	 * @throws Exception */
	public static ConsensusBet calculateConsensusBet(
			BetType betType, List<UserAndUnits> allRankedUsers, int base, int size) throws Exception {
		List<UserAndUnits> rankedUsers = allRankedUsers.subList(0, size);

		double [] weights = new double[size];

		for(int i=0; i < size; i++)
			weights[i] = Math.pow(base, (i+1)*-1);

		double sumWeights = StatUtils.sum(weights);
		
		HashMap<String, Double> weightedBetValues = new HashMap<String, Double>();

		//initialize weightedBetBalues' keys: (o, u) or (a, h)
		for (String b : betType.possibleBets)
			weightedBetValues.put(b, 0.0);
		
		for (int i=0; i < rankedUsers.size(); i++){
			UserAndUnits u = rankedUsers.get(i);
			
			weightedBetValues.put(u.user.bet, weightedBetValues.get(u.user.bet) + weights[i]);
		}

		Collection<Double> weightedValues = weightedBetValues.values();
		double maxWeightedValue = Collections.max(weightedValues);
		
		List<String> bets = new ArrayList<String>(weightedBetValues.keySet());
		
		//now get the key that has this value (of maxWeightedValue)
		for (String bet : bets)			
			if (weightedBetValues.get(bet).equals(maxWeightedValue))
				return new ConsensusBet(betType, bet, maxWeightedValue/sumWeights);
		throw new Exception("error getting key for maxWeightedValue in calculateConsenusBet");
	}

	/** 
	 * @param pctCutoff2 */
	public void processID(DateSpan days, BetType betType, TeamType teamType, int depth, int base, int n, int pctCutoff) {
		// TODO Auto-generated method stub
		this.id = new ConsensusBetID(days, betType, teamType, depth, base, n, pctCutoff);

	}
}