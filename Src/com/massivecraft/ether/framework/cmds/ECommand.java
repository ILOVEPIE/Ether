/**
 * 
 */
package com.massivecraft.ether.framework.cmds;

import com.massivecraft.ether.Plugin;
import com.massivecraft.ether.framework.EPlayer;
import com.massivecraft.mcore4.MPlugin;
import com.massivecraft.mcore4.cmd.MCommand;

/**
 * @author ILOVEPIE
 *
 */
public abstract class ECommand extends MCommand {

	/**
	 * 
	 */
	protected Plugin etherPlugin;
	public ECommand() {
		super();
		etherPlugin = Plugin.etherInstance;
	}
	

	/* (non-Javadoc)
	 * @see com.massivecraft.mcore4.cmd.MCommand#p()
	 */
	@Override
	public MPlugin p() {
		return etherPlugin;
	}
	
	protected EPlayer eme;
	
	@Override
	public void fixSenderVars()
	{
		if (senderIsConsole) return;
		this.eme = EPlayer.get(this.me);
	}

}
