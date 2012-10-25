/**
 * 
 */
package com.massivecraft.ether.framework.cmds;

import java.util.ArrayList;

/**
 * @author ILOVEPIE
 *
 */
public class ListSpells extends ECommand {

	/**
	 * 
	 */
	public ListSpells() {
		this.desc = "Lists the spells you know.";
	}

	/* (non-Javadoc)
	 * @see com.massivecraft.mcore4.cmd.MCommand#perform()
	 */
	@Override
	public void perform() {
		String[] lines = eme.getSpellList().replace("\t", "    ").split("\n");
		for(int i = 0; i<lines.length;i++){
			eme.sendMessage(lines[i]);
		}
		
	}

}
