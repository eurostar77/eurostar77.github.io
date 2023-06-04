package com.bueffeltier.logic.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * lesson bietet unterscheidliche "Types" z.B. Lernkarte, Lücketext... lesson
 * ist immer auf einen user zugeschnitten und hält alle informationen, die die
 * http session benötigt.
 * 
 * // User Login
 * 
 * // Lesson spezifizieren // Zeitrahmen // Aufgabentyp // Lernstand holen //
 * Aufgaben auswählen und hinzufügen (hier erst eine) // lesson bauen und tasks
 * in liste speichern!!!!!!! // User auswerten // Fragen auswählen //
 * Userwünsche abfragen: Zeitfenster, Edutainment, Chat, etc. // Edutainment
 * kommt nicht in Lesson vor, oder? Wird User immer bespaßt // oder entscheidet
 * er sich zur AUfgabe?
 * 
 * @author Sven Guderjahn <sven.guderjahn@bueffeltier.com>
 */
@Entity(name = "Lesson")
@Table(name = "lesson")
public class Lesson
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "name")
	private String name;

	public Lesson()
	{

	}

//	@GeneratedValue(generator = "increment")
//	@GenericGenerator(name = "increment", strategy = "increment")
	public void setId(Long id)
	{
		this.id = id;
	}

	public Long getId()
	{
		return id;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getName()
	{
		return this.name;
	}

	private void buildLesson()
	{
		// User bestimmen

		// Lernstand ermitteln
		// Themen auswählen
		// gewählte Aufgabentypen generieren anhand Lernstand
//        FrageAntwortDaoImpl franDao = new FrageAntwortDaoIml();
//        franDao.findAll(clazz, sqlTableName)
	}

	/**
	 *
	 * @param type
	 * @return
	 */
//	public LearningTask getNextTask(String type)
//	{
//		// todo: zähler etablieren und nächsten task auswerfen:
//
//		// todo: künftig werden tasks bei Lesson Erstellung angelegt:
//		task = new LearningTask(type);
//
//		return task;
//	}
}
