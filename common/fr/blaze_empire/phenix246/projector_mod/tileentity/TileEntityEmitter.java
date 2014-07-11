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

package fr.blaze_empire.phenix246.projector_mod.tileentity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;

public class TileEntityEmitter extends TileEntitySpotlight
{
	private short red;
	private short green;
	private short blue;
	private int oldX;
	private int oldY;
	private int oldZ;

	/////////////////////////////////////////////////////////////////////////////////////////////////////

	public TileEntityEmitter()
	{
		red = 255;
		green = 255;
		blue = 255;
		oldX = 0;
		oldY = -1;
		oldZ = 0;
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////

	public void readFromNBT(NBTTagCompound nbtTag)
	{
		super.readFromNBT(nbtTag);

		this.red = nbtTag.getShort("color_red");
		this.green = nbtTag.getShort("color_green");
		this.blue = nbtTag.getShort("color_blue");

		this.oldX = nbtTag.getInteger("old_x");
		this.oldY = nbtTag.getInteger("old_y");
		this.oldZ = nbtTag.getInteger("old_z");
	}

	public void writeToNBT(NBTTagCompound nbtTag)
	{
		super.writeToNBT(nbtTag);

		nbtTag.setShort("color_red", this.red);
		nbtTag.setShort("color_green", this.green);
		nbtTag.setShort("color_blue", this.blue);

		nbtTag.setInteger("old_x", this.oldX);
		nbtTag.setInteger("old_y", this.oldY);
		nbtTag.setInteger("old_z", this.oldZ);
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////

	/** Setters **/
	public void setRed(int i)
	{
		this.red = (short)i;
	}

	public void setGreen(int i)
	{
		this.green = (short)i;
	}

	public void setBlue(int i)
	{
		this.blue = (short)i;
	}

	public void setOldX(int i)
	{
		this.oldX = i;
	}

	public void setOldY(int i)
	{
		this.oldY = i;
	}

	public void setOldZ(int i)
	{
		this.oldZ = i;
	}

	public void setOldCoor(int i, int j, int k)
	{
		setOldX(i);
		setOldY(j);
		setOldZ(k);
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////

	/** Getters **/
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

	public int getOldX()
	{
		return this.oldX;
	}

	public int getOldY()
	{
		return this.oldY;
	}

	public int getOldZ()
	{
		return this.oldZ;
	}
	
	public int[] getOldCoor()
	{
		return new int[] {getOldX(), getOldY(), getOldZ()};
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////

	public Packet getDescriptionPacket()
	{
		NBTTagCompound nbttagcompound = new NBTTagCompound();
		this.writeToNBT(nbttagcompound);
		return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 3, nbttagcompound);
	}

	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt)
	{
		this.readFromNBT(pkt.func_148857_g());
	}
}
