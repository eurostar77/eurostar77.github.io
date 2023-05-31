package com.bueffeltier.ui.html.molecule;

public enum SpacingSidesDV
{
	TOP("t"), BOTTOM("b"), START("s"), END("e"), X("x"), Y("y"), BLANK("");

	private final String value;

	SpacingSidesDV(String value)
	{
		this.value = value;
	}

	@Override
	public String toString()
	{
		return value;
	}
}
