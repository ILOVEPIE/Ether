package com.massivecraft.ether.config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;

import com.massivecraft.ether.Plugin;

public class Conf {

	public static String database = "default";

	public static Conf loadConfig() {
		Conf config = new Conf();
		File datafolder = Plugin.etherInstance.getDataFolder();
		if(!datafolder.exists()){
			datafolder.mkdir();
		}
		File databaseConfig = new File(datafolder, "database.cfg");
		parseCFG(config, databaseConfig);
		return config;
		
	}

	private static void parseCFG(Conf config, File cfgfile) {
		Field[] options = Conf.class.getFields();
		if(cfgfile.exists()){
		try {
			BufferedReader r = new BufferedReader(new InputStreamReader(new FileInputStream(cfgfile)));
			String s = r.readLine();
			while(s != null){
				parseOption(config,options,s);
				s = r.readLine();
			}
		} catch (Exception e) {
		}
		}else{try {
			cfgfile.createNewFile();
		} catch (IOException e) {}}
		
	}

	private static void parseOption(Conf config, Field[] options,
			String option) throws IllegalArgumentException, IllegalAccessException {
		String[] strings = option.split(":");
		if(strings.length<2){
			return;
		}
		for(int i = 0; i<options.length;i++){
			if(strings[0].equalsIgnoreCase(options[i].getName())){
				if(options[i].getType().equals(String.class)){
					options[i].setAccessible(true);
					options[i].set(config, strings[1]);
				}
			}
		}
	}
	

}
