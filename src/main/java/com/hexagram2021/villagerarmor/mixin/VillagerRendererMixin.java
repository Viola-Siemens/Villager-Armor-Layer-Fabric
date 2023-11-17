package com.hexagram2021.villagerarmor.mixin;

import com.hexagram2021.villagerarmor.client.HumanoidArmorLayer;
import com.hexagram2021.villagerarmor.client.VALModelLayers;
import com.hexagram2021.villagerarmor.client.models.VillagerArmorModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.VillagerRenderer;
import net.minecraft.client.renderer.entity.layers.ElytraLayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(VillagerRenderer.class)
public class VillagerRendererMixin {
	@Inject(method = "<init>", at = @At(value = "RETURN"))
	public void addVillagerArmorLayer(EntityRendererProvider.Context context, CallbackInfo ci) {
		VillagerRenderer current = ((VillagerRenderer)(Object)this);
		current.addLayer(new HumanoidArmorLayer<>(
				current,
				new VillagerArmorModel<>(context.bakeLayer(VALModelLayers.VILLAGER_INNER_ARMOR)),
				new VillagerArmorModel<>(context.bakeLayer(VALModelLayers.VILLAGER_OUTER_ARMOR)),
				context.getModelManager()
		));
		current.addLayer(new ElytraLayer<>(current, context.getModelSet()));
	}
}
/*
/summon minecraft:villager ~ ~ ~ {ArmorItems: [{id: "iron_boots", Count: 1, tag: {Trim: {material: "amethyst", pattern: "coast"}}}, {id: "golden_leggings", Count: 1, tag: {Trim: {material: "redstone", pattern: "host"}}}, {id: "diamond_chestplate", Count: 1, tag: {Trim: {material: "gold", pattern: "silence"}}}, {id: "turtle_helmet", Count: 1, tag: {Trim: {material: "iron", pattern: "eye"}}}]}
 */