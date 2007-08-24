package com.extjs.serverside;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.extjs.serverside.form.FormHandler;
import com.extjs.serverside.table.TableHandler;

/**
 * Servlet implementation class for Servlet: ControlServlet
 *
 * @web.servlet
 *   name="ControlServlet"
 *   display-name="ControlServlet" 
 *   description="This servlet handle the routing of request to various grid/form handlers for extjs" 
 *
 * @web.servlet-mapping
 *   url-pattern="/ControlServlet"
 *  
 * @web.servlet-mapping
 *   url-pattern="/extjs/*"
 *  
 */
 public class ControlServlet extends javax.servlet.http.HttpServlet {

	 Map<String, Handler> handlers = new HashMap<String, Handler>();
	 
	 
	 @Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		String initParameter = config.getInitParameter("handlers");
		if (initParameter == null || initParameter.trim().length() == 0) {
			// set up the default handlers
			handlers.put("table", new TableHandler());
			handlers.put("form", new FormHandler());
		} else {
			String[] handlerList = initParameter.trim().split("[;,]");
			for (int i=0;i<handlerList.length;i++) {
				String[] handlerDefinition = handlerList[i].split(":");
				if (handlerDefinition.length<3) {
					throw new ServletException("handler not correctly defined:'"+handlerList[i]+"', the format is {prefix}:{handler type}:{handler parameter}");
				}
				handlers.put(handlerDefinition[0].trim(), createHandler(handlerDefinition[1].trim(), handlerDefinition[2].trim()));
			}
		}

	}
	 
	
	
	private Handler createHandler(String type, String initValue) throws ServletException {
		if ("table".equalsIgnoreCase(type)) {
			return new TableHandler(initValue);
		}
		if ("form".equalsIgnoreCase(type)) {
			return new FormHandler(initValue);
		}
		throw new ServletException("unknown handler type:'"+type+"', supported values:'form', 'table'");
	}



	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		System.err.println("request UIR:"+request.getRequestURI());
		// /ext/table/<table name>/....
		String[] paths = request.getRequestURI().split("/");
		// paths[0] = ""
		// paths[1] = ext
		// paths[2] = table
		// paths[3] = <table name>
		
		Handler handler = handlers.get(paths[2]);
		if (handler!=null) {
			String rootPath = "/" + paths[1] + "/"+paths[2]+"/"+paths[3];
			
			handler.handleRequest(rootPath, paths[3], request, response);
		}
		
	}  	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.err.println("request SUIR:"+request.getRequestURI());

	}   	  	    
}