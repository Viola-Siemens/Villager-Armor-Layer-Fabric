package com.hexagram2021.villagerarmor.mixin;

import com.hexagram2021.villagerarmor.client.HumanoidArmorLayer;
import com.hexagram2021.villagerarmor.client.VALModelLayers;
import com.hexagram2021.villagerarmor.client.models.IllagerArmorModel;
import net.minecraft.client.model.IllagerModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
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
	public void addIllagerArmorLayer(EntityRendererProvider.Context context, IllagerModel<T> illagerModel, float shadowRadius, CallbackInfo ci) {
		IllagerRenderer<T> current = ((IllagerRenderer<T>)(Object)this);
		current.addLayer(new HumanoidArmorLayer<>(
				current,
				new IllagerArmorModel<>(context.bakeLayer(VALModelLayers.ILLAGER_INNER_ARMOR)),
				new IllagerArmorModel<>(context.bakeLayer(VALModelLayers.ILLAGER_OUTER_ARMOR)),
				context.getModelManager()
		));
		current.addLayer(new ElytraLayer<>(current, context.getModelSet()));
	}
}