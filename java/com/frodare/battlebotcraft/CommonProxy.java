package com.frodare.battlebotcraft;

import com.frodare.battlebotcraft.entities.Entities;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {

	public void preInit(FMLPreInitializationEvent e) {
		MinecraftForge.EVENT_BUS.register(new EventHandlers());

	}

	public void init(FMLInitializationEvent e) {
		Entities.init();
	}

	public void postInit(FMLPostInitializationEvent e) {

	}
}
