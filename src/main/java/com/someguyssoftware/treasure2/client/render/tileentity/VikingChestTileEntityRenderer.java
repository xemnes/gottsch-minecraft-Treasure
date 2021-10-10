/**
 * 
 */
package com.someguyssoftware.treasure2.client.render.tileentity;

import com.someguyssoftware.treasure2.client.model.ITreasureChestModel;
import net.minecraft.client.renderer.GlStateManager;

/**
 * @author Mark Gottschling on Jan 9, 2018
 *
 */
public class VikingChestTileEntityRenderer extends TreasureChestTileEntityRenderer {

	 /**
	  *
	  * @param texture
	  */
	 public VikingChestTileEntityRenderer(String texture, ITreasureChestModel model) {
		 super(texture, model);
	 }
	 
	 @Override
	 public void updateLockScale() {
		 GlStateManager.scale(0.52F, 0.52F, 0.52F);
	}
}
