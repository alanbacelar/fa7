package br.com.trabalho.listener;

import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;

@WebListener
public class XHTMLRequestListener implements ServletRequestListener {
	HashMap<String, Integer> mapXHTMLCounters = new HashMap<String, Integer>();
	int countXHTMLRequest = 0;

	@Override
	public void requestDestroyed(ServletRequestEvent event) {}

	@SuppressWarnings("unchecked")
	@Override
	public void requestInitialized(ServletRequestEvent event) {
		ServletContext context = event.getServletContext();
		ServletRequest request = event.getServletRequest();

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		
		if (context.getAttribute("mapCounters") != null) {
			mapXHTMLCounters = (HashMap<String, Integer>) context.getAttribute("mapCounters");
		}
		
		if (httpRequest.getRequestURI().endsWith(".xhtml")) {
			int count = 0;
			if (mapXHTMLCounters.containsKey(httpRequest.getRequestURI())) {
				count = mapXHTMLCounters.get(httpRequest.getRequestURI());
			}
			
			count++;
			countXHTMLRequest++;
			mapXHTMLCounters.put(httpRequest.getRequestURI(), count);
		}
		
		

		System.out.println("countXHTMLRequest:" + countXHTMLRequest);
		System.out.println("mapXHTMLCounters: " + mapXHTMLCounters.toString());
		context.setAttribute("countXHTMLRequest", countXHTMLRequest);
		context.setAttribute("mapXHTMLCounters", mapXHTMLCounters);
	}

}
