package com.bueffeltier.logic.domain;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 *
 * Sven Guderjahn <sven.guderjahn@bueffeltier.com>
 */
@Entity(name = "LearningLevel")
@Table(name = "learning_level")
public class LearningLevel implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id")
	private Long id;

	@Column(name = "user_id")
	private Long userId;

	@Column(name = "current_lesson_id")
	private Long currentLessonId;

//	@Column(name = "current_lesson_id")
//	private Long currentLessonId;

//	private String prozent;
//	private String phase;

	public LearningLevel()
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

	public Long getCurrentLessonId()
	{
		return currentLessonId;
	}

	public void setCurrentLessonId(Long currentLessonId)
	{
		this.currentLessonId = currentLessonId;
	}

	private static long getSerialversionuid()
	{
		return serialVersionUID;
	}

}
