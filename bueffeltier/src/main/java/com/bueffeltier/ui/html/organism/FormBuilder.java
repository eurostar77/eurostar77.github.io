package com.bueffeltier.ui.html.organism;

import static j2html.TagCreator.div;

import j2html.tags.DomContent;

public class FormBuilder
{
	private FormBuilder()
	{
	}

	public static FormBuilder create()
	{
		return new FormBuilder();
	}

	public FormBuilder withX(DomContent domContent)
	{
		return this;
	}

	public DomContent build()
	{
		return div(

		).withClass("");
	}
}
