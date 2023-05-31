package com.bueffeltier.ui.webapp.content.action;

import javax.servlet.http.HttpServletRequest;

import com.bueffeltier.data.jdbc.ElementJDBCFlat;
import com.bueffeltier.ui.webapp.RequestService;

/**
 *
 * @author Sven Guderjahn <sven.guderjahn@bueffeltier.com>
 */
abstract class AbstractAction implements Action
{

	private static RequestService viewService = RequestService.getInstance();

//	private String containerTag = "form";
//	private String containerTagClassName = "";
//	private static boolean useStandardForm = true;

	public AbstractAction()
	{

	}

	public String getRequestParameter(
			HttpServletRequest request,
			String parameterName
	)
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

	@Override
	public abstract void execute(HttpServletRequest request);

	@Override
	public abstract void doAjaxAction(HttpServletRequest request);

//	@Override
//	public void doPageLoad(HttpServletRequest request)
//	{
//		FileWriterWithEncoding wird die seite geladen?
//	}

	/*
	 * Überschreibt den ContentHandler standardmäßig umklammernden Form-Tag und
	 * ersetzt ihn durch einen im HTML frei positionierbaren Form-Tag.
	 */
//	public static ContainerTag<?>
//			form(int contentId, int threadId, DomContent... dc)
//	{
//		useStandardForm = false;
//		return new ContainerTag<>("form").attr("method=\"post\"")
//				.attr("autocomplete", "on")
//				.with(
//						input().withType("hidden").withName("content")
//								.withValue(Integer.toString(contentId))
//				)
//				.with(
//						input().withType("hidden").withName("thread")
//								.withValue(Integer.toString(threadId))
//				).with(dc);
//	}
}
