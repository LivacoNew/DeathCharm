package dev.livaco.deathcharm.events;

import dev.livaco.deathcharm.Config;
import dev.livaco.deathcharm.DeathCharm;
import dev.livaco.deathcharm.datacomponents.RemainingUsesComponent;
import dev.livaco.deathcharm.registration.DataComponentsRegistration;
import dev.livaco.deathcharm.registration.ItemRegistration;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.living.LivingDropsEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class RestoreInventoryEvents {
    private HashMap<UUID, List<ItemStack>> inventoriesToRestore = new HashMap<>();

    @SubscribeEvent
    public void onLivingDeath(LivingDeathEvent event) {
        if(!(event.getEntity() instanceof Player player))
            return;

        ArrayList<ItemStack> inventory = new ArrayList<>(player.getInventory().getNonEquipmentItems().stream().toList());
        inventory.add(player.getInventory().getSelectedItem());

        boolean found = false;
        for(ItemStack stack : inventory) {
            if(stack.getItem() != ItemRegistration.DEATH_CHARM.get())
                continue;

            if(Config.CHARM_USES.getAsInt() != 0) {
                // Check if we have any uses left
                RemainingUsesComponent remaining = stack.get(DataComponentsRegistration.USES_LEFT.get());
                if (remaining == null) // ?
                    continue;

                int newUses = remaining.usesLeft() - 1;
                stack.set(DataComponentsRegistration.USES_LEFT.get(), new RemainingUsesComponent(newUses));
                if (newUses == 0) {
                    // Destroy
                    stack.setCount(0);
                } else if (newUses < 0) {
                    // Destroy and don't continue
                    stack.setCount(0);
                    continue;
                }
            }

            // We can proceed
            found = true;
            break;
        }

        if(!found)
            return; // lose your stuff :(

        inventoriesToRestore.put(player.getUUID(), inventory);
    }

    @SubscribeEvent
    public void onLivingDrops(LivingDropsEvent event) {
        if(!(event.getEntity() instanceof Player player))
            return;
        if(!inventoriesToRestore.containsKey(player.getUUID()))
            return;

        event.getDrops().clear();
    }

    @SubscribeEvent
    public void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event) {
        Player player = event.getEntity();
        if(!inventoriesToRestore.containsKey(player.getUUID()))
            return;

        // Restore their stuff
        for(ItemStack stack : inventoriesToRestore.get(player.getUUID())) {
            player.getInventory().add(stack);
        }
        inventoriesToRestore.remove(player.getUUID());
    }
}
