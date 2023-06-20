package com.bueffeltier.logic.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
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
public class Lesson implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id")
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "theme")
	private String theme;

	@Column(name = "image_path")
	private String imagePath;

	@JoinColumn(name = "owner_id")
	private long ownerId;

	@OneToMany
	@JoinColumn(name = "learning_task")
	private List<LearningTask> learningTasks = new ArrayList<>();

	public Lesson()
	{

	}

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

	public String getTheme()
	{
		return theme;
	}

	public void setTheme(String theme)
	{
		this.theme = theme;
	}

	public String getImagePath()
	{
		return imagePath;
	}

	public void setImagePath(String imagePath)
	{
		this.imagePath = imagePath;
	}

	public long getOwnerId()
	{
		return ownerId;
	}

	public void setOwnerId(long ownerId)
	{
		this.ownerId = ownerId;
	}

	public List<LearningTask> getLearningTasks()
	{
		return learningTasks;
	}

	public void setLearningTasks(List<LearningTask> learningTasks)
	{
		this.learningTasks = learningTasks;
	}

	public void setLearningTask(LearningTask learningTask)
	{
		this.learningTasks.add(learningTask);
	}

	private static long getSerialversionuid()
	{
		return serialVersionUID;
	}

}
