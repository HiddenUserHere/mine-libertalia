package dev.jvfl.libertalia;

import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class DataHandler {

    public static void set(Entity entity, String key, Object value) {
        set(entity.getPersistentDataContainer(), key, value);
    }

    public static void set(Block block, String key, Object value) {

        set(block.getChunk().getPersistentDataContainer(), key, value);
    }

    public static void set(ItemStack itemStack, String key, Object value) {
        set(itemStack.getItemMeta().getPersistentDataContainer(), key, value);
    }

    public static boolean has(Entity entity, String key) {

        return get(entity.getPersistentDataContainer(), key) != null;
    }

    public static boolean has(Block block, String key) {

        return get(block.getChunk().getPersistentDataContainer(), key) != null;
    }

    public static boolean has(ItemStack itemStack, String key) {

        return get(itemStack.getItemMeta().getPersistentDataContainer(), key) != null;
    }

    public static String get(Entity entity, String key) {

        return get(entity.getPersistentDataContainer(), key);
    }

    public static String get(Block block, String key) {
        return get(block.getChunk().getPersistentDataContainer(), key);
    }

    public static String get(ItemStack itemStack, String key) {
        return get(itemStack.getItemMeta().getPersistentDataContainer(), key);
    }
    
    public static void remove(Entity entity, String key) {
            remove(entity.getPersistentDataContainer(), key);
    }

    public static void removeMetadata(Block block, String key) {
        remove(block.getChunk().getPersistentDataContainer(), key);
    }

    public static void remove(ItemStack itemStack, String key) {
        remove(itemStack.getItemMeta().getPersistentDataContainer(), key);
    }

    private static void set(PersistentDataContainer container, String key, Object value) {
        container.set(new NamespacedKey("libertalia", key), PersistentDataType.STRING, value.toString());
    }

    private static String get(PersistentDataContainer container, String key) {
        String ret = container.get(new NamespacedKey("libertalia", key), PersistentDataType.STRING);

        return ret == null ? null : ret;
    }

    private static void remove(PersistentDataContainer container, String key) {
        container.remove(new NamespacedKey("libertalia", key));
    }
}
