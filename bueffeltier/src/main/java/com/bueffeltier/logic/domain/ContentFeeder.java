package com.bueffeltier.logic.domain;

import java.util.ArrayList;
import java.util.List;

import com.bueffeltier.data.jdbc.ArticleJDBCFlat;
import com.bueffeltier.logic.foundation.pagetree.SiteRepository;

/**
 * Der Content Feeder liefert der Hauptseite der App verschiedene Artikel und
 * steuert damit unter Berücksichtigung der Geschätsinteressen von "Büffeltier"
 * Lernaktivitäten des Users. Auswahl und Anzahl der Artikel hängt von
 * verschiedenen Faktoren, wie z.B. Lernziel, Lernstand, Lerntyp, Alter,
 * Vorwissen und persönlichen Präferenzen des Users ab. Der Nutzer steht im
 * Mittelpunkt!
 * 
 * @author Sven Guderjahn <sven.guderjahn@bueffeltier.com>
 */
public class ContentFeeder
{
	private static ContentFeeder instance;

	private SiteRepository pageTreeRepo;

	private ContentFeeder()
	{
		pageTreeRepo = SiteRepository.getInstance();
	}

	public static ContentFeeder getInstance()
	{
		if (instance == null)
		{
			instance = new ContentFeeder();
		}
		return instance;
	}

	/**
	 * Gibt den nächsten nach Berechtigung für den Nutzer bestimmten Artikel
	 * zurück.
	 * 
	 * @return
	 */
	public ArticleJDBCFlat nextArticle()
	{
		// if logged in or not
		// User ermitteln
		// Gewählten Kurs laden
		// Lernstand laden
		// Anzahl der anzuzeigenden Artikel ermitteln.
		// Bestimmte Tasks setzen eine Sperre für die nächsten.
		// abarbeiten
		// an stelle 1 anzeigen
		// max. anzahl an artikeln
		// einschränkung nach lehrstand
		// auswahl nach kursverlauf.
		/*
		 * 
		 * 0. - hasNext bestimmen, nach max. Artikelzahl pro Seite, user
		 * status,.. 1. Artikelauswahl - Nach maximal durch die appSettings
		 * erlaubter Artikelanzahl. a. Nach aktueller Info zur Seite für
		 * Besucher und ggf. User der Seite. - Artikelstatus: Visitor -
		 * Info-Handler schaffen. - für den einzelnen User persönliche
		 * Nachricht. - Verknüpfung mit Landingpages und Angeboten. - Bei jeder
		 * Meldung/Artikel den Adressaten nach Login festlegen. -> Visuell mit
		 * Grafik, Besucherbilder, Urlauber/Besucher, Arbeiter,... b. Allgemeine
		 * Artikel für Besucher der Seite - Lerntipps - Selbstfindung -
		 * Kursfindung - Allgemeine News/ Berichte - Neujahr - Schulanfang -
		 * Corona - Rabatte - Darstellung On-top Im Fluss am Ende Bedingung Muss
		 * gelesen werden + Sperre auf folgende Artikel neue Artikelzufuhr alle
		 * angezeigten Artikel ohne Sperre c. Artikel für geloggte User.
		 * (Möglichkeit auch ohne Login, mit Datei?) - Verlauf und Stand der
		 * Kundengewinnung - Gesetzte Termine (+Mail) Lernstand nach
		 * Selbstfindung etc. nach Lebensziel (Big 5) 3. Nach gewähltem
		 * Lernzie/Lebensziel Nach Kursverlauf Nach Lernstand Nach Lernverhalten
		 * Fleißig, faul, dumm, intelligent Nach Zeitvolumen / zur Verfügung
		 * stehender Zeit, gewählter Uhrzeit Nach vereinbarter
		 * Lehr-/Unterrichts-Stunde (Termin) Nach Budget Nach Lehrgangsterminen,
		 * Prüfung, etc. Idee: Kaution hinterlegen Artikel nach Tag zuordnen.
		 */
		// Testartikel:
		boolean hasNext = false; // todo: prüfung implementieren.
		ArticleJDBCFlat article = null;
		try
		{
			article = pageTreeRepo.readArticle(64L);
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return article;
	}

	/*
	 * Prüft, je nach Nutzerberechtigung, ob noch weitere Artikel zu laden sind.
	 */
	public boolean hasNext()
	{
		// TODO sveng 20.11.2022: richtigen wert setzen!
//		return this.hasNext;
		return true;
	}

	/*
	 *
	 */
	public List<ArticleJDBCFlat> getArticles()
	{
		ArticleJDBCFlat article = pageTreeRepo.readArticle(64L);
		List<ArticleJDBCFlat> articles = new ArrayList<>();
		articles.add(article);
//		ArticleJDBCFlat article = pageTreeRepo.readArticle(64L);
//		List<ArticleJDBCFlat> articles = new ArrayList<>();
//		articles.add(article);
//		ArticleJDBCFlat article = pageTreeRepo.readArticle(64L);
//		List<ArticleJDBCFlat> articles = new ArrayList<>();
//		articles.add(article);
//		ArticleJDBCFlat article = pageTreeRepo.readArticle(64L);
//		List<ArticleJDBCFlat> articles = new ArrayList<>();
//		articles.add(article);

		return articles;
	}
}
