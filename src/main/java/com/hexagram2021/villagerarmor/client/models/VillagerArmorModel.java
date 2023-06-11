package com.hexagram2021.villagerarmor.client.models;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.ListModel;
import net.minecraft.client.model.VillagerHeadModel;
import net.minecraft.client.model.VillagerModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.AbstractVillager;
import org.jetbrains.annotations.NotNull;

public class VillagerArmorModel extends ListModel<AbstractVillager> implements IHumanoidModel, VillagerHeadModel {
	protected ModelPart head;
	protected final ModelPart body;
	protected final ModelPart arms;
	protected final ModelPart leftLeg;
	protected final ModelPart rightLeg;
	
	public VillagerArmorModel(float root) {
		this.texWidth = 64;
		this.texHeight = 32;
		this.head = new ModelPart(this, 0, 0);
		this.head.addBox(-4.0F, -10.0F, -4.0F, 8.0F, 8.0F, 8.0F, root);
		this.body = new ModelPart(this, 16, 16);
		this.body.addBox(-4.0F, 1.0F, -2.0F, 8.0F, 12.0F, 4.0F, root + 1.0F);
		this.rightLeg = new ModelPart(this, 0, 16);
		this.rightLeg.setPos(-2.0F, 12.0F, 0.0F);
		this.rightLeg.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, root - 0.1F);
		this.leftLeg = new ModelPart(this, 0, 16);
		this.leftLeg.mirror = true;
		this.leftLeg.setPos(2.0F, 12.0F, 0.0F);
		this.leftLeg.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, root - 0.1F);
		this.arms = new ModelPart(this, 40, 16);
		this.arms.setPos(0.0F, 2.0F, 0.0F);
		this.arms.addBox(-8.0F, -2.0F, -2.0F, 4.0F, 8.0F, 4.0F, root);
		this.arms.addBox(4.0F, -2.0F, -2.0F, 4.0F, 8.0F, 4.0F, root, true);
	}

	@Override
	public void setHeadVisible(boolean visible) {
		this.head.visible = visible;
	}

	@Override
	public void setHatVisible(boolean visible) {
	}

	@Override
	public void setBodyVisible(boolean visible) {
		this.body.visible = visible;
	}

	@Override
	public void setArmsVisible(boolean visible) {
		this.arms.visible = visible;
	}

	@Override
	public void setLegsVisible(boolean visible) {
		this.leftLeg.visible = this.rightLeg.visible = visible;
	}

	@SuppressWarnings("unused")
	public void copyPropertiesTo(VillagerArmorModel model) {
		super.copyPropertiesTo(model);
		model.head.copyFrom(this.head);
		model.body.copyFrom(this.body);
		model.arms.copyFrom(this.arms);
		model.rightLeg.copyFrom(this.rightLeg);
		model.leftLeg.copyFrom(this.leftLeg);
	}

	@Override
	public <E extends Entity> void propertiesCopyFrom(EntityModel<E> model) {
		this.attackTime = model.attackTime;
		this.riding = model.riding;
		this.young = model.young;
		if(model instanceof VillagerModel) {
			VillagerModel<?> villagerModel = (VillagerModel<?>) model;
			this.head.copyFrom(villagerModel.head);
			this.body.copyFrom(villagerModel.body);
			this.arms.copyFrom(villagerModel.arms);
			this.rightLeg.copyFrom(villagerModel.leg0);
			this.leftLeg.copyFrom(villagerModel.leg1);
		}
	}

	@Override
	public void renderModelToBuffer(PoseStack transform, VertexConsumer builder, int uv2, int overlayType, float r, float g, float b, float a) {
		this.renderToBuffer(transform, builder, uv2, overlayType, r, g, b, a);
	}

	@Override
	public void hatVisible(boolean visible) {
		this.head.visible = visible;
	}

	@Override @NotNull
	public Iterable<ModelPart> parts() {
		return ImmutableList.of(this.head, this.body, this.leftLeg, this.rightLeg, this.arms);
	}

	@Override
	public void setupAnim(@NotNull AbstractVillager entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.head.yRot = netHeadYaw * ((float)Math.PI / 180F);
		this.head.xRot = headPitch * ((float)Math.PI / 180F);
		if (entity.getUnhappyCounter() > 0) {
			this.head.zRot = 0.3F * Mth.sin(0.45F * ageInTicks);
			this.head.xRot = 0.4F;
		} else {
			this.head.zRot = 0.0F;
		}

		this.arms.y = 3.0F;
		this.arms.z = -1.0F;
		this.arms.xRot = -0.75F;
		this.rightLeg.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount * 0.5F;
		this.leftLeg.xRot = Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount * 0.5F;
		this.rightLeg.yRot = 0.0F;
		this.leftLeg.yRot = 0.0F;
	}
}
