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

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import fr.blaze_empire.phenix246.projector_mod.ProjectorMod;
import fr.blaze_empire.phenix246.projector_mod.tileentity.*;

public class MathsHelper
{
	
	private static Block block1 = ProjectorMod.emitter;
	private static Block block2 = ProjectorMod.transmitter;
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////

	public static int determineOrientation(World world, int x, int y, int z, EntityLivingBase player)
	{
		if(MathHelper.abs((float)player.posX - (float)x) < 2.0F && MathHelper.abs((float)player.posZ - (float)z) < 2.0F)
		{
			double d0 = player.posY + 1.82D - (double)player.yOffset;
			if(d0 - (double)y > 2.0D)
				return 1;
			if((double)y - d0 > 0.0D)
				return 0;
		}
		return (MathHelper.floor_double((double)(player.rotationYaw * 4.0F / 360.0F) + 2.5D) & 3) + 2;
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	
	private static boolean isT(World world, int x, int y, int z)
	{
		return !world.getBlock(x, y, z).isOpaqueCube();
	}
	
	public static boolean isSame(int x1, int y1, int z1, int x2, int y2, int z2)
	{
		if(x1 == x2 && y1 == y2 && z1 == z2)
			return true;
		return false;
	}
	
	public static int getDirection(World world, int x, int y, int z)
	{
		TileEntity tile = world.getTileEntity(x, y, z);
		if(tile instanceof TileEntitySpotlight)
		{
			TileEntitySpotlight te = (TileEntitySpotlight)tile;
			return te.getDirection();
		}
		return 0;
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public static int getMaxLengh(World world, int x, int y, int z)
	{
		int direction = getDirection(world, x, y, z);
		switch(direction)
		{
		case 0:
			return getMaxBottom(world, x, y, z);
		case 1:
			return getMaxTop(world, x, y, z);
		case 2:
			return getMaxSouth(world, x, y, z);
		case 3:
			return getMaxWest(world, x, y, z);
		case 4:
			return getMaxNorth(world, x, y, z);
		case 5:
			return getMaxEast(world, x, y, z);
		}
		return 0;
	}
	
	/** +Y */
	public static int getMaxTop(World world, int x, int y, int z)
	{
		int i = 1;
		boolean a = true;
		while(isT(world, x, y + i, z) && a)
		{
			i++;
			if(i > 256)
				a = false;
		}
		return i;
	}

	/** -Y */
	public static int getMaxBottom(World world, int x, int y, int z)
	{
		int i = 1;
		boolean a = true;
		while(isT(world, x, y - i, z) && a)
		{
			i++;
			if(i > 256)
				a = false;
		}
		return i;
	}

	/** -Z */
	public static int getMaxNorth(World world, int x, int y, int z)
	{
		int i = 1;
		boolean a = true;
		while(isT(world, x, y, z - i) && a)
		{
			i++;
			if(i > 256)
				a = false;
		}
		return i;
	}

	/** +Z */
	public static int getMaxSouth(World world, int x, int y, int z)
	{
		int i = 1;
		boolean a = true;
		while(isT(world, x, y, z + i) && a)
		{
			i++;
			if(i > 256)
				a = false;
		}
		return i;
	}

	/** -X */
	public static int getMaxWest(World world, int x, int y, int z)
	{
		int i = 1;
		boolean a = true;
		while(isT(world, x - i, y, z) && a)
		{
			i++;
			if(i > 256)
				a = false;
		}
		return i;
	}

	/** +X */
	public static int getMaxEast(World world, int x, int y, int z)
	{
		int i = 1;
		boolean a = true;
		while(isT(world, x + i, y, z) && a)
		{
			i++;
			if(i > 256)
				a = false;
		}
		return i;
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////

	public static Block getBlock(World world, int x, int y, int z)
	{
		int direction = getDirection(world, x, y, z);
		switch(direction)
		{
		case 0:
			return world.getBlock(x, y - 1, z);
		case 1:
			return world.getBlock(x, y + 1, z);
		case 2:
			return world.getBlock(x, y, z + 1);
		case 3:
			return world.getBlock(x - 1, y, z);
		case 4:
			return world.getBlock(x, y, z - 1);
		case 5:
			return world.getBlock(x + 1, y, z);
		}
		return null;
	}
	
	public static int getBlockMetadata(World world, int x, int y, int z)
	{
		int direction = getDirection(world, x, y, z);
		switch(direction)
		{
		case 0:
			return world.getBlockMetadata(x, y - 1, z);
		case 1:
			return world.getBlockMetadata(x, y + 1, z);
		case 2:
			return world.getBlockMetadata(x, y, z + 1);
		case 3:
			return world.getBlockMetadata(x - 1, y, z);
		case 4:
			return world.getBlockMetadata(x, y, z - 1);
		case 5:
			return world.getBlockMetadata(x + 1, y, z);
		}
		return 0;
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////

	public static int[] getRecepeterCoor(World world, int x, int y, int z)
	{
		int direction = getDirection(world, x, y, z);
		int distanceT = MathsHelper.getMaxLengh(world, x, y, z);
		switch(direction)
		{
		case 0:
			return new int[] {x, y - distanceT, z};
		case 1:
			return new int[] {x, y + distanceT, z};
		case 2:
			return new int[] {x, y, z + distanceT};
		case 3:
			return new int[] {x - distanceT, y, z};
		case 4:
			return new int[] {x, y, z - distanceT};
		case 5:
			return new int[] {x + distanceT, y, z};
		default:
			return new int[] {x, y, z};
		}
	}
	
	public static Block getRecepeterBlock(World world, int x, int y, int z)
	{
		int[] coor = getRecepeterCoor(world, x, y, z);
		return world.getBlock(coor[0], coor[1], coor[2]);
	}
	
	public static TileEntity getRecepeterTile(World world, int x, int y, int z)
	{
		int[] coor = getRecepeterCoor(world, x, y, z);
		return world.getTileEntity(coor[0], coor[1], coor[2]);
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public static void sendRGB(World world, int x, int y, int z, int rgb1, int rgb2, int rgb3)
	{
		int[] coor = getRecepeterCoor(world, x, y, z);
		Block recepter = world.getBlock(coor[0], coor[1], coor[2]);
		if(recepter == block2)
			setRGB(world, coor[0], coor[1], coor[2], rgb1, rgb2, rgb3);
	}

	private static void setRGB(World world, int x, int y, int z, int rgb1, int rgb2, int rgb3)
	{
		TileEntity tile = world.getTileEntity(x, y, z);
		if(tile instanceof TileEntityEmitter)
		{
			TileEntityEmitter te = (TileEntityEmitter)tile;
			te.setRed(rgb1);
			te.setGreen(rgb2);
			te.setBlue(rgb3);
			world.markBlockForUpdate(x, y, z);
		}
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////
		
	public static void toogleActivate(World world, int x, int y, int z, boolean isActive)
	{
		int distanceT = getMaxLengh(world, x, y, z);
		int direction = getDirection(world, x, y, z);
		switch(direction)
		{
		case 0: // bottom /** -Y */
			setState(world, x, y, z, isActive);
			break;
		case 1: // Top /** +Y */
			setState(world, x, y, z, isActive);
			break;
		case 2: // South /** +Z*/
			setState(world, x, y, z, isActive);
			break;
		case 3: // West /** -X */
			setState(world, x, y, z, isActive);
			break;
		case 4: // South /** -Z*/
			setState(world, x, y, z, isActive);
			break;
		case 5: // East /** +X */ //
			setState(world, x, y, z, isActive);
			break;
		}
	}
	
	public static void setInactiveBreak(World world, int x, int y, int z)
	{
		setState(world, x, y, z, false);
	}
	
	public static void setState(World world, int x, int y, int z, boolean state)
	{
		TileEntity tile = world.getTileEntity(x, y, z);
		if(tile instanceof TileEntitySpotlight)
		{
			TileEntitySpotlight te = (TileEntitySpotlight)tile;
			byte direction = te.getDirection();
			TileEntity recepter = getRecepeterTile(world, x, y, z);
			if(recepter instanceof TileEntityTransmitter || recepter instanceof TileEntityReceiver)
			{
				if(recepter instanceof TileEntityTransmitter)
				{
					TileEntityTransmitter transmitter = (TileEntityTransmitter)recepter;
					byte direction2 = transmitter.getDirection();
					int side =  ForgeDirection.OPPOSITES[direction2];
					
					if(side == 0)
						transmitter.setBottom(state);
					if(side == 1)
						transmitter.setTop(state);
					if(side == 2)
						transmitter.setNorth(state);
					if(side == 3)
						transmitter.setSouth(state);
					if(side == 4)
						transmitter.setWest(state);
					if(side == 5)
						transmitter.setEast(state);
				}
				if(recepter instanceof TileEntityReceiver)
				{
					TileEntityReceiver receiver = (TileEntityReceiver)recepter;
					byte direction2 = receiver.getDirection();
					int side =  ForgeDirection.OPPOSITES[direction2];
					
					if(side == 0)
						receiver.setBottom(state);
					if(side == 1)
						receiver.setTop(state);
					if(side == 2)
						receiver.setNorth(state);
					if(side == 3)
						receiver.setSouth(state);
					if(side == 4)
						receiver.setWest(state);
					if(side == 5)
						receiver.setEast(state);
				}
				world.markBlockForUpdate(recepter.xCoord, recepter.yCoord, recepter.zCoord);
			}
		}
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////


	
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////

}
