package ztesting;



/** deprecated.  use UserTableColumnNames and GameTableCOlumnNames instead
 * columns, by table: <br><br>
 * 
 * <b>user:</b><br>
 * gID, aS, hS, lOU, lATS, bOU, bATS, uOUa, uOUb, uOUc, uOUd, uATSa, uATSb, uATSc, uATSd <br><br>
 * 
 * <b>game: </b><br>
 * gID, d, a, h, aS, hS, lOU, lATS, rOU, rATS, bcOUa1, bcOUa2, bcOUa3, bcOUa4, bcOUb1, bcOUb2, bcOUb3, bcOUb4, bcOUc1, bcOUc2, bcOUc3, bcOUc4, bcOUd1, bcOUd2, bcOUd3, bcOUd4, rbcOUa1, rbcOUa2, rbcOUa3, rbcOUa4, rbcOUb1, rbcOUb2, rbcOUb3, rbcOUb4, rbcOUc1, rbcOUc2, rbcOUc3, rbcOUc4, rbcOUd1, rbcOUd2, rbcOUd3, rbcOUd4, bcATSa1, bcATSa2, bcATSa3, bcATSa4, bcATSb1, bcATSb2, bcATSb3, bcATSb4, bcATSc1, bcATSc2, bcATSc3, bcATSc4, bcATSd1, bcATSd2, bcATSd3, bcATSd4, rbcATSa1, rbcATSa2, rbcATSa3, rbcATSa4, rbcATSb1, rbcATSb2, rbcATSb3, rbcATSb4, rbcATSc1, rbcATSc2, rbcATSc3, rbcATSc4, rbcATSd1, rbcATSd2, rbcATSd3, rbcATSd4
 */
public class ColumnNames {

	/////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////  user table columns  (there is overlap between users and games col names) //////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/** a user's OU bet (o, u, n -- n for 'no bet')*/
	public static String bOU	= "bOU";
	/** a user's ATS bet (a, h, n -- n for 'no bet') */
	public static String bATS	= "bATS";
	
	/** a user's cumulative units for OU bets, type a */
	public static String uOUa	= "uOUa";
	/** a user's cumulative units for OU bets, type b */
	public static String uOUb	= "uOUb";
	/** a user's cumulative units for OU bets, type c */
	public static String uOUc	= "uOUc";
	/** a user's cumulative units for OU bets, type d */
	public static String uOUd	= "uOUd";
	


	/////////////////////////////////////////////////////////////////////////////////////////////////////
	////////////  game table columns  (there is overlap between users and games col names) //////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/** game ID, aaayyyymmddhhh, e.g. atl20040316den */
	public static String gID	= "gID";
	/** date */
	public static String d		= "d";
	/** away team abbreviated */
	public static String a		= "a";
	/** home team abbreviated */
	public static String h		= "h";
	/** away score */
	public static String aS		= "aS";
	/** home score */
	public static String hS		= "hS";
	/** home score + away score */
	public static String tot	= "tot";
	/** home score - away score */
	public static String spread	= "spread";
	/** OU line */
	public static String lOU	= "lOU";
	/** ATS line */
	public static String lATS	= "lATS";
	/** OU result (o, u, p -- p for push) */
	public static String rOU	= "rOU";
	/** ATS result (a, h, p -- p for push) */
	public static String rATS	= "rATS";


	/** the type 1 consensus OU bet among users ranked by type a */	
	public static String bcOUa1	= "bcOUa1";
	/** the type 2 consensus OU bet among users ranked by type a */
	public static String bcOUa2	= "bcOUa2";
	/** the type 3 consensus OU bet among users ranked by type a */
	public static String bcOUa3	= "bcOUa3";
	/** the type 4 consensus OU bet among users ranked by type a */
	public static String bcOUa4	= "bcOUa4";

	/** the type 1 consensus OU bet among users ranked by type b */
	public static String bcOUb1	= "bcOUb1";
	/** the type 2 consensus OU bet among users ranked by type b */
	public static String bcOUb2	= "bcOUb2";
	/** the type 3 consensus OU bet among users ranked by type b */
	public static String bcOUb3	= "bcOUb3";
	/** the type 4 consensus OU bet among users ranked by type b */
	public static String bcOUb4	= "bcOUb4";

	/** the type 1 consensus OU bet among users ranked by type c */
	public static String bcOUc1	= "bcOUc1";
	/** the type 2 consensus OU bet among users ranked by type c */
	public static String bcOUc2	= "bcOUc2";
	/** the type 3 consensus OU bet among users ranked by type c */
	public static String bcOUc3	= "bcOUc3";
	/** the type 4 consensus OU bet among users ranked by type c */
	public static String bcOUc4	= "bcOUc4";

	/** the type 1 consensus OU bet among users ranked by type d */
	public static String bcOUd1	= "bcOUd1";
	/** the type 2 consensus OU bet among users ranked by type d */
	public static String bcOUd2	= "bcOUd2";
	/** the type 3 consensus OU bet among users ranked by type d */
	public static String bcOUd3	= "bcOUd3";
	/** the type 4 consensus OU bet among users ranked by type d */
	public static String bcOUd4	= "bcOUd4";

	
	/** result of the type 1 consensus OU bet among users ranked by type a  (w, l, p -- win, lose, push) */	
	public static String rbcOUa1	= "rbcOUa1";
	/** result of the type 2 consensus OU bet among users ranked by type a  (w, l, p -- win, lose, push) */
	public static String rbcOUa2	= "rbcOUa2";
	/** result of the type 3 consensus OU bet among users ranked by type a  (w, l, p -- win, lose, push) */
	public static String rbcOUa3	= "rbcOUa3";
	/** result of the type 4 consensus OU bet among users ranked by type a  (w, l, p -- win, lose, push) */
	public static String rbcOUa4	= "rbcOUa4";

	/** result of the type 1 consensus OU bet among users ranked by type b  (w, l, p -- win, lose, push) */
	public static String rbcOUb1	= "rbcOUb1";
	/** result of the type 2 consensus OU bet among users ranked by type b  (w, l, p -- win, lose, push) */
	public static String rbcOUb2	= "rbcOUb2";
	/** result of the type 3 consensus OU bet among users ranked by type b  (w, l, p -- win, lose, push) */
	public static String rbcOUb3	= "rbcOUb3";
	/** result of the type 4 consensus OU bet among users ranked by type b  (w, l, p -- win, lose, push) */
	public static String rbcOUb4	= "rbcOUb4";

	/** result of the type 1 consensus OU bet among users ranked by type c  (w, l, p -- win, lose, push) */
	public static String rbcOUc1	= "rbcOUc1";
	/** result of the type 2 consensus OU bet among users ranked by type c  (w, l, p -- win, lose, push) */
	public static String rbcOUc2	= "rbcOUc2";
	/** result of the type 3 consensus OU bet among users ranked by type c  (w, l, p -- win, lose, push) */
	public static String rbcOUc3	= "rbcOUc3";
	/** result of the type 4 consensus OU bet among users ranked by type c  (w, l, p -- win, lose, push) */
	public static String rbcOUc4	= "rbcOUc4";

	/** result of the type 1 consensus OU bet among users ranked by type d  (w, l, p -- win, lose, push) */
	public static String rbcOUd1	= "rbcOUd1";
	/** result of the type 2 consensus OU bet among users ranked by type d  (w, l, p -- win, lose, push) */
	public static String rbcOUd2	= "rbcOUd2";
	/** result of the type 3 consensus OU bet among users ranked by type d  (w, l, p -- win, lose, push) */
	public static String rbcOUd3	= "rbcOUd3";
	/** result of the type 4 consensus OU bet among users ranked by type d  (w, l, p -- win, lose, push) */
	public static String rbcOUd4	= "rbcOUd4";
	
	///////

	/** the type 1 consensus ATS bet among users ranked by type a */	
	public static String bcATSa1	= "bcATSa1";
	/** the type 2 consensus ATS bet among users ranked by type a */
	public static String bcATSa2	= "bcATSa2";
	/** the type 3 consensus ATS bet among users ranked by type a */
	public static String bcATSa3	= "bcATSa3";
	/** the type 4 consensus ATS bet among users ranked by type a */
	public static String bcATSa4	= "bcATSa4";

	/** the type 1 consensus ATS bet among users ranked by type b */
	public static String bcATSb1	= "bcATSb1";
	/** the type 2 consensus ATS bet among users ranked by type b */
	public static String bcATSb2	= "bcATSb2";
	/** the type 3 consensus ATS bet among users ranked by type b */
	public static String bcATSb3	= "bcATSb3";
	/** the type 4 consensus ATS bet among users ranked by type b */
	public static String bcATSb4	= "bcATSb4";

	/** the type 1 consensus ATS bet among users ranked by type c */
	public static String bcATSc1	= "bcATSc1";
	/** the type 2 consensus ATS bet among users ranked by type c */
	public static String bcATSc2	= "bcATSc2";
	/** the type 3 consensus ATS bet among users ranked by type c */
	public static String bcATSc3	= "bcATSc3";
	/** the type 4 consensus ATS bet among users ranked by type c */
	public static String bcATSc4	= "bcATSc4";

	/** the type 1 consensus ATS bet among users ranked by type d */
	public static String bcATSd1	= "bcATSd1";
	/** the type 2 consensus ATS bet among users ranked by type d */
	public static String bcATSd2	= "bcATSd2";
	/** the type 3 consensus ATS bet among users ranked by type d */
	public static String bcATSd3	= "bcATSd3";
	/** the type 4 consensus ATS bet among users ranked by type d */
	public static String bcATSd4	= "bcATSd4";

	
	/** result of the type 1 consensus ATS bet among users ranked by type a  (w, l, p -- win, lose, push) */	
	public static String rbcATSa1	= "rbcATSa1";
	/** result of the type 2 consensus ATS bet among users ranked by type a  (w, l, p -- win, lose, push) */
	public static String rbcATSa2	= "rbcATSa2";
	/** result of the type 3 consensus ATS bet among users ranked by type a  (w, l, p -- win, lose, push) */
	public static String rbcATSa3	= "rbcATSa3";
	/** result of the type 4 consensus ATS bet among users ranked by type a  (w, l, p -- win, lose, push) */
	public static String rbcATSa4	= "rbcATSa4";

	/** result of the type 1 consensus ATS bet among users ranked by type b  (w, l, p -- win, lose, push) */
	public static String rbcATSb1	= "rbcATSb1";
	/** result of the type 2 consensus ATS bet among users ranked by type b  (w, l, p -- win, lose, push) */
	public static String rbcATSb2	= "rbcATSb2";
	/** result of the type 3 consensus ATS bet among users ranked by type b  (w, l, p -- win, lose, push) */
	public static String rbcATSb3	= "rbcATSb3";
	/** result of the type 4 consensus ATS bet among users ranked by type b  (w, l, p -- win, lose, push) */
	public static String rbcATSb4	= "rbcATSb4";

	/** result of the type 1 consensus ATS bet among users ranked by type c  (w, l, p -- win, lose, push) */
	public static String rbcATSc1	= "rbcATSc1";
	/** result of the type 2 consensus ATS bet among users ranked by type c  (w, l, p -- win, lose, push) */
	public static String rbcATSc2	= "rbcATSc2";
	/** result of the type 3 consensus ATS bet among users ranked by type c  (w, l, p -- win, lose, push) */
	public static String rbcATSc3	= "rbcATSc3";
	/** result of the type 4 consensus ATS bet among users ranked by type c  (w, l, p -- win, lose, push) */
	public static String rbcATSc4	= "rbcATSc4";

	/** result of the type 1 consensus ATS bet among users ranked by type d  (w, l, p -- win, lose, push) */
	public static String rbcATSd1	= "rbcATSd1";
	/** result of the type 2 consensus ATS bet among users ranked by type d  (w, l, p -- win, lose, push) */
	public static String rbcATSd2	= "rbcATSd2";
	/** result of the type 3 consensus ATS bet among users ranked by type d  (w, l, p -- win, lose, push) */
	public static String rbcATSd3	= "rbcATSd3";
	/** result of the type 4 consensus ATS bet among users ranked by type d  (w, l, p -- win, lose, push) */
	public static String rbcATSd4	= "rbcATSd4";
	
	
	
}
