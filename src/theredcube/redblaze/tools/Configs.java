package theredcube.redblaze.tools;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

public class Configs {
	
	private Plugin plugin;
	
	public Configs(Plugin plugin){
		this.plugin = plugin;
	}
	
	//Config
	public FileConfiguration customConfig = null;
	public File customConfigFile = null;
	public FileConfiguration getCustomConfig(String name) {//Get
	    if (customConfig == null) {
	        try {
				reloadCustomConfig(name);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				plugin.getLogger().severe("[TheRedWar]Probleme d'encodage de conifg");
				return null;
			}
	    }
	    return customConfig;
	}
	
	public void reloadCustomConfig(String name) throws UnsupportedEncodingException {//Load
	    if (customConfigFile == null) {
	    customConfigFile = new File(plugin.getDataFolder(),name + ".yml");
	    }
	    customConfig = YamlConfiguration.loadConfiguration(customConfigFile);
	 
	    // Look for defaults in the jar
	    Reader defConfigStream = new InputStreamReader(plugin.getResource(name + ".yml"), "UTF8");
	    if (defConfigStream != null) {
	        YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
	        customConfig.setDefaults(defConfig);
	    }
	    plugin.getLogger().info("[TheRedWar]Fichier " + name + " correctement chargé");
	}
	
	public void saveCustomConfig() {// Enregistrement
		
	    if (customConfig == null || customConfigFile == null) {
	        return;
	    }
	    try {
	        customConfig.save(customConfigFile);
	    } catch (IOException ex) {
	       
	    }
	}
}
