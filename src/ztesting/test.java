package ztesting;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class test {
	
	
	static List<String> lister() {
		
		List<String> list = new ArrayList<String>();
		list.add("hi");
		list.add("hi");
		list.add("hi");
		list.add("hi");
		list.add("hi");
		list.add("hi");
		list.add("hi");
		list.add("hi");
		list.add("hi");
		list.add("hi");
		
		
		
		
		
		return list;
	}
	
//	@SuppressWarnings("deprecation")
	public static void main (String [] args) throws ParseException{
		
		double [] dub = new double[]{1, 2, 3};
		for (double d : dub){
			System.out.println(d);
		}



//        Pattern pattern = Pattern.compile(".*two (\\w\\w)(\\w\\w\\w \\w)(.*)");		//console.readLine("%nEnter your regex: "));
//
//        Matcher matcher = pattern.matcher("one two three four five six seven eight nine ten");	//console.readLine("Enter input string to search: "));
//
//        while (matcher.find()) {
//            System.out.println("I found the text "+matcher.group(3)+" starting at " +
//               "index "+matcher.start()+" and ending at index "+ matcher.end()+".");
//        }
//		Date myDate = new Date("02/29/1984");	//supposed to use DateFormat.parse(String date) -- but this gives weird errors for me
////		System.out.println(d.getYear() + "-" + d.getMonth() + "-" + d.getDay());
////		d.getYear();
//				
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//        String parsed = formatter.format(myDate);
//        System.out.println(" 2. " + parsed.toString());
//
//        
//        Date dd = new Date();
//        Calendar c = Calendar.getInstance();
//        c.setTime(dd);
//        c.set(2000+3, 10, 1);
//        System.out.println(c.getTime().toString());
//        
//        c.set(Calendar.DAY_OF_MONTH, 1);
//        System.out.println(c.getTime().toString());
//        
//        c.add(Calendar.MONTH, 1);
//        System.out.println(c.getTime().toString());
//        
//        c.add(Calendar.MONTH, 1);
//        System.out.println(c.getTime().toString());
//        
//        c.add(Calendar.MONTH, 1);
//        System.out.println(c.getTime().toString());
//        
//        c.add(Calendar.MONTH, 1);
//        System.out.println(c.getTime().toString());
//        
//        c.add(Calendar.MONTH, 1);
//        System.out.println(c.getTime().toString());
//        
//        c.add(Calendar.MONTH, 1);
//        System.out.println(c.getTime().toString());
//        
//        c.add(Calendar.MONTH, 1);
//        System.out.println(c.getTime().toString());
//        
//        c.add(Calendar.MONTH, 1);
//        System.out.println(c.getTime().toString());
//        
//        c.add(Calendar.MONTH, 1);
//        System.out.println(c.getTime().toString());
//        
//        c.add(Calendar.MONTH, 1);
//        System.out.println(c.getTime().toString());
//        
//        c.add(Calendar.MONTH, 1);
//        System.out.println(c.getTime().toString());
//        
//        formatter = new SimpleDateFormat("MM/dd/yyyy");
//        System.out.println(formatter.format(c.getTime()));
//        
//        if ("aa" == "aa") System.out.println("match!");
//        
//        for (String element : lister()){
//        	System.out.println(element);
//        }
//
//        DateFormat df = new SimpleDateFormat ("MM/dd/yyyy");
//
//        Date d3 = df.parse("02/29/1984");
//        System.out.println(d3.toString());
//        
//        Integer i = new Integer(9);
//        i++;
//        System.out.println(i);
//        
//        Calendar cc = Calendar.getInstance();
//        System.out.println(cc.get(Calendar.YEAR));
        
	}

}
