package com.someguyssoftware.treasure2.client.model;

import com.someguyssoftware.treasure2.tileentity.ITreasureChestTileEntity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * 
 * @author Mark Gottschling on Mar 13, 2018
 *
 */
public class DreadPirateChestModel extends ModelBase implements ITreasureChestModel {
	// fields
	private final ModelRenderer lid;
	private final ModelRenderer baseMiddle;
	private final ModelRenderer baseSide1;
	private final ModelRenderer baseSide2;
	private final ModelRenderer bottom;
	private final ModelRenderer skullTop;
	private final ModelRenderer skullBottom;
	private final ModelRenderer topHinge1;
	private final ModelRenderer topHinge2;
	private final ModelRenderer bottomHinge1;
	private final ModelRenderer bottomHinge2;
	private final ModelRenderer handle1;
	private final ModelRenderer handle2;

	/**
	 * 
	 */
	public DreadPirateChestModel() {
		textureWidth = 128;
		textureHeight = 128;

		lid = new ModelRenderer(this);
		lid.setRotationPoint(0.0F, 15.0F, 6.0F);
		lid.cubeList.add(new ModelBox(lid, 0, 0, -7.0F, -5.0F, -13.0F, 14, 5, 14, 0.0F, true));

		baseMiddle = new ModelRenderer(this);
		baseMiddle.setRotationPoint(0.0F, 14.0F, 5.0F);
		baseMiddle.cubeList.add(new ModelBox(baseMiddle, 0, 21, -3.0F, 0.0F, -11.0F, 6, 9, 12, 0.0F, true));

		baseSide1 = new ModelRenderer(this);
		baseSide1.setRotationPoint(5.0F, 14.0F, 5.0F);
		baseSide1.cubeList.add(new ModelBox(baseSide1, 0, 44, -2.0F, 0.0F, -12.0F, 4, 9, 14, 0.0F, false));

		baseSide2 = new ModelRenderer(this);
		baseSide2.setRotationPoint(-5.0F, 14.0F, 5.0F);
		baseSide2.cubeList.add(new ModelBox(baseSide2, 0, 70, -2.0F, 0.0F, -12.0F, 4, 9, 14, 0.0F, false));

		bottom = new ModelRenderer(this);
		bottom.setRotationPoint(0.0F, 22.0F, 7.0F);
		bottom.cubeList.add(new ModelBox(bottom, 0, 96, -7.0F, 0.0F, -14.0F, 14, 2, 14, 0.0F, true));

		skullTop = new ModelRenderer(this);
		skullTop.setRotationPoint(0.0F, 15.0F, 6.0F);
		skullTop.cubeList.add(new ModelBox(skullTop, 57, 0, -3.0F, -3.0F, -14.0F, 6, 5, 2, 0.0F, true));

		skullBottom = new ModelRenderer(this);
		skullBottom.setRotationPoint(0.0F, 17.0F, -6.0F);
		skullBottom.cubeList.add(new ModelBox(skullBottom, 57, 8, -2.0F, 0.0F, -1.0F, 4, 3, 1, 0.0F, true));

		topHinge1 = new ModelRenderer(this);
		topHinge1.setRotationPoint(4.0F, 15.0F, 6.0F);
		topHinge1.cubeList.add(new ModelBox(topHinge1, 57, 14, -1.0F, -5.5F, -3.5F, 2, 4, 5, 0.0F, true));

		topHinge2 = new ModelRenderer(this);
		topHinge2.setRotationPoint(-4.0F, 15.0F, 6.0F);
		topHinge2.cubeList.add(new ModelBox(topHinge2, 57, 14, -1.0F, -5.5F, -3.5F, 2, 4, 5, 0.0F, true));

		bottomHinge1 = new ModelRenderer(this);
		bottomHinge1.setRotationPoint(4.0F, 14.0F, 7.0F);
		bottomHinge1.cubeList.add(new ModelBox(bottomHinge1, 56, 23, -1.0F, -0.5F, -1.5F, 2, 2, 2, 0.01F, true));

		bottomHinge2 = new ModelRenderer(this);
		bottomHinge2.setRotationPoint(-4.0F, 14.0F, 7.0F);
		bottomHinge2.cubeList.add(new ModelBox(bottomHinge2, 57, 23, -1.0F, -0.5F, -1.5F, 2, 2, 2, 0.01F, true));

		handle1 = new ModelRenderer(this);
		handle1.setRotationPoint(7.0F, 15.0F, -2.0F);
		handle1.cubeList.add(new ModelBox(handle1, 57, 34, 0.0F, 0.0F, 0.0F, 1, 2, 4, 0.0F, true));

		handle2 = new ModelRenderer(this);
		handle2.setRotationPoint(-8.0F, 15.0F, -2.0F);
		handle2.cubeList.add(new ModelBox(handle2, 57, 34, 0.0F, 0.0F, 0.0F, 1, 2, 4, 0.0F, true));

	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		lid.render(f5);
		baseMiddle.render(f5);
		baseSide1.render(f5);
		baseSide2.render(f5);
		bottom.render(f5);
		skullTop.render(f5);
		skullBottom.render(f5);
		topHinge1.render(f5);
		topHinge2.render(f5);
		bottomHinge1.render(f5);
		bottomHinge2.render(f5);
		handle1.render(f5);
		handle2.render(f5);
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
		float f5 = 0.0625F;

		// set the angles
		skullTop.rotateAngleX = lid.rotateAngleX;
		topHinge1.rotateAngleX = lid.rotateAngleX;
		topHinge2.rotateAngleX = lid.rotateAngleX;

		lid.render(f5);
		baseMiddle.render(f5);
		baseSide1.render(f5);
		baseSide2.render(f5);
		bottom.render(f5);
		skullTop.render(f5);
		skullBottom.render(f5);
		topHinge1.render(f5);
		topHinge2.render(f5);
		bottomHinge1.render(f5);
		bottomHinge2.render(f5);
		handle1.render(f5);
		handle2.render(f5);
	}

	@Override
	public ModelRenderer getLid() {
		return lid;
	}

}
