/**
 * 
 */
package com.massivecraft.ether;

import com.massivecraft.ether.config.Conf;
import com.massivecraft.ether.framework.SpellManager;
import com.massivecraft.mcore4.MPlugin;

/**
 * @author ILOVEPIE
 *
 */
public class Plugin extends MPlugin {
	
	public static Plugin etherInstance;
	
	public EtherCmdBase etherCommandBase;
	
	Conf config;
	
	public void Plugin(){
		Plugin.etherInstance = this;
	}
	
	@Override
	public void onEnable(){
		loadConfig();
		loadSpells();
		
	}

	private void loadSpells() {
		SpellManager.load();
	}

	private void loadConfig() {
		config = Conf.loadConfig();
	}

}
