package objects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import schemas.BetsC;


/** bet type is OU or ATS or Moneyline, etc
 */
public class BetType {

	/** OU or ATS */
	public String type;

	public String lineCol;
	public String betCol;
	public String resultCol;

	public List<String> possibleBets;

	public BetType(String type) {
		this.type = type;

		if (type.equals(BetType.OU)){
			lineCol = BetsC.lOU;
			betCol = BetsC.bOU;
			resultCol = BetsC.rOU;
			possibleBets = new ArrayList<String>();
			Collections.addAll(possibleBets, Value.o, Value.u);
		}
		if (type.equals(BetType.ATS)){
			lineCol = BetsC.lATS;
			betCol = BetsC.bATS;
			resultCol = BetsC.rATS;
			possibleBets = new ArrayList<String>();
			Collections.addAll(possibleBets, Value.a, Value.h);
		}		
	}

	public static String OU = "OU";
	public static String ATS = "ATS";
	/**Moneyline*/
	public static String ML = "ML";


	/** list of bet categories.  currently just OU and ATS (might add moneyline later) 
	 * @deprecated not used -- TODO delete*/	@Deprecated
	 public static List<String> categories = 
		 Arrays.asList(new String[]{
				 BetType.OU,
				 BetType.ATS
		 });


	 /**
	  * returns a list of two elements, an OU bettype and an ATS bettype
	  * @param gameID
	  * @return
	  */
	 public static List<BetType> listBetTypes() {

		 List<BetType> types = new ArrayList<BetType>();

		 types.add(new BetType(BetType.OU));
		 types.add(new BetType(BetType.ATS));

		 return types;
	 }
//	 public static void main(String[] args){
//		 Date d = new Date();
////		 System.out.println((new Date()).getTime());
//		 Long t1 = d.getTime();
//
//		 for (int i=0; i< 100000000; i++){
//			 List<String> thelist = new ArrayList<String>();
//			 //		System.out.println(thelist.size());
//			 Collections.addAll(thelist, "one", "two", "three");
//
//			 //		System.out.println(thelist.size());
//			 //		for (String s : thelist){
//			 //			System.out.println(s);
//			 //		}
//		 }
//
//		 System.out.println("Elapsed time: " + ((new Date()).getTime() - t1));
//	 }


}
