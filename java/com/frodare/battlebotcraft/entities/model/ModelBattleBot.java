package com.frodare.battlebotcraft.entities.model;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ModelBattleBot extends ModelBiped {

	private final float modelSize;
	private final float yOffset = 0f;


	private static final float DEFAULT_CAPE_ANGLE = 0.08f;

	public ModelBattleBot() {
		this(0.0F);
	}

	public ModelBattleBot(float modelSize) {
		super(modelSize, 0, 64, 64);
		this.modelSize = modelSize;

	}


	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
		super.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor, entityIn);

	}

}