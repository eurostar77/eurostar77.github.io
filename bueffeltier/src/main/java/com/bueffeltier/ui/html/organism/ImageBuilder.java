package com.bueffeltier.ui.html.organism;

import java.util.ArrayList;
import java.util.List;

import j2html.tags.ContainerTag;
import j2html.tags.DomContent;
import j2html.tags.attributes.IAlt;
import j2html.tags.attributes.IHeight;
import j2html.tags.attributes.IIsmap;
import j2html.tags.attributes.IOnabort;
import j2html.tags.attributes.IOnerror;
import j2html.tags.attributes.IOnload;
import j2html.tags.attributes.ISizes;
import j2html.tags.attributes.ISrc;
import j2html.tags.attributes.ISrcset;
import j2html.tags.attributes.IUsemap;
import j2html.tags.attributes.IWidth;
import j2html.tags.specialized.SpanTag;

public class ImageBuilder
{
	List<String> classes;
	List<String> ids;

	private String source;
	private String width;
	private String text;

	private ImageBuilder()
	{
		//
	}

	public static ImageBuilder create()
	{
		return new ImageBuilder();
	}

	public ImageBuilder withX(DomContent domContent)
	{
		return this;
	}

	public ImageBuilder withSource(String sourcePath)
	{
		this.source = sourcePath;
		return this;
	}

	public ImageBuilder withWidth(String width)
	{
		this.width = width;
		return this;
	}

	public ImageBuilder withText(String text)
	{
		this.text = text;
		return this;
	}

	public ImageBuilder withClass(String classString)
	{
		if (classes == null)
		{
			classes = new ArrayList<>();
		}
		this.classes.add(classString);
		return this;
	}

	public ImageBuilder withId(String idString)
	{
		if (ids == null)
		{
			ids = new ArrayList<>();
		}
		this.ids.add(idString);
		return this;
	}

	public DomContent build()
	{
		MyImgTag myImgTag = new MyImgTag();

		return myImgTag//
		    .withClass(buildClassString())//
		    .withId(buildIdString())//
		    .condAttr(source != null, "src", source)//
		    .condAttr(source != null && width != null, "width", width)//
		    .with(buildTextOpt());
	}

	private DomContent buildTextOpt()
	{
		SpanTag spanTag = null;

		if (text != null)
		{
			spanTag = new SpanTag();

			spanTag.withText(text);

			spanTag.withStyle("margin-left: 10px;");
		}

		return spanTag;
	}

	private String buildClassString()
	{
		StringBuilder builder = new StringBuilder();

		if (classes != null && classes.size() > 0)
		{
			for (String classString : classes)
			{
				builder.append(classString);
				builder.append(" ");
			}

			builder.deleteCharAt(builder.length() - 1);
		}

		return builder.toString();
	}

	private String buildIdString()
	{
		StringBuilder builder = new StringBuilder();

		if (ids != null && ids.size() > 0)
		{
			for (String idString : ids)
			{
				builder.append(idString);
				builder.append(" ");
			}

			builder.deleteCharAt(builder.length() - 1);
		}

		return builder.toString();
	}

	private class MyImgTag extends ContainerTag<MyImgTag> implements
	    IAlt<MyImgTag>, IHeight<MyImgTag>, IIsmap<MyImgTag>, IOnabort<MyImgTag>,
	    IOnerror<MyImgTag>, IOnload<MyImgTag>, ISizes<MyImgTag>, ISrc<MyImgTag>,
	    ISrcset<MyImgTag>, IUsemap<MyImgTag>, IWidth<MyImgTag>
	{

		public MyImgTag()
		{
			super("img");
			// TODO Auto-generated constructor stub
		}

	}
}
