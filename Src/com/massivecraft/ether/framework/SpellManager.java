/**
 * 
 */
package com.massivecraft.ether.framework;

import java.lang.reflect.Modifier;
import java.util.HashMap;

import java.lang.reflect.Method;
import com.massivecraft.ether.framework.interfaces.Spell;

/**
 * @author ILOVEPIE
 *
 */
public final class SpellManager {
	private static HashMap<String,Class<Spell>> spellMap = new HashMap<>();
	
	public static void load() {
		loadSpells(Spell.class);
	}
	
	public static Spell getSpell(String spellCode){
		if(spellMap.containsKey(spellCode)){
			try{
				return spellMap.get(spellCode).newInstance();
			}catch(Exception e){
				return null;
			}
		}else{
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	private static void loadSpells(Class<Spell> spell) {
		int modifiers = spell.getModifiers();
		
		//check if it is an implementation
		if(!(spell.isInterface()||Modifier.isAbstract(modifiers))){
			System.out.println("Loading Spell with Java Path: "+spell.getCanonicalName());
			//grab the class's code and add it to the list
			try{
			Method getspellcode = spell.getMethod("getSpellCode", null);
			getspellcode.setAccessible(true);
			Spell s  = spell.newInstance();
			String code = (String)getspellcode.invoke(s, null);
			spellMap.put(code,spell);
			System.out.println("Load Succeeded");
			}catch(Exception e){System.out.println("Load Failed");}
		}
		for(Class<?> i : spell.getClasses()){
			loadSpells(((Class<Spell>)i));
		}
	}

}
