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

package fr.blaze_empire.phenix246.projector_mod.client;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.registry.GameRegistry;
import fr.blaze_empire.phenix246.projector_mod.utils.EnumColor;
import fr.blaze_empire.phenix246.projector_mod.ProjectorMod;
import fr.blaze_empire.phenix246.projector_mod.tileentity.TileEntityEmitter;
import fr.blaze_empire.phenix246.projector_mod.tileentity.TileEntityTransmitter;
import fr.blaze_empire.phenix246.projector_mod.utils.MathsHelper;

public class TileEntitySpotlightRender extends TileEntitySpecialRenderer
{
	private static final ResourceLocation texture = new ResourceLocation("textures/entity/beacon_beam.png");

	private double d0, d1, d3, d4, d5, d6, d7, d8, d9, d10, d11, d12, d13, d14, d15, d16, d17, d18, d19, d20, d21, d22, d23, d24, d25, d26, d27, d28, d29, d30;
	private float distance, f0, f1, time, f3;
	private int direction, distanceT, xCoord, yCoord, zCoord;
	private int[] rgb;
	private byte b0;
	private World world;
	private boolean isPowerInput, TEisActive, isActive;
	private Block block1, block2, recepter;

	public TileEntitySpotlightRender()
	{
		this.func_147497_a(TileEntityRendererDispatcher.instance);
	}

	@Override
	public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float tick)
	{
		this.renderLaser((TileEntityEmitter)tileentity, x, y, z, tick);
	}

	public void renderLaser(TileEntityEmitter tileentity, double x, double y, double z, float tick)
	{
		block1 = ProjectorMod.emitter;
		block2 = ProjectorMod.transmitter;
		xCoord = tileentity.xCoord;
		yCoord = tileentity.yCoord;
		zCoord = tileentity.zCoord;
		world = tileentity.getWorldObj();
		direction = tileentity.getDirection();

		f0 = 0.0F;
		f1 = 1.0F;
		d0 = 0.0D;
		d1 = 1.0D;

		distanceT = MathsHelper.getMaxLengh(world, xCoord, yCoord, zCoord);
		distance = (MathsHelper.getMaxLengh(world, xCoord, yCoord, zCoord) + 0.5F);
		recepter = MathsHelper.getRecepeterBlock(world, xCoord, yCoord, zCoord);

		if(world.getBlock(xCoord, yCoord, zCoord) == block1)
			isActive = world.isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord);
		if(world.getBlock(xCoord, yCoord, zCoord) == block2) // instance
		{
			TileEntityTransmitter tile = (TileEntityTransmitter)tileentity;
			isActive = tile.isActive();
		}

		// ///////////////////////////////////////////////////////////////

		// Render

		GL11.glAlphaFunc(GL11.GL_GREATER, 0.1F);

		if(isActive)
		{

			Tessellator tessellator = Tessellator.instance;
			this.bindTexture(texture);

			GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, 10497.0F);
			GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, 10497.0F);
			GL11.glDisable(GL11.GL_LIGHTING);
			GL11.glDisable(GL11.GL_CULL_FACE);
			GL11.glDisable(GL11.GL_BLEND);
			GL11.glDepthMask(true);
			OpenGlHelper.glBlendFunc(770, 1, 1, 0);

			time = (float)world.getTotalWorldTime() + tick;
			f3 = -time * 0.2F - (float)MathHelper.floor_float(-time * 0.1F);
			b0 = 1;
			d3 = (double)time * 0.025D * (d1 - (double)(b0 & 1) * 2.5D);

			// ///////////////////////////////////////////////////////////////

			// determine the coordinates

			d5 = (double)b0 * 0.2D;
			d7 = 0.5D + Math.cos(d3 + 2.356194490192345D) * d5;
			d9 = 0.5D + Math.sin(d3 + 2.356194490192345D) * d5;
			d11 = 0.5D + Math.cos(d3 + (Math.PI / 4D)) * d5;
			d13 = 0.5D + Math.sin(d3 + (Math.PI / 4D)) * d5;
			d15 = 0.5D + Math.cos(d3 + 3.9269908169872414D) * d5;
			d17 = 0.5D + Math.sin(d3 + 3.9269908169872414D) * d5;
			d19 = 0.5D + Math.cos(d3 + 5.497787143782138D) * d5;
			d21 = 0.5D + Math.sin(d3 + 5.497787143782138D) * d5;
			d25 = 0.0D;
			d27 = 1.0D;
			d28 = (double)(-f1 + f3);

			d4 = 0.2D;
			d6 = 0.8D;
			d8 = 0.2D;
			d10 = 0.2D;
			d12 = 0.8D;
			d14 = 0.8D;
			d16 = 0.8D;
			d20 = 0.0D;
			d22 = 1.0D;
			d24 = (double)(-f1 + f3);
			d30 = 0.2D;

			// ///////////////////////////////////////////////////////////////

			// determine the colors
			short[] rgb = {255, 255, 255};
			rgb[0] = tileentity.getRed();
			rgb[1] = tileentity.getGreen();
			rgb[2] = tileentity.getBlue();

			// = MathsHelper.getRGB(world, xCoord, yCoord, zCoord, tileentity);
			// // Send colors (RBG)
			// MathsHelper.sendRGB(world, xCoord, yCoord, zCoord, rgb[0],
			// rgb[1], rgb[2]);

			// ///////////////////////////////////////////////////////////////

			tessellator.startDrawingQuads();
			tessellator.setColorRGBA(rgb[0], rgb[1], rgb[2], 32);

			if(direction == 0) // bottom /** -Y */
			{
				d23 = (double)(distance * f1);
				d29 = (double)(distance * f1) * (0.5D / d5) + d28;
				d18 = (double)(distance * f1);
				d26 = (double)(distance * f1) + d24;
				y = y + 0.5D;

				tessellator.addVertexWithUV(x + d7, y - d23, z + d9, d27, d29);
				tessellator.addVertexWithUV(x + d7, y + d0, z + d9, d27, d28);
				tessellator.addVertexWithUV(x + d11, y + d0, z + d13, d25, d28);
				tessellator.addVertexWithUV(x + d11, y - d23, z + d13, d25, d29);

				tessellator.addVertexWithUV(x + d19, y - d23, z + d21, d27, d29);
				tessellator.addVertexWithUV(x + d19, y + d0, z + d21, d27, d28);
				tessellator.addVertexWithUV(x + d15, y + d0, z + d17, d25, d28);
				tessellator.addVertexWithUV(x + d15, y - d23, z + d17, d25, d29);

				tessellator.addVertexWithUV(x + d11, y - d23, z + d13, d27, d29);
				tessellator.addVertexWithUV(x + d11, y + d0, z + d13, d27, d28);
				tessellator.addVertexWithUV(x + d19, y + d0, z + d21, d25, d28);
				tessellator.addVertexWithUV(x + d19, y - d23, z + d21, d25, d29);

				tessellator.addVertexWithUV(x + d15, y - d23, z + d17, d27, d29);
				tessellator.addVertexWithUV(x + d15, y + d0, z + d17, d27, d28);
				tessellator.addVertexWithUV(x + d7, y + d0, z + d9, d25, d28);
				tessellator.addVertexWithUV(x + d7, y - d23, z + d9, d25, d29);

				tessellator.draw();

				GL11.glEnable(GL11.GL_BLEND);
				OpenGlHelper.glBlendFunc(770, 771, 1, 0);
				GL11.glDepthMask(false);

				tessellator.startDrawingQuads();
				tessellator.setColorRGBA(rgb[0], rgb[1], rgb[2], 32);

				tessellator.addVertexWithUV(x + d30, y - d18, z + d4, d22, d26);
				tessellator.addVertexWithUV(x + d30, y + d0, z + d4, d22, d24);
				tessellator.addVertexWithUV(x + d6, y + d0, z + d8, d20, d24);
				tessellator.addVertexWithUV(x + d6, y - d18, z + d8, d20, d26);

				tessellator.addVertexWithUV(x + d14, y - d18, z + d16, d22, d26);
				tessellator.addVertexWithUV(x + d14, y + d0, z + d16, d22, d24);
				tessellator.addVertexWithUV(x + d10, y + d0, z + d12, d20, d24);
				tessellator.addVertexWithUV(x + d10, y - d18, z + d12, d20, d26);

				tessellator.addVertexWithUV(x + d6, y - d18, z + d8, d22, d26);
				tessellator.addVertexWithUV(x + d6, y + d0, z + d8, d22, d24);
				tessellator.addVertexWithUV(x + d14, y + d0, z + d16, d20, d24);
				tessellator.addVertexWithUV(x + d14, y - d18, z + d16, d20, d26);

				tessellator.addVertexWithUV(x + d10, y - d18, z + d12, d22, d26);
				tessellator.addVertexWithUV(x + d10, y + d0, z + d12, d22, d24);
				tessellator.addVertexWithUV(x + d30, y + d0, z + d4, d20, d24);
				tessellator.addVertexWithUV(x + d30, y - d18, z + d4, d20, d26);
			}
			if(direction == 1) // Top /** +Y */
			{
				d23 = (double)(distance * f1);
				d29 = (double)(distance * f1) * (0.5D / d5) + d28;
				d18 = (double)(distance * f1);
				d26 = (double)(distance * f1) + d24;
				y = y + 0.5D;

				tessellator.addVertexWithUV(x + d7, y + d23, z + d9, d27, d29);
				tessellator.addVertexWithUV(x + d7, y + d0, z + d9, d27, d28);
				tessellator.addVertexWithUV(x + d11, y + d0, z + d13, d25, d28);
				tessellator.addVertexWithUV(x + d11, y + d23, z + d13, d25, d29);

				tessellator.addVertexWithUV(x + d19, y + d23, z + d21, d27, d29);
				tessellator.addVertexWithUV(x + d19, y + d0, z + d21, d27, d28);
				tessellator.addVertexWithUV(x + d15, y + d0, z + d17, d25, d28);
				tessellator.addVertexWithUV(x + d15, y + d23, z + d17, d25, d29);

				tessellator.addVertexWithUV(x + d11, y + d23, z + d13, d27, d29);
				tessellator.addVertexWithUV(x + d11, y + d0, z + d13, d27, d28);
				tessellator.addVertexWithUV(x + d19, y + d0, z + d21, d25, d28);
				tessellator.addVertexWithUV(x + d19, y + d23, z + d21, d25, d29);

				tessellator.addVertexWithUV(x + d15, y + d23, z + d17, d27, d29);
				tessellator.addVertexWithUV(x + d15, y + d0, z + d17, d27, d28);
				tessellator.addVertexWithUV(x + d7, y + d0, z + d9, d25, d28);
				tessellator.addVertexWithUV(x + d7, y + d23, z + d9, d25, d29);

				tessellator.draw();

				GL11.glEnable(GL11.GL_BLEND);
				OpenGlHelper.glBlendFunc(770, 771, 1, 0);
				GL11.glDepthMask(false);

				tessellator.startDrawingQuads();
				tessellator.setColorRGBA(rgb[0], rgb[1], rgb[2], 32);

				tessellator.addVertexWithUV(x + d30, y + d18, z + d4, d22, d26);
				tessellator.addVertexWithUV(x + d30, y + d0, z + d4, d22, d24);
				tessellator.addVertexWithUV(x + d6, y + d0, z + d8, d20, d24);
				tessellator.addVertexWithUV(x + d6, y + d18, z + d8, d20, d26);

				tessellator.addVertexWithUV(x + d14, y + d18, z + d16, d22, d26);
				tessellator.addVertexWithUV(x + d14, y + d0, z + d16, d22, d24);
				tessellator.addVertexWithUV(x + d10, y + d0, z + d12, d20, d24);
				tessellator.addVertexWithUV(x + d10, y + d18, z + d12, d20, d26);

				tessellator.addVertexWithUV(x + d6, y + d18, z + d8, d22, d26);
				tessellator.addVertexWithUV(x + d6, y + d0, z + d8, d22, d24);
				tessellator.addVertexWithUV(x + d14, y + d0, z + d16, d20, d24);
				tessellator.addVertexWithUV(x + d14, y + d18, z + d16, d20, d26);

				tessellator.addVertexWithUV(x + d10, y + d18, z + d12, d22, d26);
				tessellator.addVertexWithUV(x + d10, y + d0, z + d12, d22, d24);
				tessellator.addVertexWithUV(x + d30, y + d0, z + d4, d20, d24);
				tessellator.addVertexWithUV(x + d30, y + d18, z + d4, d20, d26);
			}

			if(direction == 2) // South /** +Z */
			{
				d23 = (double)(distance * f1);
				d29 = (double)(distance * f1) * (0.5D / d5) + d28;
				d18 = (double)(distance * f1);
				d26 = (double)(distance * f1) + d24;
				z = z + 0.5D;

				tessellator.addVertexWithUV(x + d7, y + d9, z + d23, d27, d29);
				tessellator.addVertexWithUV(x + d7, y + d9, z + d0, d27, d28);
				tessellator.addVertexWithUV(x + d11, y + d13, z + d0, d25, d28);
				tessellator.addVertexWithUV(x + d11, y + d13, z + d23, d25, d29);

				tessellator.addVertexWithUV(x + d19, y + d21, z + d23, d27, d29);
				tessellator.addVertexWithUV(x + d19, y + d21, z + d0, d27, d28);
				tessellator.addVertexWithUV(x + d15, y + d17, z + d0, d25, d28);
				tessellator.addVertexWithUV(x + d15, y + d17, z + d23, d25, d29);

				tessellator.addVertexWithUV(x + d11, y + d13, z + d23, d27, d29);
				tessellator.addVertexWithUV(x + d11, y + d13, z + d0, d27, d28);
				tessellator.addVertexWithUV(x + d19, y + d21, z + d0, d25, d28);
				tessellator.addVertexWithUV(x + d19, y + d21, z + d23, d25, d29);

				tessellator.addVertexWithUV(x + d15, y + d17, z + d23, d27, d29);
				tessellator.addVertexWithUV(x + d15, y + d17, z + d0, d27, d28);
				tessellator.addVertexWithUV(x + d7, y + d9, z + d0, d25, d28);
				tessellator.addVertexWithUV(x + d7, y + d9, z + d23, d25, d29);

				tessellator.draw();

				GL11.glEnable(GL11.GL_BLEND);
				OpenGlHelper.glBlendFunc(770, 771, 1, 0);
				GL11.glDepthMask(false);

				tessellator.startDrawingQuads();
				tessellator.setColorRGBA(rgb[0], rgb[1], rgb[2], 32);

				tessellator.addVertexWithUV(x + d30, y + d4, z + d18, d22, d26);
				tessellator.addVertexWithUV(x + d30, y + d4, z + d0, d22, d24);
				tessellator.addVertexWithUV(x + d6, y + d8, z + d0, d20, d24);
				tessellator.addVertexWithUV(x + d6, y + d8, z + d18, d20, d26);

				tessellator.addVertexWithUV(x + d14, y + d16, z + d18, d22, d26);
				tessellator.addVertexWithUV(x + d14, y + d16, z + d0, d22, d24);
				tessellator.addVertexWithUV(x + d10, y + d12, z + d0, d20, d24);
				tessellator.addVertexWithUV(x + d10, y + d12, z + d18, d20, d26);

				tessellator.addVertexWithUV(x + d6, y + d8, z + d18, d22, d26);
				tessellator.addVertexWithUV(x + d6, y + d8, z + d0, d22, d24);
				tessellator.addVertexWithUV(x + d14, y + d16, z + d0, d20, d24);
				tessellator.addVertexWithUV(x + d14, y + d16, z + d18, d20, d26);

				tessellator.addVertexWithUV(x + d10, y + d12, z + d18, d22, d26);
				tessellator.addVertexWithUV(x + d10, y + d12, z + d0, d22, d24);
				tessellator.addVertexWithUV(x + d30, y + d4, z + d0, d20, d24);
				tessellator.addVertexWithUV(x + d30, y + d4, z + d18, d20, d26);
			}
			if(direction == 4) // North /** -Z */
			{
				d23 = (double)(distance * f1);
				d29 = (double)(distance * f1) * (0.5D / d5) + d28;
				d18 = (double)(distance * f1);
				d26 = (double)(distance * f1) + d24;
				z = z + 0.5D;

				tessellator.addVertexWithUV(x + d7, y + d9, z - d23, d27, d29);
				tessellator.addVertexWithUV(x + d7, y + d9, z - d0, d27, d28);
				tessellator.addVertexWithUV(x + d11, y + d13, z - d0, d25, d28);
				tessellator.addVertexWithUV(x + d11, y + d13, z - d23, d25, d29);

				tessellator.addVertexWithUV(x + d19, y + d21, z - d23, d27, d29);
				tessellator.addVertexWithUV(x + d19, y + d21, z - d0, d27, d28);
				tessellator.addVertexWithUV(x + d15, y + d17, z - d0, d25, d28);
				tessellator.addVertexWithUV(x + d15, y + d17, z - d23, d25, d29);

				tessellator.addVertexWithUV(x + d11, y + d13, z - d23, d27, d29);
				tessellator.addVertexWithUV(x + d11, y + d13, z - d0, d27, d28);
				tessellator.addVertexWithUV(x + d19, y + d21, z - d0, d25, d28);
				tessellator.addVertexWithUV(x + d19, y + d21, z - d23, d25, d29);

				tessellator.addVertexWithUV(x + d15, y + d17, z - d23, d27, d29);
				tessellator.addVertexWithUV(x + d15, y + d17, z - d0, d27, d28);
				tessellator.addVertexWithUV(x + d7, y + d9, z - d0, d25, d28);
				tessellator.addVertexWithUV(x + d7, y + d9, z - d23, d25, d29);

				tessellator.draw();

				GL11.glEnable(GL11.GL_BLEND);
				OpenGlHelper.glBlendFunc(770, 771, 1, 0);
				GL11.glDepthMask(false);

				tessellator.startDrawingQuads();
				tessellator.setColorRGBA(rgb[0], rgb[1], rgb[2], 32);

				tessellator.addVertexWithUV(x + d30, y + d4, z - d18, d22, d26);
				tessellator.addVertexWithUV(x + d30, y + d4, z - d0, d22, d24);
				tessellator.addVertexWithUV(x + d6, y + d8, z - d0, d20, d24);
				tessellator.addVertexWithUV(x + d6, y + d8, z - d18, d20, d26);

				tessellator.addVertexWithUV(x + d14, y + d16, z - d18, d22, d26);
				tessellator.addVertexWithUV(x + d14, y + d16, z - d0, d22, d24);
				tessellator.addVertexWithUV(x + d10, y + d12, z - d0, d20, d24);
				tessellator.addVertexWithUV(x + d10, y + d12, z - d18, d20, d26);

				tessellator.addVertexWithUV(x + d6, y + d8, z - d18, d22, d26);
				tessellator.addVertexWithUV(x + d6, y + d8, z - d0, d22, d24);
				tessellator.addVertexWithUV(x + d14, y + d16, z - d0, d20, d24);
				tessellator.addVertexWithUV(x + d14, y + d16, z - d18, d20, d26);

				tessellator.addVertexWithUV(x + d10, y + d12, z - d18, d22, d26);
				tessellator.addVertexWithUV(x + d10, y + d12, z - d0, d22, d24);
				tessellator.addVertexWithUV(x + d30, y + d4, z - d0, d20, d24);
				tessellator.addVertexWithUV(x + d30, y + d4, z - d18, d20, d26);
			}
			if(direction == 3) // West /** -X */
			{
				d23 = (double)(distance * f1);
				d29 = (double)(distance * f1) * (0.5D / d5) + d28;
				d18 = (double)(distance * f1);
				d26 = (double)(distance * f1) + d24;
				x = x + 0.5D;

				tessellator.addVertexWithUV(x - d23, y + d7, z + d9, d27, d29);
				tessellator.addVertexWithUV(x - d0, y + d7, z + d9, d27, d28);
				tessellator.addVertexWithUV(x - d0, y + d11, z + d13, d25, d28);
				tessellator.addVertexWithUV(x - d23, y + d11, z + d13, d25, d29);

				tessellator.addVertexWithUV(x - d23, y + d19, z + d21, d27, d29);
				tessellator.addVertexWithUV(x - d0, y + d19, z + d21, d27, d28);
				tessellator.addVertexWithUV(x - d0, y + d15, z + d17, d25, d28);
				tessellator.addVertexWithUV(x - d23, y + d15, z + d17, d25, d29);

				tessellator.addVertexWithUV(x - d23, y + d11, z + d13, d27, d29);
				tessellator.addVertexWithUV(x - d0, y + d11, z + d13, d27, d28);
				tessellator.addVertexWithUV(x - d0, y + d19, z + d21, d25, d28);
				tessellator.addVertexWithUV(x - d23, y + d19, z + d21, d25, d29);

				tessellator.addVertexWithUV(x - d23, y + d15, z + d17, d27, d29);
				tessellator.addVertexWithUV(x - d0, y + d15, z + d17, d27, d28);
				tessellator.addVertexWithUV(x - d0, y + d7, z + d9, d25, d28);
				tessellator.addVertexWithUV(x - d23, y + d7, z + d9, d25, d29);

				tessellator.draw();

				GL11.glEnable(GL11.GL_BLEND);
				OpenGlHelper.glBlendFunc(770, 771, 1, 0);
				GL11.glDepthMask(false);

				tessellator.startDrawingQuads();
				tessellator.setColorRGBA(rgb[0], rgb[1], rgb[2], 32);

				tessellator.addVertexWithUV(x - d18, y + d30, z + d4, d22, d26);
				tessellator.addVertexWithUV(x - d0, y + d30, z + d4, d22, d24);
				tessellator.addVertexWithUV(x - d0, y + d6, z + d8, d20, d24);
				tessellator.addVertexWithUV(x - d18, y + d6, z + d8, d20, d26);

				tessellator.addVertexWithUV(x - d18, y + d14, z + d16, d22, d26);
				tessellator.addVertexWithUV(x - d0, y + d14, z + d16, d22, d24);
				tessellator.addVertexWithUV(x - d0, y + d10, z + d12, d20, d24);
				tessellator.addVertexWithUV(x - d18, y + d10, z + d12, d20, d26);

				tessellator.addVertexWithUV(x - d18, y + d6, z + d8, d22, d26);
				tessellator.addVertexWithUV(x - d0, y + d6, z + d8, d22, d24);
				tessellator.addVertexWithUV(x - d0, y + d14, z + d16, d20, d24);
				tessellator.addVertexWithUV(x - d18, y + d14, z + d16, d20, d26);

				tessellator.addVertexWithUV(x - d18, y + d10, z + d12, d22, d26);
				tessellator.addVertexWithUV(x - d0, y + d10, z + d12, d22, d24);
				tessellator.addVertexWithUV(x - d0, y + d30, z + d4, d20, d24);
				tessellator.addVertexWithUV(x - d18, y + d30, z + d4, d20, d26);
			}
			if(direction == 5) // East /** +X */
			{
				d23 = (double)(distance * f1);
				d29 = (double)(distance * f1) * (0.5D / d5) + d28;
				d18 = (double)(distance * f1);
				d26 = (double)(distance * f1) + d24;
				x = x + 0.5D;

				tessellator.addVertexWithUV(x + d23, y + d7, z + d9, d27, d29);
				tessellator.addVertexWithUV(x + d0, y + d7, z + d9, d27, d28);
				tessellator.addVertexWithUV(x + d0, y + d11, z + d13, d25, d28);
				tessellator.addVertexWithUV(x + d23, y + d11, z + d13, d25, d29);

				tessellator.addVertexWithUV(x + d23, y + d19, z + d21, d27, d29);
				tessellator.addVertexWithUV(x + d0, y + d19, z + d21, d27, d28);
				tessellator.addVertexWithUV(x + d0, y + d15, z + d17, d25, d28);
				tessellator.addVertexWithUV(x + d23, y + d15, z + d17, d25, d29);

				tessellator.addVertexWithUV(x + d23, y + d11, z + d13, d27, d29);
				tessellator.addVertexWithUV(x + d0, y + d11, z + d13, d27, d28);
				tessellator.addVertexWithUV(x + d0, y + d19, z + d21, d25, d28);
				tessellator.addVertexWithUV(x + d23, y + d19, z + d21, d25, d29);

				tessellator.addVertexWithUV(x + d23, y + d15, z + d17, d27, d29);
				tessellator.addVertexWithUV(x + d0, y + d15, z + d17, d27, d28);
				tessellator.addVertexWithUV(x + d0, y + d7, z + d9, d25, d28);
				tessellator.addVertexWithUV(x + d23, y + d7, z + d9, d25, d29);

				tessellator.draw();

				GL11.glEnable(GL11.GL_BLEND);
				OpenGlHelper.glBlendFunc(770, 771, 1, 0);
				GL11.glDepthMask(false);

				tessellator.startDrawingQuads();
				tessellator.setColorRGBA(rgb[0], rgb[1], rgb[2], 32);

				tessellator.addVertexWithUV(x + d18, y + d30, z + d4, d22, d26);
				tessellator.addVertexWithUV(x + d0, y + d30, z + d4, d22, d24);
				tessellator.addVertexWithUV(x + d0, y + d6, z + d8, d20, d24);
				tessellator.addVertexWithUV(x + d18, y + d6, z + d8, d20, d26);

				tessellator.addVertexWithUV(x + d18, y + d14, z + d16, d22, d26);
				tessellator.addVertexWithUV(x + d0, y + d14, z + d16, d22, d24);
				tessellator.addVertexWithUV(x + d0, y + d10, z + d12, d20, d24);
				tessellator.addVertexWithUV(x + d18, y + d10, z + d12, d20, d26);

				tessellator.addVertexWithUV(x + d18, y + d6, z + d8, d22, d26);
				tessellator.addVertexWithUV(x + d0, y + d6, z + d8, d22, d24);
				tessellator.addVertexWithUV(x + d0, y + d14, z + d16, d20, d24);
				tessellator.addVertexWithUV(x + d18, y + d14, z + d16, d20, d26);

				tessellator.addVertexWithUV(x + d18, y + d10, z + d12, d22, d26);
				tessellator.addVertexWithUV(x + d0, y + d10, z + d12, d22, d24);
				tessellator.addVertexWithUV(x + d0, y + d30, z + d4, d20, d24);
				tessellator.addVertexWithUV(x + d18, y + d30, z + d4, d20, d26);
			}
			tessellator.draw();

			GL11.glEnable(GL11.GL_LIGHTING);
			GL11.glEnable(GL11.GL_TEXTURE_2D);
			GL11.glDepthMask(true);
		}
		GL11.glAlphaFunc(GL11.GL_GREATER, 0.5F);
	}
}
