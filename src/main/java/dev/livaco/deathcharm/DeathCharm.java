package dev.livaco.deathcharm;

import com.mojang.logging.LogUtils;
import dev.livaco.deathcharm.events.RestoreInventoryEvents;
import dev.livaco.deathcharm.registration.CreativeTabRegistration;
import dev.livaco.deathcharm.registration.DataComponentsRegistration;
import dev.livaco.deathcharm.registration.ItemRegistration;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.NeoForge;
import org.slf4j.Logger;

@Mod(DeathCharm.MODID)
public class DeathCharm {
    public static final String MODID = "deathcharm";
    public static final Logger LOGGER = LogUtils.getLogger();

    public DeathCharm(IEventBus modEventBus, ModContainer modContainer) {
        DataComponentsRegistration.register(modEventBus);
        ItemRegistration.register(modEventBus);
        CreativeTabRegistration.register(modEventBus);

        NeoForge.EVENT_BUS.register(new RestoreInventoryEvents());

        modContainer.registerConfig(ModConfig.Type.STARTUP, Config.SPEC);
    }
}
