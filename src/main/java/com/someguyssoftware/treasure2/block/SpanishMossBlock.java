/**
 * 
 */
package com.someguyssoftware.treasure2.block;

import java.util.Random;

import com.someguyssoftware.gottschcore.random.RandomHelper;
import com.someguyssoftware.gottschcore.world.WorldInfo;
import com.someguyssoftware.treasure2.config.TreasureConfig;
import com.someguyssoftware.treasure2.particle.AbstractMistParticle;
//import com.someguyssoftware.treasure2.particle.PoisonMistParticle;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.BushBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.fluid.Fluid;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/**
 * @author Mark Gottschling on Jul 25, 2018
 *
 */
public class SpanishMossBlock extends BushBlock implements ITreasureBlock {
	public static final BooleanProperty ACTIVATED = BooleanProperty.create("activated");

	/**
	 * 
	 */
	public SpanishMossBlock(String modID, String name, Block.Properties properties) {
		// NOTE it is wood so I didn't have to update GottschCore.get..SurfaceCoods() to check for PLANTS.
		super(properties.hardnessAndResistance(0.6F).doesNotBlockMovement().sound(SoundType.PLANT));
		setRegistryName(modID, name);
		this.setDefaultState(this.stateContainer.getBaseState().with(ACTIVATED, Boolean.valueOf(false)));
	}

	/**
	 * 
	 */
	@Override
	public boolean isNormalCube(BlockState state, IBlockReader world, BlockPos pos) {
		return false;
	}
	
	/**
	 * 
	 */
	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(ACTIVATED);
	}

	/**
	 * NOTE randomDisplayTick is on the client side only. The server is not keeping
	 * track of any particles NOTE cannot control the number of ticks per
	 * randomDisplayTick() call - it is not controlled by tickRate()
	 */
	@Override
	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState state, World world, BlockPos pos, Random random) {
		if (WorldInfo.isServerSide(world)) {
			return;
		}

		//		if (!TreasureConfig.FOG.enableFog.get()) {
		//			return;
		//		}

		if (!state.get(ACTIVATED)) {
			return;
		}

		if (RandomHelper.checkProbability(random, 75D)) {
			return;
		}

		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();

		// initial positions - has a spread area of up to 1.5 blocks
		double xPos = x;
		double yPos = y;
		double zPos = z;
		// initial velocities
		double velocityX = 0;
		double velocityY = 0;
		double velocityZ = 0;

		//		AbstractMistParticle mistParticle = new PoisonMistParticle(world, xPos, yPos, zPos, velocityX, velocityY,
		//				velocityZ, new Coords(pos)) {
		//			@Override
		//			public float provideGravity() {
		//				return 0.0001F;
		//			}
		//
		//			@Override
		//			public float provideAlpha() {
		//				return DEFAULT_PARTICLE_ALPHA;
		//			}
		//
		//			@Override
		//			public float provideMaxScale() {
		//				return 10F;
		//			}
		//
		//			@Override
		//			public void doPlayerCollisions(World world) {
		//			}
		//		};
		//		mistParticle.init();
		//
		//		Minecraft.getMinecraft().effectRenderer.addEffect(mistParticle);
	}

	@Override
	public boolean isReplaceable(BlockState blockState, Fluid fluid) {
		return true;
	}

	@Override
	protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos) {
		return true;
	}
}