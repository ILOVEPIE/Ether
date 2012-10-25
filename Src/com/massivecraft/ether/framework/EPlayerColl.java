/**
 * 
 */
package com.massivecraft.ether.framework;

import com.massivecraft.ether.Plugin;
import com.massivecraft.ether.config.Conf;
import com.massivecraft.mcore4.store.MStore;
import com.massivecraft.mcore4.store.PlayerColl;



/**
 * @author ILOVEPIE
 *
 */
public class EPlayerColl extends PlayerColl<EPlayer> {

		public EPlayerColl(String name)
		{
			super(MStore.getDb(Conf.database), Plugin.etherInstance, name, EPlayer.class);
		}
		
		@Override
		public boolean isDefault(EPlayer entity)
		{
			if (entity.hasSpells()) return false;
			if (!entity.isNeutral()) return false;
			return true;
		}
}
