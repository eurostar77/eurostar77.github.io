package com.bueffeltier.ui.html.organism;

import j2html.tags.DomContent;

public class ListGroupBuilder
{
	private ListGroupBuilder()
	{
	}

	public static ListGroupBuilder create()
	{
		return new ListGroupBuilder();
	}

	public ListGroupBuilder withX(DomContent domContent)
	{
		return this;
	}

	public DomContent build()
	{
		return ul(

		).withClass("");
	}
}
