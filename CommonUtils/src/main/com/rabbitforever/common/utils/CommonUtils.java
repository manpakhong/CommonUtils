package com.rabbitforever.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.InetAddress;
import java.sql.Blob;
import java.sql.Clob;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommonUtils {
	private final Logger logger = LoggerFactory.getLogger(getClassName());
	private static final Integer EXTREME_LONG_STRING_LENGTH = 3000;
	

//	private UtilsFactory utilsFactory;
//	private CalendarUtils calendarUtils;
	private static CommonUtils commonUtils;
	private CommonUtils() {
//		utilsFactory = UtilsFactory.getInstance();
//		calendarUtils = utilsFactory.getInstanceOfCalendarUtils();
	}
	public static CommonUtils getInstance() {
		if (commonUtils == null) {
			commonUtils = new CommonUtils();
		}
		return commonUtils;
	}
	private String getClassName() {
		String className = this.getClass().getName();
		return className;
	}
	public void trimStringArrayElement(String [] strArray) throws Exception{
		try{
			if (strArray != null && strArray.length > 0){
				for (int i=0; i < strArray.length; i++){
					if (strArray[i] != null){
						strArray[i] = strArray[i].trim();
					}
				}
			}
		} catch (Exception e){
			logger.error(getClassName() + ".trimStringArrayElement() - strArray=" + strArray, e);
			throw e;
		}
	}
	
//	public <T> List<T> getFilteredList(List<T> list, Class<T> clazz, String filterStr) {
//		List<T> returnList = new ArrayList<>();
//		if (list != null && list.size() > 0) {
//			if (clazz == ArcSurchincrementalEo.class) {				
//				if (filterStr != null && !filterStr.isEmpty()) {
//					returnList = list.stream().filter(eo -> filterStr.equals(((ArcSurchincrementalEo) eo).getRoomTypeCode())).collect(Collectors.toList());
//				}
//				if (returnList == null || returnList.size() == 0) {
//					returnList = list.stream().filter(eo -> (((ArcSurchincrementalEo) eo).getRoomTypeCode() == null)).collect(Collectors.toList());
//				}
//			}
//		}
//		return returnList;
//	}
		
	public Double getNumber(Double number) {
		return (number == null) ? 0.0 : number; 
	}
	
	public Integer getNumber(Integer number) {
		return (number == null) ? 0 : number; 
	}
	
	public boolean isNoDuplicationWithinArray(String [] strArray) throws Exception{
		boolean noDuplication = true;
		try{
			Map<String,String> noDuplicationMap = new HashMap<String, String>();
			for (String key: strArray){
				String value = noDuplicationMap.get(key);
				if (value != null){
					noDuplication = false;
					break;
				}
				noDuplicationMap.put(key, key);
			}
			
		} catch (Exception e){
			logger.error(getClassName() + ".isNoDuplicationWithinArray() - strArray=" + strArray, e);
			throw e;
		}
		return noDuplication;
	}
	
	public List<String> splitByDelimited(String str, String delimited) throws Exception{
		List<String> stringList = null;
		try {
			String[] stringArray = str.split(delimited);
			stringList = Arrays.asList(stringArray);
		} catch (Exception e) {
			logger.error(getClassName() + ".splitByDelimited() - str=" + str + ",delimited=" + delimited, e);
			throw e;
		}
		return stringList;
	}

	public String repeatString(String str, int repeat) throws Exception{
		String returnStr = null;
		try {
			char[] chars = new char[repeat];
			Arrays.fill(chars, str.charAt(0));
			returnStr = new String(chars);
		} catch (Exception e) {
			logger.error(getClassName() + ".repeatString() - str=" + str + ",repeat=" + repeat, e);
			throw e;
		}
		return returnStr;
	}



	public String replaceLineWithSpace(String sql) throws Exception {
		String result = null;
		try {
			result = sql.replace("\n", " ");
		} catch (Exception e) {
			logger.error(getClassName() + ".replaceLineWithSpace() - sql=" + sql, e);
			throw e;
		}

		return result;
	}

	public String removeGarbageFromSql(String sql) throws Exception{
		String result = null;
		try {
			result = sql.replace(";", "");

		} catch (Exception e) {
			logger.error(getClassName() + ".removeGarbageFromSql() - sql=" + sql, e);
			throw e;
		}

		return result;
	}

	public boolean isFileExisted(String filePath) throws Exception{
		boolean isFileExisted = false;
		try{
			File f = new File(filePath);
			if(f.exists() && !f.isDirectory()) {
				isFileExisted = true;
			}
		} catch (Exception e){
			logger.error(getClassName() + ".isFileExisted() - filePath=" + filePath, e);
		}
		return isFileExisted;
	}
	public boolean deleteFileIfExisted(String filePath) throws Exception{
		boolean isDeleted = false;
		try{
			File f = new File(filePath);
			if (f.exists() && !f.isDirectory()){
				if (f.delete()){
					isDeleted = true;
				}
			}
		} catch (Exception e){
			logger.error(getClassName() + ".deleteFileIfExisted() - filePath=" + filePath, e);
		}
		return isDeleted;
	}
	public byte [] readFile2ByteArray(String filePath) throws Exception{
		byte [] byteArray = null;
		File file = null;
		InputStream is = null;
		try{
			if (isFileExisted(filePath)){
				file = new File(filePath);
		        is = new FileInputStream(file);
		        
		        // Get the size of the file
		        long length = file.length();
		    
		        if (length > Integer.MAX_VALUE) {
		            logger.warn("File: " + filePath + " - length is too large, larger than integer max value!");
		        }
		    
		        // Create the byte array to hold the data
		        byte[] bytes = new byte[(int)length];
		    
		        // Read in the bytes
		        int offset = 0;
		        int numRead = 0;
		        while (offset < bytes.length
		               && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
		            offset += numRead;
		        }
		    
		        // Ensure all the bytes have been read in
		        if (offset < bytes.length) {
		            throw new IOException("Could not completely read file "+ file.getName());
		        }
		    
		        // Close the input stream and return bytes
		        byteArray = bytes;
		        
			} else {
				logger.warn(getClassName() + ".readFile2ByteArray() - filePath=" + filePath +" is not existed");
			}
		} catch (Exception e){
			logger.error(getClassName() + ".readFile2ByteArray() - filePath=" + filePath, e);
			throw e;
		} finally{
			try{
				if (is != null){
					is.close();
					is = null;
				}
			} catch (Exception e){
				logger.error(getClassName() + ".readFile2ByteArray() - cannot be closed", e);
			}
		}
		return byteArray;
	}
	public Integer tryParseInteger(String str) throws Exception{
		Integer rtnInteger = null;
		try {
			rtnInteger = Integer.parseInt(str);
		} catch (NumberFormatException e) {
			return null;
		}
		return rtnInteger;
	}
	public Boolean tryParseBoolean(String str)  throws Exception{
		Boolean rtnBoolean = null;
		try{
			rtnBoolean = Boolean.parseBoolean(str);
		} catch (Exception e){
			return null;
		}
		return rtnBoolean;
	}
	public Double tryParseDouble(String str)  throws Exception{
		Double rtnDouble = null;
		try{
			rtnDouble = Double.parseDouble(str);
		} catch (NumberFormatException e){
			return null;
		}
		return rtnDouble;
	}
	public Long tryParseLong(String str)  throws Exception{
		Long rtnLong = null;
		try{
			rtnLong = Long.parseLong(str);
		} catch (NumberFormatException e){
			return null;
		}
		return rtnLong;
	}
	public boolean isNumeric(String str){
		try{
			Double.parseDouble(str);
		} catch (NumberFormatException e){
			return false;
		}
		return true;
	}
	public BigDecimal number2BigDecimal(Number number)  throws Exception{
		BigDecimal rtnBigDecimal = null;
		try {
		if (number != null){
			rtnBigDecimal = new BigDecimal(number.intValue());
		}
		} catch (Exception e) {
			logger.error(getClassName() + ".number2BigDecimal() - number=" + number, e);
			throw e;
		}
		return rtnBigDecimal;
	}
	public Integer number2Integer(Number number) throws Exception{
		Integer rtnInteger = null;
		try {
		if (number != null){
			rtnInteger = number.intValue();
		}
		} catch (Exception e) {
			logger.error(getClassName() + ".number2Integer() - number=" + number, e);
			throw e;
		}
		return rtnInteger;
	}
	
	public Long number2Long(Number number) throws Exception{
		Long rtnLong = null;
		try {
		if (number != null){
			rtnLong = number.longValue();
		}
	} catch (Exception e) {
		logger.error(getClassName() + ".number2Long() - number=" + number, e);
		throw e;
	}
		return rtnLong;
	}
	public String genTimestampString() throws Exception{
		Date now = new Date();
		String rtnStr = null;
		SimpleDateFormat format = null;
		try{
			format = new SimpleDateFormat("yyyyddMMHHmmss");
			rtnStr = format.format(now);
		} catch (Exception e){
			logger.error(getClassName() + ".genTimestampString() - format=" + format, e);
			throw e;
		}
		return rtnStr;
	}
	public String genTimestampString(String str) throws Exception{
		Date now = new Date();
		String rtnStr = null;
		try{
			SimpleDateFormat format = new SimpleDateFormat("yyyyddMMHHmmss");
			rtnStr = format.format(now);
			rtnStr = str + "_" + rtnStr;
		} catch (Exception e){
			logger.error(getClassName() + ".genOrgTimestampString() - str=" + str, e);
			throw e;
		}
		return rtnStr;
	}
	public String convertDate2MySqlDateString(Date date) throws Exception{
		String mySqlDateString = null;
		SimpleDateFormat sdf = null;
		try{
			sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			mySqlDateString = sdf.format(date);
		} catch (Exception e){
			logger.error(getClassName() + ".convertDate2MySqlDateString() - date=" + date, e);
			throw e;
		}
		return mySqlDateString;
	}
	
//	public String convertCalendar2MySqlDateString(Calendar cal) throws Exception{
//		String mySqlDateString = "";
//		SimpleDateFormat sdf = null;
//		try{
//			sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			sdf.setTimeZone(calendarUtils.getUtcTimeZone());
//			mySqlDateString = sdf.format(cal.getTime());
//			
//		} catch (Exception e){
//			logger.error(getClassName() + ".convertDate2MySqlDateString() - cal=" + cal, e);
//			throw e;
//		}
//		return mySqlDateString;
//	}
	
	public String convertUnixTime2DateString(Integer unixtime) throws Exception{
		Date unixTimeDate = null;
		SimpleDateFormat sdf = null;
		String dateString = null;
		try {
		unixTimeDate = convertUnixTime2Date(unixtime);
		sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
		dateString = sdf.format(unixTimeDate);
		} catch (Exception e) {
			logger.error(getClassName() + ".convertUnixTime2DateString() - unixtime=" + unixtime, e);
			throw e;
		}
		return dateString;
	}
	
	public Date convertUnixTime2Date(Integer unixtime) throws Exception{
		Date unixTimeDate = null;
		try {
		unixTimeDate = new Date((long) unixtime * 1000);
		} catch (Exception e) {
			logger.error(getClassName() + ".convertUnixTime2Date() - unixtime=" + unixtime, e);
			throw e;
		}
		return unixTimeDate;
	}
	
	public int convertDate2UnixTime(Date date) throws Exception{
		int unixtime = -1;
		try {
		unixtime = (int) (date.getTime() / 1000);
		} catch (Exception e) {
			logger.error(getClassName() + ".convertDate2UnixTime() - date=" + date, e);
			throw e;
		}
		return unixtime;
	}
	
	public List<String> regMatch(String sourceString, String patternString) throws Exception{
		List<String> matchStrList = null;
		Pattern pattern = null;
		Matcher matcher = null;
		try {
		matchStrList = new ArrayList<String>();
		pattern = Pattern.compile(patternString, Pattern.CASE_INSENSITIVE);
		matcher = pattern.matcher(sourceString);
		while(matcher.find()){
			matchStrList.add(matcher.group());
		}
		} catch (Exception e) {
			logger.error(getClassName() + ".regMatch() - sourceString=" + sourceString + ",patternString=" + patternString, e);
			throw e;
		}
		return matchStrList;
	}
	
	public boolean isInteger(String str){
		try{
			Integer.parseInt(str);
		} catch (Exception e){
			if (e instanceof NumberFormatException){
				return false;
			} else {
				return false;
			}
		}
		return true;
	}
	
	public int safeLongToInt(long l) throws Exception{
		try{
		    if (l < Integer.MIN_VALUE || l > Integer.MAX_VALUE) {
		        throw new IllegalArgumentException
		            (l + " cannot be cast to int without changing its value.");
		    }
		} catch (Exception e){
			if (logger.isDebugEnabled()){
				logger.debug(getClassName() + ".safeLongToInt() - cannot be cast to int without changing its value.");
			}
			logger.error(getClassName() + ".safeLongToInt() - l=" + l, e);
			throw e;
		}
	    return (int) l;
	}
	public float safeDoubleToFloat(double d) throws Exception{
		try{
		    if (d < Float.MIN_VALUE || d > Float.MAX_VALUE) {
		        throw new IllegalArgumentException
		            (d + " cannot be cast to float without changing its value.");
		    }
		} catch (Exception e){
			if (logger.isDebugEnabled()){
				logger.debug(getClassName() + ".safeDoubleToFloat() - cannot be cast to float without changing its value.");
			}
			logger.error(getClassName() + ".safeDoubleToFloat() - d=" + d, e);
			throw e;
		}
	    return (float) d;
	}
	public char[] convertClobToCharArray(Clob clob) throws Exception{
//		char returnVal[] = null;
		Reader r = null;
		try{
			if (clob != null) {
				char clobVal[] = new char[(int) clob.length()];
			    r = clob.getCharacterStream();
			    r.read(clobVal);
			    return clobVal;
			}
		} catch (Exception e){
			logger.error(getClassName() + ".convertClobToCharArray() - clob =" + clob, e);
			throw e;
		} finally {
			if (r != null) {
				r.close();
				r = null;
			}
		}
	    return null;
	}
	public byte[] convertBlogToByteArray(Blob blob) throws Exception{
		InputStream blobIs = null;
		try{
			if (blob != null) {
				byte blobVal[] = new byte[(int) blob.length()];
			    blobIs = blob.getBinaryStream();
			    blobIs.read(blobVal);
			    return blobVal;
			}
		} catch (Exception e){
			logger.error(getClassName() + ".convertClobToCharArray() - blob =" + blob, e);
			throw e;
		} finally {
			if (blobIs != null) {
				blobIs.close();
				blobIs = null;
			}
		}
	    return null;
	}
	
	public String shortenLongStringByTrimHead(String s) throws Exception{
		try{
			if (s != null && s.length() > EXTREME_LONG_STRING_LENGTH) {
				s = s.substring(s.length() - EXTREME_LONG_STRING_LENGTH, s.length());
			}
		} catch (Exception e){
			logger.error(getClassName() + ".shortenLongString() - s =" + s, e);
			throw e;
		}
		return s;
	}
	
//	public void compressBytes(ByteArrayOutputStream outByteArrayOutputStream, List<CompressFileDto>  compressFileDtoList) throws Exception {
//		ZipOutputStream zos = null;
//		try{
//			zos = new ZipOutputStream(outByteArrayOutputStream);
//			zos.setLevel(ZipOutputStream.STORED);
//			for (int i = 0; i < compressFileDtoList.size(); i++) {
//				CompressFileDto compressFileDto = compressFileDtoList.get(i);
//				ByteArrayOutputStream baos = compressFileDto.getByteArrayOutputStream();
//				String fileName = compressFileDto.getFileName();
//				ZipEntry zipEntry = new ZipEntry(fileName);
//				zos.putNextEntry(zipEntry);
//				zos.write(baos.toByteArray());
//				zos.closeEntry();
//			}
//			
//		} catch (Exception e){
//			logger.error(getClassName() + ".compressBytes()");
//			throw e;
//		} finally {
//			if (zos != null) {
//				zos.close();
//				
//			}
//		}
//		
//	}
//	public void compressBytes(ByteArrayOutputStream outByteArrayOutputStream, List<ByteArrayOutputStream> inputByteArrayOutputStreamList) throws Exception {
//		ZipOutputStream zos = null;
//		try{
//			zos = new ZipOutputStream(outByteArrayOutputStream);
//			zos.setLevel(ZipOutputStream.STORED);
//			for (int i = 0; i < inputByteArrayOutputStreamList.size(); i++) {
//				ByteArrayOutputStream baos = inputByteArrayOutputStreamList.get(i);
//				ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
//				ZipInputStream zipStream = new ZipInputStream(bais);
//				if (i == 0) {
//				ZipEntry entry = null;
//				while ((entry = zipStream.getNextEntry()) != null) {
//				    String entryName = entry.getName();
//				    
//					zos.putNextEntry(entry);
//
//				}
//			    zipStream.closeEntry();
//				}
//			}
//		} catch (Exception e){
//			logger.error(getClassName() + ".compressBytes()");
//			throw e;
//		} finally {
//			if (zos != null) {
//				zos.close();
//			}
//		}
//	}
	
	public Double roundUpToCeiling(Double input, Integer decimalPlace) throws Exception {
		Double output = null;
		BigDecimal midConvertBd = null;
		try{
			if (input != null) {
				midConvertBd = new BigDecimal(input);
				if (midConvertBd != null) {
					output = midConvertBd.setScale(decimalPlace, RoundingMode.CEILING).doubleValue();
				}
			}
		} catch (Exception e){
			logger.error(getClassName() + ".convertClobToCharArray() - input =" + input + ",decimalPlace=" + decimalPlace, e);
			throw e;
		} finally {
			if (midConvertBd != null) {
				midConvertBd = null;
			}
		}
	    return output;
	}
	
	public Double roundUpInTermOfHalfUp(Double input, Integer decimalPlace) throws Exception {
		Double output = null;
		BigDecimal midConvertBd = null;
		try{
			if (input != null) {
				midConvertBd = new BigDecimal(input);
				if (midConvertBd != null) {
					output = midConvertBd.setScale(decimalPlace, RoundingMode.HALF_UP).doubleValue();
				}
			}
		} catch (Exception e){
			logger.error(getClassName() + ".convertClobToCharArray() - input =" + input + ",decimalPlace=" + decimalPlace, e);
			throw e;
		} finally {
			if (midConvertBd != null) {
				midConvertBd = null;
			}
		}
	    return output;
	}
	
	public String getIpAddress() throws Exception {
		String ip = null;
		try {
			InetAddress inetAddress = InetAddress.getLocalHost();
			ip = inetAddress.getHostAddress();
		} catch (Exception e) {
			logger.error(getClassName() + ".getIpAddress()", e);
			throw e;
		}
		return ip;
	}
	
	public String getHostName() throws Exception {
		String hostName = null;
		try {
			InetAddress inetAddress = InetAddress.getLocalHost();
			hostName = inetAddress.getHostName();
		} catch (Exception e) {
			logger.error(getClassName() + ".getHostName()", e);
			throw e;
		}
		return hostName;
	}
	
	public String trimClassName(String s) throws Exception {
		String rtn = null;
		try {
			if (s != null) {
				String[] split = s.split("\\.");
				rtn = split[split.length - 1];
			}
		} catch (Exception e) {
			logger.error(getClassName() + ".trimClassName() - s" + s, e);
			throw e;
		}
		return rtn;
	}
}
