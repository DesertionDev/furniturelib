package net.mcdesertion.furniturelib.listeners;

import net.mcdesertion.furniturelib.FurnitureLib;
import net.mcdesertion.furniturelib.events.FurnitureClickEvent;
import net.mcdesertion.furniturelib.events.SeatClickEvent;
import net.mcdesertion.furniturelib.objects.Furniture;
import net.mcdesertion.furniturelib.objects.Seat;
import org.bukkit.Bukkit;
import org.bukkit.entity.ArmorStand;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;

public class PlayerInteractAtEntityListener implements Listener {

    @EventHandler
    public void onClick(PlayerInteractAtEntityEvent event) {
        if (!(event.getRightClicked() instanceof ArmorStand)) {
            return;
        }
        ArmorStand armorStand = (ArmorStand) event.getRightClicked();
        if (armorStand.hasMetadata("isFurniture")) {
            event.setCancelled(true);
            Bukkit.getPluginManager().callEvent(new FurnitureClickEvent(FurnitureLib.getGson().fromJson(
                    armorStand.getMetadata("furnitureData").get(0).asString(), Furniture.class), event.getPlayer(), false));
        } else if (armorStand.hasMetadata("isSeat")) {
            event.setCancelled(true);
            Bukkit.getPluginManager().callEvent(new SeatClickEvent(FurnitureLib.getGson().fromJson(
                    armorStand.getMetadata("seatData").get(0).asString(), Seat.class), event.getPlayer(), false));
        }
    }

}
