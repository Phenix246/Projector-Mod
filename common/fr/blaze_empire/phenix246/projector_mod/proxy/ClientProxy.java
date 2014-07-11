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

package fr.blaze_empire.phenix246.projector_mod.proxy;

import cpw.mods.fml.client.registry.ClientRegistry;
import fr.blaze_empire.phenix246.projector_mod.client.TileEntitySpotlightRender;
import fr.blaze_empire.phenix246.projector_mod.proxy.CommonProxy;
import fr.blaze_empire.phenix246.projector_mod.tileentity.TileEntityEmitter;

public class ClientProxy extends CommonProxy
{
	@Override
	public void registerRender()
	{
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityEmitter.class, new TileEntitySpotlightRender());
	}
}