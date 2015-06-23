package objects;

import java.util.TreeMap;

public class Game {

	public String gID;
	
	/**yyyy-MM-dd*/
	public String d;
	public String a;
	public String h;
	public String aS;
	public String hS;
	public String tot;
	/**hS - aS*/
	public String spread;
	
	public String lOUmin;
	public String lOUmax;
	public String lOUave;
	public String lOUsd;
	public String lOUopen;			//will use later. don't delete
	public String lOUclose;
	
	public String lATSmin;
	public String lATSmax;
	public String lATSave;
	public String lATSsd;
	public String lATSopen;
	public String lATSclose;
	
	public TreeMap<String, ConsensusBet> ConsensusBets;
	
	public Game(){}
	
	/**
	 * does not use lines
	 * @param bet
	 */
	public Game(Bet bet){

		gID =		bet.gID;
		d =			bet.d;
		a =			bet.a;
		h =			bet.h;
		aS =		bet.aS;
		hS =		bet.hS;
		tot =		Integer.toString(Integer.parseInt(hS) + Integer.parseInt(aS));
		spread =	Integer.toString(Integer.parseInt(hS) - Integer.parseInt(aS));
	}

	/**
	 * does not consider lines or bets
	 * @param g
	 * @return
	 * @throws Exception
	 */
	public boolean isTheSameAs(Game g) throws Exception {
		if ((gID ==		g.gID) &&
			(d ==		g.d) &&
			(a ==		g.a) &&
			(h ==		g.h) &&
			(aS ==		g.aS) &&
			(hS ==		g.hS) &&
			(tot ==		g.tot) &&
			(spread ==	g.spread) )	return true;
		else
									throw new Exception();
	}
	

}
