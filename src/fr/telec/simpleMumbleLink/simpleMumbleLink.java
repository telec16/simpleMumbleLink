package fr.telec.simpleMumbleLink;

import java.util.logging.Level;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import fr.telec.simpleCore.Language;

public class simpleMumbleLink extends JavaPlugin implements Listener {

	private Language lg;
	private VocalServerLink vsl = null;

	/*
	 * Plugin setup
	 */
	
	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);
		
		saveDefaultConfig();
		reloadConfig();
		
		lg = new Language(this);
		
		if(getConfig().getString("vocalserver.type") == "murmur")
			vsl = new MumbleLink(getConfig().getString("vocalserver.base_uri"), getConfig().getString("vocalserver.server_id"));
		else
			getLogger().log(Level.SEVERE, "No valid type for the vocal server");
	}

	@Override
	public void onDisable() {
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("update")) {
			reloadConfig();
			lg.reload();

			sender.sendMessage(ChatColor.GRAY + "[" + getName() + "] " + lg.get("updated"));
			return true;
		}
		return false;
	}

}
