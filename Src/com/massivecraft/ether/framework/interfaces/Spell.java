/**
 * 
 */
package com.massivecraft.ether.framework.interfaces;

/**
 * When you implement a Spell you must also implment the method getSpellCode() which returns a string with the spell code in it.
 * @author ILOVEPIE
 *
 */
public interface Spell {

	/**
	 * Fetch the spell's description
	 * @return String[] representing each line of the description.
	 */
	String[] getDescription();
	
	/**
	 * Negative numbers represent a dark level, positive numbers represent a light level.
	 * @return required dark/light value
	 */
	byte getLevel();
	
	/**
	 * Set a parameter for casting the spell
	 * @param name name of the parameter.
	 * @param value value of the parameter.
	 * @return true if successful,false otherwise.
	 */
	boolean setParam(String name, String value);
	/**
	 * Clear All Casting Parameters.
	 */
	void clearParams();
	
	/**
	 * Casts the spell.
	 */
	void cast();
	
	
}
