package theredcube.redblaze.theredwar;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Server;
import org.bukkit.entity.Player;

public class PlayerUtils
{
	private File path;
	private static Map<String, RedPlayer> redplayers = new HashMap<String, RedPlayer>();
	
	public PlayerUtils(File path,Server serv)
	{
		this.path = path;
	}

	public RedPlayer getPlayer(Player player)
	{
		
		RedPlayer redplayer = redplayers.get(player.getUniqueId().toString());
		if (redplayer != null)
			return redplayer;

		File file = new File(this.path + "/" + player.getUniqueId().toString() + ".obj");
		if(!file.exists())
			return null;
		ObjectInputStream ois;
		BufferedInputStream bis;

		try
		{
			bis = new BufferedInputStream(new FileInputStream(file));
			ois = new ObjectInputStream(bis);
			redplayer = (RedPlayer)ois.readObject();
			ois.close();
			bis.close();
			redplayers.put(player.getUniqueId().toString(), redplayer);
		}
		catch (ClassNotFoundException | IOException e)
		{
			e.printStackTrace();
			redplayers.remove(player.getUniqueId().toString());
		}
		return redplayer;  
	}

	public boolean set(RedPlayer redplayer)
	{
		redplayers.put(redplayer.getUid(), redplayer);
		File file = new File(this.path + "/" + redplayer.getUid() + ".obj");
		ObjectOutputStream oos;
		BufferedOutputStream bos;

		try
		{
			if(!file.exists())
				file.createNewFile();
			bos = new BufferedOutputStream(new FileOutputStream(file));
			oos = new ObjectOutputStream(bos);
			oos.writeObject(redplayer);
			oos.close();
			bos.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public void removeFaction(RedPlayer redplayer)
	{
		redplayers.remove(redplayer);
		File file = new File(this.path + "/" + redplayer.getUid() + ".obj");
		if (file.exists())
			file.delete();
	}
}


