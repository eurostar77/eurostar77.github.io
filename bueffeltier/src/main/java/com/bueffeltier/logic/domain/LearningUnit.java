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

	@OneToOne
	@JoinColumn(name = "question")
	private Question question;

	@Transient
	private Answer answer;

	@OneToOne
	@JoinColumn(name = "knowledge_unit")
	private KnowledgeUnit knowledgeUnit;

	public LearningUnit()
	{

	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public Question getQuestion()
	{
		return question;
	}

	public void setQuestion(Question question)
	{
		this.question = question;
	}

	public Answer getAnswer()
	{
		return answer;
	}

	public void setAnswer(Answer answer)
	{
		this.answer = answer;
	}

	public KnowledgeUnit getKnowledgeUnit()
	{
		return knowledgeUnit;
	}

	public void setKnowledgeUnit(KnowledgeUnit knowledgeUnit)
	{
		this.knowledgeUnit = knowledgeUnit;
	}

	public static long getSerialversionuid()
	{
		return serialVersionUID;
	}
}