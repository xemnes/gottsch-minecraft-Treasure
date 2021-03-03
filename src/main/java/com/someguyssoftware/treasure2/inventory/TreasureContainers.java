/**
 * 
 */
package com.someguyssoftware.treasure2.inventory;

import com.someguyssoftware.treasure2.Treasure;

import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

/**
 * @author Mark Gottschling on Aug 18, 2020
 *
 */
public class TreasureContainers {
	public static ContainerType<StandardChestContainer> STANDARD_CHEST_CONTAINER_TYPE;
	public static ContainerType<StrongboxChestContainer> STRONGBOX_CHEST_CONTAINER_TYPE;
	public static ContainerType<SkullChestContainer> SKULL_CHEST_CONTAINER_TYPE;
	public static ContainerType<CompressorChestContainer> COMPRESSOR_CHEST_CONTAINER_TYPE;
	public static ContainerType<WitherChestContainer> WITHER_CHEST_CONTAINER_TYPE;
	
	
	@Mod.EventBusSubscriber(modid = Treasure.MODID, bus = EventBusSubscriber.Bus.MOD)	
	public static class RegistrationHandler {		
		
		@SubscribeEvent
		public static void registerContainers(final RegistryEvent.Register<ContainerType<?>> event) {
			STANDARD_CHEST_CONTAINER_TYPE = IForgeContainerType.create(StandardChestContainer::create);
			STANDARD_CHEST_CONTAINER_TYPE.setRegistryName("standard_chest_container");
			event.getRegistry().register(STANDARD_CHEST_CONTAINER_TYPE);
			
			STRONGBOX_CHEST_CONTAINER_TYPE = IForgeContainerType.create(StrongboxChestContainer::create);
			STRONGBOX_CHEST_CONTAINER_TYPE.setRegistryName("strongbox_chest_container");
			event.getRegistry().register(STRONGBOX_CHEST_CONTAINER_TYPE);
			
			SKULL_CHEST_CONTAINER_TYPE = IForgeContainerType.create(SkullChestContainer::create);
			SKULL_CHEST_CONTAINER_TYPE.setRegistryName("skull_chest_container");
			event.getRegistry().register(SKULL_CHEST_CONTAINER_TYPE);
			
			COMPRESSOR_CHEST_CONTAINER_TYPE = IForgeContainerType.create(CompressorChestContainer::create);
			COMPRESSOR_CHEST_CONTAINER_TYPE.setRegistryName("compressor_chest_container");
			event.getRegistry().register(COMPRESSOR_CHEST_CONTAINER_TYPE);
			
			WITHER_CHEST_CONTAINER_TYPE = IForgeContainerType.create(WitherChestContainer::create);
			WITHER_CHEST_CONTAINER_TYPE.setRegistryName("wither_chest_container");
			event.getRegistry().register(WITHER_CHEST_CONTAINER_TYPE);
		}
	}
}
