package dev.livaco.deathcharm;

import com.mojang.logging.LogUtils;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import org.slf4j.Logger;

@Mod(DeathCharm.MODID)
public class DeathCharm {
    public static final String MODID = "deathcharm";
    public static final Logger LOGGER = LogUtils.getLogger();

    public DeathCharm(IEventBus modEventBus, ModContainer modContainer) {
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }
}
