package com.bueffeltier.ui.webapp.content;

import java.util.HashMap;
import java.util.Map;

import com.bueffeltier.ui.webapp.content.action.Action;
import com.bueffeltier.ui.webapp.content.action.ArticleFeederAction;
import com.bueffeltier.ui.webapp.content.action.ContactAction;
import com.bueffeltier.ui.webapp.content.action.LessonEditorAction;
import com.bueffeltier.ui.webapp.content.action.PageEditorAction;
import com.bueffeltier.ui.webapp.content.action.FlipCardAction;
import com.bueffeltier.ui.webapp.content.action.CourseEditorAction;
import com.bueffeltier.ui.webapp.content.action.FooterAction;
import com.bueffeltier.ui.webapp.content.action.HeaderAction;
import com.bueffeltier.ui.webapp.content.action.HtmlAction;
import com.bueffeltier.ui.webapp.content.action.Http404Action;
import com.bueffeltier.ui.webapp.content.action.LoginAction;
import com.bueffeltier.ui.webapp.content.action.LoginMenuAction;
import com.bueffeltier.ui.webapp.content.action.MemberAccountSettingsAction;
import com.bueffeltier.ui.webapp.content.action.MemberLearningSettingsActionHandler;
import com.bueffeltier.ui.webapp.content.action.MemberPersonalDataAction;
import com.bueffeltier.ui.webapp.content.action.MottoAction;
import com.bueffeltier.ui.webapp.content.action.RegisterAction;
import com.bueffeltier.ui.webapp.content.action.RegisterConfirmAction;
import com.bueffeltier.ui.webapp.content.action.RegisterSubmittedAction;
import com.bueffeltier.ui.webapp.content.action.RessourcesAction;
import com.bueffeltier.ui.webapp.content.action.AppSettingsAction;
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

	public Class<? extends Action> getAction(String actionName)
	{
		Class<? extends Action> actionClass = actions.get(actionName);

		if (actionClass == null)
		{
			// TODO sveng 23.06.2023: Exeption werfen und ggf. Fehlerseite
			// laden.
			return null;
		}

		return actionClass;
	}

	private void buildActionMap()
	{
		actions = new HashMap<>();

		actions.put("TEXT", TextAction.class);
		actions.put("SITE_STRUCTURE", SiteStructureAction.class);
		actions.put("LOGIN", LoginAction.class);
		actions.put("EDIT_PAGE", PageEditorAction.class);
//		actions.put("EDIT_ARTICLE", EditArticleSettingsAction.class);
		actions.put("REGISTER", RegisterAction.class);
		actions.put("FOOTER", FooterAction.class);
//		actions.put("NAVIGATION", NavigationAction.class);
//		actions.put("EDIT_ARTICLE_ELEMENTS", EditArticleElementsAction.class);
//		actions.put("EDIT_CONTENT_ELEMENT", EditContentElementAction.class);
		actions.put("HTML", HtmlAction.class);
//		actions.put("CHOOSE_CONTENT_TYPE", ChooseContentTypeAction.class);
		actions.put("ARTICLE_FEEDER", ArticleFeederAction.class);
		actions.put("LOGIN_MENU", LoginMenuAction.class);
//		actions.put("SETTINGS", SettingsAction.class);
		actions.put("CONTACT", ContactAction.class);
		actions.put("MOTTO", MottoAction.class);
		actions.put("RESSOURCES", RessourcesAction.class);
		actions.put(
		    "LEARNING_SETTINGS", MemberLearningSettingsActionHandler.class
		);
//		actions.put("NOTES", MemberNotesAction.class);
//		actions.put("MESSAGES", MemberMessagesAction.class);
		actions.put("PERSONAL_DATA", MemberPersonalDataAction.class);
		actions.put("ACCOUNT_SETTINGS", MemberAccountSettingsAction.class);
		actions.put("APP_SETTINGS", AppSettingsAction.class);
		actions.put("USER_ADMIN", UserAdminActionr.class);
		actions.put("HEADER", HeaderAction.class);
		actions.put("FLIPCARD", FlipCardAction.class);
		actions.put("REGISTER_SUBMITTED", RegisterSubmittedAction.class);
		actions.put("REGISTER_CONFIRM", RegisterConfirmAction.class);
		actions.put("HTTP404", Http404Action.class);
		actions.put("FLIPCARD_START_PAGE", CourseEditorAction.class);
		actions.put("CREATE_FLASHCARDS", LessonEditorAction.class);
	}

	/*
	 * Nachfolgend Codeänderungen für den Fall, dass instantiierte Objekte in
	 * der Action-Map sind. Außerdem könnten die Keys aus der Action Klasse
	 * geladen werden.
	 */
//	public void registerAction(String actionName, Action actionInstance) {
//	    actions.put(actionName, actionInstance);
//	}
//
//	public Action getAction(String actionName) {
//	    Action actionInstance = actions.get(actionName);
//	    if (actionInstance == null) {
//	        // Falls die Aktion nicht gefunden wurde, instanziere sie hier (falls sie Singleton ist)
//	        if (actionName.equals("TEXT")) {
//	            actionInstance = TextAction.getInstance();
//	            actions.put(actionName, actionInstance); // Aktion zur Map hinzufügen
//	        }
//	        // Weitere Singleton-Aktionen hier hinzufügen...
//
//	        // Alternativ könnte eine Exception geworfen werden, wenn die Aktion nicht gefunden wird.
//	    }
//	    return actionInstance;
//	}
//
//	...
//
//	private void buildActionMap() {
//	    actions = new HashMap<>();
//
//	    // Die Singleton-Instanz der TextAction hinzufügen
//	    actions.put("TEXT", TextAction.getInstance());
//
//	    // Weitere Singleton-Aktionen hinzufügen...
//	}
}
