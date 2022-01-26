package net.mcdesertion.furniturelib.listeners;

import net.mcdesertion.furniturelib.FurnitureLib;
import net.mcdesertion.furniturelib.events.SeatClickEvent;
import net.mcdesertion.furniturelib.events.SeatDismountEvent;
import net.mcdesertion.furniturelib.objects.Seat;
import org.bukkit.Bukkit;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.spigotmc.event.entity.EntityDismountEvent;

public class EntityDismountListener implements Listener {

    @EventHandler
    public void onDismount(EntityDismountEvent event) {
        if (!(event.getDismounted() instanceof ArmorStand)) {
            return;
        }
        if (!(event.getEntity() instanceof Player)) {
            return;
        }
        ArmorStand armorStand = (ArmorStand) event.getDismounted();
        if (!armorStand.hasMetadata("isSeat") || armorStand.hasMetadata("canDismount")) {
            return;
        }
        event.setCancelled(true);
        Bukkit.getPluginManager().callEvent(new SeatDismountEvent(FurnitureLib.getGson().fromJson(
                armorStand.getMetadata("seatData").get(0).asString(), Seat.class), (Player) event.getEntity()));
    }

}
