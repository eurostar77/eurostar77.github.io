package com.bueffeltier.ui.webapp.content;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.bueffeltier.data.jdbc.ElementJDBCFlat;
import com.bueffeltier.ui.webapp.content.view.ArticleFeederView;
import com.bueffeltier.ui.webapp.content.view.ChooseContentTypeView;
import com.bueffeltier.ui.webapp.content.view.ContactView;
import com.bueffeltier.ui.webapp.content.view.CreateFlipcardsView;
import com.bueffeltier.ui.webapp.content.view.EditPageView;
import com.bueffeltier.ui.webapp.content.view.ElementsView;
import com.bueffeltier.ui.webapp.content.view.FlipCardStartPageView;
import com.bueffeltier.ui.webapp.content.view.FlipCardView;
import com.bueffeltier.ui.webapp.content.view.FooterView;
import com.bueffeltier.ui.webapp.content.view.HeaderView;
import com.bueffeltier.ui.webapp.content.view.HtmlView;
import com.bueffeltier.ui.webapp.content.view.Http404View;
import com.bueffeltier.ui.webapp.content.view.LoginMenuView;
import com.bueffeltier.ui.webapp.content.view.LoginView;
import com.bueffeltier.ui.webapp.content.view.MemberAccountSettingsView;
import com.bueffeltier.ui.webapp.content.view.MemberAreaView;
import com.bueffeltier.ui.webapp.content.view.MemberLearningSettingsView;
import com.bueffeltier.ui.webapp.content.view.MemberMessagesView;
import com.bueffeltier.ui.webapp.content.view.MemberNotesView;
import com.bueffeltier.ui.webapp.content.view.MottoView;
import com.bueffeltier.ui.webapp.content.view.RegisterConfirmView;
import com.bueffeltier.ui.webapp.content.view.RegisterSubmittedView;
import com.bueffeltier.ui.webapp.content.view.RegisterView;
import com.bueffeltier.ui.webapp.content.view.RessourcesView;
import com.bueffeltier.ui.webapp.content.view.SettingsView;
import com.bueffeltier.ui.webapp.content.view.SiteStructureView;
import com.bueffeltier.ui.webapp.content.view.TextViewHandler;
import com.bueffeltier.ui.webapp.content.view.UserAdminView;
import com.bueffeltier.ui.webapp.content.view.ViewHandler;

/**
 * Über diese Klasse erfolgt der Zugriff auf die ContentTypeHandler Objekte.
 * 
 * @author Sven Guderjahn <sven.guderjahn@bueffeltier.com>
 */
public class ViewRegistry
{
	private static ViewRegistry instance = null;

	private ViewRegistry()
	{

	}

	public static ViewRegistry getInstance()
	{
		if (instance == null)
		{
			instance = new ViewRegistry();
		}
		return instance;
	}

	public ViewHandler
	    getViewHandler(ElementJDBCFlat element, HttpServletRequest request)
	{
		switch (element.getType()) {

		case "TEXT":
			return TextViewHandler.getInstance();
		case "SITE_STRUCTURE":
			return SiteStructureView.getInstance();
		case "LOGIN":
			return LoginView.getInstance();
		case "EDIT_PAGE":
			return EditPageView.getInstance();
		case "EDIT_ARTICLE":
//			return EditArticleSettingsView.getInstance();
		case "REGISTER":
			return RegisterView.getInstance();
		case "FOOTER":
			return FooterView.getInstance();
		case "NAVIGATION":
//			return NavigationView.getInstance();
		case "EDIT_ARTICLE_ELEMENTS":
//			return EditArticleElementsView.getInstance();
		case "EDIT_CONTENT_ELEMENT":
//			return EditContentElementView.getInstance();
		case "HTML":
			return HtmlView.getInstance();
		case "CHOOSE_CONTENT_TYPE":
			return ChooseContentTypeView.getInstance();
		case "ARTICLE_FEEDER":
			return ArticleFeederView.getInstance();
		case "LOGIN_MENU":
			return LoginMenuView.getInstance();
		// Mitglieder-Bereich Übersicht
		case "MEMBER":
			return MemberAreaView.getInstance(); // Mitglieder-Bereich
		case "CONTACT":
			return ContactView.getInstance();
		case "MOTTO":
			return MottoView.getInstance();
		case "RESSOURCES":
			return RessourcesView.getInstance(request);
		case "LEARNING_SETTINGS":
			return MemberLearningSettingsView.getInstance();
		case "NOTES":
			return MemberNotesView.getInstance();
		case "MESSAGES":
			return MemberMessagesView.getInstance();
		case "MEMBER_ACCOUNT_SETTINGS":
			return MemberAccountSettingsView.getInstance();
		case "APP_SETTINGS":
			return SettingsView.getInstance();
		case "USER_ADMIN":
			return UserAdminView.getInstance();
		case "HEADER":
			return HeaderView.getInstance(element, request);
		case "FLIPCARD":
			return FlipCardView.getInstance();
		case "REGISTER_SUBMITTED":
			return RegisterSubmittedView.getInstance();
		case "REGISTER_CONFIRM":
			return RegisterConfirmView.getInstance();
		case "HTTP404":
			return Http404View.getInstance();
		case "FLIPCARD_START_PAGE":
			return FlipCardStartPageView.getInstance();
		case "CREATE_FLASHCARDS":
			return CreateFlipcardsView.getInstance();
		case "ELEMENTS":
			return ElementsView.getInstance();
//            case "":
//                return xxx;
//            case "":
//                return xxx;
//            todo:
//            default: throw new Exception("Content-Type does not exist!");
		}
		return null;
	}

	/**
	 *
	 * @return
	 */
	public static List<String> types()
	{
		List<String> types = Arrays.asList(
		    "TEXT", //
		    "SITE_STRUCTURE", //
		    "LOGIN", //
		    "EDIT_PAGE", //
		    "EDIT_ARTICLE", //
		    "REGISTER", //
		    "FOOTER", //
		    "NAVIGATION", //
		    "EDIT_ARTICLE_ELEMENTS", //
		    "EDIT_CONTENT_ELEMENT", //
		    "HTML", //
		    "CHOOSE_CONTENT_TYPE", //
		    "ARTICLE_FEEDER", //
		    "LOGIN_MENU", //
		    "MEMBER", //
		    "CONTACT", //
		    "RESSOURCES", //
		    "LEARNING_SETTINGS", //
		    "NOTES", "MESSAGES", //
		    "MEMBER_ACCOUNT_SETTINGS", //
		    "APP_SETTINGS", //
		    "USER_ADMIN", //
		    "HEADER", //
		    "FLIPCARD", //
		    "REGISTER_SUBMITTED", //
		    "REGISTER_CONFIRM", //
		    "HTTP404", //
		    "FLIPCARD_START_PAGE", //
		    "CREATE_FLASHCARDS", //
		    "ELEMENTS"
		);

//        List<String> types =
//        {
//            "PAGE_STRUCTURE",
//            "LOGIN",
//            "EDIT_PAGE",
//            "EDIT_ARTICLE",
//            "REGISTER",
//            "FOOTER",
//            "NAVIGATION",
//            "EDIT_ARTICLE_ELEMENTS"
//        };
		return types;
	}
}
