package com.bueffeltier.ui.html.customj2html;

import j2html.tags.EmptyTag;

public final class IncrementTag extends EmptyTag<IncrementTag>
{
	public IncrementTag(Integer number)
	{
		super("");
		number++;
	}
}