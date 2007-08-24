package com.extjs.serverside.table;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.extjs.serverside.Handler;

public class TableHandler extends Handler {

	
	Map<String, Table> tableMap = new HashMap<String, Table>();
	

	String packagePrefix;
	
	public TableHandler() {
		packagePrefix = "com.extjs.serverside.sample.table.";
	}

	public TableHandler(String packagePrefix) {
		if (!packagePrefix.endsWith(".")) {
			this.packagePrefix = packagePrefix + '.';
		} else {
			this.packagePrefix = packagePrefix;
		}
	}
	
	
	synchronized Table getTable(String tableName) {
		Table t = tableMap.get(tableName);
		if (t==null) {
			Class cls;
			try {
				cls = Class.forName(packagePrefix+tableName);
				t = (Table) cls.newInstance();
				tableMap.put(tableName, t);
			} catch (ClassNotFoundException e) {
				throw new RuntimeException(e);
			} catch (InstantiationException e) {
				throw new RuntimeException(e);
			} catch (IllegalAccessException e) {
				throw new RuntimeException(e);
			}
		}
		return t;
	}

	public void handleRequest(String basePath, String tableName, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Table table = getTable(tableName);
		if (request.getRequestURI().endsWith(".js")) {
			request.setAttribute("tableName", tableName);
			request.setAttribute("table", table);
			request.setAttribute("basePath", basePath);
			request.getRequestDispatcher("/extjs/table-js.jsp").forward(request, response);
			return;
		}
		if (request.getRequestURI().endsWith(".xml")) {
			TableDataRequest dataRequest = new TableDataRequest(request, response, table.getColumns());
			table.renderData(dataRequest);
			return;
			
		}
		response.getWriter().println("table handler called for table:"+table+", uri:"+request.getRequestURI());
	}

}
