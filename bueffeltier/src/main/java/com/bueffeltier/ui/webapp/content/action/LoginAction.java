package com.bueffeltier.ui.webapp.content.action;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import com.bueffeltier.logic.foundation.AuthPermissionService;

/**
 *
 * @author Sven Guderjahn <sven.guderjahn@bueffeltier.com>
 */
public class LoginAction extends AbstractAction
{
	private static LoginAction instance;

	private AuthPermissionService authPermissionService = AuthPermissionService
	    .getInstance();

	private LoginAction(HttpServletRequest request)
	{
		super();
	}

	public static LoginAction getInstance(HttpServletRequest request)
	{
		if (instance == null)
		{
			instance = new LoginAction(request);
		}
		return instance;
	}

	@Override
	public void execute(HttpServletRequest request)
	{
		Enumeration<String> parameterNames = request.getParameterNames();

		while (parameterNames.hasMoreElements())
		{
			String actionParameterName = parameterNames.nextElement()
			    .toString();
			switch (actionParameterName) {

			case "login":
				doLogin(request);
				break;

//			case "logout":
//				doLogout(request);
//				break;
			}
		}
	}

	private void doLogin(HttpServletRequest request)
	{
		String loginUsername = request.getParameter("username");
		String loginPassword = request.getParameter("password");

		authPermissionService.login(request, loginUsername, loginPassword);

		forwardToPage(request, "/");
	}

	private void doLogout(HttpServletRequest request)
	{
		authPermissionService.logout(request);

		forwardToPage(request, "/");

//        // todo: beim ausloggen wird die session invalidiert, prozesse gesichert.
//        // Was ist bei zeitlichem Ablauf der Session mit der Sicherung der Daten?
//        if (session != null)
//        {
//            session.invalidate();
//
//            Cookie[] cookies = request.getCookies();
//            if (cookies != null)
//            {
//                for (Cookie cookie : cookies)
//                {
//                    //                        cookie.setValue("");
//                    //                        cookie.setPath("/");
//                    cookie.setMaxAge(0);
//                    response.addCookie(cookie);
//                }
//            }
//
//            if (doUrlRewriting)
//            {
//                //todo: url vor dem redirect nicht mehr encodieren!
//            }
//
//        } else
//        {
//
//            // Session ist null - invalidate nicht nötig/möglich!
//        }
	}

	@Override
	public void doAjaxAction(HttpServletRequest request)
	{
		// TODO Auto-generated method stub

	}

}
