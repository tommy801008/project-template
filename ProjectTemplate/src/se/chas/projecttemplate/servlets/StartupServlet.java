package se.chas.projecttemplate.servlets;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * Servlet implementation class StartupServlet
 */
//@WebServlet("/StartupServlet")
public class StartupServlet extends BaseServlet {
	Logger logger = Logger.getLogger(StartupServlet.class.getName());
	
	
	private static final long serialVersionUID = 1L;
    public static String serverPath;
    public static String webInfPath;
    public static String classesPath; // ends with '/'
    public static String appConfigFilePath;
    public static String serverContext;
    public static String metaInf;
    public static String CATALINA_HOME = System.getProperty("catalina.base");
    
	/**
     * @see HttpServlet#HttpServlet()
     */
    public StartupServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	
	public void init() {
		serverPath = getServletContext().getRealPath("/");
		classesPath = serverPath + "WEB-INF" + File.separator + "classes" + File.separator;
		webInfPath = serverPath + "WEB-INF" + File.separator;
		metaInf = webInfPath + File.separator + "classes" + File.separator + "META-INF" + File.separator;
		appConfigFilePath = classesPath + "AppConfigPropertyFiles" + File.separator;
		serverContext = getServletContext().getContextPath() + "/";
		
		
	}
	
	public void destroy() {
		
	}
	
	
}
