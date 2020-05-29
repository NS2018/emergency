package com.emergency.framework.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 日期工具类
 */
public class DateUtil {

	private static final String ISO8601_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";

	/**
	 * solr日期转换 SimpleDateFormat为非线程安全的 所以使用ThreadLocal
	 */
	private final static ThreadLocal<SimpleDateFormat> SOLR_DF_LOCAL = new ThreadLocal<SimpleDateFormat>() {
		protected SimpleDateFormat initialValue() {

			return new SimpleDateFormat(ISO8601_DATE_FORMAT);

		};
	};

	private final static ThreadLocal<SimpleDateFormat> UTC_DF_LOCAL = new ThreadLocal<SimpleDateFormat>() {
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat(ISO8601_DATE_FORMAT);
		};
	};

	/**
	 * mysql date转换
	 */
	private final static ThreadLocal<SimpleDateFormat> MYSQL_DATE_DF_LOCAL = new ThreadLocal<SimpleDateFormat>() {
		protected SimpleDateFormat initialValue() {

			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());

		};
	};

	public final static long YEAR_TIME = 365 * 24 * 3600 * 1000L;

	/** 缺省日期格式 */
	public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";

	/** 缺省时间格式 */
	public static final String DEFAULT_TIME_FORMAT = "HH:mm:ss";

	/** 缺省月格式 */
	public static final String DEFAULT_MONTH = "MONTH";

	/** 缺省年格式 */
	public static final String DEFAULT_YEAR = "YEAR";

	/** 缺省日格式 */
	public static final String DEFAULT_DATE = "DAY";

	/** 缺省小时格式 */
	public static final String DEFAULT_HOUR = "HOUR";

	/** 缺省分钟格式 */
	public static final String DEFAULT_MINUTE = "MINUTE";

	/** 缺省秒格式 */
	public static final String DEFAULT_SECOND = "SECOND";

	/** 缺省长日期格式 */
	public static final String DEFAULT_DATETIME_FORMAT = "yyyy-MM-dd HH-mm";

	/** 缺省长日期格式,精确到秒 */
	public static final String DEFAULT_DATETIME_FORMAT_SEC = "yyyy-MM-dd HH:mm:ss";

	/** 星期数组 */
	public static final String[] WEEKS = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };

	/**
	 * 最大年龄值
	 */
	public final static int MAX_AGE = 1000;

	private static Date date;

	/**
	 * 取当前日期的字符串表示
	 * 
	 * @return 当前日期的字符串 ,如2010-05-28
	 **/
	public static String today() {
		return today(DEFAULT_DATE_FORMAT);
	}

	/**
	 * 根据输入的格式得到当前日期的字符串
	 * 
	 * @param strFormat
	 *            日期格式
	 * @return
	 */
	public static String today(String strFormat) {
		return toString(new Date(), strFormat);
	}

	/**
	 * 取当前时间的字符串表示,
	 * 
	 * @return 当前时间,如:21:10:12
	 **/
	public static String currentTime() {
		return currentTime(DEFAULT_TIME_FORMAT);
	}

	/**
	 * 根据输入的格式获取时间的字符串表示
	 * 
	 * @param 输出格式
	 *            ,如'hh:mm:ss'
	 * @return 当前时间,如:21:10:12
	 **/

	public static String currentTime(String strFormat) {
		return toString(new Date(), strFormat);
	}

	/**
	 * 取得相对于当前时间增加天数/月数/年数后的日期 <br>
	 * 欲取得当前日期5天前的日期,可做如下调用:<br>
	 * getAddDay("DATE", -5).
	 * 
	 * @param field
	 *            ,段,如"year","month","date",对大小写不敏感
	 * @param amount
	 *            ,增加的数量(减少用负数表示),如5,-1
	 * @return 格式化后的字符串 如"2010-05-28"
	 * @throws ParseException
	 **/

	public static String getAddDay(String field, int amount) throws ParseException {
		return getAddDay(field, amount, null);
	}

	/**
	 * 取得相对于当前时间增加天数/月数/年数后的日期,按指定格式输出
	 * 
	 * 欲取得当前日期5天前的日期,可做如下调用:<br>
	 * getAddDay("DATE", -5,'yyyy-mm-dd hh:mm').
	 * 
	 * @param field
	 *            ,段,如"year","month","date",对大小写不敏感
	 * @param amount
	 *            ,增加的数量(减少用负数表示),如5,-1
	 * @param strFormat
	 *            ,输出格式,如"yyyy-mm-dd","yyyy-mm-dd hh:mm"
	 * @return 格式化后的字符串 如"2010-05-28"
	 * @throws ParseException
	 **/
	public static String getAddDay(String field, int amount, String strFormat) throws ParseException {
		return getAddDay(null, field, amount, strFormat);
	}

	/**
	 * 功能：对于给定的时间增加天数/月数/年数后的日期,按指定格式输出
	 * 
	 * @param date
	 *            String 要改变的日期
	 * @param field
	 *            int 日期改变的字段，YEAR,MONTH,DAY
	 * @param amount
	 *            int 改变量
	 * @param strFormat
	 *            日期返回格式
	 * @return
	 * @throws ParseException
	 */
	public static String getAddDay(String date, String field, int amount, String strFormat) throws ParseException {
		if (strFormat == null) {
			strFormat = DEFAULT_DATETIME_FORMAT_SEC;
		}
		Calendar rightNow = Calendar.getInstance();
		if (date != null && !"".equals(date.trim())) {
			rightNow.setTime(parseDate(date, strFormat));
		}
		if (field == null) {
			return toString(rightNow.getTime(), strFormat);
		}
		rightNow.add(getInterval(field), amount);
		return toString(rightNow.getTime(), strFormat);
	}

	/**
	 * 获取时间间隔类型
	 * 
	 * @param field
	 *            时间间隔类型
	 * @return 日历的时间间隔
	 */
	protected static int getInterval(String field) {
		String tmpField = field.toUpperCase();
		if (tmpField.equals(DEFAULT_YEAR)) {
			return Calendar.YEAR;
		} else if (tmpField.equals(DEFAULT_MONTH)) {
			return Calendar.MONTH;
		} else if (tmpField.equals(DEFAULT_DATE)) {
			return Calendar.DATE;
		} else if (DEFAULT_HOUR.equals(tmpField)) {
			return Calendar.HOUR;
		} else if (DEFAULT_MINUTE.equals(tmpField)) {
			return Calendar.MINUTE;
		} else {
			return Calendar.SECOND;
		}
	}

	/**
	 * 获取格式化对象
	 * 
	 * @param strFormat
	 *            格式化的格式 如"yyyy-MM-dd"
	 * @return 格式化对象
	 */
	public static SimpleDateFormat getSimpleDateFormat(String strFormat) {
		if (strFormat != null && !"".equals(strFormat.trim())) {
			return new SimpleDateFormat(strFormat);
		} else {
			return new SimpleDateFormat();
		}
	}

	/**
	 * 得到当前日期的星期数
	 * 
	 * @return 当前日期的星期的字符串
	 * @throws ParseException
	 */
	public static String getWeekOfMonth() throws ParseException {
		return getWeekOfMonth(null, null);
	}

	/**
	 * 获取年的第几周
	 * 
	 * @param date
	 * @return
	 */
	public static int getWeekOfYear(Date date) {
		Calendar rightNow = Calendar.getInstance();
		rightNow.setFirstDayOfWeek(Calendar.MONDAY);
		if (date != null) {
			rightNow.setTime(date);
		}
		return rightNow.get(Calendar.WEEK_OF_YEAR);
	}

	/**
	 * 获取年
	 * 
	 * @param date
	 * @return
	 */
	public static int getYear(Date date) {
		Calendar rightNow = Calendar.getInstance();
		if (date != null) {
			rightNow.setTime(date);
		}
		return rightNow.get(Calendar.YEAR);
	}

	/**
	 * 获取月
	 * 
	 * @param date
	 * @return
	 */
	public static int getMonth(Date date) {
		Calendar rightNow = Calendar.getInstance();
		if (date != null) {
			rightNow.setTime(date);
		}
		return rightNow.get(Calendar.MONTH);
	}

	/**
	 * 根据日期的到给定日期的在当月中的星期数
	 * 
	 * @param date
	 *            给定日期
	 * @return
	 * @throws ParseException
	 */
	public static String getWeekOfMonth(String date, String fromat) throws ParseException {
		Calendar rightNow = Calendar.getInstance();
		if (date != null && !"".equals(date.trim())) {
			rightNow.setTime(parseDate(date, fromat));
		}
		return WEEKS[rightNow.get(Calendar.WEEK_OF_MONTH)];
	}

	/**
	 * 获取指定日期为周几, 范围: 1~7
	 * 
	 * @param date
	 * @return
	 */
	public static int getDayOfWeek(Date date) {
		Calendar rightNow = Calendar.getInstance();
		if (date != null) {
			rightNow.setTime(date);
		}
		return rightNow.get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * 获取指定时间戳为周几, 范围: 1~7
	 * 
	 * @param millis
	 * @return
	 */
	public static int getDayOfWeek(long millis) {
		Calendar rightNow = Calendar.getInstance();
		rightNow.setTimeInMillis(millis);
		return rightNow.get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * 获取当前为周几(周日返回1), 范围: 1~7
	 * 
	 * @return
	 */
	public static int getDayOfWeek() {
		return getDayOfWeek(null);
	}

	/**
	 * 获取月的几号
	 * 
	 * @param date
	 * @return
	 */
	public static int getDayOfMonth(Date date) {
		Calendar rightNow = Calendar.getInstance();
		if (date != null) {
			rightNow.setTime(date);
		}
		return rightNow.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 获取月的几号
	 * 
	 * @return
	 */
	public static int getDayOfMonth() {
		return getDayOfMonth(null);
	}

	/**
	 * 获取当前为周几(周日返回7), 范围: 1~7
	 * 
	 * @return
	 */
	public static int getChineseDayOfWeek() {
		return getChineseDayOfWeek(null);
	}

	/**
	 * 获取当前为周几(周日返回7), 范围: 1~7
	 * 
	 * @param date
	 * 
	 * @return
	 */
	public static int getChineseDayOfWeek(Date date) {
		int dayOfWeek = getDayOfWeek(date);
		dayOfWeek = dayOfWeek - 1;
		dayOfWeek = dayOfWeek <= 0 ? 7 : dayOfWeek;
		return dayOfWeek;
	}

	/**
	 * 获取当前为周几(周日返回7), 范围: 1~7
	 * 
	 * @param millis
	 * @return
	 */
	public static int getChineseDayOfWeek(long millis) {
		return getChineseDayOfWeek(new Date(millis));
	}

	/**
	 * 将java.util.date型按照指定格式转为字符串
	 * 
	 * @param date
	 *            源对象
	 * @param format
	 *            想得到的格式字符串
	 * @return 如：2010-05-28
	 */
	public static String toString(Date date, String format) {
		return getSimpleDateFormat(format).format(date);
	}

	/**
	 * 将java.util.date型按照缺省格式转为字符串
	 * 
	 * @param date
	 *            源对象
	 * @return 如：2010-05-28
	 */
	public static String toString(Date date) {
		return toString(date, DEFAULT_DATE_FORMAT);
	}

	/**
	 * 强制类型转换 从串到日期
	 * 
	 * @param sDate
	 *            源字符串，采用yyyy-MM-dd格式
	 * @param sFormat
	 *            ps
	 * @return 得到的日期对象
	 * @throws ParseException
	 */
	public static Date parseDate(String strDate, String format) throws ParseException {
		return getSimpleDateFormat(format).parse(strDate);
	}

	/***
	 * 根据传入的毫秒数和格式，对日期进行格式化输出
	 * 
	 * @version 2011-7-12
	 * @param object
	 * @param format
	 * @return
	 */
	public static String millisecondFormat(Long millisecond, String format) {
		if (millisecond == null || millisecond <= 0) {
			throw new IllegalArgumentException(String.format("传入的时间毫秒数[%s]不合法", "" + millisecond));
		}
		if (format == null || "".equals(format.trim())) {
			format = DEFAULT_DATE_FORMAT;
		}
		return toString(new Date(millisecond), format);
	}

	/**
	 * 强制类型转换 从串到时间戳
	 * 
	 * @param sDate
	 *            源串
	 * @param sFormat
	 *            遵循格式
	 * @return 取得的时间戳对象
	 * @throws ParseException
	 */
	public static Timestamp parseTimestamp(String strDate, String format) throws ParseException {
		Date utildate = getSimpleDateFormat(format).parse(strDate);
		return new Timestamp(utildate.getTime());
	}

	/**
	 * getCurDate 取当前日期
	 * 
	 * @return java.util.Date型日期
	 **/
	public static Date getCurDate() {
		return (new Date());
	}

	/**
	 * getCurTimestamp 取当前时间戳
	 * 
	 * @return java.sql.Timestamp
	 **/
	public static Timestamp getCurTimestamp() {
		return new Timestamp(new Date().getTime());
	}

	/**
	 * getCurTimestamp 取遵循格式的当前时间
	 * 
	 * @param sFormat
	 *            遵循格式
	 * @return java.sql.Timestamp
	 **/
	public static Date getCurDate(String format) throws Exception {
		return getSimpleDateFormat(format).parse(toString(new Date(), format));
	}

	/**
	 * Timestamp按照指定格式转为字符串
	 * 
	 * @param timestamp
	 *            源对象
	 * @param format
	 *            ps（如yyyy.mm.dd）
	 * @return 如：2010-05-28 或2010-05-281 13:21
	 */
	public static String toString(Timestamp timestamp, String format) {
		if (timestamp == null) {
			return "";
		}
		return toString(new Date(timestamp.getTime()), format);
	}

	/**
	 * Timestamp按照缺省格式转为字符串
	 * 
	 * @param ts
	 *            源对象
	 * @return 如：2010-05-28
	 */
	public static String toString(Timestamp ts) {
		return toString(ts, DEFAULT_DATE_FORMAT);
	}

	/**
	 * Timestamp按照缺省格式转为字符串，可指定是否使用长格式
	 * 
	 * @param timestamp
	 *            欲转化之变量Timestamp
	 * @param fullFormat
	 *            是否使用长格式
	 * @return 如：2010-05-28 或2010-05-28 21:21
	 */
	public static String toString(Timestamp timestamp, boolean fullFormat) {
		if (fullFormat) {
			return toString(timestamp, DEFAULT_DATETIME_FORMAT_SEC);
		} else {
			return toString(timestamp, DEFAULT_DATE_FORMAT);
		}
	}

	/**
	 * 将sqldate型按照指定格式转为字符串
	 * 
	 * @param sqldate
	 *            源对象
	 * @param sFormat
	 *            ps
	 * @return 如：2010-05-28 或2010-05-28 00:00
	 */
	public static String toString(java.sql.Date sqldate, String sFormat) {
		if (sqldate == null) {
			return "";
		}
		return toString(new Date(sqldate.getTime()), sFormat);
	}

	/**
	 * 将sqldate型按照缺省格式转为字符串
	 * 
	 * @param sqldate
	 *            源对象
	 * @return 如：2010-05-28
	 */
	public static String toString(java.sql.Date sqldate) {
		return toString(sqldate, DEFAULT_DATE_FORMAT);
	}

	/**
	 * 计算日期时间之间的差值， date1得时间必须大于date2的时间
	 * 
	 * @version 2011-7-12
	 * @param date1
	 * @param date2
	 * @return {@link Map} Map的键分别为, day(天),
	 *         hour(小时),minute(分钟)和second(秒)。
	 */
	public static Map<String, Long> timeDifference(final Date date1, final Date date2) {
		if (date1 == null || date2 == null) {
			throw new NullPointerException("date1 and date2 can't null");
		}
		long mim1 = date1.getTime();
		long mim2 = date2.getTime();
		if (mim1 < mim2) {
			throw new IllegalArgumentException(
					String.format("date1[%s] not be less than date2[%s].", mim1 + "", mim2 + ""));
		}
		long m = (mim1 - mim2 + 1) / 1000l;
		long mday = 24 * 3600;
		final Map<String, Long> map = new HashMap<String, Long>();
		map.put("day", m / mday);
		m = m % mday;
		map.put("hour", (m) / 3600);
		map.put("minute", (m % 3600) / 60);
		map.put("second", (m % 3600 % 60));
		return map;
	}

	/**
	 * 验证是否相同的日期
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean isTheSameDay(final Date date1, final Date date2) {
		String f1 = format("yyyy-MM-dd", date1);
		String f2 = format("yyyy-MM-dd", date2);
		boolean same = f1.equals(f2);
		return same;
	}

	public static Map<String, Integer> compareTo(final Date date1, final Date date2) {
		if (date1 == null || date2 == null) {
			return null;
		}
		long time1 = date1.getTime();
		long time2 = date2.getTime();
		long time = Math.max(time1, time2) - Math.min(time1, time2);
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(time);
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("year", (calendar.get(Calendar.YEAR) - 1970) > 0 ? (calendar.get(Calendar.YEAR) - 1970) : 0);
		map.put("month", (calendar.get(Calendar.MONTH) - 1) > 0 ? (calendar.get(Calendar.MONTH) - 1) : 0);
		map.put("day", (calendar.get(Calendar.DAY_OF_MONTH) - 1) > 0 ? (calendar.get(Calendar.DAY_OF_MONTH) - 1) : 0);
		map.put("hour", (calendar.get(Calendar.HOUR_OF_DAY) - 8) > 0 ? (calendar.get(Calendar.HOUR_OF_DAY) - 8) : 0);
		map.put("minute", calendar.get(Calendar.MINUTE) > 0 ? calendar.get(Calendar.MINUTE) : 0);
		map.put("second", calendar.get(Calendar.SECOND) > 0 ? calendar.get(Calendar.SECOND) : 0);
		return map;
	}

	/**
	 * 获取solr日期格式
	 * 
	 * @param date
	 * @return
	 */
	public final static String getSolrDate(Date date) {
		return SOLR_DF_LOCAL.get().format(date);
	}

	public final static String getUTCDate(Date date) {
		SimpleDateFormat simpleDateFormat = UTC_DF_LOCAL.get();
		return simpleDateFormat.format(date);
	}

	/**
	 * utc string to date
	 * 
	 * @param dateStr
	 * @return
	 * @throws ParseException
	 */
	public final static Date parseUTCDate(String dateStr) throws ParseException {
		SimpleDateFormat simpleDateFormat = UTC_DF_LOCAL.get();
		return simpleDateFormat.parse(dateStr);
	}

	/**
	 * 格式化指定日期
	 * 
	 * @param pattern
	 * @param date
	 * @return
	 */
	public final static String format(String pattern, Date date) {
		SimpleDateFormat fmt = new SimpleDateFormat(pattern);
		return fmt.format(date);
	}

	/**
	 * 格式化当前日期
	 * 
	 * @param pattern
	 * @return
	 */
	public final static String format(String pattern) {
		SimpleDateFormat fmt = new SimpleDateFormat(pattern);
		return fmt.format(new Date());
	}

	/**
	 * 转换指定的日期字符串为mysql的date格式数据
	 * 
	 * @param dateStr
	 * @return
	 * @throws ParseException
	 */
	public final static Date getMysqlDate(String dateStr) throws ParseException {
		return MYSQL_DATE_DF_LOCAL.get().parse(dateStr);
	}

	public final static String formatMysqlDate(Date date) throws ParseException {
		return MYSQL_DATE_DF_LOCAL.get().format(date);
	}

	/**
	 * 获取mysql date类型的字段默认值
	 * 
	 * @return
	 */
	public final static Date getMysqlDefaultDate() {
		try {
			return MYSQL_DATE_DF_LOCAL.get().parse("0001-01-01 00:00:00");
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 获取年龄
	 * 
	 * @param curdate
	 * @param birthday
	 * @return
	 */
	public final static int getAge(Date curdate, Date birthday) {
		if (curdate == null)
			curdate = new Date();
		long ageTimeM = curdate.getTime() - birthday.getTime();
		int age = (int) (ageTimeM / YEAR_TIME);
		age = Math.max(0, age);
		return age;
	}

	/**
	 * 根据出生日期获取年龄
	 * 
	 * @param brithday
	 * @param formateDateStr
	 * @return
	 * @throws ParseException
	 * @throws Exception
	 */
	public final static int getCurrentAgeByBirthdate(Date brithDay) {
		if (brithDay == null) {
			return 0;
		}

		try {

			Calendar calBrithDay = Calendar.getInstance();
			calBrithDay.setTime(brithDay);
			Calendar currCal = Calendar.getInstance();
			return currCal.get(Calendar.YEAR) - calBrithDay.get(Calendar.YEAR);
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * 比较两个时间是否相等
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public final static boolean isEqual(Date date1, Date date2) {
		if (date1 == null || date2 == null) {
			return false;
		}
		return (date1.getTime() == date2.getTime());
	}

	/**
	 * 获取若干天之后的日期
	 * 
	 * @param fewDays
	 *            几天之后，譬如：明天 fewDays = 1，后天 fewDays = 2；
	 * @return
	 */
	public final static Date getNextSeveralDay(int fewDays) {
		Date date = new Date();// 取时间
		return getNextSeveralDay(date, fewDays);
	}

	/**
	 * 获取某日的若干天后的日期
	 * 
	 * @param date
	 * @param fewDays
	 * @return
	 */
	public final static Date getNextSeveralDay(Date date, int fewDays) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, fewDays);// 把日期往后增加一天.整数往后推,负数往前移动
		date = calendar.getTime(); // 这个时间就是日期往后推一天的结果
		return date;
	}

	/**
	 * 获取年月日整形日期
	 * 
	 * @param date
	 * @return
	 */
	public static int getDayIntValue(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String dateNowStr = sdf.format(date);
		int day = Integer.parseInt(dateNowStr);
		return day;
	}

	/**
	 * 获取Ios8601格式日期
	 * 
	 * @param date
	 * @return
	 */
	public static String formatIso8601Date(Date date) {

		SimpleDateFormat df = new SimpleDateFormat(ISO8601_DATE_FORMAT);

		df.setTimeZone(new SimpleTimeZone(0, "UTC"));

		return df.format(date);

	}

	/**
	 * 获取指定日期所在月的天数
	 */
	public static int getCurrentMonthLastDay(Date date) {
		Calendar a = Calendar.getInstance();
		a.setTime(date);
		a.set(Calendar.DATE, 1);// 把日期设置为当月第一天
		a.roll(Calendar.DATE, -1);// 日期回滚一天，也就是最后一天
		int maxDate = a.get(Calendar.DATE);
		return maxDate;
	}

	public static void main(String[] args) throws Exception {

		Date parseDate = DateUtil.parseDate("20180512155054", "yyyyMMddHHmmss");

		Calendar vipEndTimeCal = Calendar.getInstance();
		vipEndTimeCal.setTime(DateUtil.parseDate("2018-07-25 23:10:10", "yyyy-MM-dd HH:mm:ss"));
		Calendar currTimeCal = Calendar.getInstance();
		currTimeCal.setTime(DateUtil.parseDate("2018-05-28 06:10:10", "yyyy-MM-dd HH:mm:ss"));
		currTimeCal.add(Calendar.MONTH, 1);
		currTimeCal.set(Calendar.HOUR_OF_DAY, 23);
		currTimeCal.set(Calendar.MINUTE, 59);
		currTimeCal.set(Calendar.SECOND, 59);
		Date date2 = new Date(currTimeCal.getTimeInMillis());
		Date time = currTimeCal.getTime();

		int monthNumcont = 0;
		while (true) {
			currTimeCal.add(Calendar.MONTH, 1);
			if (currTimeCal.getTime().getTime() > vipEndTimeCal.getTime().getTime()) {
				break;
			}
			monthNumcont++;
		}
		currTimeCal.add(Calendar.MONTH, -1);
		int daynum = vipEndTimeCal.get(Calendar.DAY_OF_YEAR) - currTimeCal.get(Calendar.DAY_OF_YEAR);

		// 剩余月数
		int monthNum = vipEndTimeCal.get(Calendar.MONTH) - currTimeCal.get(Calendar.MONTH);

		// System.out.println(getDayOfWeek());
		int currentMonthLastDay = getCurrentMonthLastDay(new Date());
		Calendar rightNow = Calendar.getInstance();
		// rightNow.setTime(parseDate("2015-10-17", "yyyy-MM-dd"));
		// System.out.println(getWeekOfYear(new Date()));
		TimeZone tz = TimeZone.getTimeZone("GMT");
		rightNow.setTimeZone(tz);

		System.out.println(System.currentTimeMillis() + 3600L * 24 * 365 * 1000);

	}

}
