package com.njxz.exam.util;

import org.apache.log4j.Logger;

/**
 * @author piperChan 2017 7/3
 */
public class Logable {
	public static Logger logger = Logger.getLogger(Logable.class);

	public void info(Object message) {
		logger.info(message);
	}

	public static void error(Object message) {
		logger.error(message);

	}
}
