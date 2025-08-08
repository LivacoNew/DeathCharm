package dev.livaco.deathcharm;

import net.neoforged.neoforge.common.ModConfigSpec;

public class Config {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    public static final ModConfigSpec.IntValue CHARM_USES = BUILDER
            .comment("The number of uses a charm has before it is destroyed. Set to 0 for infinity.")
            .defineInRange("charmUses", 3, 0, 64);

    static final ModConfigSpec SPEC = BUILDER.build();
}
