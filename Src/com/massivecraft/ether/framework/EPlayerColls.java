/**
 * 
 */
package com.massivecraft.ether.framework;

import com.massivecraft.ether.Plugin;
import com.massivecraft.ether.config.Const;
import com.massivecraft.mcore4.store.Colls;
import com.massivecraft.mcore4.usys.Aspect;

/**
 * @author ILOVEPIE
 *
 */
public class EPlayerColls extends Colls<EPlayerColl, EPlayer, String>{

	public static EPlayerColls ePlayerCollsInstance = new EPlayerColls();
	@Override
	public Aspect getAspect() {
		return Plugin.etherInstance.playerAspect;
	}

	@Override
	public String getBasename() {
		return Const.playerBasename;
	}

	@Override
	public EPlayerColl createColl(String name) {
		return new EPlayerColl(name);
	}

}
