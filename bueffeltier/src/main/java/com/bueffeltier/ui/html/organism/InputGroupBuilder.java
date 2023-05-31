package com.bueffeltier.ui.html.organism;

import static j2html.TagCreator.div;

import j2html.tags.DomContent;

public class InputGroupBuilder
{
	private InputGroupBuilder()
	{
	}

	public static InputGroupBuilder create()
	{
		return new InputGroupBuilder();
	}

	public InputGroupBuilder withX(DomContent domContent)
	{
		return this;
	}

	public DomContent build()
	{
		return div(

		).withClass("");
	}
}
