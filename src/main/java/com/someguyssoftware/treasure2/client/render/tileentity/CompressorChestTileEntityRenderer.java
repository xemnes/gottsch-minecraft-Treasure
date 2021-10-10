/**
 * 
 */
package com.someguyssoftware.treasure2.client.render.tileentity;

import com.someguyssoftware.treasure2.client.model.ITreasureChestModel;

import com.someguyssoftware.treasure2.tileentity.AbstractTreasureChestTileEntity;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;

/**
 * 
 * @author Mark Gottschling on Mar 23, 2018
 *
 */
public class CompressorChestTileEntityRenderer extends TreasureChestTileEntityRenderer {
 
	 /**
	  * 
	  * @param texture
	  */
	 public CompressorChestTileEntityRenderer(String texture, ITreasureChestModel model) {
		 super(texture, model);
	 }

	 @Override
	public void render(AbstractTreasureChestTileEntity te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {

		if (!(te instanceof AbstractTreasureChestTileEntity))
			return; // should never happen

		// apply the destory gl state (if any)
		applyDestroyGlState(destroyStage);

		 GlStateManager.enableBlend();
		 GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		 GlStateManager.disableAlpha();
		// get the model
		ITreasureChestModel model = getModel();
		// bind the texture
		this.bindTexture(getTexture());
		// get the chest rotation
		int meta = 0;
		if (te.hasWorld()) {
			meta = te.getBlockMetadata();
		}
		int rotation = getRotation(meta);

		// start render matrix
		GlStateManager.pushMatrix();

		// initial position (centered moved up)
		updateTranslation(x, y, z);

		// This rotation part is very important! Without it, your model will render upside-down.
		// (rotate 180 degrees around the z-axis)
		GlStateManager.rotate(180F, 0F, 0F, 1.0F);
		// rotate block to the correct direction that it is facing.
		GlStateManager.rotate((float) rotation, 0.0F, 1.0F, 0.0F);

		// add scale method
		updateScale();

		// update the lid rotation
		updateModelLidRotation(te, partialTicks);

		// render the model
		model.renderAll(te);
		GlStateManager.enableAlpha();
		GlStateManager.disableBlend();
		GlStateManager.popMatrix();

		// end of rendering chest entity ////

		// pop the destroy stage matrix
		popDestroyGlState(destroyStage);

		////////////// render the locks //////////////////////////////////////
		if (te.getLockStates() != null && !te.getLockStates().isEmpty()) {
			renderLocks(te, x, y, z);
		}

		////////////// end of render the locks //////////////////////////////////////
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
	}
    
	 @Override
	 public void updateLockScale() {
		 GlStateManager.scale(0.40F, 0.40F, 0.40F);
	} 
}
