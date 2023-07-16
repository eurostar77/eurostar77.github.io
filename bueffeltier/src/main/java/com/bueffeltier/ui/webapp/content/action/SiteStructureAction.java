package com.bueffeltier.ui.webapp.content.action;

import javax.servlet.http.HttpServletRequest;

import com.bueffeltier.logic.foundation.pagetree.SiteRepository;

/**
 *
 * @author Sven Guderjahn <sven.guderjahn@bueffeltier.com>
 */
public class SiteStructureAction extends AbstractAction
{
	private boolean showMsgBox;

	private static SiteStructureAction instance;

	private SiteRepository siteRepository = SiteRepository.getInstance();

	private SiteStructureAction()
	{
		super();
	}

	public static SiteStructureAction getInstance(HttpServletRequest request)
	{
		if (instance == null)
		{
			instance = new SiteStructureAction();
		}
		return instance;
	}

	@Override
	public void execute(HttpServletRequest request)
	{
		String editPage = request.getParameter("editPage");
		if (editPage != null)
		{
			String pageEditIdString = request.getParameter("editPage");
			Long pageEditId = Long.parseLong(pageEditIdString);
			forwardViewData(request, "pageId", pageEditId.toString());
			forwardToPage(request, "/edit-page");
		}

		String newPage = request.getParameter("newPage");
		if (newPage != null)
		{
			forwardToPage(request, "/edit-page");
		}

	}

	@Override
	public void executeAjax(HttpServletRequest request)
	{
		throw new UnsupportedOperationException("Not supported yet.");
	}

	public String pageContextStyle(int pageLevel)
	{
		StringBuilder style = new StringBuilder();
		style.append("padding-left:").append(Integer.toString(pageLevel * 20))
		    .append("px");
		return style.toString();
	}

}
