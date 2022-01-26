package net.mcdesertion.furniturelib.objects;

import lombok.Getter;
import lombok.Setter;
import net.mcdesertion.furniturelib.FurnitureLib;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class Furniture {

    private String uuid;
    private transient ArmorStand holder;
    private List<Seat> seats = new ArrayList<>();
    private transient Location location;

    public Furniture (ItemStack model, Location location, List<Seat> seats) {
        holder = (ArmorStand) location.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
        uuid = holder.getUniqueId().toString();
        holder.addDisabledSlots(EquipmentSlot.CHEST, EquipmentSlot.FEET, EquipmentSlot.HAND, EquipmentSlot.HEAD, EquipmentSlot.LEGS, EquipmentSlot.OFF_HAND);
        holder.setItem(EquipmentSlot.HEAD, model.clone());
        holder.setVisible(false);
        holder.setAI(false);
        holder.setCollidable(false);
        holder.setMetadata("isFurniture", new FixedMetadataValue(FurnitureLib.getPlugin(), true));
        holder.setMetadata("furnitureData", new FixedMetadataValue(FurnitureLib.getPlugin(), FurnitureLib.getGson().toJson(this)));
        this.location = location;
        this.seats = seats;
        for (Seat seat : seats) {
            seat.initiate();
            seat.setParent(this);
            seat.setUuid(getUuid());
        }
    }

    public void despawn() {
        for (Seat seat : seats) {
            seat.getHolder().remove();
        }
        getHolder().remove();
        FurnitureLib.getRegisteredFurniture().remove(this);
    }

}
