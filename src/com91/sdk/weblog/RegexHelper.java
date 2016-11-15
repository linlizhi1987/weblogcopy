package com91.sdk.weblog;

import java.util.ArrayList;

public class RegexHelper {
	public static ArrayList<String> Get(String content,String start,String end){
	    // ÏÂÔØÍøÂçÎÄ¼ş
		java.util.regex.Pattern p =java.util.regex.Pattern.compile("(?<=(" + start + "))[.\\s\\S]*?(?=(" + end + "))",java.util.regex.Pattern.UNICODE_CASE);
		java.util.regex.Matcher m = p.matcher(content);
		ArrayList<String> logs=new ArrayList<String>();
		
		while (m.find()) {
			logs.add(content.substring(m.start(),m.end()));
		}
		return logs;
		
	}
	
}
