package com.someguyssoftware.treasure2.particle;

import java.util.Random;

import com.someguyssoftware.gottschcore.positional.ICoords;
import com.someguyssoftware.treasure2.Treasure;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class MistParticle extends AbstractMistParticle {
	protected static final float ALPHA_VALUE = 0.1F;
	protected static final float MAX_SCALE_VALUE = 12;
	
//	private final ResourceLocation mistResourceLocation = new ResourceLocation(Treasure.MODID, "particle/mist_particle");
	private static ResourceLocation mistParticlesSprites[] = new ResourceLocation[4];
	
	static {
		mistParticlesSprites[0] = new ResourceLocation(Treasure.MODID, "particle/mist_particle");
		mistParticlesSprites[1] = new ResourceLocation(Treasure.MODID, "particle/mist_particle2");
		mistParticlesSprites[2] = new ResourceLocation(Treasure.MODID, "particle/mist_particle3");
		mistParticlesSprites[3] = new ResourceLocation(Treasure.MODID, "particle/mist_particle4");
	}
	
	/**
	 * 
	 * @param world
	 * @param x
	 * @param y
	 * @param z
	 * @param velocityX
	 * @param velocityY
	 * @param velocityZ
	 * @param parentCoords
	 */
	public MistParticle(World world, double x, double y, double z, double velocityX, double velocityY, double velocityZ, ICoords parentCoords) {
		super(world, x, y, z);//, velocityX, velocityY, velocityZ);
		this.setParentEmitterCoords(parentCoords);
                
		// set the texture to the flame texture, which we have previously added using TextureStitchEvent
		//   (see TextureStitcherBreathFX)
		// randomly select a mist sprite
		Random random = new Random();
		int index = random.nextInt(4);
		TextureAtlasSprite sprite = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(mistParticlesSprites[index].toString());
//		TextureAtlasSprite sprite = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(mistResourceLocation.toString());
		setParticleTexture(sprite);  // initialise the icon to our custom texture
	}

	@Override
	public void doPlayerCollisions(World world) {
		// do nothing
	}
	
	@Override
	public void inflictEffectOnPlayer(EntityPlayer player) {
		// do nothing
	}

	@Override
	public float getMaxScale() {
		return MAX_SCALE_VALUE;
	}
	
	@Override
	public float getMaxAlpha() {
		return ALPHA_VALUE;
	}
}

