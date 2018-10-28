package com.klcw.app.util.log;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;


public class Logger {
	/**
	 * Priority constant for the println method; use TALogger.v.
	 */
	public static final int VERBOSE = 2;

	/**
	 * Priority constant for the println method; use TALogger.d.
	 */
	public static final int DEBUG = 3;

	/**
	 * Priority constant for the println method; use TALogger.i.
	 */
	public static final int INFO = 4;

	/**
	 * Priority constant for the println method; use TALogger.w.
	 */
	public static final int WARN = 5;

	/**
	 * Priority constant for the println method; use TALogger.e.
	 */
	public static final int ERROR = 6;
	/**
	 * Priority constant for the println method.
	 */
	public static final int ASSERT = 7;
	/**
	 * 是否在logcat中显示Log日志
	 */
	private static boolean debugOn = true;
	/**
	 * 默认的log标识
	 */
	protected static final String TAG = "logger";
	private static HashMap<String, ILogger> loggerHashMap = new HashMap<String, ILogger>();
	private static final ILogger defaultLogger = new PrintToLogCatLogger();

	public static void removeLogger(ILogger logger) {
		String loggerName = logger.getClass().getName();
		if (loggerHashMap.containsKey(loggerName)) {
			logger.close();
			loggerHashMap.remove(loggerName);
		}
	}

	public static void d(String message) {
		d(TAG, message);
	}

	public static void e(String message) {
		e(TAG, message);
	}

	public static void i(String message) {
		i(TAG, message);
	}

	public static void v(String message) {
		v(TAG, message);
	}

	public static void w(String message) {
		w(TAG, message);
	}

	public static void d(Object object, String message) {
		printLoger(DEBUG, object, message);
	}

	public static void e(Object object, String message) {
		printLoger(ERROR, object, message);
	}

	public static void i(Object object, String message) {
		printLoger(INFO, object, message);
	}

	public static void v(Object object, String message) {
		printLoger(VERBOSE, object, message);
	}

	public static void w(Object object, String message) {
		printLoger(WARN, object, message);
	}
	
	public static void d(String tag, String message) {
		printLoger(DEBUG, tag, message);
	}

	public static void e(String tag, String message) {
		printLoger(ERROR, tag, message);
	}

	public static void i(String tag, String message) {
		printLoger(INFO, tag, message);
	}

	public static void v(String tag, String message) {
		printLoger(VERBOSE, tag, message);
	}

	public static void w(String tag, String message) {
		printLoger(WARN, tag, message);
	}

	public static void println(int priority, String tag, String message) {
		printLoger(priority, tag, message);
	}

	private static void printLoger(int priority, Object object, String message) {
		Class<?> cls = object.getClass();
		String tag = cls.getName();
		String arrays[] = tag.split("\\.");
		tag = arrays[arrays.length - 1];
		printLoger(priority, tag, message);
	}

	private static void printLoger(int priority, String tag, String message) {
		if (isDebugOn()) {
			printLoger(defaultLogger, priority, tag, message);
		}
		if (priority == ERROR || priority == WARN) {
			Iterator<Entry<String, ILogger>> iter = loggerHashMap.entrySet().iterator();
			while (iter.hasNext()) {
				Entry<String, ILogger> entry = iter.next();
				ILogger logger = entry.getValue();
				if (logger != null) {
					printLoger(logger, priority, tag, message);
				}
			}
		}
	}

	private static void printLoger(ILogger logger, int priority, String tag, String message) {

		switch (priority) {
		case VERBOSE:
			logger.v(tag, message);
			break;
		case DEBUG:
			logger.d(tag, message);
			break;
		case INFO:
			logger.i(tag, message);
			break;
		case WARN:
			logger.w(tag, message);
			break;
		case ERROR:
			logger.e(tag, message);
			break;
		default:
			break;
		}
	}
	
	public static boolean isDebugOn() {
		return debugOn;
	}

	public static void setDebugOn(boolean debugOn) {
		Logger.debugOn = debugOn;
	}
}
