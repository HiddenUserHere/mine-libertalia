package dev.jvfl.libertalia;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import net.md_5.bungee.api.ChatColor;

public class ChatHandler {

    public static void handleChat(final PlayerCommandPreprocessEvent event) {
        final Player player = event.getPlayer();
        final String command = event.getMessage();

        if (!command.startsWith("/")) {
            return;
        }
        
        //Split the command
        String[] commandSplit = command.split(" ");

        String[] args = new String[commandSplit.length - 1];
        System.arraycopy(commandSplit, 1, args, 0, commandSplit.length - 1);

        switch (commandSplit[0]) {
            case "/voltar":
                sendMessage(player, "Voltando...");

                WorldHandler.teleport(player, World.Environment.NORMAL, 1408.625, 128, -294.342);
                break;

            case "/nether":
                sendMessage(player, "Indo para o Nether...");

                WorldHandler.teleport(player, World.Environment.NETHER);
                break;

            case "/theend":
                sendMessage(player, "Indo para o The End...");

                WorldHandler.teleport(player, World.Environment.THE_END);
                break;

            case "/suicide":
                WorldHandler.kill(player);

                sendMessageToAll("Server> " + player.getName() + " cometeu suicidio!");
                break;

            case "/safeentity":
                if (args.length > 0) {
                    WorldHandler.setSafeEntity(player, Boolean.valueOf(args[0]));
                }

                sendMessage(player, ChatColor.GREEN + "Server> Safe Entity: " + String.valueOf(WorldHandler.isSafeEntity(player)));
                break;

            default:
                break;
        }
    }

    public static void sendMessageToAll(String message) {
        Bukkit.broadcastMessage(ChatColor.GREEN + "Server> " + message);
    }

    public static void sendMessage(Player player, String message)
    {
        player.sendMessage(message);
    }
}