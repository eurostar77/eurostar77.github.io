//package com.bueffeltier.ui.webapp.content.view;
//
//import static j2html.TagCreator.*;
//
//import java.util.ArrayList;
//
//import javax.servlet.http.HttpServletRequest;
//
//import com.bueffeltier.crosscutting.AppPropertyService;
//import com.bueffeltier.data.hibernate.entity.Page;
//import com.bueffeltier.data.jdbc.ElementJDBCFlat;
//import com.bueffeltier.logic.foundation.pagetree.TreeOfPages;
//
//import j2html.tags.DomContent;
//
///**
// * Stellt Navigations-Menüs zur Verfügung. todo: Bietet URL-Rewritung todo:
// * Bietet verschiedene Navigations-Typen. todo: Bietet Menüs zwischen
// * verschiedenen Levels. todo: Erfasst den jeweilig aktuellen
// * Navigations-Ausgangspunkt (akt. Seite).
// * 
// * @author Sven Guderjahn <sven.guderjahn@bueffeltier.com>
// */
//public class NavigationView extends AbstractView
//{
//
//	private static NavigationView instance;
//
//	private NavigationView()
//	{
//		super();
//	}
//
//	public static NavigationView getInstance()
//	{
//		if (instance == null)
//		{
//			instance = new NavigationView();
//		}
//		return instance;
//	}
//
//	@Override
//	public DomContent
//	    writeHtml(ElementJDBCFlat element, HttpServletRequest request)
//	{
//
//		TreeOfPages treeOfPages = new TreeOfPages();
//		ArrayList<Page> pageList = treeOfPages
//		    .getPageTree(1, 3, true, this.getRequestPermission());
//		// todo: Gesprerrte Seiten nicht anzeigen (Nav, Permission, Published)
//		// -> Anforderung TreeOfPages!
//
//		return div().with(
//		    nav(),
//		    each(
//		        pageList,
//		        page -> ul(
//		            li(
//		                a(page.getHtmlTitle()).withHref(
//		                    AppPropertyService.servlet.getServletContext()
//		                        .getContextPath()
//		                        + AppPropertyService.getServletPath() + "/"
//		                        + page.getHtmlTitle()
//							// todo: urlRewriting:
//							// .append(";jsessionid=").append(requestController.request.getSession(false).getId());
//							// todo: kompletten Link von
//							// woanders beziehen? pageTree?
//							// todo: ggf. nach wahl shift
//							// einfügen
//							// todo: pageList kann auch
//							// händisch erstellt werden.
//		                )// .withName(page.getHtmlTitle()).withTitle(page.getHtmlTitle())
//		            )
//		        )
//		    )
//		);
//
////        // todo: wenn eingaben level 0 bis 0 sind, dann exception
////        StringBuilder navString = new StringBuilder();
////        String passPath
////                = ServletProperties.getHostName()
////                + ServletProperties.getServletPath()
////                + "/"
////                + startNode.getItem().getHtmlTitle();
////        int passLevel = 0; // ist immer null, da der Pfad in der nächsten Methode immer von der Root beginnend geschrieben werden muss.
////
////        navString.append("<nav>");
////
////        // todo: wenn root existiert!
////        this.getHtmlNavigationBody(startNode, navString, startLevel, stopLevel, passLevel, passPath);
////
//////        html.append("</nav>\n");
////        navString.append("</div></nav>\n");
////
////        return navString.toString();
////
////        // URL Rewriting
////        //
////        //
////        // Wähle Menü-Type:
////        // Greife auf das Link-Array zu.
////        //
////        return div().with(
////                p("Test HTML")
////        );
//	}
//
//	@Override
//	public DomContent writeHtmlControls()
//	{
//		// Wähle Nav-Typ aus: (Dropdown)
//
//		// Wähle die Start-Seite aus einem Baum oder gib vorerst die Seiten-Id
//		// ein.
//		//
//		// Lege das Level fest.
//		//
//		return div().with(
//		    h3("Seiten-Navigation"), p("Wähle Start-Id: 1"),
//		    p("Wähle das Level: 3")
//		);
//
//	}
//
//	@Override
//	public String writeCss()
//	{
//		throw new UnsupportedOperationException("Not supported yet.");
//	}
//
//	@Override
//	protected void setCssId(String containerTagClassName)
//	{
//		// TODO Auto-generated method stub
//	}
//
//	@Override
//	protected void setContainerTag()
//	{
//		// TODO Auto-generated method stub
//	}
//}
