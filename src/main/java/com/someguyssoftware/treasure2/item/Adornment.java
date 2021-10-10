/**
 * 
 */
package com.someguyssoftware.treasure2.item;

import java.util.List;
import java.util.Optional;

import artifacts.client.model.ModelAmulet;
import artifacts.client.model.layer.LayerGloves;
import artifacts.common.item.BaubleAmulet;
import artifacts.common.item.BaubleBase;
import baubles.api.BaubleType;
import baubles.api.BaublesApi;
import baubles.api.IBauble;
import baubles.api.cap.BaublesCapabilities;
import baubles.api.cap.IBaublesItemHandler;
import baubles.api.render.IRenderBauble;
import com.someguyssoftware.gottschcore.item.ModItem;
import com.someguyssoftware.treasure2.Treasure;
import com.someguyssoftware.treasure2.capability.CharmableCapabilityProvider;
import com.someguyssoftware.treasure2.capability.CharmCapabilityProvider;
import com.someguyssoftware.treasure2.capability.ICharmCapability;
import com.someguyssoftware.treasure2.capability.ICharmableCapability;
import com.someguyssoftware.treasure2.enums.AdornmentType;
import com.someguyssoftware.treasure2.eventhandler.PlayerEventHandler;
import com.someguyssoftware.treasure2.item.charm.ICharmable;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.*;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * @author Mark Gottschling on Dec 20, 2020
 *
 */
public class Adornment extends ModItem implements IAdornment, ICharmable, IPouchable, IBauble, IRenderBauble {
	private AdornmentType type;
	public final BaubleType baubleType;
	// TODO add customName to capability

	// TODO this is max slots which exists in Item class
	// TODO need slots in a Capability that is saved to NBT
	// ex Rings can have a max of 2 charms, but Ring of Healing or low-level rings are made with only 1 slot
	/*
	 * default max of slot.
	 * maxSlots are the max number of charms an Adornment can hold.
	 * // capability slots below
	 * once a charm is added, a slot is filled and the count is decremented.
	 * when a charm runs out of uses, it is removed from teh Adornment, but the maxSlots are not incremented.
	 * once all maxSlots are used, the Adornment can not add any new Charms
	 */
	private int maxSlots = 2;

    private int level = 1;

	protected ModelBase modelAmulet = new ModelAmulet();
	protected ResourceLocation textures;

	protected SoundEvent equipSound = SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND;
	protected float equipPitch = 1;

	/**
	 * 
	 * @param modID
	 * @param name
	 * @param type
	 */
	public Adornment(String modID, String name, AdornmentType type, BaubleType baubleType, ResourceLocation textures) {
		super();
		setItemName(modID, name);
		setMaxStackSize(1);
		setCreativeTab(Treasure.TREASURE_TAB);
		setType(type);
		this.baubleType = baubleType;
		this.textures = textures;
	}

	public Adornment setEquipSound(SoundEvent equipSound, float equipPitch) {
		this.equipSound = equipSound;
		this.equipPitch = equipPitch;
		return this;
	}

	public Adornment setEquipSound(SoundEvent equipSound) {
		return setEquipSound(equipSound, 1);
	}

	@Override
	public ICapabilityProvider initCapabilities(ItemStack stack, NBTTagCompound nbt) {
		CharmableCapabilityProvider provider =  new CharmableCapabilityProvider();
		return provider;
	}

	/**
	 * 
	 */
	@Override
    public String getItemStackDisplayName(ItemStack stack) {
    	String name = super.getItemStackDisplayName(stack);
		if (stack.hasCapability(CharmableCapabilityProvider.CHARMABLE_CAPABILITY, null)) {
			ICharmableCapability cap = stack.getCapability(CharmableCapabilityProvider.CHARMABLE_CAPABILITY, null);
			if (cap.getCustomName() != null && !cap.getCustomName().isEmpty()) {
				name = cap.getCustomName();
			}
		}
		return name;
    }
    
	/**
	 * 
	 */
	@Override
	public boolean hasEffect(ItemStack stack) {
		boolean charmed =  false;
		if (stack.hasCapability(CharmableCapabilityProvider.CHARM_CAPABILITY, null)) {
			ICharmCapability cap = stack.getCapability(CharmableCapabilityProvider.CHARM_CAPABILITY, null);
			if (cap.getCharmInstances() != null && cap.getCharmInstances().size() > 0) {
				charmed = true;
			}
		}
		return charmed;
	}

	/**
	 * 
	 */
	@SuppressWarnings("deprecation")
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		super.addInformation(stack, world, tooltip, flag);
		if (isCharmed(stack)) {
			addCharmedInfo(stack, world, tooltip, flag);
		}
		else {
			tooltip.add(TextFormatting.GOLD.toString() + "" + TextFormatting.ITALIC.toString() + I18n.translateToLocal("tooltip.label.charmable"));
			tooltip.add(TextFormatting.GOLD.toString() + "" + TextFormatting.ITALIC.toString() + "Compatible gems unlock slots");
		}
		addSlotsInfo(stack, world, tooltip, flag);
	}

	public AdornmentType getType() {
		return type;
	}

	public void setType(AdornmentType type) {
		this.type = type;
	}

	@Override
	public BaubleType getBaubleType(ItemStack itemStack) {
		return baubleType;
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, @Nonnull EnumHand hand) {
		IBaublesItemHandler baubles = BaublesApi.getBaublesHandler(player);
		for (int i = 0; i < baubles.getSlots(); i++) {
			if (baubles.getStackInSlot(i).isEmpty() && baubles.isItemValidForSlot(i, player.getHeldItem(hand), player)) {
				baubles.setStackInSlot(i, player.getHeldItem(hand).copy());
				if (!player.capabilities.isCreativeMode) {
					player.getHeldItem(hand).setCount(0);
				}
				onEquipped(player.getHeldItem(hand), player);
				return new ActionResult<>(EnumActionResult.SUCCESS, player.getHeldItem(hand));
			}
		}
		return new ActionResult<>(EnumActionResult.FAIL, player.getHeldItem(hand));
	}

	@Override
	public void onEquipped(ItemStack itemstack, EntityLivingBase player) {
		player.playSound(equipSound, .75F, 0.95F * equipPitch);
	}

	@Override
	public int getMaxSlots() {
		return maxSlots;
	}

	public IAdornment setMaxSlots(int maxSlots) {
		this.maxSlots = maxSlots;
		return this;
    }
    
    @Override
    public int getLevel() {
        return level;
    }

    @Override
    public IAdornment setLevel(int level) {
        this.level = level;
        return this;
    }

	@Override
	public void onPlayerBaubleRender(ItemStack stack, EntityPlayer player, IRenderBauble.RenderType renderType, float partialTicks) {

		if (renderType == RenderType.BODY && baubleType == BaubleType.AMULET) {
			GlStateManager.enableLighting();
			GlStateManager.enableRescaleNormal();
			Helper.rotateIfSneaking(player);
			Minecraft.getMinecraft().renderEngine.bindTexture(textures);
			modelAmulet.render(player, 0, 0, player.ticksExisted + partialTicks, 0, 0, 1 / 16F);
		}
	}
}
