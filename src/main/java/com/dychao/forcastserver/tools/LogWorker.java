package com.dychao.forcastserver.tools;

import com.dychao.forcastserver.CommConfigure;
import org.apache.log4j.Logger;

public class LogWorker {
	private static Logger logger = Logger.getLogger(LogWorker.class);

	private static Logger outputLogger = Logger.getLogger(LogWorker.class);

	public static Logger getLog() {
		return logger;
	}

	public static void output(Class cls, Object object){
		LogWorker.outputLogger = Logger.getLogger(cls);
		String enable = CommConfigure.env.getProperty("log-worker.console.enable");
		if(enable.equals("true")) {
			System.out.println(object);
		} else {
			LogWorker.outputLogger.info("pure-business: "+object);
		}
	}

	public static void out(Object object){
		String enable = CommConfigure.env.getProperty("log-worker.console.enable");
		if(enable.equals("true")) {
			System.out.println(object);
		} else {
			logger.info("pure-business: "+object);
		}
	}

	public static void out(String object, String level){
		String enable = CommConfigure.env.getProperty("log-worker.console.enable");
		if(enable.equals("true")) {
			System.out.println(object);
		} else {
			if(level.equals("info"))
				logger.info("pure-business: "+object);
			if(level.equals("debug"))
				logger.debug("pure-business: "+object);
			if(level.equals("warn"))
				logger.warn("pure-business: "+object);
			if(level.equals("error"))
				logger.error("pure-business: "+object);
		}
	}

}
