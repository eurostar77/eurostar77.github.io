//package com.bueffeltier.ui.webapp.content.action;
//
//import java.util.Enumeration;
//
//import javax.servlet.http.HttpServletRequest;
//
///**
// * Stellt Navigations-Menüs zur Verfügung. todo: Bietet URL-Rewritung todo:
// * Bietet verschiedene Navigations-Typen. todo: Bietet Menüs zwischen
// * verschiedenen Levels. todo: Erfasst den jeweilig aktuellen
// * Navigations-Ausgangspunkt (akt. Seite).
// * 
// * @author Sven Guderjahn <sven.guderjahn@bueffeltier.com>
// */
//public class NavigationAction extends AbstractAction
//{
//
//	private static NavigationAction instance;
//
//	private NavigationAction()
//	{
//		super();
//	}
//
//	public static NavigationAction getInstance()
//	{
//		if (instance == null)
//		{
//			instance = new NavigationAction();
//		}
//		return instance;
//	}
//
//	@Override
//	public void execute(HttpServletRequest request)
//	{
////        log.add(this, "doAction()");
//		Enumeration parameterNames = this.getRequestParameterNames();
//		while (parameterNames.hasMoreElements())
//		{
//			String actionParameterName = parameterNames.nextElement()
//					.toString();
//			switch (actionParameterName) {
//			case "test":
//				this.setForwardingPage("xxxx"); // todo: hier id eingeben.
//				break;
//			}
//		}
//		doPageLoad();
//	}
//
//	@Override
//	public void doAjaxAction(HttpServletRequest request)
//	{
//		throw new UnsupportedOperationException("Not supported yet.");
//	}
//}
