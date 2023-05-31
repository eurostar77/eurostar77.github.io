package com.bueffeltier.ui.html.molecule;

public enum SpacingSizeDV
{
	NULL("0"), ONE("1"), TWO("2"), THREE("3"), FOUR("4"), FIVE("5"),
	AUTO("auto");

	private final String value;

	SpacingSizeDV(String value)
	{
		this.value = value;
	}

	@Override
	public String toString()
	{
		return value;
	}
}
