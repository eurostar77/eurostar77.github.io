package com.bueffeltier.ui.html.organism;

import static j2html.TagCreator.div;

import j2html.tags.DomContent;

public class ContainerBuilder
{
	private ContainerBuilder()
	{
	}

	public static ContainerBuilder create()
	{
		return new ContainerBuilder();
	}

	public ContainerBuilder withX(DomContent domContent)
	{
		return this;
	}

	public DomContent build()
	{
		return div(

		).withClass("");
	}
}
