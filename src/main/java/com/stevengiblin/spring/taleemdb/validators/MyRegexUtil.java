package com.stevengiblin.spring.taleemdb.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyRegexUtil {

	private static String pattern = "[\\p{Alnum}\\p{Punct}\\p{Blank}\n\r]*";
	private static String regex_pattern = pattern;
	private static Pattern p;
	
	static {
		p = Pattern.compile(regex_pattern, Pattern.MULTILINE);
	}
	
	// should be in a unit test
	public static void main(String[] args) {
		printTest("abc s b c x y z ABC A B C X Y Z 0123 0 1 2 3 4 5 6 789");
		printTest(":");
		printTest("::");
		printTest("sdag78@gmail.com");
		printTest("hello!");
		printTest("+-*=");
		printTest("-");
		printTest("--");
		printTest("Dha,(s)Dhin,(s),Dha, Dha,(s)Dhin,(s),Dha");
		printTest(" ");
		printTest("[1,2,3,4],[5,6,7,8]");
		printTest("Palta 1: (comment=blah)");
		printTest("Some kind of description, which might nieed to include things like (Ghe=slide, Ti=index finger, Te=middle fingers)");
		printTest("<script>");
		printTest("<b>");
		printTest("<html>");
		printTest("--");
		printTest("-");
		printTest("_");
		printTest(":");
		printTest(";");
		printTest("	"); //tab
		printTest("!");
		printTest("(");
		printTest("]");
		printTest("£");
		printTest("#");
		printTest("$");
		
		printTest("==");
		printTest("=");
		printTest("'");
		printTest("\"");
		printTest("Dha");
		
	}

	private static void printTest(String input) {
		System.out.println("");
		System.out.println("testing: "+ input);
		System.out.println("result: " + isValidCharacterInput(input));
	}

	public static boolean isValidCharacterInput(String input) {
		Matcher m = p.matcher(input);
		return m.matches();
	}

}
