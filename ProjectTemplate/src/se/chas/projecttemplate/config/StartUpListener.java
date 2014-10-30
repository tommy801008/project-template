package se.chas.projecttemplate.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

public class StartUpListener implements ServletContextListener {
	Logger logger = Logger.getLogger(StartUpListener.class.getName());
	
	public static String serverPath;
	
	  private ServletContext context = null;

	  /*This method is invoked when the Web Application has been removed 
	  and is no longer able to accept requests
	  */

	  public void contextDestroyed(ServletContextEvent event) {
			    System.out.println("Application Removed " + context.getServletContextName());
			    
			    this.context = null;

	  }


	  //This method is invoked when the Web Application
	  //is ready to service requests

	  public void contextInitialized(ServletContextEvent event)
	  {
	    this.context = event.getServletContext();
	    serverPath =  event.getServletContext().getRealPath("/");
	    System.out.println(serverPath);
	    //Output a simple message to the server's console
	    System.out.println("Application Ready");
	    
	  }
}
