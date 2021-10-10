package com.someguyssoftware.treasure2.client.model;

import com.someguyssoftware.treasure2.tileentity.ITreasureChestTileEntity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class MilkCrateModel extends ModelBase implements ITreasureChestModel {
	private final ModelRenderer bottom;
	private final ModelRenderer north;
	private final ModelRenderer south;
	private final ModelRenderer west;
	private final ModelRenderer w2_r1;
	private final ModelRenderer east;
	private final ModelRenderer e2_r1;
	private final ModelRenderer posts;
	private final ModelRenderer lid;
	private final ModelRenderer padBottom;
	private final ModelRenderer padTop;
	private final ModelRenderer hinge;


	public MilkCrateModel() {
		textureWidth = 64;
		textureHeight = 64;

		bottom = new ModelRenderer(this);
		bottom.setRotationPoint(0.0F, 24.0F, 0.0F);
		bottom.cubeList.add(new ModelBox(bottom, 0, 16, -6.0F, -1.5F, -6.0F, 12, 1, 12, 0.0F, false));

		north = new ModelRenderer(this);
		north.setRotationPoint(0.0F, 24.0F, -1.0F);
		north.cubeList.add(new ModelBox(north, 0, 38, -5.0F, -3.0F, -5.5F, 10, 3, 1, 0.0F, false));
		north.cubeList.add(new ModelBox(north, 0, 34, -5.0F, -7.0F, -5.5F, 10, 3, 1, 0.0F, false));
		north.cubeList.add(new ModelBox(north, 0, 30, -5.0F, -11.0F, -5.5F, 10, 3, 1, 0.0F, false));

		south = new ModelRenderer(this);
		south.setRotationPoint(0.0F, 24.0F, 12.0F);
		south.cubeList.add(new ModelBox(south, 0, 38, -5.0F, -3.0F, -6.5F, 10, 3, 1, 0.0F, false));
		south.cubeList.add(new ModelBox(south, 0, 34, -5.0F, -7.0F, -6.5F, 10, 3, 1, 0.0F, false));
		south.cubeList.add(new ModelBox(south, 0, 30, -5.0F, -11.0F, -6.5F, 10, 3, 1, 0.0F, false));

		west = new ModelRenderer(this);
		west.setRotationPoint(0.0F, 16.0F, 6.0F);


		w2_r1 = new ModelRenderer(this);
		w2_r1.setRotationPoint(-0.5F, -4.0F, 0.0F);
		west.addChild(w2_r1);
		setRotation(w2_r1, 0.0F, 1.5708F, 0.0F);
		w2_r1.cubeList.add(new ModelBox(w2_r1, 0, 34, 1.0F, 5.0F, 6.0F, 10, 3, 1, 0.0F, false));
		w2_r1.cubeList.add(new ModelBox(w2_r1, 23, 30, 1.0F, 1.0F, 6.0F, 10, 3, 1, 0.0F, false));
		w2_r1.cubeList.add(new ModelBox(w2_r1, 0, 38, 1.0F, 9.0F, 6.0F, 10, 3, 1, 0.0F, false));

		east = new ModelRenderer(this);
		east.setRotationPoint(-13.0F, 16.0F, 6.0F);


		e2_r1 = new ModelRenderer(this);
		e2_r1.setRotationPoint(0.5F, -4.0F, 0.0F);
		east.addChild(e2_r1);
		setRotation(e2_r1, 0.0F, 1.5708F, 0.0F);
		e2_r1.cubeList.add(new ModelBox(e2_r1, 0, 34, 1.0F, 5.0F, 6.0F, 10, 3, 1, 0.0F, false));
		e2_r1.cubeList.add(new ModelBox(e2_r1, 23, 30, 1.0F, 1.0F, 6.0F, 10, 3, 1, 0.0F, false));
		e2_r1.cubeList.add(new ModelBox(e2_r1, 0, 38, 1.0F, 9.0F, 6.0F, 10, 3, 1, 0.0F, false));

		posts = new ModelRenderer(this);
		posts.setRotationPoint(-1.0F, 24.0F, -1.0F);
		posts.cubeList.add(new ModelBox(posts, 0, 0, -6.0F, -11.0F, -6.0F, 2, 11, 2, 0.0F, false));
		posts.cubeList.add(new ModelBox(posts, 0, 0, 6.0F, -11.0F, -6.0F, 2, 11, 2, 0.0F, false));
		posts.cubeList.add(new ModelBox(posts, 0, 0, 6.0F, -11.0F, 6.0F, 2, 11, 2, 0.0F, false));
		posts.cubeList.add(new ModelBox(posts, 0, 0, -6.0F, -11.0F, 6.0F, 2, 11, 2, 0.0F, false));

		lid = new ModelRenderer(this);
		lid.setRotationPoint(0.0F, 13.0F, 7.0F);
		lid.cubeList.add(new ModelBox(lid, 0, 0, -7.0F, -2.0F, -14.0F, 14, 2, 14, 0.0F, false));

		padBottom = new ModelRenderer(this);
		padBottom.setRotationPoint(0.0F, 16.5F, 0.0F);
		padBottom.cubeList.add(new ModelBox(padBottom, 0, 19, -2.0F, -3.5F, -7.5F, 4, 2, 1, 0.0F, false));

		padTop = new ModelRenderer(this);
		padTop.setRotationPoint(0.0F, 13.0F, 7.0F);
		padTop.cubeList.add(new ModelBox(padTop, 0, 16, -2.0F, -2.0F, -14.5F, 4, 2, 1, 0.0F, false));
		padTop.cubeList.add(new ModelBox(padTop, 2, 22, -1.0F, -1.0F, -15.0F, 2, 3, 1, 0.0F, false));

		hinge = new ModelRenderer(this);
		hinge.setRotationPoint(-4.0F, 14.5F, 13.5F);
		hinge.cubeList.add(new ModelBox(hinge, 4, 16, 0.0F, -2.5F, -7.0F, 1, 3, 1, 0.0F, false));
		hinge.cubeList.add(new ModelBox(hinge, 4, 16, 7.0F, -2.5F, -7.0F, 1, 3, 1, 0.0F, false));
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		bottom.render(f5);
		north.render(f5);
		south.render(f5);
		west.render(f5);
		east.render(f5);
		posts.render(f5);
		lid.render(f5);
		padBottom.render(f5);
		padTop.render(f5);
		hinge.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	}

	@Override
	public void renderAll(ITreasureChestTileEntity te) {
		float f5 = 0.0625F;

		padTop.rotateAngleX = lid.rotateAngleX;

		lid.render(f5);
		bottom.render(f5);
		north.render(f5);
		south.render(f5);
		west.render(f5);
		east.render(f5);
		posts.render(f5);
		padBottom.render(f5);
		padTop.render(f5);
		hinge.render(f5);
	}

	@Override
	public ModelRenderer getLid() {
		return lid;
	}

}