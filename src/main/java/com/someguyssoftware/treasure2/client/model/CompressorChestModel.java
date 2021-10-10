package com.someguyssoftware.treasure2.client.model;

import com.someguyssoftware.treasure2.lock.LockState;
import com.someguyssoftware.treasure2.tileentity.ITreasureChestTileEntity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * 
 * @author Mark Gottschling on Mar 18, 2018
 *
 */
public class CompressorChestModel extends ModelBase implements ITreasureChestModel {

	//fields
    ModelRenderer base;
    ModelRenderer lid1;
    ModelRenderer lid2;
    ModelRenderer lid3;
    ModelRenderer lid4;
    ModelRenderer hinge1;
    ModelRenderer hinge2;
    ModelRenderer hinge3;
    ModelRenderer hinge4;
    ModelRenderer latch1;
    ModelRenderer latch2;
    ModelRenderer latch3;
    ModelRenderer latch4;
    
	public CompressorChestModel() {
		textureWidth = 64;
		textureHeight = 32;

		base = new ModelRenderer(this);
		base.setRotationPoint(0.0F, 14.0F, 7.0F);
		base.cubeList.add(new ModelBox(base, 0, 0, -5.0F, 0.0F, -12.0F, 10, 10, 10, 0.0F, false));

		lid1 = new ModelRenderer(this);
		lid1.setRotationPoint(4.0F, 14.0F, -5.0F);
		lid1.cubeList.add(new ModelBox(lid1, 44, 14, -4.0F, -2.0F, 0.0F, 5, 2, 5, 0.0F, false));

		lid2 = new ModelRenderer(this);
		lid2.setRotationPoint(-5.0F, 14.0F, -3.0F);
		lid2.cubeList.add(new ModelBox(lid2, 44, 21, 0.0F, -2.0F, -2.0F, 5, 2, 5, 0.0F, false));

		lid3 = new ModelRenderer(this);
		lid3.setRotationPoint(-3.0F, 14.0F, 5.0F);
		lid3.cubeList.add(new ModelBox(lid3, 44, 0, -2.0F, -2.0F, -5.0F, 5, 2, 5, 0.0F, false));

		lid4 = new ModelRenderer(this);
		lid4.setRotationPoint(5.0F, 14.0F, 3.0F);
		lid4.cubeList.add(new ModelBox(lid4, 44, 7, -5.0F, -2.0F, -3.0F, 5, 2, 5, 0.0F, false));

		hinge1 = new ModelRenderer(this);
		hinge1.setRotationPoint(3.0F, 14.0F, -5.0F);
		hinge1.cubeList.add(new ModelBox(hinge1, 0, 24, -2.0F, -1.0F, -0.5F, 3, 1, 1, 0.0F, false));

		hinge2 = new ModelRenderer(this);
		hinge2.setRotationPoint(-5.0F, 14.0F, -3.0F);
		hinge2.cubeList.add(new ModelBox(hinge2, 0, 20, -0.5F, -1.0F, -1.0F, 1, 1, 3, 0.0F, false));

		hinge3 = new ModelRenderer(this);
		hinge3.setRotationPoint(-3.0F, 14.0F, 5.0F);
		hinge3.cubeList.add(new ModelBox(hinge3, 0, 24, -1.0F, -1.0F, -0.5F, 3, 1, 1, 0.0F, false));

		hinge4 = new ModelRenderer(this);
		hinge4.setRotationPoint(5.0F, 14.0F, 3.0F);
		hinge4.cubeList.add(new ModelBox(hinge4, 0, 20, -0.5F, -1.0F, -2.0F, 1, 1, 3, 0.0F, false));

		latch1 = new ModelRenderer(this);
		latch1.setRotationPoint(2.0F, 14.0F, -5.0F);
		latch1.cubeList.add(new ModelBox(latch1, 31, 1, 3.0F, -1.0F, 1.0F, 1, 3, 2, 0.0F, false));

		latch2 = new ModelRenderer(this);
		latch2.setRotationPoint(-5.0F, 14.0F, -3.0F);
		latch2.cubeList.add(new ModelBox(latch2, 31, 6, 1.0F, -1.0F, -3.0F, 2, 3, 1, 0.0F, false));

		latch3 = new ModelRenderer(this);
		latch3.setRotationPoint(-3.0F, 14.0F, 5.0F);
		latch3.cubeList.add(new ModelBox(latch3, 31, 1, -3.0F, -1.0F, -3.0F, 1, 3, 2, 0.0F, false));

		latch4 = new ModelRenderer(this);
		latch4.setRotationPoint(5.0F, 14.0F, 2.0F);
		latch4.cubeList.add(new ModelBox(latch4, 31, 6, -3.0F, -1.0F, 3.0F, 2, 3, 1, 0.0F, false));

	}

	/**
	 * 
	 */
	 public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	  {
	    super.render(entity, f, f1, f2, f3, f4, f5);
	    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	    base.render(f5);
	    lid1.render(f5);
	    lid2.render(f5);
	    lid3.render(f5);
	    lid4.render(f5);
	    hinge1.render(f5);
	    hinge2.render(f5);
	    hinge3.render(f5);
	    hinge4.render(f5);
	    latch2.render(f5);
	  }

	/**
	 * 
	 * @param model
	 * @param x
	 * @param y
	 * @param z
	 */
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
		float originalAngle = lid1.rotateAngleX;
		float f5 = 0.0625F;
		
//		lid3.rotateAngleX = originalAngle;
//		latch3.rotateAngleX = lid3.rotateAngleX;
//		
//		lid2.rotateAngleZ = originalAngle;
//		latch2.rotateAngleZ = lid2.rotateAngleZ;
		
		// reverse the angle direction
		lid1.rotateAngleX = -(lid1.rotateAngleX);
		latch1.rotateAngleX = lid1.rotateAngleX;
		hinge1.rotateAngleX = lid1.rotateAngleX;
		lid2.rotateAngleZ = originalAngle;
		latch2.rotateAngleZ = lid2.rotateAngleZ;
		hinge2.rotateAngleZ = lid2.rotateAngleZ;
		lid3.rotateAngleX = originalAngle;
		latch3.rotateAngleX = lid3.rotateAngleX;
		hinge3.rotateAngleX = lid3.rotateAngleX;
		lid4.rotateAngleZ = lid1.rotateAngleX;
		latch4.rotateAngleZ = lid4.rotateAngleZ;
		hinge4.rotateAngleZ = lid4.rotateAngleZ;

//		for (LockState state : te.getLockStates()) {
//			if (state.getLock() != null) {
//				switch(state.getSlot().getIndex()) {
//					case 1:
//						latch2.render(f5);
//						break;
//					case 2:
//						latch3.render(f5);
//						break;
//					case 3:
//						latch4.render(f5);
//						break;
//				}
//			}
//		}

//		lid4.rotateAngleZ = lid1.rotateAngleX;
//		latch4.rotateAngleZ = lid4.rotateAngleZ;
		
	    base.render(f5);
	    lid1.render(f5);
	    lid2.render(f5);
	    lid3.render(f5);
	    lid4.render(f5);
	    hinge1.render(f5);
	    hinge2.render(f5);
	    hinge3.render(f5);
	    hinge4.render(f5);
	    latch1.render(f5);
	    latch2.render(f5);
	    latch3.render(f5);
	    latch4.render(f5);
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
		return lid1;
	}

	/**
	 * @param lid the lid to set
	 */
	public void setLid(ModelRenderer lid) {
		this.lid1 = lid;
	}

}
