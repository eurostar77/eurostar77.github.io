package com.bueffeltier.ui.webapp.content.action;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import com.bueffeltier.ui.webapp.RequestService.John;
import com.google.gson.Gson;

/**
 *
 * @author Sven Guderjahn <sven.guderjahn@bueffeltier.com>
 */
public class LessonEditorAction extends AbstractAction
{
	int cardCount = 0;

	private static LessonEditorAction instance;

	private LessonEditorAction(HttpServletRequest request)
	{
		super();
	}

	public static LessonEditorAction getInstance(HttpServletRequest request)
	{
		if (instance == null)
		{
			instance = new LessonEditorAction(request);
		}
		return instance;
	}

	@Override
	public void execute(HttpServletRequest request)
	{
		// Hier wird eine Lesson gespeichert
		// Lesson.name darf nur einmal existieren.
		// Aktion sollte in Ajax abgewickelt werden.

		Enumeration<String> parameterNames = request.getParameterNames();

		while (parameterNames.hasMoreElements())
		{
			String actionParameterName = parameterNames.nextElement()
			    .toString();

			switch (actionParameterName) {

			case "stackName":
				request.getParameter("stackName");
				break;

			case "stackTheme":
				request.getParameter("stackTheme");
				break;
			case "inputName":
				request.getParameter("inputName");
				boolean wahr = false;
				wahr = true;
				break;

			case "json":
				try
				{
//					StringBuilder sb = new StringBuilder();
//					BufferedReader reader = request.getParameter("ajax");
//					String line;
//					while ((line = reader.readLine()) != null)
//					{
//						sb.append(line);
//					}

//					String jsonData = sb.toString();
					// TODO sveng 27.06.2023: Gson-Servie, da gson threadsafe
					// ist.
					// JSON-Parsing mit Gson
					String jsonData = request.getParameter("ajax");
					Gson gson = new Gson();
					John john = gson.fromJson(jsonData, John.class);
					boolean falsch = true;
				} catch (Exception e)
				{
//					if (erfolg)
//					{
//						response.setStatus(HttpServletResponse.SC_OK); // Erfolgsantwort
//						                                               // (HTTP-Statuscode
//						                                               // 200)
//					} else
//					{
//						response.setStatus(
//						    HttpServletResponse.SC_INTERNAL_SERVER_ERROR
//						); // Fehlerantwort (HTTP-Statuscode 500)
//					}
//					// catch: ajax fehlermeldung senden.
				}
				break;

			}
		}

//
//        // Original-Stapel laden:
//        List<FrageAntwort> originalList = frageAntwortDaoImpl.findAll(); // todo: Stapel-Klasse schaffen, User-Id, Max. Kartenzahl, Methode findCardStack()
//
//        HashMap<String, FrageAntwort> updateMap = new HashMap();
//// todo: nur bestimmten stapel laden, user berechtigung checken.
//// todo: Direkt eine HashMap von FrageAntwortDaoImpl anfordern:
////
//        // Hashmap füllen:
//        for (int i = 0; i < originalList.size(); i++)
//        {
//            updateMap.put(Integer.toString(originalList.get(i).getID()), originalList.get(i));
//        }
//        /*
//         * todo: Ganzen Stapel laden und dann neue hinzufügen, alte updaten, übrige löschen.
//         * Kann man da eine Transaction draus machen? JDBC lesen!
//         */
////
//        //Änderungen des Kartestapels übernehmen:
//        Enumeration cardChanges1 = this.getRequestParameterNames();
//
//        ArrayList<String> currentIds = new ArrayList();
//
//        while (cardChanges1.hasMoreElements())
//        {
//            String currentParameterName = cardChanges1.nextElement().toString();
////            JOptionPane.showMessageDialog(null, "currentParameterName: " + currentParameterName);
////
//            // Wenn das ein Karten-Parameter ist:
//            if (currentParameterName.charAt(0) == 'q' || currentParameterName.charAt(0) == 'a')
//            {
//                // Karten-Id ermitteln:
//                String cardId = currentParameterName.substring(
//                        1, currentParameterName.length() - 1);
//                JOptionPane.showMessageDialog(null, "Karten-ID ermitteln: " + cardId);
//
//                currentIds.add(cardId);
//
//                FrageAntwort frageAntwort;
//
//                // Karte referenzieren oder neue Karte instanzieren:
//                if (updateMap.containsKey(cardId))
//                {
//                    // Frage oder Antwort setzen:
//                    // todo: in methode auslagern, da doppelt.
//                    if (currentParameterName.charAt(0) == 'q')
//                    {
//                        updateMap.get(cardId).setFrage(getRequestParameter(currentParameterName));
//                    } else
//                    {
//                        updateMap.get(cardId).setAntwort(getRequestParameter(currentParameterName));
//                    }
//                } else
//                {
//                    frageAntwort = new FrageAntwort();
//                    // Frage oder Antwort setzen:
//                    // todo: in methode auslagern, da doppelt.
//                    if (currentParameterName.charAt(0) == 'q')
//                    {
//                        frageAntwort.setFrage(getRequestParameter(currentParameterName));
//                    } else
//                    {
//                        frageAntwort.setAntwort(getRequestParameter(currentParameterName));
//                    }
//                    updateMap.put(cardId, frageAntwort);
//                }
//            }
//
//            // Ermitteln zu löschender Karten:
//            // jede cardMap auf Doppeleintrag prüfen
//            Enumeration cardChanges2 = this.getRequestParameterNames();
//
//            while (cardChanges2.hasMoreElements())
//            {
//                if (!originalList.contains(""))
//                {
//
//                }
//            }
//            /*
//             * 2. Variante:
//             */
//            for (int i = 0; i < originalList.size(); i++)
//            {
//
//                // Soll diese Karte immer noch in CardsMap bleiben?
//                // isInCurrentIds()
////                for (int j = 0; j < currentIds.size(); j++)
////                {
//                if (currentIds.contains(Integer.toString(updateMap...get(i).getID()
//
//
//                ))) // todo: in map nur mit key suchen!
//                    {
//
//                    } else
//                    {
//
//                    }
////                }
//            }
//
//            frageAntwortDaoImpl.save(frageAntwort); // todo: Vor Speichern noch prüfen, was gelöscht werden muss.
//        }
//
//		this.setForwardingPage("/lernkarten-erstellen"); // todo: this.getPage
//
//		this.doPageLoad();
	}

//	private boolean containsCard(Enumeration cards)
//	{
//		boolean containsCard = false;
//		while (cards.hasMoreElements())
//		{
////            if (cards.nextElement().)
////            {
////
////            }
//		}
//	}

	@Override
	public void executeAjax(HttpServletRequest request)
	{
		throw new UnsupportedOperationException("Not supported yet.");
	}

//	private Tag writeCard(FrageAntwort card)
//	{
//		this.cardCount++;
//		return div(
//		    h3("Karte " + this.cardCount).withClass("card-header")
//		        .withId("ch" + cardCount),
//		    div(
//		        div(
//		            h4("Aufgabe").withClass("card-header"),
//		            div(
//		                textarea(card.getFrage()).withClass("form-control")
//		                    .attr("rows", "2").withId("q" + cardCount)
//		                    .attr("name", "q" + card.getID())
//		            ).withClass("card-body")
//		        ).withClass(".g-col-4"),
//		        div(
//		            h4("Lösung").withClass("card-header"),
//		            div(
//		                textarea(card.getFrage()).withClass("form-control")
//		                    .attr("rows", "2").withId("a" + cardCount)
//		                    .attr("name", "a" + card.getID())
//		            ).withClass("card-body")
//		        ).withClass(".g-col-4")
//		    ).withClass("grid").attr("class", "grid")
//		).withClass("card my-5 bg-light").withId("card" + cardCount);
//	}
}
