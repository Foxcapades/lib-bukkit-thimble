package io.foxcapades.mc.bukkit.thimble.unsafe

import net.minecraft.core.registries.Registries
import net.minecraft.world.level.saveddata.maps.WorldMap

import org.bukkit.craftbukkit.v1_21_R2.map.CraftMapView

import org.bukkit.NamespacedKey
import org.bukkit.map.MapView

import java.util.UUID

fun MapView(key: NamespacedKey, uniqueId: UUID?): MapView =
  CraftMapView(WorldMap.a(0, false, ResourceKey(Registries.be, key.toMinecraftKey()))
    .also { if (uniqueId != null) it.uniqueId = uniqueId })

fun MapView.getWorldKey(): NamespacedKey =
  CraftMapView::class.java.getDeclaredField("worldMap")
    .apply { isAccessible = true }
    .get(this)
    .let { (it as WorldMap).e }
    .a()
    .toNamespacedKey()
