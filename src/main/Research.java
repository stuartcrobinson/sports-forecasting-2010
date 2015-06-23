package main;

import main.Master.DateSpan;



public class Research {

	/** user's historical units depth -- how many games in the past you're using to calculate a user's cumulative units */
	public int [] depths;
	
	/** for each consensus formula (the base b in b^-i, where i is the user's rank */
	public int [] bases;
	
	/** as ints */
	public int [] pctCutoffs;

	
	/** TODO not written */
	public int [] numUsersToInclude;

	/** TODO not written */
	public DateSpan [] dateSpans;

	public void initialize() {

		Master m = new Master();

		depths 	= new int[]{2,3,4};
		bases 	= new int[]{1, 2};
		pctCutoffs 	= new int[]{80, 65, 50};
		numUsersToInclude = new int[]{1, 2, 5, 10};
		
		DateSpan ds1 = m.new DateSpan(11, 1, 5, 1);
		DateSpan ds2 = m.new DateSpan(1, 1, 4, 1);
		
		dateSpans = new DateSpan[]{ds1, ds2};
		
		
	}

//	public static void main(String [] args){
//		System.out.println(System.getProperty("os.arch"));
//		System.out.println(System.getProperty("os.name"));
//		System.out.println(System.getProperty("os.version"));
//		System.out.println(System.getProperty("user.name"));
//		System.out.println(System.getProperty("user.home"));
//		System.out.println(System.getProperty("user.dir"));
//	}
}
