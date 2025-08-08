package dev.livaco.deathcharm.registration;

import dev.livaco.deathcharm.DeathCharm;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class CreativeTabRegistration {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, DeathCharm.MODID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> DEATH_CHARM_TAB = CREATIVE_MODE_TABS.register("deathcharm", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemgroup.deathcharm"))
            .icon(() -> ItemRegistration.DEATH_CHARM.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                output.accept(ItemRegistration.DEATH_CHARM.get());
            }).build());

    public static void register(IEventBus bus) {
        CREATIVE_MODE_TABS.register(bus);
    }
}