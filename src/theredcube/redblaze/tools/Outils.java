package theredcube.redblaze.tools;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Outils {
	public int getAmount(Inventory inv,ItemStack m){
		int amount = 0;
		int montant;
		for(int slot = 0;slot <= inv.getSize();slot++)
			if(inv.getItem(slot) != null){
			montant = inv.getItem(slot).getAmount();
			inv.getItem(slot).setAmount(1);
			m.setAmount(1);
			if(inv.getItem(slot).equals(m)){
				inv.getItem(slot).setAmount(montant);
				amount = amount + inv.getItem(slot).getAmount();
			}
		}
	
		
		return amount;
	}
	public static int getAmount(Player player,ItemStack m){
		ItemStack[] item = player.getInventory().getContents();
		int amount = 0;
		int montant;
		for(int slot = 0;slot < item.length;slot++){
			if(item[slot] != null){
			montant = item[slot].getAmount();
			item[slot].setAmount(1);
			m.setAmount(1);
			if(item[slot].equals(m)){
				item[slot].setAmount(montant);
				amount = amount + item[slot].getAmount();
		}
		}
	}
		
		
		return amount;
	}

	public static void clearAll(Player player,ItemStack m,int montant){
		m.setAmount(1);
		 for(int slot = 0;slot<=player.getInventory().getSize();slot++){
			 //player.sendMessage(slot + "    amount: " + montant);// TODO DEBUG
			 if(player.getInventory().getItem(slot) != null){
				 ItemStack playeritem = player.getInventory().getItem(slot).clone();
				 playeritem.setAmount(1);
				 if(playeritem.equals(m)){
				 if(montant<player.getInventory().getItem(slot).getAmount()){
					 ItemStack item = player.getInventory().getItem(slot);
					 item.setAmount(item.getAmount() - montant);
				//	 player.sendMessage("ok");// TODO DEBUG
					 montant = 0;
					 
				 }
				 else{
				 montant = montant - player.getInventory().getItem(slot).getAmount();
				 player.getInventory().setItem(slot,new ItemStack(Material.AIR));
				 }
				 
			 }
			 }
			 if(montant <= 0){
				 break;
			 }
			 
			 player.updateInventory();
		 }
	}
	
	public static ItemStack getItem(int nb,Material mat,String name, String lore1){
		List<String> itemLore = new ArrayList<>();
		itemLore.add(lore1);
		ItemStack item = new ItemStack(mat);
		ItemMeta itemm = item.getItemMeta();
		itemm.setLore(itemLore);
		itemm.setDisplayName(name);
		item.setItemMeta(itemm);
		item.setAmount(nb);
		return item;
	}
	public static ItemStack getItem(int nb,Material mat,String name, String lore1,String lore2){
		List<String> itemLore = new ArrayList<>();
		itemLore.add(lore1);
		itemLore.add(lore2);
		ItemStack item = new ItemStack(mat);
		ItemMeta itemm = item.getItemMeta();
		itemm.setLore(itemLore);
		itemm.setDisplayName(name);
		item.setItemMeta(itemm);
		item.setAmount(nb);
		return item;
	}
	public static ItemStack getItem(int nb,Material mat,String name, String lore1,String lore2,String lore3){
		List<String> itemLore = new ArrayList<>();
		itemLore.add(lore1);
		itemLore.add(lore2);
		itemLore.add(lore3);
		ItemStack item = new ItemStack(mat);
		ItemMeta itemm = item.getItemMeta();
		itemm.setLore(itemLore);
		itemm.setDisplayName(name);
		item.setItemMeta(itemm);
		item.setAmount(nb);
		return item;
	}
	public static ItemStack getItem(int nb,Material mat,String name, String lore1,String lore2,String lore3,String lore4){
		List<String> itemLore = new ArrayList<>();
		itemLore.add(lore1);
		itemLore.add(lore2);
		itemLore.add(lore3);
		itemLore.add(lore4);
		ItemStack item = new ItemStack(mat);
		ItemMeta itemm = item.getItemMeta();
		itemm.setLore(itemLore);
		itemm.setDisplayName(name);
		item.setItemMeta(itemm);
		item.setAmount(nb);
		return item;
	}
	public static ItemStack getItem(int nb,Material mat,String name, List<String> itemLore){
		ItemStack item = new ItemStack(mat);
		ItemMeta itemm = item.getItemMeta();
		itemm.setLore(itemLore);
		itemm.setDisplayName(name);
		item.setItemMeta(itemm);
		item.setAmount(nb);
		return item;
	}
	
}
