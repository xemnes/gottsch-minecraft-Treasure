/**
 * 
 */
package com.someguyssoftware.treasure2.tileentity;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import javax.annotation.Nullable;

import com.someguyssoftware.gottschcore.tileentity.AbstractModTileEntity;
import com.someguyssoftware.gottschcore.world.WorldInfo;
import com.someguyssoftware.treasure2.Treasure;
import com.someguyssoftware.treasure2.block.AbstractChestBlock;
import com.someguyssoftware.treasure2.chest.ChestSlotCount;
import com.someguyssoftware.treasure2.enums.ChestGeneratorType;
import com.someguyssoftware.treasure2.enums.Rarity;
import com.someguyssoftware.treasure2.generator.chest.IChestGenerator;
import com.someguyssoftware.treasure2.inventory.ITreasureContainer;
import com.someguyssoftware.treasure2.lock.LockState;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.FilledMapItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.IChestLid;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.INameable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.util.Constants;
/**
 * 
 * @author Mark Gottschling onDec 22, 2017
 *
 */
public abstract class AbstractTreasureChestTileEntity extends AbstractModTileEntity implements ITreasureChestTileEntity, ITickableTileEntity, INamedContainerProvider, INameable {
	public class GenerationContext {
		/*
		 * The rarity level of the loot that the chest will contain
		 */
		private Rarity lootRarity;
		/*
		 * 
		 */
		private ChestGeneratorType chestGeneratorType;

		public GenerationContext(Rarity rarity, ChestGeneratorType chestGeneratorType) {
			this.lootRarity = rarity;
			this.chestGeneratorType = chestGeneratorType;
		}

		public GenerationContext(ResourceLocation lootTable, Rarity rarity, ChestGeneratorType chestGeneratorType) {
			this.lootRarity = rarity;
			this.chestGeneratorType = chestGeneratorType;
		}

		public Rarity getLootRarity() {
			return lootRarity;
		}

		public ChestGeneratorType getChestGeneratorType() {
			return chestGeneratorType;
		}

		public ResourceLocation getLootTable() {
			return lootTable;
		}

	}

	/*
	 * A list of lockStates the chest has. The list should be the size of the max
	 * allowed for the chestType.
	 */
	private List<LockState> lockStates;

	/*
	 * The FACING index value of the TreasureChestBlock
	 */
	private Direction facing;

	/*
	 * A flag to indicate if the chest has been opened for the first time
	 */
	private boolean sealed;

	private ResourceLocation lootTable;

	/*
	 * Properties detailing how the tile entity was generated
	 */
	private GenerationContext generationContext;

	private int numberOfSlots = 27; // default size

	/*
	 * Vanilla properties for controlling the lid
	 */
	/** The current angle of the lid (between 0 and 1) */
	public float lidAngle;
	/** The angle of the lid last tick */
	public float prevLidAngle;
	/** The number of players currently using this chest */
	public int openCount;
	/** Server sync counter (once per 20 ticks) */
	public int ticksSinceSync;

	/** IInventory properties */
	//	private int numberOfSlots = 27; // default size
	private NonNullList<ItemStack> items = NonNullList.<ItemStack>withSize(getNumberOfSlots(), ItemStack.EMPTY);
	private ITextComponent customName;

	/**
	 * 
	 */
	public AbstractTreasureChestTileEntity(TileEntityType<?> type) {
		super(type);
		setFacing(Direction.NORTH.get3DDataValue());
	}

	/**
	 * Like the old updateEntity(), except more generic.
	 */
	@Override
	public void tick() {
		updateOpenCount(++this.ticksSinceSync);
		updateEntityState();
	}

	/**
	 * NOTE initialize non-zero value of this.openCount is set in startOpen()
	 * @param ticksSinceSync
	 * @return
	 */
	public void updateOpenCount(int ticksSinceSync) {
		int x = getBlockPos().getX();
		int y = getBlockPos().getY();
		int z = getBlockPos().getZ();
		
//		Treasure.LOGGER.debug("ticking @ {}, openCount -> {} ...", ticksSinceSync, this.openCount);
		if (WorldInfo.isServerSide(this.getLevel()) && this.openCount != 0
				&& (this.ticksSinceSync + x + y + z) % 200 == 0) {
			this.openCount = 0;
			float radius = 5.0F;

			for(PlayerEntity player : getLevel().getEntitiesOfClass(PlayerEntity.class, new AxisAlignedBB((double)((float)x - radius), (double)((float)y - radius), (double)((float)z - radius), 
					(double)((float)(x + 1) + radius), (double)((float)(y + 1) + radius), (double)((float)(z + 1) + radius)))) {
				if (player.containerMenu instanceof ITreasureContainer) {
					IInventory inventory = ((ITreasureContainer)player.containerMenu).getContents();
					if (inventory == this) {
						++this.openCount;
					}
				}
			}
		}
	}	
	
	/**
	 * 
	 */
	@Override
	public void updateEntityState() {
		this.prevLidAngle = this.lidAngle;
		if (this.openCount > 0 && this.lidAngle == 0.0F) {
			this.playSound(SoundEvents.CHEST_OPEN);
		}

		if (this.openCount == 0 && this.lidAngle > 0.0F || this.openCount > 0 && this.lidAngle < 1.0F) {
			float f2 = this.lidAngle;

			if (this.openCount > 0) {
				this.lidAngle += 0.1F;
			} else {
				this.lidAngle -= 0.1F;
			}

			if (this.lidAngle > 1.0F) {
				this.lidAngle = 1.0F;
			}

			float f3 = 0.5F;
			if (this.lidAngle < 0.5F && f2 >= 0.5F) {
				this.playSound(SoundEvents.CHEST_CLOSE);
			}

			if (this.lidAngle < 0.0F) {
				this.lidAngle = 0.0F;
			}
		}
	}
	
	protected void playSound(SoundEvent soundIn) {
		double d0 = (double)getBlockPos().getX() + 0.5D;
		double d1 = (double)getBlockPos().getY() + 0.5D;
		double d2 = (double)getBlockPos().getZ() + 0.5D;
		level.playSound((PlayerEntity)null, d0, d1, d2, soundIn, SoundCategory.BLOCKS, 0.5F, level.random.nextFloat() * 0.1F + 0.9F);
	}

	/**
	 * 
	 */
	@Override
	public CompoundNBT save(CompoundNBT parentNBT) {
		try {
			parentNBT = super.save(parentNBT);

			// TODO LockSlots don't have a Heading ???
			// write lock states
			writeLockStatesToNBT(parentNBT);

			// write inventory
			writeInventoryToNBT(parentNBT);

			// write custom name
			writePropertiesToNBT(parentNBT);
		} catch (Exception e) {
			Treasure.LOGGER.error("Error writing to NBT:", e);
		}
		return parentNBT;
	}

	/**
	 * 
	 * @param parentNBT
	 * @return
	 */
	public CompoundNBT writeLockStatesToNBT(CompoundNBT parentNBT) {
		try {
			// write lock states
			if (getLockStates() != null && !getLockStates().isEmpty()) {
				ListNBT list = new ListNBT();
				// write custom tile entity properties
				for (LockState state : getLockStates()) {
					//					Treasure.LOGGER.info("Writing lock state:" + state);
					CompoundNBT stateNBT = new CompoundNBT();
					state.writeToNBT(stateNBT);
					list.add(stateNBT);
				}
				parentNBT.put("lockStates", list);
			}
		} catch (Exception e) {
			Treasure.LOGGER.error("Error writing LockStates to NBT:", e);
		}
		return parentNBT;
	}

	/**
	 * Write custom properties to NBT
	 * 
	 * @param parentNBT
	 * @return
	 */
	public CompoundNBT writePropertiesToNBT(CompoundNBT parentNBT) {
		try {
			// write custom name
			if (this.hasCustomName()) {
				parentNBT.putString("CustomName", ITextComponent.Serializer.toJson(this.customName));
			}
			// write facing
			parentNBT.putInt("facing", getFacing().get3DDataValue());
			parentNBT.putBoolean("sealed", isSealed());
			if (getLootTable() != null) {
				parentNBT.putString("lootTable", getLootTable().toString());
			}
			if (getGenerationContext() != null) {
				CompoundNBT contextTag = new CompoundNBT();
				contextTag.putString("lootRarity", getGenerationContext().getLootRarity().getValue());
				contextTag.putString("chestGenType", getGenerationContext().getChestGeneratorType().name());
				parentNBT.put("genContext", contextTag);
			}
		} catch (Exception e) {
			Treasure.LOGGER.error("Error writing Properties to NBT:", e);
		}
		return parentNBT;
	}

	/**
	 * Writes the inventory to NBT
	 * 
	 * @param parentNBT
	 * @return
	 */
	public CompoundNBT writeInventoryToNBT(CompoundNBT parentNBT) {
		try {
			// write inventory
			ItemStackHelper.saveAllItems(parentNBT, this.getItems());
		} catch (Exception e) {
			Treasure.LOGGER.error("Error writing Inventory to NBT:", e);
		}
		return parentNBT;
	}

	/**
	 * 
	 * @param parentNBT
	 */
	public void readInventoryFromNBT(CompoundNBT parentNBT) {
		try {
			// read the inventory
			ItemStackHelper.loadAllItems(parentNBT, this.getItems());
		} catch (Exception e) {
			Treasure.LOGGER.error("Error reading Properties from NBT:", e);
		}
	}

	/**
	 * 
	 * @param parentNBT
	 */
	public void readLockStatesFromNBT(CompoundNBT parentNBT) {
		try {
			// read the lockstates
			if (parentNBT.contains("lockStates")) {
				//				Treasure.LOGGER.info("Has lockStates");
				if (this.getLockStates() != null) {
					//					Treasure.LOGGER.info("size of internal lockstates:" + this.getLockStates().size());
				} else {
					this.setLockStates(new LinkedList<LockState>());
					//					Treasure.LOGGER.info("created lockstates:" + this.getLockStates().size());
				}

				List<LockState> states = new LinkedList<LockState>();
				ListNBT list = parentNBT.getList("lockStates", Constants.NBT.TAG_COMPOUND);
				for (int i = 0; i < list.size(); i++) {
					CompoundNBT c = list.getCompound(i);
					LockState lockState = LockState.readFromNBT(c);
					states.add(lockState.getSlot().getIndex(), lockState);
					//					Treasure.LOGGER.info("Read NBT lockstate:" + lockState);
				}
				// update the tile entity
				setLockStates(states);
			}
		} catch (Exception e) {
			Treasure.LOGGER.error("Error reading Lock States from NBT:", e);
		}
	}

	/**
	 * 
	 * @param nbt
	 */
	public void readPropertiesFromNBT(CompoundNBT nbt) {
		try {
			// read the custom name
			if (nbt.contains("CustomName", 8)) {
				this.customName = ITextComponent.Serializer.fromJson(nbt.getString("CustomName"));
			}
			// read the facing
			if (nbt.contains("facing")) {
				this.setFacing(nbt.getInt("facing"));
			}
			if (nbt.contains("sealed")) {
				this.setSealed(nbt.getBoolean("sealed"));
			}
			if (nbt.contains("lootTable")) {
				if (!nbt.getString("lootTable").isEmpty()) {
					this.setLootTable(new ResourceLocation(nbt.getString("lootTable")));
				}
			}
			if (nbt.contains("genContext")) {
				CompoundNBT contextTag = nbt.getCompound("genContext");
				Rarity rarity = null;
				ChestGeneratorType genType = null;
				if (contextTag.contains("lootRarity")) {
					rarity = Rarity.getByValue(contextTag.getString("lootRarity"));
				}
				if (contextTag.contains("chestGenType")) {
					genType = ChestGeneratorType.valueOf(contextTag.getString("chestGenType"));
				}
				AbstractTreasureChestTileEntity.GenerationContext genContext = this.new GenerationContext(rarity, genType);
				this.setGenerationContext(genContext);
			}	
		} catch (Exception e) {
			Treasure.LOGGER.error("Error reading Properties from NBT:", e);
		}
	}

	/**
	 * 
	 */
	@Override
	public void load(BlockState state, CompoundNBT parentNBT) {
		super.load(state, parentNBT);

		try {
			readLockStatesFromNBT(parentNBT);
			readInventoryFromNBT(parentNBT);
			readPropertiesFromNBT(parentNBT);
			//			Treasure.LOGGER.info("completed read");
		} catch (Exception e) {
			Treasure.LOGGER.error("Error reading to NBT:", e);
		}
	}

	/*
	 * This method does NOT call the super's readFromNBT()
	 */
	public void readFromItemStackNBT(CompoundNBT nbt) {
		try {
			readLockStatesFromNBT(nbt);
			readInventoryFromNBT(nbt);
			readPropertiesFromNBT(nbt);
		} catch (Exception e) {
			Treasure.LOGGER.error("Error reading to NBT:", e);
		}
	}

	@Override
	@Nullable
	public SUpdateTileEntityPacket getUpdatePacket() {
		//		Treasure.LOGGER.info("getUpdatePacket is writing packet");
		// TODO write data into the nbttag
		return new SUpdateTileEntityPacket(this.worldPosition, /*3*/ -1, this.getUpdateTag());
	}

	@Override
	public CompoundNBT getUpdateTag() {
		//		Treasure.LOGGER.info("getUpdateTag is writing data");
		return this.save(new CompoundNBT());
	}

	@Override 
	public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
		//		Treasure.LOGGER.info("onDataPacket is reading data");
		//		super.onDataPacket(net, pkt);
		//		handleUpdateTag(pkt.getNbtCompound());
		load(null, pkt.getTag());
	}

	/**
	 * This controls whether the tile entity gets replaced whenever the block state
	 * is changed. Normally only want this when block actually is replaced. NOTE
	 * this method is very important!
	 */
	// NOTE logic moved to Block.onReplaced. see BlockFurnace.onReplaced for an example.
	//	@Override
	//	public boolean shouldRefresh(World world, BlockPos pos, BlockState oldState, BlockState newState) {
	//		//		Treasure.LOGGER.debug("ShouldRefresh:" + (oldState.getBlock() != newState.getBlock()));
	//		return oldState.getBlock() != newState.getBlock();
	//
	//	}

	/**
	 * Sync client and server states
	 */
	public void sendUpdates() {
		// TODO might be same as signalOpenCount()
		BlockState blockState = level.getBlockState(getBlockPos());
//		level.markBlockRangeForRenderUpdate(getBlockPos(), blockState, blockState);
		level.sendBlockUpdated(getBlockPos(), blockState, blockState, 3);
		//		world.scheduleBlockUpdate(pos, this.getType(), 0, 0); ??? replacement
		setChanged();
	}

	////////// Custom property modifiers //////////////////////
	/**
	 * @return the lockStates
	 */
	public List<LockState> getLockStates() {
		return lockStates;
	}

	/**
	 * @param lockStates the lockStates to set
	 */
	public void setLockStates(List<LockState> lockStates) {
		this.lockStates = lockStates;
	}

	/**
	 * 
	 * @return
	 */
	public boolean hasLocks() {
		// TODO TEMP do this for now. should have another property numActiveLocks so
		// that the renderer doesn't keep calling this
		if (getLockStates() == null || getLockStates().isEmpty())
			return false;
		for (LockState state : getLockStates()) {
			if (state.getLock() != null)
				return true;
		}
		return false;
	}

	/**
	 * Creates the server container. Wrapped by vanilla createMenu().
	 * @param windowID
	 * @param inventory
	 * @param player
	 * @return
	 */
	abstract public Container createServerContainer(int windowID, PlayerInventory inventory, PlayerEntity player);

	protected ITextComponent getDefaultName() {
		return new TranslationTextComponent("container.chest");
	}

	@Override
	public ITextComponent getName() {
		return this.hasCustomName() ? this.getCustomName() : this.getDefaultName();
	}		

	@Override
	public ITextComponent getDisplayName() {
		return this.getName();
	}

	/**
	 * The name is misleading; createMenu has nothing to do with creating a Screen, it is used to create the Container on the server only
	 * @param windowID
	 * @param playerInventory
	 * @param playerEntity
	 * @return
	 */
	@Nullable
	@Override
	public Container createMenu(int windowID, PlayerInventory playerInventory, PlayerEntity playerEntity) {
		// TODO it would seem that this is the place where fillWithLoot() should be called
		// see net.minecraft.tileentity.LockableLootTileEntity.createMenu()

		Treasure.LOGGER.debug("is chest sealed -> {}", this.isSealed());
		if (this.isSealed()) {
			this.setSealed(false);
			Treasure.LOGGER.debug("chest gen type -> {}", this.getGenerationContext().getChestGeneratorType()); 
			// construct the chest generator used to create the tile entity
			IChestGenerator chestGenerator = this.getGenerationContext().getChestGeneratorType().getChestGenerator();
			Treasure.LOGGER.debug("chest gen  -> {}", this.getGenerationContext().getChestGeneratorType().getChestGenerator().getClass().getSimpleName());

			// fill the chest with loot
			chestGenerator.fillChest(getLevel(), new Random(), this, this.getGenerationContext().getLootRarity(), playerEntity);			
		}

		return createServerContainer(windowID, playerInventory, playerEntity);
	}

	///////////// IInventory Methods ///////////////////////

	/**
	 * 
	 */
	@Override
	public int getContainerSize() {
		if (!hasLocks()) {
			return getNumberOfSlots();
		}
		return 0;
	}

	/**
	 * 
	 */
	@Override
	public boolean isEmpty() {
		for (ItemStack itemstack : this.items) {
			if (!itemstack.isEmpty()) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 
	 */
	@Override
	public ItemStack getItem(int index) {
		if (!hasLocks()) {
			return getItems().get(index);
		}
		return ItemStack.EMPTY;
	}

	/**
	 * 
	 */
	@Override
	public ItemStack removeItem(int index, int count) {
		ItemStack itemStack = ItemStack.EMPTY;
		if (!hasLocks()) {
			itemStack = ItemStackHelper.removeItem(this.getItems(), index, count);
			if (!itemStack.isEmpty()) {
				this.setChanged();
			}
		}
		return itemStack;
	}

	/**
	 * 
	 */
	@Override
	public ItemStack removeItemNoUpdate(int index) {
		if (hasLocks()) {
			return ItemStack.EMPTY;
		}
		return ItemStackHelper.removeItem(this.getItems(), index, 1);
	}

	/**
	 * 
	 */
	@Override
	public void setItem(int index, ItemStack stack) {
		if (!hasLocks()) {
			this.getItems().set(index, stack);
			if (stack.getCount() > this.getMaxStackSize()) {
				stack.setCount(this.getMaxStackSize());
			}
			this.setChanged();
		}
	}

	/**
	 * Returns the maximum stack size for a inventory slot. Seems to always be 64,
	 * possibly will be extended.
	 */
	@Override
	public int getMaxStackSize() {
		return 64;
	}

	/**
	 * 
	 */
	@Override
	public boolean stillValid(PlayerEntity player) {
		if (getLevel().getBlockEntity(this.getBlockPos()) != this) {
			return false;
		} else {
			boolean isUsable = player.distanceToSqr((double) this.getBlockPos().getX() + 0.5D, (double) this.getBlockPos().getY() + 0.5D,
					(double) this.getBlockPos().getZ() + 0.5D) <= 64.0D;
			return isUsable;
		}
	}

	/**
	 * See {@link Block#eventReceived} for more information. This must return true serverside before it is called
	 * clientside.
	 */
	@Override
	public boolean triggerEvent(int id, int count) {
		if (id == 1) {
			this.openCount = count;
			return true;
		} else {
			return super.triggerEvent(id, count);
		}
	}

	/**
	 * 
	 */
	@Override
	public void startOpen(PlayerEntity player) {
		Treasure.LOGGER.info("opening inventory -> {}", player.getName());

		if (hasLocks()) {
			Treasure.LOGGER.info("has locks - don't increment num players");
			return;
		}
		if (!player.isSpectator()) {
			if (this.openCount < 0) {
				this.openCount = 0;
			}
			++this.openCount;
			onOpenOrClose();
		}
	}

	/**
	 * 
	 */
	@Override
	public void stopOpen(PlayerEntity player) {
		if (!player.isSpectator()) {
			--this.openCount;
			onOpenOrClose();
		}
	}

	protected void onOpenOrClose() {
		Block block = this.getBlockState().getBlock();
		Treasure.LOGGER.debug("block for tile entity -> {}, openCount -> {}", block.getClass().getSimpleName(), this.openCount);
		if (block instanceof AbstractChestBlock) {
			getLevel().blockEvent(getBlockPos(), block, 1, this.openCount);
			getLevel().updateNeighborsAt(getBlockPos(), block);
		}

	}

	/**
	 * Returns true if automation is allowed to insert the given stack (ignoring
	 * stack size) into the given slot. For guis use Slot.isItemValid
	 */
//	@Override
//	public boolean isItemValidForSlot(int index, ItemStack stack) {
//		return true;
//	}

	@Override
	public void clearContent() {
		this.getItems().clear();
		this.setChanged();
	}

	//////////// End of IInventory Methods ///////////////

	/**
	 * @return the customName
	 */
	@Override
	public ITextComponent getCustomName() {
		return customName;
	}

	/**
	 * @param customName the customName to set
	 */
	public void setCustomName(ITextComponent customName) {
		this.customName = customName;
	}

	/**
	 * @return the numberOfSlots
	 */
	@Override
	public int getNumberOfSlots() {
		return ChestSlotCount.STANDARD.getSize();
	}

	/**
	 * @param numberOfSlots the numberOfSlots to set
	 */
	@Override
	public void setNumberOfSlots(int numberOfSlots) {
		this.numberOfSlots = numberOfSlots;
	}

	/**
	 * @param numberOfSlots the numberOfSlots to set
	 */
	//	public void setNumberOfSlots(int numberOfSlots) {
	//		this.numberOfSlots = numberOfSlots;
	//	}

	/**
	 * @return the items
	 */
	public NonNullList<ItemStack> getItems() {
		return items;
	}

	/**
	 * @param items the items to set
	 */
	public void setItems(NonNullList<ItemStack> chestContents) {
		this.items = chestContents;
	}

	public Direction getFacing() {
		//	public int getFacing() {
		return facing;
	}

	public void setFacing(Direction facing) {
		this.facing = facing;
	}

	public void setFacing(int facingIndex) {
		//		this.facing = facing;
		this.facing = Direction.from3DDataValue(facingIndex);
	}

//	@Override
//	public float getOpenNess(float partialTicks) {
//		return MathHelper.lerp(partialTicks, this.prevLidAngle, this.lidAngle);
//	}

	@Override
	public boolean isSealed() {
		return sealed;
	}

	@Override
	public void setSealed(boolean sealed) {
		this.sealed = sealed;
	}

	@Override
	public GenerationContext getGenerationContext() {
		return generationContext;
	}

	@Override
	public void setGenerationContext(GenerationContext context) {
		generationContext = context;
	}

	public ResourceLocation getLootTable() {
		return lootTable;
	}

	public void setLootTable(ResourceLocation lootTable) {
		this.lootTable = lootTable;
	}
}
