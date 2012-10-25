package com.massivecraft.ether.framework;

import net.minecraft.server.ItemStack;
import net.minecraft.server.NBTTagCompound;
import net.minecraft.server.NBTTagList;
import net.minecraft.server.NBTTagString;

import org.bukkit.craftbukkit.inventory.CraftItemStack;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;


public class Listener implements org.bukkit.event.Listener {
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onRightClick(PlayerInteractEntityEvent event){
		EPlayer etherplayer = EPlayer.get(event.getPlayer());
		if(etherplayer.hasActiveSpell()&&(event.getPlayer().getItemInHand()==null||event.getPlayer().getItemInHand().getTypeId()==0)){
			event.setCancelled(true);
			etherplayer.cast(event.getRightClicked());
		}else if(event.getPlayer().getItemInHand().getTypeId()==340){
			event.setCancelled(true);
		}
	}
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onRightClick(PlayerInteractEvent event){
		EPlayer etherplayer = EPlayer.get(event.getPlayer());
		if(etherplayer.hasActiveSpell()&&(event.getPlayer().getItemInHand()==null||event.getPlayer().getItemInHand().getTypeId()==0)){
			event.setCancelled(true);
			etherplayer.cast(null);
		}else if(event.getPlayer().getItemInHand().getTypeId()==340){
			event.setCancelled(false);
			ItemStack item = CraftItemStack.createNMSItemStack(event.getPlayer().getItemInHand());
			NBTTagCompound nbttag = item.getTag();
			NBTTagList list = nbttag.getList("pages"); 
			for(int i = 0; i<list.size();i++){
				String s = ((NBTTagString)list.get(i)).data;
				if(s.contains("[SPELLCODE:")&&s.contains(":/SPELLCODE]")){
					String[] splitstring = s.split("[SPELLCODE:");
					s = splitstring[0];
					splitstring = splitstring[1].split(":/SPELLCODE]");
					s += splitstring[1];
					etherplayer.AddSpell(splitstring[0]);
					
				}
				((NBTTagString)list.get(i)).data = s;
			}
			event.getPlayer().setItemInHand(new CraftItemStack(item));
			etherplayer.sendMessage("Spellbook used! It's now just an ordinary book... oh well...");
		}
	}
}
