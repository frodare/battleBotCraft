package com.frodare.battlebotcraft;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

@Mod(modid = BattleBotCraft.MODID, name = BattleBotCraft.MODNAME, version = BattleBotCraft.VERSION)
public class BattleBotCraft {

	public static final String MODID = "battlebotcraft";
	public static final String VERSION = "1.10.2-1";
	public static final String MODNAME = "BattleBotCraft";

	@SidedProxy(clientSide = "com.frodare.battlebotcraft.ClientProxy", serverSide = "com.frodare.battlebotcraft.ServerProxy")
	public static CommonProxy proxy;

	@Instance(value = BattleBotCraft.MODID)
	public static BattleBotCraft INSTANCE;

	@EventHandler
	public void preInit(FMLPreInitializationEvent e) {
		proxy.preInit(e);
	}

	@EventHandler
	public void init(FMLInitializationEvent e) {
		proxy.init(e);
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent e) {
		proxy.postInit(e);
	}

	@EventHandler
	public void serverLoad(FMLServerStartingEvent e) {

	}

}
