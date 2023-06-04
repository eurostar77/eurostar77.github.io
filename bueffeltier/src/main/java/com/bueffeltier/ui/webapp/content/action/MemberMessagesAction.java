//package com.bueffeltier.ui.webapp.content.action;
//
//import static j2html.TagCreator.h1;
//
//import javax.servlet.http.HttpServletRequest;
//
//import j2html.tags.Tag;
//
///**
// *
// * @author Sven Guderjahn <sven.guderjahn@bueffeltier.com>
// */
//public class MemberMessagesAction extends AbstractAction
//{
//
//	private static MemberMessagesAction instance;
//
//	private MemberMessagesAction()
//	{
//		super();
//	}
//
//	public static MemberMessagesAction getInstance()
//	{
//		if (instance == null)
//		{
//			instance = new MemberMessagesAction();
//		}
//		return instance;
//	}
//
//	@Override
//	public void execute(HttpServletRequest request)
//	{
//		throw new UnsupportedOperationException("Not supported yet.");
//	}
//
//	@Override
//	public void doAjaxAction(HttpServletRequest request)
//	{
//		throw new UnsupportedOperationException("Not supported yet.");
//	}
//
//	@Override
//	public Tag writeHtml() throws Exception
//	{
//		return form(contentId, threadId, h1("Messages"));
//		// messages
//	}
//
//	@Override
//	public Tag writeHtmlControls()
//	{
//		throw new UnsupportedOperationException("Not supported yet.");
//	}
//
//	@Override
//	public String writeCss()
//	{
//		throw new UnsupportedOperationException("Not supported yet.");
//	}
//}
