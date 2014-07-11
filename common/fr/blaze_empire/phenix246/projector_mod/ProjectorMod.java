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

package fr.blaze_empire.phenix246.projector_mod;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import org.apache.logging.log4j.Logger;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.blaze_empire.phenix246.projector_mod.blocks.BlockEmitter;
import fr.blaze_empire.phenix246.projector_mod.proxy.CommonProxy;
import fr.blaze_empire.phenix246.projector_mod.tileentity.*;


@Mod(modid = ProjectorMod.MODID, name = ProjectorMod.MOD_NAME, version = ProjectorMod.MOD_VERSION)
public class ProjectorMod
{
	// Instance
	@Instance(ProjectorMod.MODID)
	public static ProjectorMod modInstance;

	// Mod Infos
	public static final String MODID = "projector_mod";
	public static final String MOD_NAME = "Projector Mod";
	public static final String MOD_VERSION = "@VERSION@";

	// Proxy
	@SidedProxy(clientSide = "fr.blaze_empire.phenix246.projector_mod.proxy.ClientProxy", serverSide = "fr.blaze_empire.phenix246.projector_mod.proxy.CommonProxy")
	public static CommonProxy proxy;

	public static Block emitter, transmitter;

	// /** Light Machines **/
	// public static Block lightMachineEmitter, lightMachineTransmitter,
	// lightMachineReceiver, lightMachineDyer;
	// public static Block lightMachineSphericalTransmitter,
	// lightMachineTransmitterAndGate, lightMachineTransmitterOrGate;
	// public static Block lightMachineTransmitterNotGate,
	// lightMachineTransmitterXorGate;
	
	public static Logger SpotLightLogger;

	public static CreativeTabs creativeTab = new CreativeTabs("TabsProjectorMod")
	{
		@Override
		@SideOnly(Side.CLIENT)
		public Item getTabIconItem()
		{
			return Item.getItemFromBlock(fr.blaze_empire.phenix246.projector_mod.ProjectorMod.emitter);
		}
	};

	@EventHandler
	public void PreInit(FMLPreInitializationEvent event)
	{
		 SpotLightLogger = event.getModLog();

		/** Configuration **/

		/** block **/
		emitter = new BlockEmitter();
//		spotLightTransmitter = new BlockSpotLightTransmitter();;
//
		GameRegistry.registerBlock(emitter, "block_emitter");
//		GameRegistry.registerBlock(spotLightTransmitter, "block_transmitter");

		/** Render **/
		proxy.registerRender();
	}

	@EventHandler
	public void Init(FMLInitializationEvent event)
	{
		/** Tile Entity **/
		GameRegistry.registerTileEntity(TileEntitySpotlight.class, "TileEntitySpotlight");
		GameRegistry.registerTileEntity(TileEntityEmitter.class, "TileEntityEmitter");
		GameRegistry.registerTileEntity(TileEntityReceiver.class, "TileEntityReceiver");
		GameRegistry.registerTileEntity(TileEntityTransmitter.class, "TileEntityTransmitter");
	}

	@EventHandler
	public void PostInit(FMLPostInitializationEvent event)
	{
		/** Recipes **/
		GameRegistry.addRecipe(new ItemStack(emitter, 1, 0), new Object[] {"GGG", "CRC", "CIC", 'G', Blocks.glass, 'C', Blocks.cobblestone, 'R', Blocks.redstone_lamp, 'I', Items.iron_ingot});
//		GameRegistry.addRecipe(new ItemStack(spotLightTransmitter, 1, 0), new Object[] {"GGG", "CRC", "CDC", 'G', Blocks.glass, 'C', Blocks.cobblestone, 'R', Blocks.redstone_lamp, 'D', Blocks.daylight_detector});

	}
}
