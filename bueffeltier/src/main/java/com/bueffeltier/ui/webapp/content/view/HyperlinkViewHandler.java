package com.bueffeltier.ui.webapp.content.view;

//import com.mycompany.bueffeltier.servlet.logger.LogFrame;
/**
 *
 * @author Sven Guderjahn <sven.guderjahn@bueffeltier.com>
 */
// todo: rename -??? textlink siehe selfhtml!
// todo: link mit sprachangabe einführen
// todo: links kennzeichnen: https://wiki.selfhtml.org/wiki/HTML/Tutorials/Links/Gestaltung_mit_CSS#externe_Links_kennzeichnen
// todo: title-attribut einfügen???
// todo: prüfung aussagekräftiger link-bezeichnungen
public class HyperlinkViewHandler
{

	private int targetId;
	private String anchorText;
	private String sessionId;
	private static HyperlinkViewHandler instance;

	private HyperlinkViewHandler()
	{
		super();
	}

	public static HyperlinkViewHandler getInstance()
	{
		if (instance == null)
		{
			instance = new HyperlinkViewHandler();
		}
		return instance;
	}
//    LogFrame log = LogFrame.getInstance();
//    // todo: später als Interface vorgeben!?
//    public String getContent(){
//
////        todo:
////        <a title="aefawe" href="{{link_url::6}}">Unterseite 3</a>
//
//    }
//    public getLink(){
//
//        // Seite laden und link generieren, nicht direkt auf die Seite zugreifen.
//        // Wäre das überhaupt möglich? Dürfte das sein?
//        // ändert sich die Seitenzuordnung, so ändert sie diese ja im Seitenobjekt.
//
//        // Gibt es die Seite noch? Wenn nicht wird kein LInk zurückgegeben, sondern nur der Text?
//
//        <p><a title="aefawe" href="{{link_url::6}}">Unterseite 3</a></p>
//
//       "<p><a href=\"/bueffeltier/kontakt;jsessionid=" + session.getId() + "\">Kontakt</a></p>" + "\n" +
//
//    }
}
