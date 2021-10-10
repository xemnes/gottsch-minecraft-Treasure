package com.someguyssoftware.treasure2.client.model;

import com.someguyssoftware.treasure2.tileentity.ITreasureChestTileEntity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * 
 * @author Mark Gottschling on Jan 19, 2018
 *
 */
public class BandedChestModel extends ModelBase implements ITreasureChestModel {
	// fields
	ModelRenderer base;
	ModelRenderer RightBottomBand;
	ModelRenderer lid;
	ModelRenderer RightTopBand;
	ModelRenderer LeftTopBand;
	ModelRenderer FrontLeftTopBand;
	ModelRenderer BackRightTopBand;
	ModelRenderer BackLeftTopBand;
	ModelRenderer LeftBottomBand;
	ModelRenderer FrontRightTopBand;
	ModelRenderer Ledge;
	ModelRenderer Ledge2;
	ModelRenderer Ledge3;
    ModelRenderer hinge1;
    ModelRenderer hinge2;
    ModelRenderer pad;

	/**
	 * 
	 */
	public BandedChestModel() {
		textureWidth = 128;
		textureHeight = 128;

		base = new ModelRenderer(this);
		base.setRotationPoint(0.0F, 14.5F, 7.0F);
		base.cubeList.add(new ModelBox(base, 0, 20, -7.0F, 0.0F, -14.0F, 14, 9, 14, 0.0F, true));

		RightBottomBand = new ModelRenderer(this);
		RightBottomBand.setRotationPoint(-4.0F, 15.0F, 7.0F);
		RightBottomBand.cubeList.add(new ModelBox(RightBottomBand, 0, 44, -2.0F, 0.0F, -14.5F, 3, 9, 15, 0.0F, true));

		lid = new ModelRenderer(this);
		lid.setRotationPoint(0.0F, 15.5F, 7.0F);
		lid.cubeList.add(new ModelBox(lid, 0, 0, -7.0F, -5.0F, -14.0F, 14, 5, 14, 0.0F, true));

		RightTopBand = new ModelRenderer(this);
		RightTopBand.setRotationPoint(-5.0F, 15.5F, 7.0F);
		RightTopBand.cubeList.add(new ModelBox(RightTopBand, 0, 69, -1.0F, -5.5F, -14.5F, 3, 1, 15, 0.0F, true));

		LeftTopBand = new ModelRenderer(this);
		LeftTopBand.setRotationPoint(3.0F, 15.5F, 7.0F);
		LeftTopBand.cubeList.add(new ModelBox(LeftTopBand, 36, 69, 0.0F, -5.5F, -14.5F, 3, 1, 15, 0.0F, true));

		FrontLeftTopBand = new ModelRenderer(this);
		FrontLeftTopBand.setRotationPoint(4.0F, 15.5F, 7.0F);
		FrontLeftTopBand.cubeList.add(new ModelBox(FrontLeftTopBand, 66, 7, -1.0F, -4.5F, -14.5F, 3, 5, 1, 0.0F, true));

		BackRightTopBand = new ModelRenderer(this);
		BackRightTopBand.setRotationPoint(-4.0F, 15.5F, 7.0F);
		BackRightTopBand.cubeList.add(new ModelBox(BackRightTopBand, 66, 0, -2.0F, -5.5F, -0.5F, 3, 5, 1, 0.0F, true));

		BackLeftTopBand = new ModelRenderer(this);
		BackLeftTopBand.setRotationPoint(4.0F, 15.5F, 7.0F);
		BackLeftTopBand.cubeList.add(new ModelBox(BackLeftTopBand, 57, 0, -1.0F, -5.5F, -0.5F, 3, 5, 1, 0.0F, true));

		LeftBottomBand = new ModelRenderer(this);
		LeftBottomBand.setRotationPoint(4.0F, 15.0F, 7.5F);
		LeftBottomBand.cubeList.add(new ModelBox(LeftBottomBand, 36, 44, -1.0F, 0.0F, -15.0F, 3, 9, 15, 0.0F, true));

		FrontRightTopBand = new ModelRenderer(this);
		FrontRightTopBand.setRotationPoint(-4.0F, 15.5F, 7.0F);
		FrontRightTopBand.cubeList.add(new ModelBox(FrontRightTopBand, 57, 7, -2.0F, -4.5F, -14.5F, 3, 5, 1, 0.0F, true));

		Ledge = new ModelRenderer(this);
		Ledge.setRotationPoint(0.0F, 15.5F, 7.0F);
		Ledge.cubeList.add(new ModelBox(Ledge, 58, 16, -2.0F, -2.0F, -15.0F, 4, 1, 1, 0.0F, true));

		Ledge2 = new ModelRenderer(this);
		Ledge2.setRotationPoint(7.0F, 15.0F, 7.0F);
		Ledge2.cubeList.add(new ModelBox(Ledge2, 0, 0, 0.0F, -1.5F, -9.0F, 1, 1, 4, 0.0F, true));

		Ledge3 = new ModelRenderer(this);
		Ledge3.setRotationPoint(-7.0F, 15.0F, 7.0F);
		Ledge3.cubeList.add(new ModelBox(Ledge3, 0, 0, -1.0F, -1.5F, -9.0F, 1, 1, 4, 0.0F, true));

		hinge1 = new ModelRenderer(this);
		hinge1.setRotationPoint(2.0F, 14.5F, 7.0F);
		hinge1.cubeList.add(new ModelBox(hinge1, 11, 86, -1.0F, 0.0F, -0.5F, 1, 2, 1, 0.0F, true));

		hinge2 = new ModelRenderer(this);
		hinge2.setRotationPoint(-2.0F, 14.5F, 7.0F);
		hinge2.cubeList.add(new ModelBox(hinge2, 11, 86, 0.0F, 0.0F, -0.5F, 1, 2, 1, 0.0F, true));

		pad = new ModelRenderer(this);
		pad.setRotationPoint(0.0F, 15.5F, 7.0F);
		pad.cubeList.add(new ModelBox(pad, 0, 86, -2.0F, -1.0F, -14.2F, 4, 4, 1, 0.0F, true));

	}

	/**
	 * 
	 */
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		base.render(f5);
		RightBottomBand.render(f5);
		lid.render(f5);
		RightTopBand.render(f5);
		LeftTopBand.render(f5);
		FrontLeftTopBand.render(f5);
		BackRightTopBand.render(f5);
		BackLeftTopBand.render(f5);
		LeftBottomBand.render(f5);
		FrontRightTopBand.render(f5);
		Ledge.render(f5);
		Ledge2.render(f5);
		Ledge3.render(f5);
	    hinge1.render(f5);
	    hinge2.render(f5);
	    pad.render(f5);
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
		// set the angles of the latch to same as the lib
		RightTopBand.rotateAngleX = lid.rotateAngleX;
		LeftTopBand.rotateAngleX = lid.rotateAngleX;
		FrontLeftTopBand.rotateAngleX = lid.rotateAngleX;
		FrontRightTopBand.rotateAngleX = lid.rotateAngleX;
		BackLeftTopBand.rotateAngleX = lid.rotateAngleX;
		BackRightTopBand.rotateAngleX = lid.rotateAngleX;
		Ledge.rotateAngleX = lid.rotateAngleX;
		Ledge2.rotateAngleX = lid.rotateAngleX;
		Ledge3.rotateAngleX = lid.rotateAngleX;
		
		base.render(0.0625F);
		lid.render(0.0625F);
		RightBottomBand.render(0.0625F);
		RightTopBand.render(0.0625F);
		LeftTopBand.render(0.0625F);
		FrontLeftTopBand.render(0.0625F);
		BackRightTopBand.render(0.0625F);
		BackLeftTopBand.render(0.0625F);
		LeftBottomBand.render(0.0625F);
		FrontRightTopBand.render(0.0625F);
		Ledge.render(0.0625F);
		Ledge2.render(0.0625F);
		Ledge3.render(0.0625F);
	    hinge1.render(0.0625F);
	    hinge2.render(0.0625F);
	    pad.render(0.0625F);
	}

	/**
	 * @return the base
	 */
	public ModelRenderer getBase() {
		return base;
	}

	/**
	 * @param base the base to set
	 */
	public void setBase(ModelRenderer base) {
		this.base = base;
	}

	/**
	 * @return the lid
	 */
	@Override
	public ModelRenderer getLid() {
		return lid;
	}

	/**
	 * @param lid the lid to set
	 */
	public void setLid(ModelRenderer lid) {
		this.lid = lid;
	}
}
