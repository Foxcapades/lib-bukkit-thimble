package io.foxcapades.mc.bukkit.thimble.unsafe

import org.bukkit.craftbukkit.v1_21_R2.inventory.CraftMetaSpawnEgg

import org.bukkit.inventory.meta.SpawnEggMeta

fun SpawnEggMeta(): SpawnEggMeta =
  CraftMetaSpawnEgg::class.java
    .getConstructor(CraftMetaItemClass)
    .apply { isAccessible = true }
    .newInstance(null)
