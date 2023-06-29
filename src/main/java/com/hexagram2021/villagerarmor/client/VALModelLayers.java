package com.hexagram2021.villagerarmor.client;

import com.hexagram2021.villagerarmor.client.models.IllagerArmorModel;
import com.hexagram2021.villagerarmor.client.models.VillagerArmorModel;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.resources.ResourceLocation;

import static com.hexagram2021.villagerarmor.VillagerArmorLayer.MODID;

public class VALModelLayers {
	public static final ModelLayerLocation VILLAGER_INNER_ARMOR = registerInnerArmor("villager");
	public static final ModelLayerLocation VILLAGER_OUTER_ARMOR = registerOuterArmor("villager");
	public static final ModelLayerLocation ILLAGER_INNER_ARMOR = registerInnerArmor("illager");
	public static final ModelLayerLocation ILLAGER_OUTER_ARMOR = registerOuterArmor("illager");
	
	private static ModelLayerLocation registerInnerArmor(String name) {
		return register(name, "inner_armor");
	}
	
	private static ModelLayerLocation registerOuterArmor(String name) {
		return register(name, "outer_armor");
	}
	
	private static ModelLayerLocation register(String name, String layer) {
		return new ModelLayerLocation(new ResourceLocation(MODID, name), layer);
	}
	
	public static void onRegisterLayers() {
		EntityModelLayerRegistry.registerModelLayer(VILLAGER_INNER_ARMOR, () -> VillagerArmorModel.createBodyLayer(new CubeDeformation(0.0F), 0.0F, 0.25F));
		EntityModelLayerRegistry.registerModelLayer(VILLAGER_OUTER_ARMOR, () -> VillagerArmorModel.createBodyLayer(new CubeDeformation(1.0F), 0.0F, -0.5F));
		EntityModelLayerRegistry.registerModelLayer(ILLAGER_INNER_ARMOR, () -> IllagerArmorModel.createBodyLayer(new CubeDeformation(0.0F), 0.0F, 0.25F));
		EntityModelLayerRegistry.registerModelLayer(ILLAGER_OUTER_ARMOR, () -> IllagerArmorModel.createBodyLayer(new CubeDeformation(1.0F), 0.0F, -0.5F));
	}
}