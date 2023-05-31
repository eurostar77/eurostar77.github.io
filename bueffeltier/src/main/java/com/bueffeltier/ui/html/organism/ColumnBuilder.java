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

public class ColumnBuilder
{
	List<String> cssClasses;
	List<String> cssIds;
	List<DomContent> domContent;

	private SpacingPropertyDV spacingProperty;
	private SpacingSidesDV spacingSides;
	private SpacingSizeDV spacingSize;

	private GridWidthDV gridWidth;
	private BreakpointDV breakpoint;

	private ColumnBuilder()
	{
	}

	public static ColumnBuilder create()
	{
		return new ColumnBuilder();
	}

	public ColumnBuilder withX(DomContent domContent)
	{
		return this;
	}

	public ColumnBuilder withSpacing(
	    SpacingPropertyDV property,
	    SpacingSizeDV size,
	    SpacingSidesDV sides
	)
	{
		this.spacingProperty = property;

		return this;
	}

	public ColumnBuilder withSpacingProperty(SpacingPropertyDV property)
	{
		this.spacingProperty = property;

		return this;
	}

	public ColumnBuilder withSpacingSize(SpacingSizeDV size)
	{
		this.spacingSize = size;

		return this;
	}

	public ColumnBuilder withSpacingSides(SpacingSidesDV sides)
	{
		this.spacingSides = sides;

		return this;
	}

	public ColumnBuilder withGridWidth(GridWidthDV gridWidth)
	{
		this.gridWidth = gridWidth;
		return this;
	}

	public ColumnBuilder withBreakpoint(BreakpointDV breakpoint)
	{
		this.breakpoint = breakpoint;
		return this;
	}

	public ColumnBuilder withDomContent(DomContent... domContent)
	{
		if (this.domContent == null)
		{
			this.domContent = new ArrayList<>();
		}
		this.domContent.addAll(Arrays.asList(domContent));
//		this.domContent.add(domContent);
		return this;
	}

	public ColumnBuilder withClass(String cssClass)
	{
		if (this.cssClasses == null)
		{
			cssClasses = new ArrayList<>();
		}
		this.cssClasses.add(cssClass);
		return this;
	}

	public ColumnBuilder withId(String cssId)
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

		return div(each(domContent, doc -> {
			return doc;
		}))//
		    .withClass(buildClassString())//
		    .withCondId(StringUtils.isNotBlank(idString), buildIdString())//
		;
	}

	private String buildClassString()
	{
		StringBuilder builder = new StringBuilder();

		builder.append("col");

		if (breakpoint != null)
		{
			builder.append("-");
			builder.append(breakpoint);
		}
		if (gridWidth != null)
		{
			builder.append("-");
			builder.append(gridWidth);
		}

		if (this.cssClasses == null)
		{
			cssClasses = new ArrayList<>();
		}

		cssClasses.add(buildSpacingClassString());

		if (cssClasses != null && cssClasses.size() > 0)
		{
			builder.append(" ");

			for (String cssClass : cssClasses)
			{
				if (cssClass.length() > 0)
				{
					builder.append(cssClass);
					builder.append(" ");
				}
			}

			builder.deleteCharAt(builder.length() - 1);
		}

		return builder.toString();
	}

	private String buildSpacingClassString()
	{
		StringBuilder builder = new StringBuilder();

		boolean hasSpacing = spacingProperty != null;
		hasSpacing |= spacingSides != null;
		hasSpacing |= spacingSize != null;

		if (hasSpacing)
		{
			builder.append(spacingProperty.toString());
			builder.append(spacingSides.toString());
			builder.append("-");
			builder.append(spacingSize.toString());

			return builder.toString();
		} else
		{
			return "";
		}

	}

	private String buildIdString()
	{
		StringBuilder builder = new StringBuilder();

		if (cssIds != null && cssClasses.size() > 0)
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

	public enum BreakpointDV
	{
		XS("xs"), SM("sm"), MD("md"), LG("lg"), XL("xl"), XXL("xxl");

		private final String value;

		BreakpointDV(String value)
		{
			this.value = value;
		}

		@Override
		public String toString()
		{
			return value;
		}
	}

	public enum GridWidthDV
	{
		ONE("1"), TWO("2"), THREE("3"), FOUR("4"), FIVE("5"), SIX("6"),
		SEVEN("7"), EIGHT("8"), NINE("9"), TEN("10"), ELEVEN("11"),
		TWELVE("12"), AUTO("auto");

		private final String value;

		GridWidthDV(String value)
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
