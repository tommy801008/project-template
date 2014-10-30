package se.chas.projecttemplate.utils;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

public class Utilities {

	static Logger logger = Logger.getLogger(Utilities.class.getName());
	
	public static <T> boolean listIsNullOrEmpty(List<T> list) throws IllegalArgumentException {
		if (list instanceof List) {
			if (list == null || list.isEmpty()) {
				return true;
			} 
		} else {
			throw new IllegalArgumentException();
		}
		return false;
	}
	
	
	
	public static Timestamp getTimestamp() {
		Date now = new Date();
		Timestamp ts = new Timestamp(now.getTime());
		return ts;
	}

	public static int safeLongToInt(long l) {
	    int i = (int)l;
	    if ((long)i != l) {
	        throw new IllegalArgumentException(l + " cannot be cast to int without changing its value.");
	    }
	    return i;
	}
	
	public static Long stringToLong(String value) {
		Long longValue = Long.parseLong(value);
		return longValue;
	}

	public static int stringToInt(String value) {
		int intValue = Integer.parseInt(value);
		return intValue;
	}

	public static boolean stringIsNullOrEmpty(String string) {
		if (string == null || string.isEmpty()) {
			return true;
		}
		return false;
	}

	public static boolean stringIsNullOrEmpty(String... strings) {
		for (String s : strings) {
			if (s == null || s.isEmpty()) {
				return true;
			}
		}
		return false;
	}

	public static String joinStrings(Collection<String> s, String delimiter) {
		if (s.isEmpty())
			return "";
		Iterator<String> iter = s.iterator();
		StringBuffer buffer = new StringBuffer(iter.next());
		while (iter.hasNext()) {
			buffer.append(delimiter);
			buffer.append(iter.next());
		}
		return buffer.toString();
	}
	
	public static boolean checkIsNumber(String toCheck) {
		logger.debug("Utilities :::::" + " Entering checkIsNumber(String toCheck)");
		try {
			Long.parseLong(toCheck);
			return true;
		}catch (NumberFormatException e) {
			logger.debug("Utilities :::::" + " NumberFormatException checkIsNumber(String toCheck) - " + e);
			logger.debug("Utilities :::::" + " Exiting checkIsNumber(String toCheck)");
			return false;
		} catch (Exception e) {
			logger.debug("Utilities :::::" + " Exception checkIsNumber(String toCheck) - " + e);
			logger.debug("Utilities :::::" + " Exiting checkIsNumber(String toCheck)");
			return false;
		}
	}
	
	public static Integer divideBigDecimalBytesAndReturnIntegerConvertedToMb(BigDecimal decimalValue) {
		
		Double byteCountBigDTemp = decimalValue.doubleValue();
		
		if (byteCountBigDTemp == 0) {
			return 0;
		} else {
			Double divideTemp = ((byteCountBigDTemp/1024) / 1024);
	    	Integer byteCount = divideTemp.intValue();
			
			return byteCount;
		}
	}
	
	
	public static Integer divideBigDecimalBytesAndReturnIntegerConvertedToKb(BigDecimal decimalValue) {
		
		Double byteCountBigDTemp = decimalValue.doubleValue();
		
		if (byteCountBigDTemp == 0) {
			return 0;
		} else {
			Double divideTemp = byteCountBigDTemp/1024;
	    	Integer byteCount = divideTemp.intValue();
			
			return byteCount;
		}
	}
	
	public static BigDecimal divideBigDecimalBytesAndReturnBigDecimalConvertedToKb(BigDecimal decimalValue) {
		Double tempValue = decimalValue.doubleValue();
		
		if (tempValue == 0) {
			BigDecimal bigDecimal = new BigDecimal(0);
			
			return bigDecimal;
		} else {
			BigDecimal bigDecimal = decimalValue;
	    	BigDecimal bigDecimalResult = bigDecimal.divide(new BigDecimal(1024));
	    	bigDecimalResult = bigDecimalResult.setScale(0, BigDecimal.ROUND_UP);
	    	
	    	return bigDecimalResult;
		}
	}
	
	public static String calculateDynamicBytes(BigDecimal bytes) {
		
		// 1 gigabyte = 1 073 741 824
		// 1 megabyte = 1 048 576 bytes
		// 1 kilobyte = 1024 bytes
		
		
		if (bytes.doubleValue() > 1073741824) {
			// divide by 1073741824, 2 decimals
			BigDecimal bigDecimal = bytes;
			BigDecimal bigDecimalResult = bigDecimal.divide(new BigDecimal(1073741824));
			bigDecimalResult = bigDecimalResult.setScale(2, BigDecimal.ROUND_UP);
			
			return bigDecimalResult.toString() + " GB";
			
		} else if (bytes.doubleValue() < 1073741824 && bytes.doubleValue() >= 1048576) {
			// divide by 1048576, 2 decimals
			BigDecimal bigDecimal = bytes;
			BigDecimal bigDecimalResult = bigDecimal.divide(new BigDecimal(1048576));
			bigDecimalResult = bigDecimalResult.setScale(2, BigDecimal.ROUND_UP);
			
			return bigDecimalResult.toString() + " MB";
			
		} else if(bytes.doubleValue() < 1048576 && bytes.doubleValue() >= 1024) {
			// divide by 1024, 2 decimals
			BigDecimal bigDecimal = bytes;
			BigDecimal bigDecimalResult = bigDecimal.divide(new BigDecimal(1024));
			bigDecimalResult = bigDecimalResult.setScale(2, BigDecimal.ROUND_UP);
			
			return bigDecimalResult.toString() + " KB";
		} else {
			return bytes.toString() + " B";
		}
	}
	

	/**
	 * @param list of strings from where the string[] should be created 
	 * @param flag - true if containing \
	 * @return String[]
	 */
	public static String selectedOptionAsJavascriptArray(List<String> list, boolean flag) {
		
		if (list != null && !list.isEmpty()) {
			StringBuilder sb = new StringBuilder("[");
			for (String s : list) {
				if (flag) {
					sb.append("'" + insertExtraSlash(s) + "',");
				} else {
					sb.append("'" + s + "',");
				}
			}
			sb.delete(sb.lastIndexOf(","), sb.length());
			sb.append("]");
			
			return sb.toString();
		} else {
			return "[]";
		}
	}
	
	public static String insertExtraSlash(String value) {
		
		if (value.contains("\\")) 
			value = value.replace("\\", "\\\\");
		return value;
	}
	
	public static String getDynamicfileSize(BigDecimal fileSize) {
		
		// 1 gigabyte = 1 073 741 824
		// 1 megabyte = 1 048 576 bytes
		// 1 kilobyte = 1024 bytes
		
		
		if (fileSize != null) {
			if (fileSize.doubleValue() > 1073741824) {
				// divide by 1073741824, 2 decimals
				BigDecimal bigDecimal = fileSize;
				BigDecimal bigDecimalResult = bigDecimal.divide(new BigDecimal(1073741824));
				bigDecimalResult = bigDecimalResult.setScale(2, BigDecimal.ROUND_UP);
				
				return bigDecimalResult.toString() + " GB";
				
			} else if (fileSize.doubleValue() < 1073741824 && fileSize.doubleValue() >= 1048576) {
				// divide by 1048576, 2 decimals
				BigDecimal bigDecimal = fileSize;
				BigDecimal bigDecimalResult = bigDecimal.divide(new BigDecimal(1048576));
				bigDecimalResult = bigDecimalResult.setScale(2, BigDecimal.ROUND_UP);
				
				return bigDecimalResult.toString() + " MB";
				
			} else if(fileSize.doubleValue() < 1048576 && fileSize.doubleValue() >= 1024) {
				// divide by 1024, 2 decimals
				BigDecimal bigDecimal = fileSize;
				BigDecimal bigDecimalResult = bigDecimal.divide(new BigDecimal(1024));
				bigDecimalResult = bigDecimalResult.setScale(2, BigDecimal.ROUND_UP);
				
				return bigDecimalResult.toString() + " KB";
			} else {
				return fileSize.toString() + " B";
			}
		} else {
			return null;
		}
	}
}
