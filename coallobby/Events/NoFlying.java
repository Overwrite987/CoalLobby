package vk.coalstudio.ru.coallobby.Events;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import vk.coalstudio.ru.coallobby.CoalLobby;

public class NoFlying implements Listener {
	
	private final CoalLobby instance = CoalLobby.getInstance();
	
    @EventHandler
    public void nofly(PlayerMoveEvent e) {
    	FileConfiguration config = instance.getConfig();
        if(config.getBoolean("no_fly_high")) {
            Player p = e.getPlayer();
            int location = p.getLocation().getBlockY();
            Location spawn = p.getWorld().getSpawnLocation();
            if(location > config.getInt("flyheight")) {
                p.teleport(spawn);
                if (!config.getBoolean("disable_fly_message")) {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getString("prefix")) + ChatColor.translateAlternateColorCodes('&', config.getString("no_fly_chat")));
                }
                if(!config.getBoolean("disable_fly_sound")) {
                    p.playSound(p.getLocation(), Sound.valueOf(config.getString("fly_sound")), 10, 1);
                }
                if(!config.getBoolean("disable_fly_title")) {
                    p.sendTitle(ChatColor.translateAlternateColorCodes('&', config.getString("no_fly_title")), ChatColor.translateAlternateColorCodes('&', config.getString("no_fly_subtitle")), 10, 20, 10);
                }
                if (!config.getBoolean("disable_fly_bar")) {
                    p.sendActionBar(ChatColor.translateAlternateColorCodes('&', config.getString("no_fly_bar")));
                }
            }
        }
    }

}
