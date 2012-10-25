/**
 * 
 */
package com.massivecraft.ether.framework.cmds;

/**
 * @author ILOVEPIE
 *
 */
public class CancelSpell extends ECommand {

	/**
	 * cancels the currently active spell
	 */
	public CancelSpell() {
		this.desc = "Cancels the currently active spell.";
	}

	/* (non-Javadoc)
	 * @see com.massivecraft.mcore4.cmd.MCommand#perform()
	 */
	@Override
	public void perform() {
		eme.cancelSpell();
	}

}
