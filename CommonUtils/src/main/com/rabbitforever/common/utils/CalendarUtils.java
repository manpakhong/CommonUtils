package com.rabbitforever.common.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rabbitforever.common.factories.UtilsFactory;

public class CalendarUtils {

	private final String SIMPLE_DATE_TIME_FORMAT_STRING = "yyyy-MM-dd HH:mm:ss";
	private final String SIMPLE_DATE_TIME_FORMAT_WITH_UTC_TZ_OFFSET_STRING = "yyyy-MM-dd HH:mm:ss Z";
	private final String UTC_TIMEZONE_STRING = "Etc/UTC";
	private UtilsFactory utilsFactory;
	private CommonUtils commonUtils;
	private final Logger logger = LoggerFactory.getLogger(getClassName());

	public CalendarUtils() {
		utilsFactory = UtilsFactory.getInstance();
		commonUtils = utilsFactory.getInstanceOfCommonUtils();
	}
	
	private String getClassName() {
		return this.getClass().getName();
	}

	public void recomputeCalendar(Calendar cal) throws Exception {
		try {
			String refreshCal = "refreshCal - M: " + cal.get(Calendar.DAY_OF_MONTH) + "H:"
					+ cal.get(Calendar.HOUR_OF_DAY);
			if (logger.isDebugEnabled()) {
				logger.debug(getClassName() + ".convertCalendar2TimeZoneCalendar(), after recompute: " + refreshCal);
			}
		} catch (Exception e) {
			logger.error(getClassName() + ".convertJavaDate2SqlDate()- cal=" + cal, e);
			throw e;
		}
	}

	public java.sql.Date convertJavaDate2SqlDate(Date javaDate) throws Exception {
		java.sql.Date sqlDate = null;
		try {
			if (javaDate != null) {
				sqlDate = new java.sql.Date(javaDate.getTime());
			}
		} catch (Exception e) {
			logger.error(getClassName() + ".convertJavaDate2SqlDate() - javaDate=" + javaDate, e);
			throw e;
		}
		return sqlDate;
	}

	public Date convertSqlDate2JavaDate(java.sql.Date sqlDate) throws Exception {
		Date javaDate = null;
		try {
			if (sqlDate != null) {
				javaDate = new Date(sqlDate.getTime());
			}
		} catch (Exception e) {
			logger.error(getClassName() + ".convertJavaDate2SqlDate() - sqlDate=" + sqlDate, e);
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

	public String convertCalendar2MySqlDateString(Calendar calendar) throws Exception {
		StringBuilder mysqlDateSb = null;
		try {
			mysqlDateSb = new StringBuilder();
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
			logger.error(getClassName() + ".convertCalendar2MySqlDateString() - calendar=" + calendar, e);
			throw e;
		}
		return mysqlDateSb.toString();
	}

	private String paddingZero2Units(Integer intPart) throws Exception {

		String intPartStr = null;
		try {
			intPartStr = intPart.toString();
			if (intPartStr.length() == 1) {
				intPartStr = "0" + intPartStr;
			}
		} catch (Exception e) {
			logger.error(getClassName() + ".paddingZero2Units() - intPart=" + intPart, e);
			throw e;
		}
		return intPartStr;
	}

	public Date convertCalendar2TimeZoneDate(Calendar calendar, TimeZone toTimeZone) throws Exception {
		Calendar newCal = null;
		Date returnDate = null;
		try {
			newCal = convertCalendar2TimeZoneCalendar(calendar, toTimeZone);
			returnDate = newCal.getTime();
		} catch (Exception e) {
			logger.error(getClassName() + ".convertCalendar2TimeZoneDate() - calendar=" + calendar + ",toTimeZone="
					+ toTimeZone, e);
			throw e;
		}
		return returnDate;
	}

	public Calendar convertCalendar2TimeZoneCalendar(Calendar cal, TimeZone toTimeZone) throws Exception {
		Calendar newCal = null;
		try {
			String dateFormatString = convertDate2SimpleDateFormatString(cal.getTime(), toTimeZone);
			newCal = convertSimpleDateString2Calendar(dateFormatString, toTimeZone);

			// !!!!!! the following line must be existed to trigger recomputing after
			// setting time zone
			String beforeConvert = "before - M: " + cal.get(Calendar.DAY_OF_MONTH) + "H:"
					+ cal.get(Calendar.HOUR_OF_DAY);
			if (logger.isDebugEnabled()) {
				logger.debug(getClassName() + ".convertCalendar2TimeZoneCalendar(), before convert: " + beforeConvert);
			}
			cal.setTimeZone(toTimeZone);
			// !!!!!! the following line must be existed to trigger recomputing after
			// setting time zone
			String afterConvert = "after - M: " + newCal.get(Calendar.DAY_OF_MONTH) + "H:"
					+ newCal.get(Calendar.HOUR_OF_DAY);
			if (logger.isDebugEnabled()) {
				logger.debug(getClassName() + ".convertCalendar2TimeZoneCalendar(), after convert: " + afterConvert);
			}
		} catch (Exception e) {
			logger.error(
					getClassName() + ".convertCalendar2TimeZoneCalendar() - cal=" + cal + ",toTimeZone=" + toTimeZone,
					e);
			throw e;
		}
		return newCal;
	}

	public String convertDate2SimpleDateFormatString(Date date, TimeZone tz) throws Exception {
		DateFormat formatter = null;
		String str = null;
		try {
			formatter = new SimpleDateFormat(SIMPLE_DATE_TIME_FORMAT_WITH_UTC_TZ_OFFSET_STRING);
			formatter.setTimeZone(tz);
			str = formatter.format(date);
		} catch (Exception e) {
			logger.error(getClassName() + ".convertCalendar2TimeZoneCalendar() - date=" + date + ",tz=" + tz, e);
			throw e;
		}
		return str;
	}

	public Calendar convertSimpleDateString2Calendar(String stringFormat, TimeZone tz) throws Exception {
		Calendar cal = null;
		try {
			List<String> datePartList = commonUtils.regMatch(stringFormat, "\\d{4}-\\d{2}-\\d{2}");
			List<String> timePartList = commonUtils.regMatch(stringFormat, "\\d{2}:\\d{2}:\\d{2}");
			List<String> zoneOffsetList = commonUtils.regMatch(stringFormat, "[+-]\\d{4}");
			String datePart = "";
			String timePart = "";
			String zoneOffset = "";
			if (datePartList != null && datePartList.get(0) != null) {
				datePart = datePartList.get(0);
			}
			if (timePartList != null && timePartList.get(0) != null) {
				timePart = timePartList.get(0);
			}
			if (zoneOffsetList != null && zoneOffsetList.get(0) != null) {
				zoneOffset = zoneOffsetList.get(0);
			}
			String[] datePartArray = datePart.split("-");
			String[] timePartArray = timePart.split(":");

			Integer month = null;
			Integer dayOfMonth = null;
			Integer year = null;

			Integer hour = null;
			Integer minute = null;
			Integer second = null;

			if (datePartArray.length == 3) {
				if (commonUtils.isInteger(datePartArray[0])) {
					year = new Integer(datePartArray[0]);
				}
				if (commonUtils.isInteger(datePartArray[1])) {
					month = new Integer(datePartArray[1]);
				}
				if (commonUtils.isInteger(datePartArray[2])) {
					dayOfMonth = new Integer(datePartArray[2]);
				}
			}
			if (timePartArray.length == 3) {
				if (commonUtils.isInteger(timePartArray[0])) {
					hour = new Integer(timePartArray[0]);
				}
				if (commonUtils.isInteger(timePartArray[1])) {
					minute = new Integer(timePartArray[1]);
				}
				if (commonUtils.isInteger(timePartArray[2])) {
					second = new Integer(timePartArray[2]);
				}
			}
			if (month != null && dayOfMonth != null && year != null && hour != null && minute != null
					&& second != null) {
				cal = Calendar.getInstance(tz);
				cal.set(Calendar.MONTH, (month.intValue() - 1));
				cal.set(Calendar.DAY_OF_MONTH, dayOfMonth.intValue());
				cal.set(Calendar.YEAR, year.intValue());
				cal.set(Calendar.HOUR_OF_DAY, hour.intValue());
				cal.set(Calendar.MINUTE, minute.intValue());
				cal.set(Calendar.SECOND, second.intValue());
			}
			recomputeCalendar(cal);

		} catch (Exception e) {
			logger.error(getClassName() + ".getCalendarByString() - stringFormat=" + stringFormat + ",tz=" + tz, e);
			throw e;
		}
		return cal;
	}

	public Calendar getTimeZoneEndMaxTimeZoneCalendarOfTheMonth(TimeZone tz) throws Exception {
		Calendar cal = null;
		try {
			cal = getMaxTimeZoneCalendarToday(tz);
			cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
			recomputeCalendar(cal);
		} catch (Exception e) {
			logger.error(getClassName() + ".getTimeZoneEndMaxTimeZoneCalendarOfTheMonth() - tz=" + tz, e);
			throw e;
		}
		return cal;
	}

	public Calendar getTimeZoneBeginningTimeZoneMinCalendarOfTheMonth(TimeZone tz) throws Exception {
		Calendar cal = null;
		try {
			cal = getMinTimeZoneCalendarToday(tz);
			cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
			recomputeCalendar(cal);
		} catch (Exception e) {
			logger.error(getClassName() + ".getTimeZoneBeginningTimeZoneMinCalendarOfTheMonth() - tz=" + tz, e);
			throw e;
		}
		return cal;
	}

	public void moveCalendar2EndMaxOfTheMonth(Calendar cal) throws Exception {
		try {
			cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
			recomputeCalendar(cal);
		} catch (Exception e) {
			logger.error(getClassName() + ".moveCalendar2EndMaxOfTheMonth() - cal=" + cal, e);
			throw e;
		}
	}

	public Calendar getUtcEndMaxCalendarOfTheMonth() throws Exception {
		Calendar cal = null;
		try {
			cal = getMaxUtcCalendarToday();
			cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
			recomputeCalendar(cal);
		} catch (Exception e) {
			logger.error(getClassName() + ".getUtcEndMaxCalendarOfTheMonth() - cal=" + cal, e);
			throw e;
		}
		return cal;
	}

	public void moveCalendar2BeginningMinOfTheMonth(Calendar cal) throws Exception {
		try {
			cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
			recomputeCalendar(cal);
		} catch (Exception e) {
			logger.error(getClassName() + ".moveCalendar2BeginningMinOfTheMonth() - cal=" + cal, e);
			throw e;
		}
	}

	public Calendar getUtcBeginningMinCalendarOfTheMonth() throws Exception {
		Calendar cal = null;
		try {
			cal = getMinUtcCalendarToday();
			cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
			recomputeCalendar(cal);
		} catch (Exception e) {
			logger.error(getClassName() + ".getUtcBeginningMinCalendarOfTheMonth() - cal=" + cal, e);
			throw e;
		}
		return cal;
	}

	public Calendar getMinTimeZoneCalendarToday(TimeZone tz) throws Exception {
		Calendar cal = null;
		try {
			cal = Calendar.getInstance(tz);
			trimCalendar2Minimum(cal);
		} catch (Exception e) {
			logger.error(getClassName() + ".getMinTimeZoneCalendarToday() - tz=" + tz, e);
			throw e;
		}
		return cal;
	}

	public Calendar getMaxTimeZoneCalendarToday(TimeZone tz) throws Exception {
		Calendar cal = null;
		try {
			cal = Calendar.getInstance(tz);
			trimCalendar2Maximum(cal);
		} catch (Exception e) {
			logger.error(getClassName() + ".getMaxTimeZoneCalendarToday() - tz=" + tz, e);
			throw e;
		}
		return cal;
	}

	public Calendar getMinHostCalendarToday() throws Exception {
		TimeZone tz = null;
		Calendar cal = null;
		try {
			tz = TimeZone.getDefault();
			cal = Calendar.getInstance();
			cal.setTimeZone(tz);
			trimCalendar2Minimum(cal);
		} catch (Exception e) {
			logger.error(getClassName() + ".getMinHostCalendarToday() - tz=" + tz + ",cal=" + cal, e);
			throw e;
		}
		return cal;
	}

	public Calendar getMaxHostCalendarToday() throws Exception {
		TimeZone tz = null;
		Calendar cal = null;
		try {
			tz = TimeZone.getDefault();
			cal = Calendar.getInstance();
			cal.setTimeZone(tz);
			trimCalendar2Maximum(cal);
		} catch (Exception e) {
			logger.error(getClassName() + ".getMaxHostCalendarToday() - tz=" + tz + ",cal=" + cal, e);
			throw e;
		}
		return cal;
	}

	public TimeZone getUtcTimeZone() throws Exception {
		TimeZone tz = null;
		try {
			TimeZone.getTimeZone("Etc/UTC");
		} catch (Exception e) {
			logger.error(getClassName() + ".getMaxHostCalendarToday() - tz=" + tz, e);
			throw e;
		}
		return tz;
	}

	public Calendar getUtcCalendarTodayThisHourTrimMinimum() throws Exception {
		Calendar cal = null;
		try {
			cal = getUtcCalendarToday();
			trimCalendarMinuteSecond(cal);
		} catch (Exception e) {
			logger.error(getClassName() + ".getMaxHostCalendarToday() - cal=" + cal, e);
			throw e;
		}
		return cal;
	}

	public Calendar getUtcCalendarToday() throws Exception {
		TimeZone utcTz = null;
		Calendar cal = null;
		try {

			utcTz = TimeZone.getTimeZone("Etc/UTC");
			cal = Calendar.getInstance(utcTz);
		} catch (Exception e) {
			logger.error(getClassName() + ".getUtcCalendarToday() - cal=" + cal + ",utcTz=" + utcTz, e);
			throw e;
		}
		return cal;
	}

	public Calendar getMinUtcCalendarToday() throws Exception {
		Calendar cal = null;
		try {
			cal = getUtcCalendarToday();
			trimCalendar2Minimum(cal);
		} catch (Exception e) {
			logger.error(getClassName() + ".getMinUtcCalendarToday() - cal=" + cal, e);
			throw e;
		}
		return cal;
	}

	public Calendar getMaxUtcCalendarToday() throws Exception {
		Calendar cal = null;
		try {
			cal = getUtcCalendarToday();
			trimCalendar2Maximum(cal);
		} catch (Exception e) {
			logger.error(getClassName() + ".getMaxUtcCalendarToday() - cal=" + cal, e);
			throw e;
		}
		return cal;
	}

	public void trimCalendarMinuteSecond(Calendar cal) throws Exception {
		try {
			if (cal != null) {
				cal.set(Calendar.MINUTE, cal.getActualMinimum(Calendar.MINUTE));
				cal.set(Calendar.SECOND, cal.getActualMinimum(Calendar.SECOND));
				cal.set(Calendar.MILLISECOND, cal.getActualMinimum(Calendar.MILLISECOND));
				recomputeCalendar(cal);
			}
		} catch (Exception e) {
			logger.error(getClassName() + ".trimCalendarMinuteSecond() - cal=" + cal, e);
			throw e;
		}
	}

	public void trimCalendar2Minimum(Calendar cal) throws Exception {
		try {
			if (cal != null) {
				cal.set(Calendar.HOUR_OF_DAY, cal.getActualMinimum(Calendar.HOUR_OF_DAY));
				cal.set(Calendar.MINUTE, cal.getActualMinimum(Calendar.MINUTE));
				cal.set(Calendar.SECOND, cal.getActualMinimum(Calendar.SECOND));
				cal.set(Calendar.MILLISECOND, cal.getActualMinimum(Calendar.MILLISECOND));
				recomputeCalendar(cal);
			}
		} catch (Exception e) {
			logger.error(getClassName() + ".trimCalendar2Minimum() - cal=" + cal, e);
			throw e;
		}
	}

	public Date trimDateMinuteSecond(Date date) throws Exception {
		Calendar cal = null;
		Date rtnDate = null;
		try {
			cal = Calendar.getInstance();
			cal.setTime(date);
			trimCalendarMinuteSecond(cal);
			recomputeCalendar(cal);
			rtnDate = cal.getTime();
		} catch (Exception e) {
			logger.error(getClassName() + ".trimDateMinuteSecond() - date=" + date, e);
			throw e;
		}
		return rtnDate;
	}

	public Date trimDate2Minimum(Date date) throws Exception {
		Calendar cal = null;
		Date rtnDate = null;
		try {
			cal = Calendar.getInstance();
			cal.setTime(date);
			trimCalendar2Minimum(cal);
			recomputeCalendar(cal);
			rtnDate = cal.getTime();
		} catch (Exception e) {
			logger.error(getClassName() + ".trimDate2Minimum() - date=" + date, e);
			throw e;
		}
		return rtnDate;
	}

	public Date trimDate2Maximum(Date date) throws Exception {
		Calendar cal = null;
		Date rtnDate = null;
		try {
			cal = Calendar.getInstance();
			cal.setTime(date);
			trimCalendar2Maximum(cal);
			recomputeCalendar(cal);
			rtnDate = cal.getTime();
		} catch (Exception e) {
			logger.error(getClassName() + ".trimDate2Maximum() - date=" + date, e);
			throw e;
		}
		return rtnDate;
	}

	public void trimCalendar2HourMinimum(Calendar cal) throws Exception {
		try {
			if (cal != null) {
				cal.set(Calendar.MINUTE, cal.getActualMaximum(Calendar.MINUTE));
				cal.set(Calendar.SECOND, cal.getActualMaximum(Calendar.SECOND));
				cal.set(Calendar.MILLISECOND, cal.getActualMaximum(Calendar.MILLISECOND));
				recomputeCalendar(cal);
			}
		} catch (Exception e) {
			logger.error(getClassName() + ".trimCalendar2HourMinimum() - cal=" + cal, e);
			throw e;
		}
	}

	public void trimCalendar2Maximum(Calendar cal)  throws Exception{
		try {
			if (cal != null) {
				cal.set(Calendar.HOUR_OF_DAY, cal.getActualMaximum(Calendar.HOUR_OF_DAY));
				cal.set(Calendar.MINUTE, cal.getActualMaximum(Calendar.MINUTE));
				cal.set(Calendar.SECOND, cal.getActualMaximum(Calendar.SECOND));
				cal.set(Calendar.MILLISECOND, cal.getActualMaximum(Calendar.MILLISECOND));
				recomputeCalendar(cal);

			}
		} catch (Exception e) {
			logger.error(getClassName() + ".trimCalendar2Maximum() - cal=" + cal, e);
			throw e;
		}
	}

	public Integer changeDate2Unixtime(Date date) throws Exception{
		Integer rtnUnixtime = null;
		try {
			if (date != null) {
				rtnUnixtime = (int) (date.getTime() / 1000);
			}
		} catch (Exception e) {
			logger.error(getClassName() + ".changeDate2Unixtime() - date=" + date, e);
			throw e;
		}
		return rtnUnixtime;
	}
}
