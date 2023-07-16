package com.bueffeltier.ui.webapp.content.action;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Sven Guderjahn <sven.guderjahn@bueffeltier.com>
 */
public interface Action
{
//	public Action getInstance();

	public void execute(HttpServletRequest request);

	public void executeAjax(HttpServletRequest request);

//	public String getSubActionName(HttpServletRequest request);
}
