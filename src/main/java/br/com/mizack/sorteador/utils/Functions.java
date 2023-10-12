package br.com.mizack.sorteador.utils;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Functions {
	public boolean isValidEmail(String email) {
	    String regex = "^(.+)@(.+)$";
	    Pattern pattern = Pattern.compile(regex);
	    Matcher matcher = pattern.matcher(email);
	    return matcher.matches();
	}
}
