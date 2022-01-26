package net.mcdesertion.furniturelib.listeners;

import net.mcdesertion.furniturelib.FurnitureLib;
import net.mcdesertion.furniturelib.events.FurnitureSeatEvent;
import net.mcdesertion.furniturelib.events.SeatClickEvent;
import net.mcdesertion.furniturelib.events.SeatDismountEvent;
import net.mcdesertion.furniturelib.objects.Seat;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.metadata.FixedMetadataValue;

public class SeatDismountListener implements Listener {

    @EventHandler
    public void onSeatDismount(SeatDismountEvent event) {
        if (event.isCancelled()) {
            return;
        }
        Seat seat = event.getSeat();
        seat.getHolder().setMetadata("canDismount", new FixedMetadataValue(FurnitureLib.getPlugin(), true));
        seat.getHolder().removePassenger(event.getSeater());
        seat.setSeated(null);
        seat.setOccupied(false);
        seat.getHolder().removeMetadata("canDismount", FurnitureLib.getPlugin());
    }

}
