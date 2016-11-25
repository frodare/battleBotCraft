package com.frodare.battlebotcraft;

import static spark.Spark.get;

import java.util.HashSet;
import java.util.Set;

import com.frodare.battlebotcraft.entities.EntityBattleBot;
import com.frodare.battlebotcraft.entities.EntityBattleBot.BattleBotEvent;

import net.minecraft.entity.Entity;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import scala.collection.mutable.StringBuilder;
import spark.Spark;

public class EventHandlers {

	private Set<EntityBattleBot> entities = new HashSet<>();

	public EventHandlers() {
		
		try {
			Spark.staticFileLocation("/public");
			get("/hello", (req, res) -> "Hello World, boo!");
			get("/hello/:name", (req, res) -> "Hello: " + req.params(":name"));
			
			get("/entities", (req, res) -> {
				System.out.println("/entities");
				StringBuilder s = new StringBuilder();
				s.append("<h2>Entites:</h2>");
				for (Entity e : entities) {
					s.append("<div>Entity: " + e.getName()).append("</div>");
				}

				return s.toString();
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SubscribeEvent
	public void startServer(WorldEvent.Load e) {
		System.out.println("*********** world load *** ");
	}

	@SubscribeEvent
	public void onSpawnEntity(BattleBotEvent.Spawn e) {

		System.out.println("*********** spawn  *** " + e.getEntity().getName());
		entities.add(e.getEntity());
	}

}
