package fr.telec.simpleMumbleLink;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import fr.telec.simpleCore.Language;

public class simpleMumbleLink extends JavaPlugin implements Listener {

	private Language lg;

	/*
	 * Plugin setup
	 */

	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);
		
		saveDefaultConfig();
		reloadConfig();
		
		lg = new Language(this);
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
