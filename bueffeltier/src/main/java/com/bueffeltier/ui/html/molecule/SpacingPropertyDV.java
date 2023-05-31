package com.bueffeltier.ui.html.molecule;

public enum SpacingPropertyDV
{
	MARGIN("m"), PADDING("p");

	private final String value;

	SpacingPropertyDV(String value)
	{
		this.value = value;
	}

	@Override
	public String toString()
	{
		return value;
	}
}
