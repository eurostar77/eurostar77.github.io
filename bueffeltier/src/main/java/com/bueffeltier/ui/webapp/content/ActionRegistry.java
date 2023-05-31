package com.bueffeltier.ui.webapp.content;

import java.util.HashMap;
import java.util.Map;

import com.bueffeltier.ui.webapp.content.action.Action;
import com.bueffeltier.ui.webapp.content.action.ArticleFeederAction;
import com.bueffeltier.ui.webapp.content.action.ChooseContentTypeAction;
import com.bueffeltier.ui.webapp.content.action.ContactAction;
import com.bueffeltier.ui.webapp.content.action.CreateFlashcardsAction;
import com.bueffeltier.ui.webapp.content.action.EditArticleElementsAction;
import com.bueffeltier.ui.webapp.content.action.EditArticleSettingsAction;
import com.bueffeltier.ui.webapp.content.action.EditContentElementAction;
import com.bueffeltier.ui.webapp.content.action.EditPageAction;
import com.bueffeltier.ui.webapp.content.action.FlipCardAction;
import com.bueffeltier.ui.webapp.content.action.FlipCardStartPageAction;
import com.bueffeltier.ui.webapp.content.action.FooterAction;
import com.bueffeltier.ui.webapp.content.action.HeaderAction;
import com.bueffeltier.ui.webapp.content.action.HtmlAction;
import com.bueffeltier.ui.webapp.content.action.Http404Action;
import com.bueffeltier.ui.webapp.content.action.LoginAction;
import com.bueffeltier.ui.webapp.content.action.LoginMenuAction;
import com.bueffeltier.ui.webapp.content.action.MemberAccountSettingsAction;
import com.bueffeltier.ui.webapp.content.action.MemberLearningSettingsActionHandler;
import com.bueffeltier.ui.webapp.content.action.MemberMessagesAction;
import com.bueffeltier.ui.webapp.content.action.MemberNotesAction;
import com.bueffeltier.ui.webapp.content.action.MemberPersonalDataAction;
import com.bueffeltier.ui.webapp.content.action.MottoAction;
import com.bueffeltier.ui.webapp.content.action.NavigationAction;
import com.bueffeltier.ui.webapp.content.action.RegisterAction;
import com.bueffeltier.ui.webapp.content.action.RegisterConfirmAction;
import com.bueffeltier.ui.webapp.content.action.RegisterSubmittedAction;
import com.bueffeltier.ui.webapp.content.action.RessourcesAction;
import com.bueffeltier.ui.webapp.content.action.SettingsAction;
import com.bueffeltier.ui.webapp.content.action.SiteStructureAction;
import com.bueffeltier.ui.webapp.content.action.TextAction;
import com.bueffeltier.ui.webapp.content.action.UserAdminActionr;

/**
 * Über diese Klasse erfolgt der Zugriff auf die ContentTypeHandler Objekte.
 * 
 * @author Sven Guderjahn <sven.guderjahn@bueffeltier.com>
 */
public class ActionRegistry
{
	private static ActionRegistry instance = null;

	private Map<String, Class<? extends Action>> actions;

	private ActionRegistry()
	{
		buildActionMap();
	}

	public static ActionRegistry getInstance()
	{
		if (instance == null)
		{
			instance = new ActionRegistry();
		}
		return instance;
	}

	public void
	    registerAction(String actionName, Class<? extends Action> actionClass)
	{
		actions.put(actionName, actionClass);
	}

	public Class<? extends Action> getActionClass(String actionName)
	{
		return actions.get(actionName);
	}

//	request.getParameter("content"); // TODO sveng 01.12.2022: content
// verschlüsselt und auch als enum?
//        default: throw new Exception("Content-Type does not exist!");
	private void buildActionMap()
	{
		actions = new HashMap<>();

		actions.put("TEXT", TextAction.class);
		actions.put("SITE_STRUCTURE", SiteStructureAction.class);
		actions.put("LOGIN", LoginAction.class);
		actions.put("EDIT_PAGE", EditPageAction.class);
		actions.put("EDIT_ARTICLE", EditArticleSettingsAction.class);
		actions.put("REGISTER", RegisterAction.class);
		actions.put("FOOTER", FooterAction.class);
		actions.put("NAVIGATION", NavigationAction.class);
		actions.put("EDIT_ARTICLE_ELEMENTS", EditArticleElementsAction.class);
		actions.put("EDIT_CONTENT_ELEMENT", EditContentElementAction.class);
		actions.put("HTML", HtmlAction.class);
		actions.put("CHOOSE_CONTENT_TYPE", ChooseContentTypeAction.class);
		actions.put("ARTICLE_FEEDER", ArticleFeederAction.class);
		actions.put("LOGIN_MENU", LoginMenuAction.class);
//		actions.put("SETTINGS", SettingsAction.class);
		actions.put("CONTACT", ContactAction.class);
		actions.put("MOTTO", MottoAction.class);
		actions.put("RESSOURCES", RessourcesAction.class);
		actions.put(
		    "LEARNING_SETTINGS", MemberLearningSettingsActionHandler.class
		);
		actions.put("NOTES", MemberNotesAction.class);
		actions.put("MESSAGES", MemberMessagesAction.class);
		actions.put("PERSONAL_DATA", MemberPersonalDataAction.class);
		actions.put("ACCOUNT_SETTINGS", MemberAccountSettingsAction.class);
		actions.put("APP_SETTINGS", SettingsAction.class);
		actions.put("USER_ADMIN", UserAdminActionr.class);
		actions.put("HEADER", HeaderAction.class);
		actions.put("FLIPCARD", FlipCardAction.class);
		actions.put("REGISTER_SUBMITTED", RegisterSubmittedAction.class);
		actions.put("REGISTER_CONFIRM", RegisterConfirmAction.class);
		actions.put("HTTP404", Http404Action.class);
		actions.put("FLIPCARD_START_PAGE", FlipCardStartPageAction.class);
		actions.put("CREATE_FLASHCARDS", CreateFlashcardsAction.class);
	}
}
