package com.runtech.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class ParameterCheckHelper {
	

	private final static String[] sensitive = new String[]{"rpc"};
	
public static boolean isDangerousValue(String value) {
        boolean isDangerousValue = false;
        if (value != null) {
            Matcher matcher = null;
            value = value.replaceAll("\0", "");

            // Avoid anything between script tags
            Pattern scriptPattern = Pattern.compile("<script>(.*?)</script>", Pattern.CASE_INSENSITIVE);
            matcher = scriptPattern.matcher(value);
            isDangerousValue = matcher.find();

            if (isDangerousValue) {
                return true;
            }


            // Avoid anything in a src='...' type of expression
            scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\'(.*?)\\\'", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            matcher = scriptPattern.matcher(value);
            isDangerousValue = matcher.find();

            if (isDangerousValue) {
                return true;
            }

            scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\"(.*?)\\\"", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            matcher = scriptPattern.matcher(value);
            isDangerousValue = matcher.find();

            if (isDangerousValue) {
                return true;
            }

            // Remove any lonesome </script> tag
            scriptPattern = Pattern.compile("</script>", Pattern.CASE_INSENSITIVE);
            matcher = scriptPattern.matcher(value);
            isDangerousValue = matcher.find();
            if (isDangerousValue) {
                return true;
            }

            // Remove any lonesome <script ...> tag
            scriptPattern = Pattern.compile("<script(.*?)>", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            matcher = scriptPattern.matcher(value);
            isDangerousValue = matcher.find();
            if (isDangerousValue) {
                return true;
            }

            // Remove any lonesome </iframe> tag
            scriptPattern = Pattern.compile("</iframe>", Pattern.CASE_INSENSITIVE);
            matcher = scriptPattern.matcher(value);
            isDangerousValue = matcher.find();
            if (isDangerousValue) {
                return true;
            }

            // Remove any lonesome <iframe ...> tag
            scriptPattern = Pattern.compile("<iframe(.*?)>", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            matcher = scriptPattern.matcher(value);
            isDangerousValue = matcher.find();
            if (isDangerousValue) {
                return true;
            }

            // Remove any lonesome </a> tag
            scriptPattern = Pattern.compile("</a>", Pattern.CASE_INSENSITIVE);
            matcher = scriptPattern.matcher(value);
            isDangerousValue = matcher.find();
            if (isDangerousValue) {
                return true;
            }

            // Remove any lonesome <a ...> tag
            scriptPattern = Pattern.compile("<a(.*?)>", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            matcher = scriptPattern.matcher(value);
            isDangerousValue = matcher.find();
            if (isDangerousValue) {
                return true;
            }

            // Avoid eval_r(...) expressions
            scriptPattern = Pattern.compile("eval\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            matcher = scriptPattern.matcher(value);
            isDangerousValue = matcher.find();
            if (isDangerousValue) {
                return true;
            }

            // Avoid expression_r(...) expressions
            scriptPattern = Pattern.compile("expression\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            matcher = scriptPattern.matcher(value);
            isDangerousValue = matcher.find();
            if (isDangerousValue) {
                return true;
            }

            // Avoid javascript:... expressions
            scriptPattern = Pattern.compile("javascript:", Pattern.CASE_INSENSITIVE);
            matcher = scriptPattern.matcher(value);
            isDangerousValue = matcher.find();
            if (isDangerousValue) {
                return true;
            }

            // Avoid vbscript:... expressions
            scriptPattern = Pattern.compile("vbscript:", Pattern.CASE_INSENSITIVE);
            matcher = scriptPattern.matcher(value);
            isDangerousValue = matcher.find();
            if (isDangerousValue) {
                return true;
            }

            // Avoid onload= expressions
            scriptPattern = Pattern.compile("onload(.*?)=", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
            matcher = scriptPattern.matcher(value);
            isDangerousValue = matcher.find();
            if (isDangerousValue) {
                return true;
            }

            // Avoid document:... expressions
            scriptPattern = Pattern.compile("document", Pattern.CASE_INSENSITIVE);
            matcher = scriptPattern.matcher(value);
            isDangerousValue = matcher.find();
            if (isDangerousValue) {
                return true;
            }

            // Avoid url= [<>,;'"]
//            scriptPattern = Pattern.compile("[<>(),;'\"]");
//            matcher = scriptPattern.matcher(value);
//            isDangerousValue = matcher.find();
//            if (isDangerousValue) {
//                return true;
//            }
        }
        return isDangerousValue;
    }

	public static boolean isDangerousURL(String requestURL){
        // Avoid url= [<>,;'"]
		if(requestURL!=null&&requestURL.length()>0){
	      Pattern scriptPattern = Pattern.compile("[<>(),'\"]");
	      Matcher matcher = scriptPattern.matcher(requestURL);
	      if (matcher.find()) {
	          return true;
	      }
		}
      
      for (int i = 0; i < sensitive.length; i++) {
			if(requestURL!=null&&requestURL.length()>3){
				String substring = requestURL.substring(requestURL.length()-sensitive[i].length());
				if(substring.equalsIgnoreCase(sensitive[i])){
					return true;
				}
			}
		}
		return false;
	}
}
