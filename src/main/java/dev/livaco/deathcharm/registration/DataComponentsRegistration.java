package dev.livaco.deathcharm.registration;

import dev.livaco.deathcharm.DeathCharm;
import dev.livaco.deathcharm.datacomponents.RemainingUsesComponent;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class DataComponentsRegistration {
    public static final DeferredRegister.DataComponents COMPONENTS = DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, DeathCharm.MODID);

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<RemainingUsesComponent>> USES_LEFT =
            COMPONENTS.registerComponentType("uses_left", builder ->
                    builder.persistent(RemainingUsesComponent.CODEC).networkSynchronized(RemainingUsesComponent.STREAM_CODEC)
            );

    public static void register(IEventBus bus) {
        COMPONENTS.register(bus);
    }
}
