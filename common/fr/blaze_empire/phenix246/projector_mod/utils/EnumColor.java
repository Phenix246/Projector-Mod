 /*******************************************************************************
 * Copyright (c) 2014, Phenix246
 *
 * This work is made available under the terms of the Creative Commons Attribution License:
 * http://creativecommons.org/licenses/by-nc-sa/4.0/deed.en
 * Contact the author for use the source
 * 
 * Cette œuvre est mise à disposition selon les termes de la Licence Creative Commons Attribution:
 * http://creativecommons.org/licenses/by-nc-sa/4.0/deed.fr
 * Contacter l'auteur pour utiliser les sources
 ******************************************************************************/

package fr.blaze_empire.phenix246.projector_mod.utils;

public enum EnumColor
{
	WHITE(255, 255, 255),
	ORANGE(255, 165, 0),
	MAGENTA(255, 0, 255),
	LIGHT_BLUE(173, 216, 230),

	YELLOW(255, 255, 0),
	LIME(0, 255, 0),
	PINK(255, 192, 203),
	GRAY(128, 128, 128),

	LIGHT_GRAY(211, 211, 211),
	CYAN(0, 128, 128),
	PURPLE(128, 0, 128),
	BLUE(0, 0, 255),

	BROWN(165, 42, 42),
	GREEN(0, 128, 0),
	RED(255, 0, 0),
	Black(0, 0, 0),
	
	////////////////////////////////

	G_WHITE(255, 255, 255),
	G_ORANGE(240, 204, 174),
	G_MAGENTA(255, 184, 240),
	G_LIGHT_BLUE(194, 215, 240),

	G_YELLOW(245, 245, 174),
	G_LIME(204, 235, 163),
	G_PINK(250, 204, 219),
	G_GRAY(184, 184, 184),

	G_LIGHT_GRAY(215, 215, 215),
	G_CYAN(184, 204, 215),
	G_PURPLE(204, 179, 255),
	G_BLUE(174, 184, 255),

	G_BROWN(194, 184, 174),
	G_GREEN(194, 204, 174),
	G_RED(215, 174, 174),
	G_Black(163, 163, 163),
	
	////////////////////////////////
	
	S_WHITE(255, 255, 255),
	S_ORANGE(243, 221, 198),
	S_MAGENTA(235, 210, 234),
	S_LIGHT_BLUE(227, 238, 253),

	S_YELLOW(237, 234, 181),
	S_LIME(207, 237, 205),
	S_PINK(253, 226, 241),
	S_GRAY(198, 198, 198),

	S_LIGHT_GRAY(224, 225, 226),
	S_CYAN(205, 226, 236),
	S_PURPLE(211, 195, 225),
	S_BLUE(195, 199, 227),

	S_BROWN(205, 196, 190),
	S_GREEN(180, 218, 180),
	S_RED(255, 180, 180),
	S_Black(198, 198, 198),
	;
	

	private short red, green, blue;
	public static final EnumColor[] VALUES = values();

	EnumColor(int red, int green, int blue)
	{
		this.red = (short)red;
		this.green = (short)green;
		this.blue = (short)blue;
	}

	public static EnumColor fromMeta(int id)
	{
		if((id < 0) || (id >= VALUES.length))
			return VALUES[0];
		return VALUES[id];
	}

	public short getRed()
	{
		return this.red;
	}

	public short getGreen()
	{
		return this.green;
	}

	public short getBlue()
	{
		return this.blue;
	}
}
