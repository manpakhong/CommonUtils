package com.rabbitforever.common.utils;

import java.sql.Timestamp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MiscUtils {
	private final Logger logger = LoggerFactory.getLogger(getClassName());
	private static MiscUtils miscUtils;
	
	public static MiscUtils getInstance() {
		if (miscUtils == null) {
			miscUtils = new MiscUtils();
		}
		return miscUtils;
	}
	private MiscUtils() {
		
	}
	private String getClassName() {
		return this.getClass().getName();
	}
	public java.sql.Date convertUtilDate2SqlDate(java.util.Date utilDate) throws Exception{
		java.sql.Date sqlDate = null;
		try {
			sqlDate = new java.sql.Date(utilDate.getTime());
		} catch (Exception e) {
			logger.error(getClassName() + ".convertUtilDate2SqlDate() - utilDate=" + utilDate.toString(), e);
			throw e;
		}
		return sqlDate;
	}
	public java.util.Date convertSqlDate2UtilDate(java.sql.Date sqlDate) throws Exception{
		java.util.Date utilDate = null;
		try {
			utilDate = new java.util.Date(sqlDate.getTime());
		} catch (Exception e) {
			logger.error(getClassName() + ".convertSqlDate2UtilDate() - sqlDate=" + sqlDate.toString(), e);
			throw e;
		}
		return utilDate;
	}
	public Timestamp convertUtilDate2Timestamp(java.util.Date utilDate) throws Exception {
		Timestamp timestamp = null;
		try {
			timestamp = new Timestamp(utilDate.getTime());
		} catch (Exception e) {
			logger.error(getClassName() + ".convertUtilDate2Timestamp() - utilDate=" + utilDate.toString(), e);
			throw e;
		}
		return timestamp;
	}
	public java.util.Date convertTimestamp2UtilDate(Timestamp timestamp) throws Exception {
		java.util.Date utilDate = null;
		try {
			utilDate = new java.util.Date(timestamp.getTime());
		} catch (Exception e) {
			logger.error(getClassName() + ".convertUtilDate2Timestamp() - timestamp=" + timestamp.toString(), e);
			throw e;
		}
		return utilDate;
	}
}
