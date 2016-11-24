package com.frodare.battlebotcraft.entities.render;

import java.util.List;

import com.frodare.battlebotcraft.BattleBotCraft;
import com.frodare.battlebotcraft.entities.EntityBattleBot;
import com.frodare.battlebotcraft.entities.model.ModelBattleBot;
import com.google.common.collect.Lists;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderBattleBot extends RenderBiped<EntityBattleBot> {

	private static final ResourceLocation TEXTURES = new ResourceLocation(BattleBotCraft.MODID + ":textures/entity/battlebot/battlebot.png");

	private final ModelBattleBot defaultModel;

	private final List<LayerRenderer<EntityBattleBot>> defaultLayers;

	public RenderBattleBot(RenderManager renderManagerIn) {
		super(renderManagerIn, new ModelBattleBot(), 0.5F, 1.0F);

		LayerRenderer<?> layerrenderer = (LayerRenderer) this.layerRenderers.get(0);
		defaultModel = (ModelBattleBot) modelBipedMain;

		addLayer(new LayerHeldItem(this));

		LayerBipedArmor layerbipedarmor = new LayerBipedArmor(this) {
			protected void initArmor() {
				modelLeggings = new ModelZombie(0.5F, true);
				modelArmor = new ModelZombie(1.0F, true);
			}
		};
		addLayer(layerbipedarmor);

		this.defaultLayers = Lists.newArrayList(this.layerRenderers);

	}

	/**
	 * Allows the render to do state modifications necessary before the model is
	 * rendered.
	 */
	protected void preRenderCallback(EntityBattleBot entitylivingbaseIn, float partialTickTime) {
		super.preRenderCallback(entitylivingbaseIn, partialTickTime);
	}

	/**
	 * Renders the desired {@code T} type Entity.
	 */
	public void doRender(EntityBattleBot entity, double x, double y, double z, float entityYaw, float partialTicks) {
		this.swapArmor(entity);
		this.setModelVisibilities(entity);


		super.doRender(entity, x, y, z, entityYaw, partialTicks);
	}


	private void setModelVisibilities(EntityBattleBot clientPlayer) {
		ModelBattleBot modelplayer = (ModelBattleBot) this.getMainModel();

		ItemStack itemstack = clientPlayer.getHeldItemMainhand();
		ItemStack itemstack1 = clientPlayer.getHeldItemOffhand();
		modelplayer.setInvisible(true);

		modelplayer.isSneak = clientPlayer.isSneaking();
		ModelBiped.ArmPose modelbiped$armpose = ModelBiped.ArmPose.EMPTY;
		ModelBiped.ArmPose modelbiped$armpose1 = ModelBiped.ArmPose.EMPTY;

		if (itemstack != null) {
			modelbiped$armpose = ModelBiped.ArmPose.ITEM;

			if (clientPlayer.getItemInUseCount() > 0) {
				EnumAction enumaction = itemstack.getItemUseAction();

				if (enumaction == EnumAction.BLOCK) {
					modelbiped$armpose = ModelBiped.ArmPose.BLOCK;
				} else if (enumaction == EnumAction.BOW) {
					modelbiped$armpose = ModelBiped.ArmPose.BOW_AND_ARROW;
				}
			}
		}

		if (itemstack1 != null) {
			modelbiped$armpose1 = ModelBiped.ArmPose.ITEM;

			if (clientPlayer.getItemInUseCount() > 0) {
				EnumAction enumaction1 = itemstack1.getItemUseAction();

				if (enumaction1 == EnumAction.BLOCK) {
					modelbiped$armpose1 = ModelBiped.ArmPose.BLOCK;
				}
			}
		}

		if (clientPlayer.getPrimaryHand() == EnumHandSide.RIGHT) {
			modelplayer.rightArmPose = modelbiped$armpose;
			modelplayer.leftArmPose = modelbiped$armpose1;
		} else {
			modelplayer.rightArmPose = modelbiped$armpose1;
			modelplayer.leftArmPose = modelbiped$armpose;
		}

	}

	/**
	 * Returns the location of an entity's texture. Doesn't seem to be called
	 * unless you call Render.bindEntityTexture.
	 */
	protected ResourceLocation getEntityTexture(EntityBattleBot entity) {
		return TEXTURES;

	}

	private void swapArmor(EntityBattleBot zombie) {

		this.mainModel = this.defaultModel;
		this.layerRenderers = this.defaultLayers;

		this.modelBipedMain = (ModelBiped) this.mainModel;
	}

	protected void rotateCorpse(EntityBattleBot entityLiving, float p_77043_2_, float p_77043_3_, float partialTicks) {

		super.rotateCorpse(entityLiving, p_77043_2_, p_77043_3_, partialTicks);
	}
}