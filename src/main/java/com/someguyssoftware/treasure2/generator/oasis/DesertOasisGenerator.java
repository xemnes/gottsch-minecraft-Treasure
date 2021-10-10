/**
 * 
 */
package com.someguyssoftware.treasure2.generator.oasis;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import biomesoplenty.api.biome.BOPBiomes;
import com.ferreusveritas.dynamictrees.api.TreeHelper;
import com.ferreusveritas.dynamictrees.api.TreeRegistry;
import com.ferreusveritas.dynamictrees.api.treedata.ITreePart;
import com.ferreusveritas.dynamictrees.api.worldgen.IGroundFinder;
import com.ferreusveritas.dynamictrees.blocks.BlockBranch;
import com.ferreusveritas.dynamictrees.blocks.BlockRooty;
import com.ferreusveritas.dynamictrees.blocks.BlockRootyDirt;
import com.ferreusveritas.dynamictrees.blocks.BlockRootySand;
import com.ferreusveritas.dynamictrees.trees.Species;
import com.ferreusveritas.dynamictrees.trees.TreeDarkOak;
import com.ferreusveritas.dynamictrees.trees.TreeOak;
import com.ferreusveritas.dynamictrees.util.SafeChunkBounds;
import com.ferreusveritas.dynamictrees.worldgen.BiomeDataBase;
import com.ferreusveritas.dynamictrees.worldgen.JoCode;
import com.ferreusveritas.dynamictrees.worldgen.TreeGenerator;
import com.ferreusveritas.dynamictrees.worldgen.WorldGeneratorTrees;
import com.someguyssoftware.gottschcore.cube.Cube;
import com.someguyssoftware.gottschcore.positional.Coords;
import com.someguyssoftware.gottschcore.positional.ICoords;
import com.someguyssoftware.gottschcore.random.RandomHelper;
import com.someguyssoftware.gottschcore.world.WorldInfo;
import com.someguyssoftware.treasure2.Treasure;
import com.someguyssoftware.treasure2.block.AbstractChestBlock;
import com.someguyssoftware.treasure2.chest.ChestInfo;
import com.someguyssoftware.treasure2.config.IOasisConfig;
import com.someguyssoftware.treasure2.config.TreasureConfig;
import com.someguyssoftware.treasure2.enums.Rarity;
import com.someguyssoftware.treasure2.enums.WorldGeneratorType;
import com.someguyssoftware.treasure2.generator.ChestGeneratorData;
import com.someguyssoftware.treasure2.generator.GeneratorData;
import com.someguyssoftware.treasure2.generator.GeneratorResult;
import com.someguyssoftware.treasure2.generator.TemplateGeneratorData;
import com.someguyssoftware.treasure2.generator.chest.IChestGenerator;
import com.someguyssoftware.treasure2.generator.well.IWellGenerator;
import com.someguyssoftware.treasure2.generator.well.WellGenerator;
import com.someguyssoftware.treasure2.meta.StructureArchetype;
import com.someguyssoftware.treasure2.meta.StructureType;
import com.someguyssoftware.treasure2.registry.ChestRegistry;
import com.someguyssoftware.treasure2.world.gen.structure.TemplateHolder;
import com.someguyssoftware.treasure2.worldgen.SurfaceChestWorldGenerator;

import exterminatorjeff.undergroundbiomes.common.block.IgneousSand;
import exterminatorjeff.undergroundbiomes.common.block.MetamorphicSand;
import exterminatorjeff.undergroundbiomes.common.block.SedimentarySand;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockStateMatcher;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenFlowers;
import net.minecraft.world.gen.feature.WorldGenerator;

/**
 * @author Mark
 *
 */
public class DesertOasisGenerator implements IOasisGenerator<GeneratorResult<ChestGeneratorData>> {

	private static final int MIN_OASIS_RADIUS = 8;
	private static final int MAX_OASIS_RADIUS = 20;

	private int TREETYPE = 6;

	List<BlockPos> possibleTreeCoords = new ArrayList<>();

	/**
	 * 
	 */
	public DesertOasisGenerator() {
		
	}
	
	@Override
	public GeneratorResult<GeneratorData> generate(World world, Random random, ICoords coords) {

		ICoords chestCoords = null;
		setVariant(random);


		// result to return to the caller
		GeneratorResult<GeneratorData> result = new GeneratorResult<>(GeneratorData.class);

		// 1. collect location data points
		ICoords surfaceCoords = WorldInfo.getDryLandSurfaceCoords(world, coords);
		Treasure.logger.debug("surface coords -> {}", surfaceCoords.toShortString());
		if (!WorldInfo.isValidY(surfaceCoords)) {
			Treasure.logger.debug("surface coords are invalid @ {}", surfaceCoords.toShortString());
			return result.fail();
		}

		// determine size of oasis
		int radius = RandomHelper.randomInt(random, MIN_OASIS_RADIUS, MAX_OASIS_RADIUS); // min of 8, so diameter = 16, area = 16x16, same as chunk
		AxisAlignedBB oasisBounds = new AxisAlignedBB(coords.add(-radius, 0, -radius).toPos() , coords.add(radius, 0, radius).toPos());
		int width = Math.abs((int) (oasisBounds.maxX - oasisBounds.minX));
		int depth = Math.abs((int) (oasisBounds.maxZ - oasisBounds.minZ));
		ICoords centerCoords = new Coords((int)(oasisBounds.minX + width * 0.5D), (int)oasisBounds.minY, (int)(oasisBounds.minZ + depth * 0.5D));

		centerCoords = WorldInfo.getDryLandSurfaceCoords(world, centerCoords);
		
		generateBase(world, random, coords, radius);
		generateGrass(world, random, centerCoords, radius);
		generateFlowers(world, random, centerCoords, radius);
		Optional<GeneratorResult<GeneratorData>> wellResult = Optional.ofNullable(generateWell(world, random, centerCoords));

		// calculate coords for chest
		if (wellResult.isPresent() && wellResult.get().isSuccess()) {
			TemplateGeneratorData data = (TemplateGeneratorData) wellResult.get().getData();
			ICoords wellSpawnCoords = data.getSpawnCoords();
			ICoords wellSize = data.getSize();			
			// calculate the chest coords
			chestCoords = wellSpawnCoords.add(wellSize.getX(), 0, wellSize.getZ()/2);
			// update wellResult to contain the center coords
			wellResult.get().getData().setSpawnCoords(centerCoords);
		}
		else {
			chestCoords = centerCoords;
		}		
		generateChest(world, random, chestCoords);
		generateTrees(world, random, centerCoords, radius);

		Treasure.logger.info("CHEATER! Desert oasis at coords: {}", centerCoords.toShortString());
		return wellResult.get();
	}

	/**
	 * 
	 * @param world
	 * @param random
	 * @param centerCoords
	 * @return
	 */
	private GeneratorResult<GeneratorData> generateWell(World world, Random random, ICoords centerCoords) {
		IWellGenerator<GeneratorResult<GeneratorData>> wellGenerator = new WellGenerator();
		
		// select a well template (don't allow well generator to select its own - it will always be desert)
		TemplateHolder templateHolder = Treasure.TEMPLATE_MANAGER.getTemplate(world, random, StructureArchetype.SURFACE, StructureType.WELL, Biomes.FOREST);
				
		GeneratorResult<GeneratorData> result = wellGenerator.generate(world, random, centerCoords, templateHolder, TreasureConfig.WELL);
		return result;
	}

	/**
	 * 
	 * @param world
	 * @param random
	 * @param chestCoords
	 * @return
	 */
	private GeneratorResult<ChestGeneratorData> generateChest(World world, Random random, ICoords chestCoords) {
		// select a random rarity
		Rarity rarity = Rarity.values()[random.nextInt(Rarity.values().length)];
		// select a random facing
		IBlockState chestState = Blocks.CHEST.getDefaultState().withProperty(BlockChest.FACING, EnumFacing.HORIZONTALS[random.nextInt(EnumFacing.HORIZONTALS.length)]);
		SurfaceChestWorldGenerator chestWorldGen = (SurfaceChestWorldGenerator) Treasure.WORLD_GENERATORS.get(WorldGeneratorType.SURFACE_CHEST);
		IChestGenerator chestGen = chestWorldGen.getChestGenMap().get(rarity).next();
		Optional<GeneratorResult<ChestGeneratorData>> result = Optional.ofNullable(chestGen.generate(world, random, chestCoords, rarity, chestState));
		if (result.isPresent() && result.get().isSuccess()) {
			// add to chest registry as the rarity will be unknown to passed up to caller
			ChestRegistry.getInstance().register(chestCoords.toShortString(), new ChestInfo(rarity, chestCoords));
		}
		return result.get();
	}

	/**
	 * 
	 * @param world
	 * @param random
	 * @param coords
	 * @param radius
	 */
	private void generateBase(World world, Random random, ICoords coords, int radius) {
		int radiusSquared = radius * radius;
		Integer[] distancesMet = new Integer[radius + 1];
		ICoords spawnCoords = null;
		for (int xOffset = -(radius); xOffset <= radius; xOffset++) {
			for (int zOffset = -(radius); zOffset <= radius; zOffset++) {
				boolean isDistanceMet = false;
				spawnCoords = coords.add(xOffset, 0, zOffset);
				if (distancesMet[Math.abs(xOffset)] != null) {
					if (Math.abs(zOffset) <= distancesMet[Math.abs(xOffset)]) {
						isDistanceMet = true;
					}
				}
				else {
					if (coords.getDistanceSq(spawnCoords) < radiusSquared) {
						distancesMet[Math.abs(xOffset)] = Math.abs(zOffset);
						isDistanceMet = true;
					}
				}

				if (isDistanceMet) {
					spawnCoords = WorldInfo.getDryLandSurfaceCoords(world, spawnCoords.withY(255));
					// replace to a depth of 3 blocks
					for (int i = 0; i < 3; i++) {
						Cube grassCube = new Cube(world, spawnCoords);
						spawnCoords = spawnCoords.down(1);			
						Cube sandCube = new Cube(world, spawnCoords.down(1));
						if (sandCube.equalsBlock(Blocks.SAND) || (i > 0 && grassCube.equalsBlock(Blocks.GRASS))) {
							world.setBlockState(spawnCoords.toPos(), Blocks.GRASS.getDefaultState(), 3);}
					}
				}
			}
		}		
	}


	private void setVariant(Random random)
	{
		this.TREETYPE = random.nextInt(6);
	}


	private void generateTrees(World world, Random random, ICoords centerCoords, int radius) {
		int diameter = radius * 2;
		int diameterSquared = diameter * diameter;

		// get the number of trees based on size of oasis
		int numberOfTrees = (int) ((diameterSquared / 256 ) * (double)getConfig().getTreesPerChunk() * getConfig().getTreesPerChunkSizeFactor());
		// for each tree
		for (int treeIndex = 0; treeIndex < numberOfTrees; treeIndex++) {
			// find a random location within the oasis
			int xOffset = (int) (random.nextFloat() * diameter - radius);
			int zOffset = (int) (random.nextFloat() * diameter - radius);

			ICoords treeCoords = centerCoords.add(xOffset, 0, zOffset);
			IGroundFinder groundFinder = new WorldGeneratorTrees.GroundFinder();

			Biome biome = world.getBiome(treeCoords.toPos());
			BiomeDataBase.BiomeEntry biomeEntry = TreeGenerator.getTreeGenerator().getBiomeDataBase(world).getEntry(biome);
			BlockPos groundPos = groundFinder.findGround(biomeEntry, world, treeCoords.toPos());
			Treasure.logger.debug("attempting oasis tree @ -> {}", treeCoords.toShortString());

			for (BlockPos soilRange : BlockPos.getAllInBox(groundPos.add(-1, -1, -1), groundPos.add(1, 1, 1))) {
				possibleTreeCoords.add(soilRange);
			}

			IBlockState soilBlock = world.getBlockState(groundPos);
			Block block = soilBlock.getBlock();

			if (possibleTreeCoords.stream().anyMatch(pos -> world.getBlockState(pos).getBlock() instanceof ITreePart)) {
				possibleTreeCoords.clear();
				return;
			}

			else if (
					block instanceof BlockGrass
					|| block instanceof BlockSand
					|| block instanceof IgneousSand
					|| block instanceof SedimentarySand
					|| block instanceof MetamorphicSand
			){
				this.TREETYPE = random.nextInt(6);
				Species species;

				if (this.TREETYPE == 0) {
					species = TreeRegistry.findSpeciesSloppy("dynamictreesbop:palm");
				} else if (this.TREETYPE == 1) {
					species = TreeRegistry.findSpeciesSloppy("dynamictreesphc:banana");
				} else if (this.TREETYPE == 2) {
					species = TreeRegistry.findSpeciesSloppy("dynamictreesphc:coconut");
				} else if (this.TREETYPE == 3) {
					species = TreeRegistry.findSpeciesSloppy("dynamictreesphc:papaya");
				} else if (this.TREETYPE == 4) {
					species = TreeRegistry.findSpeciesSloppy("dynamictreesforestry:papaya");
				} else {
					species = TreeRegistry.findSpeciesSloppy("dynamictreesforestry:palm");
				}
				species.generate(world, groundPos, world.getBiome(treeCoords.toPos()), random, 8, SafeChunkBounds.ANY);
				TreeHelper.ageVolume(world, groundPos, 3, 14, 1, SafeChunkBounds.ANY);
				possibleTreeCoords.clear();
			}
		}

	}



	
	/**
	 * 
	 * @param world
	 * @param random
	 * @param centerCoords
	 * @param radius
	 */
	private void generateGrass(World world, Random random, ICoords centerCoords, int radius) {
		WorldGenerator grassWorldGenerator = Biomes.FOREST.getRandomWorldGenForGrass(random);
		int diameter = radius * 2;
		
		for (int grassIndex = 0; grassIndex < 5; grassIndex++) {
			// find a random location within the oasis
			int xOffset = (int) (random.nextFloat() * diameter - radius);
			int zOffset = (int) (random.nextFloat() * diameter - radius);
			grassWorldGenerator.generate(world, random, centerCoords.add(xOffset, 0, zOffset).toPos());
		}
	}
	
	/**
	 * 
	 * @param world
	 * @param random
	 * @param centerCoords
	 * @param radius
	 */
	private void generateFlowers(World world, Random random, ICoords centerCoords, int radius) {
		WorldGenFlowers worldGenerator = new WorldGenFlowers(Blocks.YELLOW_FLOWER, BlockFlower.EnumFlowerType.DANDELION);
		int diameter = radius * 2;		
		
		for (int grassIndex = 0; grassIndex < 3; grassIndex++) {
			// find a random location within the oasis
			int xOffset = (int) (random.nextFloat() * diameter - radius);
			int zOffset = (int) (random.nextFloat() * diameter - radius);

			ICoords blockpos1 = centerCoords.add(xOffset, 0, zOffset);
            BlockFlower.EnumFlowerType blockflower$enumflowertype = Biomes.FOREST.pickRandomFlower(random, blockpos1.toPos());
            BlockFlower blockflower = blockflower$enumflowertype.getBlockType().getBlock();

            if (blockflower.getDefaultState().getMaterial() != Material.AIR) {
                worldGenerator.setGeneratedBlock(blockflower, blockflower$enumflowertype);
                worldGenerator.generate(world, random, blockpos1.toPos());
            }
		}
	}

	@Override
	public IOasisConfig getConfig() {
		return TreasureConfig.OASES.desertOasisProperties;
	}

}
