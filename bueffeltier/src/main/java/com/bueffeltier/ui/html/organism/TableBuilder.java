package com.bueffeltier.ui.html.organism;

import static j2html.TagCreator.*;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.bueffeltier.ui.html.organism.ColumnBuilder.GridWidthDV;

import j2html.tags.DomContent;
import j2html.tags.specialized.ButtonTag;
import j2html.tags.specialized.ITag;
import j2html.tags.specialized.TableTag;
import j2html.tags.specialized.TbodyTag;
import j2html.tags.specialized.ThTag;
import j2html.tags.specialized.TheadTag;
import j2html.tags.specialized.TrTag;

public class TableBuilder
{

	private final List<HtmlTableColumn> columns = new ArrayList<>();
	private final List<TableRow> rows = new ArrayList<>();
	private TableColorDV tableColor;
	private boolean hasStripedRows;
	private boolean hasStripedColumns;
	private boolean hasHoverableRows;
	private boolean hasBorders;
	private boolean isBorderless;
	private boolean withoutHead;
	private TableColorDV borderColor;
	private boolean hasSmallPadding;
	private boolean hasGroupDivider;
	private TableAlignDV alignment;
	private TableHeadColorDV tableHeadColor;

	private TableBuilder()
	{
		//
	}

	public static TableBuilder create()
	{
		return new TableBuilder();
	}

	public TableBuilder
	    withColumn(String headerName, TableColumnTypeDV columnType)
	{
		columns.add(new HtmlTableColumn(headerName, columnType));
		return this;
	}

	public TableBuilder withColumn(
	    String headerName,
	    TableColumnTypeDV columnType,
	    GridWidthDV gridWidth
	)
	{
		columns.add(new HtmlTableColumn(headerName, columnType, gridWidth));
		return this;
	}

	public TableBuilder withRow(List<Object> values)
	{
		if (values.size() != columns.size())
		{
			throw new IllegalArgumentException(
			    "The number of values in the row does not match the number of columns."
			);
		}
		rows.add(new TableRow(values));
		return this;
	}

	public TableBuilder withRows(List<TableRow> rowsToAdd)
	{
//		if (rowsToAdd.get(0).length != columns.size())
//		{
//			throw new IllegalArgumentException(
//			    "The number of values in the row does not match the number of columns."
//			);
//		}
		rows.addAll(rowsToAdd);
		return this;
	}

	public TableBuilder withColor(TableColorDV color)
	{
		this.tableColor = color;
		return this;
	}

	public TableBuilder withHeadColor(TableHeadColorDV color)
	{
		this.tableHeadColor = color;
		return this;
	}

	public TableBuilder withBorderColor(TableColorDV color)
	{
		this.borderColor = color;
		return this;
	}

	public TableBuilder withStripedRows()
	{
		this.hasStripedRows = true;
		return this;
	}

	public TableBuilder withStripedColumns()
	{
		this.hasStripedColumns = true;
		return this;
	}

	public TableBuilder withHoverableRows()
	{
		this.hasHoverableRows = true;
		return this;
	}

	public TableBuilder withBorders()
	{
		this.hasBorders = true;
		return this;
	}

	public TableBuilder borderless()
	{
		this.isBorderless = true;
		return this;
	}

	public TableBuilder withoutHead()
	{
		this.withoutHead = true;
		return this;
	}

	public TableBuilder withSmallPadding()
	{
		this.hasSmallPadding = true;
		return this;
	}

	public TableBuilder withGroupDivider()
	{
		this.hasGroupDivider = true;
		return this;
	}

	public TableBuilder withAlignment(TableAlignDV alignment)
	{
		this.alignment = alignment;
		return this;
	}

	public TableTag build()
	{
		TableTag table = table().withClass(buildClassString());
		TheadTag thead = thead();
		TbodyTag tbody = tbody()
		    .withCondClass(hasGroupDivider, "table-group-divider");

		// Add header row
		TrTag tr = tr();

		for (HtmlTableColumn column : columns)
		{
			String gridWidthString = "auto";

			if (column.getGridWidth() != null)
			{
				gridWidthString = column.getGridWidth().toString();
			}

			ThTag header;
			if (column.getHeaderName() != null)
			{
				header = th(column.getHeaderName()).withScope("col")
				    .withClass("col-" + gridWidthString);
			} else
			{
				header = th().withScope("col")
				    .withClass("col-" + gridWidthString);
			}

			tr.with(header);
		}

		if (tableHeadColor == null)
		{
			thead.with(tr);

		} else
		{
			thead.with(tr).withClass("table-" + tableHeadColor.toString());
		}

		if (showTableHead())
		{
			table.with(thead);
		}

		// Add data rows
		for (TableRow row : rows)
		{
			tr = tr();

			for (int i = 0; i < columns.size(); i++)
			{
				HtmlTableColumn column = columns.get(i);

				String gridWidthString = "auto";

				if (column.getGridWidth() != null)
				{
					gridWidthString = column.getGridWidth().toString();
				}

				Object value = row.getValue(i);

				DomContent cell;

				if (column.getType() == null
				    || column.getType().equals(TableColumnTypeDV.TEXT))
				{
					cell = td(value.toString())
					    .withClass("col-" + gridWidthString);

				} else if (column.getType().equals(TableColumnTypeDV.BUTTON))
				{
					cell = td().with(

					    (ButtonTag) value
					).withClass("col-" + gridWidthString);

				} else if (column.getType().equals(TableColumnTypeDV.ICON))
				{
					// Icon aus IconBuilder
					cell = td((ITag) value).withClass("col-" + gridWidthString);
					// Alten Wert löschen
//					cell = td(
//					    //
//					    i().withClass((String) value)
//					).withClass("col-" + gridWidthString);
				}

				else if (column.getType().equals(TableColumnTypeDV.FORMCONTROL))
				{
					cell = td().with(

					    (DomContent) value
					).withClass("col-" + gridWidthString);

				} else
				{
					throw new IllegalArgumentException(
					    "Invalid column type: " + column.getType()
					);
				}

				tr.with(cell);
			}

			tbody.with(tr);
		}

		table.with(tbody);

		return table;
	}

	private boolean showTableHead()
	{
		boolean result = false;

		for (HtmlTableColumn column : columns)
		{
			if (StringUtils.isNotBlank(column.getHeaderName()))
			{
				result = true;
			}
		}

		if (withoutHead)
		{
			result = false;
		}

		return result;
	}

	private String buildClassString()
	{
		StringBuilder builder = new StringBuilder();

		builder.append("table");

		if (tableColor != null)
		{
			builder.append(" ");

			builder.append("table-" + tableColor.toString());
		}

		if (borderColor != null)
		{
			builder.append(" ");

			builder.append("border-" + borderColor.toString());
		}

		if (hasStripedRows)
		{
			builder.append(" ");

			builder.append("table-striped");
		}

		if (hasStripedColumns)
		{
			builder.append(" ");

			builder.append("table-striped-columns");
		}

		if (hasHoverableRows)
		{
			builder.append(" ");

			builder.append("table-hover");
		}

		if (hasBorders)
		{
			builder.append(" ");

			builder.append("table-bordered");

		}

		if (isBorderless)
		{
			builder.append(" ");

			builder.append("table-borderless");
		}

		if (hasSmallPadding)
		{
			builder.append(" ");

			builder.append("table-sm");
		}

		if (alignment != null)
		{
			builder.append(" ");

			builder.append(alignment.toString());
		}

		return builder.toString();
	}

	public class HtmlTableColumn
	{
		private final String headerName;
		private final TableColumnTypeDV columnType;
		private GridWidthDV gridWidth;
		private boolean withWordWrap;

		public HtmlTableColumn(String headerName, TableColumnTypeDV columnType)
		{
			this.headerName = headerName;
			this.columnType = columnType;
		}

		public HtmlTableColumn(
		    String headerName, TableColumnTypeDV columnType,
		    GridWidthDV gridWidth
		)
		{
			this.gridWidth = gridWidth;
			this.headerName = headerName;
			this.columnType = columnType;
		}

		public String getHeaderName()
		{
			return headerName;
		}

		public TableColumnTypeDV getType()
		{
			return columnType;
		}

		private GridWidthDV getGridWidth()
		{
			return gridWidth;
		}

		private boolean hasWordWrap()
		{
			return true;
		}
	}

	public enum TableColumnTypeDV
	{
		FORMCONTROL, BUTTON, TEXT, ICON
	}

	public enum TableColorDV
	{
		PRIMARY("primary"), //
		SECONDARY("secondary"), //
		SUCCESS("success"), //
		DANGER("danger"), //
		WARNING("warning"), //
		INFO("info"), //
		LIGHT("light"), //
		DARK("dark");

		private final String value;

		TableColorDV(String value)
		{
			this.value = value;
		}

		@Override
		public String toString()
		{
			return value;
		}
	}

	public enum TableHeadColorDV
	{
		LIGHT("light"), //
		DARK("dark");

		private final String value;

		TableHeadColorDV(String value)
		{
			this.value = value;
		}

		@Override
		public String toString()
		{
			return value;
		}
	}

	public enum TableAlignDV
	{
		MIDDLE("align-middle"), //
		BOTTOM("align-bottom");

		private final String value;

		TableAlignDV(String value)
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
