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
	private String name;
	
	public Configs(Plugin plugin,String nom){
		this.plugin = plugin;
		this.name = nom;
	}
	
	//Config
	public FileConfiguration customConfig = null;
	public File customConfigFile = null;
	public FileConfiguration getCustomConfig() {//Get
	        try {
				reloadCustomConfig();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				plugin.getLogger().severe("[TheRedWar]Probleme d'encodage de conifg");
				return null;
			}
	    
	    return customConfig;
	}
	
	public void reloadCustomConfig() throws UnsupportedEncodingException {//Load
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
	        reloadCustomConfig();
	    } catch (IOException ex) {
	       ex.printStackTrace();
	    }
	}
}
