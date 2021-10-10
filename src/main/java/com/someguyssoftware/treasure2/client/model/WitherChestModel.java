package com.someguyssoftware.treasure2.client.model;

import com.someguyssoftware.treasure2.tileentity.ITreasureChestTileEntity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * 
 * @author Mark Gottschling on Jun 17, 2018
 *
 */
public class WitherChestModel extends ModelBase implements ITreasureChestModel {
	// fields
	ModelRenderer bottom;
	ModelRenderer back;
	ModelRenderer top;
	ModelRenderer rside;
	ModelRenderer lside;
	ModelRenderer lfront;
	ModelRenderer rfront;
	ModelRenderer backBark;
	ModelRenderer backBark2;
	ModelRenderer sideBark;
	ModelRenderer root1;
	ModelRenderer root2;

	public WitherChestModel() {
		textureWidth = 64;
		textureHeight = 128;

		bottom = new ModelRenderer(this);
		bottom.setRotationPoint(0.0F, 20.0F, 7.0F);
		bottom.cubeList.add(new ModelBox(bottom, 0, 0, -7.0F, 0.0F, -14.0F, 14, 4, 14, 0.0F, false));

		back = new ModelRenderer(this);
		back.setRotationPoint(0.0F, 2.0F, 7.0F);
		back.cubeList.add(new ModelBox(back, 0, 19, -7.0F, 0.0F, -4.0F, 14, 18, 4, 0.0F, false));
		back.cubeList.add(new ModelBox(back, 0, 120, -6.0F, 4.0F, -11.0F, 12, 1, 7, 0.0F, false));
		back.cubeList.add(new ModelBox(back, 0, 120, -6.0F, 9.0F, -11.0F, 12, 1, 7, 0.0F, false));
		back.cubeList.add(new ModelBox(back, 0, 120, -6.0F, 14.0F, -11.0F, 12, 1, 7, 0.0F, false));

		top = new ModelRenderer(this);
		top.setRotationPoint(0.0F, -2.0F, 7.0F);
		top.cubeList.add(new ModelBox(top, 0, 42, -7.0F, 0.0F, -14.0F, 14, 4, 14, 0.0F, false));

		rside = new ModelRenderer(this);
		rside.setRotationPoint(7.0F, 2.0F, 3.0F);
		rside.cubeList.add(new ModelBox(rside, 0, 62, -4.0F, 0.0F, -6.0F, 4, 18, 6, 0.0F, false));

		lside = new ModelRenderer(this);
		lside.setRotationPoint(-7.0F, 2.0F, 3.0F);
		lside.cubeList.add(new ModelBox(lside, 21, 62, 0.0F, 0.0F, -6.0F, 4, 18, 6, 0.0F, false));

		lfront = new ModelRenderer(this);
		lfront.setRotationPoint(-7.0F, 2.0F, 3.0F);
		lfront.cubeList.add(new ModelBox(lfront, 23, 88, 0.0F, 0.0F, -10.0F, 7, 18, 4, 0.0F, false));
		lfront.cubeList.add(new ModelBox(lfront, 19, 28, 6.0F, 13.0F, -11.0F, 2, 1, 1, 0.0F, false));

		rfront = new ModelRenderer(this);
		rfront.setRotationPoint(7.0F, 2.0F, 3.0F);
		rfront.cubeList.add(new ModelBox(rfront, 0, 88, -7.0F, 0.0F, -10.0F, 7, 18, 4, 0.0F, false));
		rfront.cubeList.add(new ModelBox(rfront, 18, 26, -8.0F, 4.0F, -11.0F, 2, 1, 1, 0.0F, false));

		backBark = new ModelRenderer(this);
		backBark.setRotationPoint(4.0F, -8.0F, 7.0F);
		backBark.cubeList.add(new ModelBox(backBark, 0, 112, -4.0F, 0.0F, -2.0F, 7, 6, 2, 0.0F, false));

		backBark2 = new ModelRenderer(this);
		backBark2.setRotationPoint(-2.0F, -6.0F, 5.0F);
		backBark2.cubeList.add(new ModelBox(backBark2, 19, 112, -2.0F, 0.0F, 0.0F, 4, 4, 2, 0.0F, false));

		sideBark = new ModelRenderer(this);
		sideBark.setRotationPoint(5.0F, -5.0F, -3.0F);
		sideBark.cubeList.add(new ModelBox(sideBark, 38, 103, 0.0F, 0.0F, 0.0F, 2, 3, 8, 0.0F, false));
		sideBark.cubeList.add(new ModelBox(sideBark, 32, 115, -7.0F, 1.0F, 3.0F, 7, 2, 5, 0.0F, false));

		root1 = new ModelRenderer(this);
		root1.setRotationPoint(7.0F, 23.0F, -4.0F);
		root1.cubeList.add(new ModelBox(root1, 21, 30, 0.0F, -2.0F, 0.0F, 1, 3, 6, 0.0F, false));
		root1.cubeList.add(new ModelBox(root1, 22, 29, 1.0F, -1.0F, 2.0F, 3, 5, 3, 0.0F, false));
		root1.cubeList.add(new ModelBox(root1, 22, 29, -17.0F, -1.0F, 6.0F, 3, 5, 3, 0.0F, false));
		root1.cubeList.add(new ModelBox(root1, 23, 29, -19.0F, 0.0F, 7.0F, 2, 4, 2, 0.0F, false));
		root1.cubeList.add(new ModelBox(root1, 18, 30, -12.0F, -6.0F, 11.0F, 7, 9, 2, 0.0F, false));
		root1.cubeList.add(new ModelBox(root1, 22, 29, -9.0F, -3.0F, 13.0F, 3, 6, 3, 0.0F, false));

		root2 = new ModelRenderer(this);
		root2.setRotationPoint(-4.0F, 22.0F, -8.0F);
		root2.cubeList.add(new ModelBox(root2, 21, 31, 0.0F, 0.0F, 0.0F, 6, 2, 1, 0.0F, false));
		root2.cubeList.add(new ModelBox(root2, 21, 31, 1.0F, 1.0F, -2.0F, 3, 5, 2, 0.0F, false));
	}

	/**
	 * 
	 */
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		bottom.render(f5);
		back.render(f5);
		top.render(f5);
		rside.render(f5);
		lside.render(f5);
		lfront.render(f5);
		rfront.render(f5);
		backBark.render(f5);
		backBark2.render(f5);
		sideBark.render(f5);
		root1.render(f5);
		root2.render(f5);
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
	public void renderAll(ITreasureChestTileEntity te) {
		rside.rotateAngleY = rfront.rotateAngleY;
		lfront.rotateAngleY = -(rfront.rotateAngleY);
		lside.rotateAngleY = lfront.rotateAngleY;
		
		bottom.render(0.0625F);
		back.render(0.0625F);
		top.render(0.0625F);
		rside.render(0.0625F);
		lside.render(0.0625F);
		lfront.render(0.0625F);
		rfront.render(0.0625F);
		backBark.render(0.0625F);
		backBark2.render(0.0625F);
		sideBark.render(0.0625F);
		root1.render(0.0625F);
		root2.render(0.0625F);
	}

	@Override
	public ModelRenderer getLid() {
		return rfront;
	}
	
	public ModelRenderer getRightFrontDoor() {
		return rfront;
	}
	
	public ModelRenderer getLeftFrontDoor() {
		return lfront;
	}
	
	public ModelRenderer getRightSideDoor() {
		return rside;
	}
	
	public ModelRenderer getLeftSideDoor() {
		return lside;
	}
}
