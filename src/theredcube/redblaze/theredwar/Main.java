package theredcube.redblaze.theredwar;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import theredcube.redblaze.tools.Configs;
import theredcube.redblaze.tools.Custom;

public class Main extends JavaPlugin implements Listener{
	World world;
	Configs joueursConf = new Configs(this);
	Configs regenConf = new Configs(this);
	FileConfiguration joueurs = joueursConf.getCustomConfig("joueurs");
	public Logger log = getServer().getLogger();
	
	
	
	public void onEnable(){
		world = getServer().getWorld("war");
		getServer().getPluginManager().registerEvents(this, this);// Enregistrement des events
		log.info("[TheRedWar] Chargement terminé");
		BukkitScheduler s = Bukkit.getScheduler();
		s.scheduleSyncRepeatingTask(this, new Runnable(){

			@Override
			public void run() {
				regenblocks();
				
			}}, 0, 72000);
		
		
	}
	
	@SuppressWarnings("deprecation")
	public void regenblocks() {
		FileConfiguration regen = regenConf.getCustomConfig("regenBlocks");
		java.util.GregorianCalendar calendar = new GregorianCalendar();
		int h = calendar.get(Calendar.HOUR_OF_DAY);
		for(int i = 0;i<regen.getIntegerList("blocksx").size();i++){
			if((regen.getIntegerList("blocksh").get(i)-h)<=0){
				world.getBlockAt(regen.getIntegerList("blocksx").get(i),(Integer) regen.getIntegerList("blocksy").get(i), regen.getIntegerList("blocksz").get(i)).setTypeId(regen.getIntegerList("blocksid").get(i));
				regen.getIntegerList("blocksx").remove(i);
				regen.getIntegerList("blocksy").remove(i);
				regen.getIntegerList("blocksz").remove(i);
				regen.getIntegerList("blocksid").remove(i);
				regen.getIntegerList("blocksh").remove(i);
				regenConf.saveCustomConfig();
			}
		}
		
	}

	@EventHandler
	public void onPlace(BlockPlaceEvent event){
		// TODO Verifi si dans un village et si oui si c'est dans la maison du player
		Block block = event.getBlock();
		FileConfiguration regen = regenConf.getCustomConfig("regenBlocks");
		regen.getIntegerList("blocksh").add(new GregorianCalendar().get(Calendar.HOUR_OF_DAY));
		regen.getIntegerList("blocksx").add(block.getX());
		regen.getIntegerList("blocksy").add(block.getY());
		regen.getIntegerList("blocksz").add(block.getZ());
		regen.getIntegerList("blocksid").add(0);
		regenConf.saveCustomConfig();
	}
	
	public void onDestroy(BlockBreakEvent event){
		// TODO Verifi si dans un village et si oui si c'est dans la maison du player
		
		Block block = event.getBlock();
		FileConfiguration regen = regenConf.getCustomConfig("regenBlocks");
		regen.getIntegerList("blocksh").add(new GregorianCalendar().get(Calendar.HOUR_OF_DAY));
		regen.getIntegerList("blocksx").add(block.getX());
		regen.getIntegerList("blocksy").add(block.getY());
		regen.getIntegerList("blocksz").add(block.getZ());
		regen.getIntegerList("blocksid").add(event.get);
		regenConf.saveCustomConfig();
	}
	
	public void onDisable(){
		Bukkit.getScheduler().cancelTasks(this);// Cancel les tasks
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		
		return true;
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event){
		Player player = event.getPlayer();
		if(joueurs.contains(player.getName())){
			event.setJoinMessage(Message.messagedebienvenue(player.getName()));
			joueurs.set(player.getName() + ".money", 500);
			Custom.sendTitle(player, Message.titlebienvenue(player.getName()), Message.subtitlebienvenue(player.getName()));
			joueursConf.saveCustomConfig();
		}
		

	}
	
}
