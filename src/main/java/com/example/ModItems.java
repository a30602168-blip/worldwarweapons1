package com.example;

import com.example.WeaponDefs.Ammo;
import com.example.WeaponDefs.Gun;
import com.example.WeaponDefs.Melee;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;

import java.util.LinkedHashMap;
import java.util.Map;

public final class ModItems {
	private ModItems() {}

	public static final Map<String, Item> ALL = new LinkedHashMap<>();

	/** Registriert ein Item; sein Aussehen kommt aus items/<name>.json. */
	private static void register(String name, int stack) {
		ResourceKey<Item> key = ResourceKey.create(Registries.ITEM, WorldWarWeapons.id(name));
		Item.Properties props = new Item.Properties().stacksTo(stack);
		Item item = new Item(props.setId(key));
		Registry.register(BuiltInRegistries.ITEM, key, item);
		ALL.put(name, item);
	}

	public static void init() {
		for (Ammo a : WeaponDefs.AMMO) register(a.id(), a.stackSize());
		for (Gun g : WeaponDefs.GUNS) register(g.id(), 1);
		for (Melee m : WeaponDefs.MELEE) register(m.id(), 1);
		for (String s : WeaponDefs.ATTACHMENTS) register(s, 16);
		ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.COMBAT).register(e -> ALL.values().forEach(e::accept));
	}
}
