package dev.livaco.deathcharm.registration;

import dev.livaco.deathcharm.DeathCharm;
import dev.livaco.deathcharm.items.DeathCharmItem;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ItemRegistration {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(DeathCharm.MODID);

    public static final DeferredItem<Item> DEATH_CHARM = ITEMS.register("death_charm", registryName -> new DeathCharmItem(
            new Item.Properties().setId(ResourceKey.create(Registries.ITEM, registryName))
    ));

    public static void register(IEventBus bus) {
        ITEMS.register(bus);
    }
}