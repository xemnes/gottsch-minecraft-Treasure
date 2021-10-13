/**
 * 
 */
package com.someguyssoftware.treasure2.item;

import artifacts.Artifacts;
import baubles.api.BaubleType;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.someguyssoftware.gottschcore.armor.ModArmorBuilder;
import com.someguyssoftware.gottschcore.item.ModItem;
import com.someguyssoftware.gottschcore.item.ModSwordBuilder;
import com.someguyssoftware.treasure2.Treasure;
import com.someguyssoftware.treasure2.capability.CharmableCapabilityProvider;
import com.someguyssoftware.treasure2.capability.CharmCapabilityProvider;
import com.someguyssoftware.treasure2.capability.ICharmCapability;
import com.someguyssoftware.treasure2.capability.ICharmableCapability;
import com.someguyssoftware.treasure2.config.TreasureConfig;
import com.someguyssoftware.treasure2.enums.*;
import com.someguyssoftware.treasure2.item.charm.TreasureCharms;
import com.someguyssoftware.treasure2.world.gen.structure.ITemplateGenerator;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialTransparent;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.registries.IForgeRegistry;

/**
 * @author Mark Gottschling onDec 22, 2017
 *
 */
public class TreasureItems {
	// tab
	public static Item TREASURE_TAB;
	// coins
	public static Item GOLD_COIN;
	public static Item SILVER_COIN;

    // charmed items
	public static Item CHARMED_SILVER_COIN;
	public static CharmedCoinItem CHARMED_GOLD_COIN;
    public static CharmedGemItem CHARMED_RUBY;
    public static CharmedGemItem CHARMED_SAPPHIRE;
	public static CharmedGemItem MINERS_FRIEND;
	public static CharmedCoinItem FOOLS_COIN;
	public static CharmedCoinItem MEDICS_TOKEN;
	public static CharmedCoinItem SALANDAARS_WARD;
	public static CharmedCoinItem DWARVEN_TALISMAN;
	public static CharmedCoinItem ADEPHAGIAS_BOUNTY;
	public static CharmedCoinItem MIRTHAS_TORCH;

	// adornments
    public static Item SILVER_RING;
    public static Item SILVER_SIGNET_RING;    
    public static Item AMETHYST_SILVER_RING;
    public static Item CASTLE_RING;
    
    public static Item GOLD_RING;
    public static Item ONYX_GOLD_RING;    
    public static Item EMERALD_GOLD_RING;
    public static Item RUBY_GOLD_RING;
    public static Item DIAMOND_GOLD_RING;
    public static Item SAPPHIRE_GOLD_RING;
    
    public static Item SILVER_NECKLACE;
    public static Item SAPPHIRE_SILVER_AMULET;
    
    public static Item GOLD_NECKLACE;
    public static Item AMETHYST_GOLD_AMULET;
    public static Item ONYX_GOLD_AMULET;
    public static Item RUBY_GOLD_AMULET;    
    public static Item SAPPHIRE_GOLD_AMULET;
    
    public static Item GOLD_BRACELET;
    public static Item POCKET_WATCH;
    
    // special adornments
    public static Item ANGELS_RING;
    public static Item BRACELET_OF_WONDER;
    public static Item RING_OF_FORTITUDE;

	// pearls
	public static Item WHITE_PEARL;
	public static Item BLACK_PEARL;
    // gems
    public static Item ONYX;

	public static CharmedGemItem ANGEL_BLESSED;

	// locks
	public static LockItem WOOD_LOCK;
	public static LockItem STONE_LOCK;
	public static LockItem EMBER_LOCK;
	public static LockItem LEAF_LOCK;
	public static LockItem IRON_LOCK;
	public static LockItem GOLD_LOCK;
	public static LockItem DIAMOND_LOCK;
	public static LockItem EMERALD_LOCK;
	public static LockItem AMBER_LOCK;
	public static LockItem MALACHITE_LOCK;
	public static LockItem RUBY_LOCK;
	public static LockItem SAPPHIRE_LOCK;
	public static LockItem PERIDOT_LOCK;
	public static LockItem AMETHYST_LOCK;
	public static LockItem TOPAZ_LOCK;
	public static LockItem TANZANITE_LOCK;
	public static LockItem ONYX_LOCK;
	public static LockItem SPIDER_LOCK;
	public static LockItem WITHER_LOCK;

	// keys
	public static KeyItem WOOD_KEY;
	public static KeyItem STONE_KEY;
	public static KeyItem EMBER_KEY;
	public static KeyItem LEAF_KEY;
	public static KeyItem LIGHTNING_KEY;
	public static KeyItem IRON_KEY;
	public static KeyItem GOLD_KEY;
	public static KeyItem METALLURGISTS_KEY;
	public static KeyItem DIAMOND_KEY;
	public static KeyItem EMERALD_KEY;
	public static KeyItem AMBER_KEY;
	public static KeyItem MALACHITE_KEY;
	public static KeyItem RUBY_KEY;
	public static KeyItem SAPPHIRE_KEY;
	public static KeyItem PERIDOT_KEY;
	public static KeyItem AMETHYST_KEY;
	public static KeyItem TOPAZ_KEY;
	public static KeyItem ONYX_KEY;
	public static KeyItem TANZANITE_KEY;
	public static KeyItem JEWELLED_KEY;

	public static KeyItem WITHER_KEY;

	public static KeyItem SKELETON_KEY;
	public static KeyItem SPIDER_KEY;

	public static KeyItem PILFERERS_LOCK_PICK;
	public static KeyItem THIEFS_LOCK_PICK;

	public static KeyRingItem KEY_RING;
	public static PouchItem POUCH;
	public static PouchItem LUCKY_POUCH;
	public static PouchItem APPRENTICES_POUCH = null;
	public static PouchItem MASTERS_POUCH = null;

	// wither items
	public static Item WITHER_STICK_ITEM;
	public static Item WITHER_ROOT_ITEM;

	// swords
	public static Item SKULL_SWORD;

	// armor
	public static Item EYE_PATCH;

	// potions
	public static PotionType EXTRA_STRONG_HEALING;
	public static PotionType EXTRA_STRONG_STRENGTH;
	public static PotionType EXTRA_STRONG_LEAPING;
	public static PotionType EXTRA_STRONG_SWIFTNESS;
	public static PotionType EXTRA_STRONG_REGENERATION;
	public static PotionType EXTRA_STRONG_POISON;

	// paintings
	public static Item PAINTING_BLOCKS_BRICKS;
	public static Item PAINTING_BLOCKS_COBBLESTONE;
	public static Item PAINTING_BLOCKS_DIRT;
	public static Item PAINTING_BLOCKS_LAVA;
	public static Item PAINTING_BLOCKS_SAND;
	public static Item PAINTING_BLOCKS_WATER;
	public static Item PAINTING_BLOCKS_WOOD;

	// other
	public static Item SPANISH_MOSS;
	public static Item TREASURE_TOOL;
	public static Item SKELETON;

	/*
	 * Materials
	 */
	// SKULL //
	public static final ToolMaterial SKULL_TOOL_MATERIAL = EnumHelper.addToolMaterial("SKULL", 2, 1800, 9.0F, 4.0F, 25);
	// FOG
	public static final Material FOG = new MaterialTransparent(MapColor.AIR);

	// chest holder
	public static Multimap<Rarity, LockItem> locks;

	static {
		// TAB
		TREASURE_TAB = new ModItem().setItemName(Treasure.MODID, TreasureConfig.TREASURE_TAB_ID);

		// COINS
		GOLD_COIN = new CoinItem(Treasure.MODID, TreasureConfig.GOLD_COIN_ID);
		SILVER_COIN = new CoinItem(Treasure.MODID, TreasureConfig.SILVER_COIN_ID, Coins.SILVER);

		// CHARMED COINS
		CHARMED_SILVER_COIN = new CharmedCoinItem(Treasure.MODID, TreasureConfig.CHARMED_SILVER_COIN_ID, Coins.SILVER);
		CHARMED_GOLD_COIN = new CharmedCoinItem(Treasure.MODID, TreasureConfig.CHARMED_GOLD_COIN_ID, Coins.GOLD);
        CHARMED_RUBY = new CharmedGemItem(Treasure.MODID, TreasureConfig.CHARMED_RUBY_ID, Gems.CHARMED_RUBY);
        CHARMED_SAPPHIRE = new CharmedGemItem(Treasure.MODID, TreasureConfig.CHARMED_SAPPHIRE_ID, Gems.CHARMED_SAPPHIRE);

		ANGEL_BLESSED = new CharmedGemItem(Treasure.MODID, TreasureConfig.ANGEL_BLESSED_ID, Gems.ANGEL_BLESSED) {
			public ICapabilityProvider initCapabilities(ItemStack stack, NBTTagCompound nbt) {
				CharmCapabilityProvider provider =  new CharmCapabilityProvider();
				ICharmCapability cap = provider.getCapability(CharmCapabilityProvider.CHARM_CAPABILITY, null);
				cap.getCharmInstances().add(TreasureCharms.GRAND_HEALING.createInstance());
				cap.getCharmInstances().add(TreasureCharms.POWERFUL_SHIELDING.createInstance());
				cap.getCharmInstances().add(TreasureCharms.GORGED_FULLNESS.createInstance());
				return provider;
			}
		};
		// add to creative tab since it is a known special coin (non-dynamic in charms)
		ANGEL_BLESSED.setCreativeTab(Treasure.TREASURE_TAB);

		MINERS_FRIEND = new CharmedGemItem(Treasure.MODID, TreasureConfig.MINERS_FRIEND_ID, Gems.MINERS_FRIEND) {
			public ICapabilityProvider initCapabilities(ItemStack stack, NBTTagCompound nbt) {
				CharmCapabilityProvider provider =  new CharmCapabilityProvider();
				ICharmCapability cap = provider.getCapability(CharmCapabilityProvider.CHARM_CAPABILITY, null);
				cap.getCharmInstances().add(TreasureCharms.GLORIOUS_ILLUMINATION.createInstance());
				cap.getCharmInstances().add(TreasureCharms.GRAND_HARVESTING.createInstance());
				return provider;
			}
		};
		MINERS_FRIEND.setCreativeTab(Treasure.TREASURE_TAB);

		FOOLS_COIN = new CharmedCoinItem(Treasure.MODID, "fools_coin", Coins.SILVER) {
			public ICapabilityProvider initCapabilities(ItemStack stack, NBTTagCompound nbt) {
				CharmCapabilityProvider provider =  new CharmCapabilityProvider();
				ICharmCapability cap = provider.getCapability(CharmCapabilityProvider.CHARM_CAPABILITY, null);
				cap.getCharmInstances().add(TreasureCharms.HEALING.createInstance());
				cap.getCharmInstances().add(TreasureCharms.DECAY.createInstance());
				return provider;
			}
		};
		FOOLS_COIN.setCreativeTab(Treasure.TREASURE_TAB);

		MEDICS_TOKEN = new CharmedCoinItem(Treasure.MODID, "medics_token", Coins.GOLD) {
			public ICapabilityProvider initCapabilities(ItemStack stack, NBTTagCompound nbt) {
				CharmCapabilityProvider provider =  new CharmCapabilityProvider();
				ICharmCapability cap = provider.getCapability(CharmCapabilityProvider.CHARM_CAPABILITY, null);
				cap.getCharmInstances().add(TreasureCharms.SALANDAARS_CONVALESCENCE.createInstance());
				return provider;
			}
		};
		MEDICS_TOKEN.setCreativeTab(Treasure.TREASURE_TAB);

		SALANDAARS_WARD = new CharmedCoinItem(Treasure.MODID, "salandaars_ward", Coins.GOLD) {
			public ICapabilityProvider initCapabilities(ItemStack stack, NBTTagCompound nbt) {
				CharmCapabilityProvider provider =  new CharmCapabilityProvider();
				ICharmCapability cap = provider.getCapability(CharmCapabilityProvider.CHARM_CAPABILITY, null);
				cap.getCharmInstances().add(TreasureCharms.ARMADILLO_SHIELDING.createInstance());
				return provider;
			}
		};
		SALANDAARS_WARD.setCreativeTab(Treasure.TREASURE_TAB);

		DWARVEN_TALISMAN = new CharmedCoinItem(Treasure.MODID, "dwarven_talisman", Coins.GOLD) {
			public ICapabilityProvider initCapabilities(ItemStack stack, NBTTagCompound nbt) {
				CharmCapabilityProvider provider =  new CharmCapabilityProvider();
				ICharmCapability cap = provider.getCapability(CharmCapabilityProvider.CHARM_CAPABILITY, null);
				cap.getCharmInstances().add(TreasureCharms.GLORIOUS_HARVESTING.createInstance());
				return provider;
			}
		};
		DWARVEN_TALISMAN.setCreativeTab(Treasure.TREASURE_TAB);

		ADEPHAGIAS_BOUNTY = new CharmedCoinItem(Treasure.MODID, "adephagias_bounty", Coins.GOLD) {
			public ICapabilityProvider initCapabilities(ItemStack stack, NBTTagCompound nbt) {
				CharmCapabilityProvider provider =  new CharmCapabilityProvider();
				ICharmCapability cap = provider.getCapability(CharmCapabilityProvider.CHARM_CAPABILITY, null);
				cap.getCharmInstances().add(TreasureCharms.BURSTING_FULLNESS.createInstance());
				return provider;
			}
		};
		ADEPHAGIAS_BOUNTY.setCreativeTab(Treasure.TREASURE_TAB);

		MIRTHAS_TORCH = new CharmedCoinItem(Treasure.MODID, "mirthas_torch", Coins.GOLD) {
			public ICapabilityProvider initCapabilities(ItemStack stack, NBTTagCompound nbt) {
				CharmCapabilityProvider provider =  new CharmCapabilityProvider();
				ICharmCapability cap = provider.getCapability(CharmCapabilityProvider.CHARM_CAPABILITY, null);
				cap.getCharmInstances().add(TreasureCharms.GLORIOUS_ILLUMINATION.createInstance());
				return provider;
			}
		};
		MIRTHAS_TORCH.setCreativeTab(Treasure.TREASURE_TAB);

        // ADORNMENTS
        // 1x max slot. 0x slots. level 2
        SILVER_RING = (Item) new Adornment(Treasure.MODID, "silver_ring", AdornmentType.RING, BaubleType.RING, null).setMaxSlots(1).setLevel(3);
        // 2x max slot. 0x slots. level 4
        GOLD_RING = (Item) new Adornment(Treasure.MODID, "gold_ring", AdornmentType.RING, BaubleType.RING, null).setMaxSlots(2).setLevel(4);

        // 2x max slot. 0-1x slots. level 3
        SILVER_SIGNET_RING = (Item) new Adornment(Treasure.MODID, "silver_signet_ring", AdornmentType.RING, BaubleType.RING, null).setMaxSlots(2).setLevel(3);

        // 2x max slot. 1x slots. 1x charm. level 6
        AMETHYST_SILVER_RING = (Item) new Adornment(Treasure.MODID, "amethyst_silver_ring", AdornmentType.RING, BaubleType.RING, null).setMaxSlots(2).setLevel(6);

        // 2x max slot. 1x slots. 1x charm. level 5
        ONYX_GOLD_RING = (Item) new Adornment(Treasure.MODID, "onyx_gold_ring", AdornmentType.RING, BaubleType.RING, null).setMaxSlots(2).setLevel(5);
        // 2x max slot. 1x slots. 1x charm. level 10
        DIAMOND_GOLD_RING = (Item) new Adornment(Treasure.MODID, "diamond_gold_ring", AdornmentType.RING, BaubleType.RING, null).setMaxSlots(2).setLevel(10);
        // 2x max slot. 1x slots. 1x charm. level 11
        EMERALD_GOLD_RING = (Item) new Adornment(Treasure.MODID, "emerald_gold_ring", AdornmentType.RING, BaubleType.RING, null).setMaxSlots(2).setLevel(11);
        // 3x max slot. 1x slot. 2x charm level 12
        RUBY_GOLD_RING = (Item) new Adornment(Treasure.MODID, "ruby_gold_ring", AdornmentType.RING, BaubleType.RING, null).setMaxSlots(3).setLevel(12);
        // 3x max slots. 1x slots. 2x charms. level 13
        SAPPHIRE_GOLD_RING = (Item) new Adornment(Treasure.MODID, "sapphire_gold_ring", AdornmentType.RING, BaubleType.RING, null).setMaxSlots(3).setLevel(13);

        // 2x max slots. 1x slots. 1x charms. level 12
        CASTLE_RING = (Item) new Adornment(Treasure.MODID, "castle_ring", AdornmentType.RING, BaubleType.RING, null).setMaxSlots(2).setLevel(12);

        // 1x max slots. level 3
        SILVER_NECKLACE = (Item) new Adornment(Treasure.MODID, "silver_necklace", AdornmentType.AMULET, BaubleType.AMULET, new ResourceLocation(Treasure.MODID, "textures/models/adornments/amulets/silver_necklace.png")).setMaxSlots(1).setLevel(3);
        // 2x max slots. level 4
        GOLD_NECKLACE = (Item) new Adornment(Treasure.MODID, "gold_necklace", AdornmentType.AMULET, BaubleType.AMULET, new ResourceLocation(Treasure.MODID, "textures/models/adornments/amulets/gold_necklace.png")).setMaxSlots(2).setLevel(4);

        // 2x max slot. 2x slots. 2x charms. level 13
        SAPPHIRE_SILVER_AMULET = (Item) new Adornment(Treasure.MODID, "sapphire_silver_amulet", AdornmentType.AMULET, BaubleType.AMULET, new ResourceLocation(Treasure.MODID, "textures/models/adornments/amulets/sapphire_silver_amulet.png")).setMaxSlots(2).setLevel(13);

        // 3x max slot. 1x slot. 1x charm. level 10
        AMETHYST_GOLD_AMULET = (Item) new Adornment(Treasure.MODID, "amethyst_gold_amulet", AdornmentType.AMULET, BaubleType.AMULET, new ResourceLocation(Treasure.MODID, "textures/models/adornments/amulets/amethyst_gold_amulet.png")).setMaxSlots(3).setLevel(10);
        // 3x max slot. 1x slot. 1x charm. level 11
        ONYX_GOLD_AMULET = (Item) new Adornment(Treasure.MODID, "onyx_gold_amulet", AdornmentType.AMULET, BaubleType.AMULET, new ResourceLocation(Treasure.MODID, "textures/models/adornments/amulets/onyx_gold_amulet.png")).setMaxSlots(3).setLevel(11);
        // 4x max slot. 2x slots. 2x charms. level 12
        RUBY_GOLD_AMULET = (Item) new Adornment(Treasure.MODID, "ruby_gold_amulet", AdornmentType.AMULET, BaubleType.AMULET, new ResourceLocation(Treasure.MODID, "textures/models/adornments/amulets/ruby_gold_amulet.png")).setMaxSlots(4).setLevel(12);
        // 4x max slot. 2x slots. 2x charms. level 13
        SAPPHIRE_GOLD_AMULET = (Item) new Adornment(Treasure.MODID, "sapphire_amulet", AdornmentType.AMULET, BaubleType.AMULET, new ResourceLocation(Treasure.MODID, "textures/models/adornments/amulets/sapphire_amulet.png")).setMaxSlots(4).setLevel(13);

        // 4x max slot. 1x slots. 1x charms. level 10
        GOLD_BRACELET = (Item) new Adornment(Treasure.MODID, "gold_bracelet", AdornmentType.BRACELET, BaubleType.TRINKET, null).setMaxSlots(4).setLevel(10);

        // 3x max slot 1x slots. 1x charms. level 8
        POCKET_WATCH = (Item) new Adornment(Treasure.MODID, "pocket_watch", AdornmentType.POCKET, BaubleType.BELT, null).setMaxSlots(3).setLevel(8);

        // TEMP DEBUGGING
//        RUBY_GOLD_RING = (Item) new Adornment(Treasure.MODID, "ruby_gold_ring", AdornmentType.RING) {
//			public ICapabilityProvider initCapabilities(ItemStack stack, NBTTagCompound nbt) {
//				CharmableCapabilityProvider provider =  new CharmableCapabilityProvider();
//				ICharmCapability cap = provider.getCapability(CharmableCapabilityProvider.CHARM_CAPABILITY, null);
//				cap.getCharmInstances().add(TreasureCharms.ABSOLUTE_SHIELDING_4.createInstance());
//				ICharmableCapability charmableCap = provider.getCapability(CharmableCapabilityProvider.CHARMABLE_CAPABILITY, null);
//				charmableCap.setSlots(0);
//				charmableCap.setCustomName("Ring of X");
//				return provider;
//			}
//        }.setMaxSlots(2).setLevel(5);

        ANGELS_RING = (Item) new Adornment(Treasure.MODID, "angels_ring", AdornmentType.RING, BaubleType.RING, new ResourceLocation(Treasure.MODID, "textures/models/adornments/rings/angels_ring.png")) {
            public ICapabilityProvider initCapabilities(ItemStack stack, NBTTagCompound nbt) {
                CharmableCapabilityProvider charmableProvider =  new CharmableCapabilityProvider();
                ICharmCapability charmCap = charmableProvider.getCapability(CharmCapabilityProvider.CHARM_CAPABILITY, null);
                charmCap.getCharmInstances().add(TreasureCharms.FIRE_IMMUNITY_13.createInstance());
                charmCap.getCharmInstances().add(TreasureCharms.ARMADILLO_SHIELDING.createInstance());
                charmCap.getCharmInstances().add(TreasureCharms.SALANDAARS_CONVALESCENCE.createInstance());

                ICharmableCapability charmableCap = charmableProvider.getCapability(CharmableCapabilityProvider.CHARMABLE_CAPABILITY, null);
                charmableCap.setSlots(1);
				charmableCap.setCustomName("Ring of the Angels");
                return charmableProvider;
            }
        }.setMaxSlots(4).setLevel(10);

        RING_OF_FORTITUDE = (Item) new Adornment(Treasure.MODID, "ring_of_fortitude", AdornmentType.RING, BaubleType.RING, new ResourceLocation(Treasure.MODID, "textures/models/adornments/rings/ring_of_fortitude.png")) {
            public ICapabilityProvider initCapabilities(ItemStack stack, NBTTagCompound nbt) {
                CharmableCapabilityProvider charmableProvider =  new CharmableCapabilityProvider();
                ICharmCapability charmCap = charmableProvider.getCapability(CharmCapabilityProvider.CHARM_CAPABILITY, null);
                charmCap.getCharmInstances().add(TreasureCharms.ARMADILLO_SHIELDING.createInstance());
                charmCap.getCharmInstances().add(TreasureCharms.REFLECTION_5.createInstance());

                ICharmableCapability charmableCap = charmableProvider.getCapability(CharmableCapabilityProvider.CHARMABLE_CAPABILITY, null);
                charmableCap.setSlots(2);
				charmableCap.setCustomName("Ring of Fortitude");
                return charmableProvider;
            }
        }.setMaxSlots(2).setLevel(12);

        BRACELET_OF_WONDER = (Item) new Adornment(Treasure.MODID, "bracelet_of_wonder", AdornmentType.BRACELET, BaubleType.TRINKET, new ResourceLocation(Treasure.MODID, "textures/models/adornments/bracelets/bracelet_of_wonder.png")) {
            public ICapabilityProvider initCapabilities(ItemStack stack, NBTTagCompound nbt) {
                CharmableCapabilityProvider charmableProvider =  new CharmableCapabilityProvider();
                ICharmCapability charmCap = charmableProvider.getCapability(CharmCapabilityProvider.CHARM_CAPABILITY, null);
                charmCap.getCharmInstances().add(TreasureCharms.SHIELDING_13.createInstance());
                charmCap.getCharmInstances().add(TreasureCharms.REFLECTION_10.createInstance());

                ICharmableCapability charmableCap = charmableProvider.getCapability(CharmableCapabilityProvider.CHARMABLE_CAPABILITY, null);
                charmableCap.setSlots(4);
				charmableCap.setCustomName("Bracelet of Wonder");
                return charmableProvider;
            }
        }.setMaxSlots(4).setLevel(10);

		// PEARLS
		WHITE_PEARL = new PearlItem(Treasure.MODID, TreasureConfig.WHITE_PEARL_ID, Pearls.WHITE);
		BLACK_PEARL = new PearlItem(Treasure.MODID, TreasureConfig.BLACK_PEARL_ID, Pearls.BLACK);

		// KEYS
		WOOD_KEY = new KeyItem(Treasure.MODID, TreasureConfig.WOOD_KEY_ID)
				.setCategory(Category.ELEMENTAL)
				.setRarity(Rarity.COMMON)
				.setCraftable(false)
				.setMaxDamage(TreasureConfig.KEYS_LOCKS.woodKeyMaxUses);

		STONE_KEY = new KeyItem(Treasure.MODID, TreasureConfig.STONE_KEY_ID)
				.setCategory(Category.ELEMENTAL)
				.setRarity(Rarity.COMMON)
				.setCraftable(false)
				.setMaxDamage(TreasureConfig.KEYS_LOCKS.stoneKeyMaxUses);

		EMBER_KEY = new EmberKey(Treasure.MODID, TreasureConfig.EMBER_KEY_ID)
				.setCategory(Category.ELEMENTAL)
				.setRarity(Rarity.SCARCE)
				.setCraftable(false)
				.setMaxDamage(TreasureConfig.KEYS_LOCKS.emberKeyMaxUses);

		LEAF_KEY = new KeyItem(Treasure.MODID, TreasureConfig.LEAF_KEY_ID)
				.setCategory(Category.ELEMENTAL)
				.setRarity(Rarity.UNCOMMON)
				.setCraftable(false)
				.setMaxDamage(TreasureConfig.KEYS_LOCKS.leafKeyMaxUses);

		LIGHTNING_KEY = new LightningKey(Treasure.MODID, TreasureConfig.LIGHTNING_KEY_ID)
				.setCategory(Category.ELEMENTAL)
				.setRarity(Rarity.SCARCE)
				.setBreakable(false)
				.setCraftable(false)
				.setMaxDamage(TreasureConfig.KEYS_LOCKS.lightningKeyMaxUses);

		IRON_KEY = new KeyItem(Treasure.MODID, TreasureConfig.IRON_KEY_ID)
				.setCategory(Category.METALS)
				.setRarity(Rarity.UNCOMMON)
				.setCraftable(false)
				.setMaxDamage(TreasureConfig.KEYS_LOCKS.ironKeyMaxUses);

		GOLD_KEY = new KeyItem(Treasure.MODID, TreasureConfig.GOLD_KEY_ID)
				.setCategory(Category.METALS)
				.setRarity(Rarity.SCARCE)
				.setCraftable(false)
				.setMaxDamage(TreasureConfig.KEYS_LOCKS.goldKeyMaxUses);

		DIAMOND_KEY = new KeyItem(Treasure.MODID, TreasureConfig.DIAMOND_KEY_ID)
				.setCategory(Category.GEMS)
				.setRarity(Rarity.RARE)
				.setBreakable(false)
				.setCraftable(false)
				.setMaxDamage(TreasureConfig.KEYS_LOCKS.diamondKeyMaxUses);

		EMERALD_KEY = new KeyItem(Treasure.MODID, TreasureConfig.EMERALD_KEY_ID)
				.setCategory(Category.GEMS)
				.setRarity(Rarity.RARE)
				.setBreakable(false)
				.setCraftable(false)
				.setMaxDamage(TreasureConfig.KEYS_LOCKS.emeraldKeyMaxUses);

		AMBER_KEY = new KeyItem(Treasure.MODID, TreasureConfig.AMBER_KEY_ID)
				.setCategory(Category.GEMS)
				.setRarity(Rarity.RARE)
				.setBreakable(false)
				.setCraftable(true)
				.setMaxDamage(TreasureConfig.KEYS_LOCKS.amberKeyMaxUses);

		MALACHITE_KEY = new KeyItem(Treasure.MODID, TreasureConfig.MALACHITE_KEY_ID)
				.setCategory(Category.GEMS)
				.setRarity(Rarity.RARE)
				.setBreakable(false)
				.setCraftable(true)
				.setMaxDamage(TreasureConfig.KEYS_LOCKS.malachiteKeyMaxUses);

		RUBY_KEY = new KeyItem(Treasure.MODID, TreasureConfig.RUBY_KEY_ID)
				.setCategory(Category.GEMS)
				.setRarity(Rarity.EPIC)
				.setBreakable(false)
				.setCraftable(true)
				.setMaxDamage(TreasureConfig.KEYS_LOCKS.rubyKeyMaxUses);

		SAPPHIRE_KEY = new KeyItem(Treasure.MODID, TreasureConfig.SAPPHIRE_KEY_ID)
				.setCategory(Category.GEMS)
				.setRarity(Rarity.EPIC)
				.setBreakable(false)
				.setCraftable(true)
				.setMaxDamage(TreasureConfig.KEYS_LOCKS.sapphireKeyMaxUses);

		PERIDOT_KEY = new KeyItem(Treasure.MODID, TreasureConfig.PERIDOT_KEY_ID)
				.setCategory(Category.GEMS)
				.setRarity(Rarity.EPIC)
				.setBreakable(false)
				.setCraftable(true)
				.setMaxDamage(TreasureConfig.KEYS_LOCKS.peridotKeyMaxUses);

		AMETHYST_KEY = new KeyItem(Treasure.MODID, TreasureConfig.AMETHYST_KEY_ID)
				.setCategory(Category.GEMS)
				.setRarity(Rarity.EPIC)
				.setBreakable(false)
				.setCraftable(true)
				.setMaxDamage(TreasureConfig.KEYS_LOCKS.amethystKeyMaxUses);

		TOPAZ_KEY = new KeyItem(Treasure.MODID, TreasureConfig.TOPAZ_KEY_ID)
				.setCategory(Category.GEMS)
				.setRarity(Rarity.EPIC)
				.setBreakable(false)
				.setCraftable(true)
				.setMaxDamage(TreasureConfig.KEYS_LOCKS.topazKeyMaxUses);

		TANZANITE_KEY = new KeyItem(Treasure.MODID, TreasureConfig.TANZANITE_KEY_ID)
				.setCategory(Category.GEMS)
				.setRarity(Rarity.EPIC)
				.setBreakable(false)
				.setCraftable(true)
				.setMaxDamage(TreasureConfig.KEYS_LOCKS.tanzaniteKeyMaxUses);

		ONYX_KEY = new KeyItem(Treasure.MODID, TreasureConfig.ONYX_KEY_ID)
				.setCategory(Category.GEMS)
				.setRarity(Rarity.EPIC)
				.setBreakable(false)
				.setCraftable(true)
				.setMaxDamage(TreasureConfig.KEYS_LOCKS.onyxKeyMaxUses);

		JEWELLED_KEY = new JewelledKey(Treasure.MODID, TreasureConfig.JEWELLED_KEY_ID)
				.setCategory(Category.GEMS)
				.setRarity(Rarity.EPIC)
				.setBreakable(false)
				.setCraftable(false)
				.setMaxDamage(TreasureConfig.KEYS_LOCKS.jewelledKeyMaxUses);

		METALLURGISTS_KEY = new MetallurgistsKey(Treasure.MODID, TreasureConfig.METALLURGISTS_KEY_ID)
				.setCategory(Category.METALS)
				.setRarity(Rarity.RARE)
				.setBreakable(false)
				.setCraftable(false)
				.setMaxDamage(TreasureConfig.KEYS_LOCKS.metallurgistsKeyMaxUses);

		SKELETON_KEY = new SkeletonKey(Treasure.MODID, TreasureConfig.SKELETON_KEY_ID)
				.setCategory(Category.UNIQUE)
				.setRarity(Rarity.RARE)
				.setBreakable(false)
				.setCraftable(false)
				.setMaxDamage(TreasureConfig.KEYS_LOCKS.skeletonKeyMaxUses);

		SPIDER_KEY = new KeyItem(Treasure.MODID, TreasureConfig.SPIDER_KEY_ID)
				.setCategory(Category.MOB)
				.setRarity(Rarity.SCARCE)
				.setBreakable(true)
				.setCraftable(true)
				.setMaxDamage(TreasureConfig.KEYS_LOCKS.spiderKeyMaxUses);

		WITHER_KEY = new KeyItem(Treasure.MODID, TreasureConfig.WITHER_KEY_ID)
				.setCategory(Category.WITHER)
				.setRarity(Rarity.RARE)
				.setBreakable(false)
				.setCraftable(true)
				.setMaxDamage(TreasureConfig.KEYS_LOCKS.witherKeyMaxUses);

		PILFERERS_LOCK_PICK = new PilferersLockPick(Treasure.MODID, TreasureConfig.PILFERERS_LOCK_PICK_ID)
				.setCategory(Category.UNIQUE)
				.setRarity(Rarity.COMMON)
				.setBreakable(true)
				.setCraftable(true)
				.setMaxDamage(TreasureConfig.KEYS_LOCKS.pilferersLockPickMaxUses)
				.setSuccessProbability(32);

		THIEFS_LOCK_PICK = new ThiefsLockPick(Treasure.MODID, TreasureConfig.THIEFS_LOCK_PICK_ID)
				.setCategory(Category.UNIQUE)
				.setRarity(Rarity.UNCOMMON)
				.setBreakable(true)
				.setCraftable(true)
				.setMaxDamage(TreasureConfig.KEYS_LOCKS.thiefsLockPickMaxUses)
				.setSuccessProbability(48);

		// KEY SILVER_RING
		KEY_RING = new KeyRingItem(Treasure.MODID, TreasureConfig.KEY_RING_ID);

		// POUCHES
		POUCH = new PouchItem(Treasure.MODID, TreasureConfig.POUCH_ID);
		LUCKY_POUCH = new PouchItem(Treasure.MODID, TreasureConfig.LUCKY_POUCH_ID).setPouchType(PouchType.ARCANE);
		APPRENTICES_POUCH = new PouchItem(Treasure.MODID, TreasureConfig.APPRENTICES_POUCH_ID).setPouchType(PouchType.ARCANE);
		MASTERS_POUCH = new PouchItem(Treasure.MODID, TreasureConfig.MASTERS_POUCH_ID).setPouchType(PouchType.ARCANE);

		// LOCKS
		WOOD_LOCK = new LockItem(Treasure.MODID, TreasureConfig.WOOD_LOCK_ID, new KeyItem[] {WOOD_KEY, LIGHTNING_KEY, PILFERERS_LOCK_PICK, THIEFS_LOCK_PICK, SKELETON_KEY})
				.setCategory(Category.ELEMENTAL)
				.setRarity(Rarity.COMMON);
		STONE_LOCK = new LockItem(Treasure.MODID, TreasureConfig.STONE_LOCK_ID, new KeyItem[] {STONE_KEY, LIGHTNING_KEY, PILFERERS_LOCK_PICK, THIEFS_LOCK_PICK, SKELETON_KEY})
				.setCategory(Category.ELEMENTAL)
				.setRarity(Rarity.COMMON);
		EMBER_LOCK = new EmberLock(Treasure.MODID, TreasureConfig.EMBER_LOCK_ID, new KeyItem[] {EMBER_KEY, LIGHTNING_KEY, SKELETON_KEY})
				.setCategory(Category.ELEMENTAL)
				.setRarity(Rarity.SCARCE);
		LEAF_LOCK = new LockItem(Treasure.MODID, TreasureConfig.LEAF_LOCK_ID, new KeyItem[] {LEAF_KEY, LIGHTNING_KEY, PILFERERS_LOCK_PICK, THIEFS_LOCK_PICK, SKELETON_KEY})
				.setCategory(Category.ELEMENTAL)
				.setRarity(Rarity.UNCOMMON);
		IRON_LOCK = new LockItem(Treasure.MODID, TreasureConfig.IRON_LOCK_ID, new KeyItem[] {IRON_KEY, METALLURGISTS_KEY, PILFERERS_LOCK_PICK, THIEFS_LOCK_PICK, SKELETON_KEY})
				.setCategory(Category.METALS)
				.setRarity(Rarity.UNCOMMON);
		GOLD_LOCK = new LockItem(Treasure.MODID, TreasureConfig.GOLD_LOCK_ID, new KeyItem[] {GOLD_KEY, METALLURGISTS_KEY, THIEFS_LOCK_PICK, SKELETON_KEY})
				.setCategory(Category.METALS)
				.setRarity(Rarity.SCARCE);
		DIAMOND_LOCK = new LockItem(Treasure.MODID, TreasureConfig.DIAMOND_LOCK_ID, new KeyItem[] {DIAMOND_KEY, JEWELLED_KEY, SKELETON_KEY})
				.setCategory(Category.GEMS)
				.setRarity(Rarity.RARE);
		EMERALD_LOCK = new LockItem(Treasure.MODID, TreasureConfig.EMERALD_LOCK_ID, new KeyItem[] {EMERALD_KEY, JEWELLED_KEY, SKELETON_KEY})
				.setCategory(Category.GEMS)
				.setRarity(Rarity.RARE);
		AMBER_LOCK = new LockItem(Treasure.MODID, TreasureConfig.AMBER_LOCK_ID, new KeyItem[] {AMBER_KEY, JEWELLED_KEY, SKELETON_KEY})
				.setCategory(Category.GEMS)
				.setRarity(Rarity.RARE);
		MALACHITE_LOCK = new LockItem(Treasure.MODID, TreasureConfig.MALACHITE_LOCK_ID, new KeyItem[] {MALACHITE_KEY, JEWELLED_KEY, SKELETON_KEY})
				.setCategory(Category.GEMS)
				.setRarity(Rarity.RARE);
		RUBY_LOCK = new LockItem(Treasure.MODID, TreasureConfig.RUBY_LOCK_ID, new KeyItem[] {RUBY_KEY, JEWELLED_KEY})
				.setCategory(Category.GEMS)
				.setRarity(Rarity.EPIC);
		SAPPHIRE_LOCK = new LockItem(Treasure.MODID, TreasureConfig.SAPPHIRE_LOCK_ID, new KeyItem[] {SAPPHIRE_KEY, JEWELLED_KEY})
				.setCategory(Category.GEMS)
				.setRarity(Rarity.EPIC);
		PERIDOT_LOCK = new LockItem(Treasure.MODID, TreasureConfig.PERIDOT_LOCK_ID, new KeyItem[] {PERIDOT_KEY, JEWELLED_KEY})
				.setCategory(Category.GEMS)
				.setRarity(Rarity.EPIC);
		AMETHYST_LOCK = new LockItem(Treasure.MODID, TreasureConfig.AMETHYST_LOCK_ID, new KeyItem[] {AMETHYST_KEY, JEWELLED_KEY})
				.setCategory(Category.GEMS)
				.setRarity(Rarity.EPIC);
		TOPAZ_LOCK = new LockItem(Treasure.MODID, TreasureConfig.TOPAZ_LOCK_ID, new KeyItem[] {TOPAZ_KEY, JEWELLED_KEY})
				.setCategory(Category.GEMS)
				.setRarity(Rarity.EPIC);
		TANZANITE_LOCK = new LockItem(Treasure.MODID, TreasureConfig.TANZANITE_LOCK_ID, new KeyItem[] {TANZANITE_KEY, JEWELLED_KEY})
				.setCategory(Category.GEMS)
				.setRarity(Rarity.EPIC);
		ONYX_LOCK = new LockItem(Treasure.MODID, TreasureConfig.ONYX_LOCK_ID, new KeyItem[] {ONYX_KEY, JEWELLED_KEY})
				.setCategory(Category.GEMS)
				.setRarity(Rarity.EPIC);
		SPIDER_LOCK = new LockItem(Treasure.MODID, TreasureConfig.SPIDER_LOCK_ID, new KeyItem[] {SPIDER_KEY, THIEFS_LOCK_PICK, SKELETON_KEY})
				.setCategory(Category.POTION)
				.setRarity(Rarity.SCARCE);
		WITHER_LOCK = new LockItem(Treasure.MODID, TreasureConfig.WITHER_LOCK_ID, new KeyItem[] {WITHER_KEY})
				.setCategory(Category.WITHER)
				.setRarity(Rarity.SCARCE);


		locks = ArrayListMultimap.create();
		locks.put(WOOD_LOCK.getRarity(), WOOD_LOCK);
		locks.put(STONE_LOCK.getRarity(), STONE_LOCK);
		locks.put(EMBER_LOCK.getRarity(), EMBER_LOCK);
		locks.put(LEAF_LOCK.getRarity(), LEAF_LOCK);
		locks.put(IRON_LOCK.getRarity(), IRON_LOCK);
		locks.put(GOLD_LOCK.getRarity(), GOLD_LOCK);
		locks.put(DIAMOND_LOCK.getRarity(), DIAMOND_LOCK);
		locks.put(EMERALD_LOCK.getRarity(), EMERALD_LOCK);
		locks.put(AMBER_LOCK.getRarity(), AMBER_LOCK);
		locks.put(MALACHITE_LOCK.getRarity(), MALACHITE_LOCK);
		locks.put(RUBY_LOCK.getRarity(), RUBY_LOCK);
		locks.put(SAPPHIRE_LOCK.getRarity(), SAPPHIRE_LOCK);
		locks.put(PERIDOT_LOCK.getRarity(), PERIDOT_LOCK);
		locks.put(AMETHYST_LOCK.getRarity(), AMETHYST_LOCK);
		locks.put(TOPAZ_LOCK.getRarity(), TOPAZ_LOCK);
		locks.put(TANZANITE_LOCK.getRarity(), TANZANITE_LOCK);
		locks.put(ONYX_LOCK.getRarity(), ONYX_LOCK);
		locks.put(SPIDER_LOCK.getRarity(), SPIDER_LOCK);
		// NOTE wither lock is a special and isn't used in the general locks list

		// other
		ModSwordBuilder builder = new ModSwordBuilder();
		SKULL_SWORD = builder
				.withModID(Treasure.MODID)
				.withName(TreasureConfig.SKULL_SWORD_ID)
				.withMaterial(SKULL_TOOL_MATERIAL)
				.withRepairItem(Items.BONE)
				.withCreativeTab(Treasure.TREASURE_TAB)
				.build();

		// NOTE if going to add lots of different armor, tools and swords then use a List<Pair<>> or "props" object. See MetalsItems.java
		ModArmorBuilder armorBuilder = new ModArmorBuilder();
		EYE_PATCH = armorBuilder
				.withModID(Treasure.MODID)
				.withName(TreasureConfig.EYE_PATCH_ID)
				.withMaterial(ItemArmor.ArmorMaterial.LEATHER)
				.withRenderIndex(2)
				.withSlot(EntityEquipmentSlot.HEAD)
				.withTexture("textures/models/armor/eye_patch.png")
				.withRepairItem(Items.LEATHER)
				.withCreativeTab(Treasure.TREASURE_TAB)
				.build();

		// wither items
		WITHER_STICK_ITEM = new WitherStickItem(Treasure.MODID, TreasureConfig.WITHER_STICK_ITEM_ID);
		WITHER_ROOT_ITEM = new WitherRootItem(Treasure.MODID, TreasureConfig.WITHER_ROOT_ITEM_ID);

		// potions
		EXTRA_STRONG_HEALING = new PotionType("healing",
				new PotionEffect[] {new PotionEffect(MobEffects.INSTANT_HEALTH, 1, 2)}).setRegistryName(Treasure.MODID, "extra_strong_healing");
		EXTRA_STRONG_STRENGTH = new PotionType("strength",
				new PotionEffect[] {new PotionEffect(MobEffects.STRENGTH, 1800, 2)}).setRegistryName(Treasure.MODID, "extra_strong_strength");
		EXTRA_STRONG_LEAPING = new PotionType("leaping",
				new PotionEffect[] {new PotionEffect(MobEffects.JUMP_BOOST, 1800, 2)}).setRegistryName(Treasure.MODID, "extra_strong_leaping");
		EXTRA_STRONG_SWIFTNESS = new PotionType("swiftness",
				new PotionEffect[] {new PotionEffect(MobEffects.SPEED, 1800, 2)}).setRegistryName(Treasure.MODID, "extra_strong_swiftness");
		EXTRA_STRONG_REGENERATION = new PotionType("regeneration",
				new PotionEffect[] {new PotionEffect(MobEffects.REGENERATION, 450, 2)}).setRegistryName(Treasure.MODID, "extra_strong_regeneration");
		EXTRA_STRONG_POISON = new PotionType("poison",
				new PotionEffect[] {new PotionEffect(MobEffects.POISON, 432, 2)}).setRegistryName(Treasure.MODID, "extra_strong_poison");

		SPANISH_MOSS = new SpanishMossItem(Treasure.MODID, TreasureConfig.SPANISH_MOSS_ITEM_ID);
		//				.setItemName(Treasure.MODID, TreasureConfig.SPANISH_MOSS_ITEM_ID)
		//				.setCreativeTab(Treasure.TREASURE_TAB);

		TREASURE_TOOL = new TreasureToolItem(Treasure.MODID, TreasureConfig.TREASURE_TOOL_ITEM_ID);

		PAINTING_BLOCKS_DIRT = new PaintingItem(Treasure.MODID, TreasureConfig.PAINTING_BLOCKS_DIRT_ID, Rarity.SCARCE)
				.setPaintingName("Dirt").setCollectionName("Blocks").setCollectionIssue("1")	.setCollectionSize("7").setArtist("o2xygeno");
		PAINTING_BLOCKS_COBBLESTONE = new PaintingItem(Treasure.MODID, TreasureConfig.PAINTING_BLOCKS_COBBLESTONE_ID, Rarity.SCARCE)
				.setPaintingName("Cobblestone").setCollectionName("Blocks").setCollectionIssue("2").setCollectionSize("7").setArtist("o2xygeno");
		PAINTING_BLOCKS_WATER = new PaintingItem(Treasure.MODID, TreasureConfig.PAINTING_BLOCKS_WATER_ID, Rarity.SCARCE)
				.setPaintingName("Water").setCollectionName("Blocks").setCollectionIssue("3")	.setCollectionSize("7").setArtist("o2xygeno");

		PAINTING_BLOCKS_SAND = new PaintingItem(Treasure.MODID, TreasureConfig.PAINTING_BLOCKS_SAND_ID, Rarity.RARE)
				.setPaintingName("Sand").setCollectionName("Blocks").setCollectionIssue("4").setCollectionSize("7").setArtist("o2xygeno");
		PAINTING_BLOCKS_WOOD = new PaintingItem(Treasure.MODID, TreasureConfig.PAINTING_BLOCKS_WOOD_ID, Rarity.RARE)
				.setPaintingName("Wood").setCollectionName("Blocks").setCollectionIssue("5")	.setCollectionSize("7").setArtist("o2xygeno");

		PAINTING_BLOCKS_BRICKS = new PaintingItem(Treasure.MODID, TreasureConfig.PAINTING_BLOCKS_BRICKS_ID, Rarity.EPIC)
				.setPaintingName("Bricks").setCollectionName("Blocks").setCollectionIssue("6").setCollectionSize("7").setArtist("o2xygeno");
		PAINTING_BLOCKS_LAVA = new PaintingItem(Treasure.MODID, TreasureConfig.PAINTING_BLOCKS_LAVA_ID, Rarity.EPIC)
				.setPaintingName("Lava").setCollectionName("Blocks").setCollectionIssue("7").setCollectionSize("7").setArtist("o2xygeno");

        ONYX = new GemItem(Treasure.MODID, TreasureConfig.ONYX_ID, Gems.ONYX);

		SKELETON = new SkeletonItem(Treasure.MODID, TreasureConfig.SKELETON_ID);
	}

	/**
	 * 
	 * @author Mark Gottschling on Nov 9, 2018
	 *
	 */
	@Mod.EventBusSubscriber(modid = Treasure.MODID)
	public static class PotionRegistrationHandler {
		@SubscribeEvent
		public static void registerPotions(final RegistryEvent.Register<PotionType> event) {
			final IForgeRegistry<PotionType> registry = event.getRegistry();
			registry.register(EXTRA_STRONG_HEALING);
			registry.register(EXTRA_STRONG_LEAPING);
			registry.register(EXTRA_STRONG_POISON);
			registry.register(EXTRA_STRONG_REGENERATION);
			registry.register(EXTRA_STRONG_STRENGTH);
			registry.register(EXTRA_STRONG_SWIFTNESS);
		}
	}

	/**
	 * 
	 * @author Mark Gottschling on Jan 12, 2018
	 *
	 */
	@Mod.EventBusSubscriber(modid = Treasure.MODID)
	public static class RegistrationHandler {

		@SubscribeEvent
		public static void registerItems(final RegistryEvent.Register<Item> event) {

			final IForgeRegistry<Item> registry = event.getRegistry();

			final Item[] items = {
					TREASURE_TAB,
					SILVER_COIN,
					GOLD_COIN,
					CHARMED_SILVER_COIN,
					CHARMED_GOLD_COIN,
                    CHARMED_RUBY,
                    CHARMED_SAPPHIRE,
					ANGEL_BLESSED,
					MINERS_FRIEND,
					FOOLS_COIN,
					MEDICS_TOKEN,
					SALANDAARS_WARD,
					DWARVEN_TALISMAN,
					ADEPHAGIAS_BOUNTY,
					MIRTHAS_TORCH,
                    SILVER_RING,
                    SILVER_SIGNET_RING,
                    AMETHYST_SILVER_RING,
                    ONYX_GOLD_RING,
					GOLD_RING,
                    RUBY_GOLD_RING,
                    EMERALD_GOLD_RING,
                    DIAMOND_GOLD_RING,
                    SAPPHIRE_GOLD_RING,
                    CASTLE_RING,
                    ANGELS_RING,
                    RING_OF_FORTITUDE,
                    BRACELET_OF_WONDER,
                    SILVER_NECKLACE,
                    GOLD_NECKLACE,
                    SAPPHIRE_SILVER_AMULET,
                    AMETHYST_GOLD_AMULET,
                    ONYX_GOLD_AMULET,
                    RUBY_GOLD_AMULET,
                    SAPPHIRE_GOLD_AMULET,
                    GOLD_BRACELET,
                    POCKET_WATCH,
					WHITE_PEARL,
					BLACK_PEARL,
					WOOD_LOCK,
					STONE_LOCK,
					EMBER_LOCK,
					LEAF_LOCK,
					IRON_LOCK,
					GOLD_LOCK,
					DIAMOND_LOCK,
					EMERALD_LOCK,
					AMBER_LOCK,
					MALACHITE_LOCK,
					RUBY_LOCK,
					SAPPHIRE_LOCK,
					PERIDOT_LOCK,
					AMETHYST_LOCK,
					TOPAZ_LOCK,
					TANZANITE_LOCK,
					ONYX_LOCK,
					SPIDER_LOCK,
					WITHER_LOCK,
					WOOD_KEY,
					STONE_KEY,
					EMBER_KEY,
					LEAF_KEY,
					LIGHTNING_KEY,
					IRON_KEY,
					GOLD_KEY,
					DIAMOND_KEY,
					EMERALD_KEY,
					AMBER_KEY,
					MALACHITE_KEY,
					RUBY_KEY,
					SAPPHIRE_KEY,
					PERIDOT_KEY,
					AMETHYST_KEY,
					TOPAZ_KEY,
					TANZANITE_KEY,
					ONYX_KEY,
					JEWELLED_KEY,
					METALLURGISTS_KEY,
					SKELETON_KEY,
					SPIDER_KEY,
					WITHER_KEY,
					PILFERERS_LOCK_PICK,
					THIEFS_LOCK_PICK,
					KEY_RING,
					POUCH,
					LUCKY_POUCH,
					APPRENTICES_POUCH,
					MASTERS_POUCH,
					SKULL_SWORD,
					EYE_PATCH,
					WITHER_STICK_ITEM,
					WITHER_ROOT_ITEM,
					SPANISH_MOSS,
					TREASURE_TOOL,
					PAINTING_BLOCKS_BRICKS,
					PAINTING_BLOCKS_COBBLESTONE,
					PAINTING_BLOCKS_DIRT,
					PAINTING_BLOCKS_LAVA,
					PAINTING_BLOCKS_SAND,
					PAINTING_BLOCKS_WATER,
                    PAINTING_BLOCKS_WOOD,
                    ONYX,
					SKELETON
			};
			registry.registerAll(items);

            // register to the ore dictionary
			OreDictionary.registerOre("onyx", TreasureItems.ONYX);

            OreDictionary.registerOre("gemOnyx", TreasureItems.ONYX);

		}
	}
}
