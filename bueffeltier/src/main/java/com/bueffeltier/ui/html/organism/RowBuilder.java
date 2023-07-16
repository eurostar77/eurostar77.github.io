package com.bueffeltier.ui.html.organism;

import static j2html.TagCreator.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.bueffeltier.ui.html.molecule.SpacingPropertyDV;
import com.bueffeltier.ui.html.molecule.SpacingSidesDV;
import com.bueffeltier.ui.html.molecule.SpacingSizeDV;

import j2html.tags.DomContent;

public class RowBuilder
{
	List<String> cssClasses;
	List<String> cssIds;
	List<DomContent> domContent;

	private SpacingPropertyDV spacingProperty;
	private SpacingSidesDV spacingSides;
	private SpacingSizeDV spacingSize;
	private RowAlignmentDV rowAlignment;

	private RowBuilder()
	{
	}

	public static RowBuilder create()
	{
		return new RowBuilder();
	}

	public RowBuilder withSpacing(
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

	public RowBuilder withDomContent(DomContent... domContent)
	{
		if (this.domContent == null)
		{
			this.domContent = new ArrayList<>();
		}
		this.domContent.addAll(Arrays.asList(domContent));
//		this.domContent.add(domContent);
		return this;
	}

	public RowBuilder withAlignment(RowAlignmentDV alignment)
	{
		this.rowAlignment = alignment;

		return this;
	}

	public RowBuilder withClass(String cssClass)
	{
		if (this.cssClasses == null)
		{
			cssClasses = new ArrayList<>();
		}
		this.cssClasses.add(cssClass);
		return this;
	}

	public RowBuilder withId(String cssId)
	{
		if (this.cssIds == null)
		{
			cssIds = new ArrayList<>();
		}
		this.cssIds.add(cssId);
		return this;
	}

	public DomContent build()
	{
//		String classString = null;
		String idString = null;

		if (this.cssClasses == null)
		{
			cssClasses = new ArrayList<>();
		}

		cssClasses.add(buildSpacingClassString());

		return div(each(domContent, doc -> {
			return doc;
		}))//
		    .withClass(buildClassString())//
		    .withCondId(StringUtils.isNotBlank(idString), buildIdString())//
		;
	}

	// TODO sveng 25.02.2023: zusammen mit form control builder in eingene class
	private String buildSpacingClassString()
	{
		StringBuilder builder = new StringBuilder();

		if (spacingProperty == null)
		{
			builder.append("m");

		} else
		{
			builder.append(spacingProperty.toString());
		}

		if (spacingSides == null)
		{
			builder.append("b");

		} else
		{
			builder.append(spacingSides.toString());
		}

		builder.append("-");

		if (spacingSize == null)
		{
			builder.append("1");

		} else
		{
			builder.append(spacingSize.toString());
		}

		return builder.toString();
	}

	private String buildClassString()
	{
		StringBuilder builder = new StringBuilder();

		builder.append("row");

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

		if (rowAlignment != null)
		{
			builder.append(" ");

			builder.append(rowAlignment.toString());
		}

		return builder.toString();
	}

	private String buildIdString()
	{
		StringBuilder builder = new StringBuilder();

		if (cssIds != null && cssIds.size() > 0)
		{
			for (String cssId : cssIds)
			{
				builder.append(cssId);
				builder.append(" ");
			}

			builder.deleteCharAt(builder.length() - 1);
		}

		return builder.toString();
	}

	public enum RowAlignmentDV
	{
		START("justify-content-start"), //
		END("justify-content-end"), //
		CENTER("justify-content-center"), //
		BETWEEN("justify-content-between"), //
		AROUND("justify-content-around"), //
		EVENLY("justify-content-evenly");

		private final String value;

		RowAlignmentDV(String value)
		{
			this.value = value;
		}

		@Override
		public String toString()
		{
			return value;
		}
	}
}
