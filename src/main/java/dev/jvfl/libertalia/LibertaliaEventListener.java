package dev.jvfl.libertalia;

import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.EquipmentSlot;

public class LibertaliaEventListener implements Listener {

    @EventHandler
    public void onPlayerJoin(final PlayerJoinEvent event) {
        final Player player = event.getPlayer();
        player.sendMessage("Bem-vindo(a), " + player.getName() + "!");
    }
    
    //When player type a command
    @EventHandler
    public void onPlayerCommand(final PlayerCommandPreprocessEvent event) {
        ChatHandler.handleChat(event);
    }


    //On player respawn after death
    @EventHandler
    public void onPlayerRespawn(final PlayerRespawnEvent event) {
        final Player player = event.getPlayer();

        player.sendMessage("Voltando para o spawn...");

        WorldHandler.teleport(player, World.Environment.NORMAL, 1408.625, 128, -294.342);
    }
    

    //Prevent entities from being destroyed
    @EventHandler
    public void onBlockBreakEvent(final BlockBreakEvent event) {
        if (!WorldHandler.canBeDestroyed(event.getBlock())) {
            event.setCancelled(true);

            final Player player = event.getPlayer();
            if (player != null)
            {
                ChatHandler.sendMessage(player, "You can't destroy this entity");
            }
            return;
        }

    }

    //When player put a entity in the world
    @EventHandler
    public void onPlayerInteract(final PlayerInteractEvent event) {
        //Get player
        if (event.getPlayer() == null) {
            return;
        }

        if(event.getHand() == EquipmentSlot.HAND) return;
        
        final Player player = event.getPlayer();
        if(event.getAction() == Action.RIGHT_CLICK_BLOCK && WorldHandler.isSafeEntity(player)) {
            //Check if the entity is safe
            WorldHandler.setCanBeDestroyed(event.getClickedBlock(), WorldHandler.canBeDestroyed(event.getClickedBlock()) ? false : true);

            if(WorldHandler.isSafeEntity(player))
            {
                ChatHandler.sendMessage(player, "Chunk is now " + (WorldHandler.canBeDestroyed(event.getClickedBlock()) ? "unsafe" : "safe"));
            }
        }
    }
}
