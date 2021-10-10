package com.someguyssoftware.treasure2.inventory;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

/**
 * This is a special container for the Key Ring
 * @author Mark Gottschling on Mar 9, 2018
 *
 */
public class KeyRingContainer extends AbstractChestContainer {
	class Point {
		public int x;
		public int  y;
		public Point(int x, int y) { this.x = x; this.y = y;}
	}
	
	/**
	 * 
	 * @param invPlayer
	 * @param inventory
	 */
	public KeyRingContainer(InventoryPlayer invPlayer, IInventory inventory) {
		super(invPlayer, inventory);
		// set the dimensions
		setContainerInventoryXPos(64);
		
		// build the container
		buildContainer(invPlayer, inventory);
	}
	
	/**
	 * KeyRing prevents the held slot (which contains the key ring itself) from being moved.
	 */
	@Override
	public void buildHotbar(InventoryPlayer player) {
		for (int x = 0; x < HOTBAR_SLOT_COUNT; x++) {
			int slotNumber = x;
			if (slotNumber == player.currentItem) {
				addSlotToContainer(new NoSlot(player, slotNumber, getHotbarXPos() + getSlotXSpacing() * x, getHotbarYPos()));
			}
			else {
				addSlotToContainer(new Slot(player, slotNumber, getHotbarXPos() + getSlotXSpacing() * x, getHotbarYPos()));
			}
		}
	}
	
	/**
	 * 
	 */
	@Override
	public void buildContainerInventory() {

		//row 1
		addSlotToContainer(new KeyRingSlot(inventory, 0, 44, 18));
		addSlotToContainer(new KeyRingSlot(inventory, 1, 62, 18));
		addSlotToContainer(new KeyRingSlot(inventory, 2, 80, 18));
		addSlotToContainer(new KeyRingSlot(inventory, 3, 98, 18));
		addSlotToContainer(new KeyRingSlot(inventory, 4, 116, 18));
		//row2
		addSlotToContainer(new KeyRingSlot(inventory, 5, 26, 36));
		addSlotToContainer(new KeyRingSlot(inventory, 6, 44, 36));
		addSlotToContainer(new KeyRingSlot(inventory, 7, 62, 36));
		addSlotToContainer(new KeyRingSlot(inventory, 8, 80, 36));
		addSlotToContainer(new KeyRingSlot(inventory, 9, 98, 36));
		addSlotToContainer(new KeyRingSlot(inventory, 10, 116, 36));
		addSlotToContainer(new KeyRingSlot(inventory, 11, 134, 36));
		//row3
		addSlotToContainer(new KeyRingSlot(inventory, 12, 26, 54));
		addSlotToContainer(new KeyRingSlot(inventory, 13, 44, 54));
		addSlotToContainer(new KeyRingSlot(inventory, 14, 116, 54));
		addSlotToContainer(new KeyRingSlot(inventory, 15, 134, 54));
		//row4
		addSlotToContainer(new KeyRingSlot(inventory, 16, 26, 72));
		addSlotToContainer(new KeyRingSlot(inventory, 17, 44, 72));
		addSlotToContainer(new KeyRingSlot(inventory, 18, 116, 72));
		addSlotToContainer(new KeyRingSlot(inventory, 19, 134, 72));
		//row5
		addSlotToContainer(new KeyRingSlot(inventory, 20, 44, 90));
		addSlotToContainer(new KeyRingSlot(inventory, 21, 62, 90));
		addSlotToContainer(new KeyRingSlot(inventory, 22, 80, 90));
		addSlotToContainer(new KeyRingSlot(inventory, 23, 98, 90));
		addSlotToContainer(new KeyRingSlot(inventory, 24, 116, 90));

	}
	
	@Override
	public int getHotbarYPos() {
		return 175;
	}
	
	@Override
	public int getPlayerInventoryYPos() {
		return 117;
	}
	
	@Override
	public int getContainerInventorySlotCount() {
		return 25;
	}
}
