package theredcube.redblaze.theredwar;

import java.io.Serializable;

import org.bukkit.entity.Player;

public class RedPlayer implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8742914836581300221L;
	private String name;
	private String uid;
	private int money;
	
	//States
	private int kills;
	private int death;
	private int batailles;
	

	RedPlayer(Player player){
		this.setName(player.getName());
		this.setUid(player.getUniqueId().toString());
		this.money = 500;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMoney() {
		return money;
	}

	public void addMoney(int amount) {
		this.money = money + amount;
	}
	public void removeMoney(int amount) {
		this.money = money - amount;
	}

	
	
	
	// Stats
	public int getKills() {
		return kills;
	}

	public void addKills(int amount) {
		this.kills = this.kills + amount;
	}
	
	public int getDeath() {
		return death;
	}

	public void addDeath(int amount) {
		this.death = this.death + amount;
	}
	
	public int getBatailles() {
		return batailles;
	}

	public void addbatailles(int amount) {
		this.batailles = this.batailles + amount;
	}
}
