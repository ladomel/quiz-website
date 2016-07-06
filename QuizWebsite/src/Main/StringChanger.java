package Main;

import java.util.HashMap;
import java.util.Map;

public class StringChanger {
	
	private Map<Character, String> changes;
	
	private static final String NEW_AMPERSAND = "&amp;";
	private static final String NEW_LESS_THAN = "&lt;";
	private static final String NEW_GREATER_THAN = "&gt;";
	
	public StringChanger()
	{
		changes = new HashMap<Character, String>();
		changes.put('&', NEW_AMPERSAND);
		changes.put('<', NEW_LESS_THAN);
		changes.put('>', NEW_GREATER_THAN);
	}
	
	/**
	 * This method takes string as an input returns string
	 * with & < and > changed to &amp; , &lt;, &gt; 
	 * 
	 * @param before - string to change
	 * @return changed string
	 */
	public String changeString(String before)
	{
		if(before == null) return "";
		String after = "";
		char nextChar;
		
		for(int i = 0; i < before.length(); i++)
		{
			nextChar = before.charAt(i);
			if(changes.containsKey(nextChar)) {after+=changes.get(nextChar);}
			else {after+=nextChar;}
		}
		
		return after;
	}
}
