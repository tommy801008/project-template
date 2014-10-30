package se.chas.projecttemplate.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.web.context.ConfigurableWebApplicationContext;
import org.springframework.web.context.ServletContextAware;


public class SpringInitializer implements ApplicationContextInitializer<ConfigurableWebApplicationContext>, ServletContextAware {
	
	Logger logger = Logger.getLogger(SpringInitializer.class.getName());
	
	private static final String SPRING_CONFIG = "GeneralSettings.properties";
	private static String pathToPropertyFiles;
	private String pathSpring;
	private String configSpring;
	
	public SpringInitializer() {

	}
	
	public void findServerPath() {
		
	}
	
    public void initialize(ConfigurableWebApplicationContext ctx) {
    	
    	logger.debug(this.getClass().getName() + " Entering Initialize");
    	
    	Properties startProperties = new Properties();
    	InputStream iStream;
    	
    	pathToPropertyFiles = ctx.getServletContext().getRealPath("/") + "WEB-INF" + File.separator + "classes" + File.separator + "AppConfigPropertyFiles";
    	pathSpring = pathToPropertyFiles + File.separator + SPRING_CONFIG;
    	logger.debug(this.getClass().getName() + " Spring Security Path to use : \n " + pathSpring);

		try {
			iStream = new FileInputStream(pathSpring);
			logger.debug(this.getClass().getName() + " Loading Property to determine which Spring Bean Definitions to use.");
			startProperties.load(iStream);
			logger.debug(this.getClass().getName() + " Loaded Property to determine which Spring Bean Definitions to use.");
			
			configSpring = startProperties.getProperty("auth.to.use");
			logger.debug(this.getClass().getName() + " Spring Definitions to use : " + configSpring);
			
		} catch (IOException e) {
			logger.debug(this.getClass().getName() + " IOException " + e);
		}
		
		logger.debug(this.getClass().getName() + " Setting spring bean definition file to : \n " + "WEB-INF" + File.separator + configSpring);
		
		ctx.setConfigLocation("WEB-INF" + File.separator + configSpring);

    }

	@Override
	public void setServletContext(ServletContext context) {
		pathToPropertyFiles = context.getContextPath();
	}
}
