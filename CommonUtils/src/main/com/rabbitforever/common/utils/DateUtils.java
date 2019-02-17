package com.rabbitforever.common.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateUtils {
	private final Logger logger = LoggerFactory.getLogger(DateUtils.class);
	private static DateUtils dateUtils;
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
}
