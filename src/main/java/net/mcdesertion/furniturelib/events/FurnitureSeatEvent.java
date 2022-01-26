package net.mcdesertion.furniturelib.events;

import lombok.Getter;
import net.mcdesertion.furniturelib.objects.Furniture;
import net.mcdesertion.furniturelib.objects.Seat;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class FurnitureSeatEvent extends Event {

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
    private Furniture furniture;

    @Getter
    private Seat seat;

    @Getter
    private Player seater;

    @Getter
    private boolean cancelled = false;

    public FurnitureSeatEvent(Furniture furniture, Seat seat, Player seater) {
        this.furniture = furniture;
        this.seat = seat;
        this.seater = seater;
    }
}
