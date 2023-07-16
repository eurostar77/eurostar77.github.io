package com.bueffeltier.ui.webapp.content.action;

import javax.servlet.http.HttpServletRequest;

import com.bueffeltier.data.jdbc.ElementJDBCFlat;
import com.bueffeltier.ui.webapp.ViewDataService;

/**
 *
 * @author Sven Guderjahn <sven.guderjahn@bueffeltier.com>
 */
public abstract class AbstractAction implements Action
{
	private ViewDataService viewDataService = ViewDataService.getInstance();

	public AbstractAction()
	{
		//
	}

	public String
	    getRequestParameter(HttpServletRequest request, String parameterName)
	{
		return request.getParameter(parameterName);
	}

//	public int getRequestPermission(HttpServletRequest request)
//	{
//		return request.getRequestPermission();
//	}

	public long getContentId(ElementJDBCFlat element)
	{
		return element.getId();
	}

	public void forwardToPage(HttpServletRequest request, String path)
	{
		request.setAttribute("responsePath", path);
		// TODO sveng 01.12.2022: besser eine seitenId angeben.
	}

	// TODO sveng 25.06.2023: viewDataService umbenennen und umbauen.
	// Abfragen, von welcher Seite das request kommt?
	// Umbau auf Generische Typen
	// Service umbenennen
	// Service nicht nur bei Redirect verwenden (requestAttribute dafür
	// auflösen)
	public void
	    forwardViewData(HttpServletRequest request, String key, String value)
	{
		viewDataService.addForwardingData(request, key, value);
	}

//	protected void executeSubAction(
//	    HttpServletRequest request,
//	    String actionClassName,
//	    String actionParameter
//	)
//	{
//		if (StringUtils.isNotBlank(actionClassName))
//		{
//			request.setAttribute("nextSubActionName", actionClassName);
//
//		} else
//		{
//			request.removeAttribute("nextSubActionName");
//		}
//	}

	@Override
	public abstract void execute(HttpServletRequest request);

	public void executeInternal(HttpServletRequest request) throws Exception
	{
		execute(request);
	}

	@Deprecated
	@Override
	public abstract void executeAjax(HttpServletRequest request);

	@Deprecated
	public void executeAjaxInternal(HttpServletRequest request) throws Exception
	{
		executeAjax(request);
	}
}
