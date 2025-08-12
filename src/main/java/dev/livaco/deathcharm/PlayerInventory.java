package dev.livaco.deathcharm;

import net.minecraft.core.NonNullList;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.fml.ModList;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.capability.ICuriosItemHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PlayerInventory {
    protected NonNullList<ItemStack> vanillaSlots;
    protected List<ItemStack> curiosSlots;

    public PlayerInventory(Player player) {
        this.cloneVanillaInventory(player.getInventory());

        // Curios
        if(ModList.get().isLoaded("curios"))
            this.cloneCuriosInventory(player);
    }

    public void restoreToPlayer(Player player) {
        for(int i = 0; i < this.vanillaSlots.size(); i++) {
            player.getInventory().setItem(i, this.vanillaSlots.get(i));
        }

        // Curios
        if(ModList.get().isLoaded("curios") && this.curiosSlots != null) {
            for(ItemStack stack : this.curiosSlots) {
                player.getInventory().placeItemBackInInventory(stack);
            }
        }
    }

    protected void cloneVanillaInventory(Inventory inventory) {
        // This is probably expensive? Any way to clone the Inventory directly?
        vanillaSlots = NonNullList.withSize(inventory.getContainerSize(), ItemStack.EMPTY);
        for(int i = 0; i < inventory.getContainerSize(); i++) {
            vanillaSlots.set(i, inventory.getItem(i).copy());
        }
    }

    protected void cloneCuriosInventory(Player player) {
        Optional<ICuriosItemHandler> curiosInventoryOptional = CuriosApi.getCuriosInventory(player);
        if(curiosInventoryOptional.isEmpty())
            return;

        ICuriosItemHandler curiosInventory = curiosInventoryOptional.get();
        this.curiosSlots = new ArrayList<>();
        for(int i = 0; i < curiosInventory.getEquippedCurios().getSlots(); i++) {
            this.curiosSlots.add(curiosInventory.getEquippedCurios().getStackInSlot(i).copy());
        }
    }
}