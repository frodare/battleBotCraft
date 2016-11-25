package com.frodare.battlebotcraft;

import static spark.Spark.get;

import java.util.HashSet;
import java.util.Set;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;
import org.mozilla.javascript.tools.shell.Global;

import com.frodare.battlebotcraft.entities.EntityBattleBot;
import com.frodare.battlebotcraft.entities.EntityBattleBot.BattleBotEvent;

import net.minecraft.entity.Entity;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import scala.collection.mutable.StringBuilder;
import spark.Spark;

public class EventHandlers {

	private int count;
	private Set<EntityBattleBot> entities = new HashSet<>();

	public EventHandlers() {
		runJS();
		try {
			Spark.staticFileLocation("/public");
			get("/hello", (req, res) -> "Hello World");
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

	public void log(String s) {
		System.out.println("LOG (" + count + "): " + s);
	}

	private void runJS() {
		String evaluationScript = "print('TEST!'); BBC.log('TEST FROM JS');";
		Context cx = Context.enter();
		try {
			Scriptable scope = cx.initStandardObjects();
			Global global = new Global(cx);
			Object wrappedValue = Context.javaToJS(this, global);
			ScriptableObject.putProperty(global, "BBC", wrappedValue);
			cx.evaluateString(global, evaluationScript, "EvaluationScript", 1, null);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Context.exit();
		}
	}

	@SubscribeEvent
	public void startServer(WorldEvent.Load e) {

	}

	@SubscribeEvent
	public void onSpawnEntity(BattleBotEvent.Spawn e) {
		entities.add(e.getEntity());
	}

}
