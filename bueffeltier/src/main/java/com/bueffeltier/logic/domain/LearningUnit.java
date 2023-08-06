package com.bueffeltier.logic.domain;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity(name = "LearningUnit")
@Table(name = "learning_unit")
public class LearningUnit implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id")
	private Long id;

	@Column(name = "uuid", unique = true, nullable = false)
	private String uuid;

	@OneToOne
	@JoinColumn(name = "question")
	private Question question;

	@Transient
	private Answer answer;

	@OneToOne
	@JoinColumn(name = "knowledge_unit")
	private Knowledge knowledge;

	public LearningUnit()
	{

	}

	public Long getId()
	{
		return this.id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getUuid()
	{
		return this.uuid;
	}

	public void setUuid(String uuid)
	{
		this.uuid = uuid;
	}

	public Question getQuestion()
	{
		return this.question;
	}

	public void setQuestion(Question question)
	{
		this.question = question;
	}

	public boolean hasQuestion()
	{
		return this.question != null;
	}

	public Answer getAnswer()
	{
		return this.answer;
	}

	public void setAnswer(Answer answer)
	{
		this.answer = answer;
	}

	public Knowledge getKnowledge()
	{
		return this.knowledge;
	}

	public void setKnowledgeUnit(Knowledge knowledge)
	{
		this.knowledge = knowledge;
	}

	public boolean hasKnowledge()
	{
		return this.knowledge != null;
	}

	public static long getSerialversionuid()
	{
		return serialVersionUID;
	}
}