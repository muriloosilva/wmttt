package br.com.wmtt.server.util;

import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class DataUtil {
	
	public static Date formatDateToSave(String d){
		
		SimpleDateFormat sdf=null;  
		Date da = null;
		try {
			
			TimeZone tz = TimeZone.getTimeZone("America/Sao_Paulo");  
	        TimeZone.setDefault(tz); 
	        
	        sdf =  new SimpleDateFormat("yyyy-MM-dd"); 
	        
	        da = new Date(sdf.parse(d).getTime());
			 
			//data = new Date(((java.util.Date) fmt.parse(hora)).getTime());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return da;
		
	}

	public static Time formatTimeToSave(String ti){
		SimpleDateFormat sdf=null;  
		Calendar ca = null;
		try {
			
			TimeZone tz = TimeZone.getTimeZone("America/Sao_Paulo");  
	        TimeZone.setDefault(tz);  
	        ca = GregorianCalendar.getInstance(tz);
	        
	        sdf =  new SimpleDateFormat("HH:mm:ss"); 
	        
	        ca.setTime(sdf.parse(ti.toString()));
			 
			//data = new Date(((java.util.Date) fmt.parse(hora)).getTime());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Time t = new Time(ca.getTime().getTime());
		return t;
	}
	
	public static Time formatTimeShow(Time ti){
		SimpleDateFormat sdf=null;  
		Calendar ca = null;
		try {
			
			TimeZone tz = TimeZone.getTimeZone("America/Sao_Paulo");  
	        TimeZone.setDefault(tz);  
	        ca = GregorianCalendar.getInstance(tz);
	        
	        sdf =  new SimpleDateFormat("HH:mm"); 
	        
	        ca.setTime(sdf.parse(ti.toString()));
			 
			//data = new Date(((java.util.Date) fmt.parse(hora)).getTime());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Time t = new Time(ca.getTime().getTime());
		return t;
	}

}
