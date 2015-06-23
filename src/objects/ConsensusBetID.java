package objects;

import main.Master.DateSpan;

public class ConsensusBetID {

	public String sID;
	
	public DateSpan days;
	public BetType betType;
	public TeamType teamType;
	public int depth;
	public int base;
	public int pctCutoff;
	
	
	public ConsensusBetID(){
		sID = "";
	}

	public ConsensusBetID(DateSpan days, BetType betType, TeamType teamType, int depth, int base, int n, int pctCutoff) {

		this.betType = betType;
		this.teamType = teamType;
		this.depth = depth;
		this.base = base;
		this.pctCutoff = pctCutoff;
		
		this.sID = days +"_"+ betType.type +"_"+ teamType.type +"_"+ depth +"_"+ base +"_"+ n +"_"+ pctCutoff;
		
	}
	
}
