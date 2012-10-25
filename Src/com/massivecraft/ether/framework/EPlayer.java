package com.massivecraft.ether.framework;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Vector;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import com.massivecraft.ether.framework.interfaces.Spell;
import com.massivecraft.ether.test.Arrow;
import com.massivecraft.mcore4.store.PlayerEntity;
import com.massivecraft.vampire.VPlayer;
import com.massivecraft.vampire.VPlayerColls;


public class EPlayer extends PlayerEntity<EPlayer> {

	@Override
	protected EPlayer getThis() {
		return this;
	}

	@Override
	protected Class<EPlayer> getClazz() {
		return EPlayer.class;
	}
	
	public float alignment = 0.0f;
	public List<String> spells = new Vector<String>();
	public transient Spell activeSpell;
	
	public boolean canDarkMagic(){
		if(Bukkit.getPluginManager().isPluginEnabled("Vampire")||Bukkit.getPluginManager().isPluginEnabled("vampire")){
			VPlayer p = VPlayerColls.i.getForWorld(this.getPlayer().getWorld().getName()).get(this.getPlayer());
			return (!p.isHuman()||p.isInfected());
		}else{
			return true;
		}
		/*ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		World world = this.getPlayer().getWorld();
		Class<?> collections = classloader.loadClass("com.massivecraft.vampire.VPlayerColls");
		Field i = collections.getDeclaredField("i");
		i.setAccessible(true);
		Class<?> vamplist = i.getType();
		Method getforWorld = vamplist.getDeclaredMethod("getForWorld", String.class);
		getforWorld.setAccessible(true);
		Object listInWorld = getforWorld.invoke(i.get(null), world.getName());
		Method getPlayerInWorld = listInWorld.getClass().getMethod("get", Player.class);
		getPlayerInWorld.setAccessible(true);
		Object vplayer = getPlayerInWorld.invoke(listInWorld, this.getPlayer());
		Method isVampire = vplayer.getClass().getMethod("isVampire", null);
		isVampire.setAccessible(true);
		Method isInfected = vplayer.getClass().getMethod("isInfected", null);
		isInfected.setAccessible(true);
		return ((Boolean)isVampire.invoke(vplayer, null))||((Boolean)isInfected.invoke(vplayer,null));*/
		
		
	}
	public boolean hasActiveSpell(){
		return this.activeSpell!=null;
	}
	public boolean canLightMagic(){
		if(Bukkit.getPluginManager().isPluginEnabled("Vampire")||Bukkit.getPluginManager().isPluginEnabled("vampire")){
			return VPlayerColls.i.getForWorld(this.getPlayer().getWorld().getName()).get(this.getPlayer()).isHuman();
		}else{
			return true;
		}
		/*ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		World world = this.getPlayer().getWorld();
		Class<?> collections = classloader.loadClass("com.massivecraft.vampire.VPlayerColls");
		Field i = collections.getDeclaredField("i");
		i.setAccessible(true);
		Class<?> vamplist = i.getType();
		Method getforWorld = vamplist.getDeclaredMethod("getForWorld", String.class);
		getforWorld.setAccessible(true);
		Object listInWorld = getforWorld.invoke(i.get(null), world.getName());
		Method getPlayerInWorld = listInWorld.getClass().getMethod("get", Player.class);
		getPlayerInWorld.setAccessible(true);
		Object vplayer = getPlayerInWorld.invoke(listInWorld, this.getPlayer());
		Method isVampire = vplayer.getClass().getMethod("isVampire", null);
		isVampire.setAccessible(true);
		return !((Boolean)isVampire.invoke(vplayer, null));*/
	}
	
	public void AddSpell(String spell){
		this.spells.add(spell);
	}
	public void RemoveSpell(String spell){
		this.spells.remove(spell);
	}
	public Spell getSpell(int i){
		return SpellManager.getSpell(this.spells.get(i));
	}
	public String getSpellList(){
		this.AddSpell(Arrow.getSpellCode());
		String spells = "Spells:\n";
		for(int i = 0; i<this.spells.size();i++){
			spells += i+". "+ SpellManager.getSpell(this.spells.get(i)).getDescription()+"\n";
		}
		return spells;
	}
	public void RemoveSpells(){
		this.spells.clear();
	}
	public boolean hasSpells(){
		return this.spells.isEmpty();
	}
	public boolean isNeutral(){
		return this.alignment==0;
	}
	private final static transient EPlayer defaultInstance = new EPlayer();
	@Override public EPlayer getDefaultInstance() { return defaultInstance; }

	public static EPlayer get(Player me) {
		return EPlayerColls.ePlayerCollsInstance.getForWorld(me.getWorld().getName()).get(me);
	}

	public boolean setSpell(int i) {
		try{
			this.activeSpell = this.getSpell(i);
		}catch(Exception e){
			return false;
		}
		return true;
	}
	
	public void cast(Entity target){
		this.activeSpell.setCaster(this);
		this.activeSpell.setTarget(target);
		this.getPlayer().setFoodLevel(this.getPlayer().getFoodLevel()-Math.abs(this.activeSpell.getLevel())/2);
		if(checkCanCast()){
			this.activeSpell.cast();
		}else{
			this.getPlayer().damage(Math.abs(this.activeSpell.getLevel()));
		}
	}

	private boolean checkCanCast() {
		if(this.activeSpell.getLevel()>0){
			if(this.activeSpell.getLevel()>this.alignment)return false;
			return this.canLightMagic();
		}
		if(this.activeSpell.getLevel()<0){
			if(this.activeSpell.getLevel()<this.alignment)return false;
			return this.canDarkMagic();
		}
		return true;
	}

	public void cancelSpell() {
		this.activeSpell = null;
	}
}
