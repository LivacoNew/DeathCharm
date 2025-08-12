package dev.livaco.deathcharm.items;

import dev.livaco.deathcharm.Config;
import dev.livaco.deathcharm.datacomponents.RemainingUsesComponent;
import dev.livaco.deathcharm.registration.DataComponentsRegistration;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class DeathCharmItem extends Item {
    public DeathCharmItem(Properties properties) {
        super(properties.stacksTo(1)
                .component(DataComponentsRegistration.USES_LEFT.get(), new RemainingUsesComponent(Config.CHARM_USES.getAsInt()))
                .rarity(Rarity.UNCOMMON));
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);

        RemainingUsesComponent remaining = stack.get(DataComponentsRegistration.USES_LEFT.get());
        if(remaining != null && Config.CHARM_USES.getAsInt() != 0)
            tooltipComponents.add(Component.translatable("tooltip.deathcharm.deathcharm.usesleft", remaining.usesLeft()));

        tooltipComponents.add(Component.translatable("tooltip.deathcharm.deathcharm.description"));
    }
}
