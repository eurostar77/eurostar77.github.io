package com.bueffeltier.ui.webapp.content.action;

import javax.servlet.http.HttpServletRequest;

import com.bueffeltier.data.jdbc.ElementJDBCFlat;
import com.bueffeltier.ui.webapp.RedirectDataService;

/**
 *
 * @author Sven Guderjahn <sven.guderjahn@bueffeltier.com>
 */
public abstract class AbstractAction implements Action
{
	RedirectDataService redirectDataService = RedirectDataService.getInstance();

	public AbstractAction()
	{

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

	public void
	    forwardViewData(HttpServletRequest request, String key, Object value)
	{
		redirectDataService.addData(request, key, value);
	}

	@Override
	public abstract void execute(HttpServletRequest request);

	@Override
	public abstract void doAjaxAction(HttpServletRequest request);
}
