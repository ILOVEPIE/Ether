/**
 * 
 */
package com.massivecraft.ether.test;

import org.bukkit.entity.Entity;

import com.massivecraft.ether.framework.EPlayer;
import com.massivecraft.ether.framework.interfaces.Spell;

/**
 * @author ILOVEPIE
 *
 */
public class Arrow implements Spell {

	private Entity target;
	private EPlayer caster;
	
	public static String getSpellCode(){
		return "0";
	}

	/* (non-Javadoc)
	 * @see com.massivecraft.ether.framework.interfaces.Spell#getDescription()
	 */
	@Override
	public String getDescription() {
		return "A test spell";
	}

	/* (non-Javadoc)
	 * @see com.massivecraft.ether.framework.interfaces.Spell#getLevel()
	 */
	@Override
	public byte getLevel() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.massivecraft.ether.framework.interfaces.Spell#setParam(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean setParam(String name, String value) {
		return false;
	}

	/* (non-Javadoc)
	 * @see com.massivecraft.ether.framework.interfaces.Spell#setTarget(org.bukkit.entity.Entity)
	 */
	@Override
	public void setTarget(Entity target) {
		this.target = target;

	}

	/* (non-Javadoc)
	 * @see com.massivecraft.ether.framework.interfaces.Spell#setCaster(com.massivecraft.ether.framework.EPlayer)
	 */
	@Override
	public void setCaster(EPlayer caster) {
		this.caster = caster;
	}

	/* (non-Javadoc)
	 * @see com.massivecraft.ether.framework.interfaces.Spell#clearParams()
	 */
	@Override
	public void clearParams() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.massivecraft.ether.framework.interfaces.Spell#cast()
	 */
	@Override
	public void cast() {
		caster.getPlayer().launchProjectile(org.bukkit.entity.Arrow.class);
	}

}
