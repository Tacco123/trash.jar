package com.tacco.trash;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Collections;
import java.util.List;

public class TrashPlugin extends JavaPlugin implements Listener, TabExecutor {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        getCommand("trash").setExecutor(this);
        getCommand("trash").setTabCompleter(this);
        getLogger().info("TrashPlugin aktiviert!");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Nur Spieler können diesen Befehl nutzen!");
            return true;
        }
        Player player = (Player) sender;
        Inventory trashInv = Bukkit.createInventory(null, 27, ChatColor.DARK_GRAY + "🗑 Müllcontainer");
        player.openInventory(trashInv);
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String alias, String[] args) {
        return Collections.emptyList();
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        if (event.getView().getTitle().contains("Müllcontainer")) {
            event.getPlayer().sendMessage(ChatColor.GRAY + "Die Items im Müllcontainer wurden gelöscht.");
        }
    }
}
