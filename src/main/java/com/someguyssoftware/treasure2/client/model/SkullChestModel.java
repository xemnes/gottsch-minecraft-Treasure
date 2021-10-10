package com.someguyssoftware.treasure2.client.model;

import com.someguyssoftware.treasure2.tileentity.ITreasureChestTileEntity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * 
 * @author Mark Gottschling on Nov 28, 2018
 *
 */
public class SkullChestModel extends ModelBase implements ITreasureChestModel {
	// fields
	ModelRenderer top;
	ModelRenderer head;
	ModelRenderer jaw;
	ModelRenderer jawBottom;

	/**
	 * 
	 */
	public SkullChestModel() {
		textureWidth = 64;
		textureHeight = 64;

		top = new ModelRenderer(this);
		top.setRotationPoint(0.0F, 22.0F, 1.0F);
		top.cubeList.add(new ModelBox(top, 0, 0, -3.0F, -7.0F, -4.0F, 6, 1, 6, 0.0F, true));

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, 22.0F, 1.0F);
		head.cubeList.add(new ModelBox(head, 0, 8, -4.0F, -6.0F, -5.0F, 8, 5, 8, 0.0F, true));

		jaw = new ModelRenderer(this);
		jaw.setRotationPoint(0.0F, 22.0F, 1.0F);
		jaw.cubeList.add(new ModelBox(jaw, 0, 22, -3.0F, -1.0F, -5.0F, 6, 1, 6, 0.0F, true));

		jawBottom = new ModelRenderer(this);
		jawBottom.setRotationPoint(0.0F, 22.0F, 1.0F);
		jawBottom.cubeList.add(new ModelBox(jawBottom, 0, 30, -3.0F, 0.0F, -5.0F, 6, 2, 5, 0.0F, true));

	}

	/**
	 * 
	 */
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		top.render(f5);
		head.render(f5);
		jaw.render(f5);
		jawBottom.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	}

	/**
	 * 
	 */
	@Override
	public void renderAll(ITreasureChestTileEntity te) {
		head.rotateAngleX = top.rotateAngleX;
		jaw.rotateAngleX = top.rotateAngleX;
		
		// render all the parts
		top.render(0.0625F);
		head.render(0.0625F);
		jaw.render(0.0625F);
		jawBottom.render(0.0625F);
	}

	@Override
	public ModelRenderer getLid() {
		return top;
	}

	public void setLid(ModelRenderer lid) {
		this.top = lid;
	}
}
