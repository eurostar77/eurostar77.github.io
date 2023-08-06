package com.bueffeltier.ui.html.organism;

import static j2html.TagCreator.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import j2html.tags.DomContent;
import j2html.tags.specialized.DivTag;
import j2html.tags.specialized.FormTag;

public class FormBuilder
{
	private List<DomContent> domContent;
	private Map<String, String> data;
	private ArrayList<String> classes;
	private boolean needsValidation;
	private String formId;

	private FormBuilder()
	{
		//
	}

	public static FormBuilder create()
	{
		return new FormBuilder();
	}

	public FormBuilder withDomContent(DomContent... domContent)
	{
		if (this.domContent == null)
		{
			this.domContent = new ArrayList<>();
		}

		this.domContent.addAll(Arrays.asList(domContent));

		return this;
	}

	public FormBuilder withValidation()
	{
		this.needsValidation = true;
		return this;
	}

	public FormBuilder withId(String formId)
	{
		this.formId = formId;
		return this;
	}

	public FormBuilder withData(String key, String value)
	{
		if (this.data == null)
		{
			data = new HashMap<String, String>();
		}
		data.put(key, value);
		return this;
	}

	/*
	 * acton name = element.getType()
	 */
	public FormTag build(String actionName)
	{
		return form()//
		    .with(
		        input()//
		            .withType("hidden")//
		            .withName("action")//
		            .withValue(actionName)//
		    )//
		    .withCondClass(classes != null, "")//
		    .withId(formId)//
		    .condWith(data != null, getFormDataOpt())//
		    .condWith(domContent != null, getDomContentOpt())//
		    .attr("method", "post")//
		    .attr("autocomplete", "on")//
		    .attr("action", "Servlet")//
		    .condAttr(needsValidation, "novalidate", null)
		//
		;
	}

//	return new ContainerTag<>("form")//
//	    .attr("method=\"post\"")//
//	    .attr("autocomplete", "on")//
//	    .attr("action", "Servlet").with(
//	        input()//
//	            .withType("hidden")//
//	            .withName("action")//
//	            .withValue(actionName)//
//	    )//
//	    .with(domContent);//

	private String buildClassString()
	{
		String classString = null;

		if (needsValidation)
		{
			classString = "needs-validation";
		}

		return classString;
	}

	private DomContent getFormDataOpt()
	{
		DivTag dataDiv = null;

		if (data != null)
		{
			dataDiv = new DivTag();

			for (Map.Entry<String, String> d : data.entrySet())
			{
				dataDiv.withData(d.getKey(), d.getValue());
			}
		}

		return dataDiv.withClass("form-data");
	}

	private DomContent[] getDomContentOpt()
	{
		if (domContent != null)
		{
			return domContent.toArray(new DomContent[0]);

		} else
		{
			return new DomContent[0];
		}
	}
}
