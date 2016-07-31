package org.javaosc.framework.web;

import java.beans.Introspector;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.javaosc.framework.constant.ProperConstant;
import org.javaosc.framework.context.AnnotationScaner;
import org.javaosc.framework.context.BeanFactory;
import org.javaosc.framework.context.Configuration;
import org.javaosc.framework.jdbc.ConnectionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @description
 * @author Dylan Tao
 * @date 2014-09-09
 * Copyright 2014 Javaosc Team. All Rights Reserved.
 */
public class ContextListener implements ServletContextListener {
	
	private static final Logger log = LoggerFactory.getLogger(ContextListener.class);

	public void contextDestroyed(ServletContextEvent event) {
		
		ActionContext.destroy();
		Configuration.clear();
		BeanFactory.clear();
		RouteNodeRegistry.clear();
		ConnectionHandler.destroy();
		
		Introspector.flushCaches();
		System.gc();
		
		log.info("====== Javaosc Framework flushing cache ======");
	}

	public void contextInitialized(ServletContextEvent event) {
		
		long initTime = System.currentTimeMillis();
		
		ServletContext sc = event.getServletContext();
		
//		String logbackFile =sc.getInitParameter("logBackConfigLocation");
//		if(StringUtil.isNotBlank(logbackFile)){
//			Logba.load(PathUtil.getClassPath() + logbackFile);
//		}
		
		Configuration.load(sc.getInitParameter(ProperConstant.CONFIG_FILE_NAME));
		
		BeanFactory.initKeywords();
		
		AnnotationScaner annotationObject = new AnnotationScaner();
		annotationObject.load();
		annotationObject = null;
		
//		ConnectionHandler.init();
		
		log.debug("Bean factory: {}", BeanFactory.beanMap);
		
		initTime = System.currentTimeMillis() - initTime;
		
		log.info("====== Javaosc Framework startup in {} ms ======", initTime);
		
		System.gc();
	}

//	private static void load (String logbackFileLocation) throws IOException, JoranException{  
//        LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();  
//          
//        File externalConfigFile = new File(logbackFileLocation);  
//        if(!externalConfigFile.exists()){  
//            throw new IOException("Logback External Config File Parameter does not reference a file that exists");  
//        }else{  
//            if(!externalConfigFile.isFile()){  
//                throw new IOException("Logback External Config File Parameter exists, but does not reference a file");  
//            }else{  
//                if(!externalConfigFile.canRead()){  
//                    throw new IOException("Logback External Config File exists and is a file, but cannot be read.");  
//                }else{  
//                    JoranConfigurator configurator = new JoranConfigurator();  
//                    configurator.setContext(lc);  
//                    lc.reset();  
//                    configurator.doConfigure(logbackFileLocation);  
//                    StatusPrinter.printInCaseOfErrorsOrWarnings(lc);  
//                }  
//            }     
//        }  
//    }  

}
