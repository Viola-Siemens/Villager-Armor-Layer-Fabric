package com.hexagram2021.villagerarmor.mixin;

import com.hexagram2021.villagerarmor.client.HumanoidArmorLayer;
import com.hexagram2021.villagerarmor.client.models.VillagerArmorModel;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.WitchRenderer;
import net.minecraft.client.renderer.entity.layers.ElytraLayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WitchRenderer.class)
public class WitchRendererMixin {
	@Inject(method = "<init>", at = @At(value = "RETURN"))
	public void addWitchArmorLayer(EntityRenderDispatcher rendererManager, CallbackInfo ci) {
		WitchRenderer current = ((WitchRenderer)(Object)this);
		current.addLayer(new HumanoidArmorLayer<>(current, new VillagerArmorModel(0.5F), new VillagerArmorModel(1.0F)));
		current.addLayer(new ElytraLayer<>(current));
	}
}