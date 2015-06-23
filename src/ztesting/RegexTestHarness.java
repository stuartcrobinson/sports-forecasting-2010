package ztesting;


import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class RegexTestHarness {

    public static void main(String[] args){
    	
            Pattern pattern = Pattern.compile("user=([-\\d]*)&sportID");	//.*user=-(\\d)&sportID.*");		//console.readLine("%nEnter your regex: "));

            Matcher matcher = pattern.matcher("profile.aspx?user=-63417&sportID=9&type=4");	//console.readLine("Enter input string to search: "));

            boolean found = false;
            while (matcher.find()) {
                System.out.println("I found the text "+matcher.group(1)+" starting at " +
                   "index "+matcher.start()+" and ending at index "+ matcher.end()+".");
                found = true;
            }
            if(!found){
                System.out.println("No match found.");
            }
    }
}

