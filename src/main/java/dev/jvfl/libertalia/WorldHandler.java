package dev.jvfl.libertalia;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.World.Environment;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class WorldHandler {

    private static World getWorld(Environment world)  {
        for (World w : Bukkit.getServer().getWorlds()) {
            if (w.getEnvironment() == world) {
                return w;
            }
        }

        return null;
    }

    public static void teleport(Player player, Environment world, double x, double y, double z) {
        teleport(player, world, x, y, z, 1000);
    }

    public static void teleport(Player player, Environment world, double x, double y, double z, double time) {
        // Get the world
        World w = getWorld(world);
        Location location = new Location(w, x, y, z);

        //Convert the time to ticks
        time *= 20;
        time /= 1000;

        //Teleport the player to the spawn point after a delay
        Bukkit.getScheduler().runTaskLater(Libertalia.getPlugin(Libertalia.class), new Runnable() {
            @Override
            public void run() {
                player.teleport(location);
            }
        }, (long)time);
    }

    public static void teleport(Player player, Environment world) {
        //Get the world
        World w = getWorld(world);
        Location location = new Location(w, w.getSpawnLocation().getX(), w.getSpawnLocation().getY(),
                w.getSpawnLocation().getZ());

        //Teleport the player to the spawn point
        player.teleport(location);
    }
    
    public static void kill(Player player) {
        player.setHealth(0);
    }

    public static boolean canBeDestroyed(Block block) {

        if(block == null) {
            return false;
        }

        String metadata = DataHandler.get(block, "can_be_destroyed");
        if (metadata == null) {
            return true;
        }

        return Boolean.valueOf(metadata);
    }

    public static void setCanBeDestroyed(Block block, boolean value) {
        DataHandler.set(block, "can_be_destroyed", value);
    }

    public static void setSafeEntity(Entity entity, boolean value) {
        DataHandler.set(entity, "safe_entity", value);
    }

    public static boolean isSafeEntity(Entity entity) {
        String metadata = DataHandler.get(entity, "safe_entity");
        if (metadata == null) {
            return false;
        }

        return Boolean.valueOf(metadata);
    }
}
