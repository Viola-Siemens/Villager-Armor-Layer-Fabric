package com.hexagram2021.villagerarmor.mixin;

import com.hexagram2021.villagerarmor.client.HumanoidArmorLayer;
import com.hexagram2021.villagerarmor.client.models.VillagerArmorModel;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.VillagerRenderer;
import net.minecraft.client.renderer.entity.layers.ElytraLayer;
import net.minecraft.server.packs.resources.ReloadableResourceManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(VillagerRenderer.class)
public class VillagerRendererMixin {
	@Inject(method = "<init>", at = @At(value = "RETURN"))
	public void addVillagerArmorLayer(EntityRenderDispatcher rendererManager, ReloadableResourceManager resourceManager, CallbackInfo ci) {
		VillagerRenderer current = ((VillagerRenderer)(Object)this);
		current.addLayer(new HumanoidArmorLayer<>(current, new VillagerArmorModel(0.5F), new VillagerArmorModel(1.0F)));
		current.addLayer(new ElytraLayer<>(current));
	}
}
/*
/summon minecraft:villager ~ ~ ~ {ArmorItems: [{id: "iron_boots", Count: 1}, {id: "golden_leggings", Count: 1}, {id: "diamond_chestplate", Count: 1}, {id: "turtle_helmet", Count: 1}]}
 */