package com.bueffeltier.logic.foundation;

public enum PermissionDV
{
	BESUCHER(0), BENUTZER(1), MITGLIED(2), PREMIUM_MITGLIED(3), ADMIN(4),
	//
	;

	private final int number;

	private PermissionDV(int number)
	{
		this.number = number;
	}

	public int getNumber()
	{
		return number;
	}
}
