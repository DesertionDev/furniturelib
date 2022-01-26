package net.mcdesertion.furniturelib.events;

import lombok.Getter;
import net.mcdesertion.furniturelib.objects.Seat;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class SeatDismountEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    @NotNull
    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Getter
    private Seat seat;

    @Getter
    private Player seater;

    @Getter
    private boolean cancelled = false;

    public SeatDismountEvent(Seat seat, Player seater) {
        this.seat = seat;
        this.seater = seater;
    }
}
