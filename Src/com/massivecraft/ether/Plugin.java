/**
 * 
 */
package com.massivecraft.ether;

import org.bukkit.Bukkit;

import com.massivecraft.ether.config.Conf;
import com.massivecraft.ether.config.Const;
import com.massivecraft.ether.framework.Listener;
import com.massivecraft.ether.framework.SpellManager;
import com.massivecraft.ether.framework.cmds.EtherCmdBase;
import com.massivecraft.mcore4.MPlugin;
import com.massivecraft.mcore4.usys.Aspect;
import com.massivecraft.mcore4.usys.AspectColl;

/**
 * @author ILOVEPIE
 * 
 */
public class Plugin extends MPlugin {

	public static Plugin etherInstance;

	public EtherCmdBase etherCommandBase;

	Conf config;

	public Aspect playerAspect;

	public Plugin() {
		Plugin.etherInstance = this;
	}

	@Override
	public void onEnable() {
		if (!preEnable())
			return;

		// Init aspects
		this.playerAspect = AspectColl.i.get(Const.playerAspectId, true);
		this.playerAspect.register();
		this.playerAspect
				.setDesc("<i>Every player's alignment and spells:",
						"<i>the player's spells.",
						"<i>What is their alignment?", "Add spells by simply installing a plugin that has spell-implementing classes!");
		loadConfig();
		loadSpells();
		etherCommandBase=new EtherCmdBase();
		etherCommandBase.register();
		Bukkit.getServer().getPluginManager().registerEvents(new Listener(),this);

	}

	private void loadSpells() {
		SpellManager.load();
	}

	private void loadConfig() {
		config = Conf.loadConfig();
	}

}
