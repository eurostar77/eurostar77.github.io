package com.bueffeltier.ui.webapp.content.view;

import static j2html.TagCreator.*;

import javax.servlet.http.HttpServletRequest;

import com.bueffeltier.crosscutting.AppPropertyService;
import com.bueffeltier.data.jdbc.ElementJDBCFlat;
import com.bueffeltier.logic.foundation.AuthPermissionService;

import j2html.tags.DomContent;

/**
 *
 * @author Sven Guderjahn <sven.guderjahn@bueffeltier.com>
 */
public class RegisterConfirmView extends AbstractView
{

	private static RegisterConfirmView instance;

	private AuthPermissionService authPermissionService = AuthPermissionService
	    .getInstance();

	private AppPropertyService appPropertyService = AppPropertyService
	    .getInstance();

	private RegisterConfirmView()
	{
		super();
	}

	public static RegisterConfirmView getInstance()
	{
		if (instance == null)
		{
			instance = new RegisterConfirmView();
		}
		return instance;
	}

	@Override
	public DomContent
	    writeHtml(ElementJDBCFlat element, HttpServletRequest request)
	        throws Exception
	{
		String activationKey = request.getQueryString()
		    .substring(9, request.getQueryString().length());
		// todo: wenn sich der seitenname ändert, key trotzdem lesbar machen.

		boolean activationSucceeded = authPermissionService
		    .activateAccount(request, activationKey);

		if (activationSucceeded)
		{
			return div(
			    p(appPropertyService.getPageContentRegisterConfirmedText()),
			    // todo: atomic design element: homeLink?
			    p(
			        a("Homepage").attr(
			            "href", appPropertyService.getServletHomePagePath()
			        )
			    ),
			    p(
			        a("Login").attr(
			            "href", appPropertyService.getServletLoginPagePath()
			        )
			    )
			);
		} else
		{
			return div(
			    p(
			        "Es liegen keine Registrationsdaten vor. Bitte registrieren Sie sich erneut!"
			    )
			);
		}
	}

	@Override
	public DomContent writeHtmlControls()
	{
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public String writeCss()
	{
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	protected void setCssId(String containerTagClassName)
	{
		// TODO Auto-generated method stub

	}

	@Override
	protected void setContainerTag()
	{
		// TODO Auto-generated method stub

	}
}
