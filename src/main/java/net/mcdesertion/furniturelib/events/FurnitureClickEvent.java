package net.mcdesertion.furniturelib.events;

import lombok.Getter;
import net.mcdesertion.furniturelib.objects.Furniture;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class FurnitureClickEvent extends Event {

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
    private Furniture clicked;

    @Getter
    private Player clicker;

    @Getter
    private boolean cancelled;

    public FurnitureClickEvent(Furniture clicked, Player clicker, boolean cancelled) {
        this.clicked = clicked;
        this.clicker = clicker;
        this.cancelled = cancelled;
    }
}
