package com.bueffeltier.logic.domain;

import com.bueffeltier.data.hibernate.daoImpl.FrageAntwortDaoImpl;
import com.bueffeltier.data.hibernate.entity.FrageAntwort;

/**
 *
 * @author Sven Guderjahn <sven.guderjahn@bueffeltier.com>
 */
// todo: rename LerningType?
public class LearningTask
{

    private final String FLASHCARD = "flashcard";
    private String jspType;
    private FrageAntwort fran;
    private int nextQuestion = 0;

    /**
     *
     * @param type
     */
    public LearningTask(String type)
    {
        this.jspType = type;
        // aufgabenContent laden

    }

    private void loadDbContent(String type)
    {
        switch (type)
        {
            case "flashcard":
                FrageAntwortDaoImpl franDaoImpl = new FrageAntwortDaoImpl();
                // todo: hibernate zugriffe schreiben
                // Auswahl der Frage findet sowieso in Unterklasse von Lesson statt.
//                x
                // hier vorerst:
                fran = franDaoImpl.findById(FrageAntwort.class, 1);
                break;

        }
    }

    private void setJspType(String type)
    {
        switch (type)
        {
            case FLASHCARD:
                this.jspType = type;
                break;
        }
    }
    // todo:
    // in html ausgeben
    // zeilenumbrüche mitgeben!

    /**
     *
     * @return
     */
    public String getContent()
    {
        return "<p>Lernkartenbeispiel</p>"
                + "<div id=\"header\">" + "\n"
                + "<%@ include file=\"/jspf/flashcard.jspf\" %>";
    }

    /**
     *
     * @return
     */
    public String getJspType()
    {
        return jspType;
    }
    // todo: diese methode gehört gar nicht in die allgemeine Klasse LearningTask!

    /**
     *
     * @return
     */
    public FrageAntwort getNextFrageAntwort()
    {
        FrageAntwortDaoImpl franDaoImpl = new FrageAntwortDaoImpl();
        fran = franDaoImpl.findById(FrageAntwort.class, nextQuestion);
//        get nextId from DAO!!!
//        for.franDaoImpl
//            this.nextQuestion++;
        return fran;
    }
}
