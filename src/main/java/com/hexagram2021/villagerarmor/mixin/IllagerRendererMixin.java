package com.hexagram2021.villagerarmor.mixin;

import com.hexagram2021.villagerarmor.client.HumanoidArmorLayer;
import com.hexagram2021.villagerarmor.client.models.IllagerArmorModel;
import net.minecraft.client.model.IllagerModel;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.IllagerRenderer;
import net.minecraft.client.renderer.entity.layers.ElytraLayer;
import net.minecraft.world.entity.monster.AbstractIllager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(IllagerRenderer.class)
public class IllagerRendererMixin<T extends AbstractIllager> {
	@SuppressWarnings("unchecked")
	@Inject(method = "<init>", at = @At(value = "RETURN"))
	public void addIllagerArmorLayer(EntityRenderDispatcher rendererManager, IllagerModel<T> model, float shadowRadius, CallbackInfo ci) {
		IllagerRenderer<T> current = ((IllagerRenderer<T>)(Object)this);
		current.addLayer(new HumanoidArmorLayer<>(current, new IllagerArmorModel(0.5F), new IllagerArmorModel(1.0F)));
		current.addLayer(new ElytraLayer<>(current));
	}
}