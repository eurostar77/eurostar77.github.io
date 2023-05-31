package com.bueffeltier.ui.html.organism;

import static j2html.TagCreator.div;

import j2html.tags.DomContent;

public class SampleBuilder
{
	private SampleBuilder()
	{
	}

	public static SampleBuilder create()
	{
		return new SampleBuilder();
	}

	public SampleBuilder withX(DomContent domContent)
	{
		return this;
	}

	public DomContent build()
	{
		return div(

		).withClass("");
	}
}
