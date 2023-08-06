package com.bueffeltier.ui.html.organism;

import static j2html.TagCreator.label;

import java.util.ArrayList;
import java.util.List;

import com.bueffeltier.ui.html.molecule.SpacingPropertyDV;
import com.bueffeltier.ui.html.molecule.SpacingSidesDV;
import com.bueffeltier.ui.html.molecule.SpacingSizeDV;

import j2html.tags.DomContent;
import j2html.tags.specialized.LabelTag;

public class LabelBuilder
{
	List<String> cssClasses;
	String cssId;

	private SpacingPropertyDV spacingProperty;
	private SpacingSidesDV spacingSides;
	private SpacingSizeDV spacingSize;

	boolean isHidden = false;

	private LabelBuilder()
	{
	}

	public static LabelBuilder create()
	{
		return new LabelBuilder();
	}

	public LabelBuilder withX(DomContent domContent)
	{
		return this;
	}

	public LabelBuilder isHidden(boolean isHidden)
	{
		this.isHidden = isHidden;

		return this;
	}

	public LabelBuilder withSpacing(
	    SpacingPropertyDV property,
	    SpacingSidesDV sides,
	    SpacingSizeDV size
	)
	{
		this.spacingProperty = property;
		this.spacingSize = size;
		this.spacingSides = sides;
		return this;
	}

	public LabelBuilder withSpacingProperty(SpacingPropertyDV property)
	{
		this.spacingProperty = property;

		return this;
	}

	public LabelBuilder withSpacingSize(SpacingSizeDV size)
	{
		this.spacingSize = size;

		return this;
	}

	public LabelBuilder withSpacingSides(SpacingSidesDV sides)
	{
		this.spacingSides = sides;

		return this;
	}

	public LabelBuilder withClass(String cssClass)
	{
		if (this.cssClasses == null)
		{
			cssClasses = new ArrayList<>();
		}

		this.cssClasses.add(cssClass);

		return this;
	}

	public LabelBuilder withId(String cssId)
	{
		this.cssId = cssId;

		return this;
	}

	/**
	 * 
	 * @param text
	 * @param forId
	 * @return
	 */
	public LabelTag build(String text, String forId)
	{
		return label(text)//
		    .withFor(forId)//
		    .withClass(buildClassString())//
		    .withCondId(cssId != null, cssId);
	}

	private String buildClassString()
	{
		StringBuilder builder = new StringBuilder();

		builder.append("col-form-label");

		if (isHidden)
		{
			builder.append(" ");
			builder.append("visually-hidden");
		}

		boolean hasSpacing = spacingProperty != null;
		hasSpacing |= spacingSides != null;
		hasSpacing |= spacingSize != null;

		if (hasSpacing)
		{
			builder.append(spacingProperty.toString());
			builder.append(spacingSides.toString());
			builder.append("-");
			builder.append(spacingSize.toString());
		}

		if (cssClasses != null && cssClasses.size() > 0)
		{
			builder.append(" ");

			for (String cssClass : cssClasses)
			{
				builder.append(cssClass);
				builder.append(" ");
			}

			builder.deleteCharAt(builder.length() - 1);
		}

		return builder.toString();
	}
}
