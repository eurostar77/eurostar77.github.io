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

/**
 *
 * @author Sven Guderjahn <sven.guderjahn@bueffeltier.com>
 */
@Entity
@Table(name = "learning_task")
public class LearningTask implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id")
	private Long id;

	@Column(name = "task_name")
	private String name;

	@Column(name = "image_path")
	private String imagePath;

	@OneToMany
	@JoinColumn(name = "learning_unit")
	private List<LearningUnit> learningUnit;

	@Column(name = "type")
	private String type;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String taskName)
	{
		this.name = taskName;
	}

	private String getImagePath()
	{
		return imagePath;
	}

	private void setImagePath(String imagePath)
	{
		this.imagePath = imagePath;
	}

	private static long getSerialversionuid()
	{
		return serialVersionUID;
	}

	public List<LearningUnit> getQuestionsAndAnswers()
	{
		return learningUnit;
	}

	public void setQuestionsAndAnswers(List<LearningUnit> learningUnit)
	{
		this.learningUnit = learningUnit;
	}

	public void addQuestionAndAnswer(LearningUnit learningUnit)
	{
		this.learningUnit.add(learningUnit);
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

}
