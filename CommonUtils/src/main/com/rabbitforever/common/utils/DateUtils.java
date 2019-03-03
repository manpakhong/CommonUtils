package com.rabbitforever.common.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateUtils {
	private final Logger logger = LoggerFactory.getLogger(DateUtils.class);
	private static DateUtils dateUtils;
	private static final String SIMPLE_DATE_TIME_SQL_FORMAT = "yyyy-MM-dd HH:mm:ss";
	private static final String SIMPLE_TIME_STRING_FORMAT_HHmm = "HH:mm";
	private String getClassName() {
		return this.getClass().getName();
	}

	private DateUtils() {
	}
	
	public static DateUtils getInstance() {
		if (dateUtils == null) {
			dateUtils = new DateUtils();
		}
		return dateUtils;
	}

	public Long calculateNoOfMinutesDifferent(Date dateFrom, Date dateTo) {
		Long differentInMins = null;
		try {
			long diffInMillies = Math.abs(dateTo.getTime() - dateFrom.getTime());
			differentInMins = TimeUnit.MINUTES.convert(diffInMillies, TimeUnit.MILLISECONDS);
			
		} catch (Exception e) {
			logger.error(getClassName() + ".calculateNoOfDaysDifferent()- dateFrom=" + dateFrom + ",dateTo=" + dateTo, e);
			throw e;
		} finally {

		}
		return differentInMins;
	}
	
	public Long calculateNoOfDayDifferent(Date dateFrom, Date dateTo) {
		Long differentInDays = null;
		try {
			long diffInMillies = Math.abs(dateTo.getTime() - dateFrom.getTime());
			differentInDays = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
			
		} catch (Exception e) {
			logger.error(getClassName() + ".calculateNoOfDaysDifferent()- dateFrom=" + dateFrom + ",dateTo=" + dateTo, e);
			throw e;
		} finally {

		}
		return differentInDays;
	}
	public Date parseDateStringToDate(String dateString_yyyyMMdd) throws Exception{
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyyMMdd").parse(dateString_yyyyMMdd);
		} catch (Exception e) {
			logger.error(getClassName() + ".parseDateStringToDate()- dateString_yyyyMMdd=" + dateString_yyyyMMdd, e);
			throw e;
		}
		return date;
	}
	public Date parseDateTimeStringToDate(String dateString_yyyyMMdd_HHmmss) throws Exception{
		Date date = null;
		DateFormat formatter = null;
		try {
			formatter = new SimpleDateFormat("yyyyMMdd_HHmmss");
			date = formatter.parse(dateString_yyyyMMdd_HHmmss);
		} catch (Exception e) {
			logger.error(getClassName() + ".parseDateStringToDate()- dateString_yyyyMMdd_HHmmss=" + dateString_yyyyMMdd_HHmmss, e);
			throw e;
		} finally {
			formatter = null;
		}
		return date;
	}
	public Date parseDateTimeDetailStringToDate(String dateString_yyyyMMdd_HHmmss_SSS) throws Exception{
		Date date = null;
		try {
			date = new SimpleDateFormat("yyyyMMdd_HHmmss_SSS").parse(dateString_yyyyMMdd_HHmmss_SSS);
		} catch (Exception e) {
			logger.error(getClassName() + ".parseDateStringToDate()- dateString_yyyyMMdd_HHmmss_SSS=" + dateString_yyyyMMdd_HHmmss_SSS, e);
			throw e;
		}
		return date;
	}
	public String getDateParamString() throws Exception {
		String dateTimeString = null;
		DateFormat formatter = null;
		Date date = null;
		try {
			formatter = new SimpleDateFormat("yyyyMMdd");
			date = new Date();
			dateTimeString = formatter.format(date);
		} catch (Exception e) {
			logger.error(getClassName() + ".getDateString()", e);
			throw e;
		}
		return dateTimeString;
	}
	public String getDateTimeDetailParamString() throws Exception {
		String dateTimeString = null;
		DateFormat formatter = null;
		Date date = null;
		try {
			formatter = new SimpleDateFormat("yyyyMMdd_HHmmss_SSS");
			date = new Date();
			dateTimeString = formatter.format(date);
		} catch (Exception e) {
			logger.error(getClassName() + ".getDateTimeString()", e);
			throw e;
		}
		return dateTimeString;
	}
	public String getDateTimeSecondParamString() throws Exception {
		String dateTimeString = null;
		DateFormat formatter = null;
		Date date = null;
		try {
			formatter = new SimpleDateFormat("yyyyMMdd_HHmmss");
			date = new Date();
			dateTimeString = formatter.format(date);
		} catch (Exception e) {
			logger.error(getClassName() + ".getDateTimeString()", e);
			throw e;
		}
		return dateTimeString;
	}
	public String getTodayDisplayDateString() throws Exception {
		String dateString = null;

		try {
			dateString = convertDateTimeToDisplayDateString(new Date());
		} catch (Exception e) {
			logger.error(getClassName() + ".getTodayDateString() ", e);
			throw e;
		}
		return dateString;
	}
	public String convertDateTimeToDisplayDateString(Date date) throws Exception {
		String dateString = null;
		DateFormat formatter = null;
		try {
			formatter = new SimpleDateFormat("dd/MM/yyyy");
			dateString = formatter.format(date);
		} catch (Exception e) {
			logger.error(getClassName() + ".convertDateTimeToDisplayDateString() - date=" + date, e);
			throw e;
		} finally {
			if (formatter != null) {
				formatter = null;
			}
		}
		return dateString;
	}
	public String convertDateTimeToDisplayDateTimeString(Date date) throws Exception {
		String dateString = null;
		DateFormat formatter = null;
		try {
			formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			dateString = formatter.format(date);
		} catch (Exception e) {
			logger.error(getClassName() + ".convertDateTimeToDisplayDateTimeString() - date=" + date, e);
			throw e;
		}
		return dateString;
	}
	public String convertDate2ParamDateString(Date date) throws Exception {
		String dateString = null;
		DateFormat formatter = null;
		try {
			formatter = new SimpleDateFormat("yyyyMMdd_HHmmss");
			dateString = formatter.format(date);
		} catch (Exception e) {
			logger.error(getClassName() + ".convertDate2ParamDateString() - date=" + date, e);
			throw e;
		}
		return dateString;
	}
	public Date convertParamDateTimeString2Date(String dateTime) throws Exception {
		Date date = null;
		DateFormat formatter = null;
		try {
			formatter = new SimpleDateFormat("yyyyMMdd_HHmmss");
			date = formatter.parse(dateTime);
		} catch (Exception e) {
			logger.error(getClassName() + ".convertParamDateTimeString2Date() - dateTime=" + dateTime, e);
			throw e;
		}
		return date;
	}
	public Date getDefaultNullableDateTime() throws Exception {
		Calendar cal = null;
		try {
			cal = Calendar.getInstance();
			cal.set(Calendar.YEAR, 1111);
			cal.set(Calendar.MONTH, Calendar.NOVEMBER);
			cal.set(Calendar.DAY_OF_MONTH, 11);
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);
		} catch (Exception e) {
			logger.error(getClassName() + ".getDefaultNullableDateTime() - cal=" + cal, e);
			throw e;
		}
		return cal.getTime();
	}

	public Date getTodayWithoutTime() throws Exception {
		Calendar cal = null;
		try {
			cal = Calendar.getInstance();
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);
		} catch (Exception e) {
			logger.error(getClassName() + ".getTodayWithoutTime() - cal=" + cal, e);
			throw e;
		}
		return cal.getTime();
	}

	public boolean areTheSameDate(Date date1, Date date2) throws Exception {
		boolean theSame = true;
		try {

			if (date1 != null && date2 != null) {
				Calendar cal1 = Calendar.getInstance();
				cal1.setTime(date1);
				int year1 = cal1.get(Calendar.YEAR);
				int month1 = cal1.get(Calendar.MONTH);
				int day1 = cal1.get(Calendar.DAY_OF_MONTH);
				// int hour1 = cal1.get(Calendar.HOUR_OF_DAY);
				// int minute1 = cal1.get(Calendar.MINUTE);
				// int second1 = cal1.get(Calendar.SECOND);

				Calendar cal2 = Calendar.getInstance();
				cal2.setTime(date2);
				int year2 = cal2.get(Calendar.YEAR);
				int month2 = cal2.get(Calendar.MONTH);
				int day2 = cal2.get(Calendar.DAY_OF_MONTH);
				// int hour2 = cal2.get(Calendar.HOUR_OF_DAY);
				// int minute2 = cal2.get(Calendar.MINUTE);
				// int second2 = cal2.get(Calendar.SECOND);

				if (year1 != year2) {
					theSame = false;
				}
				if (month1 != month2) {
					theSame = false;
				}
				if (day1 != day2) {
					theSame = false;
				}
				// if (hour1 != hour2){
				// theSame = false;
				// }
				// if (minute1 != minute2){
				// theSame = false;
				// }
				// if (second1 != second2){
				// theSame = false;
				// }
			} else {
				theSame = false;
				logger.warn(getClassName() + ".areTheSameDate() - : date1 or date2 value(s) is/ are null!");
			}
		} catch (Exception e) {
			logger.error(getClassName() + ".areTheSameDate() - date1=" + date1 + ", date2=" + date2, e);
			throw e;
		}
		return theSame;
	}

	public boolean areTheSameTime(Date date1, Date date2) throws Exception {
		boolean theSame = true;
		try {
			if (date1 != null && date2 != null) {
				Calendar cal1 = Calendar.getInstance();
				cal1.setTime(date1);
				int year1 = cal1.get(Calendar.YEAR);
				int month1 = cal1.get(Calendar.MONTH);
				int day1 = cal1.get(Calendar.DAY_OF_MONTH);
				int hour1 = cal1.get(Calendar.HOUR_OF_DAY);
				int minute1 = cal1.get(Calendar.MINUTE);
				int second1 = cal1.get(Calendar.SECOND);

				Calendar cal2 = Calendar.getInstance();
				cal2.setTime(date2);
				int year2 = cal2.get(Calendar.YEAR);
				int month2 = cal2.get(Calendar.MONTH);
				int day2 = cal2.get(Calendar.DAY_OF_MONTH);
				int hour2 = cal2.get(Calendar.HOUR_OF_DAY);
				int minute2 = cal2.get(Calendar.MINUTE);
				int second2 = cal2.get(Calendar.SECOND);

				if (year1 != year2) {
					theSame = false;
				}
				if (month1 != month2) {
					theSame = false;
				}
				if (day1 != day2) {
					theSame = false;
				}
				if (hour1 != hour2) {
					theSame = false;
				}
				if (minute1 != minute2) {
					theSame = false;
				}
				if (second1 != second2) {
					theSame = false;
				}
			} else {
				theSame = false;
				logger.warn(getClassName() + ".areTheSameTime() - : date1 or date2 value(s) is/ are null!");
			}
		} catch (Exception e) {
			logger.error(getClassName() + ".areTheSameTime() - date1=" + date1 + ", date2=" + date2, e);
			throw e;
		}
		return theSame;
	}

	public Date trimDateTimeMilliSecond2DateTime(Date date) throws Exception {
		Date dateRtn = null;
		try {
			if (date != null) {
				Calendar cal = Calendar.getInstance();
				cal.setTime(date);

				// int actualMinOfHour = cal.getActualMinimum(Calendar.HOUR_OF_DAY);
				// int actualMinOfMinute = cal.getActualMinimum(Calendar.MINUTE);
				// int actualMinOfSecond = cal.getActualMinimum(Calendar.SECOND);
				int actualMinOfMilliSecond = cal.getActualMinimum(Calendar.MILLISECOND);

				// cal.set(Calendar.HOUR_OF_DAY, actualMinOfHour);
				// cal.set(Calendar.MINUTE, actualMinOfMinute);
				// cal.set(Calendar.SECOND, actualMinOfSecond);
				cal.set(Calendar.MILLISECOND, actualMinOfMilliSecond);

				dateRtn = cal.getTime();
			}
		} catch (Exception e) {
			logger.error(getClassName() + ".trimDateTimeMilliSecond2DateTime() - date=" + date, e);
			throw e;
		}
		return dateRtn;
	}

	public Date trimDateTime2Date(Date date) throws Exception {
		Date dateRtn = null;
		try {
			if (date != null) {
				Calendar cal = Calendar.getInstance();
				cal.setTime(date);

				int actualMinOfHour = cal.getActualMinimum(Calendar.HOUR_OF_DAY);
				int actualMinOfMinute = cal.getActualMinimum(Calendar.MINUTE);
				int actualMinOfSecond = cal.getActualMinimum(Calendar.SECOND);
				int actualMinOfMilliSecond = cal.getActualMinimum(Calendar.MILLISECOND);

				cal.set(Calendar.HOUR_OF_DAY, actualMinOfHour);
				cal.set(Calendar.MINUTE, actualMinOfMinute);
				cal.set(Calendar.SECOND, actualMinOfSecond);
				cal.set(Calendar.MILLISECOND, actualMinOfMilliSecond);

				dateRtn = cal.getTime();
			}
		} catch (Exception e) {
			logger.error(getClassName() + ".trimDateTime2Date() - date=" + date, e);
			throw e;
		}
		return dateRtn;
	}



	public boolean isDateEqualsOrBeforeAnother(Date date1, Date date2) throws Exception {
		boolean isDate1EqualsOrBeforeDate2 = false;
		try {
			if (date1 != null && date2 != null) {

				date1 = trimDateTime2Date(date1);
				date2 = trimDateTime2Date(date2);

				Calendar cal1 = Calendar.getInstance();
				cal1.setTime(date1);

				Calendar cal2 = Calendar.getInstance();
				cal2.setTime(date2);

				boolean areTheSameDate = areTheSameDate(date1, date2);
				if (areTheSameDate) {
					isDate1EqualsOrBeforeDate2 = true;
				} else if (cal1.before(cal2)) {
					isDate1EqualsOrBeforeDate2 = true;
				}
			}
		} catch (Exception e) {
			logger.error(getClassName() + ".isDateEqualsOrBeforeAnother() - date1=" + date1 + ", date2=" + date2, e);
			throw e;
		}
		return isDate1EqualsOrBeforeDate2;
	}

	public boolean isDefaultNullableDateTime(Date date) throws Exception {
		boolean isDefaultNullableDateTime = false;
		try {
			if (date != null) {
				Date defaultNullableDateTime = getDefaultNullableDateTime();
				isDefaultNullableDateTime = areTheSameTime(defaultNullableDateTime, date);
			}
		} catch (Exception e) {
			logger.error(getClassName() + ".isDefaultNullableDateTime() - date=" + date, e);
			throw e;
		}
		return isDefaultNullableDateTime;
	}
	public java.sql.Date convertJavaDate2SqlDate(Date javaDate) throws Exception {
		java.sql.Date sqlDate = null;
		try {
			if (javaDate != null) {
				sqlDate = new java.sql.Date(javaDate.getTime());
			}
		} catch (Exception e) {
			logger.error(getClassName() + ".convertJavaDate2SqlDate()- javaDate=" + javaDate, e);
			throw e;
		}
		return sqlDate;
	}

	public Calendar convertDate2Calendar(Date javaDate) throws Exception {
		Calendar calendar = null;
		try {
			if (javaDate != null) {
				calendar = Calendar.getInstance();
				calendar.setTime(javaDate);
			}
		} catch (Exception e) {
			logger.error(getClassName() + ".convertDate2Calendar()- javaDate=" + javaDate, e);
			throw e;
		}
		return calendar;
	}
	public Date convertSqlDate2JavaDate(java.sql.Date sqlDate) throws Exception {
		Date javaDate = null;
		try {
			if (sqlDate != null) {
				javaDate = new Date(sqlDate.getTime());
			}
		} catch (Exception e) {
			logger.error(getClassName() + ".convertJavaDate2SqlDate()- sqlDate=" + sqlDate, e);
			throw e;
		}
		return javaDate;
	}

	public Timestamp convertSqlDate2SqlTimestamp(Date date) throws Exception {
		Timestamp timestamp = null;
		try {
			if (date != null) {
				timestamp = new Timestamp(date.getTime());
			}
		} catch (Exception e) {
			logger.error(getClassName() + ".convertJavaDate2SqlDate() - date=" + date, e);
			throw e;
		}
		return timestamp;
	}

	public String convertCalendar2SqlDateString(Calendar calendar) throws Exception {
		StringBuilder mysqlDateSb = new StringBuilder();
		try {
			mysqlDateSb.append(calendar.get(Calendar.YEAR));
			mysqlDateSb.append("-");
			mysqlDateSb.append(paddingZero2Units(calendar.get(Calendar.MONTH) + 1));
			mysqlDateSb.append("-");
			mysqlDateSb.append(paddingZero2Units(calendar.get(Calendar.DAY_OF_MONTH)));
			mysqlDateSb.append(" ");
			mysqlDateSb.append(paddingZero2Units(calendar.get(Calendar.HOUR)));
			mysqlDateSb.append(":");
			mysqlDateSb.append(paddingZero2Units(calendar.get(Calendar.MINUTE)));
			mysqlDateSb.append(":");
			mysqlDateSb.append(paddingZero2Units(calendar.get(Calendar.SECOND)));
		} catch (Exception e) {
			logger.error(getClassName() + ".convertCalendar2SqlDateString() - calendar=" + calendar, e);
			throw e;
		}
		return mysqlDateSb.toString();
	}

	private String paddingZero2Units(Integer intPart) throws Exception {
		String intPartStr = intPart.toString();
		try {
			if (intPartStr.length() == 1) {
				intPartStr = "0" + intPartStr;
			}
		} catch (Exception e) {
			logger.error(getClassName() + ".paddingZero2Units - intPart=" + intPart, e);
			throw e;
		}
		return intPartStr;
	}

	public List<String> regMatch(String sourceString, String patternString) throws Exception {
		List<String> matchStrList = new ArrayList<String>();
		try {
			Pattern pattern = Pattern.compile(patternString, Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(sourceString);
			while (matcher.find()) {
				matchStrList.add(matcher.group());
			}
		} catch (Exception e) {
			logger.error(
					getClassName() + ".regMatch() - sourceString=" + sourceString + ",patternString=" + patternString,
					e);
			throw e;
		}
		return matchStrList;
	}

	public boolean isInteger(String str) {
		try {
			Integer.parseInt(str);
		} catch (Exception e) {
			if (e instanceof NumberFormatException) {
				return false;
			} else {
				return false;
			}
		}
		return true;
	}
	public Date tryParseSqlDateString(String sqlFormatDateString) {
		Date date = null;
		try {
			if (sqlFormatDateString != null && !sqlFormatDateString.isEmpty()) {
				date = this.parseSqlDateString2Date(sqlFormatDateString);
			}
		} catch (Exception e) {
			return date;
		}
		return date;
	}
	public Date parseSqlDateString2Date(String sqlFormatDateString) throws Exception {
		Date date = null;
		SimpleDateFormat formatter;
		try {
			if (sqlFormatDateString != null  && !sqlFormatDateString.isEmpty()) {
				formatter = new SimpleDateFormat(SIMPLE_DATE_TIME_SQL_FORMAT);
				date = new Date();
				date = formatter.parse(sqlFormatDateString);
			} else {
				logger.warn(getClassName() + ".parseSqlDateString2Date() - sqlFormatDateString is null or empty, sqlFormatDateString=" + sqlFormatDateString);
			}
		} catch (Exception e) {
			logger.error(getClassName() + ".parseSqlDateString2Date()- sqlFormatDateString=" + sqlFormatDateString, e);
			throw e;
		} finally {
			formatter = null;
		}
		return date;

	}
	public boolean isDateEqualsOrAfterAnother(Date date1, Date date2) throws Exception {
		boolean isDate1EqualsOrAfterDate2 = false;
		try {
			if (date1 != null && date2 != null) {

				date1 = trimDateTime2Date(date1);
				date2 = trimDateTime2Date(date2);

				Calendar cal1 = Calendar.getInstance();
				cal1.setTime(date1);

				Calendar cal2 = Calendar.getInstance();
				cal2.setTime(date2);

				boolean areTheSameDate = areTheSameDate(date1, date2);
				if (areTheSameDate) {
					isDate1EqualsOrAfterDate2 = true;
				} else if (cal1.after(cal2)) {
					isDate1EqualsOrAfterDate2 = true;
				}
			}
		} catch (Exception e) {
			logger.error(getClassName() + ".isDateEqualsOrAfterAnother() - date1=" + date1 + ", date2=" + date2, e);
			throw e;
		}
		return isDate1EqualsOrAfterDate2;
	}


	
	//This method ONLY aware the time of provided date
	public boolean isWithinTime(Date date, Date dateFrom, Date dateTo) throws Exception {
		boolean isWithinTime = false;
		try {
			DateFormat df = new SimpleDateFormat("HHmmss");
			int timeFrom = Integer.parseInt(df.format(dateFrom));
			int timeTo = Integer.parseInt(df.format(dateTo));
			int serviceTime = Integer.parseInt(df.format(date));
			isWithinTime = (timeFrom <= serviceTime) && (serviceTime <= timeTo);
		} catch (Exception e) {
			logger.error(getClassName() + ".isWithinTime() - date=" + date + ", dateFrom=" + dateFrom + ", dateTo=" + dateTo, e);
			throw e;
		}
		return isWithinTime;
	}
	
	public int compareTimePartOnly(Calendar cal1, Calendar cal2) throws Exception {
		int result = 0;
		try {
			result = compareTimePartOnly(cal1.getTime(), cal2.getTime());
		} catch (Exception e) {
			logger.error(getClassName() + ".compareTimePartOnly() - cal1=" + cal1 + ", cal2=" + cal2, e);
			throw e;
		}
		return result;
	}
	public boolean isTime1EqualToTime2(Date date1, Date date2) throws Exception {
		boolean isEqual = false;
		try {
			int result = compareTimePartOnly(date1, date2);
			if (result == 0) {
				isEqual = true;
			}
		} catch (Exception e) {
			logger.error(getClassName() + ".isTime1AfterTime2() - date1=" + date1 + ", date2=" + date2, e);
			throw e;
		}
		return isEqual;
	}
	public boolean isTime1AfterTime2(Date date1, Date date2) throws Exception {
		boolean isAfter = false;
		try {
			int result = compareTimePartOnly(date1, date2);
			if (result > 0) {
				isAfter = true;
			}
		} catch (Exception e) {
			logger.error(getClassName() + ".isTime1AfterTime2() - date1=" + date1 + ", date2=" + date2, e);
			throw e;
		}
		return isAfter;
	}
	public boolean isTime1BeforeTime2(Date date1, Date date2) throws Exception {
		boolean isBefore = false;
		try {
			int result = compareTimePartOnly(date1, date2);
			if (result < 0) {
				isBefore = true;
			}
		} catch (Exception e) {
			logger.error(getClassName() + ".isTime1BeforeTime2() - date1=" + date1 + ", date2=" + date2, e);
			throw e;
		}
		return isBefore;
	}
	public int compareTimePartOnly(Date date1, Date date2) throws Exception {
		int result = 0;
		try {
			DateFormat df = new SimpleDateFormat("HHmmss");
			int time1 = Integer.parseInt(df.format(date1));
			int time2 = Integer.parseInt(df.format(date2));
			if (time1 > time2) {
				result = 1;
			} else if (time1 < time2) {
				result = -1;
			} else if (time1 == time2) {
				result = 0;
			}
		} catch (Exception e) {
			logger.error(getClassName() + ".compareTimePartOnly() - date1=" + date1 + ", date2=" + date2, e);
			throw e;
		}
		return result;
	}
	public Date parseDateString_HHmm2Date(String dateString_HHmm) throws Exception {
		Date date = null;
		SimpleDateFormat formatter;
		try {
			if (dateString_HHmm != null  && !dateString_HHmm.isEmpty()) {
				formatter = new SimpleDateFormat(SIMPLE_TIME_STRING_FORMAT_HHmm);
				date = new Date();
				date = formatter.parse(dateString_HHmm);
			} else {
				logger.warn(getClassName() + ".parseDateString_HHmm2Date() - dateString_HHmm is null or empty, dateString_HHmm=" + dateString_HHmm);
			}
		} catch (Exception e) {
			logger.error(getClassName() + ".parseDateString_HHmm2Date()- dateString_HHmm=" + dateString_HHmm, e);
			throw e;
		} finally {
			formatter = null;
		}
		return date;

	}

}
