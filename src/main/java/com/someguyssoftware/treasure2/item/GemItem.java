package com.someguyssoftware.treasure2.item;

import biomesoplenty.api.enums.BOPGems;
import com.someguyssoftware.gottschcore.cube.Cube;
import com.someguyssoftware.gottschcore.item.ModItem;
import com.someguyssoftware.gottschcore.loot.LootPoolShell;
import com.someguyssoftware.gottschcore.loot.LootTableShell;
import com.someguyssoftware.gottschcore.positional.Coords;
import com.someguyssoftware.gottschcore.positional.ICoords;
import com.someguyssoftware.gottschcore.random.RandomHelper;
import com.someguyssoftware.gottschcore.world.WorldInfo;
import com.someguyssoftware.treasure2.Treasure;
import com.someguyssoftware.treasure2.block.IWishingWellBlock;
import com.someguyssoftware.treasure2.enums.Coins;
import com.someguyssoftware.treasure2.enums.Gems;
import com.someguyssoftware.treasure2.enums.Rarity;
import com.someguyssoftware.treasure2.item.wish.IWishable;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootPool;

import java.util.*;

import static com.someguyssoftware.treasure2.Treasure.logger;

/**
 * @author Mark Gottschling on May 14, 2020
 *
 */
public class GemItem extends ModItem implements IWishable, IPouchable {


	private Gems gem;
	
	/**
	 * 
	 */
	public GemItem (String modID, String name)	 {
		super();
		this.setItemName(modID, name);
		setCreativeTab(Treasure.TREASURE_TAB);

		this.gem = Gems.ONYX;
	}

	/**
	 *
	 * @param gem
	 */
	public GemItem(String modID, String name, Gems gem) {
		this(modID, name);
		this.setGem(gem);
	}

	/**
	 *
	 */
	@SuppressWarnings("deprecation")
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		super.addInformation(stack, worldIn, tooltip, flagIn);
		tooltip.add(TextFormatting.GOLD + "" + TextFormatting.ITALIC + "Can be thrown into Wishing Wells!");
		if (getGem() == Gems.ONYX) {
			tooltip.add(TextFormatting.GOLD + "" + TextFormatting.ITALIC + "Can unlock slots on Adornments");
			tooltip.add(I18n.translateToLocalFormatted("tooltip.label.rarity", TextFormatting.AQUA + "EPIC"));
		}
		else tooltip.add(I18n.translateToLocalFormatted("tooltip.label.rarity", TextFormatting.AQUA + "RARE"));
	}

	/**
	 *
	 */
	@Override
	public boolean onEntityItemUpdate(EntityItem entityItem) {
		// get the item stack or number of items.
		ItemStack entityItemStack = entityItem.getItem();

		World world = entityItem.getEntityWorld();
		if (WorldInfo.isClientSide(world)) {
			return super.onEntityItemUpdate(entityItem);
		}

		// get the position
		ICoords coords = new Coords(entityItem.getPosition());
		Cube cube = new Cube(world, coords);
		int numWishingWellBlocks = 0;
		// check if in water
		if (cube.equalsBlock(Blocks.WATER)) {
			// check if the water block is adjacent to 2 wishing well blocks
			ICoords checkCoords = coords.add(-1, 0, -1);
			for (int z = 0; z < 3; z++) {
				for (int x = 0; x < 3; x++) {
					Cube checkCube = new Cube(world, checkCoords);
					if (checkCube.toBlock() instanceof IWishingWellBlock) {
						numWishingWellBlocks++;
					}
					if (numWishingWellBlocks >= 2) {
						break;
					}
				}
			}

			if (numWishingWellBlocks >=2) {
				Random random = new Random();
				for (int itemIndex = 0; itemIndex < entityItemStack.getCount(); itemIndex++) {
					// generate an item for each item in the stack
					generateLootItem(world, random, entityItem, coords);
				}
				return true;
			}
		}

		return super.onEntityItemUpdate(entityItem);
	}


	/**
	 *
	 * @param world
	 * @param random
	 * @param entityItem
	 * @param coords
	 */
	private void generateLootItem(World world, Random random, EntityItem entityItem, ICoords coords) {
		ItemStack gemItem = entityItem.getItem();
		NBTTagCompound nbt = gemItem.getTagCompound();
		List<LootTableShell> lootTables = new ArrayList<>();

		// determine gem type
		if (getGem() == Gems.ONYX) {
			lootTables.addAll(Treasure.LOOT_TABLE_MASTER.getLootTableByRarity(Rarity.EPIC));
		}
		else lootTables.addAll(Treasure.LOOT_TABLE_MASTER.getLootTableByRarity(Rarity.RARE));

		ItemStack stack = null;
		// handle if loot tables is null or size = 0. return an item (apple) to ensure continuing functionality
		if (lootTables == null || lootTables.size() == 0) {
			stack = new ItemStack(Items.APPLE);
		}
		else {
			// get the player if the gem was tossed
			EntityPlayer player = null;
			if (nbt != null && nbt.hasKey(DROPPED_BY_KEY)) {
				Treasure.logger.debug("dropped by key ->{}", nbt.getString(DROPPED_BY_KEY));
				player = Optional.of(world.getPlayerEntityByUUID(UUID.fromString(nbt.getString(DROPPED_BY_KEY))))
						.orElseGet(() -> {
									Treasure.logger.debug("getting player by name");
									return world.getPlayerEntityByName(nbt.getString(DROPPED_BY_KEY));
								}
						);

				if (player != null && logger.isDebugEnabled()) {
					logger.debug("gem dropped by player -> {}", player.getName());
				}
			}
			// build the loot context
			LootContext lootContext = getLootContext(world, player);

			// select a table shell
			LootTableShell tableShell = lootTables.get(RandomHelper.randomInt(random, 0, lootTables.size()-1));
			if (tableShell.getResourceLocation() == null) {
				return;
			}

			// get the vanilla table from shell
			net.minecraft.world.storage.loot.LootTable table = world.getLootTableManager().getLootTableFromLocation(tableShell.getResourceLocation());
			// get a list of loot pools
			List<LootPoolShell> lootPoolShells = tableShell.getPools();

			List<ItemStack> itemStacks = new ArrayList<>();
			for (LootPoolShell pool : lootPoolShells) {
				logger.debug("gem: processing pool -> {}", pool.getName());
				// go get the vanilla managed pool
				LootPool lootPool = table.getPool(pool.getName());

				// geneate loot from pools
				lootPool.generateLoot(itemStacks, random,  lootContext);
			}

			// get effective rarity
			Rarity effectiveRarity = Treasure.LOOT_TABLE_MASTER.getEffectiveRarity(tableShell, (getGem() == Gems.ONYX) ? Rarity.EPIC : Rarity.RARE);
			logger.debug("gem: using effective rarity -> {}", effectiveRarity);

			// get all injected loot tables
			logger.debug("gem: searching for injectable tables for category ->{}, rarity -> {}", tableShell.getCategory(), effectiveRarity);
			Optional<List<LootTableShell>> injectLootTableShells = buildInjectedLootTableList(tableShell.getCategory(), effectiveRarity);
			if (injectLootTableShells.isPresent()) {
				logger.debug("gem: found injectable tables for category ->{}, rarity -> {}", tableShell.getCategory(), effectiveRarity);
				logger.debug("gem: size of injectable tables -> {}", injectLootTableShells.get().size());
				itemStacks.addAll(getLootItems(world, random, injectLootTableShells.get(), lootContext));
			}

			// select one item randomly
			stack = itemStacks.get(RandomHelper.randomInt(0, itemStacks.size()-1));
		}

		// spawn the item
		if (stack != null) {
			world.playSound(null, coords.toPos(), SoundEvents.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.BLOCKS, 0.5F, 2.0F);
			((WorldServer) world).spawnParticle(EnumParticleTypes.END_ROD, (double)coords.getX()+0.5D, (double)coords.getY()+1, (double)coords.getZ()+0.5D, 100, 0.0D, 0.1D, 0.0D, 0.5D);
			InventoryHelper.spawnItemStack(world, (double)coords.getX(), (double)coords.getY()+1, (double)coords.getZ(), stack);
		}
		// remove the item entity
		entityItem.setDead();
	}

	/**
	 * @return the gem
	 */
	public Gems getGem() {
		return gem;
	}
	/**
	 * @param gem the gem to set
	 * @return
	 */
	public GemItem setGem(Gems gem) {
		this.gem = gem;
		return this;
	}
}
