package com.bueffeltier.logic.domain;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity(name = "Question")
@Table(name = "question")
public class Question implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id")
	private Long id;

	@Column(name = "text")
	private String text;

	@OneToMany
	@JoinColumn(name = "knowledge_unit_ref")
	private List<KnowledgeUnit> referencedKnowledgeUnits;

	public Question()
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

	public String getText()
	{
		return text;
	}

	public void setText(String text)
	{
		this.text = text;
	}

	public List<KnowledgeUnit> getReferencedKnowledgeUnits()
	{
		return referencedKnowledgeUnits;
	}

	public void setReferencedKnowledgeUnits(
	    List<KnowledgeUnit> referencedKnowledgeUnits
	)
	{
		this.referencedKnowledgeUnits = referencedKnowledgeUnits;
	}

}
