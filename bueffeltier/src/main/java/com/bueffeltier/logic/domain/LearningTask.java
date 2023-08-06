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

	@Column(name = "uuid", unique = true, nullable = false)
	private String uuid;

	@Column(name = "task_name")
	private String name;

	@Column(name = "image_path")
	private String imagePath;

	@OneToMany
	@JoinColumn(name = "learning_unit")
	private List<LearningUnit> learningUnit;

	@Column(name = "type")
	private String type;

	public void setId(Long id)
	{
		this.id = id;
	}

	public Long getId()
	{
		return id;
	}

	public String getUuid()
	{
		return uuid;
	}

	public void setUuid(String uuid)
	{
		this.uuid = uuid;
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

	public List<LearningUnit> getLearningUnits()
	{
		return learningUnit;
	}

	public void setLearningUnits(List<LearningUnit> learningUnit)
	{
		this.learningUnit = learningUnit;
	}

	public void addLearningUnit(LearningUnit learningUnit)
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
