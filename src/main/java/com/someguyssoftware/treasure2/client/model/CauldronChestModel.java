package com.someguyssoftware.treasure2.client.model;

import com.someguyssoftware.treasure2.lock.LockState;
import com.someguyssoftware.treasure2.tileentity.ITreasureChestTileEntity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class CauldronChestModel extends ModelBase implements ITreasureChestModel {
	// fields
	ModelRenderer front;
	ModelRenderer back;
	ModelRenderer left;
	ModelRenderer right;
	ModelRenderer bottom;
	ModelRenderer flFoot;
	ModelRenderer frFoot;
	ModelRenderer blFoot;
	ModelRenderer brFoot;
	ModelRenderer fl2;
	ModelRenderer fr2;
	ModelRenderer bl2;
	ModelRenderer br2;
	ModelRenderer lidLeft;
	ModelRenderer lidRight;
	ModelRenderer pad1;
	ModelRenderer pad2;
	ModelRenderer water;

	public CauldronChestModel() {
		textureWidth = 128;
		textureHeight = 128;

		front = new ModelRenderer(this);
		front.setRotationPoint(-6.0F, 8.0F, -8.0F);
		front.cubeList.add(new ModelBox(front, 0, 0, 0.0F, 0.0F, 0.0F, 12, 14, 2, 0.0F, false));

		back = new ModelRenderer(this);
		back.setRotationPoint(-6.0F, 8.0F, 6.0F);
		back.cubeList.add(new ModelBox(back, 32, 0, 0.0F, 0.0F, 0.0F, 12, 14, 2, 0.0F, false));

		left = new ModelRenderer(this);
		left.setRotationPoint(6.0F, 8.0F, -8.0F);
		left.cubeList.add(new ModelBox(left, 0, 18, 0.0F, 0.0F, 0.0F, 2, 14, 16, 0.0F, false));

		right = new ModelRenderer(this);
		right.setRotationPoint(-8.0F, 8.0F, -8.0F);
		right.cubeList.add(new ModelBox(right, 38, 18, 0.0F, 0.0F, 0.0F, 2, 14, 16, 0.0F, false));

		bottom = new ModelRenderer(this);
		bottom.setRotationPoint(-6.0F, 20.0F, -6.0F);
		bottom.cubeList.add(new ModelBox(bottom, 0, 50, 0.0F, 1.0F, 0.0F, 12, 1, 12, 0.0F, false));

		flFoot = new ModelRenderer(this);
		flFoot.setRotationPoint(4.0F, 21.0F, -8.0F);
		flFoot.cubeList.add(new ModelBox(flFoot, 0, 80, -1.0F, 1.0F, 0.0F, 5, 2, 2, 0.0F, false));

		frFoot = new ModelRenderer(this);
		frFoot.setRotationPoint(-8.0F, 21.0F, -8.0F);
		frFoot.cubeList.add(new ModelBox(frFoot, 14, 80, 0.0F, 1.0F, 0.0F, 5, 2, 2, 0.0F, false));

		blFoot = new ModelRenderer(this);
		blFoot.setRotationPoint(4.0F, 21.0F, 6.0F);
		blFoot.cubeList.add(new ModelBox(blFoot, 14, 80, -1.0F, 1.0F, 0.0F, 5, 2, 2, 0.0F, false));

		brFoot = new ModelRenderer(this);
		brFoot.setRotationPoint(-8.0F, 21.0F, 6.0F);
		brFoot.cubeList.add(new ModelBox(brFoot, 0, 80, 0.0F, 1.0F, 0.0F, 5, 2, 2, 0.0F, false));

		fl2 = new ModelRenderer(this);
		fl2.setRotationPoint(6.0F, 21.0F, -6.0F);
		fl2.cubeList.add(new ModelBox(fl2, 0, 87, 0.0F, 1.0F, 0.0F, 2, 2, 3, 0.0F, false));

		fr2 = new ModelRenderer(this);
		fr2.setRotationPoint(-8.0F, 21.0F, -6.0F);
		fr2.cubeList.add(new ModelBox(fr2, 10, 87, 0.0F, 1.0F, 0.0F, 2, 2, 3, 0.0F, false));

		bl2 = new ModelRenderer(this);
		bl2.setRotationPoint(6.0F, 21.0F, 4.0F);
		bl2.cubeList.add(new ModelBox(bl2, 9, 87, 0.0F, 1.0F, -1.0F, 2, 2, 3, 0.0F, false));

		br2 = new ModelRenderer(this);
		br2.setRotationPoint(-8.0F, 21.0F, 4.0F);
		br2.cubeList.add(new ModelBox(br2, 0, 87, 0.0F, 1.0F, -1.0F, 2, 2, 3, 0.0F, false));

		lidLeft = new ModelRenderer(this);
		lidLeft.setRotationPoint(6.0F, 10.0F, -6.0F);
		lidLeft.cubeList.add(new ModelBox(lidLeft, 0, 65, -6.0F, 0.0F, 0.0F, 6, 1, 12, 0.0F, false));

		lidRight = new ModelRenderer(this);
		lidRight.setRotationPoint(-6.0F, 10.0F, -6.0F);
		lidRight.cubeList.add(new ModelBox(lidRight, 38, 65, 0.0F, 0.0F, 0.0F, 6, 1, 12, 0.0F, false));

		pad1 = new ModelRenderer(this);
		pad1.setRotationPoint(0.0F, 24.0F, 0.0F);
		pad1.cubeList.add(new ModelBox(pad1, 44, 0, 2.0F, -16.0F, 2.0F, 2, 2, 1, 0.0F, false));
		pad1.cubeList.add(new ModelBox(pad1, 31, 0, -6.0F, -15.1F, 1.0F, 12, 2, 3, 0.0F, false));


		pad2 = new ModelRenderer(this);
		pad2.setRotationPoint(-6.0F, 24.0F, 0.0F);
		pad2.cubeList.add(new ModelBox(pad2, 34, 0, 2.0F, -16.0F, 2.0F, 2, 2, 1, 0.0F, false));
		pad2.cubeList.add(new ModelBox(pad2, 31, 0, 0.0F, -15.1F, 1.0F, 12, 2, 3, 0.0F, false));

		water = new ModelRenderer(this);
		water.setRotationPoint(7.0F, 9.0F, -8.0F);
		water.cubeList.add(new ModelBox(water, 36, 51, -13.0F, 0.0F, 2.0F, 12, 1, 12, 0.0F, false));

	}

	/**
	 * 
	 */
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		front.render(f5);
		back.render(f5);
		left.render(f5);
		right.render(f5);
		bottom.render(f5);
		flFoot.render(f5);
		frFoot.render(f5);
		blFoot.render(f5);
		brFoot.render(f5);
		fl2.render(f5);
		fr2.render(f5);
		bl2.render(f5);
		br2.render(f5);
		lidLeft.render(f5);
		lidRight.render(f5);
		pad1.render(f5);
		pad2.render(f5);
		water.render(f5);
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
		lidRight.rotateAngleZ = -lidLeft.rotateAngleZ;
		water.rotateAngleZ = -lidLeft.rotateAngleZ;

		float angle = 0.0625F;
		front.render(angle);
		back.render(angle);
		left.render(angle);
		right.render(angle);
		bottom.render(angle);
		lidLeft.render(angle);
		lidRight.render(angle);
		flFoot.render(angle);
		fl2.render(angle);
		frFoot.render(angle);
		fr2.render(angle);
		blFoot.render(angle);
		bl2.render(angle);
		brFoot.render(angle);
		br2.render(angle);

		for (LockState state : te.getLockStates()) {
			if (state.getLock() != null) {
				switch(state.getSlot().getIndex()) {
					case 0:
						pad1.render(angle);
						break;
					case 1:
						pad2.render(angle);
						break;
				}
			}
		}
		water.render(angle);
	}

	@Override
	public ModelRenderer getLid() {
		return lidLeft;
	}

}