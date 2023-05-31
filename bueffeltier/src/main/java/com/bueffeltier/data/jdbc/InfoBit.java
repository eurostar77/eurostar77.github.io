package com.bueffeltier.data.jdbc;
// Generated 21.10.2018 03:11:17 by Hibernate Tools 4.3.1

/**
 * @author Sven Guderjahn <sven.guderjahn@bueffeltier.com>
 */
public class InfoBit implements java.io.Serializable
{
	private long infoBitId;
	private String infoBitQuestion;
	private String infoBitAnswer;
	private int infoBitRightAnswerCount;
	private int infoBitWrongAnswerCount;
	private long taskId;
	private String taskName;
	private long lectionId;
	private String lectionName;
	private long userId;

	/**
	 *
	 */
	public InfoBit()
	{

	}

	public long getInfoBitId()
	{
		return infoBitId;
	}

	public void setInfoBitId(long infoBitId)
	{
		this.infoBitId = infoBitId;
	}

	public String getInfoBitQuestion()
	{
		return infoBitQuestion;
	}

	public void setInfoBitQuestion(String infoBitQuestion)
	{
		this.infoBitQuestion = infoBitQuestion;
	}

	public String getInfoBitAnswer()
	{
		return infoBitAnswer;
	}

	public void setInfoBitAnswer(String infoBitAnswer)
	{
		this.infoBitAnswer = infoBitAnswer;
	}

	public int getInfoBitRightAnswerCount()
	{
		return infoBitRightAnswerCount;
	}

	public void setInfoBitRightAnswerCount(int infoBitRightAnswerCount)
	{
		this.infoBitRightAnswerCount = infoBitRightAnswerCount;
	}

	public int getInfoBitWrongAnswerCount()
	{
		return infoBitWrongAnswerCount;
	}

	public void setInfoBitWrongAnswerCount(int infoBitWrongAnswerCount)
	{
		this.infoBitWrongAnswerCount = infoBitWrongAnswerCount;
	}

	public long getTaskId()
	{
		return taskId;
	}

	public void setTaskId(long taskId)
	{
		this.taskId = taskId;
	}

	public String getTaskName()
	{
		return taskName;
	}

	public void setTaskName(String taskName)
	{
		this.taskName = taskName;
	}

	public long getLectionId()
	{
		return lectionId;
	}

	public void setLectionId(long lectionId)
	{
		this.lectionId = lectionId;
	}

	public String getLectionName()
	{
		return lectionName;
	}

	public void setLectionName(String lectionName)
	{
		this.lectionName = lectionName;
	}

	public long getUserId()
	{
		return userId;
	}

	public void setUserId(long userId)
	{
		this.userId = userId;
	}
}
