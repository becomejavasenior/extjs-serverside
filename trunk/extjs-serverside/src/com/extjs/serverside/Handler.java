package com.extjs.serverside;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class Handler {

	public abstract void handleRequest(String basePath, String objectName,
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException;

}