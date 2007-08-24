package com.extjs.serverside.table;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TableDataRequest {

	HttpServletRequest request;

	HttpServletResponse response;

	PrintWriter out;

	boolean inRow;

	int cellPosition;

	TableColumn[] columns;

	public TableDataRequest(HttpServletRequest request,
			HttpServletResponse response, TableColumn[] columns) {
		super();
		this.request = request;
		this.response = response;
		this.columns = columns;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public Integer getStart() {
		String value = request.getParameter("start");
		if (value != null) {
			return Integer.valueOf(value);
		}
		return null;
	}

	public Integer getLimit() {
		String value = request.getParameter("limit");
		if (value != null) {
			return Integer.valueOf(value);
		}
		return null;
	}

	public void startResponse(Integer size) throws IOException {
		if (out == null) {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/xml");
			out = response.getWriter();
		}
		out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		out.println("<table>");
		if (size != null) {
			out.println("  <size>" + size + "</size>");
		}
		inRow = false;
	}

	public void startRow() {
		if (inRow) {
			out.println(" </row>");
		}
		out.println(" <row>");
		inRow = true;
		cellPosition = 0;
	}

	public void writeCell(Object value) {

		String tagName = Utils.getDataIndex(columns, cellPosition);
		cellPosition++;
		out.println("  <" + tagName + ">" + value + "</" + tagName + ">");
	}
	
	public void writeCustomValue(String name,Object value) {
		out.println("  <"+name+">"+value+"</"+ name+">");
	}

	public void endResponse() {
		if (inRow) {
			out.println(" </row>");
			inRow = false;
		}
		out.println("</table>");
	}


}
