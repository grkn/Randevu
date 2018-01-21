package com.hizliyol.core.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public interface Constants {

	String mmYY = "MM-yyyy";
	String ddMMYYYYHHmm = "dd-MM-yyyy HH:mm";
	
	default Date getFormattedDate(String date,String format) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.parse(date);
    }
	
}
