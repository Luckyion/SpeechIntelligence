package me.videa.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.content.Context;

/**
 * 
 */
public class DateTimeUtil {
	/**
	 * 日期时间格式 yyyy-MM-dd HH:mm:ss
	 */
	public static final String LONGTIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
	/**
	 * 日期格式 yyyy-MM-dd
	 */
	public static final String MEDIATIME_PATTERN = "yyyy-MM-dd";
	/**
	 * 时间格式 HH:mm:SS
	 */
	public static final String SHORTTIME_PATTERN = "HH:mm:SS";
	// 格式 20050101
	private static final SimpleDateFormat dateFormat1 = new SimpleDateFormat(
			"yyyyMMdd");
	// 格式 20050101121212
	private static final SimpleDateFormat timeFormat1 = new SimpleDateFormat(
			"yyyyMMddHHmmss");

	// 格式 2005-01-01
	private static final SimpleDateFormat dateFormat2 = new SimpleDateFormat(
			"yyyy-MM-dd");
	// 格式 2005-01-01 12:12:12
	private static final SimpleDateFormat timeFormat2 = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	/**
	 * @param dt
	 *            参数
	 * @return boolean
	 */
	public static boolean isDateTime(String dt) {
		SimpleDateFormat sdf = new SimpleDateFormat();
		try {
			sdf.parse(dt);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 得到指定日期的字符串格式：YYYY-MM-DD HH:mm:SS.ms
	 * 
	 * @param dt
	 *            指定的日期
	 * @return String
	 */
	public static String getFullTime(Calendar dt) {
		return formatCalendar(dt, LONGTIME_PATTERN);
	}

	/**
	 * 得到当前日期的字符串格式：YYYY-MM-DD HH:mm:SS.ms
	 * 
	 * @return String
	 */
	public static String getFullTime() {
		return getFullTime(Calendar.getInstance());
	}

	/**
	 * 得到短日期格式：HH:mm:SS.ms
	 * 
	 * @param datetime
	 *            日期
	 * @return String
	 */
	public static String getShortTime(Calendar datetime) {
		return formatCalendar(datetime, SHORTTIME_PATTERN);
	}

	/**
	 * 得到当前日期短日期格式：HH:mm:SS.ms
	 * 
	 * @return String
	 */
	public static String getShortTime() {
		return getShortTime(Calendar.getInstance());
	}

	/**
	 * 得到日期格式：YYYY-MM-DD
	 * 
	 * @param datetime
	 *            日期
	 * @return String
	 */
	public static String getMediumTime(Calendar datetime) {
		return formatCalendar(datetime, MEDIATIME_PATTERN);
	}

	/**
	 * 得到当前日期的年月日格式：YYYY-MM-DD
	 * 
	 * @return String
	 */
	public static String getMediumTime() {
		return getMediumTime(Calendar.getInstance());
	}

	/**
	 * 比较两个日期的早晚，日期格式为：yyyy-MM－dd
	 * 
	 * @param date1
	 *            日期1
	 * @param date2
	 *            日期2
	 * @return int 如果日期1晚于日期2，则返回大于0；如果日期1等日期2，则返回0；如果日期1早于日期2，则返回小于0
	 */
	public static int compareDate(String date1, String date2) {
		Date dt1 = toDate(date1);
		Date dt2 = toDate(date2);
		return dt1.compareTo(dt2);
	}

	/**
	 * 比较自定义格式化模式的两个日期的早晚
	 * 
	 * @param date1
	 *            日期1
	 * @param date2
	 *            日期2
	 * @param pattern
	 *            要格式化的模式
	 * @return int 如果日期1晚于日期2，则返回大于0；如果日期1等日期2，则返回0；如果日期1早于日期2，则返回小于0
	 */
	public static int compareDefinedDate(String date1, String date2,
			String pattern) {

		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Date dt1 = null;
		Date dt2 = null;
		try {
			dt1 = sdf.parse(date1);
			dt2 = sdf.parse(date2);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return dt1.compareTo(dt2);
	}

	/**
	 * 得到当前年份
	 * 
	 * @return int
	 */
	public static int getCurrentYear() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.YEAR);
	}

	/**
	 * 得到当前月份
	 * 
	 * @return int
	 */
	public static int getCurrentMonth() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.MONTH) + 1;
	}

	/**
	 * 得到当前日
	 * 
	 * @return int
	 */
	public static int getCurrentDay() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 格式化给定时间
	 * 
	 * @param cal
	 *            给定时间
	 * @param pattern
	 *            要格式化的模式
	 * @return 格式化后的字符串
	 */
	public static String formatCalendar(Calendar cal, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(cal.getTime());
	}

	/**
	 * 将字符串(yyy-MM-dd)转化为日期
	 * 
	 * @param strDate
	 *            待转化的日期字符串
	 * @return 日期对象,如果字符串格式非法，则返回null
	 */
	public static Date toDate(String strDate) {
		SimpleDateFormat sdf = new SimpleDateFormat(MEDIATIME_PATTERN);
		try {
			return sdf.parse(strDate);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 将字符串(yyy-MM-dd)转化为日期
	 * 
	 * @param strDate
	 *            待转化的日期字符串
	 * @return 日期对象,如果字符串格式非法，则返回null
	 */
	public static Date toDateTime(String strDate) {
		SimpleDateFormat sdf = new SimpleDateFormat(LONGTIME_PATTERN);
		try {
			return sdf.parse(strDate);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 将字符串(yyy-MM-dd)转化为日期
	 * 
	 * @param strDate
	 *            待转化的日期字符串
	 * @return Calendar 日期对象,如果字符串格式非法，则返回null
	 */
	public static Calendar toCalendar(String strDate) {
		Date dt = toDate(strDate);
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		return cal;
	}
	
	/**
	 * 将字符串(yyy-MM-dd HH:mm:ss)转化为日期时间
	 * 
	 * @param strDate
	 *            待转化的日期字符串
	 * @return Calendar 日期对象,如果字符串格式非法，则返回null
	 */
	public static Calendar toLongCalendar(String strDate) {
		Date dt = toDateTime(strDate);
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		return cal;
	}

	public static void main(String[] args) {
		// System.out.println(getFullTime(Calendar.getInstance()));
		Date date = new Date();
		System.err.println(DateTimeUtil.dateTimeToString(date));
	}

	/**
	 * 将日期转化为指定格式的字符串
	 * 
	 * @param date
	 *            日期
	 * @param format
	 *            格式字符串
	 * @return 指定格式的日期字符串
	 */
	public static String formatDate(java.util.Date date, String format) {
		if (null == date) {
			return null;
		}
		SimpleDateFormat sdFromat = new SimpleDateFormat(format);
		return sdFromat.format(date);
	}

	/**
	 * 日期转换为字符串 短日期
	 * 
	 * @param date
	 *            参数
	 * @return String
	 */
	public static String dateToString(Date date) {
		if (date != null) {
			return dateFormat2.format(date);
		} else {
			return null;
		}
	}

	/**
	 * 日期转换为字符串 长日期
	 * 
	 * @param date
	 *            参数
	 * @return String
	 */
	public static String dateTimeToString(Date date) {
		if (date != null) {
			return timeFormat2.format(date);
		} else {
			return null;
		}
	}

	/**
	 * 获得一个月的最大天数
	 * 
	 * @param strdate
	 *            参数
	 * @return int
	 */
	public static int getMaxDay(String strdate) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
		int maxDay = 0;
		try {
			Date date = format.parse(strdate);
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			maxDay = cal.getActualMaximum(Calendar.DATE);// 给定此 Calendar
															// 的时间值，返回指定日历字段可能拥有的最大值
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return maxDay;
	}

	public static Date timestamppToDate(Timestamp tt) {
		return new Date(tt.getTime());
	}

	/**
	 * 更改格式日期yyyy-MM-dd为日期格式为yyyy/MM/dd
	 * 
	 * @param sourceDate
	 * @return
	 */
	public static String fromatDate(String sourceDate) {
		StringBuffer sb = new StringBuffer(sourceDate);
		int year = Integer.parseInt(sb.substring(0, sb.indexOf("-")));
		sb.delete(0, sb.indexOf("-") + 1);
		int month = Integer.parseInt(sb.substring(0, sb.indexOf("-")));
		sb.delete(0, sb.indexOf("-") + 1);
		int day = Integer.parseInt(sb.toString());
		Calendar now = Calendar.getInstance();
		int yearNow = now.get(Calendar.YEAR);
		if (yearNow == year) {
			return "" + month + "/" + day;
		}
		return year + "/" + month + "/" + day;
	}

	/**
	 * 格式化格式为yyyy-MM-dd的日期，得到格式为MM/dd
	 * 
	 * @param sourceDate
	 * @return
	 */
	public static String fromatShortDate(String sourceDate) {
		StringBuffer sb = new StringBuffer(sourceDate);
		sb.delete(0, sb.indexOf("-") + 1);
		int month = Integer.parseInt(sb.substring(0, sb.indexOf("-")));
		sb.delete(0, sb.indexOf("-") + 1);
		int day = Integer.parseInt(sb.toString());
		return "" + month + "/" + day;
	}

	/**
	 * 得到当前如期，格式为yyyy.MM.dd
	 * 
	 * @return
	 */
	public static String getDateForamt() {
		// TODO Auto-generated method stub
		Date nowTime = new Date();
		System.out.println(nowTime);
		SimpleDateFormat time = new SimpleDateFormat("yyyy.MM.dd");
		return time.format(nowTime);
	}

	/**
	 * 更改日期格式为2013/12/30
	 * 
	 * @param sourceDate
	 * @return
	 */
	public static String fromatDateToID() {

		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		int month = now.get(Calendar.MONTH);
		int day = now.get(Calendar.DAY_OF_MONTH);
		int h = now.get(Calendar.HOUR);
		int m = now.get(Calendar.MINUTE);
		return year + "" + month + "" + day + "" + h + "" + m;
	}

	/**
	 * 更改日期格式为2013/12/30
	 * 
	 * @param sourceDate
	 * @return
	 */
	public static String fromatCurrentDate() {

		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		int month = now.get(Calendar.MONTH) + 1;
		int day = now.get(Calendar.DAY_OF_MONTH);
		int h = now.get(Calendar.HOUR);
		int m = now.get(Calendar.MINUTE);
		return year + "-" + month + "-" + day;
	}
	
	/**
	 * 对时间戳格式进行格式化，保证时间戳长度为13位
	 * 
	 * @param timestamp
	 *            时间戳
	 * @return 返回为13位的时间戳
	 */
	public static String formatTimestamp(String timestamp)
	{
		if (timestamp == null || "".equals(timestamp))
		{
			return "";
		}
		String tempTimeStamp = timestamp + "00000000000000";
		StringBuffer stringBuffer = new StringBuffer(tempTimeStamp);
		return tempTimeStamp = stringBuffer.substring(0, 13);
	}
	/**
	 * 根据 timestamp 生成各类时间状态串
	 * 
	 * @param timestamp
	 *            距1970 00:00:00 GMT的秒数
	 * @param format
	 *            格式
	 * @return 时间状态串(如：刚刚5分钟前)
	 */
	public static String getTimeState(String timestamp, String format)
	{
		if (timestamp == null || "".equals(timestamp))
		{
			return "";
		}

		try
		{
			timestamp = formatTimestamp(timestamp);
			long _timestamp = Long.parseLong(timestamp);
			if (System.currentTimeMillis() - _timestamp < 1 * 60 * 1000)
			{
				return "刚刚";
			} else if (System.currentTimeMillis() - _timestamp < 30 * 60 * 1000)
			{
				return ((System.currentTimeMillis() - _timestamp) / 1000 / 60)
						+ "分钟前";
			} else
			{
				Calendar now = Calendar.getInstance();
				Calendar c = Calendar.getInstance();
				c.setTimeInMillis(_timestamp);
				if (c.get(Calendar.YEAR) == now.get(Calendar.YEAR)
						&& c.get(Calendar.MONTH) == now.get(Calendar.MONTH)
						&& c.get(Calendar.DATE) == now.get(Calendar.DATE))
				{
					SimpleDateFormat sdf = new SimpleDateFormat("今天 HH:mm");
					return sdf.format(c.getTime());
				}
				if (c.get(Calendar.YEAR) == now.get(Calendar.YEAR)
						&& c.get(Calendar.MONTH) == now.get(Calendar.MONTH)
						&& c.get(Calendar.DATE) == now.get(Calendar.DATE) - 1)
				{
					SimpleDateFormat sdf = new SimpleDateFormat("昨天 HH:mm");
					return sdf.format(c.getTime());
				} else if (c.get(Calendar.YEAR) == now.get(Calendar.YEAR))
				{
					SimpleDateFormat sdf = null;
					if (format != null && !format.equalsIgnoreCase(""))
					{
						sdf = new SimpleDateFormat(format);

					} else
					{
						sdf = new SimpleDateFormat("M月d日 HH:mm:ss");
					}

					return sdf.format(c.getTime());
				} else
				{
					SimpleDateFormat sdf = null;
					if (format != null && !format.equalsIgnoreCase(""))
					{
						sdf = new SimpleDateFormat(format);

					} else
					{
						sdf = new SimpleDateFormat("yyyy年M月d日 HH:mm:ss");
					}
					return sdf.format(c.getTime());
				}
			}
		} catch (Exception e)
		{
			e.printStackTrace();
			return "";
		}
	}
}
