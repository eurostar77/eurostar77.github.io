package com.bueffeltier.ui.webapp.content.action;

import static j2html.TagCreator.*;

import javax.servlet.http.HttpServletRequest;

import com.bueffeltier.data.hibernate.entity.FrageAntwort;

import j2html.tags.Tag;

/**
 *
 * @author Sven Guderjahn <sven.guderjahn@bueffeltier.com>
 */
public class CreateFlashcardsAction extends AbstractAction
{

	int cardCount = 0;

	private static CreateFlashcardsAction instance;

	private CreateFlashcardsAction()
	{
		super();
	}

	public static CreateFlashcardsAction getInstance()
	{
		if (instance == null)
		{
			instance = new CreateFlashcardsAction();
		}
		return instance;
	}

	@Override
	public void execute(HttpServletRequest request)
	{
//        FrageAntwortDaoImpl frageAntwortDaoImpl = new FrageAntwortDaoImpl();
//        /*
//        Fragen tragen den Wert q.
//        Antworten tragen den Wert a.
//        Neue Fragen tragen den Wert qn.
//        Neue Antworten tragen den Wert an.
//         */
//        Enumeration parameterNames = this.getRequestParameterNames();
////        while (parameterNames.hasMoreElements())
////        {
////            JOptionPane.showMessageDialog(null, parameterNames.nextElement().toString());
////        }
//        while (parameterNames.hasMoreElements())
//        {
//            String actionParameterName = parameterNames.nextElement().toString();
//
//            switch (actionParameterName)
//            {
//                case "stackName":
//                    getRequestParameter("stackName");
//                    JOptionPane.showMessageDialog(null, getRequestParameter("stackName"));
//                    break;
//                case "stackTheme":
//                    getRequestParameter("stackTheme");
//                    break;
//                case "stackDescription":
//                    getRequestParameter("stackDescription");
//                    break;
//                case "stackAccess":
//                    getRequestParameter("stackAccess");
//                    break;
//            }
//        }
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

//		this.setForwardingPage("/lernkarten-erstellen"); // todo: this.getPage

//		this.doPageLoad();
	}

//    private boolean containsCard(Enumeration cards)
//    {
//        boolean containsCard = false;
//        while (cards.hasMoreElements())
//        {
//            if (cards.nextElement().)
//            {
//
//            }
//        }
//    }
	@Override
	public void doAjaxAction(HttpServletRequest request)
	{
		throw new UnsupportedOperationException("Not supported yet.");
	}

	private Tag writeCard(FrageAntwort card)
	{
		this.cardCount++;
		return div(
		    h3("Karte " + this.cardCount).withClass("card-header")
		        .withId("ch" + cardCount),
		    div(
		        div(
		            h4("Aufgabe").withClass("card-header"),
		            div(
		                textarea(card.getFrage()).withClass("form-control")
		                    .attr("rows", "2").withId("q" + cardCount)
		                    .attr("name", "q" + card.getID())
		            ).withClass("card-body")
		        ).withClass(".g-col-4"),
		        div(
		            h4("Lösung").withClass("card-header"),
		            div(
		                textarea(card.getFrage()).withClass("form-control")
		                    .attr("rows", "2").withId("a" + cardCount)
		                    .attr("name", "a" + card.getID())
		            ).withClass("card-body")
		        ).withClass(".g-col-4")
		    ).withClass("grid").attr("class", "grid")
		).withClass("card my-5 bg-light").withId("card" + cardCount);
	}
}
