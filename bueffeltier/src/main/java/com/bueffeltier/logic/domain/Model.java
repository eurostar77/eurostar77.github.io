package com.bueffeltier.logic.domain;

import java.util.List;
import javax.swing.JOptionPane;

import com.bueffeltier.data.hibernate.daoImpl.FrageAntwortDaoImpl;
import com.bueffeltier.data.hibernate.entity.FrageAntwort;
import com.bueffeltier.data.hibernate.entity.User;

/**
 * todo: login des nutzers loggen in eigener methode.
 * todo: model global zur verfügung stellen und nicht nur einem nutzer.
 * Model liefert User-Sessions
 * @author Sven Guderjahn <sven.guderjahn@bueffeltier.com>
 */
public class Model
{

    private final User user = null;
    private final boolean isLoggedIn = false;

    /**
     * todo: klären,wie man den konstruktor einer klasse dokumentiert.
     */
    public Model()
    {
    }

    /**
     *
     * @param userName
     * @param pw
     * @return
     */
    public Lesson getLesson(String userName, String pw)
    {

        // todo:
        // wenn eingeloggt
        // dann Lesson erstellen mit user-daten
        // man kann html-lesson holen oder ...
        // LessonType=... (hier vorerst nur FrageAntwort)
        Lesson lesson = new Lesson(userName, pw);
        return lesson;
    }

    /**
     *
     * @return
     */
    public List<FrageAntwort> getAllFrageAntwort()
    {
        FrageAntwortDaoImpl franDAO = new FrageAntwortDaoImpl();
        List<FrageAntwort> list;
        list = franDAO.findAll(FrageAntwort.class, "frage_antwort");
        return list;
    }

    /**
     *
     */
    public static void alleFragen()
    {
        FrageAntwortDaoImpl franDAO = new FrageAntwortDaoImpl();

        List<FrageAntwort> list;
        list = franDAO.findAll(FrageAntwort.class, "frage_antwort");
        for (int i = 0; i < list.size(); i++)
        {
            JOptionPane.showMessageDialog(null, list.get(i).getFrage() + "\n");
        }
    }
}
