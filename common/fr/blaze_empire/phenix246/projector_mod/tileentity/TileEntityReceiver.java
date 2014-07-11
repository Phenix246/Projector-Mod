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

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;

public class TileEntityReceiver extends TileEntitySpotlight
{
	private boolean top;
	private boolean bottom;
	private boolean north;
	private boolean south;
	private boolean east;
	private boolean west;
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////

	public TileEntityReceiver()
	{
		top = false;
		bottom = false;
		north = false;
		south = false;
		east = false;
		west = false;
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////

	public void readFromNBT(NBTTagCompound nbtTag)
	{
		super.readFromNBT(nbtTag);

		this.top = nbtTag.getBoolean("top");
		this.bottom = nbtTag.getBoolean("bottom");
		this.north = nbtTag.getBoolean("north");
		this.south = nbtTag.getBoolean("south");
		this.east = nbtTag.getBoolean("east");
		this.west = nbtTag.getBoolean("west");
	}

	public void writeToNBT(NBTTagCompound nbtTag)
	{
		super.writeToNBT(nbtTag);
		
		nbtTag.setBoolean("top", this.top);
		nbtTag.setBoolean("bottom", this.bottom);
		nbtTag.setBoolean("north", this.north);
		nbtTag.setBoolean("south", this.south);
		nbtTag.setBoolean("east", this.east);
		nbtTag.setBoolean("west", this.west);
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////

	/** Setters **/
	public void setTop(boolean b)
	{
		this.top = b;
	}
	
	public void setBottom(boolean b)
	{
		this.bottom = b;
	}

	public void setNorth(boolean b)
	{
		this.north = b;
	}
	
	public void setSouth(boolean b)
	{
		this.south = b;
	}
	
	public void setEast(boolean b)
	{
		this.east = b;
	}
	
	public void setWest(boolean b)
	{
		this.west = b;
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/** Getters **/
	public boolean getTop()
	{
		return this.top;
	}
	
	public boolean getBottom()
	{
		return this.bottom;
	}
	
	public boolean getNorth()
	{
		return this.north;
	}
	
	public boolean getSouth()
	{
		return this.south;
	}
	
	public boolean getEast()
	{
		return this.east;
	}
	
	public boolean getWest()
	{
		return this.west;
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
