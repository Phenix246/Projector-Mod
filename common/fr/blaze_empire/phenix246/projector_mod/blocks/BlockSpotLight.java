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

import fr.blaze_empire.phenix246.projector_mod.ProjectorMod;
import fr.blaze_empire.phenix246.projector_mod.tileentity.TileEntitySpotlight;
import fr.blaze_empire.phenix246.projector_mod.utils.MathsHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockSpotLight extends BlockContainer
{
	/////////////////////////////////////////////////////////////////////////////////////////////////////

	public BlockSpotLight()
	{
		super(Material.piston);
		this.setLightLevel(0.625F);
		this.setHardness(2.0F);
		this.setResistance(2.0F);

		this.setCreativeTab(ProjectorMod.creativeTab);
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////
	
	@Override
	public TileEntity createNewTileEntity(World world, int metadata)
	{
		return new TileEntitySpotlight();
	}

	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase player, ItemStack stack)
	{
		int direction = MathsHelper.determineOrientation(world, x, y, z, player);
		if(stack.getItemDamage() == 0)
		{
			TileEntity tile = world.getTileEntity(x, y, z);
			if(tile instanceof TileEntitySpotlight)
			{
				TileEntitySpotlight te = (TileEntitySpotlight)tile;
				te.setDirection(direction);
				world.markBlockForUpdate(x, y, z);
			}
		}
	}
	
}
