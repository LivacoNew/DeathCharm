package dev.livaco.deathcharm.items;

import dev.livaco.deathcharm.Config;
import dev.livaco.deathcharm.datacomponents.RemainingUsesComponent;
import dev.livaco.deathcharm.registration.DataComponentsRegistration;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;

import java.util.function.Consumer;

public class DeathCharmItem extends Item {
    public DeathCharmItem(Properties properties) {
        super(properties.stacksTo(1)
                .setNoCombineRepair()
                .component(DataComponentsRegistration.USES_LEFT.get(), new RemainingUsesComponent(Config.CHARM_USES.getAsInt()))
                .rarity(Rarity.UNCOMMON));
    }

    @SuppressWarnings("deprecation")
    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay tooltipDisplay, Consumer<Component> tooltipAdder, TooltipFlag flag) {
        super.appendHoverText(stack, context, tooltipDisplay, tooltipAdder, flag);

        RemainingUsesComponent remaining = stack.get(DataComponentsRegistration.USES_LEFT.get());
        if(remaining != null && Config.CHARM_USES.getAsInt() != 0)
            tooltipAdder.accept(Component.translatable("tooltip.deathcharm.deathcharm.usesleft", remaining.usesLeft()));

        tooltipAdder.accept(Component.translatable("tooltip.deathcharm.deathcharm.description"));
    }
}
