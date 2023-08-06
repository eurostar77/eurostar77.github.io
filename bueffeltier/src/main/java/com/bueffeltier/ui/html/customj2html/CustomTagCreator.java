package com.bueffeltier.ui.html.customj2html;

public class CustomTagCreator
{
	private CustomTagCreator()
	{
		//
	}

	public static IncrementTag increment(Integer number)
	{
		return new IncrementTag(number);
	}

//	public static methodTag method(Integer number)
//	{
//		return new IncrementTag(number);
//	}
}
