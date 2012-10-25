package com.massivecraft.ether.framework.cmds;

import com.massivecraft.mcore4.cmd.HelpCommand;

public class EtherCmdBase extends ECommand{

	private ListSpells listSpells = new ListSpells();
	public EtherCmdBase() {
		super();
		this.addAliases("E","Ether","Aether","Magic","Spell");
		this.addSubCommand(HelpCommand.getInstance());
		this.addSubCommand(listSpells);
		this.desc = "Activate your magical powers!";
	}

	@Override
	public void perform() {
		if(this.senderIsConsole){
			return;
		}
		try{
		int i = Integer.parseInt(this.arg(0));
		if(!eme.setSpell(i)){
			eme.sendMessage("You don't know any such spell!");
		}else{
			//confirm it somehow
		}
		}catch(Exception e){
			HelpCommand.getInstance().execute(this.sender, this.args, this.commandChain);
		}
	}

}
