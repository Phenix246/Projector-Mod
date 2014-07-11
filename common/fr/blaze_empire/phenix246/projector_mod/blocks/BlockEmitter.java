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

package fr.blaze_empire.phenix246.projector_mod.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.common.registry.GameRegistry;
import fr.blaze_empire.phenix246.projector_mod.tileentity.TileEntityEmitter;
import fr.blaze_empire.phenix246.projector_mod.utils.EnumColor;
import fr.blaze_empire.phenix246.projector_mod.utils.MathsHelper;
import fr.blaze_empire.phenix246.projector_mod.ProjectorMod;
import fr.blaze_empire.phenix246.projector_mod.tileentity.TileEntitySpotlight;

public class BlockEmitter extends BlockSpotLight
{
	private Block recepter;
	private IIcon sides, face;

	// ///////////////////////////////////////////////////////////////////////////////////////////////////

	public BlockEmitter()
	{
		super();

		this.setBlockName("emiter");
	}

	// ///////////////////////////////////////////////////////////////////////////////////////////////////

	// Icons
	public void registerBlockIcons(IIconRegister iIconRegister)
	{
		face = iIconRegister.registerIcon(ProjectorMod.MODID + ":emitter");
		sides = iIconRegister.registerIcon(ProjectorMod.MODID + ":nothing");
	}

	public IIcon getIcon(int side, int damage)
	{
		if(side == 3)
			return face;
		else
			return sides;
	}

	public IIcon getIcon(IBlockAccess iba, int x, int y, int z, int side)
	{
		TileEntitySpotlight te = (TileEntitySpotlight)iba.getTileEntity(x, y, z);
		int direction = ((TileEntitySpotlight)te).getDirection();

		if(direction == 0 && side == 0)
			return face;
		if(direction == 1 && side == 1)
			return face;
		if(direction == 2 && side == 3)
			return face;
		if(direction == 3 && side == 4)
			return face;
		if(direction == 4 && side == 2)
			return face;
		if(direction == 5 && side == 5)
			return face;
		else
			return sides;
	}

	// ///////////////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public TileEntity createNewTileEntity(World world, int metadata)
	{
		return new TileEntityEmitter();
	}

	// ///////////////////////////////////////////////////////////////////////////////////////////////////

	public void updateTick(World world, int x, int y, int z, Random rand)
	{

		// get active
		// boolean isActive = false;
		
		// Send activate
		MathsHelper.toogleActivate(world, x, y, z, world.isBlockIndirectlyGettingPowered(x, y, z));
		// check new position
		TileEntity tile = world.getTileEntity(x, y, z);
		if(tile instanceof TileEntityEmitter)
		{
			TileEntityEmitter te = (TileEntityEmitter)tile;
			int[] oldCoor = te.getOldCoor();
			int[] coor = MathsHelper.getRecepeterCoor(world, x, y, z);
			if(!MathsHelper.isSame(coor[0], coor[1], coor[2], oldCoor[0], oldCoor[1], oldCoor[2]))
			{
				MathsHelper.setState(world, x, y, z, false);
				te.setOldCoor(coor[0], coor[1], coor[2]);
				world.markBlockForUpdate(oldCoor[0], oldCoor[1], oldCoor[2]);
			}
		}
	}

	public void onBlockAdded(World world, int x, int y, int z)
	{
		TileEntity tile = world.getTileEntity(x, y, z);
		if(tile instanceof TileEntityEmitter && tile != null)
		{
			TileEntityEmitter te = ((TileEntityEmitter)tile);
			/** determinate the colors **/
			short rgb1 = 255;
			short rgb2 = 255;
			short rgb3 = 255;

			rgb1 = te.getRed();
			rgb2 = te.getGreen();
			rgb3 = te.getBlue();

			Block color = MathsHelper.getBlock(world, x, y, z);
			int meta = MathsHelper.getBlockMetadata(world, x, y, z);

			if(color == Blocks.stained_glass)
			{
				int metadata = MathsHelper.getBlockMetadata(world, x, y, z);
				rgb1 = EnumColor.fromMeta(metadata + 16).getRed();
				rgb2 = EnumColor.fromMeta(metadata + 16).getGreen();
				rgb3 = EnumColor.fromMeta(metadata + 16).getBlue();
			}
			if(color == Blocks.ice)
			{
				rgb1 = 171;
				rgb2 = 202;
				rgb3 = 255;
			}
			// Block cGlass = GameRegistry.findBlock("phenix246_mod_build",
			// "block_clean_glass");
			// if(cGlass != null && color == cGlass)
			// {
			// if(color == cGlass)
			// {
			// int metadata = MathsHelper.getBlockMetadata(world, y, y, z);
			// rgb1 = EnumColor.fromMeta(metadata + 32).getRed();
			// rgb2 = EnumColor.fromMeta(metadata + 32).getGreen();
			// rgb3 = EnumColor.fromMeta(metadata + 32).getBlue();
			// }
			// }

			/** send colors **/
			if(te.getRed() != rgb1)
				te.setRed(rgb1);
			if(te.getGreen() != rgb2)
				te.setGreen(rgb2);
			if(te.getBlue() != rgb3)
				te.setBlue(rgb3);
			if(te.getRed() != rgb1 || te.getGreen() != rgb2 || te.getBlue() != rgb3)
				MathsHelper.sendRGB(world, x, y, z, rgb1, rgb2, rgb3);
		}
	}

	public void breakBlock(World world, int x, int y, int z, Block oldBlock, int oldMetadata)
	{
		// Set Inactive
		MathsHelper.setInactiveBreak(world, x, y, z);

		super.breakBlock(world, x, y, z, oldBlock, oldMetadata);
	}
}