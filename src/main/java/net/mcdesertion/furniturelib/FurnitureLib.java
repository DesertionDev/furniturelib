package net.mcdesertion.furniturelib;

import com.google.gson.Gson;
import lombok.Getter;
import net.mcdesertion.furniturelib.listeners.EntityDismountListener;
import net.mcdesertion.furniturelib.listeners.PlayerInteractAtEntityListener;
import net.mcdesertion.furniturelib.listeners.SeatClickListener;
import net.mcdesertion.furniturelib.listeners.SeatDismountListener;
import net.mcdesertion.furniturelib.objects.Furniture;
import net.mcdesertion.furniturelib.objects.Seat;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class FurnitureLib {

    @Getter
    private static JavaPlugin plugin;

    @Getter
    private static List<Furniture> registeredFurniture = new ArrayList<>();

    @Getter
    private static Gson gson = new Gson();

    public FurnitureLib(JavaPlugin plugin) {
        FurnitureLib.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(new PlayerInteractAtEntityListener(), plugin);
        Bukkit.getPluginManager().registerEvents(new EntityDismountListener(), plugin);
        Bukkit.getPluginManager().registerEvents(new SeatClickListener(), plugin);
        Bukkit.getPluginManager().registerEvents(new SeatDismountListener(), plugin);
    }

    public Furniture createFurniture(Location location, ItemStack model, List<Seat> seats) {
        Furniture furniture = new Furniture(model, location, seats);
        registeredFurniture.add(furniture);
        return furniture;
    }

    public void scanForFurniture() {
        Map<String, Furniture> foundFurniture = new HashMap<>();
        List<Seat> foundSeats = new ArrayList<>();
        for (World world : Bukkit.getWorlds()) {
            for (ArmorStand armorStand : world.getEntitiesByClass(ArmorStand.class)) {
                if (armorStand.hasMetadata("isFurniture")) {
                    Furniture furniture = getGson().fromJson(armorStand.getMetadata("furnitureData").get(0).asString(), Furniture.class);
                    furniture.setHolder(armorStand);
                    foundFurniture.put(furniture.getUuid(), furniture);
                }
                if (armorStand.hasMetadata("isSeat")) {
                    Seat seat = getGson().fromJson(armorStand.getMetadata("seatData").get(0).asString(), Seat.class);
                    seat.setHolder(armorStand);
                    foundSeats.add(seat);
                }
            }
        }
        for (Seat foundSeat : foundSeats) {
            if (foundFurniture.containsKey(foundSeat.getUuid())) {
                foundFurniture.get(foundSeat.getUuid()).getSeats().add(foundSeat);
            }
        }
        registeredFurniture.addAll(foundFurniture.values());
    }
}
