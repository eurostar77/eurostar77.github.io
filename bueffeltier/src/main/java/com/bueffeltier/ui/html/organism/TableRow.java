package com.bueffeltier.ui.html.organism;

import java.util.List;

public class TableRow
{
	private List<Object> values = null;

	public TableRow()
	{
		//
	}

	public TableRow(List<Object> values)
	{
		this.values = values;
	}

	public List<Object> getValues()
	{
		return values;
	}

	public Object getValue(int index)
	{
		return values.get(index);
	}

	public void addValue(Object value)
	{
		values.add(value);
	}

	public int size()
	{
		return values.size();
	}

	@Override
	public String toString()
	{
		return values.toString();
	}

//	@Override
//	public boolean equals(Object obj)
//	{
//		if (obj == this)
//		{
//			return true;
//		}
//		if (!(obj instanceof TableRow))
//		{
//			return false;
//		}
//		TableRow other = (TableRow) obj;
//		return Arrays.equals(this.values, other.values);
//	}
//
//	@Override
//	public int hashCode()
//	{
//		return Collections.hashCode(values);
//	}
}
