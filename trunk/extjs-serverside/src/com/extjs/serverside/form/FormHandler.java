package com.extjs.serverside.form;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.extjs.serverside.Handler;

public class FormHandler extends Handler {

	String packagePrefix;

	public FormHandler() {
		packagePrefix = "com.extjs.serverside.sample.form.";
	}

	public FormHandler(String packagePrefix) {
		if (!packagePrefix.endsWith(".")) {
			this.packagePrefix = packagePrefix + '.';
		} else {
			this.packagePrefix = packagePrefix;
		}
	}

	synchronized Form getForm(String formName) {
		Class cls;
		try {
			cls = Class.forName(packagePrefix + formName);
			return (Form) cls.newInstance();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		} catch (InstantiationException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void handleRequest(String basePath, String objectName,
			HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Form form = getForm(objectName);
		if (request.getRequestURI().endsWith(".js")) {

			response.setContentType("application/x-javascript");
			response.setCharacterEncoding("utf-8");
			PrintWriter pw = response.getWriter();

			pw.println(form.renderComponent());
			pw.flush();
		}
	}

}
