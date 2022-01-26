package net.mcdesertion.furniturelib.listeners;

import net.mcdesertion.furniturelib.events.FurnitureSeatEvent;
import net.mcdesertion.furniturelib.events.SeatClickEvent;
import net.mcdesertion.furniturelib.objects.Seat;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class SeatClickListener implements Listener {

    @EventHandler
    public void onSeatClick(SeatClickEvent event) {
        if (event.isCancelled()) {
            return;
        }
        Seat seat = event.getClicked();
        if (seat.isOccupied()) {
            return;
        }
        seat.getHolder().addPassenger(event.getClicker());
        seat.setSeated(event.getClicker());
        seat.setOccupied(true);
        Bukkit.getPluginManager().callEvent(new FurnitureSeatEvent(seat.getParent(), seat, event.getClicker()));
    }

}
