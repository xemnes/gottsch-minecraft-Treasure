package com.someguyssoftware.treasure2.client.model;

import com.someguyssoftware.treasure2.tileentity.ITreasureChestTileEntity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * 
 * @author Mark Gottschling on Jan 22, 2020
 *
 */
public class SpiderChestModel extends ModelBase implements ITreasureChestModel {
	private final ModelRenderer chest;
	private final ModelRenderer chest_r1;
	private final ModelRenderer chest_r2;
	private final ModelRenderer chest_r3;
	private final ModelRenderer chest_r4;
	private final ModelRenderer chest_r5;
	private final ModelRenderer chest_r6;
	private final ModelRenderer headBone;
	private final ModelRenderer bone;
	private final ModelRenderer bone_r1;
	private final ModelRenderer bone2;
	private final ModelRenderer bone2_r1;
	private ModelRenderer lid;

	public SpiderChestModel() {
		textureWidth = 64;
		textureHeight = 64;

		chest = new ModelRenderer(this);
		chest.setRotationPoint(-8.0F, 16.0F, 8.0F);
		chest.cubeList.add(new ModelBox(chest, 0, 0, 2.0F, -4.0F, -10.0F, 12, 5, 10, 0.0F, false));

		chest_r1 = new ModelRenderer(this);
		chest_r1.setRotationPoint(1.9F, 3.1699F, -5.7956F);
		chest.addChild(chest_r1);
		setRotationAngle(chest_r1, 0.3927F, 0.0F, 0.2618F);
		chest_r1.cubeList.add(new ModelBox(chest_r1, 42, 13, -1.0F, -3.1065F, 3.8613F, 2, 10, 2, 0.0F, false));

		chest_r2 = new ModelRenderer(this);
		chest_r2.setRotationPoint(1.2F, 3.1699F, -5.7956F);
		chest.addChild(chest_r2);
		setRotationAngle(chest_r2, 0.1745F, 0.0F, 0.3927F);
		chest_r2.cubeList.add(new ModelBox(chest_r2, 42, 13, -1.0F, -4.5268F, 0.9965F, 2, 10, 2, 0.0F, false));

		chest_r3 = new ModelRenderer(this);
		chest_r3.setRotationPoint(1.2F, 3.1699F, -5.7956F);
		chest.addChild(chest_r3);
		setRotationAngle(chest_r3, -0.1745F, 0.0F, 0.3927F);
		chest_r3.cubeList.add(new ModelBox(chest_r3, 42, 13, -1.0F, -4.5085F, -3.0686F, 2, 10, 2, 0.0F, false));

		chest_r4 = new ModelRenderer(this);
		chest_r4.setRotationPoint(14.1F, 3.1699F, -5.7956F);
		chest.addChild(chest_r4);
		setRotationAngle(chest_r4, 0.3927F, 0.0F, -0.2618F);
		chest_r4.cubeList.add(new ModelBox(chest_r4, 42, 13, -1.0F, -3.1065F, 3.8613F, 2, 10, 2, 0.0F, false));

		chest_r5 = new ModelRenderer(this);
		chest_r5.setRotationPoint(14.8F, 3.1699F, -5.7956F);
		chest.addChild(chest_r5);
		setRotationAngle(chest_r5, 0.1745F, 0.0F, -0.3927F);
		chest_r5.cubeList.add(new ModelBox(chest_r5, 42, 13, -1.0F, -4.5268F, 0.9965F, 2, 10, 2, 0.0F, false));

		chest_r6 = new ModelRenderer(this);
		chest_r6.setRotationPoint(14.8F, 3.1699F, -5.7956F);
		chest.addChild(chest_r6);
		setRotationAngle(chest_r6, -0.1745F, 0.0F, -0.3927F);
		chest_r6.cubeList.add(new ModelBox(chest_r6, 42, 13, -1.0F, -4.5085F, -3.0686F, 2, 10, 2, 0.0F, false));

		headBone = new ModelRenderer(this);
		headBone.setRotationPoint(0.0F, 13.0F, 8.0F);
		headBone.cubeList.add(new ModelBox(headBone, 0, 30, -4.0F, -3.75F, -16.0F, 8, 8, 6, 0.0F, false));

		bone = new ModelRenderer(this);
		bone.setRotationPoint(0.0F, 13.0F, 8.0F);
		setRotationAngle(bone, -0.3927F, 0.0F, 0.0F);


		bone_r1 = new ModelRenderer(this);
		bone_r1.setRotationPoint(7.0F, 7.9181F, -2.9934F);
		bone.addChild(bone_r1);
		setRotationAngle(bone_r1, -0.0121F, 0.0992F, -0.2427F);
		bone_r1.cubeList.add(new ModelBox(bone_r1, 42, 13, -1.8693F, -3.3706F, -5.8904F, 2, 10, 2, 0.0F, false));

		bone2 = new ModelRenderer(this);
		bone2.setRotationPoint(0.0F, 13.0F, 8.0F);
		setRotationAngle(bone2, -0.3927F, 0.0F, 0.0F);


		bone2_r1 = new ModelRenderer(this);
		bone2_r1.setRotationPoint(-7.0F, 7.9181F, -2.9934F);
		bone2.addChild(bone2_r1);
		setRotationAngle(bone2_r1, -0.0121F, -0.0992F, 0.2427F);
		bone2_r1.cubeList.add(new ModelBox(bone2_r1, 42, 13, -0.1307F, -3.3706F, -5.8904F, 2, 10, 2, 0.0F, false));

		lid = new ModelRenderer(this);
		lid.setRotationPoint(0.0F, 13.0F, 8.0F);
		lid.cubeList.add(new ModelBox(lid, 0, 15, -6.0F, -5.0F, -10.0F, 12, 5, 10, 0.01F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		chest.render(f5);
		headBone.render(f5);
		bone.render(f5);
		bone2.render(f5);
		lid.render(f5);
	}
	
	@Override
	public void renderAll(ITreasureChestTileEntity te) {
		headBone.rotateAngleX = lid.rotateAngleX;
		chest.render(0.0625F);
		bone.render(0.0625F);
		bone2.render(0.0625F);
		headBone.render(0.0625F);
		lid.render(0.0625F);
	}
	
	@Override
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	}
	
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
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