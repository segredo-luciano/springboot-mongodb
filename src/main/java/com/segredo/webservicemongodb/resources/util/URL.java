package com.segredo.webservicemongodb.resources.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class URL {

	public static String decodeParam(String text) {
		try {
			return URLDecoder.decode(text, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}
	
	public static LocalDate convertDate(String textDate, LocalDate defaultDate) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		if(!textDate.equals("")) {
			LocalDate date = LocalDate.parse(textDate);
			return LocalDate.parse(date.format(dtf), dtf);
		} else {			
			return LocalDate.parse(defaultDate.format(dtf), dtf);
		}
	}
}
