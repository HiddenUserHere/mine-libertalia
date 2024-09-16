package dev.jvfl.libertalia;

import org.bukkit.plugin.java.JavaPlugin;

public final class Libertalia extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

        // Registering the event listener
        getServer().getPluginManager().registerEvents(new LibertaliaEventListener(), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
