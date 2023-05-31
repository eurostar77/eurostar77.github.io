package com.bueffeltier.ui.html;

import java.awt.Image;
import java.util.ArrayList;

/**
 * Layouts für Seiten.
 * 
 * @author Sven Guderjahn <sven.guderjahn@bueffeltier.com>
 */
public class Layout
{
	// todo: layouts werden später aus der db geladen.

	private int themeColor;
	private Image favicon16x16; // html oder css?
	private Image logo;
	private String viewport;
	private String[] scripts;
	// Ich verzichte darauf, Header und Spalten anzubieten. Header wird vorerst
	// als Artikel realisiert. Spalten ggf. durch Content-Hanlder.

	/**
	 *
	 */
	public Layout()
	{
		init();
	}

	private void init()
	{

	}

	/**
	 *
	 * @return
	 */
	public static ArrayList<String> getLayoutNames()
	{
		ArrayList<String> names = new ArrayList<>();
		names.add("Standard-Layout");
		names.add("Spezial-Layout");
		names.add("Test-Layout");
		return names;
	}

	/**
	 *
	 * @param layoutName
	 * @return
	 */
	public String getCss(String layoutName)
	{
		// Datenbankabfrage des Layout...
		return "";
	}
}
