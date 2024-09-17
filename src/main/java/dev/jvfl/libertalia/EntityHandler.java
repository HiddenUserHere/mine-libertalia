package dev.jvfl.libertalia;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Tameable;

public class EntityHandler {

    static public Entity OwnsLookingEntity(Player player) {
        List<Entity> entities = getLookingAt(player);

        for(Entity entity : entities) {
            //Is Entity an Wolf or Cat?
            if(entity.getType() == EntityType.WOLF || entity.getType() == EntityType.CAT) {
                Tameable tameable = (Tameable) entity;
                tameable.setOwner(player);
                tameable.setTarget(null);

                return entity;
            }
        }

        return null;
    }

    static private List<Entity> getLookingAt(Player player) {
        List<Entity> entities = new ArrayList<Entity>();

        entities.clear();

        for (Entity entity : player.getNearbyEntities(10, 10, 10)) {
            if (isLookingAt(player, entity)) {
                entities.add(entity);
            }
        }

        return entities;
    }

    static private boolean isLookingAt(Player player, Entity target) {
        return player.hasLineOfSight(target);
    }
}
