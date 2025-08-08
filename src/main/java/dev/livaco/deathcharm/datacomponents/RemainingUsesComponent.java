package dev.livaco.deathcharm.datacomponents;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

public record RemainingUsesComponent(int usesLeft) {
    public static final Codec<RemainingUsesComponent> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    Codec.INT.fieldOf("usesLeft").forGetter(RemainingUsesComponent::usesLeft)
            ).apply(instance, RemainingUsesComponent::new)
    );
    public static final StreamCodec<ByteBuf, RemainingUsesComponent> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.INT, RemainingUsesComponent::usesLeft,
            RemainingUsesComponent::new
    );
}