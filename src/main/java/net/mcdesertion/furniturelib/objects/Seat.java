package net.mcdesertion.furniturelib.objects;

import lombok.Getter;
import lombok.Setter;
import net.mcdesertion.furniturelib.FurnitureLib;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.metadata.FixedMetadataValue;

@Getter
@Setter
public class Seat {

    private String uuid;
    private transient ArmorStand holder;
    private transient Furniture parent;
    private transient boolean occupied = false;
    private transient Player seated = null;
    private transient Location location;

    public Seat(Location location) {
        this.location = location;
    }

    public void initiate() {
        holder = (ArmorStand) location.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
        holder.addDisabledSlots(EquipmentSlot.CHEST, EquipmentSlot.FEET, EquipmentSlot.HAND, EquipmentSlot.HEAD, EquipmentSlot.LEGS, EquipmentSlot.OFF_HAND);
        holder.setVisible(false);
        holder.setAI(false);
        holder.setCollidable(false);
        holder.setMetadata("isSeat", new FixedMetadataValue(FurnitureLib.getPlugin(), true));
        holder.setMetadata("seatData", new FixedMetadataValue(FurnitureLib.getPlugin(), FurnitureLib.getGson().toJson(this)));
    }

}
