package com.hexagram2021.villagerarmor;

import com.hexagram2021.villagerarmor.client.VALModelLayers;
import net.fabricmc.api.ClientModInitializer;

@SuppressWarnings("unused")
public class VillagerArmorLayer implements ClientModInitializer {
	public static final String MODID = "villagerarmor";
	public static final String NAME = "Villager Armor Layer";
	
	@Override
	public void onInitializeClient() {
		VALModelLayers.onRegisterLayers();
	}
}
